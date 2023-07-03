package com.zw.cloud.netty.web.dao.chat;

import com.zw.cloud.netty.web.entity.chat.ChatMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
public interface ChatMsgMapper extends BaseMapper<ChatMsg> {

    /**
     * 批处理更新消息为已签收
     */
    void batchUpdateMsgSigned(List<Long> msgIdList);

}
