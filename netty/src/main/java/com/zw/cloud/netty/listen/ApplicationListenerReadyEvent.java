package com.zw.cloud.netty.listen;

import com.zw.cloud.netty.server.Server;
import com.zw.cloud.netty.utils.IpAddressUtils;
import com.zw.cloud.netty.utils.RedisUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerReadyEvent implements ApplicationListener<ApplicationReadyEvent> , DisposableBean {

    @Value("${netty.server.port}")
    private int nettyServerPort;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        new Server(nettyServerPort,redisUtils);
    }

    @Override
    public void destroy() throws Exception {
        redisUtils.del("netty-ws-server");
    }
}