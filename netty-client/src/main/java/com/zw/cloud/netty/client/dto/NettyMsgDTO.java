package com.zw.cloud.netty.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class NettyMsgDTO<T> implements Serializable {

    /**
     * 消息标签
     */
    private String tag;
    /**
     * 消息内容
     */
    private T data;

    /**
     * 消息发送者身份标识
     */
    private String identity;

    /**
     * 发往的目标组id
     */
    private String targetGroupId;

    /**
     * 发往的目标channelId，（当与目标组id共存时，以channelId为优先级）
     */
    private String targetChannelId;

    /**
     * 该消息的回复是否只有发送者自身接收
     */
    private Boolean onlySenderReceive;
}
