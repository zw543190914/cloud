package com.zw.cloud.influxdb.entity;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Measurement(name = "device_report_data_wash")
public class IotInfoDo implements Serializable {


    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  采集时间
     */
    @TimeColumn
    @Column(name = "time")
    private Instant time;

    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  数据上报时间
     */
    @Column(name = "rtime")
    private Long rtime;
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  采集时间
     */
    @Column(name = "ctime")
    private Long ctime;
    /**
     * 消息类型
     * 1：事件类、2： 状态类
     */
    @Column(name = "messageType")
    private Integer messageType;
    /**
     * 【事件类】事件编码
     * 参考 字典 【事件类】事件编码 字典
     */
    @Column(name = "eventCode")
    private Integer eventCode;
    /**
     * 车速设定值
     */
    @Column(name = "speedSetting")
    private BigDecimal speedSetting;
    /**
     * 车速实际值
     */
    @Column(name = "speed")
    private BigDecimal speed;
    /**
     * 生产产量
     */
    @Column(name = "output")
    private BigDecimal output;
    /**
     * 1#前设定
     */
    @Column(name = "troughPresetTemp1")
    private BigDecimal troughPresetTemp1;
    /**
     * 1#前实际
     */
    @Column(name = "troughActualTemp1")
    private BigDecimal troughActualTemp1;
    /**
     * 1#后设定
     */
    @Column(name = "troughPresetTemp2")
    private BigDecimal troughPresetTemp2;
    /**
     * 1#后实际
     */
    @Column(name = "troughActualTemp2")
    private BigDecimal troughActualTemp2;
    /**
     * 2#前设定
     */
    @Column(name = "troughPresetTemp3")
    private BigDecimal troughPresetTemp3;
    /**
     * 2#后实际
     */
    @Column(name = "troughActualTemp3")
    private BigDecimal troughActualTemp3;
    /**
     * 2#后设定
     */
    @Column(name = "troughPresetTemp4")
    private BigDecimal troughPresetTemp4;
    /**
     * 2#后实际
     */
    @Column(name = "troughActualTemp4")
    private BigDecimal troughActualTemp4;
    /**
     * 3#前设定
     */
    @Column(name = "troughPresetTemp5")
    private BigDecimal troughPresetTemp5;
    /**
     * 3#前实际
     */
    @Column(name = "troughActualTemp5")
    private BigDecimal troughActualTemp5;
    /**
     * 3#后设定
     */
    @Column(name = "troughPresetTemp6")
    private BigDecimal troughPresetTemp6;
    /**
     * 3#后实际
     */
    @Column(name = "troughActualTemp6")
    private BigDecimal troughActualTemp6;
    /**
     * 4#前设定
     */
    @Column(name = "troughPresetTemp7")
    private BigDecimal troughPresetTemp7;
    /**
     * 4#前实际
     */
    @Column(name = "troughActualTemp7")
    private BigDecimal troughActualTemp7;
    /**
     * 4#后设定
     */
    @Column(name = "troughPresetTemp8")
    private BigDecimal troughPresetTemp8;
    /**
     * 4#后实际
     */
    @Column(name = "troughActualTemp8")
    private BigDecimal troughActualTemp8;
    /**
     * 5#前设定
     */
    @Column(name = "troughPresetTemp9")
    private BigDecimal troughPresetTemp9;
    /**
     * 5#前实际
     */
    @Column(name = "troughActualTemp9")
    private BigDecimal troughActualTemp9;
    /**
     * 5#后设定
     */
    @Column(name = "troughPresetTemp10")
    private BigDecimal troughPresetTemp10;
    /**
     * 5#后实际
     */
    @Column(name = "troughActualTemp10")
    private BigDecimal troughActualTemp10;
    /**
     * 6#前设定
     */
    @Column(name = "troughPresetTemp11")
    private BigDecimal troughPresetTemp11;
    /**
     * 6#前实际
     */
    @Column(name = "troughActualTemp11")
    private BigDecimal troughActualTemp11;
    /**
     * 6#后设定
     */
    @Column(name = "troughPresetTemp12")
    private BigDecimal troughPresetTemp12;
    /**
     * 6#后实际
     */
    @Column(name = "troughActualTemp12")
    private BigDecimal troughActualTemp12;

    /**
     * 温度13#设定值
     */
    @Column(name = "troughPresetTemp13")
    private BigDecimal troughPresetTemp13;
    /**
     * 温度13#实际值
     */
    @Column(name = "troughActualTemp13")
    private BigDecimal troughActualTemp13;

