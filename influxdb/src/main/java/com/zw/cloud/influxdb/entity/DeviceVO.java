package com.zw.cloud.influxdb.entity;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class DeviceVO {

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
     * 车速
     */
    @Column(name = "speed")
    private BigDecimal speed;
    /**
     * 进布张力
     */
    @Column(name = "inClothTension")
    private BigDecimal inClothTension;
    /**
     * 轧车张力
     */
    @Column(name = "outClothTension")
    private BigDecimal outClothTension;
    /**
     * 轧车液位
     */
    @Column(name = "rollingCarLiquidLevel")
    private BigDecimal rollingCarLiquidLevel;
    /**
     * 烘房设定温度1
     */
    @Column(name = "dryingRoomPresetTemp1")
    private BigDecimal dryingRoomPresetTemp1;
    /**
     * 烘房实际温度1
     */
    @Column(name = "dryingRoomActualTemp1")
    private BigDecimal dryingRoomActualTemp1;
    /**
     * 烘房设定温度2
     */
    @Column(name = "dryingRoomPresetTemp2")
    private BigDecimal dryingRoomPresetTemp2;
    /**
     * 烘房实际温度2
     */
    @Column(name = "dryingRoomActualTemp2")
    private BigDecimal dryingRoomActualTemp2;
    /**
     * 烘房设定温度3
     */
    @Column(name = "dryingRoomPresetTemp3")
    private BigDecimal dryingRoomPresetTemp3;
    /**
     * 烘房实际温度3
     */
    @Column(name = "dryingRoomActualTemp3")
    private BigDecimal dryingRoomActualTemp3;
    /**
     * 烘房设定温度4
     */
    @Column(name = "dryingRoomPresetTemp4")
    private BigDecimal dryingRoomPresetTemp4;
    /**
     * 烘房实际温度4
     */
    @Column(name = "dryingRoomActualTemp4")
    private BigDecimal dryingRoomActualTemp4;
    /**
     * 烘房设定温度5
     */
    @Column(name = "dryingRoomPresetTemp5")
    private BigDecimal dryingRoomPresetTemp5;
    /**
     * 烘房实际温度5
     */
    @Column(name = "dryingRoomActualTemp5")
    private BigDecimal dryingRoomActualTemp5;
    /**
     * 烘房设定温度6
     */
    @Column(name = "dryingRoomPresetTemp6")
    private BigDecimal dryingRoomPresetTemp6;
    /**
     * 烘房实际温度6
     */
    @Column(name = "dryingRoomActualTemp6")
    private BigDecimal dryingRoomActualTemp6;
    /**
     * 烘房设定温度7
     */
    @Column(name = "dryingRoomPresetTemp7")
    private BigDecimal dryingRoomPresetTemp7;
    /**
     * 烘房实际温度7
     */
    @Column(name = "dryingRoomActualTemp7")
    private BigDecimal dryingRoomActualTemp7;
    /**
     * 烘房设定温度8
     */
    @Column(name = "dryingRoomPresetTemp8")
    private BigDecimal dryingRoomPresetTemp8;
    /**
     * 烘房实际温度8
     */
    @Column(name = "dryingRoomActualTemp8")
    private BigDecimal dryingRoomActualTemp8;
    /**
     * 烘房设定温度9
     */
    @Column(name = "dryingRoomPresetTemp9")
    private BigDecimal dryingRoomPresetTemp9;
    /**
     * 烘房实际温度9
     */
    @Column(name = "dryingRoomActualTemp9")
    private BigDecimal dryingRoomActualTemp9;
    /**
     * 总幅
     */
    @Column(name = "totalAmplitude")
    private BigDecimal totalAmplitude;
    /**
     * 烘房门幅
     */
    @Column(name = "dryingRoomDoorWidth")
    private BigDecimal dryingRoomDoorWidth;
    /**
     * 上超喂
     */
    @Column(name = "topFeed")
    private BigDecimal topFeed;
    /**
     * 下超喂
     */
    @Column(name = "lowerFeed")
    private BigDecimal lowerFeed;
    /**
     * 左毛刷超喂
     */
    @Column(name = "leftBrushFeed")
    private BigDecimal leftBrushFeed;
    /**
     * 右毛刷超喂
     */
    @Column(name = "rightBrushFeed")
    private BigDecimal rightBrushFeed;
    /**
     * 进布超喂
     */
    @Column(name = "inClothFeed")
    private BigDecimal inClothFeed;
    /**
     * 冷水辊超喂
     */
    @Column(name = "outClothFeed")
    private BigDecimal outClothFeed;
    /**
     * 落布超喂
     */
    @Column(name = "fallingFeed")
    private BigDecimal fallingFeed;
    /**
     * 摆布超喂
     */
    @Column(name = "swingFeed")
    private BigDecimal swingFeed;
    /**
     * 排风设定转速1
     */
    @Column(name = "speciWindSpeed1")
    private BigDecimal speciWindSpeed1;
    /**
     * 排风转速1
     */
    @Column(name = "windSpeed1")
    private BigDecimal windSpeed1;
    /**
     * 排风设定转速2
     */
    @Column(name = "speciWindSpeed2")
    private BigDecimal speciWindSpeed2;
    /**
     * 排风转速2
     */
    @Column(name = "windSpeed2")
    private BigDecimal windSpeed2;
    /**
     * 排风设定转速3
     */
    @Column(name = "speciWindSpeed3")
    private BigDecimal speciWindSpeed3;
    /**
     * 排风转速3
     */
    @Column(name = "windSpeed3")
    private BigDecimal windSpeed3;
    /**
     * 排风设定转速4
     */
    @Column(name = "speciWindSpeed4")
    private BigDecimal speciWindSpeed4;
    /**
     * 排风转速4
     */
    @Column(name = "windSpeed4")
    private BigDecimal windSpeed4;
    /**
     * 循环风设定转速1
     */
    @Column(name = "speciCycleWindSpeed1")
    private BigDecimal speciCycleWindSpeed1;
    /**
     * 循环风转速1
     */
    @Column(name = "cycleWindSpeed1")
    private BigDecimal cycleWindSpeed1;
    /**
     * 循环风设定转速2
     */
    @Column(name = "speciCycleWindSpeed2")
    private BigDecimal speciCycleWindSpeed2;
    /**
     * 循环风转速2
     */
    @Column(name = "cycleWindSpeed2")
    private BigDecimal cycleWindSpeed2;
    /**
     * 循环风设定转速3
     */
    @Column(name = "speciCycleWindSpeed3")
    private BigDecimal speciCycleWindSpeed3;
    /**
     * 循环风转速3
     */
    @Column(name = "cycleWindSpeed3")
    private BigDecimal cycleWindSpeed3;
    /**
     * 循环风设定转速4
     */
    @Column(name = "speciCycleWindSpeed4")
    private BigDecimal speciCycleWindSpeed4;
    /**
     * 循环风转速4
     */
    @Column(name = "cycleWindSpeed4")
    private BigDecimal cycleWindSpeed4;
    /**
     * 循环风设定转速5
     */
    @Column(name = "speciCycleWindSpeed5")
    private BigDecimal speciCycleWindSpeed5;
    /**
     * 循环风转速5
     */
    @Column(name = "cycleWindSpeed5")
    private BigDecimal cycleWindSpeed5;
    /**
     * 循环风设定转速6
     */
    @Column(name = "speciCycleWindSpeed6")
    private BigDecimal speciCycleWindSpeed6;
    /**
     * 循环风转速6
     */
    @Column(name = "cycleWindSpeed6")
    private BigDecimal cycleWindSpeed6;
    /**
     * 循环风设定转速7
     */
    @Column(name = "speciCycleWindSpeed7")
    private BigDecimal speciCycleWindSpeed7;
    /**
     * 循环风转速7
     */
    @Column(name = "cycleWindSpeed7")
    private BigDecimal cycleWindSpeed7;
    /**
     * 循环风设定转速8
     */
    @Column(name = "speciCycleWindSpeed8")
    private BigDecimal speciCycleWindSpeed8;
    /**
     * 循环风转速8
     */
    @Column(name = "cycleWindSpeed8")
    private BigDecimal cycleWindSpeed8;
    /**
     * 循环风设定转速9
     */
    @Column(name = "speciCycleWindSpeed9")
    private BigDecimal speciCycleWindSpeed9;
    /**
     * 循环风转速9
     */
    @Column(name = "cycleWindSpeed9")
    private BigDecimal cycleWindSpeed9;
    /**
     * 料槽温度
     */
    @Column(name = "troughTemp")
    private BigDecimal troughTemp;
    /**
     * 启停、急停信号
     */
    @Column(name = "stopSignal")
    private Integer stopSignal;
    /**
     * 允许开机信号
     */
    @Column(name = "allowStartSignal")
    private Integer allowStartSignal;
    /**
     * 局部设备开机状态
     */
    @Column(name = "partDeviceStartStatus")
    private Integer partDeviceStartStatus;
    /**
     * 报警信号
     */
    @Column(name = "warnSignal")
    private Integer warnSignal;
    /**
     * 排风设定转速5
     */
    @Column(name = "speciWindSpeed5")
    private BigDecimal speciWindSpeed5;
    /**
     * 排风转速5
     */
    @Column(name = "windSpeed5")
    private BigDecimal windSpeed5;
    /**
     * 排风设定转速6
     */
    @Column(name = "speciWindSpeed6")
    private BigDecimal speciWindSpeed6;
    /**
     * 排风转速6
     */
    @Column(name = "windSpeed6")
    private BigDecimal windSpeed6;
    /**
     * 烘房设定温度10
     */
    @Column(name = "dryingRoomPresetTemp10")
    private BigDecimal dryingRoomPresetTemp10;
    /**
     * 烘房实际温度10
     */
    @Column(name = "dryingRoomActualTemp10")
    private BigDecimal dryingRoomActualTemp10;
    /**
     * 循环风设定转速10
     */
    @Column(name = "speciCycleWindSpeed10")
    private BigDecimal speciCycleWindSpeed10;
    /**
     * 循环风转速10
     */
    @Column(name = "cycleWindSpeed10")
    private BigDecimal cycleWindSpeed10;
}
