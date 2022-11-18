package com.zw.cloud.mybatis.plus.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.zw.cloud.mybatis.plus.db.typehandlers.JsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 定型工艺
 * </p>
 *
 * @author zw
 * @since 2022-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "base_tender_craft", autoResultMap = true)
public class BaseTenderCraft implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

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
     * 颜色编号
     */
    private String colorNo;

    /**
     * 颜色名称
     */
    private String colorName;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 上超喂
     */
    private BigDecimal topFeed;

    /**
     * 下超喂
     */
    private BigDecimal lowerFeed;

    /**
     * 毛刷超喂
     */
    private BigDecimal brushFeed;

    /**
     * 车速
     */
    private BigDecimal speed;

    /**
     * 落布超喂
     */
    private BigDecimal fallingFeed;

    /**
     * 冷水辊超喂
     */
    private BigDecimal outClothFeed;

    /**
     * 排风设定转速
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object speciWindSpeed;

    /**
     * 循环风设定转速
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object speciCycleWindSpeed;

    /**
     * 烘箱设定温度
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object dryingRoomPresetTemp;

    /**
     * 助剂(防水类、软剂类、其他)
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object assistant;

    /**
     * 工厂id
     */
    @TableField(fill = FieldFill.INSERT)
    private String orgCode;

    /**
     * 白坯克重
     */
    private BigDecimal gramWeight;

    /**
     * 纬密
     */
    private String weftDensity;

    /**
     * 总门幅
     */
    private String totalAmplitude;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建系统
     */
    private String createSystem;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改系统
     */
    private String updateSystem;

    /**
     * 是否删除：0未删除，1已删除
     */
    private Integer isDeleted;

    /**
     * 创建用户名称
     */
    private String createUserName;

    /**
     * 配桶数量
     */
    private BigDecimal ptQuantity;

    /**
     * 工序类型
     */
    private Long craftType;

    /**
     * 规格
     */
    private String specification;

    /**
     * 风格
     */
    private String style;

    /**
     * 风机转速
     */
    private BigDecimal fanSpeed;

    /**
     * 烘箱温度
     */
    private BigDecimal dryingRoomTemp;

    /**
     * 排风转速
     */
    private BigDecimal exhaustSpeed;


    /**
     * 轧车压力(0-50)
     */
    private BigDecimal rollingPressure;

    /**
     * 扭度
     */
    private BigDecimal torsion;

    /**
     * 克重
     */
    private String scheduleGramHeft;


    /**
     * 纬密
     */
    private String scheduleWeftDensity;


    /**
     * 弹力
     */
    private String scheduleElasticity;

    /**
     * 设备id,支持多条
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private /*TODO*/ Object deviceIds;

    /**
     * 设备id
     */
    @TableField(exist = false)
    private Long deviceId;

    /**
     * 设备名字
     */

    private String deviceName;
    /**
     * 动态字段
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object dynamicField;

    /**
     * 工艺类型
     */
    private Long craftMold;

    /**
     * 定型要求
     */
    private String stereotypeRequirement;

    /**
     * 客户要求
     */
    @TableField(exist = false)
    private String customerRequest;

    /**
     * 工艺来源(0-标准工艺,1-算法工艺)
     */
    private Integer craftSource;

    /**
     * 生产记录表id(base_product_record)
     */
    private Long productRecordId;

    /**
     * 门幅JSON
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object doorWidthActual;
    /**
     * 成品布克重(成品克重)
     */
    private String productWeight;

    /**
     * 缩率
     */
    private BigDecimal shrinkage;

    /**
     * 厚度
     */
    private String thickness;

    /**
     * 中车操作员
     */
    private String preOperator;

    /**
     * 设备品牌型号
     */
    private String brand;
}
