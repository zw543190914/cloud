package com.zw.cloud.tools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asyncTaskExecutor/monitoring")
public class ThreadPoolTaskMonitoringController {

    @Autowired
    @Qualifier("asyncTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping
    //http://localhost:9040/asyncTaskExecutor/monitoring
    public String monitoring() {
        int corePoolSize = threadPoolTaskExecutor.getCorePoolSize();
        int activeCount = threadPoolTaskExecutor.getActiveCount();
        int poolSize = threadPoolTaskExecutor.getPoolSize();
        int maxPoolSize = threadPoolTaskExecutor.getMaxPoolSize();
        return "corePoolSize:" + corePoolSize + " activeCount:" + activeCount + " poolSize:" + poolSize + " maxPoolSize:" + maxPoolSize;
    }
}
