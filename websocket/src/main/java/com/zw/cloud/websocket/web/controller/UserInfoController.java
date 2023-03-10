package com.zw.cloud.websocket.web.controller;


import com.zw.cloud.websocket.web.entity.chat.UserInfo;
import com.zw.cloud.websocket.web.entity.chat.UserVo;
import com.zw.cloud.websocket.web.service.api.chat.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String registerOrLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession) {
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(password);
        try {
            UserVo userVo = userServices.registerOrLogin(user);
            httpSession.setAttribute("uid", userVo.getId());
            return "chatroom";
        } catch (Exception e) {
            return "fail";
        }

    }

    @RequestMapping("/login")
    //http://localhost:18092/chat/user/login
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
        return "login";
    }

}

