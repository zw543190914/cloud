package com.zw.cloud.tools.entity.mqtt;

import com.zw.cloud.common.annotation.MonitoringPoint;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 定型机-状态数据
 *
 * @author panlizhi
 * @date 2021/8/12
 */
@Data
public class IotInfoDto implements Serializable {
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  数据上报时间
     */
    private Long rtime;
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  采集时间
     */
    private Long ctime;
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
     * 车速设定值
     */
    @MonitoringPoint(chineseDesc = "车速设定值")
    private BigDecimal speedSetting;
    /**
     * 车速
     */
    @MonitoringPoint(chineseDesc = "车速")
    private BigDecimal speed;
    /**
     * 进布张力
     */
    @MonitoringPoint(chineseDesc = "进布张力")
    private BigDecimal inClothTension;
    /**
     * 轧车张力
     */
    @MonitoringPoint(chineseDesc = "轧车张力")
    private BigDecimal outClothTension;
    /**
     * 轧车液位
     */
    @MonitoringPoint(chineseDesc = "轧车液位")
    private BigDecimal rollingCarLiquidLevel;
    /**
     * 烘房设定温度1
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度1")
    private BigDecimal dryingRoomPresetTemp1;
    /**
     * 烘房实际温度1
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度1")
    private BigDecimal dryingRoomActualTemp1;
    /**
     * 烘房设定温度2
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度2")
    private BigDecimal dryingRoomPresetTemp2;
    /**
     * 烘房实际温度2
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度2")
    private BigDecimal dryingRoomActualTemp2;
    /**
     * 烘房设定温度3
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度3")
    private BigDecimal dryingRoomPresetTemp3;
    /**
     * 烘房实际温度3
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度3")
    private BigDecimal dryingRoomActualTemp3;
    /**
     * 烘房设定温度4
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度4")
    private BigDecimal dryingRoomPresetTemp4;
    /**
     * 烘房实际温度4
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度4")
    private BigDecimal dryingRoomActualTemp4;
    /**
     * 烘房设定温度5
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度5")
    private BigDecimal dryingRoomPresetTemp5;
    /**
     * 烘房实际温度5
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度5")
    private BigDecimal dryingRoomActualTemp5;
    /**
     * 烘房设定温度6
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度6")
    private BigDecimal dryingRoomPresetTemp6;
    /**
     * 烘房实际温度6
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度6")
    private BigDecimal dryingRoomActualTemp6;
    /**
     * 烘房设定温度7
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度7")
    private BigDecimal dryingRoomPresetTemp7;
    /**
     * 烘房实际温度7
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度7")
    private BigDecimal dryingRoomActualTemp7;
    /**
     * 烘房设定温度8
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度8")
    private BigDecimal dryingRoomPresetTemp8;
    /**
     * 烘房实际温度8
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度8")
    private BigDecimal dryingRoomActualTemp8;
    /**
     * 烘房设定温度9
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度9")
    private BigDecimal dryingRoomPresetTemp9;
    /**
     * 烘房实际温度9
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度9")
    private BigDecimal dryingRoomActualTemp9;
    /**
     * 总幅
     */
    @MonitoringPoint(chineseDesc = "总幅")
    private BigDecimal totalAmplitude;
    /**
     * 烘房门幅
     */
    @MonitoringPoint(chineseDesc = "烘房门幅")
    private BigDecimal dryingRoomDoorWidth;
    /**
     * 上超喂
     */
    @MonitoringPoint(chineseDesc = "上超喂")
    private BigDecimal topFeed;
    /**
     * 下超喂
     */
    @MonitoringPoint(chineseDesc = "下超喂")
    private BigDecimal lowerFeed;
    /**
     * 左毛刷超喂
     */
    @MonitoringPoint(chineseDesc = "左毛刷超喂")
    private BigDecimal leftBrushFeed;
    /**
     * 右毛刷超喂
     */
    @MonitoringPoint(chineseDesc = "右毛刷超喂")
    private BigDecimal rightBrushFeed;
    /**
     * 进布超喂
     */
    @MonitoringPoint(chineseDesc = "进布超喂")
    private BigDecimal inClothFeed;
    /**
     * 冷水辊超喂
     */
    @MonitoringPoint(chineseDesc = "冷水辊超喂")
    private BigDecimal outClothFeed;
    /**
     * 落布超喂
     */
    @MonitoringPoint(chineseDesc = "落布超喂")
    private BigDecimal fallingFeed;
    /**
     * 摆布超喂
     */
    @MonitoringPoint(chineseDesc = "摆布超喂")
    private BigDecimal swingFeed;
    /**
     * 排风设定转速1
     */
    @MonitoringPoint(chineseDesc = "排风设定转速1")
    private BigDecimal speciWindSpeed1;
    /**
     * 排风转速1
     */
    @MonitoringPoint(chineseDesc = "排风转速1")
    private BigDecimal windSpeed1;
    /**
     * 排风设定转速2
     */
    @MonitoringPoint(chineseDesc = "排风设定转速2")
    private BigDecimal speciWindSpeed2;
    /**
     * 排风转速2
     */
    @MonitoringPoint(chineseDesc = "排风转速2")
    private BigDecimal windSpeed2;
    /**
     * 排风设定转速3
     */
    @MonitoringPoint(chineseDesc = "排风设定转速3")
    private BigDecimal speciWindSpeed3;
    /**
     * 排风转速3
     */
    @MonitoringPoint(chineseDesc = "排风转速3")
    private BigDecimal windSpeed3;
    /**
     * 排风设定转速4
     */
    @MonitoringPoint(chineseDesc = "排风设定转速4")
    private BigDecimal speciWindSpeed4;
    /**
     * 排风转速4
     */
    @MonitoringPoint(chineseDesc = "排风转速4")
    private BigDecimal windSpeed4;
    /**
     * 循环风设定转速1
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed1;
    /**
     * 循环风转速1
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed1;
    /**
     * 循环风设定转速2
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed2;
    /**
     * 循环风转速2
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed2;
    /**
     * 循环风设定转速3
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed3;
    /**
     * 循环风转速3
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed3;
    /**
     * 循环风设定转速4
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed4;
    /**
     * 循环风转速4
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed4;
    /**
     * 循环风设定转速5
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed5;
    /**
     * 循环风转速5
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed5;
    /**
     * 循环风设定转速6
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed6;
    /**
     * 循环风转速6
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed6;
    /**
     * 循环风设定转速7
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed7;
    /**
     * 循环风转速7
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed7;
    /**
     * 循环风设定转速8
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed8;
    /**
     * 循环风转速8
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed8;
    /**
     * 循环风设定转速9
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed9;
    /**
     * 循环风转速9
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed9;
    /**
     * 料槽温度
     */
    @MonitoringPoint(chineseDesc = "料槽温度")
    private BigDecimal troughTemp;
    /**
     * 启停、急停信号
     */
    private Integer stopSignal;
    /**
     * 允许开机信号
     */
    private Integer allowStartSignal;
    /**
     * 局部设备开机状态
     */
    private Integer partDeviceStartStatus;
    /**
     * 报警信号
     */
    private Integer warnSignal;
    /**
     * 排风设定转速5
     */
    @MonitoringPoint(chineseDesc = "排风设定转速5")
    private BigDecimal speciWindSpeed5;
    /**
     * 排风转速5
     */
    @MonitoringPoint(chineseDesc = "排风转速5")
    private BigDecimal windSpeed5;
    /**
     * 排风设定转速6
     */
    @MonitoringPoint(chineseDesc = "排风设定转速6")
    private BigDecimal speciWindSpeed6;
    /**
     * 排风转速6
     */
    @MonitoringPoint(chineseDesc = "排风转速6")
    private BigDecimal windSpeed6;
    /**
     * 排风设定转速7
     */
    @MonitoringPoint(chineseDesc = "排风设定转速7")
    private BigDecimal speciWindSpeed7;
    /**
     * 排风转速7
     */
    @MonitoringPoint(chineseDesc = "排风转速7")
    private BigDecimal windSpeed7;
    /**
     * 烘房设定温度10
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度10")
    private BigDecimal dryingRoomPresetTemp10;
    /**
     * 烘房实际温度10
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度10")
    private BigDecimal dryingRoomActualTemp10;
    /**
     * 烘房设定温度11
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度11")
    private BigDecimal dryingRoomPresetTemp11;
    /**
     * 烘房实际温度11
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度11")
    private BigDecimal dryingRoomActualTemp11;
    /**
     * 烘房设定温度12
     */
    @MonitoringPoint(chineseDesc = "烘房设定温度12")
    private BigDecimal dryingRoomPresetTemp12;
    /**
     * 烘房实际温度12
     */
    @MonitoringPoint(chineseDesc = "烘房实际温度12")
    private BigDecimal dryingRoomActualTemp12;
    /**
     * 循环风设定转速10
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed10;
    /**
     * 循环风转速10
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed10;
    /**
     * 循环风设定转速11
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed11;
    /**
     * 循环风转速11
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed11;
    /**
     * 循环风设定转速12
     */
    @MonitoringPoint(chineseDesc = "循环风设定转速")
    private BigDecimal speciCycleWindSpeed12;
    /**
     * 循环风转速12
     */
    @MonitoringPoint(chineseDesc = "循环风转速")
    private BigDecimal cycleWindSpeed12;
    /**
     * 烘房设定平均温度
     */
    private BigDecimal dryingRoomPresetAvgTemp;
    /**
     * 烘房实际平均温度
     */
    private BigDecimal dryingRoomActualAvgTemp;
    /**
     * 循环风设定平均转速
     */
    private BigDecimal speciCycleWindAvgSpeed;
    /**
     * 循环风平均转速
     */
    private BigDecimal cycleWindAvgSpeed;


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
    /**
     * 协议版本号
     */
    private BigDecimal version;

