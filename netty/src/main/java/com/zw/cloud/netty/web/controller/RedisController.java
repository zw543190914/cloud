package com.zw.cloud.netty.web.controller;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.zw.cloud.netty.utils.IpAddressUtils;
import com.zw.cloud.netty.utils.RedisUtils;
import com.zw.cloud.netty.web.entity.poem.Poem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/getObject/{key}")
    //http://localhost:18092/redis/getObject/zw
    public Poem getObject(@PathVariable("key") String key) {
        redisUtils.set(key,buildPoem(),60);
        Poem poem = (Poem)redisUtils.get(key);
        log.info("[RedisController][getObject] poem is {}",JSON.toJSONString(poem));
        return poem;
    }

    @GetMapping("/setAdd/{key}")
    //http://localhost:18092/redis/setAdd/setTest
    public Set<Object> setAdd(@PathVariable("key") String key){
        redisUtils.sSetAndTime(key,60,buildPoem(),buildPoem());
        return redisUtils.sGet(key);
    }

    @GetMapping("/sortedSet")
    //http://localhost:18092/redis/sortedSet
    public Set sortedSet(){
        for (int i = 0; i < 5; i++) {
            redisTemplate.opsForZSet().remove("zw", i);
            redisTemplate.opsForZSet().add("zw", String.valueOf(i), i);
        }
        redisTemplate.opsForZSet().incrementScore("zw","0",2);

        // 倒序前三
        Set<ZSetOperations.TypedTuple<String>> zw = redisTemplate.opsForZSet().reverseRangeWithScores("zw", 0, 3);
        zw.forEach(k -> {
            System.out.println(k.getValue());
            System.out.println(JSON.toJSONString(k));
        });
        Set zw1 = redisTemplate.opsForZSet().rangeByScore("zw", 3, 5);
        zw1.forEach(k -> {
            System.out.println(JSON.toJSONString(k));
        });
        return zw;
    }

    @GetMapping("/hmset/{key}")
    //http://localhost:18092/redis/hmset/hmsetKey
    public Object hmset(@PathVariable("key") String key){
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2",buildPoem());
        boolean success = redisUtils.hmset(key, map, 60);
        Map<Object, Object> resultMap = redisUtils.hmget(key);
        log.info("[RedisController][hmset]success is {},resultMap is {}",success,JSON.toJSONString(resultMap));
        return redisUtils.hget(key,"key2");
    }

    @GetMapping("/listSet/{key}")
    //http://localhost:18092/redis/listSet/listKey
    public List<Object> listSet(@PathVariable("key") String key){
        ArrayList<Poem> poems = Lists.newArrayList(buildPoem(), buildPoem());
        boolean success = redisUtils.lSet(key, poems, 60);
        List<Object> list = redisUtils.lGet(key, 0, -1);
        log.info("[RedisController][listSet]success is {},list is {}",success,JSON.toJSONString(list));
        return list;
    }

    @GetMapping("/setnx")
    //http://localhost:18092/redis/setnx
    public Boolean setnx(){
        return redisUtils.setnx("key", "value", 60);
    }

    private Poem buildPoem() {
        Poem poem = new Poem();
        poem.setId(1L);
        poem.setTitle("test");
        return poem;
    }

}
