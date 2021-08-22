package com.zw.cloud.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping
public class SecurityController {

    private static Logger logger = getLogger(SecurityController.class);

    @GetMapping("/doLogin")
    //http://localhost:9050/doLogin
    public String doLogin(){
        return "success";
    }

    @RequestMapping("/mylogin.html")
    public String myLogin() {
        return "mylogin";
    }
}
