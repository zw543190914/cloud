package com.zw.cloud.feignconsumer.controller;

import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.feignconsumer.service.IFeignConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/consumer")
@Slf4j
public class FeignConsumerController {

    @Autowired
    private IFeignConsumerService feignConsumerService;

    @Value("${spring.datasource.druid.url}")
    private String url;

    private Logger logger = LoggerFactory.getLogger(FeignConsumerController.class);

    @GetMapping("/queryAllUser/{pageNo}/{pageSize}")
    //http://localhost:9010/feign/consumer/queryAllUser/1/10
    public WebResult queryAllUser(@PathVariable Integer pageNo, @PathVariable Integer pageSize){
        log.info("[FeignConsumerController][queryAllUser] druid.url is {}",url);
        return feignConsumerService.queryAllUser(pageNo, pageSize);
    }

}

