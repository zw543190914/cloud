package com.zw.cloud.feignconsumer.controller;

import com.zw.cloud.global.response.wrapper.entity.WebResult;
import com.zw.cloud.feignproviderapi.client.FeignProviderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/consumer")
@Slf4j
public class FeignConsumerController {

    @Autowired
    private FeignProviderClient feignProviderClient;

    @Value("${spring.datasource.druid.url}")
    private String url;

    @GetMapping("/queryAllUser/{pageNo}/{pageSize}")
    //http://localhost:9010/feign/consumer/queryAllUser/1/10
    public WebResult queryAllUser(@PathVariable Integer pageNo, @PathVariable Integer pageSize){
        log.info("[FeignConsumerController][queryAllUser] druid.url is {}",url);
        return feignProviderClient.queryAllUser(pageNo, pageSize);
    }

}

