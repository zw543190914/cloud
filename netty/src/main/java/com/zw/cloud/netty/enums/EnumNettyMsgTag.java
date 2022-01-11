package com.zw.cloud.netty.enums;

/**
 * netty消息tag类型
 */
public enum EnumNettyMsgTag {

    CONNECT(1, "第一次(或重连)初始化连接"),
    CHAT(2, "聊天消息"),
    SIGNED(3, "消息签收"),
    HEART(4, "客户端保持心跳"),
    PULL_FRIEND(5, "拉取好友");
    private Integer key;
    private String desc;

    EnumNettyMsgTag(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }
}