    /**
     * 一键设定循环风
     */
    private BigDecimal speciCycleWindSpeedSetting;
    /**
     * 排风设定转速8
     */
    @MonitoringPoint(chineseDesc = "排风设定转速8")
    private BigDecimal speciWindSpeed8;
    /**
     * 排风转速8
     */
    @MonitoringPoint(chineseDesc = "排风转速8")
    private BigDecimal windSpeed8;
    /**
     * 上针超喂
     */
    @MonitoringPoint(chineseDesc = "上针超喂")
    private BigDecimal needleOverfeeding;
    /**
     * 出布辊超喂
     */
    @MonitoringPoint(chineseDesc = "出布辊超喂")
    private BigDecimal overfeedOfClothDeliveryRoller;
    /**
     * 引布带超喂
     */
    @MonitoringPoint(chineseDesc = "引布带超喂")
    private BigDecimal clothGuideBeltOverfeed;

    /**
     * 所有门幅设定
     */
    @MonitoringPoint(chineseDesc = "所有门幅设定")
    private BigDecimal allDoorWidthSetting;
    /**
     * 总门幅设定
     */
    @MonitoringPoint(chineseDesc = "总门幅设定")
    private BigDecimal totalDoorWidthSetting;
    /**
     * 前门幅设定
     */
    @MonitoringPoint(chineseDesc = "前门幅设定")
    private BigDecimal frontDoorWidthSetting;
    /**
     * 前门幅实际
     */
    @MonitoringPoint(chineseDesc = "前门幅实际")
    private BigDecimal frontDoorWidthActual;
    /**
     * 后门幅设定
     */
    @MonitoringPoint(chineseDesc = "后门幅设定")
    private BigDecimal rearDoorWidthSetting;
    /**
     * 后门幅实际
     */
    @MonitoringPoint(chineseDesc = "后门幅实际")
    private BigDecimal rearDoorWidthActual;
    /**
     * 门幅1设定
     */
    @MonitoringPoint(chineseDesc = "门幅1设定")
    private BigDecimal doorWidthSetting1;
    /**
     * 门幅1实际
     */
    @MonitoringPoint(chineseDesc = "门幅1实际")
    private BigDecimal doorWidthActual1;
    /**
     * 门幅2设定
     */
    @MonitoringPoint(chineseDesc = "门幅2设定")
    private BigDecimal doorWidthSetting2;
    /**
     * 门幅2实际
     */
    @MonitoringPoint(chineseDesc = "门幅2实际")
    private BigDecimal doorWidthActual2;
    /**
     * 门幅3设定
     */
    @MonitoringPoint(chineseDesc = "门幅3设定")
    private BigDecimal doorWidthSetting3;
    /**
     * 门幅3实际
     */
    @MonitoringPoint(chineseDesc = "门幅3实际")
    private BigDecimal doorWidthActual3;
    /**
     * 门幅4设定
     */
    @MonitoringPoint(chineseDesc = "门幅4设定")
    private BigDecimal doorWidthSetting4;
    /**
     * 门幅4实际
     */
    @MonitoringPoint(chineseDesc = "门幅4实际")
    private BigDecimal doorWidthActual4;
    /**
     * 门幅5设定
     */
    @MonitoringPoint(chineseDesc = "门幅5设定")
    private BigDecimal doorWidthSetting5;
    /**
     * 门幅5实际
     */
    @MonitoringPoint(chineseDesc = "门幅5实际")
    private BigDecimal doorWidthActual5;
    /**
     * 门幅6设定
     */
    @MonitoringPoint(chineseDesc = "门幅6设定")
    private BigDecimal doorWidthSetting6;
    /**
     * 门幅6实际
     */
    @MonitoringPoint(chineseDesc = "门幅6实际")
    private BigDecimal doorWidthActual6;
    /**
     * 门幅7设定
     */
    @MonitoringPoint(chineseDesc = "门幅7设定")
    private BigDecimal doorWidthSetting7;
    /**
     * 门幅7实际
     */
    @MonitoringPoint(chineseDesc = "门幅7实际")
    private BigDecimal doorWidthActual7;

