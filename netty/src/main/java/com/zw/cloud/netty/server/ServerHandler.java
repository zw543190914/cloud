package com.zw.cloud.netty.server;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.enums.EnumNettyMsgTag;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.buffer.Unpooled.copiedBuffer;
import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    // 用于记录和管理所有客户端的channle
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static ConcurrentHashMap<String, Channel> manage = new ConcurrentHashMap<String, Channel>();

   /* public static  void put(String senderId,Channel channel){
        manage.put(senderId,channel);
    }
    public static Channel get(String senderId){
        return manage.get(senderId);
    }*/

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //添加到channelGroup通道组
        clients.add(ctx.channel());
        System.out.println("与客户端 " + ctx.channel().id() + "建立连接，通道开启！现有连接数："+ clients.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        clients.remove(ctx.channel());
        System.out.println("与客户端断开连接，通道关闭！现有连接数：" + clients.size());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest
        if (msg instanceof FullHttpRequest) {

            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();

            Map paramMap = getUrlParams(uri);
            System.out.println("接收到的参数是："+ JSON.toJSONString(paramMap));
            //如果url包含参数，需要处理
            if(uri.contains("?")){
                String newUri=uri.substring(0,uri.indexOf("?"));
                System.out.println(newUri);
                request.setUri(newUri);
            }
            String data = "http request over";
            ByteBuf buf = copiedBuffer(data, CharsetUtil.UTF_8);
            FullHttpResponse response = responseOK(HttpResponseStatus.OK, buf);
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (!keepAlive) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }

        }else if(msg instanceof TextWebSocketFrame){
            //正常的TEXT消息类型
            TextWebSocketFrame frame=(TextWebSocketFrame)msg;
            System.out.println("客户端收到服务器数据：" +frame.text());
            sendAllMessage(frame.text());
        }else if(msg instanceof BinaryWebSocketFrame){
            System.out.println("收到二进制消息："+((BinaryWebSocketFrame)msg).content().readableBytes());
            BinaryWebSocketFrame binaryWebSocketFrame=new BinaryWebSocketFrame(Unpooled.buffer().writeBytes("xxx".getBytes()));
            ctx.channel().writeAndFlush(binaryWebSocketFrame);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生了异常后关闭连接，同时从channelgroup移除
        ctx.channel().close();
    }

    public static void sendAllMessage(String message){
        if (StringUtils.isBlank(message)){
            System.out.println("message is null");
            return;
        }
        try {
            NettyMsgDTO nettyMsgDTO = JSON.parseObject(message, NettyMsgDTO.class);
            if (EnumNettyMsgTag.HEART.getKey().equals(nettyMsgDTO.getTag())) {
                log.info("收到 HEART 消息 , nettyMsgDTO = {}", JSON.toJSONString(nettyMsgDTO));
            } else {
                clients.writeAndFlush( new TextWebSocketFrame(message));
            }
        } catch (Exception e) {
            log.error("msg转换DTO异常, msg={}", message);
            //收到信息后，群发给所有channel
            clients.writeAndFlush( new TextWebSocketFrame(message));
        }
    }


    private static Map getUrlParams(String url){
        Map<String,String> map = new HashMap<>();
        url = url.replace("?",";");
        if (!url.contains(";")){
            return map;
        }
        if (url.split(";").length > 0){
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr){
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key,value);
            }
            return  map;

        }else{
            return map;
        }
    }

    private FullHttpResponse responseOK(HttpResponseStatus status, ByteBuf content) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, content);
        if (content != null) {
            response.headers().set("Content-Type", "text/plain;charset=UTF-8");
            response.headers().set("Content_Length", response.content().readableBytes());
        }
        return response;
    }

}
