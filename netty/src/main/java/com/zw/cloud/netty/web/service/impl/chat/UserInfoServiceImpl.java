package com.zw.cloud.netty.web.service.impl.chat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.netty.enums.SearchFriendsStatusEnum;
import com.zw.cloud.netty.web.entity.chat.FriendsRequest;
import com.zw.cloud.netty.web.entity.chat.MyFriend;
import com.zw.cloud.netty.web.entity.chat.UserInfo;
import com.zw.cloud.netty.web.dao.chat.UserInfoMapper;
import com.zw.cloud.netty.web.service.api.chat.IFriendsRequestService;
import com.zw.cloud.netty.web.service.api.chat.IMyFriendService;
import com.zw.cloud.netty.web.service.api.chat.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

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

}