    /**
     * 门幅8设定
     */
    @MonitoringPoint(chineseDesc = "门幅8设定")
    private BigDecimal doorWidthSetting8;
    /**
     * 门幅8实际
     */
    @MonitoringPoint(chineseDesc = "门幅8实际")
    private BigDecimal doorWidthActual8;
    /**
     * 门幅9设定
     */
    @MonitoringPoint(chineseDesc = "门幅9设定")
    private BigDecimal doorWidthSetting9;
    /**
     * 门幅9实际
     */
    @MonitoringPoint(chineseDesc = "门幅9实际")
    private BigDecimal doorWidthActual9;
    /**
     * 门幅10设定
     */
    @MonitoringPoint(chineseDesc = "门幅10设定")
    private BigDecimal doorWidthSetting10;
    /**
     * 门幅10实际
     */
    @MonitoringPoint(chineseDesc = "门幅10实际")
    private BigDecimal doorWidthActual10;
    /**
     * 门幅11设定
     */
    @MonitoringPoint(chineseDesc = "门幅11设定")
    private BigDecimal doorWidthSetting11;
    /**
     * 门幅11实际
     */
    @MonitoringPoint(chineseDesc = "门幅11实际")
    private BigDecimal doorWidthActual11;
    /**
     * 门幅12设定
     */
    @MonitoringPoint(chineseDesc = "门幅12设定")
    private BigDecimal doorWidthSetting12;
    /**
     * 门幅12实际
     */
    @MonitoringPoint(chineseDesc = "门幅12实际")
    private BigDecimal doorWidthActual12;