    /**
     * 温度14#设定值
     */
    @Column(name = "troughPresetTemp14")
    private BigDecimal troughPresetTemp14;
    /**
     * 温度14#实际值
     */
    @Column(name = "troughActualTemp14")
    private BigDecimal troughActualTemp14;

    /**
     * 温度15#设定值
     */
    @Column(name = "troughPresetTemp15")
    private BigDecimal troughPresetTemp15;
    /**
     * 温度15#实际值
     */
    @Column(name = "troughActualTemp15")
    private BigDecimal troughActualTemp15;

    /**
     * 温度16#设定值
     */
    @Column(name = "troughPresetTemp16")
    private BigDecimal troughPresetTemp16;
    /**
     * 温度16#实际值
     */
    @Column(name = "troughActualTemp16")
    private BigDecimal troughActualTemp16;

    /**
     * 预湿槽温度设定
     */
    @Column(name = "steamerPresetTemp")
    private BigDecimal steamerPresetTemp;
    /**
     * 预湿槽温度实际
     */
    @Column(name = "steamerActualTemp")
    private BigDecimal steamerActualTemp;

    /**
     * 进布张力设定值
     */
    @Column(name = "feedCenteringTensionPreset")
    private BigDecimal feedCenteringTensionPreset;
    /**
     * 进布张力实际值
     */
    @Column(name = "feedCenteringTensionActual")
    private BigDecimal feedCenteringTensionActual;

    /**
     * 张力1#设定值
     */
    @Column(name = "tension1")
    private BigDecimal tension1;
    /**
     * 张力1#实际值
     */
    @Column(name = "tensionAct1")
    private BigDecimal tensionAct1;
    /**
     * 张力2#设定值
     */
    @Column(name = "tension2")
    private BigDecimal tension2;
    /**
     * 张力2#实际值
     */
    @Column(name = "tensionAct2")
    private BigDecimal tensionAct2;
    /**
     * 张力3#设定值
     */
    @Column(name = "tension3")
    private BigDecimal tension3;
    /**
     * 张力3#实际值
     */
    @Column(name = "tensionAct3")
    private BigDecimal tensionAct3;
    /**
     * 张力4#设定值
     */
    @Column(name = "tension4")
    private BigDecimal tension4;
    /**
     * 张力4#实际值
     */
    @Column(name = "tensionAct4")
    private BigDecimal tensionAct4;
    /**
     * 张力5#设定值
     */
    @Column(name = "tension5")
    private BigDecimal tension5;
    /**
     * 张力5#实际值
     */
    @Column(name = "tensionAct5")
    private BigDecimal tensionAct5;
    /**
     * 张力6#设定值
     */
    @Column(name = "tension6")
    private BigDecimal tension6;
    /**
     * 张力6#实际值
     */
    @Column(name = "tensionAct6")
    private BigDecimal tensionAct6;
    /**
     * 张力7#设定值
     */
    @Column(name = "tension7")
    private BigDecimal tension7;
    /**
     * 张力7#实际值
     */
    @Column(name = "tensionAct7")
    private BigDecimal tensionAct7;
    /**
     * 张力8#设定值
     */
    @Column(name = "tension8")
    private BigDecimal tension8;
    /**
     * 张力8#实际值
     */
    @Column(name = "tensionAct8")
    private BigDecimal tensionAct8;
    /**
     * 张力9#设定值
     */
    @Column(name = "tension9")
    private BigDecimal tension9;
    /**
     * 张力9#实际值
     */
    @Column(name = "tensionAct9")
    private BigDecimal tensionAct9;
    /**
     * 张力10#设定值
     */
    @Column(name = "tension10")
    private BigDecimal tension10;
    /**
     * 张力10#实际值
     */
    @Column(name = "tensionAct10")
    private BigDecimal tensionAct10;
    /**
     * 张力11#设定值
     */
    @Column(name = "tension11")
    private BigDecimal tension11;
    /**
     * 张力11#实际值
     */
    @Column(name = "tensionAct11")
    private BigDecimal tensionAct11;
    /**
     * 张力12#设定值
     */
    @Column(name = "tension12")
    private BigDecimal tension12;
    /**
     * 张力12#实际值
     */
    @Column(name = "tensionAct12")
    private BigDecimal tensionAct12;
    /**
     * 张力13#设定值
     */
    @Column(name = "tension13")
    private BigDecimal tension13;
    /**
     * 张力13#实际值
     */
    @Column(name = "tensionAct13")
    private BigDecimal tensionAct13;
    /**
     * 张力14#设定值
     */
    @Column(name = "tension14")
    private BigDecimal tension14;
    /**
     * 张力14#实际值
     */
    @Column(name = "tensionAct14")
    private BigDecimal tensionAct14;
    /**
     * 张力15#设定值
     */
    @Column(name = "tension15")
    private BigDecimal tension15;
    /**
     * 张力15#实际值
     */
    @Column(name = "tensionAct15")
    private BigDecimal tensionAct15;
    /**
     * 张力16#设定值
     */
    @Column(name = "tension16")
    private BigDecimal tension16;
    /**
     * 张力16#实际值
     */
    @Column(name = "tensionAct16")
    private BigDecimal tensionAct16;


