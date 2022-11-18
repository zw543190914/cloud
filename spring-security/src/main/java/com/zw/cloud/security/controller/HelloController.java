package com.zw.cloud.security.controller;

import com.zw.cloud.security.service.api.sys.ISysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class HelloController {
    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('PRODUCT','ADMIN')")
    //@Secured({"ROLE_PRODUCT","ROLE_ADMIN"})
    //http://localhost:9050/hello
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello2")
    //http://localhost:9050/hello2
    @PreAuthorize("hasRole('ORDER')")
    //@Secured("ROLE_ORDER")
    public String hello2() {
        return "hello2";
    }

    @GetMapping("/hello3")
    //http://localhost:9050/hello2
    @PreAuthorize("hasAuthority('ORDER')")
    public String hello3() {
        return "hello3";
    }

    @RequestMapping("/index")
    @PreAuthorize(value="isAuthenticated()")
    public String index() {
        return "index";
    }
}
