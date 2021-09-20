package com.zw.cloud.netty.client;

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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@Component
public class WebSocketClientFactory implements ApplicationRunner, DisposableBean {

    private static List<WebSocketClient> websocketClientList = new CopyOnWriteArrayList<>();

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    private WebSocketConfigDTO webSocketConfigDTO;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public void destroy() throws Exception {
        log.info("webSocketClient正在关闭");
        try {
            if (websocketClientList.size() > 0) {
                for (WebSocketClient websocketClient : websocketClientList) {
                    if (websocketClient.getChannel() != null) {
                        websocketClient.close();
                    }
                }
            }
        } catch (Exception e) {
            log.error("关闭wecsocket factory 异常", e);
        }
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//
//    }

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
        Map<String, Long> serviceMap = redisUtils.getMapValues("WEBSOCKET:NODE:LIST", Long.class);

        log.info("webSocketConfigDTO  -->  {}", JSON.toJSONString(webSocketConfigDTO));

        for (String addr : serviceMap.keySet()) {
            try {
                addr = addr.replaceAll("#", ":");
                String finalAddr = addr;
                /*executorService.execute(() -> {

                });*/
                //添加客户端列表
                websocketClientList.add(new WebSocketClient(finalAddr, webSocketConfigDTO));
            } catch (Exception e) {
                log.error("初始化客户端异常, addr={}", addr, e);
            }
        }
    }
}
