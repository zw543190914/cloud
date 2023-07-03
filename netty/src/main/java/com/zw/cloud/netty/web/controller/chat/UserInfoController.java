package com.zw.cloud.netty.web.controller.chat;


import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.common.utils.DingTalkUtils;
import com.zw.cloud.common.utils.JjwtUtils;
import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.enums.SearchFriendsStatusEnum;
import com.zw.cloud.netty.web.entity.bo.UserBO;
import com.zw.cloud.netty.web.entity.chat.ChatMsg;
import com.zw.cloud.netty.web.entity.chat.UserInfo;
import com.zw.cloud.netty.web.entity.vo.FriendsRequestVO;
import com.zw.cloud.netty.web.entity.vo.MyFriendsVO;
import com.zw.cloud.netty.web.entity.vo.UserVo;
import com.zw.cloud.netty.web.service.api.chat.IChatMsgService;
import com.zw.cloud.netty.web.service.api.chat.IUserInfoService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
@RestController
@RequestMapping("/chat/user")
@Slf4j
public class UserInfoController {

    @Autowired
    IUserInfoService userServices;
    @Autowired
    IChatMsgService chatMsgService;

    /**
     * 用户登录与注册
     */
    @PostMapping("/registerOrLogin")
    //http://localhost:18092/chat/user/registerOrLogin
    public UserVo registerOrLogin(UserInfo user) {
        return userServices.registerOrLogin(user);
    }

