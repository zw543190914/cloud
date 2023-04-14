package com.zw.cloud.netty.client.enums;


/**
 * netty消息tag类型
 */
public enum EnumNettyMsgTag {

    CONNECT(1, "第一次(或重连)初始化连接"),
    CHAT(2, "聊天消息"),
    SIGNED(3, "消息签收"),
    HEART(4, "客户端保持心跳"),
    PULL_FRIEND(5, "拉取好友"),
    CLOSE_WS(6, "客户端主动关闭连接"),
    LOGIN(7, "客户端重新登陆"),
    ADD_FRIEND(8, "添加好友申请"),
    REFRESH_TOKEN(9, "刷新TOKEN"),
    WEB_USER_INFO(10, "web网页登陆用户信息");
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
