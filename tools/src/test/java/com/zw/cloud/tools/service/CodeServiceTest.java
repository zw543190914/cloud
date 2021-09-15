package com.zw.cloud.tools.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zw.cloud.tools.dao.CodeDao;
import com.zw.cloud.tools.entity.Code;
import com.zw.cloud.tools.service.impl.CodeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class CodeServiceTest {

    //将生成MockDao，并注入到@InjectMocks指定的类中
    @Mock
    CodeDao codeDao;

    @InjectMocks
    CodeServiceImpl codeService;

    @Test
    void queryAllByLimit() {
        Mockito.when(codeDao.queryAllByLimit(anyInt(), anyInt())).thenReturn(Lists.newArrayList());
        List<Code> codes = codeService.queryAllByLimit(1, 20);
        System.out.println(JSON.toJSONString(codes));
    }

    @Test
    void insert() {
        Code code = new Code();
        code.setGmtCreate(new Date());
        code.setStatus((byte)2);
        Mockito.doNothing().when(codeDao).insert(any());
        codeService.insert(code);
    }
}