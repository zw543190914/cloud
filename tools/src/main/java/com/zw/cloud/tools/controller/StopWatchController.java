package com.zw.cloud.tools.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stop-watch")
@Slf4j
public class StopWatchController {

    @GetMapping
    //http://localhost:9040/tc/stop-watch
    public void test() throws Exception{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("task1");
        Thread.sleep(100);
        stopWatch.stop();
        stopWatch.start("task2");
        Thread.sleep(200);
        stopWatch.stop();
        stopWatch.start("task3");
        Thread.sleep(300);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }
}
