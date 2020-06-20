package com.zw.cloud.feignconsumer.controller;

import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.feignconsumer.service.IFeignConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/consumer")
public class FeignConsumerController {

    @Autowired
    private IFeignConsumerService feignConsumerService;

    private Logger logger = LoggerFactory.getLogger(FeignConsumerController.class);

    @GetMapping("/queryAllUser/{pageNo}/{pageSize}")
    //http://localhost:9010//feign/consumer/queryAllUser/1/10
    public WebResult queryAllUser(@PathVariable Integer pageNo, @PathVariable Integer pageSize){
        return feignConsumerService.queryAllUser(pageNo, pageSize);
    }

}

