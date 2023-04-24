package com.zw.cloud.tools.schedule.task;

import com.zw.cloud.tools.utils.CustomerExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class ScheduleTask {

    AtomicInteger count = new AtomicInteger();

    @Async("asyncTaskExecutor")
    @Scheduled(cron = "35 0/2 * * * ? ")
    public void task() throws InterruptedException {
        log.info("[ScheduleTask][task] start");
        int increment = count.getAndIncrement();
        TimeUnit.SECONDS.sleep(50);
        log.info("[ScheduleTask][task] {}",Thread.currentThread().getName() + ":" +increment);
    }

    public static void main(String[] args) {
        CustomerExecutorService.scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("haha");
        },0, 30, TimeUnit.SECONDS);
    }
}
