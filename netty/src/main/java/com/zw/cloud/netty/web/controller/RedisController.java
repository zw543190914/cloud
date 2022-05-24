package com.zw.cloud.netty.web.controller;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.utils.IpAddressUtils;
import com.zw.cloud.netty.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    //AtomicInteger atomicInteger = new AtomicInteger(1);
    int i = 1;

    @GetMapping
    //http://localhost:18092/redis
    public Set sendMsg(){
       /* for (int i = 0; i < 5; i++) {
            redisTemplate.opsForZSet().remove("zw", i);
            redisTemplate.opsForZSet().add("zw", String.valueOf(i), i);
        }
        redisTemplate.opsForZSet().incrementScore("zw","0",2);*/
        // 倒序前三
        Set<ZSetOperations.TypedTuple<String>> zw = redisTemplate.opsForZSet().reverseRangeWithScores("zw", 0, 3);
        zw.forEach(k -> {
            System.out.println(k.getValue());
            System.out.println(JSON.toJSONString(k));
        });

        return zw;
    }

    @GetMapping("/setadd")
    //http://localhost:18092/redis/setadd
    public void setadd(){
        for (int j = 0; j < 10; j++) {
            redisTemplate.opsForSet().add("set",String.valueOf(j));
        }
        Boolean hasKey = redisTemplate.hasKey("set");
        System.out.println(hasKey);
    }

    @GetMapping("/setnx")
    //http://localhost:18092/redis/setnx
    public void setnx(){
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("key", "value", 60, TimeUnit.SECONDS);
        System.out.println(aBoolean);
    }

    @GetMapping("/tryLock")
    //http://localhost:18092/redis/tryLock
    public void tryLock(){
        for (int j = 0; j < 100; j++) {
            test();
        }
    }

    @GetMapping("/sSet")
    //http://localhost:18092/redis/sSet
    public void sSet() {
        redisUtils.sSet("netty-ws-server", IpAddressUtils.getIpAddress() + "#" + 111);
    }

    private void test() {
        Boolean setnx = redisUtils.setnx("test_lock", "uuid", 3);
        if (!setnx) {
            while (true) {
                if (redisUtils.setnx("test_lock", "uuid", 3)) {
                    break;
                }
            }
        }
        try {
            System.out.println(i++);
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisUtils.del("test_lock");
        }
    }
}
