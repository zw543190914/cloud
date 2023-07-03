package com.zw.cloud.netty.web.service.impl.chat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.netty.web.entity.chat.MyFriend;
import com.zw.cloud.netty.web.dao.chat.MyFriendMapper;
import com.zw.cloud.netty.web.service.api.chat.IMyFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
@Service
@Slf4j
public class MyFriendServiceImpl extends ServiceImpl<MyFriendMapper, MyFriend> implements IMyFriendService {

    @Override
    public MyFriend queryMyFriendById(Long myUserId, Long myFriendUserId) {
        return baseMapper.selectOne(new LambdaQueryWrapper<MyFriend>()
                .eq(MyFriend::getMyUserId,myUserId)
                .eq(MyFriend::getMyFriendUserId,myFriendUserId));
    }

    /**
     * 通过好友请求并保存数据到my_friend
     */
    @Override
    public void saveFriends(Long sendUserId, Long acceptUserId){
        MyFriend myFriends = new MyFriend();
        myFriends.setMyUserId(sendUserId);
        myFriends.setMyFriendUserId(acceptUserId);
        try {
            baseMapper.insert(myFriends);
        } catch (DuplicateKeyException e) {
            log.warn("[MyFriendServiceImpl][saveFriends] 好友申请已通过");
        }
    }
}
