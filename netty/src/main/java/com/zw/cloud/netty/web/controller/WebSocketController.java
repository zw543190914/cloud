package com.zw.cloud.netty.web.controller;

import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.server.handler.ServerHandler;
import io.netty.channel.Channel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/socket")
public class WebSocketController {

    @GetMapping("/sendMsg")
    //http://localhost:18092/socket/sendMsg?msg=test&targetUserId=
    public void sendMsg(String msg,String targetUserId){
        NettyMsgDTO nettyMsgDTO = new NettyMsgDTO();
        nettyMsgDTO.setUserId("netty-server");
        nettyMsgDTO.setData(msg);
        nettyMsgDTO.setTargetUserId(targetUserId);
        ServerHandler.sendTextMessage(nettyMsgDTO);
    }

    @GetMapping("/onlineUserList")
    //http://localhost:18092/socket/onlineUserList
    public Enumeration<String> onlineUserList(){
        ConcurrentHashMap<String, Channel> userManage = ServerHandler.userManage;
        return userManage.keys();
    }

}
