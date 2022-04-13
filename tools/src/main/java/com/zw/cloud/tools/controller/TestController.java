package com.zw.cloud.tools.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author zhengwei
 * @date 2022/4/12 18:53
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @GetMapping
    //http://127.0.0.1:9040/test
    public List<Map<String,Object>> queryList(){
        Map<String,Object> jsonObject = new HashMap<>();
        jsonObject.put("title","新冠疫情");
        jsonObject.put("time", LocalDateTime.now());
        jsonObject.put("url","/images/nick.jpg");

        Map<String,Object> jsonObject2 = new HashMap<>();
        jsonObject2.put("title","商改");
        jsonObject2.put("time",new Date());
        jsonObject2.put("url","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.51tietu.net%2Fpic%2F2019-091108%2Fvmipqy4caz4vmipqy4caz4.jpg&refer=http%3A%2F%2Fimg9.51tietu.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650772948&t=c175d48d866995d3474b52bd4547972a");

        Map<String,Object> jsonObject3 = new HashMap<>();
        jsonObject3.put("title","美图");
        jsonObject3.put("time","2022-04-01");
        jsonObject3.put("url","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.jj20.com%2Fup%2Fallimg%2Fmx12%2F0F420115037%2F200F4115037-11.jpg&refer=http%3A%2F%2Fpic.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638372678&t=004b8405f7b0780a5226dbb99b04c924");
        return Lists.newArrayList(jsonObject, jsonObject2, jsonObject3);
    }
}
