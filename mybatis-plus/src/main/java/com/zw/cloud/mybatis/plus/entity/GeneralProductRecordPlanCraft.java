package com.zw.cloud.mybatis.plus.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 通用生产记录工艺关联表
 * </p>
 *
 * @author zw
 * @since 2022-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GeneralProductRecordPlanCraft implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 通用生产记录id(general_product_record)
     */
    private Long generalProductRecordId;

    /**
     * 机构编号
     */
    private String orgCode;

    /**
     * 温度
     */
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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 修改系统
     */
    private String updateSystem;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
