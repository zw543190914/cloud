package com.zw.cloud.netty.server;

import com.zw.cloud.netty.utils.IpAddressUtils;
import com.zw.cloud.netty.utils.RedisUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Server {

    public Server(int port, RedisUtils redisUtils){
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)  // 绑定线程池
                    .channel(NioServerSocketChannel.class) // 指定使用的channel
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.SO_BACKLOG,1024*1024*10)
                    .childHandler(new NettyServerInitializer());

            //标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            //Netty4使用对象池，重用缓冲区
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //是否启用心跳保活机制
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            //禁止使用Nagle算法，便于小数据即时传输
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            // LEAK: ByteBuf.release() was not called before it's garbage-collected.
            ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
            // 	绑定端口，同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            future.addListener(f -> {
                if (f.isSuccess()) {
                    log.info("[Netty Server] Server startup.. port is {}",port);
                } else {
                    log.error("[Netty Server] Server startup fail");
                }
            });
            redisUtils.sSet("netty-ws-server", IpAddressUtils.getIpAddress() + "#" + port);
            //等待服务监听端口关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            redisUtils.del("netty-ws-server");
            log.warn("[Netty Server] Server shutdown.. ");
        }
    }
}
