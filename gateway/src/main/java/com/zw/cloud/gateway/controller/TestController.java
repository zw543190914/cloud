package com.zw.cloud.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/gateway")
@RestController
@Slf4j
@RefreshScope
public class TestController {
    @Value("${spring.datasource.druid.url}")
    private String url;
    @Value("${spring.environment}")
    private String environment;

    @GetMapping
    //http://localhost:8000/gateway
    public void test(){
        log.info("[TestController][test] environment is {},url is {}",environment,url);
    }
}
