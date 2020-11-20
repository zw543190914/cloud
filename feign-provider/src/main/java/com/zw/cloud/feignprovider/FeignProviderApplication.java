package com.zw.cloud.feignprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.zw.cloud.dao")
@EnableScheduling
public class FeignProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignProviderApplication.class, args);
    }

}
