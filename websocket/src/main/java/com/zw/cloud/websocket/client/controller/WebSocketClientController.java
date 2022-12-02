package com.zw.cloud.websocket.client.controller;

import com.zw.cloud.websocket.client.service.MsgSendService;
import com.zw.cloud.websocket.entity.WebSocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/client")
public class WebSocketClientController {
    @Autowired
    private MsgSendService msgSendService;

    /**
     * {"targetId":"002","msgContent":"test1"}
     */
    @PostMapping("/sendMsg")
    //http://127.0.0.1:18092/ws/client/sendMsg
    public void sendMsg(@RequestBody WebSocketMessage webSocketMessage) {
        msgSendService.sendMsg(webSocketMessage);
    }

}
