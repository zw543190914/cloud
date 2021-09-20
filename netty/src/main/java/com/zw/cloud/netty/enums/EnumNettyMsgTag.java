package com.zw.cloud.netty.enums;

/**
 * netty消息tag类型
 */
public enum EnumNettyMsgTag {

    HEART("client_heart", "心跳"),
    ADD_CHANNEL("add_channel", "新增channel信息"),
    ADD_CHANNEL_FAILURE("failure_heart_channel", "心跳连接失败");

    private String key;
    private String desc;

    EnumNettyMsgTag(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
