package com.zw.cloud.netty.client;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.client.dto.NettyMsgDTO;
import com.zw.cloud.netty.client.dto.WebSocketConfigDTO;
import com.zw.cloud.netty.client.handler.WebSocketClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

@Slf4j
@Getter
@Setter
public class WebSocketClient {

    private WebSocketConfigDTO webSocketConfigDTO;

    private Channel channel;

    private String channelId;

    private Integer connnetFailureCountNum = 0;

    /**
     * addr地址
     */
    private String addr;

    private EventLoopGroup bossGroup;

    public WebSocketClient(String addr, WebSocketConfigDTO webSocketConfigDTO) {
        this.addr = addr;
        this.webSocketConfigDTO = webSocketConfigDTO;
        start();
    }

    /**
     * 关闭客户端
     */
    public void close() {
        log.info("webSocketClient正在关闭");
        try {
            log.info("关闭时，channelId={}", this.channel.id().asLongText());
            if (this.channel != null) {
                this.channel.close();
            }
        } catch (Exception e) {
            log.error("关闭websocket client 异常", e);
        } finally {
            if (this.bossGroup != null) {
                this.bossGroup.shutdownGracefully();
            }
        }
    }

    /**
     * 客户端初始化
     */
    private void start() {
        String url = "";
        try {
            Bootstrap bootstrap = new Bootstrap();
            this.bossGroup = new NioEventLoopGroup();
            bootstrap.group(bossGroup);
            //服务端可以探测客户端的连接是否还存活着,如果客户端因为断电或者网络问题或者客户端挂掉了等,那么服务端的连接可以关闭掉,释放资源。
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            //通常希望服务是低延迟的,建议将TCP_NODELAY设置为true。
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            //服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
            bootstrap.option(ChannelOption.SO_BACKLOG, webSocketConfigDTO.getBackLog());
            bootstrap.handler(new LoggingHandler(LogLevel.INFO));
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    //pipeline.addLast(new IdleStateHandler(60, 20, 60 * 10, TimeUnit.SECONDS));
                    //ChunkedWriteHandler分块写处理，文件过大会将内存撑爆
                    pipeline.addLast(new ChunkedWriteHandler());
                    /**
                     * 作用是将一个Http的消息组装成一个完成的HttpRequest或者HttpResponse，那么具体的是什么
                     * 取决于是请求还是响应, 该Handler必须放在HttpServerCodec后的后面
                     */
                    pipeline.addLast(new ChannelHandler[]{new HttpClientCodec(),
                            new HttpObjectAggregator(webSocketConfigDTO.getDataMaxLength())});
                    WebSocketClientHandler webSocketClientHandler = new WebSocketClientHandler();
                    webSocketClientHandler.setWebSocketClient(WebSocketClient.this);
                    pipeline.addLast("hookedHandler", webSocketClientHandler);

                }
            });

            url = String
                    .format("%s://%s%s?identity=%s&orgCode=%s&token=%s", webSocketConfigDTO.getWsProtocol(), addr,
                            webSocketConfigDTO.getWsPath(),
                            webSocketConfigDTO.getIdentity(), webSocketConfigDTO.getIdentity(),
                            webSocketConfigDTO.getIdentityToken());
            URI websocketURI = new URI(url);
            // //根据升级协议，获取http请求头sec-websocket-version获取客户端支持版本
             //根据版本创建不同版本握手对象
            WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory
                    .newHandshaker(websocketURI, WebSocketVersion.V13, null, true, new DefaultHttpHeaders(),
                            webSocketConfigDTO.getDataMaxLength());
            this.channel = bootstrap.connect(websocketURI.getHost(), websocketURI.getPort()).sync().channel();
            WebSocketClientHandler handler = (WebSocketClientHandler) this.channel.pipeline().get("hookedHandler");
            handler.setHandshaker(handshaker);
            handshaker.handshake(channel);
            //阻塞等待是否握手成功
            handler.handshakeFuture().sync();

            log.info("webSocketClient启动成功,addr-{}", addr);
        } catch (Exception e) {
            log.info("websocket client 初始化失败, url={}, ", url, e);
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 发送消息
     *
     * @param data            消息主体
     * @param targetGroupId   目标组别
     * @param tag             消息tag
     * @param targetChannelId 目标ChannelId
     */
    public void sendMsg(Object data, String targetGroupId,
            String tag, String targetChannelId, WebSocketConfigDTO webSocketConfigDTO) {
        NettyMsgDTO<Object> nettyMsgDTO = new NettyMsgDTO<>();
        try {
            nettyMsgDTO.setIdentity(webSocketConfigDTO.getIdentity());
            nettyMsgDTO.setTargetGroupId(targetGroupId);
            nettyMsgDTO.setData(data);
            nettyMsgDTO.setTag(tag);
            nettyMsgDTO.setOnlySenderReceive(false);
            nettyMsgDTO.setTargetChannelId(targetChannelId);
//            log.info("发送内容 nettyMsgDTO = {}", JsonUtil.toJsonString(nettyMsgDTO));
            TextWebSocketFrame frame = new TextWebSocketFrame(JSON.toJSONString(nettyMsgDTO));
            if (channel == null) {
                log.info("channel is null, return");
                return;
            }
            channel.writeAndFlush(frame).addListener((ChannelFutureListener) channelFuture1 -> {
                try {
                    if (channelFuture1.isSuccess()) {
                        //删除记录
                        connnetFailureCountNum = 0;
                    } else {
                        connnetFailureCountNum++;
                        log.warn("发送消息失败={}  ", channelFuture1.cause().getMessage());
                    }
                } catch (Exception e) {
                    log.error("发送消息异常", e);
                }
            });
        } catch (Exception e) {
            log.error("webClient monitor客户端发送消息失败， msg={}", JSON.toJSONString(nettyMsgDTO), e);
        }
    }
}
