package com.zw.cloud.mybatis.plus.enums;

import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.common.utils.StringTransforUtils;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum DataReportEnum {
    SPEED("e01", "车速", "speed", "speedSetting"),
    TROUGHACTUALTEMP1("e05", "1#水槽温度实际温度", "troughActualTemp1", "troughPresetTemp1"),
    TROUGHACTUALTEMP2("e07", "2#水槽温度实际温度", "troughActualTemp2", "troughPresetTemp2"),
    TROUGHACTUALTEMP3("e09", "3#水槽温度实际温度", "troughActualTemp3", "troughPresetTemp3"),
    TROUGHACTUALTEMP4("e11", "4#水槽温度实际温度", "troughActualTemp4", "troughPresetTemp4"),
    TROUGHACTUALTEMP5("e13", "5#水槽温度实际温度", "troughActualTemp5", "troughPresetTemp5"),
    TROUGHACTUALTEMP6("e15", "6#水槽温度实际温度", "troughActualTemp6", "troughPresetTemp6"),
    TROUGHACTUALTEMP7("e17", "7#水槽温度实际温度", "troughActualTemp7", "troughPresetTemp7"),
    TROUGHACTUALTEMP8("e19", "8#水槽温度实际温度", "troughActualTemp8", "troughPresetTemp8"),
    TROUGHACTUALTEMP9("e21", "9#水槽温度实际温度", "troughActualTemp9", "troughPresetTemp9"),
    TROUGHACTUALTEMP10("e23", "10#水槽温度实际温度", "troughActualTemp10", "troughPresetTemp10"),
    TROUGHACTUALTEMP11("e25", "11#水槽温度实际温度", "troughActualTemp11", "troughPresetTemp11"),
    TROUGHACTUALTEMP12("e27", "12#水槽温度实际温度", "troughActualTemp12", "troughPresetTemp12"),
    FEEDCENTERINGTENSIONACTUAL("e32", "进布张力", "feedCenteringTensionActual", "feedCenteringTensionPreset"),
    TENSIONOUTLEPOSITIONACTUAL("e54", "出布张力", "tensionOutlePositionActual", "tensionOutlePositionPreset");


    private String alertType;
    private String alertTypeName;
    private String alertTypeDesc;
    private String alertTypeSettingName;

    DataReportEnum(String alertType, String alertTypeName, String alertTypeDesc, String alertTypeSettingName) {
        this.alertType = alertType;
        this.alertTypeName = alertTypeName;
        this.alertTypeDesc = alertTypeDesc;
        this.alertTypeSettingName = alertTypeSettingName;
    }

    public static DataReportEnum get(String alertTypeDesc) {
        DataReportEnum[] dataReportEnums = values();
        for (DataReportEnum dataReportEnum : dataReportEnums) {
            if (Objects.equals(dataReportEnum.getAlertTypeDesc(), alertTypeDesc)) {
                return dataReportEnum;
            }
        }
        throw new BizException(MessageFormat.format("枚举类型{0}不存在", alertTypeDesc));
    }

    public static void main(String[] args) {
        for (DataReportEnum dataReportEnum : DataReportEnum.values()) {
            String actual = StringTransforUtils.lowerCamelToUnderscore(dataReportEnum.alertTypeDesc);
            String setting = StringTransforUtils.lowerCamelToUnderscore(dataReportEnum.alertTypeSettingName);
            System.out.println("`" + actual + "` decimal(8,4) DEFAULT NULL COMMENT '" + dataReportEnum.alertTypeName + "',");
            System.out.println("`" + setting + "` decimal(8,4) DEFAULT NULL COMMENT '" + dataReportEnum.alertTypeName.replace("实际","设定") + "',");
        }

    }
}
