package com.zw.cloud.websocket.client.service;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.websocket.entity.WebSocketMessage;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsgSendService {
    @Autowired
    private WebSocketClient webSocketClient;

    @Value("${ws.client.accessId}")
    public String userId;

    public void sendMsg(WebSocketMessage webSocketMessage) {
        webSocketMessage.setCurrentId(userId);
        webSocketClient.send(JSON.toJSONString(webSocketMessage));
    }
}
