package com.zw.cloud.netty.client.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * netty消息tag类型
 */
public enum EnumNettyMsgTag {

    CONNECT(1, "第一次(或重连)初始化连接"),
    CHAT(2, "聊天消息"),
    SIGNED(3, "消息签收"),
    HEART(4, "客户端保持心跳"),
    PULL_FRIEND(5, "拉取好友"),
    CONNECT_FAIL(6, "连接失败");
    private Integer key;
    private String desc;

    EnumNettyMsgTag(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public static Set<Integer> allKeys(){
        Set<Integer> keys = new HashSet<>();
        for (EnumNettyMsgTag value : EnumNettyMsgTag.values()) {
            keys.add(value.key);
        }
        return keys;
    }
}
