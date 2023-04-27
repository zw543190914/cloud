package com.zw.cloud.netty.server.handler;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.enums.EnumNettyMsgTag;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        // 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲 ）
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;        // 强制类型转换

            if (event.state() == IdleState.READER_IDLE) {
                log.info("[HeartBeatHandler][userEventTriggered] {} 进入读空闲...",ctx.channel().id());
                //sendReConnectMsgToClient(ctx.channel());
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("[HeartBeatHandler][userEventTriggered] {} 进入写空闲...",ctx.channel().id());
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.info("[HeartBeatHandler][userEventTriggered] {} 读写空闲，连接数量为：{}",ctx.channel().id(), ServerHandler.clients.size());
                // 关闭无用的channel，以防资源浪费
                ctx.channel().close();
                //log.info("[HeartBeatHandler][userEventTriggered]channel关闭后，clients的数量为：{}",ServerHandler.clients.size());
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }

    }

    private void sendReConnectMsgToClient(Channel channel) {
        String channelId = channel.id().asLongText();
        NettyMsgDTO nettyMsgDTO = new NettyMsgDTO();
        nettyMsgDTO.setTag(EnumNettyMsgTag.HEART.getType());
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(nettyMsgDTO)));
        log.info("[HeartBeatHandler][userEventTriggered] sendReConnectMsgToClient channelId is {}",channelId);
    }
}
