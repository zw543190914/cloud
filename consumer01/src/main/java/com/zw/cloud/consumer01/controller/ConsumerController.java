package com.zw.cloud.consumer01.controller;

import com.zw.cloud.consumer01.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private IConsumerService consumerService;

    @GetMapping("/consunerToProvider/{msg}")
    // http://127.0.0.1:8020/consumer/consunerToProvider/msg
    public String consunerToProvider(@PathVariable String msg){
        return consumerService.detail(msg);
    }

}
