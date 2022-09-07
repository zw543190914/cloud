package com.zw.cloud.dubboconsumer.controller;

import com.zw.cloud.dubboconsumer.base.ThreadContext;
import com.zw.cloud.dubboproviderapi.service.IProviderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 直接调用服务提供者方法
 */
@RestController
@RequestMapping("/dubbo/consumer")
@Slf4j
public class DubboConsumerController {

    @DubboReference(check = false,mock = "com.zw.cloud.dubboconsumer.service.ProviderServiceMock")
    private IProviderService providerService;

    @Value("${spring.datasource.druid.url}")
    private String url;

    @GetMapping("/consumerToProvider/{msg}")
    //http://localhost:8020/dubbo/consumer/consumerToProvider/consumer
    public String consumerToProvider(@PathVariable String msg){
        ThreadContext.getWorkIdThreadLocal().set("001");
        log.info("[DubboConsumerController][consumerToProvider]workId is 001");
        return providerService.testProvider(msg);
    }

}
