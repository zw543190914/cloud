package com.zw.cloud.feignprovider.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorFrameworkConfig {

    private Logger logger = LoggerFactory.getLogger(CuratorFrameworkConfig.class);

    @Bean
    public CuratorFramework getZkClient() {

        logger.info("[CuratorFrameworkConfig]getZkClient start");
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("demo")
                .build();
        zkClient.start();
        logger.info("[CuratorFrameworkConfig]getZkClient end");
        return zkClient;
    }

}
