package com.zw.cloud.netty.client.util;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.client.WebSocketClient;
import com.zw.cloud.netty.client.WebSocketClientFactory;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class WebSocketSendMsgUtil {

    /**
     * 向工厂发送消息内容
     *
     * @param data          消息体
     * @param targetGroupId 工厂编码
     * @param tag           主题
     * @return
     */
    public static boolean sendMsg(Object data, String targetGroupId, String tag) {
        log.info("websocket消息发送，targetGroupId={}，tag={}，data={}", targetGroupId, tag, JSON.toJSONString(data));
        for (WebSocketClient websocketClient : WebSocketClientFactory.getWebsocketClientList()) {
            websocketClient.sendMsg(data, targetGroupId, tag, null,
                    websocketClient.getWebSocketConfigDTO());
        }
        return true;
    }

}