    /**
     * 上超速度
     */
    @MonitoringPoint(chineseDesc = "上超速度")
    private BigDecimal upperSuperSpeed;
    /**
     * 上针超喂
     */
    @MonitoringPoint(chineseDesc = "上针超喂")
    private BigDecimal downSuperSpeed;
    /**
     * 左毛刷速度
     */
    @MonitoringPoint(chineseDesc = "左毛刷速度")
    private BigDecimal leftBrushSpeed;
    /**
     * 右毛刷速度
     */
    @MonitoringPoint(chineseDesc = "右毛刷速度")
    private BigDecimal rightBrushSpeed;
    /**
     * 出布速度
     */
    @MonitoringPoint(chineseDesc = "出布速度")
    private BigDecimal publishingSpeed;
    /**
     * 落布速度
     */
    @MonitoringPoint(chineseDesc = "落布速度")
    private BigDecimal droppingSpeed;
    /**
     * 摆布速度
     */
    @MonitoringPoint(chineseDesc = "摆布速度")
    private BigDecimal swingSpeed;
    /**
     * 引布带速度
     */
    @MonitoringPoint(chineseDesc = "引布带速度")
    private BigDecimal quickTapeSpeed;
    /**
     * 轧车速度
     */
    @MonitoringPoint(chineseDesc = "轧车速度")
    private BigDecimal rollingSpeed;

    /**
     * 循环风上1
     */
    private BigDecimal circulatingAirUpper1;

    /**
     * 循环风上2
     */
    private BigDecimal circulatingAirUpper2;

    /**
     * 循环风上3
     */
    private BigDecimal circulatingAirUpper3;

    /**
     * 循环风上4
     */
    private BigDecimal circulatingAirUpper4;

    /**
     * 循环风上5
     */
    private BigDecimal circulatingAirUpper5;

    /**
     * 循环风上6
     */
    private BigDecimal circulatingAirUpper6;

    /**
     * 循环风上7
     */
    private BigDecimal circulatingAirUpper7;

    /**
     * 循环风上8
     */
    private BigDecimal circulatingAirUpper8;

    /**
     * 循环风上9
     */
    private BigDecimal circulatingAirUpper9;

