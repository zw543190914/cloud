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

    @GetMapping("/queryDeviceReportDataWashByTime/{iotCode}/{minusMinutes}")
    //http://localhost:10010/device-report-data-wash/queryDeviceReportDataWashByTime/1211-zw/15
    public List<IotInfoDo> queryDeviceReportDataWashByTime(@PathVariable String iotCode,@PathVariable Integer minusMinutes) {
        LocalDateTime endTime = LocalDateTime.now();
        return deviceReportDataWashQueryService.queryDeviceReportDataWashByTime(Collections.singletonList(iotCode), endTime.minusMinutes(minusMinutes), endTime);
    }

    /**
     * 插入
     */
    @GetMapping("/insert/{iotCode}/{tem}")
    //http://localhost:10010/device-report-data-wash/insert/1211-zw/33
    public void insert(@PathVariable String iotCode,@PathVariable Integer tem){
        IotInfoDo iotInfoDo = buildIotInfoDo(BigDecimal.valueOf(tem),BigDecimal.valueOf(33),
                BigDecimal.valueOf(44),BigDecimal.valueOf(55),BigDecimal.valueOf(66),
                BigDecimal.valueOf(77),BigDecimal.valueOf(88),iotCode);
        Point point = Point.measurementByPOJO(iotInfoDo.getClass()).addFieldsFromPOJO(iotInfoDo).build();
        deviceReportDataInfluxdb.write(point);
    }

    /**
     * 批量插入
     */
    @GetMapping("/batchInsert/{iotCode}")
    //http://localhost:10010/device-report-data-wash/batchInsert/1211-zw
    public void batchInsert(@PathVariable String iotCode){
        IotInfoDo iotInfoDo = buildIotInfoDo(BigDecimal.valueOf(22),BigDecimal.valueOf(33),
                BigDecimal.valueOf(44),BigDecimal.valueOf(55),BigDecimal.valueOf(66),
                BigDecimal.valueOf(77),BigDecimal.valueOf(88),iotCode);
        BatchPoints.Builder builder = BatchPoints.builder();
        Lists.newArrayList(iotInfoDo).forEach(m -> builder.point(Point.measurementByPOJO(m.getClass()).addFieldsFromPOJO(m).build()));
        //写入
        deviceReportDataInfluxdb.write(builder.build());
    }

    public IotInfoDo buildIotInfoDo(BigDecimal troughPresetTemp,BigDecimal tension,
                                    BigDecimal inflowRate,BigDecimal waterUptake,
                                    BigDecimal assistantPreset,BigDecimal rollingPressure,
                                    BigDecimal waterRatio,String iotCode) {
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
        iotInfoDo.setTroughPresetTemp1(troughPresetTemp);
        iotInfoDo.setTroughActualTemp1(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp2(troughPresetTemp);
        iotInfoDo.setTroughActualTemp2(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp3(troughPresetTemp);
        iotInfoDo.setTroughActualTemp3(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp4(troughPresetTemp);
        iotInfoDo.setTroughActualTemp4(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp5(troughPresetTemp);
        iotInfoDo.setTroughActualTemp5(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp6(troughPresetTemp);
        iotInfoDo.setTroughActualTemp6(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp7(troughPresetTemp);
        iotInfoDo.setTroughActualTemp7(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp8(troughPresetTemp);
        iotInfoDo.setTroughActualTemp8(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp9(troughPresetTemp);
        iotInfoDo.setTroughActualTemp9(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp10(troughPresetTemp);
        iotInfoDo.setTroughActualTemp10(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp11(troughPresetTemp);
        iotInfoDo.setTroughActualTemp11(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp12(troughPresetTemp);
        iotInfoDo.setTroughActualTemp12(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp13(troughPresetTemp);
        iotInfoDo.setTroughActualTemp13(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp14(troughPresetTemp);
        iotInfoDo.setTroughActualTemp14(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp15(troughPresetTemp);
        iotInfoDo.setTroughActualTemp15(troughPresetTemp);
        iotInfoDo.setTroughPresetTemp16(troughPresetTemp);
        iotInfoDo.setTroughActualTemp16(troughPresetTemp);
        iotInfoDo.setSteamerPresetTemp(new BigDecimal("0"));
        iotInfoDo.setSteamerActualTemp(new BigDecimal("0"));
        iotInfoDo.setFeedCenteringTensionPreset(new BigDecimal("0"));
        iotInfoDo.setFeedCenteringTensionActual(new BigDecimal("0"));
        iotInfoDo.setTension1(tension);
        iotInfoDo.setTensionAct1(tension);
        iotInfoDo.setTension2(tension);
        iotInfoDo.setTensionAct2(tension);
        iotInfoDo.setTension3(tension);
        iotInfoDo.setTensionAct3(tension);
        iotInfoDo.setTension4(tension);
        iotInfoDo.setTensionAct4(tension);
        iotInfoDo.setTension5(tension);
        iotInfoDo.setTensionAct5(tension);
        iotInfoDo.setTension6(tension);
        iotInfoDo.setTensionAct6(tension);
        iotInfoDo.setTension7(tension);
        iotInfoDo.setTensionAct7(tension);
        iotInfoDo.setTension8(tension);
        iotInfoDo.setTensionAct8(tension);
        iotInfoDo.setTension9(tension);
        iotInfoDo.setTensionAct9(tension);
        iotInfoDo.setTension10(tension);
        iotInfoDo.setTensionAct10(tension);
        iotInfoDo.setTension11(tension);
        iotInfoDo.setTensionAct11(tension);
        iotInfoDo.setTension12(tension);
        iotInfoDo.setTensionAct12(tension);
        iotInfoDo.setTension13(tension);
        iotInfoDo.setTensionAct13(tension);
        iotInfoDo.setTension14(tension);
        iotInfoDo.setTensionAct14(tension);
        iotInfoDo.setTension15(tension);
        iotInfoDo.setTensionAct15(tension);
        iotInfoDo.setTension16(tension);
        iotInfoDo.setTensionAct16(tension);
        iotInfoDo.setTensionOutlePositionPreset(new BigDecimal("0"));
        iotInfoDo.setTensionOutlePositionActual(new BigDecimal("0"));
        iotInfoDo.setInflowRate1(inflowRate);
        iotInfoDo.setInflowRateAct1(inflowRate);
        iotInfoDo.setInflowRate2(inflowRate);
        iotInfoDo.setInflowRateAct2(inflowRate);
        iotInfoDo.setInflowRate3(inflowRate);
        iotInfoDo.setInflowRateAct3(inflowRate);
        iotInfoDo.setInflowRate4(inflowRate);
        iotInfoDo.setInflowRateAct4(inflowRate);
        iotInfoDo.setWaterUptake1(waterUptake);
        iotInfoDo.setWaterUptakeAct1(waterUptake);
        iotInfoDo.setWaterUptake2(waterUptake);
        iotInfoDo.setWaterUptakeAct2(waterUptake);
        iotInfoDo.setWaterUptake3(waterUptake);
        iotInfoDo.setWaterUptakeAct3(waterUptake);
        iotInfoDo.setWaterUptake4(waterUptake);
        iotInfoDo.setWaterUptakeAct4(waterUptake);
        iotInfoDo.setAssistantPreset1(assistantPreset);
        iotInfoDo.setAssistantAct1(assistantPreset);
        iotInfoDo.setAssistantPreset2(assistantPreset);
        iotInfoDo.setAssistantAct2(assistantPreset);
        iotInfoDo.setAssistantPreset3(assistantPreset);
        iotInfoDo.setAssistantAct3(assistantPreset);
        iotInfoDo.setAssistantPreset4(assistantPreset);
        iotInfoDo.setAssistantAct4(assistantPreset);
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
        iotInfoDo.setRollingPressure1(rollingPressure);
        iotInfoDo.setRollingPressureAct1(rollingPressure);
        iotInfoDo.setRollingPressure2(rollingPressure);
        iotInfoDo.setRollingPressureAct2(rollingPressure);
        iotInfoDo.setRollingPressure3(rollingPressure);
        iotInfoDo.setRollingPressureAct3(rollingPressure);
        iotInfoDo.setRollingPressure4(rollingPressure);
        iotInfoDo.setRollingPressureAct4(rollingPressure);
        iotInfoDo.setOutRollingPressure(new BigDecimal("0"));
        iotInfoDo.setOutRollingPressureAct(new BigDecimal("0"));
        iotInfoDo.setPh1(new BigDecimal("0"));
        iotInfoDo.setPhAct1(new BigDecimal("0"));
        iotInfoDo.setPh2(new BigDecimal("0"));
        iotInfoDo.setPhAct2(new BigDecimal("0"));
        iotInfoDo.setWaterRatio1(waterRatio);
        iotInfoDo.setWaterRatioAct1(waterRatio);
        iotInfoDo.setWaterRatio2(waterRatio);
        iotInfoDo.setWaterRatioAct2(waterRatio);
        iotInfoDo.setWaterRatio3(waterRatio);
        iotInfoDo.setWaterRatioAct3(waterRatio);
        iotInfoDo.setWaterRatio4(waterRatio);
        iotInfoDo.setWaterRatioAct4(waterRatio);
        iotInfoDo.setGramWeight(new BigDecimal("0"));
        iotInfoDo.setDevice(iotCode);
        iotInfoDo.setDeviceType("wash");
        return iotInfoDo;
    }

}
