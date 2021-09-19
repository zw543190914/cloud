package com.zw.cloud.websocket.Server.endpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zw.cloud.websocket.entity.OneToOneMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 前后端交互的类实现消息的接收推送(自己发送给另一个人)
 *
 * @ServerEndpoint(value = "/test/oneToOne") 前端通过此URI 和后端交互，建立连接
 * http://localhost:18092/oneToOne.html
 * {"msg":"你好", "name":"user1"}
 */
@Slf4j
@ServerEndpoint(value = "/test/oneToOne/{name}")
@Component
public class OneToOneWebSocket {

    /**
     * 记录当前在线连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    /**
     * 存放所有在线的客户端
     */
    private static Map<String, String> clientIdName = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("name") String name) {
        if (clients.containsKey(name)) {
            throw new RuntimeException("用户已存在");
        }
        onlineCount.incrementAndGet(); // 在线数加1
        clients.put(name, session);
        clientIdName.put(session.getId(), name);
        log.info("有新连接加入：{}，当前在线人数为：{},basicRemote : {}", name, onlineCount.get(), session.getBasicRemote());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        if (!clientIdName.containsKey(session.getId())) {
            return;
        }
        onlineCount.decrementAndGet(); // 在线数减1
        String name = clientIdName.get(session.getId());
        clients.remove(name);
        clientIdName.remove(session.getId());
        log.info("有一连接关闭：{}，当前在线人数为：{}", name, onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务端收到客户端[{}]的消息[{}]", session.getId(), message);
        try {
            OneToOneMessage myMessage = JSON.parseObject(message, OneToOneMessage.class);
            if (myMessage != null) {
                Session toSession = clients.get(myMessage.getName());
                if (toSession != null) {
                    this.sendMessage(myMessage.getMsg(), toSession);
                }
            }
        } catch (Exception e) {
            log.error("解析失败：{}", e);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 仅启动一次
     */
    @PostConstruct
    public void startCodeOrderHeart() {
        startHeart();
        System.out.println("启动心跳线程");
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息[{}]", toSession.getId(), message);
            if (message.equals("ping")) {
                toSession.getBasicRemote().sendText("pong");
            } else {
                toSession.getBasicRemote().sendText(message);
            }

        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败：", e);
        }
    }

    /**
     * 启动心跳包
     */
    private synchronized void startHeart() {
        // 心跳检测
        KeepHeartThread keepHeart = new KeepHeartThread();
        ExecutorService keepHeartExecutorService = Executors.newScheduledThreadPool(1);
        ((ScheduledExecutorService) keepHeartExecutorService).scheduleAtFixedRate(keepHeart, 1, 5, TimeUnit.SECONDS);
    }

    /**
     * @description server发送心跳包
     */
    private class KeepHeartThread implements Runnable {
        @Override
        public void run() {
            JSONObject heartJson = new JSONObject();
            heartJson.put("服务端心跳", "保持心跳");
            heartJson.put("commandCode", 1);
            LocalDateTime now = LocalDateTime.now();
            heartJson.put("timeStamp", now.getNano());
            try {
                log.debug("发送心跳包当前人数为:" + clients.size() + "当前时间:" + now);
                sendPing(heartJson.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 发送心跳包
     *
     * @param message
     */
    private synchronized void sendPing(String message) throws Exception {
        if (clients.size() <= 0) {
            return;
        }
        for (Session session : clients.values()) {
            log.debug("发心跳:{}", session.toString() + ",当前时间:" + LocalDateTime.now());
            session.getBasicRemote().sendText(message);
        }
    }

}
