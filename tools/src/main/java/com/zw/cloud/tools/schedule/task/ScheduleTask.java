package com.zw.cloud.tools.schedule.task;

import com.zw.cloud.tools.utils.CustomerExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class ScheduleTask {

    AtomicInteger count = new AtomicInteger();

    @Async("asyncTaskExecutor")
    //@Scheduled(cron = "35 0/2 * * * ? ")
    public void task() {
        log.info("[ScheduleTask][task] start");
        int increment = count.getAndIncrement();
        log.info("[ScheduleTask][task] {}",Thread.currentThread().getName() + ":" +increment);
    }

    public static void main(String[] args) {
        /*CustomerExecutorService.scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("haha");
        },0L,0L, TimeUnit.SECONDS);*/
        CustomerExecutorService.scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getId() + ":" + System.currentTimeMillis());
            runTask();
        }, 5L, TimeUnit.SECONDS);

        //CustomerExecutorService.scheduledExecutorService.shutdown();
    }

    private static void runTask(){
        CustomerExecutorService.scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getId() + ":" + System.currentTimeMillis());
            runTask();
        }, 5L, TimeUnit.SECONDS);
    }
}
