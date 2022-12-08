package com.zw.cloud.netty.web.service.impl.chat;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.common.utils.EncryptUtils;
import com.zw.cloud.common.utils.JjwtUtils;
import com.zw.cloud.common.utils.ZXingCodeSimpleUtils;
import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.enums.EnumNettyMsgTag;
import com.zw.cloud.netty.enums.OperatorFriendRequestTypeEnum;
import com.zw.cloud.netty.enums.SearchFriendsStatusEnum;
import com.zw.cloud.netty.web.entity.chat.FriendsRequest;
import com.zw.cloud.netty.web.entity.chat.ImgAttachment;
import com.zw.cloud.netty.web.entity.chat.MyFriend;
import com.zw.cloud.netty.web.entity.chat.UserInfo;
import com.zw.cloud.netty.web.dao.chat.UserInfoMapper;
import com.zw.cloud.netty.web.entity.vo.FriendsRequestVO;
import com.zw.cloud.netty.web.entity.vo.MyFriendsVO;
import com.zw.cloud.netty.web.entity.vo.UserVo;
import com.zw.cloud.netty.web.service.api.chat.IFriendsRequestService;
import com.zw.cloud.netty.web.service.api.chat.IImgAttachmentService;
import com.zw.cloud.netty.web.service.api.chat.IMyFriendService;
import com.zw.cloud.netty.web.service.api.chat.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zw.cloud.netty.server.handler.ServerHandler.userManage;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private IMyFriendService myFriendService;
    @Autowired
    private IFriendsRequestService friendsRequestService;
    @Autowired
    IImgAttachmentService imgAttachmentService;

    @Override
    @Transactional
    public UserVo registerOrLogin(UserInfo user) {
        log.info("[UserInfoServiceImpl][registerOrLogin]user is {}", JSON.toJSONString(user));

        UserInfo userResult = checkUserNameIsExit(user.getUsername());
        UserVo userVo = new UserVo();
        if (Objects.nonNull(userResult)) {
            // 登录
            if (userResult.getStatus() == 0) {
                throw new BizException("账号已经被锁定");
            }
            //此用户存在，可登录
            if (!StringUtils.equals(userResult.getPassword(), EncryptUtils.encrypted(user.getPassword()))) {
                throw new BizException("密码错误 或者 用户名已经被注册");
            }
            BeanUtils.copyProperties(userResult, userVo);
            String jwt = JjwtUtils.createJwt(String.valueOf(userVo.getId()), userVo.getUsername(), Lists.newArrayList(""));
            userVo.setAccessToken(jwt);
            log.info("[UserInfoServiceImpl][registerOrLogin]userVo is {}", JSON.toJSONString(userVo));
            return userVo;
        }

        //注册
        int count = imgAttachmentService.count();
        int id = RandomUtil.randomInt(1, count);
        ImgAttachment imgAttachment = imgAttachmentService.getById((long) id);
        if (Objects.nonNull(imgAttachment)) {
            user.setFaceImage(imgAttachment.getUrl());
            user.setFaceImageBig(imgAttachment.getUrl());
        }
        user.setNickname(user.getUsername());
        user.setPassword(EncryptUtils.encrypted(user.getPassword()));

        String qrCode = null;
        try {
            qrCode = ZXingCodeSimpleUtils.crateQRCode("bird_qrcode:" + user.getUsername());
        } catch (Exception e) {
            log.error("[UserInfoServiceImpl][registerOrLogin]crateQRCode error is ", e);
        }
        user.setQrcode(qrCode);
        save(user);
        BeanUtils.copyProperties(user, userVo);
        String jwt = JjwtUtils.createJwt(String.valueOf(userVo.getId()), userVo.getUsername(), Lists.newArrayList(""));
        userVo.setAccessToken(jwt);
        log.info("[UserInfoServiceImpl][registerOrLogin]userVo is {}", JSON.toJSONString(userVo));
        return userVo;
    }

    @Override
    public UserInfo checkUserNameIsExit(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername, username));
    }

    @Override
    public SearchFriendsStatusEnum preconditionSearchFriends(Long myUserId, String friendUserName) {
        UserInfo user = checkUserNameIsExit(friendUserName);
        //1.搜索的用户如果不存在，则返回【无此用户】
        if (Objects.isNull(user)) {
            return SearchFriendsStatusEnum.USER_NOT_EXIST;
        }
        //2.搜索的账号如果是你自己，则返回【不能添加自己】
        if (myUserId.equals(user.getId())) {
            return SearchFriendsStatusEnum.NOT_YOURSELF;
        }
        //3.搜索的朋友已经是你好友，返回【该用户已经是你的好友】
        MyFriend myfriend = new MyFriend();
        myfriend.setMyUserId(myUserId);
        myfriend.setMyFriendUserId(user.getId());
        MyFriend myF = myFriendService.queryMyFriendById(myUserId, user.getId());
        if (Objects.nonNull(myF)) {
            return SearchFriendsStatusEnum.ALREADY_FRIENDS;
        }
        return SearchFriendsStatusEnum.SUCCESS;
    }

    @Override
    public void sendFriendRequest(Long myUserId, String friendUserName) {
        UserInfo user = checkUserNameIsExit(friendUserName);
        MyFriend myF = myFriendService.queryMyFriendById(myUserId, user.getId());
        if (Objects.isNull(myF)) {
            log.info("[UserInfoServiceImpl][sendFriendRequest]");
            FriendsRequest friendsRequest = new FriendsRequest();
            friendsRequest.setSendUserId(myUserId);
            friendsRequest.setAcceptUserId(user.getId());
            friendsRequestService.save(friendsRequest);
        }
    }

    @Override
    public List<FriendsRequestVO> queryFriendRequestList(Long acceptUserId) {
        log.info("[UserInfoServiceImpl][queryFriendRequestList]acceptUserId is {}", acceptUserId);

        List<FriendsRequestVO> friendRequestList = baseMapper.queryFriendRequestList(acceptUserId);
        List<Long> sendUserIdList = friendRequestList.stream().map(FriendsRequestVO::getSendUserId).collect(Collectors.toList());
        log.info("[UserInfoServiceImpl][queryFriendRequestList]friendRequestList is {}", JSON.toJSONString(sendUserIdList));
        return friendRequestList;
    }

    @Override
    public List<MyFriendsVO> queryMyFriends(Long userId) {
        return baseMapper.queryMyFriends(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MyFriendsVO> operFriendRequest(Long acceptUserId, Long sendUserId, Integer operType) {
        FriendsRequest friendsRequest = new FriendsRequest();
        friendsRequest.setAcceptUserId(acceptUserId);
        friendsRequest.setSendUserId(sendUserId);
        //删除好友请求表中的数据
        friendsRequestService.deleteByFriendRequest(friendsRequest);
        if (operType.equals(OperatorFriendRequestTypeEnum.PASS.type)) {
            //进行双向好友数据保存
            log.info("[UserInfoServiceImpl][operFriendRequest] 进行双向好友数据保存");
            myFriendService.saveFriends(sendUserId, acceptUserId);
            myFriendService.saveFriends(acceptUserId, sendUserId);

            Channel sendChannel = userManage.get(String.valueOf(sendUserId));
            if (Objects.nonNull(sendChannel)) {
                //使用websocket 主动推送消息到请求发起者，更新他的通讯录列表为最新
                //消息的推送
                NettyMsgDTO nettyMsgDTO = new NettyMsgDTO();
                nettyMsgDTO.setTag(EnumNettyMsgTag.PULL_FRIEND.getType());
                nettyMsgDTO.setUserId(String.valueOf(acceptUserId));
                nettyMsgDTO.setTargetUserId(String.valueOf(sendUserId));
                nettyMsgDTO.setData("添加好友申请已通过");
                sendChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(nettyMsgDTO)));
                log.info("[UserInfoServiceImpl][operFriendRequest] 添加好友申请已通过");
            }

        }
        //查询好友表中的列表数据
        return baseMapper.queryMyFriends(acceptUserId);
    }


}
