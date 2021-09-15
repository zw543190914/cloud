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


    @Async
    public void test() throws Exception{
        log.info("test thread is " + Thread.currentThread().getName());
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            log.info("thread is " + Thread.currentThread().getName());
            return "haha";
        });
        new Thread(futureTask).start();

        System.out.println(futureTask.get());
    }

   /* @Scheduled(fixedRate = 1000)
    public void test2(){
        log.info("test2 thread is " + Thread.currentThread().getName());
    }*/


}
