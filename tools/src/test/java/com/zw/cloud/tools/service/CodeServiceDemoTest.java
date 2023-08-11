package com.zw.cloud.tools.service;

import com.google.common.collect.Lists;
import com.zw.cloud.common.utils.DateTimeUtils;
import com.zw.cloud.tools.dao.CodeDao;
import com.zw.cloud.tools.entity.Code;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Date;

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
     void before() {

        MockitoAnnotations.openMocks(this);
        // 解决mybatisplus orderby LambdaQueryWrapper
        //TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ProductRecord.class);

    }


    @Test
     void queryAllByLimit() {
        Mockito.when(codeDao.queryAllByLimit(anyInt(), anyInt())).thenReturn(Lists.newArrayList());
        Assertions.assertDoesNotThrow(() -> codeService.queryAllByLimit(1, 20));
    }

    @Test
     void insert() {
        Code code = new Code();
        code.setGmtCreate(new Date());
        code.setStatus((byte)2);
        Mockito.doNothing().when(codeDao).insert(any());
        Assertions.assertDoesNotThrow(() -> codeService.insert(code));
    }

    @Test
     void plusMonths() {
        try (MockedStatic<DateTimeUtils> dateTimeUtilsMockedStatic = mockStatic(DateTimeUtils.class)) {
            dateTimeUtilsMockedStatic.when(()-> DateTimeUtils.plusMonths(anyInt())).thenReturn(new Date());
            Assertions.assertDoesNotThrow(() -> DateTimeUtils.plusMonths(1));
        }
    }
}