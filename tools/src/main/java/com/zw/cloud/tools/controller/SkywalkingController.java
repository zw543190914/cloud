package com.zw.cloud.tools.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skywalking")
@Slf4j
public class SkywalkingController {

    /**
     * -javaagent:D:\skywalking-agent\skywalking-agent.jar -Dskywalking.agent.service_name=tools -Dskywalking.collector.backend_service=localhost:11800
     */
    @GetMapping
    //http://localhost:9040/skywalking
    public void test() {
        log.info("test info");
        log.warn("test warn");
        log.error("test error");
    }
}
