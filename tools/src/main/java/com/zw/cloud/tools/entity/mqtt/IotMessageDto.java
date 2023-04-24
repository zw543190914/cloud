package com.zw.cloud.tools.entity.mqtt;

import com.zw.cloud.common.utils.bean.annotation.CopyField;
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
public class IotMessageDto implements Serializable {
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  数据上报时间
     */
    @CopyField(targetFieldName = "rtime")
    private Long rtime;
    /**
     * 表示设备发送数据的时间戳，单位(秒），必填  采集时间
     */
    @CopyField(targetFieldName = "ctime")
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
     * 车速设定值
     */
    @CopyField(targetFieldName = "speedSetting")
    private BigDecimal d01;
    /**
     * 车速
     */
    @CopyField(targetFieldName = "speed")
    private BigDecimal d02;
    /**
     * 进布张力
     */
    @CopyField(targetFieldName = "inClothTension")
    private BigDecimal d03;
    /**
     * 轧车张力
     */
    @CopyField(targetFieldName = "outClothTension")
    private BigDecimal d04;
    /**
     * 轧车液位
     */
    @CopyField(targetFieldName = "rollingCarLiquidLevel")
    private BigDecimal d05;
    /**
     * 烘房设定温度1
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp1")
    private BigDecimal d06;
    /**
     * 烘房实际温度1
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp1")
    private BigDecimal d07;
    /**
     * 烘房设定温度2
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp2")
    private BigDecimal d08;
    /**
     * 烘房实际温度2
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp2")
    private BigDecimal d09;
    /**
     * 烘房设定温度3
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp3")
    private BigDecimal d10;
    /**
     * 烘房实际温度3
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp3")
    private BigDecimal d11;
    /**
     * 烘房设定温度4
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp4")
    private BigDecimal d12;
    /**
     * 烘房实际温度4
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp4")
    private BigDecimal d13;
    /**
     * 烘房设定温度5
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp5")
    private BigDecimal d14;
    /**
     * 烘房实际温度5
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp5")
    private BigDecimal d15;
    /**
     * 烘房设定温度6
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp6")
    private BigDecimal d16;
    /**
     * 烘房实际温度6
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp6")
    private BigDecimal d17;
    /**
     * 烘房设定温度7
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp7")
    private BigDecimal d18;
    /**
     * 烘房实际温度7
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp7")
    private BigDecimal d19;
    /**
     * 烘房设定温度8
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp8")
    private BigDecimal d20;
    /**
     * 烘房实际温度8
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp8")
    private BigDecimal d21;
    /**
     * 烘房设定温度9
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp9")
    private BigDecimal d22;
    /**
     * 烘房实际温度9
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp9")
    private BigDecimal d23;
    /**
     * 总幅
     */
    @CopyField(targetFieldName = "totalAmplitude")
    private BigDecimal d24;
    /**
     * 烘房门幅
     */
    @CopyField(targetFieldName = "dryingRoomDoorWidth")
    private BigDecimal d25;
    /**
     * 上超喂
     */
    @CopyField(targetFieldName = "topFeed")
    private BigDecimal d26;
    /**
     * 下超喂
     */
    @CopyField(targetFieldName = "lowerFeed")
    private BigDecimal d27;
    /**
     * 左毛刷超喂
     */
    @CopyField(targetFieldName = "leftBrushFeed")
    private BigDecimal d28;
    /**
     * 右毛刷超喂
     */
    @CopyField(targetFieldName = "rightBrushFeed")
    private BigDecimal d29;
    /**
     * 进布超喂
     */
    @CopyField(targetFieldName = "inClothFeed")
    private BigDecimal d30;
    /**
     * 冷水辊超喂
     */
    @CopyField(targetFieldName = "outClothFeed")
    private BigDecimal d31;
    /**
     * 落布超喂
     */
    @CopyField(targetFieldName = "fallingFeed")
    private BigDecimal d32;
    /**
     * 摆布超喂
     */
    @CopyField(targetFieldName = "swingFeed")
    private BigDecimal d33;
    /**
     * 排风设定转速1
     */
    @CopyField(targetFieldName = "speciWindSpeed1")
    private BigDecimal d34;
    /**
     * 排风转速1
     */
    @CopyField(targetFieldName = "windSpeed1")
    private BigDecimal d35;
    /**
     * 排风设定转速2
     */
    @CopyField(targetFieldName = "speciWindSpeed2")
    private BigDecimal d36;
    /**
     * 排风转速2
     */
    @CopyField(targetFieldName = "windSpeed2")
    private BigDecimal d37;
    /**
     * 排风设定转速3
     */
    @CopyField(targetFieldName = "speciWindSpeed3")
    private BigDecimal d38;
    /**
     * 排风转速3
     */
    @CopyField(targetFieldName = "windSpeed3")
    private BigDecimal d39;
    /**
     * 排风设定转速4
     */
    @CopyField(targetFieldName = "speciWindSpeed4")
    private BigDecimal d40;
    /**
     * 排风转速4
     */
    @CopyField(targetFieldName = "windSpeed4")
    private BigDecimal d41;
    /**
     * 循环风设定转速1
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed1")
    private BigDecimal d42;
    /**
     * 循环风转速1
     */
    @CopyField(targetFieldName = "cycleWindSpeed1")
    private BigDecimal d43;
    /**
     * 循环风设定转速2
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed2")
    private BigDecimal d44;
    /**
     * 循环风转速2
     */
    @CopyField(targetFieldName = "cycleWindSpeed2")
    private BigDecimal d45;
    /**
     * 循环风设定转速3
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed3")
    private BigDecimal d46;
    /**
     * 循环风转速3
     */
    @CopyField(targetFieldName = "cycleWindSpeed3")
    private BigDecimal d47;
    /**
     * 循环风设定转速4
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed4")
    private BigDecimal d48;
    /**
     * 循环风转速4
     */
    @CopyField(targetFieldName = "cycleWindSpeed4")
    private BigDecimal d49;
    /**
     * 循环风设定转速5
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed5")
    private BigDecimal d50;
    /**
     * 循环风转速5
     */
    @CopyField(targetFieldName = "cycleWindSpeed5")
    private BigDecimal d51;
    /**
     * 循环风设定转速6
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed6")
    private BigDecimal d52;
    /**
     * 循环风转速6
     */
    @CopyField(targetFieldName = "cycleWindSpeed6")
    private BigDecimal d53;
    /**
     * 循环风设定转速7
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed7")
    private BigDecimal d54;
    /**
     * 循环风转速7
     */
    @CopyField(targetFieldName = "cycleWindSpeed7")
    private BigDecimal d55;
    /**
     * 循环风设定转速8
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed8")
    private BigDecimal d56;
    /**
     * 循环风转速8
     */
    @CopyField(targetFieldName = "cycleWindSpeed8")
    private BigDecimal d57;
    /**
     * 循环风设定转速9
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed9")
    private BigDecimal d58;
    /**
     * 循环风转速9
     */
    @CopyField(targetFieldName = "cycleWindSpeed9")
    private BigDecimal d59;
    /**
     * 料槽温度
     */
    @CopyField(targetFieldName = "troughTemp")
    private BigDecimal d60;
    /**
     * 启停、急停信号
     */
    @CopyField(targetFieldName = "stopSignal")
    private Integer d61;
    /**
     * 允许开机信号
     */
    @CopyField(targetFieldName = "allowStartSignal")
    private Integer d62;
    /**
     * 局部设备开机状态
     */
    @CopyField(targetFieldName = "partDeviceStartStatus")
    private Integer d63;
    /**
     * 报警信号
     */
    @CopyField(targetFieldName = "warnSignal")
    private Integer d64;
    /**
     * 排风设定转速5
     */
    @CopyField(targetFieldName = "speciWindSpeed5")
    private BigDecimal d65;
    /**
     * 排风转速5
     */
    @CopyField(targetFieldName = "windSpeed5")
    private BigDecimal d66;
    /**
     * 排风设定转速6
     */
    @CopyField(targetFieldName = "speciWindSpeed6")
    private BigDecimal d67;
    /**
     * 排风转速6
     */
    @CopyField(targetFieldName = "windSpeed6")
    private BigDecimal d68;
    /**
     * 烘房设定温度10
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp10")
    private BigDecimal d69;
    /**
     * 烘房实际温度10
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp10")
    private BigDecimal d70;
    /**
     * 循环风设定转速10
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed10")
    private BigDecimal d71;
    /**
     * 循环风转速10
     */
    @CopyField(targetFieldName = "cycleWindSpeed10")
    private BigDecimal d72;
    /**
     * 烘房设定温度11
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp11")
    private BigDecimal d73;
    /**
     * 烘房实际温度11
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp11")
    private BigDecimal d74;
    /**
     * 烘房设定温度12
     */
    @CopyField(targetFieldName = "dryingRoomPresetTemp12")
    private BigDecimal d75;
    /**
     * 烘房实际温度12
     */
    @CopyField(targetFieldName = "dryingRoomActualTemp12")
    private BigDecimal d76;
    /**
     * 排风设定转速7
     */
    @CopyField(targetFieldName = "speciWindSpeed7")
    private BigDecimal d77;
    /**
     * 排风转速7
     */
    @CopyField(targetFieldName = "windSpeed7")
    private BigDecimal d78;
    /**
     * 循环风设定转速11
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed11")
    private BigDecimal d79;
    /**
     * 循环风转速11
     */
    @CopyField(targetFieldName = "cycleWindSpeed11")
    private BigDecimal d80;
    /**
     * 循环风设定转速12
     */
    @CopyField(targetFieldName = "speciCycleWindSpeed12")
    private BigDecimal d81;
    /**
     * 循环风转速12
     */
    @CopyField(targetFieldName = "cycleWindSpeed12")
    private BigDecimal d82;

