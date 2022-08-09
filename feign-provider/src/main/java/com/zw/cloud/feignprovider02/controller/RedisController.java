package com.zw.cloud.feignprovider02.controller;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.feignprovider02.entity.User;
import com.zw.cloud.feignprovider02.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/provider/redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping
    //http://localhost:9000/provider/redis
    public String testObject(){
        User user = new User();
        user.setId(1L);
        user.setAge((byte)20);
        user.setDescription("test");
        redisUtils.set("1",user);
        Object result = redisUtils.get("1");
        System.out.println(result.toString());
        return JSON.toJSONString(result);
    }

    @GetMapping("/flushDb")
    //http://localhost:9000/provider/redis/flushDb
    public void flushDb(){

        redisUtils.flushDb();

    }

    @GetMapping("/testList")
    //http://localhost:9000/provider/redis/testList
    public List<Object> testList(){
        List<Object> userList = new ArrayList<>();
        for (int i = 0;i < 10 ;i ++){
            User user = new User();
            user.setId((long)i+1);
            user.setAge((byte)20);
            user.setDescription("test" + i);
            userList.add(user);
        }

        boolean first = redisUtils.lSet("first", userList);
        List<Object> objectList = redisUtils.lGet("first", 0, 3);
        System.out.println(objectList.toString());
        return objectList;
    }
}
