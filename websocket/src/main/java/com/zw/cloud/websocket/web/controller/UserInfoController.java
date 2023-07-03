package com.zw.cloud.websocket.web.controller;


import com.zw.cloud.websocket.web.entity.chat.UserInfo;
import com.zw.cloud.websocket.web.entity.chat.UserVo;
import com.zw.cloud.websocket.web.service.api.chat.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
@Controller
@RequestMapping("/chat/user")
@Slf4j
public class UserInfoController {

    @Autowired
    IUserInfoService userServices;

    /**
     * 用户登录与注册
     */
    @PostMapping("/registerOrLogin")
    //http://localhost:18092/chat/user/registerOrLogin
    public ModelAndView registerOrLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(password);
        ModelAndView modelAndView = new ModelAndView();
        try {
            UserVo userVo = userServices.registerOrLogin(user);
            modelAndView.setViewName("/chatroom.html");
            modelAndView.addObject("uid",userVo.getId());
            return modelAndView;
        } catch (Exception e) {
            modelAndView.setViewName("/fail.html");
            return modelAndView;
        }

    }

    @RequestMapping("/login")
    //http://localhost:18092/chat/user/login
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "login";
    }

}

