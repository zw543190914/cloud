package com.zw.cloud.mybatis.plus.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/user-test")
public class UserTestController {
    @Autowired
    private IUserTestService userService;

    @GetMapping
    //http://localhost:8080/user-test?name=test2
    public void saveOrUpdate(String name) {
        UserInfo user = new UserInfo();
        user.setName(name);
        userService.saveOrUpdate(user);
        System.out.println(JSON.toJSONString(user));
    }


    @GetMapping("/batchUpdate")
    //http://localhost:8080/user-test/batchUpdate
    public void batchUpdate() {
        UserInfo user = new UserInfo();
        //user.setId(1438686379807698945L);
        user.setName("hh");
        user.setAge(11);
        UserInfo user2 = new UserInfo();
        //user2.setId(1438688954489552898L);
        user2.setName("r挺剂TF-630ff");
        user2.setAge(22);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","name11");
        jsonObject.put("date",LocalDateTime.now());
        user2.setOther(Lists.newArrayList(jsonObject));
        //userService.batchUpdateUserListByMapper(Lists.newArrayList(user,user2));
        //userService.batchUpdateUserList(Lists.newArrayList(user,user2));
        userService.batchSaveOrUpdate(Lists.newArrayList(user,user2));
    }

    @GetMapping("/query")
    //http://localhost:8080/user-test/query?name=fd2
    public Page<UserInfo> pageQuery(String name) {
        UserInfo user = new UserInfo();
        user.setName(name);
       /* QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name")
                .eq(Objects.nonNull(user.getId()),"id",user.getId())
                .eq(Objects.nonNull(user.getName()),"name",user.getName())
                .orderByDesc("id");*/
                //.last("limit 1");
       /* LambdaQueryWrapper<UserInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Objects.nonNull(user.getId()), UserInfo::getId, user.getId())
                .eq(Objects.nonNull(user.getName()), UserInfo::getName, user.getName())
                .orderByDesc(UserInfo::getId);*/
        LambdaQueryWrapper<UserInfo> lambdaQuery = Wrappers.lambdaQuery(user);
        Page<UserInfo> page = new Page<>(1,10);
        return userService.page(page, lambdaQuery);
    }

    @GetMapping("/queryJsonData")
    //http://localhost:8080/user-test/queryJsonData?name=ee挺剂TF-630
    public List<UserInfo> queryJsonData(String name) {
        return userService.queryJsonData(name);
    }

    @GetMapping("/queryJsonDataLike")
    //http://localhost:8080/user-test/queryJsonDataLike?name=挺剂
    public List<UserInfo> queryJsonDataLike(String name) {
        return userService.queryJsonDataLike(name);
    }

    @GetMapping("/queryAllDataTest")
    //http://localhost:8080/user-test/queryAllDataTest
    public List<UserInfo> queryAllDataTest() {
        return userService.queryAllDataTest();
    }
}
