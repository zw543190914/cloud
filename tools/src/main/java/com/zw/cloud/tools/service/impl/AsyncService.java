package com.zw.cloud.tools.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncService {


    @Async("asyncTaskExecutor")
    public void asyncTask() {
        log.info("[ScheduleService]asyncTask thread is " + Thread.currentThread().getName());
    }

}
