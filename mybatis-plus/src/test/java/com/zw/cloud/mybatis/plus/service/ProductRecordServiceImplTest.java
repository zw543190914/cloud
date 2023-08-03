package com.zw.cloud.mybatis.plus.service;

import com.google.common.collect.Lists;
import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordReportQueryDTO;
import com.zw.cloud.mybatis.plus.mapper.ProductRecordMapper;
import com.zw.cloud.mybatis.plus.service.impl.ProductRecordServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ProductRecordServiceImpl.class})
public class ProductRecordServiceImplTest {
    @InjectMocks
    ProductRecordServiceImpl productRecordService;
    @Mock
    ProductRecordMapper baseMapper;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void mockSkipPrivate() throws Exception {

        ProductRecordServiceImpl productRecordServiceMock = PowerMockito.mock(ProductRecordServiceImpl.class);
        Method fillSourceAndIsShowExceptionsParam = PowerMockito.method(ProductRecordServiceImpl.class, "fillSourceAndIsShowExceptionsParam", ProductRecordReportQueryDTO.class);
        //PowerMockito.doReturn(0).when(productRecordServiceMock, fillSourceAndIsShowExceptionsParam).withArguments(any());
        PowerMockito.doNothing().when(productRecordServiceMock, fillSourceAndIsShowExceptionsParam).withArguments(any());
        PowerMockito.when(productRecordServiceMock.queryAllFinishedProductForReport(any())).thenCallRealMethod();

        ProductRecordReportQueryDTO productRecordReportQueryDTO = new ProductRecordReportQueryDTO();
        productRecordReportQueryDTO.setStartTime(LocalDateTime.now());
        productRecordReportQueryDTO.setEndTime(LocalDateTime.now());

        Mockito.when(baseMapper.queryAllFinishedProductForReport(any())).thenReturn(Lists.newArrayList());


        productRecordServiceMock.queryAllFinishedProductForReport(productRecordReportQueryDTO);
    }


}
