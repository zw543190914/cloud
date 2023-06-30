package com.zw.cloud.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

public class UserInfoServiceImplTest {
    @InjectMocks
    UserInfoServiceImpl userInfoService;
    @Mock
    private UserInfoMapper baseMapper;
    @Mock
    private ThreadPoolTaskExecutor ioThreadPoolTaskExecutor;
    @Mock
    private PlatformTransactionManager transactionManager;

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

       /* try (MockedStatic<MybatisPlusUtil> mybatisPlusUtil = mockStatic(MybatisPlusUtil.class)) {
            mybatisPlusUtil.when(() -> MybatisPlusUtil.toPageInfo(any(), (Class<GeneralProductRecord>) any())).thenReturn(pageInfo);
            mybatisPlusUtil.when(() -> MybatisPlusUtil.wrapperFrom(any(), any())).thenReturn(new QueryWrapper<GeneralProductRecord>());
            Mockito.when(mapper.selectPage(any(), any())).thenReturn(null);
            Mockito.when(mapper.selectList(any())).thenReturn(list);
            generalProductRecordServiceForApp.pageQuery(new GeneralProductHistoryQueryDTO());
            Assertions.assertDoesNotThrow(() -> generalProductRecordServiceForApp.pageQuery(new GeneralProductHistoryQueryDTO()));
        }*/
    }

    @Test
    void asynUpdate(){
        Mockito.doAnswer((InvocationOnMock invocation) -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(ioThreadPoolTaskExecutor).execute(Mockito.any(Runnable.class));
        userInfoService.asynUpdate(2L);
    }

    @Test
    void asynUpdateTask() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<UserInfoServiceImpl> userInfoServiceClass = UserInfoServiceImpl.class;
        Method asynUpdateTask = userInfoServiceClass.getDeclaredMethod("asynUpdateTask", Long.class);
        asynUpdateTask.setAccessible(true);
        Mockito.when(baseMapper.updateById(any())).thenReturn(1);
        Assertions.assertDoesNotThrow(() -> asynUpdateTask.invoke(userInfoService,2L));
    }
}
