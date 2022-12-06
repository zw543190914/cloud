package com.zw.cloud.netty.web.controller.chat;


import com.alibaba.fastjson.JSON;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.netty.enums.SearchFriendsStatusEnum;
import com.zw.cloud.netty.web.entity.bo.UserBO;
import com.zw.cloud.netty.web.entity.chat.ChatMsg;
import com.zw.cloud.netty.web.entity.chat.UserInfo;
import com.zw.cloud.netty.web.entity.vo.FriendsRequestVO;
import com.zw.cloud.netty.web.entity.vo.MyFriendsVO;
import com.zw.cloud.netty.web.entity.vo.UserVo;
import com.zw.cloud.netty.web.service.api.chat.IChatMsgService;
import com.zw.cloud.netty.web.service.api.chat.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @RequestMapping("/registerOrLogin")
    public UserVo registerOrLogin(UserInfo user) {
        return userServices.registerOrLogin(user);
    }

    /**
     * 用户头像上传访问方法
     */
    @RequestMapping("/uploadFaceBase64")
    public UserVo uploadFaceBase64(@RequestBody UserBO userBO) throws Exception {
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
     * 修改昵称
     */
    @RequestMapping("/setNickname")
    public UserVo setNickName(UserInfo user){
        userServices.updateById(user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    @RequestMapping("/searchFriend")
    public UserVo searchFriend(String myUserId,String friendUserName){
        if(StringUtils.isBlank(myUserId)|| StringUtils.isBlank(friendUserName)){
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
    @RequestMapping("/addFriendRequest")
    public void addFriendRequest(String myUserId,String friendUserName){
        if(StringUtils.isBlank(myUserId)|| StringUtils.isBlank(friendUserName)){
            throw new BizException("好友信息为空");
        }

        SearchFriendsStatusEnum status = userServices.preconditionSearchFriends(myUserId,friendUserName);
        if(status.status.equals(SearchFriendsStatusEnum.SUCCESS.status)){
            userServices.sendFriendRequest(myUserId,friendUserName);
        } else {
            throw new BizException(status.msg);
        }

    }

    /**
     * 好友请求列表查询
     */
    @RequestMapping("/queryFriendRequest")
    public List<FriendsRequestVO> queryFriendRequest(String userId){
        return userServices.queryFriendRequestList(userId);
    }

    /**
     * 好友请求处理
     */
    @RequestMapping("/operFriendRequest")
    public List<MyFriendsVO> operFriendRequest(String acceptUserId,String sendUserId,Integer operType){
        return userServices.operFriendRequest(acceptUserId, sendUserId, operType);
    }

    /**
     * 好友列表查询
     * @param userId
     * @return
     */
    @RequestMapping("/myFriends")
    public List<MyFriendsVO> myFriends(String userId){
        if (StringUtils.isBlank(userId)){
            throw new BizException("用户id为空");
        }
        //数据库查询好友列表
        return userServices.queryMyFriends(userId);
    }

    /**
     * 用户手机端获取未签收的消息列表
     */
    @RequestMapping("/getUnReadMsgList")
    public List<ChatMsg> getUnReadMsgList(String acceptUserId){
        if(StringUtils.isBlank(acceptUserId)){
            throw new BizException("接收者ID不能为空");
        }
        //根据接收ID查找未签收的消息列表
        return chatMsgService.getUnReadMsgList(acceptUserId);
    }
}

