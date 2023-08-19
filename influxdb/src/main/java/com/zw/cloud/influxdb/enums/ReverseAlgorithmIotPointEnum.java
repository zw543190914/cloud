package com.zw.cloud.influxdb.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆向算法，iot点位
 */
@Getter
public enum ReverseAlgorithmIotPointEnum {
    SPEED("speedSetting","车速"),
    TROUGH_PRESET_TEMP("troughPresetTemp","温度"),
    TENSION("tension","张力"),
    FEED_CENTERING_TENSIONPRESET("feedCenteringTensionPreset","进布张力"),
    TENSION_OUTLE_POSITION_PRESET("tensionOutlePositionPreset","出布张力"),
    PH("ph","PH"),
    ROLLING_PRESSURE("rollingPressure","轧车压力"),
    WATER_UPTAKE("waterUptake","真空吸水/真空频率"),
    ASSISTANT_PRESET("assistantPreset","助剂配比"),
    INFLOW_RATE("inflowRate","补水量"),
    WATER_RATIO("waterRatio","含水量"),

    ;
    private String fieldName;
    private String remark;

    ReverseAlgorithmIotPointEnum(String fieldName, String remark) {
        this.fieldName = fieldName;
        this.remark = remark;
    }

    /**
     * 点位字段名称
     */
    public static List<String> buildFieldNameList() {
        ArrayList<String> fieldNameList = new ArrayList<>();
        fieldNameList.add(SPEED.fieldName);
        for (int i = 1; i <= 16; i++) {
            fieldNameList.add(TROUGH_PRESET_TEMP.fieldName + i);
        }
        for (int i = 1; i <= 16; i++) {
            fieldNameList.add(TENSION.fieldName + i);
        }
        fieldNameList.add(FEED_CENTERING_TENSIONPRESET.fieldName);
        fieldNameList.add(TENSION_OUTLE_POSITION_PRESET.fieldName);
        for (int i = 1; i <= 2; i++) {
            fieldNameList.add(PH.fieldName + i);
        }
        for (int i = 1; i <= 4; i++) {
            fieldNameList.add(ROLLING_PRESSURE.fieldName + i);
        }
        for (int i = 1; i <= 4; i++) {
            fieldNameList.add(WATER_UPTAKE.fieldName + i);
        }
        for (int i = 1; i <= 4; i++) {
            fieldNameList.add(ASSISTANT_PRESET.fieldName + i);
        }
        for (int i = 1; i <= 4; i++) {
            fieldNameList.add(INFLOW_RATE.fieldName + i);
        }
        for (int i = 1; i <= 4; i++) {
            fieldNameList.add(WATER_RATIO.fieldName + i);
        }

        return fieldNameList;
    }

}
