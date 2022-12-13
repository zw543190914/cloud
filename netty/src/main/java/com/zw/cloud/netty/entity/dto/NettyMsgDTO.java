package com.zw.cloud.netty.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class NettyMsgDTO implements Serializable {

    /**
     * 消息标签
     *     CONNECT(1, "第一次(或重连)初始化连接"),
     *     CHAT(2, "聊天消息"),
     *     SIGNED(3, "消息签收"),
     *     HEART(4, "客户端保持心跳"),
     *     PULL_FRIEND(5, "拉取好友"),
     *     CONNECT_FAIL(6, "连接失败");
     */
    private Integer tag;
    /**
     * 消息内容
     */
    private String data;

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

}
