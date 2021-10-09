package com.zw.cloud.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Slf4j
@Validated
public class ValidationServiceImpl {

    public void test(@Valid User user){
        log.info("[ValidationServiceImpl][test] user is {}", JSON.toJSONString(user));
    }
}
