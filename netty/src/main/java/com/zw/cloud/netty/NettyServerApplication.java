package com.zw.cloud.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyServerApplication {
    public static void main(String[] args) {
        //http://localhost:18092/socket.html
        SpringApplication.run(NettyServerApplication.class);

    }
}
