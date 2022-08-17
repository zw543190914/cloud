package com.zw.cloud.feignconsumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.zw.cloud.feignproviderapi.client")
@EnableDiscoveryClient
@MapperScan(basePackages = "com.zw.cloud.feignconsumer.test.dao,com.zw.cloud.feignconsumer.test2.dao")
public class FeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApplication.class, args);
    }

}