    /**
     * 循环风上10
     */
    private BigDecimal circulatingAirUpper10;

    /**
     * 循环风上11
     */
    private BigDecimal circulatingAirUpper11;

    /**
     * 循环风上12
     */
    private BigDecimal circulatingAirUpper12;

    /**
     * 循环风下1
     */
    private BigDecimal circulatingAirLower1;

    /**
     * 循环风下2
     */
    private BigDecimal circulatingAirLower2;

    /**
     * 循环风下3
     */
    private BigDecimal circulatingAirLower3;

    /**
     * 循环风下4
     */
    private BigDecimal circulatingAirLower4;

    /**
     * 循环风下5
     */
    private BigDecimal circulatingAirLower5;

    /**
     * 循环风下6
     */
    private BigDecimal circulatingAirLower6;

    /**
     * 循环风下7
     */
    private BigDecimal circulatingAirLower7;

    /**
     * 循环风下8
     */
    private BigDecimal circulatingAirLower8;

    /**
     * 循环风下9
     */
    private BigDecimal circulatingAirLower9;

    /**
     * 循环风下10
     */
    private BigDecimal circulatingAirLower10;

    /**
     * 循环风下11
     */
    private BigDecimal circulatingAirLower11;

    /**
     * 循环风下12
     */
    private BigDecimal circulatingAirLower12;

    /**
     * 平均循环风上
     */
    private BigDecimal avgCirculatingAirUpper;
    /**
     * 平均循环风下
     */
    private BigDecimal avgCirculatingAirLower;

    /**
     * 循环风上(设定值)1
     */
    private BigDecimal circulatingAirUpperSetting1;

    /**
     * 循环风上(设定值)2
     */
    private BigDecimal circulatingAirUpperSetting2;

    /**
     * 循环风上(设定值)3
     */
    private BigDecimal circulatingAirUpperSetting3;

    /**
     * 循环风上(设定值)4
     */
    private BigDecimal circulatingAirUpperSetting4;

    /**
     * 循环风上(设定值)5
     */
    private BigDecimal circulatingAirUpperSetting5;

    /**
     * 循环风上(设定值)6
     */
    private BigDecimal circulatingAirUpperSetting6;

    /**
     * 循环风上(设定值)7
     */
    private BigDecimal circulatingAirUpperSetting7;

    /**
     * 循环风上(设定值)8
     */
    private BigDecimal circulatingAirUpperSetting8;

    /**
     * 循环风上(设定值)9
     */
    private BigDecimal circulatingAirUpperSetting9;

    /**
     * 循环风上(设定值)10
     */
    private BigDecimal circulatingAirUpperSetting10;

    /**
     * 循环风上(设定值)11
     */
    private BigDecimal circulatingAirUpperSetting11;

    /**
     * 循环风上(设定值)12
     */
    private BigDecimal circulatingAirUpperSetting12;

    /**
     * 循环风下(设定值)1
     */
    private BigDecimal circulatingAirLowerSetting1;

    /**
     * 循环风下(设定值)2
     */
    private BigDecimal circulatingAirLowerSetting2;

    /**
     * 循环风下(设定值)3
     */
    private BigDecimal circulatingAirLowerSetting3;

    /**
     * 循环风下(设定值)4
     */
    private BigDecimal circulatingAirLowerSetting4;

    /**
     * 循环风下(设定值)5
     */
    private BigDecimal circulatingAirLowerSetting5;

    /**
     * 循环风下(设定值)6
     */
    private BigDecimal circulatingAirLowerSetting6;

    /**
     * 循环风下(设定值)7
     */
    private BigDecimal circulatingAirLowerSetting7;

    /**
     * 循环风下(设定值)8
     */
    private BigDecimal circulatingAirLowerSetting8;

    /**
     * 循环风下(设定值)9
     */
    private BigDecimal circulatingAirLowerSetting9;

    /**
     * 循环风下(设定值)10
     */
    private BigDecimal circulatingAirLowerSetting10;

    /**
     * 循环风下(设定值)11
     */
    private BigDecimal circulatingAirLowerSetting11;

    /**
     * 循环风下(设定值)12
     */
    private BigDecimal circulatingAirLowerSetting12;

    /**
     * 平均循环风(设定值)上
     */
    private BigDecimal avgCirculatingAirUpperSetting;
    /**
     * 平均循环风(设定值)下
     */
    private BigDecimal avgCirculatingAirLowerSetting;
}
