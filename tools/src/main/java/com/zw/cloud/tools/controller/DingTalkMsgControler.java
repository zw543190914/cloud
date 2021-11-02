package com.zw.cloud.tools.controller;

import com.zw.cloud.common.annotation.SendDingTalkMsgOnException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dingTalk")
public class DingTalkMsgControler {

    @GetMapping
    @SendDingTalkMsgOnException
    //http://localhost:9040/dingTalk
    public void testAnnotation(){
        System.out.println(1/0);
    }
}
