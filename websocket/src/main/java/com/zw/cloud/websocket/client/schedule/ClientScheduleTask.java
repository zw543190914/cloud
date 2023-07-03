package com.zw.cloud.websocket.client.schedule;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClientScheduleTask {
    @Autowired
    private WebSocketClient webSocketClient;


    @Scheduled(cron = "0 0/5 * * * ? ")
    @Async
    public void reconnectTask() {
        log.info("[ClientScheduleTask][reconnectTask] start ");
        if (webSocketClient.isClosed()) {
            log.info("[ClientScheduleTask][reconnectTask] reconnect start ");
            webSocketClient.reconnect();
            log.info("[ClientScheduleTask][reconnectTask] reconnect success ");
        }
        log.info("[ClientScheduleTask][reconnectTask] end ");
    }
}
