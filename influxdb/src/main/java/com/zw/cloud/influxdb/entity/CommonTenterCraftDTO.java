package com.zw.cloud.influxdb.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
public class CommonTenterCraftDTO implements Serializable {


    /**
     * 主键
     */
    private Long id;

    /**
     * 机构编号
     */
    private String orgCode;

    /**
     * 工艺编码
     */
    @NotBlank(message = "工艺编码不能为空")
    private String craftCode;

    /**
     * 工艺名称
     */
    private String craftName;

    /**
     * 工序类型
     */
    @NotBlank(message = "工序类型不能为空")
    private String craftType;

    /**
     * 工序映射id
     */
    @NotNull(message = "工序映射id不能为空")
    private Long craftId;

    /**
     * 设备类型,1-平洗机,2-汽蒸机,3-冷堆机,4-减量机,5-退浆机
     */
    @NotNull(message = "设备类型不能为空")
    private Long deviceType;

    /**
     * 设备类型名称
     */
    private String deviceTypeName;

    /**
     * 适用设备id,支持多条
     */
    @NotNull(message = "适用设备不能为空")
    private /*TODO*/ Object deviceIds;

    /**
     * 适用设备,多个逗号隔开
     */
    private String deviceNames;

    /**
     * 温度
     */
    @NotNull(message = "温度不能为空")
    private BigDecimal temperature;

    /**
     * 车速
     */
    private BigDecimal speed;

    /**
     * 碱浓度
     */
    private BigDecimal alkaliConcentration;

    /**
     * 保温时间
     */
    private BigDecimal holdingTime;

    /**
     * 减量率
     */
    private BigDecimal decrementRate;

    /**
     * 克重差
     */
    private BigDecimal gramWeightDiff;

    /**
     * PH值
     */
    private BigDecimal ph;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否已经删除，0未删除,1已删除
     */
    private Integer isDeleted;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建系统
     */
    private String createSystem;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建时间
     */
    private String createTimeStr;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 更新用户名称
     */
    private String updateUserName;

    /**
     * 修改系统
     */
    private String updateSystem;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新时间
     */
    private String updateTimeStr;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 水槽张力
     */
    @Range(max = 100,min = 0,message = "张力取值范围为0-100闭区间")
    private Integer tension;

    /**
     * 出布轧车压力(0-100)
     */
    @Range(max = 100,min = 0,message = "出布轧车压力取值范围为0-100闭区间")
    private Integer rollingPressure;

    /**
     * 真空频率(0-100)
     */
    @Range(max = 100,min = 0,message = "真空频率取值范围为0-100闭区间")
    private BigDecimal vacuumFrequency;

    /**
     * 助剂
     */
    private Object assistant;

    /**
     * 温度JSON
     */
    private Object temperatureJson;

    /**
     * 水槽张力JSON
     */
    private Object tensionJson;

    /**
     * 转换后的助剂
     */
    private String assistantStr;

    /**
     * 出布张力 0-100闭区间，单位“N”
     */
    private BigDecimal fabricTension;

    /**
         * 进布张力 0-100闭区间，单位“N”
     */
    private BigDecimal feedTension;

    /**
     * 进布轧车压力(0-100)
     */
    @Range(max = 100,min = 0,message = "进布轧车压力取值范围为0-100闭区间")
    private Integer feedRollingPressure;

    /**
     * 轧车压力
     */
    private BigDecimal carTension;

    /**
     * 轧车压力JSON
     */
    private /*TODO*/ Object carTensionJson;

    /**
     * ph值JSON
     */
    private /*TODO*/ Object phJson;

    /**
     * 真空吸水/真空频率JSON
     */
    private /*TODO*/ Object vacuumFrequencyJson;

    /**
     * 补水量
     */
    private BigDecimal supplyWaterQuantity;

    /**
     * 补水量JSON
     */
    private /*TODO*/ Object supplyWaterQuantityJson;

    /**
     * 含水量
     */
    private BigDecimal waterContent;

    /**
     * 含水量JSON
     */
    private /*TODO*/ Object waterContentJson;

    /**
     * 助剂配比
     */
    private BigDecimal assistantRate;

    /**
     * 助剂配比JSON
     */
    private /*TODO*/ Object assistantRateJson;

    /**
     * 客户编号
     */
    private String customerNo;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 坯布名称
     */
    private String fabricName;

    /**
     * 坯布编号
     */
    private String fabricNo;

    /**
     * 色号
     */
    private String colorNo;

    /**
     * 颜色名称
     */
    private String colorName;

    /**
     * 缩率
     */
    private BigDecimal shrinkage;

    /**
     * 色系
     */
    private String colorSystem;

    /**
     * 规格
     */
    private String specification;

    /**
     * 成品门幅
     */
    private String fabricWidth;

    /**
     * 成品布克重(成品克重)
     */
    private String productWeight;

    /**
     * 厚度
     */
    private String thickness;

    /**
     * 工艺来源(0-标准工艺,1-算法工艺)
     */
    private Integer craftSource;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 设备品牌型号
     */
    private String brand;
}