    /**
     * 出布张力设定值
     */
    @Column(name = "tensionOutlePositionPreset")
    private BigDecimal tensionOutlePositionPreset;
    /**
     * 出布张力实际值
     */
    @Column(name = "tensionOutlePositionActual")
    private BigDecimal tensionOutlePositionActual;
    /**
     * 1#补水量 设定值
     */
    @Column(name = "inflowRate1")
    private BigDecimal inflowRate1;
    /**
     * 1#补水量 实际值
     */
    @Column(name = "inflowRateAct1")
    private BigDecimal inflowRateAct1;
    /**
     * 2#补水量 设定值
     */
    @Column(name = "inflowRate2")
    private BigDecimal inflowRate2;
    /**
     * 2#补水量 实际值
     */
    @Column(name = "inflowRateAct2")
    private BigDecimal inflowRateAct2;
    /**
     * 3#补水量 设定值
     */
    @Column(name = "inflowRate3")
    private BigDecimal inflowRate3;
    /**
     * 3#补水量 实际值
     */
    @Column(name = "inflowRateAct3")
    private BigDecimal inflowRateAct3;

    /**
     * 4#补水量 设定值
     */
    @Column(name = "inflowRate4")
    private BigDecimal inflowRate4;
    /**
     * 4#补水量 实际值
     */
    @Column(name = "inflowRateAct4")
    private BigDecimal inflowRateAct4;


    /**
     * 真空抽吸/频率1#设定值
     */
    @Column(name = "waterUptake1")
    private BigDecimal waterUptake1;
    /**
     * 真空抽吸/频率1#实际值
     */
    @Column(name = "waterUptakeAct1")
    private BigDecimal waterUptakeAct1;

    /**
     * 真空抽吸/频率2#设定值
     */
    @Column(name = "waterUptake2")
    private BigDecimal waterUptake2;
    /**
     * 真空抽吸/频率2#实际值
     */
    @Column(name = "waterUptakeAct2")
    private BigDecimal waterUptakeAct2;

    /**
     * 真空抽吸/频率3#设定值
     */
    @Column(name = "waterUptake3")
    private BigDecimal waterUptake3;
    /**
     * 真空抽吸/频率3#实际值
     */
    @Column(name = "waterUptakeAct3")
    private BigDecimal waterUptakeAct3;

    /**
     * 真空抽吸/频率4#设定值
     */
    @Column(name = "waterUptake4")
    private BigDecimal waterUptake4;
    /**
     * 真空抽吸/频率4#实际值
     */
    @Column(name = "waterUptakeAct4")
    private BigDecimal waterUptakeAct4;

    /**
     * 助剂配比1#设定值
     */
    @Column(name = "assistantPreset1")
    private BigDecimal assistantPreset1;
    /**
     * 助剂配比1#实际值
     */
    @Column(name = "assistantAct1")
    private BigDecimal assistantAct1;

    /**
     * 2#助剂设定值
     */
    @Column(name = "assistantPreset2")
    private BigDecimal assistantPreset2;

    /**
     * 助剂配比2#实际值
     */
    @Column(name = "assistantAct2")
    private BigDecimal assistantAct2;

    /**
     * 3#助剂设定值
     */
    @Column(name = "assistantPreset3")
    private BigDecimal assistantPreset3;

    /**
     * 助剂配比3#实际值
     */
    @Column(name = "assistantAct3")
    private BigDecimal assistantAct3;

    /**
     * 4#助剂设定值
     */
    @Column(name = "assistantPreset4")
    private BigDecimal assistantPreset4;

    /**
     * 助剂配比4#实际值
     */
    @Column(name = "assistantAct4")
    private BigDecimal assistantAct4;


