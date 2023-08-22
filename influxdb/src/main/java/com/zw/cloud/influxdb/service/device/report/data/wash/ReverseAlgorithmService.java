package com.zw.cloud.influxdb.service.device.report.data.wash;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.common.utils.BigDecimalUtils;
import com.zw.cloud.influxdb.entity.CommonTenterCraftDTO;
import com.zw.cloud.influxdb.entity.IotInfoDo;
import com.zw.cloud.influxdb.enums.ReverseAlgorithmIotPointEnum;
import com.zw.cloud.influxdb.utils.IotInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.zw.cloud.influxdb.enums.ReverseAlgorithmIotPointEnum.*;


@Service
@Slf4j
public class ReverseAlgorithmService {

    @Autowired
    private DeviceReportDataWashQueryService deviceReportDataWashQueryService;

    /**
     * 结束时 逆向算法生成
     */
    public void reverseAlgorithmWhenFinish(List<String> iotCodeList, LocalDateTime startTime, LocalDateTime endTime) {
        if (CollectionUtils.isEmpty(iotCodeList)) {
            log.info("[ReverseAlgorithmServiceImpl][reverseAlgorithmWhenFinish]iotCodeList is empty,end");
            return;
        }

        // influxDB 数据查询
        List<IotInfoDo> iotInfoDoList = queryIotInfoDoByTime(iotCodeList, startTime, endTime);
        log.info("[ReverseAlgorithmServiceImpl][reverseAlgorithmWhenFinish]iotInfoDoList is {}", JSON.toJSONString(iotInfoDoList));
        if (CollectionUtils.isEmpty(iotInfoDoList)) {
            return;
        }

        CommonTenterCraftDTO tenterCraftDTO = buildCommonTenterCraftDTO(iotInfoDoList);
        log.info("[ReverseAlgorithmServiceImpl][reverseAlgorithmWhenFinish] tenterCraftDTO {}", JSON.toJSONString(tenterCraftDTO));

    }

