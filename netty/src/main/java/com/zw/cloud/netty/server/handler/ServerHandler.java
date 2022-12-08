package com.zw.cloud.netty.server.handler;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.enums.EnumNettyMsgTag;
import com.zw.cloud.netty.enums.MsgSignFlagEnum;
import com.zw.cloud.netty.server.CustomerExecutorService;
import com.zw.cloud.netty.server.NettyFullHttpRequestHandlerService;
import com.zw.cloud.netty.utils.SpringUtil;
import com.zw.cloud.netty.web.entity.chat.ChatMsg;
import com.zw.cloud.netty.web.service.api.chat.IChatMsgService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    //属性名称：握手处理器
    private static final AttributeKey<WebSocketServerHandshaker> HAND_SHAKE_ATTR = AttributeKey.valueOf("HAND_SHAKE");

    public static final AttributeKey<String> USER_ID = AttributeKey.valueOf("USER_ID");

    // 用于记录和管理所有客户端的channle
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * userId -> Channel
     */
    public static ConcurrentHashMap<String, Channel> userManage = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //添加到channelGroup通道组
        clients.add(ctx.channel());
        log.info("[ServerHandler][channelActive] 与客户端 {} 建立连接，通道开启！现有连接数：{}", ctx.channel().id(), clients.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Attribute<String> channelAttr = ctx.channel().attr(USER_ID);
        if (Objects.nonNull(channelAttr.get())) {
            userManage.remove(channelAttr.get());
        }
        clients.remove(ctx.channel());
        log.info("[ServerHandler][channelInactive] 与客户端 {} 断开连接，userId is {},通道关闭！现有连接数：{}", ctx.channel().id(),channelAttr.get(), clients.size());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            NettyFullHttpRequestHandlerService.handlerFullHttpRequest(ctx,request);

        } else if (msg instanceof WebSocketFrame) {
            log.info("[ServerHandler][channelRead] WebSocketFrame 接收到客户端：{} ", ctx.channel().id());
            //处理websocket客户端的消息
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生了异常后关闭连接，同时从channelgroup移除
        ctx.channel().close();
        clients.remove(ctx.channel());
    }

    public static void sendTextMessage(NettyMsgDTO nettyMsgDTO) {

        String userId = nettyMsgDTO.getUserId();
        String targetUserId = nettyMsgDTO.getTargetUserId();
        String targetGroupId = nettyMsgDTO.getTargetGroupId();

        Integer tag = nettyMsgDTO.getTag();
        // 心跳消息
        if (Objects.equals(EnumNettyMsgTag.HEART.getType(),tag)) {
            log.info("[ServerHandler][channelRead][sendTextMessage] heart msg,userId is {},nettyMsgDTO is {}", userId, JSON.toJSONString(nettyMsgDTO));
            return;
        }

        if (Objects.equals(EnumNettyMsgTag.CONNECT.getType(),tag)) {
            //2.1 当websocket 第一次open的时候，初始化channel，把用的channel 和 userid 关联起来
            // 握手时已处理
            log.info("[ServerHandler][channelRead][sendTextMessage] 第一次(或重连)初始化连接,userId is {},nettyMsgDTO is {}", userId, JSON.toJSONString(nettyMsgDTO));
            return;
        }
        // 聊天消息
        if (Objects.equals(EnumNettyMsgTag.CHAT.getType(),tag)) {
            //2.2 聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态[未签收]
            ChatMsg chatMsg = new ChatMsg();
            chatMsg.setSendUserId(Long.parseLong(userId));
            chatMsg.setAcceptUserId(Long.parseLong(targetUserId));
            chatMsg.setMsg(nettyMsgDTO.getData());
            if (StringUtils.isNotBlank(targetGroupId)) {
                chatMsg.setAcceptGroupId(Long.parseLong(targetGroupId));
            }

            IChatMsgService chatMsgService = (IChatMsgService) SpringUtil.getBean("chatMsgServiceImpl");
            chatMsg.setSignFlag(MsgSignFlagEnum.unsign.getType());
            // 异步保存消息
            CustomerExecutorService.pool.submit(() -> chatMsgService.save(chatMsg));

            //发送消息
            sendMsg(nettyMsgDTO);

        }
        //  消息签收
        if (Objects.equals(EnumNettyMsgTag.SIGNED.getType(),tag)) {
            //2.3 签收消息类型，针对具体的消息进行签收，修改数据库中对应消息的签收状态[已签收]
            //扩展字段在signed 类型消息中 ，代表需要去签收的消息id，逗号间隔
            String msgIdsStr = nettyMsgDTO.getData();
            String[] msgId = msgIdsStr.split(",");

            List<Long> msgIdList = new ArrayList<>();
            for (String mid: msgId) {
                if(StringUtils.isNotBlank(mid)){
                    msgIdList.add(Long.parseLong(mid));
                }
            }
            if(CollectionUtils.isNotEmpty(msgIdList)){
                IChatMsgService chatMsgService = (IChatMsgService) SpringUtil.getBean("chatMsgServiceImpl");
                //批量签收
                chatMsgService.batchUpdateMsgSigned(msgIdList);
            }

        }
    }

    private static void sendMsg(NettyMsgDTO nettyMsgDTO) {
        String receiverId = nettyMsgDTO.getTargetUserId();
        String targetGroupId = nettyMsgDTO.getTargetGroupId();
        //发送消息
        // 私聊
        if (StringUtils.isNotBlank(receiverId)) {
            Channel receiverChannel = userManage.get(receiverId);
            if(Objects.nonNull(receiverChannel) && receiverChannel.isActive()){
                //用户在线
                receiverChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(nettyMsgDTO)));
            } else {
                log.info("[ServerHandler][channelRead][sendTextMessage]userId is {}, receiverId is {},用户离线", nettyMsgDTO.getUserId(), receiverId);
            }
            return;
        }
        if (StringUtils.isNotBlank(targetGroupId)) {
            // TODO 群消息
            return;
        }
        // 全部发送
        clients.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(nettyMsgDTO)));
    }

    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            log.info("[ServerHandler][channelRead]CloseWebSocketFrame 接收到客户端：{}", ctx.channel().id());
            WebSocketServerHandshaker handshaker = ctx.channel().attr(HAND_SHAKE_ATTR).get();
            if (handshaker == null) {
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                return;
            }
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            log.info("[ServerHandler][channelRead]PingWebSocketFrame 接收到客户端：{}", ctx.channel().id());
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 文本消息
        if (frame instanceof TextWebSocketFrame) {
            //正常的TEXT消息类型
            TextWebSocketFrame msg = (TextWebSocketFrame) frame;
            log.info("[ServerHandler][channelRead]TextWebSocketFrame channelId is {} msg is {}", ctx.channel().id(), msg.text());
            if (StringUtils.isBlank(msg.text())) {
                log.info("[ServerHandler][channelRead]TextWebSocketFrame message is null");
                return;
            }
            NettyMsgDTO nettyMsgDTO = JSON.parseObject(msg.text(), NettyMsgDTO.class);
            sendTextMessage(nettyMsgDTO);
        }
        if (frame instanceof BinaryWebSocketFrame) {
            /* ByteBuf content = frame.content();
            content.markReaderIndex();
            int flag = content.readInt();
            log.info("[ServerHandler][channelRead]BinaryWebSocketFrame 接收到客户端：{} 二进制消息,标志位: {}", ctx.channel().id(), flag);
            content.resetReaderIndex();

            ByteBuf byteBuf = Unpooled.directBuffer(frame.content().capacity());
            byteBuf.writeBytes(frame.content());

            clients.writeAndFlush(new BinaryWebSocketFrame(byteBuf));*/
            /*tag = (int)(params.get("tag"));
            userId = (String) (params.get("userId"));*/

            // 写入磁盘
            ByteBuf data = frame.content();
            //先读一个byte，确定文件名长度
            byte filenameLength = data.readByte();
            //分配一个HeapBuffer用于读取文件名
            ByteBuf fileName = ByteBufAllocator.DEFAULT.heapBuffer();
            data.readBytes(fileName,filenameLength);

            FileChannel fileChannel = null;
            try {
                String filePath = "C:\\download\\" + fileName.toString(StandardCharsets.UTF_8);
                //写入
                File file = new File(filePath);
                file.createNewFile();
                RandomAccessFile target = new RandomAccessFile(file, "rw");
                fileChannel = target.getChannel();
                //这里直接将PooledDirectByteBuf中余下的数据直接写入FileChannel
                data.getBytes(0, fileChannel, 0L, data.readableBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //最后要记得释放分配的内存空间，关闭FileChannel
                frame.release();
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        /*if (Objects.equals(tag,EnumNettyMsgTag.CONNECT.getType()) && StringUtils.isNotBlank(userId)) {
            userManage.put(userId,ctx.channel());
        }*/
    }


}
