package com.zw.cloud.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zw.cloud.global.response.wrapper.entity.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
@RefreshScope
public class SentinelController {

    private Logger logger = LoggerFactory.getLogger(SentinelController.class);

    @GetMapping(value = "/hotKey")
    @SentinelResource(value = "hotKey", blockHandler = "dealHotKey")
    // http://localhost:8090/sentinel/hotKey?name=1&value=2
    public WebResult testHotKey(@RequestParam String name, @RequestParam(required = false)  String value) {
        return WebResult.success().withData(name);
    }

    public WebResult dealHotKey(String name, String value, BlockException exception) {
        return WebResult.fail("热点被限流:" + exception.getClass().getCanonicalName());
    }

    /**
     * 处理 sentinel 限流配置 == blockHandler
     * @return
     */
    @GetMapping(value = "/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler")
    // http://localhost:8090/sentinel/customerBlockHandler
    public WebResult customerBlockHandler() {

        return WebResult.success().withData("succcess");
    }


    /**
     * 处理程序运行异常==fallbacl
     * @return
     */
    @GetMapping(value = "/runtimeException")
    @SentinelResource(value = "runtimeException"/*,fallback = "fallback",exceptionsToIgnore = {IllegalArgumentException.class}*/)
    // http://localhost:8090/sentinel/runtimeException?age=1
    public WebResult runtimeException(int age) {
        if (5 == age){
            throw new RuntimeException("age is error");
        }
        return WebResult.success().withData("succcess");
    }

    public WebResult fallback(int age,Throwable throwable) {
        return WebResult.fail("自定义异常：" + throwable.getMessage());
    }

}

