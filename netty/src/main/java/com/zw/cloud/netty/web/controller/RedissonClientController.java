package com.zw.cloud.netty.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisson")
@Slf4j
public class RedissonClientController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/lock")
    //http://localhost:18092/redisson/lock
    public void lock(){
        long start = System.currentTimeMillis();
        test();
        System.out.println(System.currentTimeMillis() - start);
    }

    private void test() {
        RLock lock = redissonClient.getLock("test_lock");
        lock.lock(3,TimeUnit.SECONDS);
        log.info("[lock] start ");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
