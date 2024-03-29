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
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        log.info("[NettyServerInitializer][initChannel] 收到新连接");
        ChannelPipeline pipeline= socketChannel.pipeline();
        //以下三个是Http的支持
        //http解码器
        pipeline.addLast(new HttpServerCodec());
        //http聚合器 聚合 websocket 的数据帧，因为客户端可能分段向服务器端发送数据
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024 * 10));
        //支持写大数据流 以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        // webSocket 数据压缩扩展，当添加这个的时候WebSocketServerProtocolHandler的第三个参数需要设置成true
        pipeline.addLast(new WebSocketServerCompressionHandler());
        // ====================== 增加心跳支持 start    ======================
        // 针对客户端，如果在30秒没有向服务端发送读写心跳(ALL)，则主动断开
        // 读或者写空闲以及读写空闲
        pipeline.addLast(new IdleStateHandler(5, 8, 10, TimeUnit.MINUTES));
        // 自定义的空闲状态检测
        pipeline.addLast(new HeartBeatHandler());
        // ====================== 增加心跳支持 end    ======================

        //添加自定义的助手类
        pipeline.addLast(new ServerHandler());
        // 服务器端向外暴露的 web socket 端点，当客户端传递比较大的对象时，maxFrameSize参数的值需要调大
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws",null,true,10485760));

    }
}