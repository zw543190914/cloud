package com.zw.cloud.netty.server.handler;

import com.zw.cloud.netty.server.handler.ServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {

        // 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲 ）
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;        // 强制类型转换

            if (event.state() == IdleState.READER_IDLE) {
                log.info("[HeartBeatHandler][userEventTriggered]进入读空闲...");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("[HeartBeatHandler][userEventTriggered]进入写空闲...");
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.info("[HeartBeatHandler][userEventTriggered]读写空闲，channel关闭前，clients的数量为：{}", ServerHandler.clients.size());
                Channel channel = ctx.channel();
                /*if (ServerHandler.clients.contains(channel)){
                    NettyMsgDTO nettyMsgDTO = new NettyMsgDTO();
                    nettyMsgDTO.setTargetChannelId(channel.id().asLongText());
                    nettyMsgDTO.setTag(EnumNettyMsgTag.ADD_CHANNEL_FAILURE.getKey());
                    ctx.writeAndFlush( new TextWebSocketFrame(JSON.toJSONString(nettyMsgDTO)));
                }
                */
                // 关闭无用的channel，以防资源浪费
                channel.close();
                log.info("[HeartBeatHandler][userEventTriggered]channel关闭后，clients的数量为：{}",ServerHandler.clients.size());

            }
        }

    }
}
