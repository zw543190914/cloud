package com.zw.cloud.websocket;

import com.zw.cloud.websocket.server.endpoint.OneToManyWebSocket;
import com.zw.cloud.websocket.server.endpoint.OneToOneWebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebSocketApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WebSocketApplication.class);
        OneToManyWebSocket.setApplicationContext(applicationContext);
        OneToOneWebSocket.setApplicationContext(applicationContext);
    }
}
