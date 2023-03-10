package com.zw.cloud.websocket.server.endpoint;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.websocket.entity.WebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 前后端交互的类实现消息的接收推送(自己发送给所有人(不包括自己))
 *
 * @ServerEndpoint(value = "/test/oneToMany") 前端通过此URI 和后端交互，建立连接
 * ws://localhost:18092/test/oneToMany
 * http://localhost:18092/chat/user/login
 */
@Slf4j
@ServerEndpoint(value = "/test/oneToMany/{username}")
@Component
public class OneToManyWebSocket {

    /** 记录当前在线连接数 */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    public static Map<String, Session> getClients() {
        return clients;
    }

    /** 存放所有在线的客户端 username -> Session */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    /** 存放所有在线的客户端 sessionId -> username */
    private static Map<String, String> sessionIdToUserIdMap = new ConcurrentHashMap<>();

    /**
     * 解决SpringBoot中webScocket不能注入bean的问题
     */
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext){
        OneToManyWebSocket.applicationContext = applicationContext;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        WebSocketMessage message = new WebSocketMessage();
        message.setCurrentId(username);
        if (clients.containsKey(username)) {
            message.setMsgType("0");
            message.setMsgContent(username + " 重复登陆,强制下线,请注意......");
            clients.get(username).getBasicRemote().sendText(JSON.toJSONString(message));
            session.getBasicRemote().sendText(JSON.toJSONString(message));
            session.close();
        }
        onlineCount.incrementAndGet(); // 在线数加1
        clients.put(username, session);
        sessionIdToUserIdMap.put(session.getId(),username);
        log.info("[OneToManyWebSocket][onOpen] 有新连接加入,username is {},sessionId is {},当前在线人数为：{},clients size is {}",username, session.getId(), onlineCount.get(),clients.size());
        message.setMsgType("-1");
        message.setMsgContent("【" + username + "】 上线");
        sendMessage(message,session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("username") String username,Session session) throws IOException {
        sessionIdToUserIdMap.remove(session.getId());
        Session removeSession = clients.remove(username);
        if (Objects.isNull(removeSession)) {
            return;
        }
        onlineCount.decrementAndGet(); // 在线数减1
        log.info("[OneToManyWebSocket][onClose] 有一连接关闭：{},username is {},当前在线人数为：{}", removeSession.getId(),username, onlineCount.get());
        WebSocketMessage message = new WebSocketMessage();
        message.setMsgContent("【" + username + "】 下线");
        message.setCurrentId(username);
        message.setMsgType("-2");
        sendMessage(message,session);
    }

    /**
     * 收到客户端消息后调用的方法
     * {"msgType":0,"msgContent":"test2"}
     */
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("username") String username) throws IOException {
        log.info("[OneToManyWebSocket][onMessage] 文本消息 服务端收到客户端[{}][{}]的消息:{}", session.getId(),username, message);
        WebSocketMessage myMessage = JSON.parseObject(message, WebSocketMessage.class);
        myMessage.setCurrentId(username);
        sendMessage(myMessage, session);
    }

    @OnMessage
    public void onMessage(byte[] message, Session session,@PathParam("username") String username) throws IOException {
        log.info("[OneToManyWebSocket][onMessage] 二进制消息 服务端收到客户端[{}][{}]的消息", session.getId(),username);
        WebSocketMessage myMessage = JSON.parseObject(message, WebSocketMessage.class);
        myMessage.setCurrentId(username);
        sendMessage(myMessage, session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        String username = sessionIdToUserIdMap.get(session.getId());
        log.error("[OneToManyWebSocket][onError] 发生错误,session.id is {},username is {},error is ",session.getId(),username,error);
    }

    /**
     * 群发消息
     */
    private void sendMessage(WebSocketMessage message, Session fromSession) throws IOException {
        message.setDate(LocalDateTime.now());
        if (StringUtils.isNotBlank(message.getTargetId())) {
            Session toSession = clients.get(message.getTargetId());
            if (Objects.isNull(toSession)) {
                message.setMsgContent(message.getTargetId() + " 不在线");
                fromSession.getAsyncRemote().sendText(JSON.toJSONString(message));
                return;
            }
            sendToOneMsg(message,fromSession,toSession);
            return;
        }
        String fromSessionId = fromSession.getId();
        int hasSendCount = 1;
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            Session toSession = sessionEntry.getValue();
            // 排除掉自己
            if (!fromSessionId.equals(toSession.getId())) {
                //log.info("[OneToManyWebSocket][sendMessage] 服务端给客户端[{}]发送消息{},total clients is {},has send {}", toSession.getId(), message,clients.size(),hasSendCount++);
                toSession.getAsyncRemote().sendText(JSON.toJSONString(message));
            }
        }
    }

    private void sendToOneMsg(WebSocketMessage message, Session fromSession,Session toSession) throws IOException {
        String type = message.getMsgType();
        //对方挂断
        if ("hangup".equals(type)) {
            message.setMsgContent("对方挂断！");
        }

        //视频通话请求
        if ("call_start".equals(type)) {
            message.setMsgContent("1");
        }

        toSession.getBasicRemote().sendText(JSON.toJSONString(message));

    }
}
