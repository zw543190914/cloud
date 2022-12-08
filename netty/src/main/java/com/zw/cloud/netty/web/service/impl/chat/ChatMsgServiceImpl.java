package com.zw.cloud.netty.web.service.impl.chat;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.netty.enums.MsgSignFlagEnum;
import com.zw.cloud.netty.web.entity.chat.ChatMsg;
import com.zw.cloud.netty.web.dao.chat.ChatMsgMapper;
import com.zw.cloud.netty.web.service.api.chat.IChatMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgMapper, ChatMsg> implements IChatMsgService {


    @Override
    public List<ChatMsg> getUnReadMsgList(Long acceptUserId) {
        log.info("[ChatMsgServiceImpl][getUnReadMsgList]acceptUserId is {}", acceptUserId);
        List<ChatMsg> chatMsgList = baseMapper.selectList(new LambdaQueryWrapper<ChatMsg>().eq(ChatMsg::getAcceptUserId, acceptUserId)
                .eq(ChatMsg::getSignFlag, MsgSignFlagEnum.unsign.getType()));
        if (CollectionUtils.isNotEmpty(chatMsgList)) {
            List<Long> idList = chatMsgList.stream().map(ChatMsg::getId).collect(Collectors.toList());
            log.info("[ChatMsgServiceImpl][getUnReadMsgList]acceptUserId is {},chatMsgList is {}",acceptUserId, JSON.toJSON(idList));
        } else {
            log.info("[ChatMsgServiceImpl][getUnReadMsgList]acceptUserId is {},chatMsgList is empty",acceptUserId);
        }
        return chatMsgList;
    }

    /**
     * 批处理更新消息为已签收
     */
    @Override
    public void batchUpdateMsgSigned(List<Long> msgIdList) {
        log.info("[ChatMsgServiceImpl][batchUpdateMsgSigned]msgIdList is {}", JSON.toJSON(msgIdList));
        baseMapper.batchUpdateMsgSigned(msgIdList);
    }


}
