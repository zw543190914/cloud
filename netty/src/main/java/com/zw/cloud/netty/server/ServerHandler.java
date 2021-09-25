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
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.buffer.Unpooled.copiedBuffer;
import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    //属性名称：握手处理器
    private static final AttributeKey<WebSocketServerHandshaker> HAND_SHAKE_ATTR = AttributeKey.valueOf("HAND_SHAKE");

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
        log.info("[ServerHandler][channelActive] 与客户端 {} 建立连接，通道开启！现有连接数：{}", ctx.channel().id(),clients.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        clients.remove(ctx.channel());
        log.info("[ServerHandler][channelInactive] 与客户端 {} 断开连接，通道关闭！现有连接数：{}", ctx.channel().id(),clients.size());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            if(isFileUpload(request)){
                //文件上传
            } else if(isWebSocketHandShake(request)) { //判断是否为websocket握手请求
                handleShake(ctx, request);
                Map paramMap = getUrlParams(uri);
                if (MapUtils.isNotEmpty(paramMap)){
                    String newUri = uri.substring(0,uri.indexOf("?"));
                    log.info("[ServerHandler][channelRead]FullHttpRequest 接收到客户端：{} newUri：{}", ctx.channel().id(),newUri);
                }
            } else {
                Map paramMap = getUrlParams(uri);
                log.info("[ServerHandler][channelRead]FullHttpRequest 接收到客户端：{} 参数是：{}", ctx.channel().id(),JSON.toJSONString(paramMap));
                //如果url包含参数，需要处理
                if(uri.contains("?")){
                    String newUri = uri.substring(0,uri.indexOf("?"));
                    log.info("[ServerHandler][channelRead]FullHttpRequest 接收到客户端：{} newUri：{}", ctx.channel().id(),newUri);
                    //重新设置请求地址 /ws
                    request.setUri(newUri);
                }
                //接着建立请求
                super.channelRead(ctx, msg);
            }


        }else if(msg instanceof WebSocketFrame){
            //处理websocket客户端的消息
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
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

    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame){
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            WebSocketServerHandshaker handshaker = ctx.channel().attr(HAND_SHAKE_ATTR).get();
            if(handshaker == null){
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                return;
            }
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (frame instanceof TextWebSocketFrame) {
            //正常的TEXT消息类型
            TextWebSocketFrame msg = (TextWebSocketFrame)frame;
            log.info("[ServerHandler][channelRead]TextWebSocketFrame 接收到客户端：{} 参数是：{}", ctx.channel().id(),msg.text());
            sendAllMessage(msg.text());
        }
        if(frame instanceof BinaryWebSocketFrame){
            ByteBuf content = frame.content();
            content.markReaderIndex();
            int flag = content.readInt();
            log.info("[ServerHandler][channelRead]BinaryWebSocketFrame 接收到客户端：{} 二进制消息,标志位: {}", ctx.channel().id(),flag);
            content.resetReaderIndex();

            ByteBuf byteBuf = Unpooled.directBuffer(frame.content().capacity());
            byteBuf.writeBytes(frame.content());
            ctx.writeAndFlush(new BinaryWebSocketFrame(byteBuf));
        }
    }

    //判断是否为文件上传
    private boolean isFileUpload(FullHttpRequest msg){
        String uri = msg.uri();
        //1、判断是否为文件上传自定义URI("/upload") 2、判断是否为POST方法 3、判断Content-Type头是否包含multipart/form-data
        String contentType = msg.headers().get(HttpHeaderNames.CONTENT_TYPE);
        if(contentType == null || contentType.isEmpty()){
            return false;
        }
        return Objects.equals("/upload",uri)
                && ((FullHttpRequest) msg).method() == HttpMethod.POST
                && contentType.toLowerCase().contains(HttpHeaderValues.MULTIPART_FORM_DATA);
    }

    //判断是否为websocket握手请求
    private boolean isWebSocketHandShake(FullHttpRequest request){
        //1、判断是否为get 2、判断Upgrade头是否包含websocket 3、Connection头是否包含upgrade
        return request.method().equals(HttpMethod.GET)
                && request.headers().contains(HttpHeaderNames.UPGRADE, HttpHeaderValues.WEBSOCKET, true)
                && request.headers().contains(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE, true);
    }
    //处理握手
    private void handleShake(ChannelHandlerContext ctx, FullHttpRequest request){
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(null, null, false);
        WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), request);
            //保存socket的自定义ID与信道的对应关系
           /* Map<String, String> params = ParamUtil.getRequestParams(request);
            String id = params.get(MyConfig.SOCKET_ID);
            WsClientManager.getInstance().putChannel(id, ctx.channel());
            //绑定属性到channel
            ctx.channel().attr(HAND_SHAKE_ATTR).set(handshaker);
            ctx.channel().attr(SOCKET_ID_ATTR).set(id);*/
        }
    }

}
