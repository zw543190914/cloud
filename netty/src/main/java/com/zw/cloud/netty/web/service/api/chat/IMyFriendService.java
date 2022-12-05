package com.zw.cloud.netty.web.service.api.chat;

import com.zw.cloud.netty.web.entity.chat.MyFriend;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
public interface IMyFriendService extends IService<MyFriend> {

    MyFriend queryMyFriendById(String myUserId,String myFriendUserId);

    /**
     * 处理好友请求——通过好友请求
     */
    void saveFriends(String sendUserId, String acceptUserId);
}
