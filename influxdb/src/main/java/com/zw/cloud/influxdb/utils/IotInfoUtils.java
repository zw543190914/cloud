package com.zw.cloud.influxdb.utils;

import com.google.common.collect.Lists;
import com.zw.cloud.influxdb.entity.IotInfoDo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class IotInfoUtils {

    /**
     * 按 每节进行数据填充
     */
    public static LinkedHashMap<String, List<BigDecimal>> buildValueByEachSection(List<IotInfoDo> allIotInfoDtoList, List<String> filedNameList){
        LinkedHashMap<String,List<BigDecimal>> dryingRoomActualTempMap = new LinkedHashMap<>();
        Class<IotInfoDo> iotInfoDtoClass = IotInfoDo.class;
        for (IotInfoDo iotInfoDto : allIotInfoDtoList) {
            for (String fieldName : filedNameList) {
                try {
                    Field declaredField = iotInfoDtoClass.getDeclaredField(fieldName);
                    declaredField.setAccessible(true);
                    Object value = declaredField.get(iotInfoDto);
                    if (Objects.nonNull(value)) {
                        BigDecimal actualTempValue = (BigDecimal) value;
                        List<BigDecimal> valueList = Optional.ofNullable(dryingRoomActualTempMap.get(fieldName)).orElse(Lists.newArrayList());
                        valueList.add(actualTempValue);
                        dryingRoomActualTempMap.put(fieldName,valueList);
                    }
                } catch (Exception e) {
                    log.error("[IotInfoUtils][buildValueByEachSection] error is ",e);
                }
            }
        }
        return dryingRoomActualTempMap;
    }
}
