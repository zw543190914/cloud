package com.zw.cloud.tools.service.impl;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.tools.annotation.Ide;
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

    @Ide(perFix = "tool_test",paramIndex = 0,objectFieldName = "name")
    public void testObject(@Valid User user){
        log.info("[ValidationServiceImpl][testObject] user is {}", JSON.toJSONString(user));
    }

    @Ide(perFix = "tool_test",paramIndex = 0)
    public void testParam(@NotNull(message = "id is null") Long id){
        log.info("[ValidationServiceImpl][testParam] id is {}", id);
    }
}
