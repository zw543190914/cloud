/*
package com.zw.cloud.netty.schedule;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class TryLockScheduleTask {

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
            log.error("[TryLockScheduleTask][task1] error is ", e);
            return;
        }
        if (!hasLock) {
            log.info("[TryLockScheduleTask][task1] 获取锁失败, {}", Thread.currentThread().getName());
            return;
        }
        log.info("[TryLockScheduleTask][task1] start, {}", Thread.currentThread().getName());
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
            log.error("[TryLockScheduleTask][task2] error is ", e);
            return;
        }
        if (!hasLock) {
            log.info("[TryLockScheduleTask][task2] 获取锁失败, {}", Thread.currentThread().getName());
            return;
        }
        log.info("[TryLockScheduleTask][task2] start, {}", Thread.currentThread().getName());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
*/
