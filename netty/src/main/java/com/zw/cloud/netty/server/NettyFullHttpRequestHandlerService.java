package com.zw.cloud.netty.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zw.cloud.common.utils.JjwtUtils;
import com.zw.cloud.netty.entity.dto.MultipartRequestDTO;
import io.jsonwebtoken.Claims;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zw.cloud.netty.server.handler.ServerHandler.*;
import static com.zw.cloud.netty.utils.UrlParamsUtils.getUrlParams;

@Slf4j
public class NettyFullHttpRequestHandlerService {

    public static void handlerFullHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception{
        String uri = request.uri();
        //下载任务处理
        // http://localhost:18888/downFile?path=C:\zj\application-qa.yml
        if (request.uri().contains("/downFile")) {
            log.info("[ServerHandler][channelRead]FullHttpRequest uri is {},downFile", uri);
            Map<String, String> paramMap = getUrlParams(uri);
            responseExportFile(ctx, paramMap.get("path"));
        }
        //文件上传
        if (isFileUpload(request)) {
            log.info("[ServerHandler][channelRead]FullHttpRequest uri is {},isFileUpload ", uri);
            try {
                MultipartRequestDTO MultipartBody = getMultipartBody(request);
                if (Objects.nonNull(MultipartBody)) {
                    Map<String, FileUpload> fileUploads = MultipartBody.getFileUploads();
                    //输出文件信息
                    for (String key : fileUploads.keySet()) {
                        //获取文件对象
                        FileUpload file = fileUploads.get(key);
                        log.info("[ServerHandler][channelRead]FullHttpRequest FileUpload fileName is {} ", file.getFile().getPath());
                        //获取文件流
                        InputStream in = new FileInputStream(file.getFile());
                        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
                        String content = bf.lines().collect(Collectors.joining("\n"));
                        //打印文件
                        log.info("[ServerHandler][channelRead]FullHttpRequest FileUpload content is {}  \n", content);
                            /*ChunkedStream chunkedStream = new ChunkedStream(in);
                            clients.writeAndFlush(chunkedStream);*/
                    }
                    //输出参数信息
                    JSONObject params = MultipartBody.getParams();
                    log.info("[ServerHandler][channelRead]FullHttpRequest FileUpload params is {}", JSONObject.toJSONString(params));

                    responseHttp(ctx, "success");
                }
            } finally {
                request.release();
            }

        } else if (isWebSocketHandShake(request)) { //判断是否为websocket握手请求
            log.info("[ServerHandler][channelRead]FullHttpRequest uri is {},isWebSocketHandShake ", uri);
            handleShake(ctx, request);
        } else {
            Map<String, String> paramMap = getUrlParams(uri);
            log.info("[ServerHandler][channelRead] 接收到客户端：{} 参数是：{}", ctx.channel().id(), JSON.toJSONString(paramMap));
            //如果url包含参数，需要处理
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                log.info("[ServerHandler][channelRead] 接收到客户端：{} newUri：{}", ctx.channel().id(), newUri);
            }
               /* if (uri.contains("/ws")) {
                    //接着建立请求--http一直保持
                    super.channelRead(ctx, msg);
                } else {
                    ctx.close();
                }*/
            ctx.close();
        }
    }

    //判断是否为文件上传
    private static boolean isFileUpload(FullHttpRequest msg) {
        String uri = msg.uri();
        //1、判断是否为文件上传自定义URI("/upload") 2、判断是否为POST方法 3、判断Content-Type头是否包含multipart/form-data
        String contentType = msg.headers().get(HttpHeaderNames.CONTENT_TYPE);
        if (contentType == null || contentType.isEmpty()) {
            return false;
        }
        return uri.contains("/upload")
                && ((FullHttpRequest) msg).method() == HttpMethod.POST
                && contentType.toLowerCase().contains(HttpHeaderValues.MULTIPART_FORM_DATA);
    }

    //判断是否为websocket握手请求
    private static boolean isWebSocketHandShake(FullHttpRequest request) {
        //1、判断是否为get 2、判断Upgrade头是否包含websocket 3、Connection头是否包含upgrade
        return request.method().equals(HttpMethod.GET)
                && request.headers().contains(HttpHeaderNames.UPGRADE, HttpHeaderValues.WEBSOCKET, true)
                && request.headers().contains(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE, true);
    }

    //处理握手
    private static void handleShake(ChannelHandlerContext ctx, FullHttpRequest request) {
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(null, null, true);
        WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            ChannelFuture handshake = handshaker.handshake(ctx.channel(), request);
            Map<String, String> params = getUrlParams(request.uri());
            final String accessToken = params.get("accessToken");
            if (StringUtils.isBlank(accessToken)) {
                log.warn("[ServerHandler][channelRead] accessToken is null");
                ctx.channel().close();
                return;
            }
            Claims claims = JjwtUtils.parseJwt(accessToken);
            final String userId = claims.getId();
            handshake.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    try {
                        io.netty.util.Attribute<String> channelAttr = ctx.channel().attr(USER_ID);
                        if (Objects.nonNull(channelAttr.get())) {
                            log.warn("[ServerHandler][channelRead]handleShake channelAttr not null");
                        } else {
                            log.info("[ServerHandler][channelRead]handleShake channelAttr set accessToken is {}",accessToken);
                            channelAttr.set(userId);
                        }
                        if (Objects.nonNull(userManage.get(userId))){
                            throw new RuntimeException("userId is error");
                        }
                        userManage.put(userId,ctx.channel());
                    } catch (Exception e) {
                        log.error("[ServerHandler][channelRead]handleShake failed");
                        responseHttp(ctx,"error");
                    }
                }
            });
            //保存socket的自定义ID与信道的对应关系
           /* Map<String, String> params = ParamUtil.getRequestParams(request);
            String id = params.get(MyConfig.SOCKET_ID);
            WsClientManager.getInstance().putChannel(id, ctx.channel());
            //绑定属性到channel
            ctx.channel().attr(HAND_SHAKE_ATTR).set(handshaker);
            ctx.channel().attr(SOCKET_ID_ATTR).set(id);*/
        }
    }

    private static void responseExportFile(ChannelHandlerContext ctx, String path) {
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
                        log.info("[ServerHandler][channelRead]responseExportFile fileName is {},transfer progress: {}", fileName, progress);
                        //System.out.println("file {} transfer progress: {}");
                    } else {
                        log.info("[ServerHandler][channelRead]responseExportFile fileName is {},transfer progress: {}/{}", fileName, progress, total);
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


    private static void responseHttp(ChannelHandlerContext ctx, String content) {
        // 1.设置响应
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));

        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");

        // 2.发送
        // 注意必须在使用完之后，close channel
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }
}
