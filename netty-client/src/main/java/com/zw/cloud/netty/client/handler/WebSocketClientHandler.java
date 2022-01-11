package com.zw.cloud.netty.client.handler;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.client.enums.EnumNettyMsgTag;
import com.zw.cloud.netty.client.factory.WebSocketClient;
import com.zw.cloud.netty.client.dto.NettyMsgDTO;
import com.zw.cloud.netty.client.util.NettyHeartUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

@Slf4j
@Getter
@Setter
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketClient webSocketClient;

    private WebSocketClientHandshaker handshaker;

    private ChannelPromise handshakeFuture;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        this.handshakeFuture = ctx.newPromise();
    }

    public ChannelFuture handshakeFuture() {
        return this.handshakeFuture;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();
        FullHttpResponse response;
        if (!this.handshaker.isHandshakeComplete()) {
            try {
                response = (FullHttpResponse) msg;
                //握手协议返回，设置结束握手
                this.handshaker.finishHandshake(ch, response);
                //设置成功
                this.handshakeFuture.setSuccess();
            } catch (WebSocketHandshakeException e) {
                FullHttpResponse res = (FullHttpResponse) msg;
                String errorMsg = String.format("WebSocket Client failed to connect,status:%s,reason:%s", res.status(),
                        res.content().toString(
                                CharsetUtil.UTF_8));
                log.error("[WebSocketClientHandler][channelRead0] errorMsg={}", errorMsg, e);
                this.handshakeFuture.setFailure(new Exception(errorMsg));
            }
        } else if (msg instanceof FullHttpResponse) {
            response = (FullHttpResponse) msg;
            //this.listener.onFail(response.status().code(), response.content().toString(CharsetUtil.UTF_8));
            throw new IllegalStateException(
                    "Unexpected FullHttpResponse (getStatus=" + response.status() + ", content=" + response.content()
                            .toString(CharsetUtil.UTF_8) + ')');
        } else if (msg instanceof TextWebSocketFrame) {

            WebSocketFrame frame = (WebSocketFrame) msg;
            String msgJson = ((TextWebSocketFrame) frame).text();
            NettyMsgDTO nettyMsgDTO = null;
            if (StringUtils.isNotEmpty(msgJson)) {
                try {
                    nettyMsgDTO = JSON.parseObject(msgJson, NettyMsgDTO.class);
                    log.info("[WebSocketClientHandler][channelRead0] msg转换DTO, msg={}", msgJson);
                } catch (Exception e) {
                    log.error("[WebSocketClientHandler][channelRead0] msg转换DTO异常, msg={}", msgJson);
                }
            }
            // 处理消息
            if (nettyMsgDTO == null) {
                return;
            }
            if (EnumNettyMsgTag.allKeys().contains(nettyMsgDTO.getTag())) {
                //处理客户端上线、下线等存活问题，维护存活的客户端节点列表
                NettyHeartUtil.dealClientActive(nettyMsgDTO, webSocketClient);
            }
           /* String targetGroupId = nettyMsgDTO.getTargetGroupId();
            String identity = nettyMsgDTO.getIdentity();
            String tag = nettyMsgDTO.getTag();*/

        } else if (msg instanceof BinaryWebSocketFrame) {
            ByteBuf content = ((BinaryWebSocketFrame) msg).content();
            byte[] byteArray = new byte[content.capacity()];
            content.readBytes(byteArray);
            log.info("[WebSocketClientHandler][channelRead0]BinaryWebSocketFrame msg={}", new String(byteArray, StandardCharsets.UTF_8));

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
