package com.zw.cloud.netty.web.service.impl.chat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.netty.enums.MsgSignFlagEnum;
import com.zw.cloud.netty.web.entity.chat.ChatMsg;
import com.zw.cloud.netty.web.dao.chat.ChatMsgMapper;
import com.zw.cloud.netty.web.service.api.chat.IChatMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public List<ChatMsg> getUnReadMsgList(Long acceptUserId) {
        return baseMapper.selectList(new LambdaQueryWrapper<ChatMsg>().eq(ChatMsg::getAcceptUserId,acceptUserId)
                .eq(ChatMsg::getSignFlag, MsgSignFlagEnum.unsign.getType()));
    }

    /**
     * 批处理更新消息为已签收
     */
    @Override
    public void batchUpdateMsgSigned(List<Long> msgIdList) {
        baseMapper.batchUpdateMsgSigned(msgIdList);
    }


}
