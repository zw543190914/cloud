package com.zw.cloud.tools.schedule.task;

import com.zw.cloud.tools.utils.CustomerExecutorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduleTask {

    AtomicInteger count = new AtomicInteger();

    //@Scheduled(fixedDelay = 5000)
    public void task() {
        int increment = count.getAndIncrement();
        System.out.println(Thread.currentThread().getName() + ":" +increment);
    }

    public static void main(String[] args) {
        CustomerExecutorService.scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("haha");
        },0, 30, TimeUnit.SECONDS);
    }
}
