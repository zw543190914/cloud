package com.zw.cloud.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class HelloController {

    private static Logger logger = getLogger(HelloController.class);

    @GetMapping("/hello")
    //http://localhost:9050/hello
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello2";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
