package com.zw.cloud.netty.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisson")
public class RedissonClientController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/tryLock")
    //http://localhost:18092/redisson/tryLock
    public void tryLock(){
        /*for (int j = 0; j < 100; j++) {
            test();
        }*/
        test();
    }
    //AtomicInteger atomicInteger = new AtomicInteger(1);
    int i = 1;
    private void test() {
        RLock lock = redissonClient.getLock("test_lock");

            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁 --watchLock失效
            //tryLock = lock.tryLock(100,10, TimeUnit.SECONDS);
            // 要使 watchLog机制生效 ，lock时 不要设置 过期时间
        boolean tryLock = lock.tryLock();
        if (tryLock) {
            try {
                System.out.println(i++);
                TimeUnit.SECONDS.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}
