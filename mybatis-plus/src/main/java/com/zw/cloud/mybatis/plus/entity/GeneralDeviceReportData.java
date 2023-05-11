package com.zw.cloud.mybatis.plus.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 通用设备上报数据
 * </p>
 *
 * @author zw
 * @since 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "general_device_report_data", autoResultMap = true)
public class GeneralDeviceReportData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 采集时间
     */
    private LocalDateTime ctime;

    /**
     * 设定速度
     */
    private BigDecimal speedSetting;

    /**
     * 实际车速
     */
    private BigDecimal speed;

    /**
     * 1#水槽温度实际温度
     */
    private BigDecimal troughActualTemp1;

    /**
     * 1#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp1;

    /**
     * 2#水槽温度实际温度
     */
    private BigDecimal troughActualTemp2;

    /**
     * 2#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp2;

    /**
     * 3#水槽温度实际温度
     */
    private BigDecimal troughActualTemp3;

    /**
     * 3#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp3;

    /**
     * 4#水槽温度实际温度
     */
    private BigDecimal troughActualTemp4;

    /**
     * 4#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp4;

    /**
     * 5#水槽温度实际温度
     */
    private BigDecimal troughActualTemp5;

    /**
     * 5#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp5;

    /**
     * 6#水槽温度实际温度
     */
    private BigDecimal troughActualTemp6;

    /**
     * 6#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp6;

    /**
     * 7#水槽温度实际温度
     */
    private BigDecimal troughActualTemp7;

    /**
     * 7#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp7;

    /**
     * 8#水槽温度实际温度
     */
    private BigDecimal troughActualTemp8;

    /**
     * 8#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp8;

    /**
     * 9#水槽温度实际温度
     */
    private BigDecimal troughActualTemp9;

    /**
     * 9#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp9;

    /**
     * 10#水槽温度实际温度
     */
    private BigDecimal troughActualTemp10;

    /**
     * 10#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp10;

    /**
     * 11#水槽温度实际温度
     */
    private BigDecimal troughActualTemp11;

    /**
     * 11#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp11;

    /**
     * 12#水槽温度实际温度
     */
    private BigDecimal troughActualTemp12;

    /**
     * 12#水槽温度设定温度
     */
    private BigDecimal troughPresetTemp12;

    /**
     * 进布张力实际
     */
    private BigDecimal feedCenteringTensionActual;

    /**
     * 进布张力设定
     */
    private BigDecimal feedCenteringTensionPreset;

    /**
     * 出布张力实际
     */
    private BigDecimal tensionOutlePositionActual;

    /**
     * 出布张力设定
     */
    private BigDecimal tensionOutlePositionPreset;

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
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
