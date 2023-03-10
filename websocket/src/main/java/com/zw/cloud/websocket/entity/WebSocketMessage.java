package com.zw.cloud.websocket.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime date;

    /**
     * 消息类型(0:文本,1:图片,-1:上线,-2:下线,-3:重复登陆)
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
