package com.zw.cloud.netty.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zw.cloud.netty.entity.dto.MultipartRequestDTO;
import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.enums.EnumNettyMsgTag;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    public static void put(String senderId,Channel channel){
        manage.put(senderId,channel);
    }
    public static Channel get(String senderId){
        return manage.get(senderId);
    }

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
            log.info("[ServerHandler][channelRead]FullHttpRequest uri is {} ", uri);
            //下载任务处理
            // http://localhost:18888/downFile?path=C:\zj\application-qa.yml
            if (request.uri().contains("/downFile")) {
                Map<String,String> paramMap = getUrlParams(uri);
                responseExportFile(ctx, paramMap.get("path"));
            }
            //文件上传
            if(isFileUpload(request)){
                MultipartRequestDTO MultipartBody = getMultipartBody(request);
                if (Objects.nonNull(MultipartBody)) {
                    Map<String, FileUpload> fileUploads = MultipartBody.getFileUploads();
                    //输出文件信息
                    for (String key : fileUploads.keySet()) {
                        //获取文件对象
                        FileUpload file = fileUploads.get(key);
                        log.info("[ServerHandler][channelRead]FullHttpRequest FileUpload fileName is {} ",  file.getFile().getPath());
                        //获取文件流
                        InputStream in = new FileInputStream(file.getFile());
                        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
                        String content = bf.lines().collect(Collectors.joining("\n"));
                        //打印文件
                        log.info("[ServerHandler][channelRead]FullHttpRequest FileUpload content is {}  \n", content);

                    }
                    //输出参数信息
                    JSONObject params = MultipartBody.getParams();
                    //输出文件信息
                    log.info("[ServerHandler][channelRead]FullHttpRequest FileUpload params is {}", JSONObject.toJSONString(params));
                    handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
                }
                ctx.close();
            } else if(isWebSocketHandShake(request)) { //判断是否为websocket握手请求
                handleShake(ctx, request);
                Map<String,String> paramMap = getUrlParams(uri);
                if (MapUtils.isNotEmpty(paramMap)){
                    String newUri = uri.substring(0,uri.indexOf("?"));
                    log.info("[ServerHandler][channelRead]WebSocketHandShake 接收到客户端：{} newUri：{}", ctx.channel().id(),newUri);
                }
            } else {
                Map<String,String> paramMap = getUrlParams(uri);
                log.info("[ServerHandler][channelRead] 接收到客户端：{} 参数是：{}", ctx.channel().id(),JSON.toJSONString(paramMap));
                //如果url包含参数，需要处理
                if(uri.contains("?")){
                    String newUri = uri.substring(0,uri.indexOf("?"));
                    log.info("[ServerHandler][channelRead] 接收到客户端：{} newUri：{}", ctx.channel().id(),newUri);
                }
                if (uri.contains("/ws")) {
                    manage.put(paramMap.get("nickName"),ctx.channel());
                    //接着建立请求
                    super.channelRead(ctx, msg);
                } else {
                    ctx.close();
                }
            }

        } else if(msg instanceof WebSocketFrame){
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
            log.info("[ServerHandler][channelRead][sendAllMessage]message is null");
            return;
        }
        try {
            NettyMsgDTO<String> nettyMsgDTO = JSON.parseObject(message, NettyMsgDTO.class);
            String nickName = nettyMsgDTO.getNickName();
            String targetChannelId = nettyMsgDTO.getTargetUserId();
            if (EnumNettyMsgTag.HEART.getKey().equals(nettyMsgDTO.getTag())) {
                log.info("[ServerHandler][channelRead][sendAllMessage]nickName is {}, heart msg,nettyMsgDTO is {}",nickName,JSON.toJSONString(nettyMsgDTO));
            } else if (StringUtils.isBlank(targetChannelId)){
                clients.writeAndFlush(new TextWebSocketFrame(nettyMsgDTO.getData()));
            } else {
                Channel channel = manage.get(targetChannelId);
                channel.writeAndFlush(nettyMsgDTO.getData());
            }
        } catch (Exception e) {
            log.error("[ServerHandler][channelRead][sendAllMessage]msg转换DTO异常,msg is {},error is ",message,e);
            //收到信息后，群发给所有channel
            clients.writeAndFlush(new TextWebSocketFrame(message));
        }
    }


    private static Map<String,String> getUrlParams(String url){
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
            return map;

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
        log.info("[ServerHandler][channelRead]TextWebSocketFrame 接收到客户端：{}", ctx.channel().id());

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
            clients.writeAndFlush(new BinaryWebSocketFrame(byteBuf));
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
        return uri.contains("/upload")
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
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(null, null, true);
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

    public static void responseExportFile(ChannelHandlerContext ctx, String path) {
        File file = new File(path);
        try {

            //随机读取文件
            final RandomAccessFile raf = new RandomAccessFile(file, "r");
            long fileLength = raf.length();
            //定义response对象
            HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            //设置请求头部
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, fileLength);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/octet-stream; charset=UTF-8");
            String fileName = file.getName();
            response.headers().add(HttpHeaderNames.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            ctx.write(response);
            //设置事件通知对象
            ChannelFuture sendFileFuture = ctx
                    .write(new DefaultFileRegion(raf.getChannel(), 0, fileLength), ctx.newProgressivePromise());

            sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
                //文件传输完成执行监听器
                @Override
                public void operationComplete(ChannelProgressiveFuture future)
                        throws Exception {
                    log.info("[ServerHandler][channelRead]responseExportFile fileName is {}, transfer complete.", fileName);
                }
                //文件传输进度监听器
                @Override
                public void operationProgressed(ChannelProgressiveFuture future,
                                                long progress, long total) throws Exception {
                    if (total < 0) {
                        log.info("[ServerHandler][channelRead]responseExportFile fileName is {},transfer progress: {}", fileName,progress);
                        //System.out.println("file {} transfer progress: {}");
                    } else {
                        log.info("[ServerHandler][channelRead]responseExportFile fileName is {},transfer progress: {}/{}", fileName,progress,total);
                        //System.out.println("file {} transfer progress: {}/{}");
                    }
                }
            });
            //刷新缓冲区数据，文件结束标志符
            ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static MultipartRequestDTO getMultipartBody(FullHttpRequest request) {
        try {
            //创建HTTP对象工厂
            HttpDataFactory factory = new DefaultHttpDataFactory(true);
            //使用HTTP POST解码器
            HttpPostRequestDecoder httpDecoder = new HttpPostRequestDecoder(factory, request);
            httpDecoder.setDiscardThreshold(0);

            //自定义对象bean
            MultipartRequestDTO multipartRequest = new MultipartRequestDTO();
            //存放文件对象
            Map<String, FileUpload> fileUploads = new HashMap<>();
            //存放参数对象
            JSONObject body = new JSONObject();
            //通过迭代器获取HTTP的内容
            java.util.List<InterfaceHttpData> interfaceHttpDataList = httpDecoder.getBodyHttpDatas();
            for (InterfaceHttpData data : interfaceHttpDataList) {
                //如果数据类型为文件类型，则保存到fileUploads对象中
                if (data != null && InterfaceHttpData.HttpDataType.FileUpload.equals(data.getHttpDataType())) {
                    FileUpload fileUpload = (FileUpload) data;
                    fileUploads.put(data.getName(), fileUpload);
                }
                //如果数据类型为参数类型，则保存到body对象中
                if (data != null && data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                    Attribute attribute = (Attribute) data;
                    body.put(attribute.getName(), attribute.getValue());
                }
            }
            //存放文件信息
            multipartRequest.setFileUploads(fileUploads);
            //存放参数信息
            multipartRequest.setParams(body);
            return multipartRequest;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
