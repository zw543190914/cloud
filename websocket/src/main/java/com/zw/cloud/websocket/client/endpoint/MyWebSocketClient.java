package com.zw.cloud.websocket.client.endpoint;


import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

@Slf4j
public class MyWebSocketClient extends WebSocketClient {


    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public MyWebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("[MyWebSocketClient][onOpen]");

    }

    @Override
    public void onMessage(String s) {
        log.info("[MyWebSocketClient][onMessage] msg is {}",s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("[MyWebSocketClient][onClose] is is {},s is {},b is {}",i,s,b);

    }

    @Override
    public void onError(Exception e) {
        log.error("[MyWebSocketClient][onError] e is ",e);
    }
}
