package com.zw.cloud.netty.controller;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.entity.dto.NettyMsgDTO;
import com.zw.cloud.netty.server.ServerHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class IndexController {


    AtomicInteger atomicInteger = new AtomicInteger(1);

    /*@GetMapping("/index")
    //http://localhost:18092/index
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView("socket");
        mav.addObject("uid", atomicInteger.getAndAdd(1));
        return mav;
    }*/

    @GetMapping("/sendMsg")
    //http://localhost:18092/sendMsg?msg=test
    public void sendMsg(String msg){
        NettyMsgDTO<String> nettyMsgDTO = new NettyMsgDTO<>();
        nettyMsgDTO.setUserId("0000");
        nettyMsgDTO.setData(msg);
        ServerHandler.sendAllMessage(nettyMsgDTO);
    }

}