    /**
     * 生产时间 = 中车结束时间 - 中车开始时间
     * A 如果生产时间 >= 6分钟 ，总时间段取值为 开始时间 至 结束时间前3分钟
     *    否则 取 开始时间 至 结束时间 作为总时间段
     * B 根据 总时间段 范围 查询influxDB 数据。
     * C 总时间段是否 >= 3 分钟
     *     如果  总时间段 不足 3 分钟，并且无停车，取该时间段数据作为基础数据；如果有停车，数据无效，结束。
     *     如果  总时间段 >= 3 分钟,并且无停车，取中间1/3 数据作为基础数据；如果有停车，则取值时间为 最后一次停车后1分钟时间 到 中车结束时间，取中间1/3数据作为基础数据
     */
    private List<IotInfoDo> queryIotInfoDoByTime(List<String> iotCodeList, LocalDateTime startTime, LocalDateTime endTime) {
        // 如果生产时间 >= 6分钟 ，总时间段取值为 开始时间 至 结束时间前3分钟
        //   否则 取 开始时间 至 结束时间 作为总时间段
        Duration duration = Duration.between(startTime, endTime);
        long totalMinutes = duration.toMinutes();
        if (totalMinutes >= 6) {
            endTime = endTime.minusMinutes(3);
        }
        // 根据 总时间段 范围 查询influxDB 数据。
        List<IotInfoDo> iotInfoDoList = deviceReportDataWashQueryService.queryDeviceReportDataWashByTime(iotCodeList, startTime, endTime);
        if (CollectionUtils.isEmpty(iotInfoDoList)) {
            return Collections.emptyList();
        }
        // 如果  总时间段 不足 3 分钟，并且无停车，取该时间段数据作为基础数据；如果有停车，数据无效，结束。
        Duration validDuration = Duration.between(startTime, endTime);
        if (validDuration.toMinutes() < 3) {
            Optional<IotInfoDo> stopData = iotInfoDoList.stream().filter(iotInfoDto -> Objects.isNull(iotInfoDto.getSpeedSetting()) || iotInfoDto.getSpeedSetting().compareTo(BigDecimal.ZERO) == 0).findFirst();
            if (stopData.isPresent()) {
                log.info("[ReverseAlgorithmServiceImpl][reverseAlgorithmWhenFinish] 总时间段 不足3分钟,有停车，数据无效，结束");
                return Collections.emptyList();
            }
            log.info("[ReverseAlgorithmServiceImpl][reverseAlgorithmWhenFinish] 总时间段 不足3分钟,无停车");
            return iotInfoDoList;
        }
        // 如果  总时间段 >= 3 分钟,并且无停车，取中间1/3 数据作为基础数据；如果有停车，则取值时间为 最后一次停车后1分钟时间 到 中车结束时间，取中间1/3数据作为基础数据
        log.info("[ReverseAlgorithmServiceImpl][reverseAlgorithmWhenFinish] 总时间段 >= 3 分钟");
        Optional<IotInfoDo> stopIotInfoDtoOptional = iotInfoDoList.stream().filter(iotInfoDto -> Objects.isNull(iotInfoDto.getSpeedSetting()) || iotInfoDto.getSpeedSetting().compareTo(BigDecimal.ZERO) == 0).max(Comparator.comparing(IotInfoDo::getTime));
        // 出现停车
        if (stopIotInfoDtoOptional.isPresent()) {
            // 则取值时间为 最后一次停车后1分钟时间段，取1/3
            IotInfoDo stopIotInfoDto = stopIotInfoDtoOptional.get();
            Instant startInstant = stopIotInfoDto.getTime().plusSeconds(60);
            Duration durationTime = Duration.between(startInstant, endTime.atZone(ZoneId.systemDefault()).toInstant());
            // 取 中间 1/3 时间数据
            long startPoint = durationTime.toMillis() / 3;//开始位置
            long endPoint = durationTime.toMillis() / 3 * 2;//结束位置
            long startEpochMilli = startInstant.toEpochMilli();
            long startMilli = startEpochMilli + startPoint;
            long endMilli = startEpochMilli + endPoint;
            log.info("[ReverseAlgorithmServiceImpl][reverseAlgorithmWhenFinish] 总时间段 >= 3 分钟,出现停车");
            return iotInfoDoList.stream().filter(iotInfoDto -> iotInfoDto.getTime().toEpochMilli() >= startMilli && iotInfoDto.getTime().toEpochMilli() <= endMilli).collect(Collectors.toList());
        }

        // 生产时间>=3分钟, 没有停车 ,则取值时间为生产时间中间1/3时间段
        long s = duration.toMillis() / 3;//开始位置
        long e = duration.toMillis() / 3 * 2;//结束位置
        long datum = startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long start = datum + s;
        long end = datum + e;
        return iotInfoDoList.stream().filter(iotInfoDto -> {
            long epochMilli = iotInfoDto.getTime().toEpochMilli();
            return epochMilli >= start && epochMilli <= end;
        }).collect(Collectors.toList());
    }

