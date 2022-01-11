package com.zw.cloud.netty.client.factory;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.client.dto.WebSocketConfigDTO;
import com.zw.cloud.netty.client.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;


@Slf4j
@Component
public class WebSocketClientFactory implements ApplicationRunner, DisposableBean {

    private static List<WebSocketClient> websocketClientList = new CopyOnWriteArrayList<>();

    private static ExecutorService executorService = new ThreadPoolExecutor(4, 6, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10240),new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private WebSocketConfigDTO webSocketConfigDTO;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public void destroy() throws Exception {
        log.info("[WebSocketClientFactory][destroy]webSocketClient正在关闭");
        try {
            if (websocketClientList.size() > 0) {
                for (WebSocketClient websocketClient : websocketClientList) {
                    if (websocketClient.getChannel() != null) {
                        websocketClient.close();
                    }
                }
            }
        } catch (Exception e) {
            log.error("[WebSocketClientFactory][destroy]关闭wecsocket factory 异常:",e);
        }
    }


    /**
     * 获取客户端列表
     *
     * @return
     */
    public static List<WebSocketClient> getWebsocketClientList() {
        return websocketClientList;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 获取服务器和服务器列表 redis 测试数据 WEBSOCKET:NODE:LIST  127.0.0.1#18888  360
        Set<Object> serverList = redisUtils.sGet("netty-ws-server");
        log.info("[WebSocketClientFactory][run]webSocketConfigDTO is {}",JSON.toJSONString(webSocketConfigDTO));

        for (Object addr : serverList) {
            String serverAddr = String.valueOf(addr);
            log.info("[WebSocketClientFactory][run]serverAddr is {}",serverAddr);
            try {
                serverAddr = serverAddr.replaceAll("#", ":");
                String finalAddr = serverAddr;
                executorService.execute(() -> {
                    WebSocketClient webSocketClient = new WebSocketClient(finalAddr, webSocketConfigDTO);
                    //添加客户端列表
                    websocketClientList.add(webSocketClient);
                });

            } catch (Exception e) {
                log.error("[WebSocketClientFactory][run]初始化客户端异常, addr={} : {}",addr,e);
            }
        }
    }
}
