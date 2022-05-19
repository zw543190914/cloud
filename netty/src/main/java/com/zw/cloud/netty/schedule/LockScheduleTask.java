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
public class LockScheduleTask {

    @Autowired
    private RedissonClient redissonClient;

    @Scheduled(cron = "0/2 * * * * ? ")
    @Async
    public void task1() {
        RLock lock = redissonClient.getLock("test_lock");
        lock.lock();
        log.info("[LockScheduleTask][task1] start, {}", Thread.currentThread().getName());
        throw new RuntimeException("ex");
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }*/
    }

    @Scheduled(cron = "0/2 * * * * ? ")
    @Async
    public void task2() {
        RLock lock = redissonClient.getLock("test_lock");
        lock.lock();
        log.info("[LockScheduleTask][task2] start, {}", Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
