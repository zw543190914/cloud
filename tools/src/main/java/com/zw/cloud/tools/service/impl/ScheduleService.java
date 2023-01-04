package com.zw.cloud.tools.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.FutureTask;

@Service
@Slf4j
public class ScheduleService {


    @Async("asyncTaskExecutor")
    public void test() throws Exception{
        log.info("[ScheduleService]test thread is " + Thread.currentThread().getName());
        /*FutureTask<String> futureTask = new FutureTask<String>(() -> {
            log.info("[ScheduleService] futureTask thread is " + Thread.currentThread().getName());
            return "haha";
        });
        new Thread(futureTask).start();
        log.info("[ScheduleService]test futureTask.get() is {}" , futureTask.get());
        */
        log.info("[ScheduleService]test thread is " + Thread.currentThread().getName());
    }

}
