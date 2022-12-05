package com.zw.cloud.netty.web.service.impl.chat;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.enums.MsgActionEnum;
import com.zw.cloud.netty.enums.OperatorFriendRequestTypeEnum;
import com.zw.cloud.netty.enums.SearchFriendsStatusEnum;
import com.zw.cloud.netty.web.entity.chat.FriendsRequest;
import com.zw.cloud.netty.web.entity.chat.MyFriend;
import com.zw.cloud.netty.web.entity.chat.UserInfo;
import com.zw.cloud.netty.web.dao.chat.UserInfoMapper;
import com.zw.cloud.netty.web.entity.vo.DataContent;
import com.zw.cloud.netty.web.entity.vo.FriendsRequestVO;
import com.zw.cloud.netty.web.entity.vo.MyFriendsVO;
import com.zw.cloud.netty.web.service.api.chat.IFriendsRequestService;
import com.zw.cloud.netty.web.service.api.chat.IMyFriendService;
import com.zw.cloud.netty.web.service.api.chat.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.zw.cloud.netty.server.handler.ServerHandler.userManage;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private IMyFriendService myFriendService;
    @Autowired
    private IFriendsRequestService friendsRequestService;

    @Override
    public UserInfo checkUserNameIsExit(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername,username));
    }

    @Override
    public SearchFriendsStatusEnum preconditionSearchFriends(String myUserId, String friendUserName) {
        UserInfo user = checkUserNameIsExit(friendUserName);
        //1.搜索的用户如果不存在，则返回【无此用户】
        if(Objects.isNull(user)){
            return SearchFriendsStatusEnum.USER_NOT_EXIST;
        }
        //2.搜索的账号如果是你自己，则返回【不能添加自己】
        if(myUserId.equals(user.getId())){
            return SearchFriendsStatusEnum.NOT_YOURSELF;
        }
        //3.搜索的朋友已经是你好友，返回【该用户已经是你的好友】
        MyFriend myfriend = new MyFriend();
        myfriend.setMyUserId(myUserId);
        myfriend.setMyFriendUserId(user.getId());
        MyFriend myF = myFriendService.queryMyFriendById(myUserId,user.getId());
        if(Objects.nonNull(myF)){
            return SearchFriendsStatusEnum.ALREADY_FRIENDS;
        }
        return SearchFriendsStatusEnum.SUCCESS;
    }

    @Override
    public void sendFriendRequest(String myUserId, String friendUserName) {
        UserInfo user = checkUserNameIsExit(friendUserName);
        MyFriend myF = myFriendService.queryMyFriendById(myUserId,user.getId());
        if (Objects.nonNull(myF)) {
            FriendsRequest friendsRequest = new FriendsRequest();
            friendsRequest.setSendUserId(myUserId);
            friendsRequest.setAcceptUserId(user.getId());
            friendsRequestService.save(friendsRequest);
        }
    }

    @Override
    public List<FriendsRequestVO> queryFriendRequestList(String acceptUserId) {
        return baseMapper.queryFriendRequestList(acceptUserId);
    }

    @Override
    public List<MyFriendsVO> queryMyFriends(String userId){
        return baseMapper.queryMyFriends(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MyFriendsVO> operFriendRequest(String acceptUserId, String sendUserId, Integer operType) {
        FriendsRequest friendsRequest = new FriendsRequest();
        friendsRequest.setAcceptUserId(acceptUserId);
        friendsRequest.setSendUserId(sendUserId);
        //删除好友请求表中的数据
        friendsRequestService.deleteByFriendRequest(friendsRequest);
       if(operType.equals(OperatorFriendRequestTypeEnum.PASS.type)){
            //进行双向好友数据保存
            myFriendService.saveFriends(sendUserId,acceptUserId);
            myFriendService.saveFriends(acceptUserId,sendUserId);

           Channel sendChannel  = userManage.get(sendUserId);
           if(Objects.nonNull(sendChannel)){
               //使用websocket 主动推送消息到请求发起者，更新他的通讯录列表为最新
               //消息的推送
               NettyMsgDTO nettyMsgDTO = new NettyMsgDTO();
               nettyMsgDTO.setTag(MsgActionEnum.PULL_FRIEND.type);
               nettyMsgDTO.setUserId(acceptUserId);
               nettyMsgDTO.setTargetUserId(sendUserId);
               nettyMsgDTO.setData("添加好友申请已通过");
               sendChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(nettyMsgDTO)));
           }

        }
        //查询好友表中的列表数据
        return baseMapper.queryMyFriends(acceptUserId);
    }


}
