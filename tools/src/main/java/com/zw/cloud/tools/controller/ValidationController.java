package com.zw.cloud.tools.controller;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.service.impl.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    @Autowired
    private ValidationServiceImpl validationService;

    @PostMapping
    //http://localhost:9040/validation
    public void test(@RequestBody @Valid User user){
        System.out.println(JSON.toJSONString(user));
    }

    @PutMapping
    //http://localhost:9040/validation
    public void test2(@RequestBody User user){
        validationService.test(user);
    }

}
