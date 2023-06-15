package com.zw.cloud.mybatis.plus.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @GetMapping("/testClientAbortException")
    //http://127.0.0.1:8082/test/testClientAbortException
    public void testClientAbortException() throws ClientAbortException {
        throw new ClientAbortException("ClientAbortException");
    }

    @GetMapping("/testException")
    //http://127.0.0.1:8082/test/testException
    public void testException() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
    }

}