    /**
     * 用户登录与注册
     */
    @PostMapping("/refreshAccessToken")
    //http://localhost:18092/chat/user/refreshAccessToken
    public UserVo refreshAccessToken(Long userId) {
        return userServices.refreshAccessToken(userId);
    }
    /**
     * 用户头像上传访问方法
     */
    @PostMapping("/uploadFaceBase64")
    public UserVo uploadFaceBase64(@RequestBody UserBO userBO) {
        //获取前端传过来的base64的字符串，然后转为文件对象在进行上传
        String base64Data = userBO.getFaceData();
        log.info("[UserInfoController][uploadFaceBase64]userBO is {}", JSON.toJSONString(userBO));
        //更新用户头像
        UserInfo user = new UserInfo();
        user.setId(userBO.getUserId());
        user.setFaceImage(base64Data);
        user.setFaceImageBig(base64Data);
        userServices.updateById(user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    /**
     * 用户头像上传访问方法
     */
    @PostMapping("/uploadFace")
    public UserVo uploadFace(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
        String base64Data = Base64.encodeBase64String(file.getBytes());
        String userId = request.getParameter("userId");
        log.info("[UserInfoController][uploadFaceBase64]base64Data is {}", base64Data);
        //更新用户头像
        UserInfo user = new UserInfo();
        user.setId(Long.parseLong(userId));
        user.setFaceImage(base64Data);
        user.setFaceImageBig(base64Data);
        userServices.updateById(user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    /**
     * 修改昵称
     */
    @PostMapping("/setNickname")
    public UserVo setNickName(UserInfo user){
        log.info("[UserInfoController][setNickName]user is {}", JSON.toJSONString(user));
        userServices.updateById(user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    @PostMapping("/searchFriend")
    //http://localhost:18092/chat/user/searchFriend?myUserId=&friendUserName=
    public UserVo searchFriend(Long myUserId,String friendUserName){
        log.info("[UserInfoController][searchFriend]myUserId is {},friendUserName is {}", myUserId,friendUserName);
        if(Objects.isNull(myUserId) || StringUtils.isBlank(friendUserName)){
            throw new BizException("好友信息为空");
        }
        /**
         * 前置条件：
         * 1.搜索的用户如果不存在，则返回【无此用户】
         * 2.搜索的账号如果是你自己，则返回【不能添加自己】
         * 3.搜索的朋友已经是你好友，返回【该用户已经是你的好友】
         */
        SearchFriendsStatusEnum status = userServices.preconditionSearchFriends(myUserId,friendUserName);
        if(status.getStatus().equals(SearchFriendsStatusEnum.SUCCESS.status)){
            UserInfo user = userServices.checkUserNameIsExit(friendUserName);
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            return userVo;
        } else {
            throw new BizException(status.msg);
        }
    }

    /**
     * 发送添加好友请求的方法
     */
    @PostMapping("/addFriendRequest")
    public void addFriendRequest(Long myUserId,String friendUserName){
        log.info("[UserInfoController][addFriendRequest]myUserId is {},friendUserName is {}", myUserId,friendUserName);

        if(Objects.isNull(myUserId) || StringUtils.isBlank(friendUserName)){
            throw new BizException("好友信息为空");
        }

        SearchFriendsStatusEnum status = userServices.preconditionSearchFriends(myUserId,friendUserName);
        if(status.status.equals(SearchFriendsStatusEnum.SUCCESS.status)){
            try {
                userServices.sendFriendRequest(myUserId,friendUserName);
            } catch (DuplicateKeyException e) {
                throw new BizException("添加好友申请已发送,请勿重新发送");
            }
            log.info("[UserInfoController][addFriendRequest]myUserId is {},friendUserName is {}, end", myUserId,friendUserName);

        } else {
            throw new BizException(status.msg);
        }

    }

    /**
     * 好友请求列表查询
     */
    @PostMapping("/queryFriendRequest")
    public List<FriendsRequestVO> queryFriendRequest(Long userId){
        return userServices.queryFriendRequestList(userId);
    }

    /**
     * 好友请求处理
     */
    @PostMapping("/operFriendRequest")
    public List<MyFriendsVO> operFriendRequest(Long acceptUserId,Long sendUserId,Integer operType){
        log.info("[UserInfoController][operFriendRequest]acceptUserId is {},sendUserId is {},operType is {}", acceptUserId,sendUserId,operType);
        return userServices.operFriendRequest(acceptUserId, sendUserId, operType);
    }

    /**
     * 好友列表查询
     * @param userId
     * @return
     */
    @PostMapping("/myFriends")
    public List<MyFriendsVO> myFriends(Long userId){
        log.info("[UserInfoController][myFriends]userId is {}", userId);
        if (Objects.isNull(userId)){
            throw new BizException("用户id为空");
        }
        //数据库查询好友列表
        List<MyFriendsVO> myFriendsVOS = userServices.queryMyFriends(userId);
        log.info("[UserInfoController][myFriends]myFriendsVOS is {}", JSON.toJSONString(myFriendsVOS));
        return myFriendsVOS;
    }

    /**
     * 用户手机端获取未签收的消息列表
     */
    @PostMapping("/getUnReadMsgList")
    public List<ChatMsg> getUnReadMsgList(Long acceptUserId){
        if(Objects.isNull(acceptUserId)){
            throw new BizException("接收者ID不能为空");
        }
        //根据接收ID查找未签收的消息列表
        return chatMsgService.getUnReadMsgList(acceptUserId);
    }

    @PostMapping("/suggest")
    public void suggest(@RequestBody NettyMsgDTO nettyMsgDTO) throws Exception{
        DingTalkUtils.sendDingTalkMsg(null,nettyMsgDTO.getUserId(),nettyMsgDTO.getData());
    }

    /**
     * 校验 token 是否过期
     */
    @PostMapping("/checkAccessTokenExpiration")
    public Boolean checkAccessTokenExpiration(String accessToken){
        log.info("[UserInfoController][checkAccessTokenExpiration]accessToken is {}", accessToken);
        // 过期会抛出异常 ExpiredJwtException
        Claims claims;
        try {
            claims = JjwtUtils.parseJwt(accessToken);
            log.info("[UserInfoController][checkAccessTokenExpiration]username is {}", claims.getSubject());
        } catch (Exception e) {
            return true;
        }
        String userId = claims.getId();
        return StringUtils.isBlank(userId);
    }

    /**
     * 模拟一些用户
     */
    @GetMapping("/mockUser")
    //http://localhost:18092/chat/user/mockUser
    public void mockUser() {
        String userName = "云浅月、风萧萧、枫无痕、溯汐潮、类似情、说再见、不多情、秋千索、小浪漫、誶誶淰、琉璃丶子、薄荷绿ζ、雲朵兒、" +
                "笑忘初、挽青丝、旧萤火、久旧酒、半情歌、欧啦啦、莓小汐、乱人心、对白说、背影落、墨花残、泪染裳、薄%荷凉、" +
                "情未%了、之#若素、不二❣心、伴度微、花染颜、不｜将就、半心人、离情几度、清风挽心、风花雪月、" +
                "北墓南笙、晨曦慕雪、花开宿语、清风疏影、一壶温酒、浮萍一絮、素年凉音、安之若曦、七色凉橙、长裙绿衣、" +
                "花影何处、离雨弥港、流苏如画、听风忆雪、舞叶之秋、予之笑颜、静谧而安、似梦似幻、静谧幽蓝、幻墨如烟、何以心动、" +
                "清风入梦、浅语花开、烟雨醉巷、似水往昔、一抹晚夏、素年锦媛、盛世流光、几分曾经、折现浪漫、哑剧真动、暮色年華、瑜锦婼殇";
        String[] split = userName.split("、");
        List<UserInfo> userInfos = Lists.newArrayList(split).stream().map(name -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(name);
            userInfo.setPassword("123");
            return userInfo;
        }).collect(Collectors.toList());
        userInfos.forEach(userInfo -> userServices.registerOrLogin(userInfo));
    }
}

