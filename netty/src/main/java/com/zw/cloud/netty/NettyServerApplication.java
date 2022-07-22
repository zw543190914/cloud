package com.zw.cloud.netty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.zw.cloud.netty.web.dao")
//@EnableScheduling
public class NettyServerApplication {
    public static void main(String[] args) {
        //http://localhost:18092/socket.html
        SpringApplication.run(NettyServerApplication.class);

    }
}
