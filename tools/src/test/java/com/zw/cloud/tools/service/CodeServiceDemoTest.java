package com.zw.cloud.tools.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zw.cloud.tools.dao.CodeDao;
import com.zw.cloud.tools.entity.Code;
import com.zw.cloud.tools.service.impl.CodeServiceImpl;
import com.zw.cloud.tools.utils.DateTimeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

class CodeServiceDemoTest {

    //将生成MockDao，并注入到@InjectMocks指定的类中
    @Mock
    CodeDao codeDao;

    @InjectMocks
    CodeServiceImpl codeService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void queryAllByLimit() {
        Mockito.when(codeDao.queryAllByLimit(anyInt(), anyInt())).thenReturn(Lists.newArrayList());
        List<Code> codes = codeService.queryAllByLimit(1, 20);
        System.out.println(JSON.toJSONString(codes));
    }

    @Test
    public void insert() {
        Code code = new Code();
        code.setGmtCreate(new Date());
        code.setStatus((byte)2);
        Mockito.doNothing().when(codeDao).insert(any());
        codeService.insert(code);
    }

    @Test
    public void plusMonths() {
        try (MockedStatic<DateTimeUtils> dateTimeUtilsMockedStatic = mockStatic(DateTimeUtils.class)) {
            dateTimeUtilsMockedStatic.when(()-> DateTimeUtils.plusMonths(anyInt())).thenReturn(new Date());
            System.out.println(DateTimeUtils.plusMonths(1));
        }
    }
}