    /**
     * 根据 influxDB 数据，计算工艺
     */
    private CommonTenterCraftDTO buildCommonTenterCraftDTO(List<IotInfoDo> iotInfoDoList) {
        CommonTenterCraftDTO tenterCraft = new CommonTenterCraftDTO();
        List<String> fieldNameList = ReverseAlgorithmIotPointEnum.buildFieldNameList();
        LinkedHashMap<String, List<BigDecimal>> iotMap = IotInfoUtils.buildValueByEachSection(iotInfoDoList, fieldNameList);

        // 车速 连续出现2次(含)以上数值中的最大值，如有效时间段内无连续值，则取最大值；
        List<BigDecimal> speedList = iotMap.get(ReverseAlgorithmIotPointEnum.SPEED.getFieldName());
        tenterCraft.setSpeed(BigDecimalUtils.consecutiveDigits(speedList));

        // 温度 精确到整数
        // 取相同数量最多的值，如有多个，取最后值；
        LinkedHashMap<String, BigDecimal> troughPresetTempMap = new LinkedHashMap<>();
        for (int i = 1; i <= TROUGH_PRESET_TEMP.getMaxCount(); i++) {
            List<BigDecimal> troughPresetTempList = iotMap.get(TROUGH_PRESET_TEMP.getFieldName() + i);
            troughPresetTempMap.put("temperature" + i,BigDecimalUtils.filterMaxSameAndLastValueFromList(troughPresetTempList,0));
        }
        tenterCraft.setTemperatureJson(BigDecimalUtils.subMap(troughPresetTempMap,"temperature",14));
        // 总值：去掉前2个点位和最后2个点位，取剩余点位最大值
        tenterCraft.setTemperature(BigDecimalUtils.queryMaxAfterFilterFirstSecondAndLastValue(troughPresetTempMap));

        // 张力 精确到整数
        LinkedHashMap<String, BigDecimal> tensionMap = new LinkedHashMap<>();
        for (int i = 1; i <= TENSION.getMaxCount(); i++) {
            List<BigDecimal> troughPresetTempList = iotMap.get(TENSION.getFieldName() + i);
            tensionMap.put(TENSION.getFieldName() + i,BigDecimalUtils.filterMaxSameAndLastValueFromList(troughPresetTempList,0));
        }
        tenterCraft.setTensionJson(BigDecimalUtils.subMap(tensionMap,TENSION.getFieldName(),12));

        // 进布张力
        BigDecimal feedCenteringTensionPreset = BigDecimalUtils.filterMaxSameAndLastValueFromList(iotMap.get(FEED_CENTERING_TENSIONPRESET.getFieldName()),0);
        // 如无进布张力点位采集数据值，则取第1个张力点位「设定值」；
        if (Objects.isNull(feedCenteringTensionPreset)) {
            feedCenteringTensionPreset = tensionMap.get("tension1");
        }
        tenterCraft.setFeedTension(feedCenteringTensionPreset);

        // 出布张力
        BigDecimal tensionOutlePositionPreset = BigDecimalUtils.filterMaxSameAndLastValueFromList(iotMap.get(TENSION_OUTLE_POSITION_PRESET.getFieldName()),0);
        if (Objects.isNull(tensionOutlePositionPreset)) {
            tensionOutlePositionPreset = tensionMap.get("tension16");
        }
        tenterCraft.setFabricTension(tensionOutlePositionPreset);

        // 张力总值 取 出布张力 计算结果；
        tenterCraft.setTension(Objects.isNull(tensionOutlePositionPreset) ? null : tensionOutlePositionPreset.intValue());

        // PH 保留2位小数
        LinkedHashMap<String, BigDecimal> phMap = new LinkedHashMap<>();
        for (int i = 1; i <= PH.getMaxCount(); i++) {
            List<BigDecimal> phList = iotMap.get(PH.getFieldName() + i);
            phMap.put(PH.getFieldName() + i,BigDecimalUtils.filterMaxSameAndLastValueFromList(phList,2));
        }
        tenterCraft.setPhJson(BigDecimalUtils.subMap(phMap,PH.getFieldName(),2));
        // 总：取最后1个PH点位的计算结果；
        tenterCraft.setPh(phMap.get("ph2"));

        // 轧车压力 保留1位小数
        LinkedHashMap<String, BigDecimal> rollingPressureMap = new LinkedHashMap<>();
        for (int i = 1; i <= ROLLING_PRESSURE.getMaxCount(); i++) {
            List<BigDecimal> rollingPressureList = iotMap.get(ROLLING_PRESSURE.getFieldName() + i);
            rollingPressureMap.put(ROLLING_PRESSURE.getFieldName() + i,BigDecimalUtils.filterMaxSameAndLastValueFromList(rollingPressureList,1));
        }
        tenterCraft.setCarTensionJson(BigDecimalUtils.subMap(rollingPressureMap,ROLLING_PRESSURE.getFieldName(),4));

        // 进布轧车压力 --不用算
        //tenterCraft.setFeedRollingPressure();
        // 出布轧车压力
        //总、出布轧车压力：取最后1个轧车压力点位上报的「设定值」；
        BigDecimal rollingPressure = rollingPressureMap.get("rollingPressure4");
        Integer rollingPressureInt = Objects.isNull(rollingPressure) ? null : rollingPressure.intValue();
        tenterCraft.setRollingPressure(rollingPressureInt);
        // 轧车压力 总值
        tenterCraft.setCarTension(rollingPressure);

        // 真空吸水/真空频率 保留一位小数
        LinkedHashMap<String, BigDecimal> waterUptakeMap = new LinkedHashMap<>();
        for (int i = 1; i <= WATER_UPTAKE.getMaxCount(); i++) {
            List<BigDecimal> waterUptakeList = iotMap.get(WATER_UPTAKE.getFieldName() + i);
            waterUptakeMap.put(WATER_UPTAKE.getFieldName() + i,BigDecimalUtils.filterMaxSameAndLastValueFromList(waterUptakeList,1));
        }
        tenterCraft.setVacuumFrequencyJson(BigDecimalUtils.subMap(waterUptakeMap,WATER_UPTAKE.getFieldName(),3));
        tenterCraft.setVacuumFrequency(waterUptakeMap.get("waterUptake4"));

        // 助剂配比 保留一位小数
        LinkedHashMap<String, BigDecimal> assistantPresetMap = new LinkedHashMap<>();
        for (int i = 1; i <= ASSISTANT_PRESET.getMaxCount(); i++) {
            List<BigDecimal> assistantPresetList = iotMap.get(ASSISTANT_PRESET.getFieldName() + i);
            assistantPresetMap.put(ASSISTANT_PRESET.getFieldName() + i,BigDecimalUtils.filterMaxSameAndLastValueFromList(assistantPresetList,1));
        }
        tenterCraft.setAssistantRate(assistantPresetMap.get("assistantPreset4"));
        tenterCraft.setAssistantRateJson(BigDecimalUtils.subMap(assistantPresetMap,ASSISTANT_PRESET.getFieldName(),2));

        // 补水量 保留一位小数
        LinkedHashMap<String, BigDecimal> inflowRateMap = new LinkedHashMap<>();
        for (int i = 1; i <= INFLOW_RATE.getMaxCount(); i++) {
            List<BigDecimal> inflowRateList = iotMap.get(INFLOW_RATE.getFieldName() + i);
            inflowRateMap.put(INFLOW_RATE.getFieldName() + i,BigDecimalUtils.filterMaxSameAndLastValueFromList(inflowRateList,1));
        }
        tenterCraft.setSupplyWaterQuantity(assistantPresetMap.get("inflowRate4"));
        tenterCraft.setSupplyWaterQuantityJson(BigDecimalUtils.subMap(inflowRateMap,INFLOW_RATE.getFieldName(),1));

        // 含水量 保留一位小数
        LinkedHashMap<String, BigDecimal> waterRatioMap = new LinkedHashMap<>();
        for (int i = 1; i <= WATER_RATIO.getMaxCount(); i++) {
            List<BigDecimal> waterRatioList = iotMap.get(WATER_RATIO.getFieldName() + i);
            waterRatioMap.put(WATER_RATIO.getFieldName() + i,BigDecimalUtils.filterMaxSameAndLastValueFromList(waterRatioList,1));
        }
        tenterCraft.setWaterContent(assistantPresetMap.get("waterRatio4"));
        tenterCraft.setWaterContentJson(BigDecimalUtils.subMap(waterRatioMap,WATER_RATIO.getFieldName(),0));

        tenterCraft.setCreateTime(LocalDateTime.now());
        tenterCraft.setUpdateTime(LocalDateTime.now());
        tenterCraft.setCreateSystem(null);
        tenterCraft.setUpdateSystem(null);

        return tenterCraft;
    }
}
