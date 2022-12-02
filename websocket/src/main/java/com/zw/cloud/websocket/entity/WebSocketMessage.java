package com.zw.cloud.websocket.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WebSocketMessage implements Serializable {
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 发送人
     */
    private String currentId;
    /**
     * 接收人
     */
    private String targetId;
    /**
     * 消息类型(0:文本,1:图片)
     */
    private Integer msgType;
}
