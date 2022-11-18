package com.zw.cloud.tools.controller;

import com.zw.cloud.common.annotation.SendDingTalkMsgOnException;
import com.zw.cloud.common.annotation.UseTimeCount;
import com.zw.cloud.common.exception.BizException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dingTalk")
public class DingTalkMsgControler {

    @GetMapping
    @SendDingTalkMsgOnException
    //@UseTimeCount
    //http://localhost:9040/dingTalk
    public void testAnnotation(){

        throw new RuntimeException("测试异常");
    }

}
