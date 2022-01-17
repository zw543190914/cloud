package com.zw.cloud.tools.controller;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.bindcheckgroup.InsertCheckGroup;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.service.impl.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    @Autowired
    private ValidationServiceImpl validationService;

    @PostMapping
    //http://localhost:9040/validation
    public void test(@RequestBody @Validated(InsertCheckGroup.class) User user) {
        System.out.println(JSON.toJSONString(user));
    }

    @PutMapping
    //http://localhost:9040/validation
    public void testObject(@RequestBody User user) {
        validationService.testObject(user);
    }

    @GetMapping
    //http://localhost:9040/validation?id=1
    public void testParam(Long id) {
        validationService.testParam(id);
    }

}
