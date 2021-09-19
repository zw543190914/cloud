package com.zw.cloud.websocket;

import com.zw.cloud.websocket.server.endpoint.OneToOneWebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebSocketApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WebSocketApplication.class);
        OneToOneWebSocket.setApplicationContext(applicationContext);
    }
}
