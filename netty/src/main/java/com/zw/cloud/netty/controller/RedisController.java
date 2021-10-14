package com.zw.cloud.netty.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

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
}
