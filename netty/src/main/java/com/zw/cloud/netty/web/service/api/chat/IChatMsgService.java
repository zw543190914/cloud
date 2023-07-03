package com.zw.cloud.netty.web.service.api.chat;

import com.zw.cloud.netty.web.entity.chat.ChatMsg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
public interface IChatMsgService extends IService<ChatMsg> {

    /**
     * 未读消息查询
     */
    List<ChatMsg> getUnReadMsgList(Long acceptUserId);

    /**
     * 批处理更新消息为已签收
     */
    void batchUpdateMsgSigned(List<Long> msgIdList);
}
