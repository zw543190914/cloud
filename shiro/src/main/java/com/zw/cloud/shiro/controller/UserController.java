package com.zw.cloud.shiro.controller;

import com.zw.cloud.shiro.entity.User;
import com.zw.cloud.shiro.service.api.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/shiro/user")
public class UserController {

    @Autowired
    private IUserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/insert")
    public User insert(@RequestBody User user){
        return userService.insert(user);
    }

    @PutMapping
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @GetMapping
    @RequiresRoles(value = {"admin"})
    //http://localhost:9030/shiro/user
    public Map<String, Object> queryPermission(@RequestParam String username){
        return userService.queryPermission(username);
    }

    @GetMapping("/queryUserByUserName")
    //http://localhost:9030/shiro/user/queryUserByUserName?username=zw
    @RequiresPermissions(value = {"insert"})
    public User queryUserByUserName(@RequestParam String username) {
        return userService.queryUserByUserName(username);
    }

    @PostMapping("/login")
    //http://localhost:9030/shiro/user/login
    public String loginForRedis(@RequestBody User user) {
        if (StringUtils.isBlank(user.getUserName())){
            throw new RuntimeException("用户名不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            subject.login(token);
            Session session = subject.getSession();
            logger.info("[login success] sessionId is {}",session.getId());
            return "success";
        } catch (Exception e) {
            throw e;
        }
    }

    //退出登录
    @GetMapping("/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
    }
}
