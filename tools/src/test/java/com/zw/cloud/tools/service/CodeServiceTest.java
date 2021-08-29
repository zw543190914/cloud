package com.zw.cloud.tools.service;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.base.BaseTest;
import com.zw.cloud.tools.entity.Code;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


class CodeServiceTest extends BaseTest {

    @MockBean
    private CodeService codeService;


    @Test
    void queryAllByLimit() {
        List<Code> codes = codeService.queryAllByLimit(1, 20);
        System.out.println(JSON.toJSONString(codes));
    }

    @Test
    void insert() {
        Code code = new Code();
        code.setGmtCreate(new Date());
        code.setStatus((byte)2);
        //Mockito.when(codeService.insert(any(Code.class))).thenReturn(code);
        codeService.insert(code);

    }
}