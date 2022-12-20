package com.zw.cloud.websocket.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

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
     * hangup 对方挂断
     * call_start 视频通话请求
     * offer
     * answer
     * _ice
     */
    private String msgType;

    private String sdp;

    private Map iceCandidate;
}
