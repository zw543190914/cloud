package com.zw.cloud.netty.web.service.impl.chat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.zw.cloud.netty.web.entity.chat.FriendsRequest;
import com.zw.cloud.netty.web.entity.chat.MyFriend;
import com.zw.cloud.netty.web.dao.chat.MyFriendMapper;
import com.zw.cloud.netty.web.service.api.chat.IMyFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
@Service
public class MyFriendServiceImpl extends ServiceImpl<MyFriendMapper, MyFriend> implements IMyFriendService {

    @Override
    public MyFriend queryMyFriendById(String myUserId, String myFriendUserId) {
        return baseMapper.selectOne(new LambdaQueryWrapper<MyFriend>()
                .eq(MyFriend::getMyUserId,myUserId)
                .eq(MyFriend::getMyFriendUserId,myFriendUserId));
    }

    /**
     * 通过好友请求并保存数据到my_friend
     */
    @Override
    public void saveFriends(String sendUserId, String acceptUserId){
        MyFriend myFriends = new MyFriend();
        myFriends.setMyUserId(sendUserId);
        myFriends.setMyFriendUserId(acceptUserId);
        baseMapper.insert(myFriends);
    }
}
