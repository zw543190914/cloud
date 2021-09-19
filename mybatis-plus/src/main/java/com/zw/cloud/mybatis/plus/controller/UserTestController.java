package com.zw.cloud.mybatis.plus.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.service.api.IUserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

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

    @GetMapping("/query")
    //http://localhost:8080/user-test/query
    public Page<UserInfo> pageQuery() {
        UserInfo user = new UserInfo();
       /* QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name")
                .eq(Objects.nonNull(user.getId()),"id",user.getId())
                .eq(Objects.nonNull(user.getName()),"name",user.getName())
                .orderByDesc("id");*/
                //.last("limit 1");
        LambdaQueryWrapper<UserInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Objects.nonNull(user.getId()), UserInfo::getId, user.getId())
                .eq(Objects.nonNull(user.getName()), UserInfo::getName, user.getName())
                .orderByDesc(UserInfo::getId);
        Page<UserInfo> page = new Page<>(1,10);
        return userService.page(page, queryWrapper);
    }
}
