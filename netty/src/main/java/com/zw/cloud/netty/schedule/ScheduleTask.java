package com.zw.cloud.netty.schedule;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengwei
 * @date 2022/4/21 19:44
 */
@Component
@Slf4j
public class ScheduleTask {

    @Autowired
    private RedissonClient redissonClient;

    @Scheduled(cron = "0/5 * * * * ? ")
    @Async
    public void task1() {
        RLock lock = redissonClient.getLock("test_lock");
        boolean hasLock;
        try {
            hasLock = lock.tryLock(0,3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("[ScheduleTask][task1] error is ", e);
            return;
        }
        if (!hasLock) {
            log.info("[ScheduleTask][task1] 获取锁失败, {}", Thread.currentThread().getName());
            return;
        }
        log.info("[ScheduleTask][task1] start, {}", Thread.currentThread().getName());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/5 * * * * ? ")
    @Async
    public void task2() {
        RLock lock = redissonClient.getLock("test_lock");
        boolean hasLock;
        try {
            hasLock = lock.tryLock(0,3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("[ScheduleTask][task2] error is ", e);
            return;
        }
        if (!hasLock) {
            log.info("[ScheduleTask][task2] 获取锁失败, {}", Thread.currentThread().getName());
            return;
        }
        log.info("[ScheduleTask][task2] start, {}", Thread.currentThread().getName());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
