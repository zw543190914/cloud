package com.zw.cloud.mybatis.plus.entity;

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
 * 定型生产力系数
 * </p>
 *
 * @author zw
 * @since 2023-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "productivity_coefficient", autoResultMap = true)
public class ProductivityCoefficient implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 统计日期
     */
    private LocalDate statisticalDate;

    /**
     * 生产记录数量
     */
    private Integer totalProductRecord;

    /**
     * 生产力系数
     */
    private BigDecimal productivityCoefficient;

    /**
     * 有工艺的数量占比
     */
    private BigDecimal hasCraftRate;

    /**
     * 定型生产整体匹配度
     */
    private BigDecimal craftSuitabilityRate;

    /**
     * 时长可控范围内的数量占比
     */
    private BigDecimal controllableTime;

    /**
     * 定型数量回修率
     */
    private BigDecimal repairRate;

    /**
     * 无工艺占比
     */
    private BigDecimal notHasCraftRate;

    /**
     * 有匹配度占比
     */
    private BigDecimal hasCraftSuitabilityRate;

    /**
     * 无匹配度占比
     */
    private BigDecimal notHasCraftSuitability;

    /**
     * 匹配度大于标准占比
     */
    private BigDecimal craftSuitabilityGreaterThanStandardRate;

    /**
     * 匹配度大于整体占比
     */
    private BigDecimal craftSuitabilityGreaterThanOverallRate;

    /**
     * 匹配度小于标准占比
     */
    private BigDecimal craftSuitabilityLessThanStandardRate;

    /**
     * 匹配度小于整体占比
     */
    private BigDecimal craftSuitabilityLessThanOverallRate;

    /**
     * 时长正常占比
     */
    private BigDecimal durationNormalRate;

    /**
     * 时长异常占比
     */
    private BigDecimal durationAbnormalRate;

    /**
     * 无时长占比
     */
    private BigDecimal noTimeRate;

    /**
     * 工艺参数差异分布
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object craftDifferentialDistribution;

    /**
     * 系统操作不规范人员分布
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object nonStandardOperationsDistribution;

    /**
     * 机构编号
     */
    @TableField(fill = FieldFill.INSERT)
    private String orgCode;

    /**
     * 是否已经删除，0未删除,1已删除
     */
    private Integer isDeleted;

    /**
     * 创建用户
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建系统
     */
    @TableField(fill = FieldFill.INSERT)
    private String createSystem;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新用户
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * 修改系统
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateSystem;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
