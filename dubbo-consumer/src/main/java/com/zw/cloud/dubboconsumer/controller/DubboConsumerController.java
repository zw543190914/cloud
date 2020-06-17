package com.zw.cloud.dubboconsumer.controller;

import com.zw.cloud.dubboproviderapi.service.IProviderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 直接调用服务提供者方法
 */
@RestController
@RequestMapping("/dubbo/consumer")
public class DubboConsumerController {
    @Reference
    private IProviderService providerService;

    @GetMapping("/consumerToProvider/{msg}")
    //http://localhost:8020/dubbo/consumer/consumerToProvider/consumer
    public String consumerToProvider(@PathVariable String msg){
        return providerService.testProvider(msg);
    }

}
