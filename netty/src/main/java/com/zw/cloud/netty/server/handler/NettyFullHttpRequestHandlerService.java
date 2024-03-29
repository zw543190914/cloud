package com.zw.cloud.netty.server.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zw.cloud.common.utils.JjwtUtils;
import com.zw.cloud.netty.entity.dto.MultipartRequestDTO;
import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.enums.EnumNettyMsgTag;
import com.zw.cloud.netty.utils.SpringUtil;
import com.zw.cloud.netty.web.entity.chat.UserInfo;
import com.zw.cloud.netty.web.entity.vo.UserVo;
import com.zw.cloud.netty.web.service.api.chat.IUserInfoService;
import io.jsonwebtoken.Claims;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
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

import static com.zw.cloud.netty.server.handler.ServerHandler.*;
import static com.zw.cloud.netty.utils.UrlParamsUtils.getUrlParams;

@Slf4j
public class NettyFullHttpRequestHandlerService {

    public static void handlerFullHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        String uri = request.uri();
        if (isWebSocketHandShake(request)) { //判断是否为websocket握手请求
            log.info("[ServerHandler][channelRead]FullHttpRequest WebSocketHandShake uri is {},isWebSocketHandShake ", uri);
            handleShake(ctx, request);
        }
    }

    /**
     * 判断是否为websocket握手请求
     */
    private static boolean isWebSocketHandShake(FullHttpRequest request) {
        //1、判断是否为get 2、判断Upgrade头是否包含websocket 3、Connection头是否包含upgrade
        return request.method().equals(HttpMethod.GET)
                && request.headers().contains(HttpHeaderNames.UPGRADE, HttpHeaderValues.WEBSOCKET, true)
                && request.headers().contains(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE, true);
    }

    /**
     * 处理握手
     */
    private static void handleShake(ChannelHandlerContext ctx, FullHttpRequest request) {
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(null, null, true);
        WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            ChannelFuture handshake = handshaker.handshake(ctx.channel(), request);
            Map<String, String> params = getUrlParams(request.uri());
            final String accessToken = params.get("accessToken");
            final String username = params.get("username");
            handshake.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (StringUtils.isBlank(accessToken) && StringUtils.isBlank(username)) {
                        log.warn("[ServerHandler][channelRead]FullHttpRequest WebSocketHandShake accessToken && username is null");
                        responseHttp(ctx, "error");
                        ctx.channel().writeAndFlush(new TextWebSocketFrame(buildCloseNettyMsgDTO()));
                        return;
                    }
                    String userId ;
                    if (StringUtils.isNotBlank(accessToken)) {
                        // mui-app websocket
                        Claims claims;
                        try {
                            // 过期会抛出异常 ExpiredJwtException
                            claims = JjwtUtils.parseJwt(accessToken);
                        } catch (Exception e) {
                            log.warn("[ServerHandler][channelRead]FullHttpRequest WebSocketHandShake accessToken is expiration");
                            responseHttp(ctx, "error");
                            ctx.channel().writeAndFlush(new TextWebSocketFrame(buildCloseNettyMsgDTO()));
                            return;
                        }
                        userId = claims.getId();
                    } else {
                        // web 网页登陆
                        String password = params.get("password");
                        IUserInfoService userInfoService = SpringUtil.getBean(IUserInfoService.class);
                        UserInfo userInfo = new UserInfo();
                        userInfo.setUsername(username);
                        userInfo.setPassword(password);
                        try {
                            UserVo userVo = userInfoService.registerOrLogin(userInfo);
                            userId = String.valueOf(userVo.getId());
                            JSONObject webLogin = new JSONObject();
                            webLogin.put("tag",EnumNettyMsgTag.WEB_USER_INFO.getType());
                            webLogin.put("userInfo",userVo);
                            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(webLogin)));
                        } catch (Exception e) {
                            log.warn("[ServerHandler][channelRead]FullHttpRequest WebSocketHandShake username is {},error is ",username,e);
                            responseHttp(ctx, e.getMessage());
                            return;
                        }
                    }
                    if (Objects.nonNull(userManage.get(userId))) {
                        //关闭重复连接
                        NettyMsgDTO nettyMsgDTO = new NettyMsgDTO();
                        nettyMsgDTO.setTag(EnumNettyMsgTag.CLOSE_WS.getType());
                        nettyMsgDTO.setData("用户在其他客户端登陆");
                        userManage.get(userId).writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(nettyMsgDTO)));
                        userManage.get(userId).close();
                    }

                    try {
                        io.netty.util.Attribute<String> channelAttr = ctx.channel().attr(USER_ID);
                        if (Objects.nonNull(channelAttr.get())) {
                            log.warn("[ServerHandler][channelRead]handleShake channelAttr not null");
                        } else {
                            log.info("[ServerHandler][channelRead]handleShake channelAttr accessToken is {}", accessToken);
                            channelAttr.set(userId);
                        }
                        userManage.put(userId, ctx.channel());
                        log.info("[ServerHandler][channelRead]handleShake success,现有连接数: {} ,userManage.size is {},userId is {}",clients.size(),userManage.size(),userId);
                    } catch (Exception e) {
                        log.error("[ServerHandler][channelRead]handleShake failed ",e);
                        responseHttp(ctx, e.getMessage());
                        ctx.channel().writeAndFlush(new TextWebSocketFrame(buildCloseNettyMsgDTO()));
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

    private static String buildCloseNettyMsgDTO() {
        NettyMsgDTO nettyMsgDTO = new NettyMsgDTO();
        nettyMsgDTO.setTag(EnumNettyMsgTag.LOGIN.getType());
        return JSON.toJSONString(nettyMsgDTO);
    }
    /**
     * 判断是否为文件上传
     */
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
