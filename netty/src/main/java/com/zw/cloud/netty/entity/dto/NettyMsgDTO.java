package com.zw.cloud.netty.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class NettyMsgDTO<T> implements Serializable {

    /**
     * 消息标签
     */
    private Integer tag;
    /**
     * 消息内容
     */
    private T data;

    /**
     * 消息发送者身份标识
     */
    private String userId;

    /**
     * 发往的目标组id
     */
    private String targetGroupId;

    /**
     * 发往的目标 targetUserId
     */
    private String targetUserId;

    /**
     * 该消息的回复是否只有发送者自身接收
     */
    private Boolean onlySenderReceive;
}