    /**
     * 批次耗量1#耗量
     */
    @Column(name = "batchConsumption1")
    private BigDecimal batchConsumption1;
    /**
     * 批次耗量2#耗量
     */
    @Column(name = "batchConsumption2")
    private BigDecimal batchConsumption2;

    /**
     * 批次耗量3#耗量
     */
    @Column(name = "batchConsumption3")
    private BigDecimal batchConsumption3;

    /**
     * 批次耗量4#耗量
     */
    @Column(name = "batchConsumption4")
    private BigDecimal batchConsumption4;

    /**
     * 总耗量1#耗量
     */
    @Column(name = "totalConsumption1")
    private BigDecimal totalConsumption1;
    /**
     * 总耗量2#耗量
     */
    @Column(name = "totalConsumption2")
    private BigDecimal totalConsumption2;
    /**
     * 总助剂耗量3#实际值
     */
    @Column(name = "totalConsumption3")
    private BigDecimal totalConsumption3;
    /**
     * 总助剂耗量4#实际值
     */
    @Column(name = "totalConsumption4")
    private BigDecimal totalConsumption4;

    /**
     * 进布轧车压力设定值
     */
    @Column(name = "feedRollingPressure")
    private BigDecimal feedRollingPressure;
    /**
     * 进布轧车压力实际值
     */
    @Column(name = "feedRollingPressureAct")
    private BigDecimal feedRollingPressureAct;

    /**
     * 轧车压力1#设定值
     */
    @Column(name = "rollingPressure1")
    private BigDecimal rollingPressure1;
    /**
     * 轧车压力1#实际值
     */
    @Column(name = "rollingPressureAct1")
    private BigDecimal rollingPressureAct1;
    /**
     * 轧车压力2#设定值
     */
    @Column(name = "rollingPressure2")
    private BigDecimal rollingPressure2;
    /**
     * 轧车压力2#实际值
     */
    @Column(name = "rollingPressureAct2")
    private BigDecimal rollingPressureAct2;
    /**
     * 轧车压力3#设定值
     */
    @Column(name = "rollingPressure3")
    private BigDecimal rollingPressure3;
    /**
     * 轧车压力3#实际值
     */
    @Column(name = "rollingPressureAct3")
    private BigDecimal rollingPressureAct3;
    /**
     * 轧车压力4#设定值
     */
    @Column(name = "rollingPressure4")
    private BigDecimal rollingPressure4;
    /**
     * 轧车压力4#实际值
     */
    @Column(name = "rollingPressureAct4")
    private BigDecimal rollingPressureAct4;

    /**
     * 出布轧车压力设定值
     */
    @Column(name = "outRollingPressure")
    private BigDecimal outRollingPressure;
    /**
     * 出布轧车压力实际值
     */
    @Column(name = "outRollingPressureAct")
    private BigDecimal outRollingPressureAct;

    /**
     * PH值1#设定值
     */
    @Column(name = "ph1")
    private BigDecimal ph1;
    /**
     * PH值1#实际值
     */
    @Column(name = "phAct1")
    private BigDecimal phAct1;
    /**
     * PH值2#设定值
     */
    @Column(name = "ph2")
    private BigDecimal ph2;
    /**
     * PH值2#实际值
     */
    @Column(name = "phAct2")
    private BigDecimal phAct2;

    /**
     * 含水量1#设定值
     */
    @Column(name = "waterRatio1")
    private BigDecimal waterRatio1;
    /**
     * 含水量1#实际值
     */
    @Column(name = "waterRatioAct1")
    private BigDecimal waterRatioAct1;
    /**
     * 含水量2#设定值
     */
    @Column(name = "waterRatio2")
    private BigDecimal waterRatio2;
    /**
     * 含水量2#实际值
     */
    @Column(name = "waterRatioAct2")
    private BigDecimal waterRatioAct2;
    /**
     * 含水量3#设定值
     */
    @Column(name = "waterRatio3")
    private BigDecimal waterRatio3;
    /**
     * 含水量3#实际值
     */
    @Column(name = "waterRatioAct3")
    private BigDecimal waterRatioAct3;
    /**
     * 含水量4#设定值
     */
    @Column(name = "waterRatio4")
    private BigDecimal waterRatio4;
    /**
     * 含水量4#实际值
     */
    @Column(name = "waterRatioAct4")
    private BigDecimal waterRatioAct4;

    /**
     * 克重设定值
     */
    @Column(name = "gramWeight")
    private BigDecimal gramWeight;

    /**
     * dataType
     */
    @Column(name = "deviceType")
    private String deviceType;
    /**
     * 设备
     */
    @Column(name = "device")
    private String device;
}
