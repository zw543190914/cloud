package com.zw.cloud.websocket.server.endpoint;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.websocket.entity.WebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 前后端交互的类实现消息的接收推送(自己发送给所有人(不包括自己))
 *
 * @ServerEndpoint(value = "/test/oneToMany") 前端通过此URI 和后端交互，建立连接
 * ws://localhost:18092/test/oneToMany
 * http://localhost:18092/index.html
 */
@Slf4j
@ServerEndpoint(value = "/test/oneToMany/{userId}")
@Component
public class OneToManyWebSocket {

    /** 记录当前在线连接数 */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /** 存放所有在线的客户端 userId -> Session */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    /** 存放所有在线的客户端 sessionId -> userId */
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
    public void onOpen(@PathParam("userId") String userId, Session session) {
        if (clients.containsKey(userId)) {
            try {
                session.close();
                log.info("[OneToManyWebSocket][onOpen] session is {},userId repeated ", session.getId());
            } catch (IOException e) {
                log.error("[OneToManyWebSocket][onOpen] session is {},session.close error is ", session.getId(), e);
            }
            return;
        }
        onlineCount.incrementAndGet(); // 在线数加1
        clients.put(userId, session);
        sessionIdToUserIdMap.put(session.getId(),userId);
        log.info("[OneToManyWebSocket][onOpen] 有新连接加入：{},当前在线人数为：{},clients size is {}", session.getId(), onlineCount.get(),clients.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        String sessionId = sessionIdToUserIdMap.remove(session.getId());
        if (StringUtils.isNotBlank(sessionId)) {
            Session removeSession = clients.remove(sessionId);
            onlineCount.decrementAndGet(); // 在线数减1
            log.info("[OneToManyWebSocket][onClose] 有一连接关闭：{}，当前在线人数为：{}", removeSession.getId(), onlineCount.get());
        }

    }

    /**
     * 收到客户端消息后调用的方法
     * {"currentId":"001","targetId":"002","msg":"test2"}
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("[OneToManyWebSocket][onMessage] 服务端收到客户端[{}]的消息:{}", session.getId(), message);
        WebSocketMessage myMessage = JSON.parseObject(message, WebSocketMessage.class);
        this.sendMessage(myMessage, session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("[OneToManyWebSocket][onError] 发生错误");
        error.printStackTrace();
    }

    /**
     * 群发消息
     */
    private void sendMessage(WebSocketMessage message, Session fromSession) {
        if (StringUtils.isNotBlank(message.getTargetId())) {
            Session toSession = clients.get(message.getTargetId());
            toSession.getAsyncRemote().sendText(JSON.toJSONString(message));
            return;
        }
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            Session toSession = sessionEntry.getValue();
            // 排除掉自己
            if (!fromSession.getId().equals(toSession.getId())) {
                log.info("[OneToManyWebSocket][sendMessage] 服务端给客户端[{}]发送消息{}", toSession.getId(), message);
                toSession.getAsyncRemote().sendText(JSON.toJSONString(message));
            }
        }
    }}
