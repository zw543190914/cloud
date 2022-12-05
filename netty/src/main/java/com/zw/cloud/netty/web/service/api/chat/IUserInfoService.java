package com.zw.cloud.netty.web.service.api.chat;

import com.zw.cloud.netty.enums.SearchFriendsStatusEnum;
import com.zw.cloud.netty.web.entity.chat.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
public interface IUserInfoService extends IService<UserInfo> {

    UserInfo checkUserNameIsExit(String username);


    /**
     * 搜索好友的前置条件
     */
    SearchFriendsStatusEnum preconditionSearchFriends(String myUserId, String friendUserName);

    /**
     * 发送好友请求
     */
    void sendFriendRequest(String myUserId,String friendUserName);
}
