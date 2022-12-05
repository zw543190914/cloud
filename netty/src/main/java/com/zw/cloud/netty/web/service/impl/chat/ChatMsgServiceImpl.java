package com.zw.cloud.netty.web.service.impl.chat;

import com.zw.cloud.netty.web.entity.chat.ChatMsg;
import com.zw.cloud.netty.web.dao.chat.ChatMsgMapper;
import com.zw.cloud.netty.web.service.api.chat.IChatMsgService;
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
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgMapper, ChatMsg> implements IChatMsgService {

}
