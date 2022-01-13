package com.zw.cloud.netty.server;

import com.zw.cloud.netty.server.handler.HeartBeatHandler;
import com.zw.cloud.netty.server.handler.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        log.info("[NettyServerInitializer][initChannel] 收到新连接");
        ChannelPipeline pipeline= socketChannel.pipeline();
        //以下三个是Http的支持
        //http解码器
        pipeline.addLast(new HttpServerCodec());
        //http聚合器
        pipeline.addLast(new HttpObjectAggregator(1024*1024));
        //支持写大数据流 以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        // webSocket 数据压缩扩展，当添加这个的时候WebSocketServerProtocolHandler的第三个参数需要设置成true
        pipeline.addLast(new WebSocketServerCompressionHandler());
        // ====================== 增加心跳支持 start    ======================
        // 针对客户端，如果在30秒没有向服务端发送读写心跳(ALL)，则主动断开
        // 如果是读空闲或者写空闲，不处理,读写空闲超过300秒，则断开连接
        //pipeline.addLast(new IdleStateHandler(0, 0, 300, TimeUnit.SECONDS));
        // 自定义的空闲状态检测
        pipeline.addLast(new HeartBeatHandler());
        // ====================== 增加心跳支持 end    ======================

        //添加自定义的助手类
        pipeline.addLast(new ServerHandler());
        //websocket支持,设置路由
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws",null,true,65535));

    }
}