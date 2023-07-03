package com.zw.cloud.netty.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisson")
@Slf4j
public class RedissonClientController {

    @Autowired
    private RedissonClient redissonClient;
    @Resource(name = "ioThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor ioThreadPoolTaskExecutor;

    @GetMapping("/lock")
    //http://localhost:18093/redisson/lock
    public void lock(){
        ioThreadPoolTaskExecutor.execute(this::test);
    }

    private void test() {
        RLock lock = redissonClient.getLock("test_lock");
        lock.lock(8,TimeUnit.SECONDS);
        log.info("[lock] start ");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                log.info("[lock] lock.isLocked() ");
                lock.unlock();
            }
        }
    }
}
