package com.zw.cloud.netty.listen;

import com.zw.cloud.netty.server.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${netty.server.port}")
    private int nettyServerPort;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.err.println("----应用服务已经启动成功----");
        new Server(nettyServerPort);
    }
}