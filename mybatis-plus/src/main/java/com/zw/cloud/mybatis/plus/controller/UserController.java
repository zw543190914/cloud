package com.zw.cloud.mybatis.plus.controller;


import com.zw.cloud.mybatis.plus.entity.User;
import com.zw.cloud.mybatis.plus.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-03-26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/test")
    //http://localhost:8080/user/test
    public List<User> queryUserRoleIdList() {
        return userService.queryUserRoleIdList();
    }
}

