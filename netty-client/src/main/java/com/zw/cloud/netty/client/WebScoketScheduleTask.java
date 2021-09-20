package com.zw.cloud.netty.client;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.client.dto.WebSocketConfigDTO;
import com.zw.cloud.netty.client.enums.EnumNettyMsgTag;
import com.zw.cloud.netty.client.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WebScoketScheduleTask {

    @Autowired
    private WebSocketConfigDTO webSocketConfigDTO;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 心跳保持
     */
    @Scheduled(cron = "${websocket.config.task.heart-listen-cron}")
    public void refreshWebsocketClientHeart() {
        log.info("refreshWebsocketClientHeart start...");

        //获取发送服务列表
        List<WebSocketClient> socketClients = WebSocketClientFactory.getWebsocketClientList();
        if (socketClients == null || socketClients.size() <= 0) {
            log.warn("客户端完成服务连接设备为 null 注意检查service服务器列表");
        } else {
            for (WebSocketClient webSocketClient : WebSocketClientFactory.getWebsocketClientList()) {
                try {
                    WebSocketConfigDTO webSocketConfigDTO = webSocketClient.getWebSocketConfigDTO();
                    if (webSocketClient.getChannel() != null) {
                        log.info("客户端心跳 ---> {}", JSON.toJSONString(webSocketClient));
                        webSocketClient
                                .sendMsg(webSocketClient.getChannelId(),
                                        webSocketConfigDTO.getIdentity(),
                                        EnumNettyMsgTag.HEART.getKey(), webSocketClient.getChannelId(),
                                        webSocketConfigDTO);
                        if (webSocketClient.getConnnetFailureCountNum() > webSocketClient.getWebSocketConfigDTO()
                                .getTask()
                                .getConnnetFailureCountNum()) {
                            log.info("服务器 【{}】无法连接 ， 客户端关闭 ", webSocketClient.getAddr());
                            webSocketClient.getChannel().close();
                            WebSocketClientFactory.getWebsocketClientList().remove(webSocketClient);
                        }
                    } else {
                        WebSocketClientFactory.getWebsocketClientList().remove(webSocketClient);
                    }
                } catch (Exception e) {
                    log.error("定时发送客户端心跳消息任务执行异常", e);
                }
            }
        }
        log.info("refreshWebsocketClientHeart end...");
    }

    /**
     * 设备列表重连
     */
    @Scheduled(cron = "${websocket.config.task.reconnection-client-cron}")
    public void reconnectionClientHeart() {
        log.info(" reconnectionClientHeart start... ");

        //获取系统websocket服务
        Map<String, Long> serviceMap = redisUtils
                .getMapValues("WEBSOCKET:NODE:LIST",Long.class);

        //服务器列表
        List<String> webScocketList = new ArrayList<>(serviceMap.size());

        for (String ipAndPortStr : serviceMap.keySet()) {
            //判断是否要添加到重连列表
            if (serviceMap.get(ipAndPortStr) > webSocketConfigDTO.getTask().getServiceListenOutTime() * 60 * 1000) {
                webScocketList.add(ipAndPortStr.replaceAll("#", ":"));
            }
        }

        List<String> currentSocketConnectionList = new ArrayList<>();
        List<WebSocketClient> webSocketClients = WebSocketClientFactory.getWebsocketClientList();
        for (WebSocketClient webSocketClient : webSocketClients) {
            currentSocketConnectionList.add(webSocketClient.getAddr());
        }

        for (String key : webScocketList) {
            if (!currentSocketConnectionList.contains(key)) {
                try {
                    WebSocketClient webSocketClient = new WebSocketClient(key, webSocketConfigDTO);
                    webSocketClients.add(webSocketClient);
                    log.info("reconnectionClientHeart add = {}", JSON.toJSONString(webSocketClient));
                } catch (Exception e) {
                    log.error("reconnectionClientHeart error ", e);
                }
            }
        }

        log.info(" reconnectionClientHeart end... ");
    }
}
