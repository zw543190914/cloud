package com.zw.cloud.websocket.client.config;

import com.zw.cloud.websocket.client.endpoint.MyWebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class WebSocketClientConfig {

    @Value("${ws.client.accessId}")
    public String userId;

    /**
     * 使用 tomcat 时，消息大小配置
     * https://www.cnblogs.com/yunnick/p/14545252.html
     */
    @Bean
    public WebSocketClient webSocketClient() throws URISyntaxException{
        log.info("[WebSocketClientConfig][webSocketClient] init");
        String ws="ws://localhost:18092/test/oneToMany/" + userId;
        try {
            WebSocketClient webSocketClient = new MyWebSocketClient(new URI(ws));
            // 启动成功后连接
            //webSocketClient.connect();
            return webSocketClient;
        } catch (URISyntaxException e) {
            log.error("[WebSocketClientConfig][webSocketClient] error is ",e);
            throw e;
        }
    }


    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            String ws="ws://localhost:18092/test/oneToMany/" + System.currentTimeMillis();
            try {
                WebSocketClient webSocketClient = new MyWebSocketClient(new URI(ws));
                webSocketClient.connect();
                TimeUnit.MILLISECONDS.sleep(100);
                log.info("[WebSocketClientConfig][test] connect count is {}",i);
            } catch (URISyntaxException | InterruptedException e) {
                log.error("[WebSocketClientConfig][test] error is ",e);
            }
        }

    }

}
