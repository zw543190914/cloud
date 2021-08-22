package com.zw.cloud.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserContrtoller {

    @GetMapping("/userInfo")
    //http://localhost:9050/userInfo
    public void userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        System.out.println(authentication.getAuthorities());

        // ecurityContextHolder 策略 默认：MODE_THREADLOCAL
        // -Dspring.security.strategy=MODE_INHERITABLETHREADLOCAL
        /*new Thread(()->{
            Authentication newAuthentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(newAuthentication.getName());
            System.out.println(newAuthentication.getAuthorities());
        }).start();*/
    }
}