    /**
     * 协议版本号
     */
    @CopyField(targetFieldName = "version")
    private BigDecimal d150;

    /**
     * 一键设定循环风
     */
    @CopyField(targetFieldName = "speciCycleWindSpeedSetting")
    private BigDecimal d300;
    /**
     * 排风设定转速8
     */
    @CopyField(targetFieldName = "speciWindSpeed8")
    private BigDecimal d400;
    /**
     * 排风转速8
     */
    @CopyField(targetFieldName = "windSpeed8")
    private BigDecimal d401;
    /**
     * 上针超喂
     */
    @CopyField(targetFieldName = "needleOverfeeding")
    private BigDecimal d500;
    /**
     * 出布辊超喂
     */
    @CopyField(targetFieldName = "overfeedOfClothDeliveryRoller")
    private BigDecimal d501;
    /**
     * 引布带超喂
     */
    @CopyField(targetFieldName = "clothGuideBeltOverfeed")
    private BigDecimal d502;

    /**
     * 所有门幅设定
     */
    @CopyField(targetFieldName = "allDoorWidthSetting")
    private BigDecimal d600;
    /**
     * 总门幅设定
     */
    @CopyField(targetFieldName = "totalDoorWidthSetting")
    private BigDecimal d601;
    /**
     * 前门幅设定
     */
    @CopyField(targetFieldName = "frontDoorWidthSetting")
    private BigDecimal d602;
    /**
     * 前门幅实际
     */
    @CopyField(targetFieldName = "frontDoorWidthActual")
    private BigDecimal d603;
    /**
     * 后门幅设定
     */
    @CopyField(targetFieldName = "rearDoorWidthSetting")
    private BigDecimal d604;
    /**
     * 后门幅实际
     */
    @CopyField(targetFieldName = "rearDoorWidthActual")
    private BigDecimal d605;
    /**
     * 门幅1设定
     */
    @CopyField(targetFieldName = "doorWidthSetting1")
    private BigDecimal d606;
    /**
     * 门幅1实际
     */
    @CopyField(targetFieldName = "doorWidthActual1")
    private BigDecimal d607;
    /**
     * 门幅2设定
     */
    @CopyField(targetFieldName = "doorWidthSetting2")
    private BigDecimal d608;
    /**
     * 门幅2实际
     */
    @CopyField(targetFieldName = "doorWidthActual2")
    private BigDecimal d609;
    /**
     * 门幅3设定
     */
    @CopyField(targetFieldName = "doorWidthSetting3")
    private BigDecimal d610;
    /**
     * 门幅3实际
     */
    @CopyField(targetFieldName = "doorWidthActual3")
    private BigDecimal d611;
    /**
     * 门幅4设定
     */
    @CopyField(targetFieldName = "doorWidthSetting4")
    private BigDecimal d612;
    /**
     * 门幅4实际
     */
    @CopyField(targetFieldName = "doorWidthActual4")
    private BigDecimal d613;
    /**
     * 门幅5设定
     */
    @CopyField(targetFieldName = "doorWidthSetting5")
    private BigDecimal d614;
    /**
     * 门幅5实际
     */
    @CopyField(targetFieldName = "doorWidthActual5")
    private BigDecimal d615;
    /**
     * 门幅6设定
     */
    @CopyField(targetFieldName = "doorWidthSetting6")
    private BigDecimal d616;
    /**
     * 门幅6实际
     */
    @CopyField(targetFieldName = "doorWidthActual6")
    private BigDecimal d617;
    /**
     * 门幅7设定
     */
    @CopyField(targetFieldName = "doorWidthSetting7")
    private BigDecimal d618;
    /**
     * 门幅7实际
     */
    @CopyField(targetFieldName = "doorWidthActual7")
    private BigDecimal d619;

