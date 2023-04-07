package com.zw.cloud.tools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = "com.zw.cloud.tools.dao")
@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass = true)
//@EnableScheduling
public class ToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsApplication.class, args);
        /**
         * ApplicationPreparedEvent
         * ApplicationStartedEvent
         * ApplicationReadyEvent
         */
    }

}
