package com.zw.cloud.websocket.client.config;

import com.zw.cloud.websocket.client.endpoint.MyWebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Configuration
@Slf4j
public class WebSocketClientConfig {

    @Value("${ws.client.accessId}")
    public String userId;

    @Lazy
    @Bean
    public WebSocketClient webSocketClient() throws URISyntaxException{
        String ws="ws://localhost:18092/test/oneToMany/" + userId;
        try {
            WebSocketClient webSocketClient = new MyWebSocketClient(new URI(ws));
            webSocketClient.connect();
            return webSocketClient;
        } catch (URISyntaxException e) {
            log.error("[WebSocketClientConfig][webSocketClient] error is ",e);
            throw e;
        }
    }



}
