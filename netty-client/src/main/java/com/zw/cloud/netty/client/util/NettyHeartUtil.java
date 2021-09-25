package com.zw.cloud.netty.client.util;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.client.factory.WebSocketClient;
import com.zw.cloud.netty.client.dto.NettyMsgDTO;
import com.zw.cloud.netty.client.enums.EnumNettyMsgTag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyHeartUtil {

    /**
     * 处理客户端上线、下线等存活问题，维护存活的客户端节点列表
     *
     * @param nettyMsgDTO 消息体
     */
    public static synchronized void dealClientActive(NettyMsgDTO nettyMsgDTO, WebSocketClient websocketClient) {
        //获取当前客户端连接当前服务端的channel信息
        if (EnumNettyMsgTag.ADD_CHANNEL.getKey().equals(nettyMsgDTO.getTag())) {
            websocketClient.setChannelId(nettyMsgDTO.getTargetChannelId());
            log.info("[NettyHeartUtil][dealClientActive]收到 add_channel 消息 , nettyMsgDTO = {}", JSON.toJSONString(nettyMsgDTO));
            return;
        }
        if (EnumNettyMsgTag.HEART.getKey().equals(nettyMsgDTO.getTag())) {
            log.info("[NettyHeartUtil][dealClientActive]收到 HEART 消息 , nettyMsgDTO = {}", JSON.toJSONString(nettyMsgDTO));
            return;
        }
        if (EnumNettyMsgTag.ADD_CHANNEL_FAILURE.getKey().equals(nettyMsgDTO.getTag())) {
            log.info("[NettyHeartUtil][dealClientActive]收到 failure_heart_channel 消息 , nettyMsgDTO = {}", JSON.toJSONString(nettyMsgDTO));
            websocketClient.close();
        }
    }
}
