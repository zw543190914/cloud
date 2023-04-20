package com.zw.cloud.mybatis.plus.service;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.impl.UserInfoServiceImpl;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

public class UserInfoServiceImplTest {
    @InjectMocks
    UserInfoServiceImpl userInfoService;
    @Mock
    private UserInfoMapper baseMapper;

    @BeforeEach
    void before() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testBatchInsertByMybatisPlus(){
        try (MockedStatic<SqlHelper> sqlHelperMockedStatic = mockStatic(SqlHelper.class)) {
            sqlHelperMockedStatic.when(() -> SqlHelper.executeBatch(any(),any(),anyCollection(),anyInt(),any())).thenReturn(true);
            Assertions.assertDoesNotThrow(() -> userInfoService.testBatchInsertByMybatisPlus(Lists.newArrayList()));
        }

    }
}
