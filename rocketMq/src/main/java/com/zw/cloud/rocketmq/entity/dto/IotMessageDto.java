package com.zw.cloud.rocketmq.entity.dto;

import com.zw.cloud.common.utils.bean.annotation.CopyField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class IotMessageDto implements Serializable {
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  数据上报时间
     */
    @CopyField(targetFieldName = "rtime")
    private Long rtime;
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  采集时间
     */
    private Long ctime;
    /**
     * 消息类型
     * 1：事件类、2： 状态类
     */
    @CopyField(targetFieldName = "messageType")
    private Integer mt;
    /**
     * 【事件类】事件编码
     * 参考 字典 【事件类】事件编码 字典
     */
    @CopyField(targetFieldName = "eventCode")
    private Integer ec;
    /**
     * 工艺速度
     */
    @CopyField(targetFieldName = "speedSetting")
    private BigDecimal e01;
    /**
     * 实际车速
     */
    @CopyField(targetFieldName = "speed")
    private BigDecimal e02;
    /**
     * 生产产量
     */
    @CopyField(targetFieldName = "output")
    private BigDecimal e03;
    /**
     * 1#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp1")
    private BigDecimal e04;
    /**
     * 1#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp1")
    private BigDecimal e05;
    /**
     * 2#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp2")
    private BigDecimal e06;
    /**
     * 2#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp2")
    private BigDecimal e07;
    /**
     * 3#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp3")
    private BigDecimal e08;
    /**
     * 3#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp3")
    private BigDecimal e09;
    /**
     * 4#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp4")
    private BigDecimal e10;
    /**
     * 4#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp4")
    private BigDecimal e11;
    /**
     * 5#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp5")
    private BigDecimal e12;
    /**
     * 5#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp5")
    private BigDecimal e13;
    /**
     * 6#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp6")
    private BigDecimal e14;
    /**
     * 6#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp6")
    private BigDecimal e15;
    /**
     * 7#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp7")
    private BigDecimal e16;
    /**
     * 7#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp7")
    private BigDecimal e17;
    /**
     * 8#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp8")
    private BigDecimal e18;
    /**
     * 8#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp8")
    private BigDecimal e19;
    /**
     * 9#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp9")
    private BigDecimal e20;
    /**
     * 9#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp9")
    private BigDecimal e21;
    /**
     * 10#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp10")
    private BigDecimal e22;
    /**
     * 10#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp10")
    private BigDecimal e23;
    /**
     * 11#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp11")
    private BigDecimal e24;
    /**
     * 11#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp11")
    private BigDecimal e25;
    /**
     * 12#水槽温度设定值
     */
    @CopyField(targetFieldName = "troughPresetTemp12")
    private BigDecimal e26;
    /**
     * 12#水槽温度实际温度
     */
    @CopyField(targetFieldName = "troughActualTemp12")
    private BigDecimal e27;
    /**
     * 汽蒸箱内温度设定值
     */
    @CopyField(targetFieldName = "steamerPresetTemp")
    private BigDecimal e28;
    /**
     * 汽蒸箱内温度实际温度
     */
    @CopyField(targetFieldName = "steamerActualTemp")
    private BigDecimal e29;
    /**
     * 主蒸汽管道实际温度
     */
    @CopyField(targetFieldName = "mainPipelineActualTemp")
    private BigDecimal e30;
    /**
     * 进布对中张力设定值
     */
    @CopyField(targetFieldName = "feedCenteringTensionPreset")
    private BigDecimal e31;
    /**
     * 进布对中张力实际值
     */
    @CopyField(targetFieldName = "feedCenteringTensionActual")
    private BigDecimal e32;
    /**
     * 1/2水洗S辊前张力设定值
     */
    @CopyField(targetFieldName = "waterBeforePreset1")
    private BigDecimal e33;
    /**
     * 1/2水洗S辊前张力实际值
     */
    @CopyField(targetFieldName = "waterBeforeActual1")
    private BigDecimal e34;
    /**
     * 1/2水洗S辊后张力设定值
     */
    @CopyField(targetFieldName = "waterAfterPreset1")
    private BigDecimal e35;
    /**
     * 1/2水洗S辊后张力实际值
     */
    @CopyField(targetFieldName = "waterAfterActual1")
    private BigDecimal e36;
    /**
     * 3/4水洗S辊前张力设定值
     */
    @CopyField(targetFieldName = "waterBeforePreset2")
    private BigDecimal e37;
    /**
     * 3/4水洗S辊前张力实际值
     */
    @CopyField(targetFieldName = "waterBeforeActual2")
    private BigDecimal e38;
    /**
     * 3/4水洗S辊后张力设定值
     */
    @CopyField(targetFieldName = "waterAfterPreset2")
    private BigDecimal e39;
    /**
     * 3/4水洗S辊后张力实际值
     */
    @CopyField(targetFieldName = "waterAfterActual2")
    private BigDecimal e40;
    /**
     * 5/6水洗S辊前张力设定值
     */
    @CopyField(targetFieldName = "waterBeforePreset3")
    private BigDecimal e41;
    /**
     * 5/6水洗S辊前张力实际值
     */
    @CopyField(targetFieldName = "waterBeforeActual3")
    private BigDecimal e42;
    /**
     * 5/6水洗S辊后张力设定值
     */
    @CopyField(targetFieldName = "waterAfterPreset3")
    private BigDecimal e43;
    /**
     * 5/6水洗S辊后张力实际值
     */
    @CopyField(targetFieldName = "waterAfterActual3")
    private BigDecimal e44;
    /**
     * 7/8水洗S辊前张力设定值
     */
    @CopyField(targetFieldName = "waterBeforePreset4")
    private BigDecimal e45;
    /**
     * 7/8水洗S辊前张力实际值
     */
    @CopyField(targetFieldName = "waterBeforeActual4")
    private BigDecimal e46;
    /**
     * 7/8水洗S辊后张力设定值
     */
    @CopyField(targetFieldName = "waterAfterPreset4")
    private BigDecimal e47;
    /**
     * 7/8水洗S辊后张力实际值
     */
    @CopyField(targetFieldName = "waterAfterActual4")
    private BigDecimal e48;
    /**
     * 蒸箱出布张力设定值
     */
    @CopyField(targetFieldName = "ovenOutletTensionPreset")
    private BigDecimal e49;
    /**
     * 蒸箱出布张力实际值
     */
    @CopyField(targetFieldName = "ovenOutletTensionActual")
    private BigDecimal e50;
    /**
     * 9/10真空吸水进布张力设定值
     */
    @CopyField(targetFieldName = "feedingTensionPreset")
    private BigDecimal e51;
    /**
     * 9/10真空吸水进布张力实际值
     */
    @CopyField(targetFieldName = "feedingTensionActual")
    private BigDecimal e52;
    /**
     * 出布位置张力设定值
     */
    @CopyField(targetFieldName = "tensionOutlePositionPreset")
    private BigDecimal e53;
    /**
     * 出布位置张力实际值
     */
    @CopyField(targetFieldName = "tensionOutlePositionActual")
    private BigDecimal e54;
    /**
     * 1#加水口瞬时流量
     */
    @CopyField(targetFieldName = "inflowRate1")
    private BigDecimal e55;
    /**
     * 2#加水口瞬时流量
     */
    @CopyField(targetFieldName = "inflowRate2")
    private BigDecimal e56;
    /**
     * 3#加水口瞬时流量
     */
    @CopyField(targetFieldName = "inflowRate3")
    private BigDecimal e57;
    /**
     * 压缩空气压力值
     */
    @CopyField(targetFieldName = "airPressure")
    private BigDecimal e58;
    /**
     * 真空吸水压力
     */
    @CopyField(targetFieldName = "waterUptake")
    private BigDecimal e59;
    /**
     * 1#助剂设定值
     */
    @CopyField(targetFieldName = "assistantPreset1")
    private BigDecimal e60;
    /**
     * 2#助剂设定值
     */
    @CopyField(targetFieldName = "assistantPreset2")
    private BigDecimal e61;
    /**
     * 批次耗量1#耗量
     */
    @CopyField(targetFieldName = "batchConsumption1")
    private BigDecimal e62;
    /**
     * 批次耗量2#耗量
     */
    @CopyField(targetFieldName = "batchConsumption2")
    private BigDecimal e63;
    /**
     * 总耗量1#耗量
     */
    @CopyField(targetFieldName = "totalConsumption1")
    private BigDecimal e64;
    /**
     * 总耗量2#耗量
     */
    @CopyField(targetFieldName = "totalConsumption2")
    private BigDecimal e65;

}
