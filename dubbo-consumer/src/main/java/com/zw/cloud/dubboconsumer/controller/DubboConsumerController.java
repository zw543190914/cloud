package com.zw.cloud.dubboconsumer.controller;

import com.zw.cloud.dubboconsumer.base.ThreadContext;
import com.zw.cloud.dubboproviderapi.service.IProviderService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.OpenOption;
import java.util.Optional;

/**
 * 直接调用服务提供者方法
 */
@RestController
@RequestMapping("/dubbo/consumer")
public class DubboConsumerController {
    @DubboReference(check = false)
    private IProviderService providerService;
    @Autowired
    private ThreadContext threadContext;
    @Value("${spring.datasource.druid.url}")
    private String url;

    @GetMapping("/consumerToProvider/{msg}")
    //http://localhost:8020/dubbo/consumer/consumerToProvider/consumer
    public String consumerToProvider(@PathVariable String msg){
        // 注入用户信息
        ThreadLocal<String> workIdThreadLocal = threadContext.getWorkIdThreadLocal();
        String workId = StringUtils.isBlank(workIdThreadLocal.get()) ? "0001" : workIdThreadLocal.get();
        RpcContext.getContext().getObjectAttachments().put("workId", workId);
        return providerService.testProvider(msg);
    }

}
