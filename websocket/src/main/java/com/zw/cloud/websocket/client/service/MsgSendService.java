package com.zw.cloud.websocket.client.service;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.websocket.config.WebSocketClientConfig;
import com.zw.cloud.websocket.entity.WebSocketMessage;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgSendService {
    @Autowired
    private WebSocketClient webSocketClient;

    public void sendMsg(WebSocketMessage webSocketMessage) {
        webSocketMessage.setCurrentId(WebSocketClientConfig.userId);
        webSocketClient.send(JSON.toJSONString(webSocketMessage));
    }
}
