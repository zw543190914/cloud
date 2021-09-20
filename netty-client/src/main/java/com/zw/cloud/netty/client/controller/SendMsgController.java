package com.zw.cloud.netty.client.controller;

import com.zw.cloud.netty.client.util.WebSocketSendMsgUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMsgController {

    @GetMapping("/sendMsg")
    //http://localhost:18093/sendMsg?msg=test2
    public void sendMsg(String msg){
        WebSocketSendMsgUtil.sendMsg(msg,"group1","tag1");
    }
}
