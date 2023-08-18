package com.zw.cloud.influxdb.controller;
import java.math.BigDecimal;
import java.time.Instant;

import com.google.common.collect.Lists;
import com.zw.cloud.influxdb.entity.IotInfoDo;
import com.zw.cloud.influxdb.service.device.report.data.wash.DeviceReportDataWashQueryService;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/device-report-data-wash")
public class DeviceReportDataWashController {
    @Autowired
    @Qualifier("deviceReportDataInfluxdb")
    private InfluxDB deviceReportDataInfluxdb;

    @Autowired
    private DeviceReportDataWashQueryService deviceReportDataWashQueryService;

    @GetMapping("/queryDeviceReportDataWashByTime/{iotCode}")
    //http://localhost:10010/device-report-data-wash/queryDeviceReportDataWashByTime/1211-zw
    public List<IotInfoDo> queryDeviceReportDataWashByTime(@PathVariable String iotCode) {
        LocalDateTime endTime = LocalDateTime.now();
        return deviceReportDataWashQueryService.queryDeviceReportDataWashByTime(Collections.singletonList(iotCode), endTime.minusMinutes(5), endTime);
    }

    /**
     * 插入
     */
    @GetMapping("/insert/{iotCode}")
    //http://localhost:10010/device-report-data-wash/insert/1211-zw
    public void insert(@PathVariable String iotCode){
        IotInfoDo iotInfoDo = buildIotInfoDo(iotCode);
        Point point = Point.measurementByPOJO(iotInfoDo.getClass()).addFieldsFromPOJO(iotInfoDo).build();
        deviceReportDataInfluxdb.write(point);
    }

    /**
     * 批量插入
     */
    @GetMapping("/batchInsert/{iotCode}")
    //http://localhost:10010/influxdb/batchInsert/1211-zw
    public void batchInsert(@PathVariable String iotCode){
        IotInfoDo iotInfoDo = buildIotInfoDo(iotCode);
        BatchPoints.Builder builder = BatchPoints.builder();
        Lists.newArrayList(iotInfoDo).forEach(m -> builder.point(Point.measurementByPOJO(m.getClass()).addFieldsFromPOJO(m).build()));
        //写入
        deviceReportDataInfluxdb.write(builder.build());
    }

