package com.zw.cloud.common.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeishuMsgDTO {

    /**
     * 时间戳
     */
    private Integer timestamp;

    /**
     * 签名
     */
    private String sign;

    /**
     * 消息类型
     */
    private String msg_type;

    /**
     * 消息内容
     */
    private MsgContent content;

    @Getter
    @Setter
    public static class MsgContent {
        private String text;
    }
}
