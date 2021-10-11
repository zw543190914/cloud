package com.zw.cloud.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Slf4j
@Validated
public class ValidationServiceImpl {

    public void testObject(@Valid User user){
        log.info("[ValidationServiceImpl][testObject] user is {}", JSON.toJSONString(user));
    }

    public void testParam(@NotNull(message = "id is null") Long id){
        log.info("[ValidationServiceImpl][testParam] id is {}", id);
    }
}
