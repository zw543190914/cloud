package com.zw.cloud.netty.client.schedule;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.client.factory.WebSocketClient;
import com.zw.cloud.netty.client.factory.WebSocketClientFactory;
import com.zw.cloud.netty.client.dto.WebSocketConfigDTO;
import com.zw.cloud.netty.client.enums.EnumNettyMsgTag;
import com.zw.cloud.netty.client.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        log.info("[WebScoketScheduleTask][refreshWebsocketClientHeart] start...");

        //获取发送服务列表
        List<WebSocketClient> socketClients = WebSocketClientFactory.getWebsocketClientList();
        if (CollectionUtils.isEmpty(socketClients)) {
            log.warn("[WebScoketScheduleTask][refreshWebsocketClientHeart] 客户端完成服务连接设备为 null 注意检查service服务器列表");
        } else {
            for (WebSocketClient webSocketClient : socketClients) {
                try {
                    WebSocketConfigDTO webSocketConfigDTO = webSocketClient.getWebSocketConfigDTO();
                    if (webSocketClient.getChannel() != null) {
                        log.info("[WebScoketScheduleTask][refreshWebsocketClientHeart] 客户端心跳 ---> {}", JSON.toJSONString(webSocketClient));
                        try {
                            webSocketClient
                                    .sendMsg("heart beat",
                                            null,
                                            EnumNettyMsgTag.HEART.getKey(), null,
                                            webSocketConfigDTO.getUserId());
                        } catch (Exception e) {
                            log.warn("[WebScoketScheduleTask][refreshWebsocketClientHeart] 客户端心跳异常，", e);
                        }
                        Integer connnetFailureCountNum = webSocketClient.getConnnetFailureCountNum();
                        Integer failureCountNum = webSocketClient
                                .getWebSocketConfigDTO()
                                .getTask()
                                .getConnnetFailureCountNum();
                        log.warn("[WebScoketScheduleTask][refreshWebsocketClientHeart] connectFailureCountNum is {},config failureCountNum is {}", connnetFailureCountNum,failureCountNum);
                        if (connnetFailureCountNum >= failureCountNum) {
                            log.info("[WebScoketScheduleTask][refreshWebsocketClientHeart]服务器 {} 无法连接 ， 客户端关闭 ", webSocketClient.getAddr());
                            webSocketClient.getChannel().close();
                            WebSocketClientFactory.getWebsocketClientList().remove(webSocketClient);
                        }
                    } else {
                        WebSocketClientFactory.getWebsocketClientList().remove(webSocketClient);
                    }
                } catch (Exception e) {
                    log.error("[WebScoketScheduleTask][refreshWebsocketClientHeart] 定时发送客户端心跳消息任务执行异常", e);
                }
            }
        }
        log.info("[WebScoketScheduleTask][refreshWebsocketClientHeart] refreshWebsocketClientHeart end ");
    }

    /**
     * 设备列表重连
     */
    @Scheduled(cron = "${websocket.config.task.reconnection-client-cron}")
    public void reconnectionClientHeart() {
        log.info("[WebScoketScheduleTask][reconnectionClientHeart] reconnectionClientHeart start... ");

        //获取系统websocket服务
        Set<Object> serverSet = redisUtils.sGet("netty-ws-server");
        if (CollectionUtils.isEmpty(serverSet)){
            log.info("[WebScoketScheduleTask][reconnectionClientHeart] websocket服务器列表为空...");

        }
        //服务器列表
        List<String> webScocketList = new ArrayList<>();

        for (Object ipAndPortStr : serverSet) {
            webScocketList.add(String.valueOf(ipAndPortStr).replaceAll("#", ":"));
            //log.info("[WebScoketScheduleTask][reconnectionClientHeart] ipAndPortStr is {},connectionTime is {}",ipAndPortStr,connectionTime);
            //判断是否要添加到重连列表
            /*if (3600 > webSocketConfigDTO.getTask().getServiceListenOutTime()) {
                ipAndPortStr = String.valueOf(ipAndPortStr);
                webScocketList.add(ipAndPortStr.replaceAll("#", ":"));
            }*/
        }

        List<String> currentSocketConnectionList = new ArrayList<>();
        List<WebSocketClient> webSocketClients = WebSocketClientFactory.getWebsocketClientList();
        for (WebSocketClient webSocketClient : webSocketClients) {
            //当前连接列表
            String addr = webSocketClient.getAddr();
            log.info("[WebScoketScheduleTask][reconnectionClientHeart] currentSocketConnection has {}", addr);
            currentSocketConnectionList.add(addr);
        }

        for (String addr : webScocketList) {
            if (!currentSocketConnectionList.contains(addr)) {
                try {
                    WebSocketClient webSocketClient = new WebSocketClient(addr, webSocketConfigDTO);
                    webSocketClients.add(webSocketClient);
                    log.info("[WebScoketScheduleTask][reconnectionClientHeart] reconnectionClientHeart addr is {}", JSON.toJSONString(webSocketClient));
                } catch (Exception e) {
                    log.error("reconnectionClientHeart error ", e);
                    log.info("[WebScoketScheduleTask][reconnectionClientHeart] reconnectionClientHeart error is ", e);
                }
            }
        }

        log.info("[WebScoketScheduleTask][reconnectionClientHeart] reconnectionClientHeart end ");
    }
}
