package com.zw.cloud.netty.client.util;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.client.factory.WebSocketClient;
import com.zw.cloud.netty.client.factory.WebSocketClientFactory;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class WebSocketSendMsgUtil {


    public static boolean sendMsg(Object data, String targetGroupId, String targetUserId, Integer tag) {
        log.info("[WebSocketSendMsgUtil][sendMsg]websocket消息发送，targetGroupId={}，tag={}，data={}", targetGroupId, tag, JSON.toJSONString(data));
        for (WebSocketClient websocketClient : WebSocketClientFactory.getWebsocketClientList()) {
            websocketClient.sendMsg(data, targetGroupId, tag, targetUserId,
                    websocketClient.getWebSocketConfigDTO().getUserId());
        }
        return true;
    }

}
