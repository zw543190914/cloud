package com.zw.cloud.influxdb.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆向算法，iot点位
 */
@Getter
public enum ReverseAlgorithmIotPointEnum {
    SPEED("speedSetting","车速",0),
    TROUGH_PRESET_TEMP("troughPresetTemp","温度",16),
    TENSION("tension","张力",16),
    FEED_CENTERING_TENSIONPRESET("feedCenteringTensionPreset","进布张力",0),
    TENSION_OUTLE_POSITION_PRESET("tensionOutlePositionPreset","出布张力",0),
    PH("ph","PH",2),
    ROLLING_PRESSURE("rollingPressure","轧车压力",4),
    WATER_UPTAKE("waterUptake","真空吸水/真空频率",4),
    ASSISTANT_PRESET("assistantPreset","助剂配比",4),
    INFLOW_RATE("inflowRate","补水量",4),
    WATER_RATIO("waterRatio","含水量",4),

    ;
    private String fieldName;
    private String remark;
    private Integer maxCount;

    ReverseAlgorithmIotPointEnum(String fieldName, String remark, Integer maxCount) {
        this.fieldName = fieldName;
        this.remark = remark;
        this.maxCount = maxCount;
    }

    /**
     * 点位字段名称
     */
    public static List<String> buildFieldNameList() {
        ArrayList<String> fieldNameList = new ArrayList<>();
        fieldNameList.add(SPEED.fieldName);
        for (int i = 1; i <= TROUGH_PRESET_TEMP.maxCount; i++) {
            fieldNameList.add(TROUGH_PRESET_TEMP.fieldName + i);
        }
        for (int i = 1; i <= TENSION.maxCount; i++) {
            fieldNameList.add(TENSION.fieldName + i);
        }
        fieldNameList.add(FEED_CENTERING_TENSIONPRESET.fieldName);
        fieldNameList.add(TENSION_OUTLE_POSITION_PRESET.fieldName);
        for (int i = 1; i <= PH.maxCount; i++) {
            fieldNameList.add(PH.fieldName + i);
        }
        for (int i = 1; i <= ROLLING_PRESSURE.maxCount; i++) {
            fieldNameList.add(ROLLING_PRESSURE.fieldName + i);
        }
        for (int i = 1; i <= WATER_UPTAKE.maxCount; i++) {
            fieldNameList.add(WATER_UPTAKE.fieldName + i);
        }
        for (int i = 1; i <= ASSISTANT_PRESET.maxCount; i++) {
            fieldNameList.add(ASSISTANT_PRESET.fieldName + i);
        }
        for (int i = 1; i <= INFLOW_RATE.maxCount; i++) {
            fieldNameList.add(INFLOW_RATE.fieldName + i);
        }
        for (int i = 1; i <= WATER_RATIO.maxCount; i++) {
            fieldNameList.add(WATER_RATIO.fieldName + i);
        }

        return fieldNameList;
    }

}
