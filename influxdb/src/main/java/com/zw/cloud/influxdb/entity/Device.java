package com.zw.cloud.influxdb.entity;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;

@Data
@Measurement(name = "device_report_data")
public class Device {

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
    private Float rtime;
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  采集时间
     */
    @Column(name = "ctime")
    private Float ctime;
    /**
     * 消息类型
     * 1：事件类、2： 状态类
     */
    @Column(name = "messageType")
    private Float messageType = 1f;
    /**
     * 【事件类】事件编码
     * 参考 字典 【事件类】事件编码 字典
     */
    @Column(name = "eventCode")
    private Float eventCode;
    /**
     * 车速设定值
     */
    @Column(name = "speedSetting")
    private Float speedSetting;
    /**
     * 车速
     */
    @Column(name = "speed")
    private Float speed;
    /**
     * 进布张力
     */
    @Column(name = "inClothTension")
    private Float inClothTension;
    /**
     * 轧车张力
     */
    @Column(name = "outClothTension")
    private Float outClothTension;
    /**
     * 轧车液位
     */
    @Column(name = "rollingCarLiquidLevel")
    private Float rollingCarLiquidLevel;
    /**
     * 烘房设定温度1
     */
    @Column(name = "dryingRoomPresetTemp1")
    private Float dryingRoomPresetTemp1;
    /**
     * 烘房实际温度1
     */
    @Column(name = "dryingRoomActualTemp1")
    private Float dryingRoomActualTemp1;
    /**
     * 烘房设定温度2
     */
    @Column(name = "dryingRoomPresetTemp2")
    private Float dryingRoomPresetTemp2;
    /**
     * 烘房实际温度2
     */
    @Column(name = "dryingRoomActualTemp2")
    private Float dryingRoomActualTemp2;
    /**
     * 烘房设定温度3
     */
    @Column(name = "dryingRoomPresetTemp3")
    private Float dryingRoomPresetTemp3;
    /**
     * 烘房实际温度3
     */
    @Column(name = "dryingRoomActualTemp3")
    private Float dryingRoomActualTemp3;
    /**
     * 烘房设定温度4
     */
    @Column(name = "dryingRoomPresetTemp4")
    private Float dryingRoomPresetTemp4;
    /**
     * 烘房实际温度4
     */
    @Column(name = "dryingRoomActualTemp4")
    private Float dryingRoomActualTemp4;
    /**
     * 烘房设定温度5
     */
    @Column(name = "dryingRoomPresetTemp5")
    private Float dryingRoomPresetTemp5;
    /**
     * 烘房实际温度5
     */
    @Column(name = "dryingRoomActualTemp5")
    private Float dryingRoomActualTemp5;
    /**
     * 烘房设定温度6
     */
    @Column(name = "dryingRoomPresetTemp6")
    private Float dryingRoomPresetTemp6;
    /**
     * 烘房实际温度6
     */
    @Column(name = "dryingRoomActualTemp6")
    private Float dryingRoomActualTemp6;
    /**
     * 烘房设定温度7
     */
    @Column(name = "dryingRoomPresetTemp7")
    private Float dryingRoomPresetTemp7;
    /**
     * 烘房实际温度7
     */
    @Column(name = "dryingRoomActualTemp7")
    private Float dryingRoomActualTemp7;
    /**
     * 烘房设定温度8
     */
    @Column(name = "dryingRoomPresetTemp8")
    private Float dryingRoomPresetTemp8;
    /**
     * 烘房实际温度8
     */
    @Column(name = "dryingRoomActualTemp8")
    private Float dryingRoomActualTemp8;
    /**
     * 烘房设定温度9
     */
    @Column(name = "dryingRoomPresetTemp9")
    private Float dryingRoomPresetTemp9;
    /**
     * 烘房实际温度9
     */
    @Column(name = "dryingRoomActualTemp9")
    private Float dryingRoomActualTemp9;
    /**
     * 总幅
     */
    @Column(name = "totalAmplitude")
    private Float totalAmplitude;
    /**
     * 烘房门幅
     */
    @Column(name = "dryingRoomDoorWidth")
    private Float dryingRoomDoorWidth;
    /**
     * 上超喂
     */
    @Column(name = "topFeed")
    private Float topFeed;
    /**
     * 下超喂
     */
    @Column(name = "lowerFeed")
    private Float lowerFeed;
    /**
     * 左毛刷超喂
     */
    @Column(name = "leftBrushFeed")
    private Float leftBrushFeed;
    /**
     * 右毛刷超喂
     */
    @Column(name = "rightBrushFeed")
    private Float rightBrushFeed;
    /**
     * 进布超喂
     */
    @Column(name = "inClothFeed")
    private Float inClothFeed;
    /**
     * 冷水辊超喂
     */
    @Column(name = "outClothFeed")
    private Float outClothFeed;
    /**
     * 落布超喂
     */
    @Column(name = "fallingFeed")
    private Float fallingFeed;
    /**
     * 摆布超喂
     */
    @Column(name = "swingFeed")
    private Float swingFeed;
    /**
     * 排风设定转速1
     */
    @Column(name = "speciWindSpeed1")
    private Float speciWindSpeed1;
    /**
     * 排风转速1
     */
    @Column(name = "windSpeed1")
    private Float windSpeed1;
    /**
     * 排风设定转速2
     */
    @Column(name = "speciWindSpeed2")
    private Float speciWindSpeed2;
    /**
     * 排风转速2
     */
    @Column(name = "windSpeed2")
    private Float windSpeed2;
    /**
     * 排风设定转速3
     */
    @Column(name = "speciWindSpeed3")
    private Float speciWindSpeed3;
    /**
     * 排风转速3
     */
    @Column(name = "windSpeed3")
    private Float windSpeed3;
    /**
     * 排风设定转速4
     */
    @Column(name = "speciWindSpeed4")
    private Float speciWindSpeed4;
    /**
     * 排风转速4
     */
    @Column(name = "windSpeed4")
    private Float windSpeed4;
    /**
     * 循环风设定转速1
     */
    @Column(name = "speciCycleWindSpeed1")
    private String speciCycleWindSpeed1;
    /**
     * 循环风转速1
     */
    @Column(name = "cycleWindSpeed1")
    private String cycleWindSpeed1;
    /**
     * 循环风设定转速2
     */
    @Column(name = "speciCycleWindSpeed2")
    private Float speciCycleWindSpeed2;
    /**
     * 循环风转速2
     */
    @Column(name = "cycleWindSpeed2")
    private Float cycleWindSpeed2;
    /**
     * 循环风设定转速3
     */
    @Column(name = "speciCycleWindSpeed3")
    private Float speciCycleWindSpeed3;
    /**
     * 循环风转速3
     */
    @Column(name = "cycleWindSpeed3")
    private Float cycleWindSpeed3;
    /**
     * 循环风设定转速4
     */
    @Column(name = "speciCycleWindSpeed4")
    private Float speciCycleWindSpeed4;
    /**
     * 循环风转速4
     */
    @Column(name = "cycleWindSpeed4")
    private Float cycleWindSpeed4;
    /**
     * 循环风设定转速5
     */
    @Column(name = "speciCycleWindSpeed5")
    private Float speciCycleWindSpeed5;
    /**
     * 循环风转速5
     */
    @Column(name = "cycleWindSpeed5")
    private Float cycleWindSpeed5;
    /**
     * 循环风设定转速6
     */
    @Column(name = "speciCycleWindSpeed6")
    private Float speciCycleWindSpeed6;
    /**
     * 循环风转速6
     */
    @Column(name = "cycleWindSpeed6")
    private Float cycleWindSpeed6;
    /**
     * 循环风设定转速7
     */
    @Column(name = "speciCycleWindSpeed7")
    private Float speciCycleWindSpeed7;
    /**
     * 循环风转速7
     */
    @Column(name = "cycleWindSpeed7")
    private Float cycleWindSpeed7;
    /**
     * 循环风设定转速8
     */
    @Column(name = "speciCycleWindSpeed8")
    private Float speciCycleWindSpeed8;
    /**
     * 循环风转速8
     */
    @Column(name = "cycleWindSpeed8")
    private Float cycleWindSpeed8;
    /**
     * 循环风设定转速9
     */
    @Column(name = "speciCycleWindSpeed9")
    private Float speciCycleWindSpeed9;
    /**
     * 循环风转速9
     */
    @Column(name = "cycleWindSpeed9")
    private Float cycleWindSpeed9;
    /**
     * 料槽温度
     */
    @Column(name = "troughTemp")
    private Float troughTemp;
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
    private Float speciWindSpeed5;
    /**
     * 排风转速5
     */
    @Column(name = "windSpeed5")
    private Float windSpeed5;
    /**
     * 排风设定转速6
     */
    @Column(name = "speciWindSpeed6")
    private Float speciWindSpeed6;
    /**
     * 排风转速6
     */
    @Column(name = "windSpeed6")
    private Float windSpeed6;
    /**
     * 烘房设定温度10
     */
    @Column(name = "dryingRoomPresetTemp10")
    private Float dryingRoomPresetTemp10;
    /**
     * 烘房实际温度10
     */
    @Column(name = "dryingRoomActualTemp10")
    private Float dryingRoomActualTemp10;
    /**
     * 循环风设定转速10
     */
    @Column(name = "speciCycleWindSpeed10")
    private Float speciCycleWindSpeed10;
    /**
     * 循环风转速10
     */
    @Column(name = "cycleWindSpeed10")
    private Float cycleWindSpeed10;

    /**
     * dataType
     */
    @Column(name = "dataType")
    private String dataType;
    /**
     * 设备
     */
    @Column(name = "device")
    private String device;
}
