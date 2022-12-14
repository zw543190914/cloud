package com.zw.cloud.netty.web.controller.chat;

import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.enums.EnumNettyMsgTag;
import com.zw.cloud.netty.server.handler.ServerHandler;
import io.netty.channel.Channel;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/socket")
public class WebSocketController {

    @PostMapping("/sendMsg")
    //http://localhost:18092/socket/sendMsg?msg=test&targetUserId=
    public void sendMsg(@RequestBody NettyMsgDTO nettyMsgDTO){
        ServerHandler.sendChatMsg(nettyMsgDTO);
    }

    @GetMapping("/onlineUserList")
    //http://localhost:18092/socket/onlineUserList
    public Enumeration<String> onlineUserList(){
        ConcurrentHashMap<String, Channel> userManage = ServerHandler.userManage;
        return userManage.keys();
    }

}
