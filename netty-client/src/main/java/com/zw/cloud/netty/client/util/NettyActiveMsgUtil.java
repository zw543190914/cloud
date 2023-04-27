package com.zw.cloud.netty.client.util;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.netty.client.factory.WebSocketClient;
import com.zw.cloud.netty.client.dto.NettyMsgDTO;
import com.zw.cloud.netty.client.enums.EnumNettyMsgTag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyActiveMsgUtil {

    /**
     * 处理客户端上线、下线等存活问题，维护存活的客户端节点列表
     *
     * @param nettyMsgDTO 消息体
     */
    public static synchronized void dealClientActive(NettyMsgDTO nettyMsgDTO, WebSocketClient websocketClient) {
        //获取当前客户端连接当前服务端的channel信息
        if (EnumNettyMsgTag.CONNECT.getType().equals(nettyMsgDTO.getTag())) {
            log.info("[NettyHeartUtil][dealClientActive]收到 {} CONNECT 消息 , nettyMsgDTO = {}", nettyMsgDTO.getUserId(),JSON.toJSONString(nettyMsgDTO));
            return;
        }
        if (EnumNettyMsgTag.HEART.getType().equals(nettyMsgDTO.getTag())) {
            log.info("[NettyHeartUtil][dealClientActive]收到 {} HEART 消息 , nettyMsgDTO = {}", nettyMsgDTO.getUserId(),JSON.toJSONString(nettyMsgDTO));
            return;
        }
        if (EnumNettyMsgTag.CLOSE_WS.getType().equals(nettyMsgDTO.getTag())) {
            log.info("[NettyHeartUtil][dealClientActive]收到 {} CLOSE_WS 消息 , nettyMsgDTO = {}",nettyMsgDTO.getUserId(), JSON.toJSONString(nettyMsgDTO));
            websocketClient.close();
        }
    }
}
