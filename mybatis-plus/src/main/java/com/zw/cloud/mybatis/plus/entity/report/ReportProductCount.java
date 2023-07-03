package com.zw.cloud.mybatis.plus.entity.report;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.zw.cloud.mybatis.plus.db.typehandlers.JsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 定型产量统计报表
 * </p>
 *
 * @author zw
 * @since 2023-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "report_product_count",autoResultMap = true)
public class ReportProductCount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 工厂code
     */
    @TableField(fill = FieldFill.INSERT)
    private String orgCode;

    /**
     * 设备id
     */
    private Long deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 车间id
     */
    private Long workshopId;

    /**
     * 统计日期，格式 yyyy-MM-dd
     */
    private LocalDate calcDay;

    /**
     * 产量
     */
    private BigDecimal productQuantity;

    /**
     * 生产记录数
     */
    private Integer productNum;

    /**
     * 白班产量
     */
    private BigDecimal whiteProductQuantity;

    /**
     * 白班生产记录数
     */
    private Integer whiteProductNum;

    /**
     * 白班产量占比
     */
    private BigDecimal whiteProductQuantityRate;

    /**
     * 晚班产量
     */
    private BigDecimal blackProductQuantity;

    /**
     * 晚班生产记录数
     */
    private Integer blackProductNum;

    /**
     * 晚班产量占比
     */
    private BigDecimal blackProductQuantityRate;

    /**
     * 按工序统计的生产信息
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object craftProductInfo;

    /**
     * 按产量等级统计的生产信息
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object productLevelInfo;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建用户
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 修改用户
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * 创建系统
     */
    @TableField(fill = FieldFill.INSERT)
    private String createSystem;

    /**
     * 更新系统
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateSystem;


}