    /**
     * 门幅8设定
     */
    @CopyField(targetFieldName = "doorWidthSetting8")
    private BigDecimal d620;
    /**
     * 门幅8实际
     */
    @CopyField(targetFieldName = "doorWidthActual8")
    private BigDecimal d621;
    /**
     * 门幅9设定
     */
    @CopyField(targetFieldName = "doorWidthSetting9")
    private BigDecimal d622;
    /**
     * 门幅9实际
     */
    @CopyField(targetFieldName = "doorWidthActual9")
    private BigDecimal d623;
    /**
     * 门幅10设定
     */
    @CopyField(targetFieldName = "doorWidthSetting10")
    private BigDecimal d624;
    /**
     * 门幅10实际
     */
    @CopyField(targetFieldName = "doorWidthActual10")
    private BigDecimal d625;
    /**
     * 门幅11设定
     */
    @CopyField(targetFieldName = "doorWidthSetting11")
    private BigDecimal d626;
    /**
     * 门幅11实际
     */
    @CopyField(targetFieldName = "doorWidthActual11")
    private BigDecimal d627;
    /**
     * 门幅12设定
     */
    @CopyField(targetFieldName = "doorWidthSetting12")
    private BigDecimal d628;
    /**
     * 门幅12实际
     */
    @CopyField(targetFieldName = "doorWidthActual12")
    private BigDecimal d629;

    /**
     * 上超速度
     */
    @CopyField(targetFieldName = "upperSuperSpeed")
    private BigDecimal d700;
    /**
     * 上超超喂
     */
    @CopyField(targetFieldName = "downSuperSpeed")
    private BigDecimal d701;
    /**
     * 左毛刷速度
     */
    @CopyField(targetFieldName = "leftBrushSpeed")
    private BigDecimal d702;
    /**
     * 右毛刷速度
     */
    @CopyField(targetFieldName = "rightBrushSpeed")
    private BigDecimal d703;
    /**
     * 出布速度
     */
    @CopyField(targetFieldName = "publishingSpeed")
    private BigDecimal d704;
    /**
     * 落布速度
     */
    @CopyField(targetFieldName = "droppingSpeed")
    private BigDecimal d705;
    /**
     * 摆布速度
     */
    @CopyField(targetFieldName = "swingSpeed")
    private BigDecimal d706;
    /**
     * 引布带速度
     */
    @CopyField(targetFieldName = "quickTapeSpeed")
    private BigDecimal d707;
    /**
     * 轧车速度
     */
    @CopyField(targetFieldName = "rollingSpeed")
    private BigDecimal d708;
}
