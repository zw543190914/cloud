package com.zw.cloud.mybatis.plus.service.impl.report;

import com.zw.cloud.common.utils.DateTimeUtils;
import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportCountQueryDTO;
import com.zw.cloud.mybatis.plus.mapper.ReportProductCountMapper;
import com.zw.cloud.mybatis.plus.utils.MockEntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.mockito.ArgumentMatchers.any;

public class ReportProductCountServiceImplTest {
    @InjectMocks
    ReportProductCountServiceImpl reportProductCountService;
    @Mock
    ReportProductCountMapper baseMapper;

    @BeforeEach
    void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void queryProductReportCount() {
        Mockito.when(baseMapper.selectList(any())).thenReturn(MockEntityUtils.mockReportProductCountList());
        ProductReportCountQueryDTO productReportCountQueryDTO = new ProductReportCountQueryDTO();
        productReportCountQueryDTO.setOrgCode("devController");
        productReportCountQueryDTO.setStartTime(DateTimeUtils.parse2date("2023-06-01"));
        productReportCountQueryDTO.setEndTime(DateTimeUtils.parse2date("2023-06-26"));
        productReportCountQueryDTO.setWorkshopId(1L);
        productReportCountQueryDTO.setTimeType(1);
        productReportCountQueryDTO.setCaleType(1);
        Assertions.assertDoesNotThrow(() -> reportProductCountService.queryProductReportCount(productReportCountQueryDTO));

        productReportCountQueryDTO.setTimeType(2);
        Assertions.assertDoesNotThrow(() -> reportProductCountService.queryProductReportCount(productReportCountQueryDTO));

        productReportCountQueryDTO.setTimeType(3);
        Assertions.assertDoesNotThrow(() -> reportProductCountService.queryProductReportCount(productReportCountQueryDTO));
    }

    @Test
    void exportProductReportCount() {
        Mockito.when(baseMapper.selectList(any())).thenReturn(MockEntityUtils.mockReportProductCountList());
        ProductReportCountQueryDTO productReportCountQueryDTO = new ProductReportCountQueryDTO();
        productReportCountQueryDTO.setOrgCode("devController");
        productReportCountQueryDTO.setStartTime(DateTimeUtils.parse2date("2023-06-01"));
        productReportCountQueryDTO.setEndTime(DateTimeUtils.parse2date("2023-06-26"));
        productReportCountQueryDTO.setWorkshopId(1L);
        productReportCountQueryDTO.setTimeType(1);
        productReportCountQueryDTO.setCaleType(1);
        productReportCountQueryDTO.setTimeType(3);
        Assertions.assertDoesNotThrow(() -> reportProductCountService.exportProductReportCount(productReportCountQueryDTO));
    }
}
