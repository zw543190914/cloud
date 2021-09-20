package com.zw.cloud.netty.client.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "websocket.config")
public class WebSocketConfigDTO implements Serializable {

    /**
     * WebSocket协议
     */
    private String wsProtocol;

    /**
     * WebSocket访问路径
     */
    private String wsPath;

    /**
     * 身份标识
     */
    private String identity;

    /**
     * 身份token
     */
    private String identityToken;

    /**
     * webSocket backLog
     */
    private Integer backLog;

    /**
     * webSocket 传输最大数据量(不常改)
     */
    private Integer dataMaxLength;

    /**
     * 定时任务参数
     */
    private Task task;

    @Getter
    @Setter
    public static class Task implements Serializable {

        private Integer connnetFailureCountNum;

        private Integer serviceListenOutTime;

    }
}