    public IotInfoDo buildIotInfoDo(String iotCode) {
        IotInfoDo iotInfoDo = new IotInfoDo();
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        Instant instant = Instant.ofEpochSecond(second);
        iotInfoDo.setTime(instant);
        iotInfoDo.setRtime(0L);
        iotInfoDo.setCtime(0L);
        iotInfoDo.setMessageType(0);
        iotInfoDo.setEventCode(0);
        iotInfoDo.setSpeedSetting(new BigDecimal("0"));
        iotInfoDo.setSpeed(new BigDecimal("0"));
        iotInfoDo.setOutput(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp1(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp1(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp2(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp2(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp3(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp3(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp4(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp4(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp5(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp5(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp6(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp6(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp7(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp7(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp8(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp8(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp9(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp9(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp10(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp10(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp11(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp11(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp12(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp12(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp13(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp13(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp14(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp14(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp15(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp15(new BigDecimal("0"));
        iotInfoDo.setTroughPresetTemp16(new BigDecimal("0"));
        iotInfoDo.setTroughActualTemp16(new BigDecimal("0"));
        iotInfoDo.setSteamerPresetTemp(new BigDecimal("0"));
        iotInfoDo.setSteamerActualTemp(new BigDecimal("0"));
        iotInfoDo.setFeedCenteringTensionPreset(new BigDecimal("0"));
        iotInfoDo.setFeedCenteringTensionActual(new BigDecimal("0"));
        iotInfoDo.setTension1(new BigDecimal("0"));
        iotInfoDo.setTensionAct1(new BigDecimal("0"));
        iotInfoDo.setTension2(new BigDecimal("0"));
        iotInfoDo.setTensionAct2(new BigDecimal("0"));
        iotInfoDo.setTension3(new BigDecimal("0"));
        iotInfoDo.setTensionAct3(new BigDecimal("0"));
        iotInfoDo.setTension4(new BigDecimal("0"));
        iotInfoDo.setTensionAct4(new BigDecimal("0"));
        iotInfoDo.setTension5(new BigDecimal("0"));
        iotInfoDo.setTensionAct5(new BigDecimal("0"));
        iotInfoDo.setTension6(new BigDecimal("0"));
        iotInfoDo.setTensionAct6(new BigDecimal("0"));
        iotInfoDo.setTension7(new BigDecimal("0"));
        iotInfoDo.setTensionAct7(new BigDecimal("0"));
        iotInfoDo.setTension8(new BigDecimal("0"));
        iotInfoDo.setTensionAct8(new BigDecimal("0"));
        iotInfoDo.setTension9(new BigDecimal("0"));
        iotInfoDo.setTensionAct9(new BigDecimal("0"));
        iotInfoDo.setTension10(new BigDecimal("0"));
        iotInfoDo.setTensionAct10(new BigDecimal("0"));
        iotInfoDo.setTension11(new BigDecimal("0"));
        iotInfoDo.setTensionAct11(new BigDecimal("0"));
        iotInfoDo.setTension12(new BigDecimal("0"));
        iotInfoDo.setTensionAct12(new BigDecimal("0"));
        iotInfoDo.setTension13(new BigDecimal("0"));
        iotInfoDo.setTensionAct13(new BigDecimal("0"));
        iotInfoDo.setTension14(new BigDecimal("0"));
        iotInfoDo.setTensionAct14(new BigDecimal("0"));
        iotInfoDo.setTension15(new BigDecimal("0"));
        iotInfoDo.setTensionAct15(new BigDecimal("0"));
        iotInfoDo.setTension16(new BigDecimal("0"));
        iotInfoDo.setTensionAct16(new BigDecimal("0"));
        iotInfoDo.setTensionOutlePositionPreset(new BigDecimal("0"));
        iotInfoDo.setTensionOutlePositionActual(new BigDecimal("0"));
        iotInfoDo.setInflowRate1(new BigDecimal("0"));
        iotInfoDo.setInflowRateAct1(new BigDecimal("0"));
        iotInfoDo.setInflowRate2(new BigDecimal("0"));
        iotInfoDo.setInflowRateAct2(new BigDecimal("0"));
        iotInfoDo.setInflowRate3(new BigDecimal("0"));
        iotInfoDo.setInflowRateAct3(new BigDecimal("0"));
        iotInfoDo.setInflowRate4(new BigDecimal("0"));
        iotInfoDo.setInflowRateAct4(new BigDecimal("0"));
        iotInfoDo.setWaterUptake1(new BigDecimal("0"));
        iotInfoDo.setWaterUptakeAct1(new BigDecimal("0"));
        iotInfoDo.setWaterUptake2(new BigDecimal("0"));
        iotInfoDo.setWaterUptakeAct2(new BigDecimal("0"));
        iotInfoDo.setWaterUptake3(new BigDecimal("0"));
        iotInfoDo.setWaterUptakeAct3(new BigDecimal("0"));
        iotInfoDo.setWaterUptake4(new BigDecimal("0"));
        iotInfoDo.setWaterUptakeAct4(new BigDecimal("0"));
        iotInfoDo.setAssistantPreset1(new BigDecimal("0"));
        iotInfoDo.setAssistantAct1(new BigDecimal("0"));
        iotInfoDo.setAssistantPreset2(new BigDecimal("0"));
        iotInfoDo.setAssistantAct2(new BigDecimal("0"));
        iotInfoDo.setAssistantPreset3(new BigDecimal("0"));
        iotInfoDo.setAssistantAct3(new BigDecimal("0"));
        iotInfoDo.setAssistantPreset4(new BigDecimal("0"));
        iotInfoDo.setAssistantAct4(new BigDecimal("0"));
        iotInfoDo.setBatchConsumption1(new BigDecimal("0"));
        iotInfoDo.setBatchConsumption2(new BigDecimal("0"));
        iotInfoDo.setBatchConsumption3(new BigDecimal("0"));
        iotInfoDo.setBatchConsumption4(new BigDecimal("0"));
        iotInfoDo.setTotalConsumption1(new BigDecimal("0"));
        iotInfoDo.setTotalConsumption2(new BigDecimal("0"));
        iotInfoDo.setTotalConsumption3(new BigDecimal("0"));
        iotInfoDo.setTotalConsumption4(new BigDecimal("0"));
        iotInfoDo.setFeedRollingPressure(new BigDecimal("0"));
        iotInfoDo.setFeedRollingPressureAct(new BigDecimal("0"));
        iotInfoDo.setRollingPressure1(new BigDecimal("0"));
        iotInfoDo.setRollingPressureAct1(new BigDecimal("0"));
        iotInfoDo.setRollingPressure2(new BigDecimal("0"));
        iotInfoDo.setRollingPressureAct2(new BigDecimal("0"));
        iotInfoDo.setRollingPressure3(new BigDecimal("0"));
        iotInfoDo.setRollingPressureAct3(new BigDecimal("0"));
        iotInfoDo.setRollingPressure4(new BigDecimal("0"));
        iotInfoDo.setRollingPressureAct4(new BigDecimal("0"));
        iotInfoDo.setOutRollingPressure(new BigDecimal("0"));
        iotInfoDo.setOutRollingPressureAct(new BigDecimal("0"));
        iotInfoDo.setPh1(new BigDecimal("0"));
        iotInfoDo.setPhAct1(new BigDecimal("0"));
        iotInfoDo.setPh2(new BigDecimal("0"));
        iotInfoDo.setPhAct2(new BigDecimal("0"));
        iotInfoDo.setWaterRatio1(new BigDecimal("0"));
        iotInfoDo.setWaterRatioAct1(new BigDecimal("0"));
        iotInfoDo.setWaterRatio2(new BigDecimal("0"));
        iotInfoDo.setWaterRatioAct2(new BigDecimal("0"));
        iotInfoDo.setWaterRatio3(new BigDecimal("0"));
        iotInfoDo.setWaterRatioAct3(new BigDecimal("0"));
        iotInfoDo.setWaterRatio4(new BigDecimal("0"));
        iotInfoDo.setWaterRatioAct4(new BigDecimal("0"));
        iotInfoDo.setGramWeight(new BigDecimal("0"));
        iotInfoDo.setDevice(iotCode);
        iotInfoDo.setDeviceType("wash");
        return iotInfoDo;
    }

}
