package com.zw.cloud.netty.server;

import com.zw.cloud.netty.utils.IpAddressUtils;
import com.zw.cloud.netty.utils.RedisUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.HashMap;
import java.util.Map;

public class Server {

    public Server(int port, RedisUtils redisUtils){
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        try {
            b.group(bossGroup, workerGroup)  // 绑定线程池
                    .channel(NioServerSocketChannel.class) // 指定使用的channel
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.SO_BACKLOG,1024*1024*10)
                    .childHandler(new NettyServerInitializer());

            // 	绑定端口，同步等待成功
            ChannelFuture cf = b.bind(port).sync();
            System.out.println(" Server startup.. port: " + port);
            Map<String,Object> serverMap = new HashMap<>();
            serverMap.put(IpAddressUtils.getIpAddress() + "#" + port,360L);
            redisUtils.hmset("WEBSOCKET:NODE:LIST", serverMap);

            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            redisUtils.hdel("WEBSOCKET:NODE:LIST", IpAddressUtils.getIpAddress() + "#" + port);
            System.out.println(" Server shutdown.. ");
        }
    }
}
