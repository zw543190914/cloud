package com.zw.cloud.netty.enums;

/**
 * netty消息tag类型
 */
public enum EnumNettyMsgTag {

    CONNECT(1, "第一次(或重连)初始化连接"),
    CHAT(2, "聊天消息"),
    SIGNED(3, "消息签收"),
    HEART(4, "客户端保持心跳"),
    PULL_FRIEND(5, "拉取好友"),
    PUSH(6, "广播消息");
    private Integer type;
    private String desc;

    EnumNettyMsgTag(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }
}
