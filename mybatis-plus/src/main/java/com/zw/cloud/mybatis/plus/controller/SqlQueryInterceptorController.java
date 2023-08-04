package com.zw.cloud.mybatis.plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.zw.cloud.common.thread.local.InheritableThreadLocalUtil;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.service.api.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sql-interceptor")
public class SqlQueryInterceptorController {

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("/queryByOrgCode/{orgCode}")
    //http://localhost:8082/sql-interceptor/queryByOrgCode/zw
    public List<UserInfo> queryByOrgCode(@PathVariable String orgCode) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getOrgCode,orgCode).eq(UserInfo::getName,"2253").in(UserInfo::getAge, Lists.newArrayList(23,24));
        return userInfoService.list(queryWrapper);
    }

    @GetMapping("/queryByOrgCodeForMapper/{orgCode}")
    //http://localhost:8082/sql-interceptor/queryByOrgCodeForMapper/zw
    public List<UserInfo> queryByOrgCodeForMapper(@PathVariable String orgCode) {
        return userInfoService.queryByOrgCode(orgCode);
    }
}
