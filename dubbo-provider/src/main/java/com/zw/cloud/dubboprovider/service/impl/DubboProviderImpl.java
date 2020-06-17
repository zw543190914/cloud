package com.zw.cloud.dubboprovider.service.impl;

import com.zw.cloud.dubboproviderapi.service.IProviderService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * dubbo 服务提供者，使用 dubbo 提供的service注解
 */
@Service
public class DubboProviderImpl implements IProviderService {

    @Value("${environment}")
    private String environment;
    @Value("${server.port}")
    private int port;

    @Override
    public String testProvider(String msg) {
        return String.format("this is dubbo provider, server port is %s, nacos environment is %s,consumer msg is %s",
                port,environment,msg);
    }
}
