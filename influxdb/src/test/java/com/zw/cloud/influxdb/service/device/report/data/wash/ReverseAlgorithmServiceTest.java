package com.zw.cloud.influxdb.service.device.report.data.wash;

import com.google.common.collect.Lists;
import com.zw.cloud.influxdb.entity.IotInfoDo;
import com.zw.cloud.influxdb.service.utils.MockEntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

public class ReverseAlgorithmServiceTest {

    @InjectMocks
    private ReverseAlgorithmService reverseAlgorithmService;
    @Mock
    private DeviceReportDataWashQueryService deviceReportDataWashQueryService;

    @BeforeEach
    void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void reverseAlgorithmWhenFinish() {
        LocalDateTime endTime = LocalDateTime.now();

        Assertions.assertDoesNotThrow(() -> reverseAlgorithmService.reverseAlgorithmWhenFinish(
                Lists.newArrayList("test"),endTime.minusMinutes(8),endTime));

        List<IotInfoDo> iotInfoDos = MockEntityUtils.buildIotInfoDoList();
        Mockito.when(deviceReportDataWashQueryService.queryDeviceReportDataWashByTime(anyList(),any(),any())).thenReturn(iotInfoDos);
        //总时间段 不足 3 分钟，并且有停车
        Assertions.assertDoesNotThrow(() -> reverseAlgorithmService.reverseAlgorithmWhenFinish(
                Lists.newArrayList("test"),endTime.minusMinutes(2),endTime));

        // 总时间段 >= 3 分钟,并且有停车
        Assertions.assertDoesNotThrow(() -> reverseAlgorithmService.reverseAlgorithmWhenFinish(
                Lists.newArrayList("test"),endTime.minusMinutes(8),endTime));

        iotInfoDos.forEach(iotInfoDo -> {
            iotInfoDo.setSpeedSetting(BigDecimal.TEN);
            iotInfoDo.setTensionOutlePositionPreset(null);
            iotInfoDo.setFeedCenteringTensionPreset(null);
        });

        //总时间段 不足 3 分钟，无停车
        Assertions.assertDoesNotThrow(() -> reverseAlgorithmService.reverseAlgorithmWhenFinish(
                Lists.newArrayList("test"),endTime.minusMinutes(2),endTime));

        // 总时间段 >= 3 分钟,无有停车
        Assertions.assertDoesNotThrow(() -> reverseAlgorithmService.reverseAlgorithmWhenFinish(
                Lists.newArrayList("test"),endTime.minusMinutes(8),endTime));

    }

}
