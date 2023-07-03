package com.zw.cloud.rocketmq.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class IotInfoDto implements Serializable {
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  数据上报时间
     */
    private Long rtime;
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  采集时间
     */
    private LocalDateTime ctime;
    /**
     * 消息类型
     * 1：事件类、2： 状态类
     */
    private Integer messageType;
    /**
     * 【事件类】事件编码
     * 参考 字典 【事件类】事件编码 字典
     */
    private Integer eventCode;

    /**
     * 工艺速度
     */
    private BigDecimal speedSetting;
    /**
     * 实际车速
     */
    private BigDecimal speed;
    /**
     * 生产产量
     */
    private BigDecimal output;
    /**
     * 1#水槽温度设定值
     */
    private BigDecimal troughPresetTemp1;
    /**
     * 1#水槽温度实际温度
     */
    private BigDecimal troughActualTemp1;
    /**
     * 2#水槽温度设定值
     */
    private BigDecimal troughPresetTemp2;
    /**
     * 2#水槽温度实际温度
     */
    private BigDecimal troughActualTemp2;
    /**
     * 3#水槽温度设定值
     */
    private BigDecimal troughPresetTemp3;
    /**
     * 3#水槽温度实际温度
     */
    private BigDecimal troughActualTemp3;
    /**
     * 4#水槽温度设定值
     */
    private BigDecimal troughPresetTemp4;
    /**
     * 4#水槽温度实际温度
     */
    private BigDecimal troughActualTemp4;
    /**
     * 5#水槽温度设定值
     */
    private BigDecimal troughPresetTemp5;
    /**
     * 5#水槽温度实际温度
     */
    private BigDecimal troughActualTemp5;
    /**
     * 6#水槽温度设定值
     */
    private BigDecimal troughPresetTemp6;
    /**
     * 6#水槽温度实际温度
     */
    private BigDecimal troughActualTemp6;
    /**
     * 7#水槽温度设定值
     */
    private BigDecimal troughPresetTemp7;
    /**
     * 7#水槽温度实际温度
     */
    private BigDecimal troughActualTemp7;
    /**
     * 8#水槽温度设定值
     */
    private BigDecimal troughPresetTemp8;
    /**
     * 8#水槽温度实际温度
     */
    private BigDecimal troughActualTemp8;
    /**
     * 9#水槽温度设定值
     */
    private BigDecimal troughPresetTemp9;
    /**
     * 9#水槽温度实际温度
     */
    private BigDecimal troughActualTemp9;
    /**
     * 10#水槽温度设定值
     */
    private BigDecimal troughPresetTemp10;
    /**
     * 10#水槽温度实际温度
     */
    private BigDecimal troughActualTemp10;
    /**
     * 11#水槽温度设定值
     */
    private BigDecimal troughPresetTemp11;
    /**
     * 11#水槽温度实际温度
     */
    private BigDecimal troughActualTemp11;
    /**
     * 12#水槽温度设定值
     */
    private BigDecimal troughPresetTemp12;
    /**
     * 12#水槽温度实际温度
     */
    private BigDecimal troughActualTemp12;
    /**
     * 汽蒸箱内温度设定值
     */
    private BigDecimal steamerPresetTemp;
    /**
     * 汽蒸箱内温度实际温度
     */
    private BigDecimal steamerActualTemp;
    /**
     * 主蒸汽管道实际温度
     */
    private BigDecimal mainPipelineActualTemp;
    /**
     * 进布对中张力设定值
     */
    private BigDecimal feedCenteringTensionPreset;
    /**
     * 进布对中张力实际值
     */
    private BigDecimal feedCenteringTensionActual;
    /**
     * 1/2水洗S辊前张力设定值
     */
    private BigDecimal waterBeforePreset1;
    /**
     * 1/2水洗S辊前张力实际值
     */
    private BigDecimal waterBeforeActual1;
    /**
     * 1/2水洗S辊后张力设定值
     */
    private BigDecimal waterAfterPreset1;
    /**
     * 1/2水洗S辊后张力实际值
     */
    private BigDecimal waterAfterActual1;
    /**
     * 3/4水洗S辊前张力设定值
     */
    private BigDecimal waterBeforePreset2;
    /**
     * 3/4水洗S辊前张力实际值
     */
    private BigDecimal waterBeforeActual2;
    /**
     * 3/4水洗S辊后张力设定值
     */
    private BigDecimal waterAfterPreset2;
    /**
     * 3/4水洗S辊后张力实际值
     */
    private BigDecimal waterAfterActual2;
    /**
     * 5/6水洗S辊前张力设定值
     */
    private BigDecimal waterBeforePreset3;
    /**
     * 5/6水洗S辊前张力实际值
     */
    private BigDecimal waterBeforeActual3;
    /**
     * 5/6水洗S辊后张力设定值
     */
    private BigDecimal waterAfterPreset3;
    /**
     * 5/6水洗S辊后张力实际值
     */
    private BigDecimal waterAfterActual3;
    /**
     * 7/8水洗S辊前张力设定值
     */
    private BigDecimal waterBeforePreset4;
    /**
     * 7/8水洗S辊前张力实际值
     */
    private BigDecimal waterBeforeActual4;
    /**
     * 7/8水洗S辊后张力设定值
     */
    private BigDecimal waterAfterPreset4;
    /**
     * 7/8水洗S辊后张力实际值
     */
    private BigDecimal waterAfterActual4;
    /**
     * 蒸箱出布张力设定值
     */
    private BigDecimal ovenOutletTensionPreset;
    /**
     * 蒸箱出布张力实际值
     */
    private BigDecimal ovenOutletTensionActual;
    /**
     * 9/10真空吸水进布张力设定值
     */
    private BigDecimal feedingTensionPreset;
    /**
     * 9/10真空吸水进布张力实际值
     */
    private BigDecimal feedingTensionActual;
    /**
     * 出布位置张力设定值
     */
    private BigDecimal tensionOutlePositionPreset;
    /**
     * 出布位置张力实际值
     */
    private BigDecimal tensionOutlePositionActual;
    /**
     * 1#加水口瞬时流量
     */
    private BigDecimal inflowRate1;
    /**
     * 2#加水口瞬时流量
     */
    private BigDecimal inflowRate2;
    /**
     * 3#加水口瞬时流量
     */
    private BigDecimal inflowRate3;
    /**
     * 压缩空气压力值
     */
    private BigDecimal airPressure;
    /**
     * 真空吸水压力
     */
    private BigDecimal waterUptake;
    /**
     * 1#助剂设定值
     */
    private BigDecimal assistantPreset1;
    /**
     * 2#助剂设定值
     */
    private BigDecimal assistantPreset2;
    /**
     * 批次耗量1#耗量
     */
    private BigDecimal batchConsumption1;
    /**
     * 批次耗量2#耗量
     */
    private BigDecimal batchConsumption2;
    /**
     * 总耗量1#耗量
     */
    private BigDecimal totalConsumption1;
    /**
     * 总耗量2#耗量
     */
    private BigDecimal totalConsumption2;


    /**
     * 设备id
     * 发送 当期定型机实时运行的工艺参数 时使用
     */
    private Long deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 机构编号
     * 发送 当期定型机实时运行的工艺参数 时使用
     */
    private String orgCode;

}
