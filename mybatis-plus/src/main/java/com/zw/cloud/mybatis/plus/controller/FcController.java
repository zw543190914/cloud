package com.zw.cloud.mybatis.plus.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.zw.cloud.mybatis.plus.entity.Fc;
import com.zw.cloud.mybatis.plus.service.api.IFcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 体彩 前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/fc")
public class FcController {

    @Autowired
    private IFcService fcService;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/insert")
    //http://localhost:8080/fc/insert
    public void insertFcList() {
        List<Fc> fcList = main(null);
        fcService.saveBatch(fcList,fcList.size());
    }

    @RequestMapping("queryFcList")
    //http://localhost:8080/fc/queryFcList
    public void queryFcList() {
        LambdaQueryWrapper<Fc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.lt(Fc::getId,22088);
        queryWrapper.orderByDesc(Fc::getId).last("limit 5");
        List<Fc> fcList = fcService.list(queryWrapper);
        Set<Integer> codeSet = new HashSet<>(10);
        fcList.forEach(tc -> {
            codeSet.add(tc.getOne());
            codeSet.add(tc.getTwo());
            codeSet.add(tc.getThree());
            codeSet.add(tc.getFour());
            codeSet.add(tc.getFive());
            codeSet.add(tc.getSix());
        });
        Set<Integer> blueSet = fcList.stream().map(Fc::getSeven).collect(Collectors.toSet());
        System.out.println(JSON.toJSONString(codeSet.stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList())));
        System.out.println(JSON.toJSONString(blueSet.stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList())));
    }

    public static List<Fc> main(String[] args) {
        List<Fc> fcList = Lists.newArrayList("22086\t01\t04\t08\t21\t23\t24\t11",
                "22085\t07\t09\t14\t31\t32\t33\t13",
                "22084\t03\t18\t23\t24\t25\t32\t09",
                "22083\t08\t12\t13\t14\t19\t20\t05",
                "22082\t04\t10\t11\t23\t30\t32\t14",
                "22081\t04\t08\t11\t21\t27\t30\t01",
                "22080\t05\t12\t15\t17\t18\t27\t04",
                "22079\t01\t09\t15\t17\t22\t23\t16",
                "22078\t01\t04\t05\t15\t17\t31\t09",
                "22077\t03\t17\t18\t19\t20\t27\t16",
                "22076\t08\t09\t10\t13\t24\t29\t02")
                .stream().map(str -> {
                    String[] split = str.split("\t");
                    Fc.FcBuilder builder = Fc.builder();
                    builder.id(Integer.parseInt(split[0])).one(Integer.parseInt(split[1]))
                            .two(Integer.parseInt(split[2])).three(Integer.parseInt(split[3]))
                            .four(Integer.parseInt(split[4])).five(Integer.parseInt(split[5]))
                            .six(Integer.parseInt(split[6])).seven(Integer.parseInt(split[7]));
                    return builder.build();
                }).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(fcList));
        return fcList;
    }
}
