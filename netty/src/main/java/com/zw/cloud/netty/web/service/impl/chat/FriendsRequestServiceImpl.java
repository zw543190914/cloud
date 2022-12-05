package com.zw.cloud.netty.web.service.impl.chat;

import com.zw.cloud.netty.web.entity.chat.FriendsRequest;
import com.zw.cloud.netty.web.dao.chat.FriendsRequestMapper;
import com.zw.cloud.netty.web.service.api.chat.IFriendsRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class FriendsRequestServiceImpl extends ServiceImpl<FriendsRequestMapper, FriendsRequest> implements IFriendsRequestService {

}
