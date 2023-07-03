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
 * 通用生产记录表
 * </p>
 *
 * @author zw
 * @since 2022-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GeneralProductRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 流转卡号
     */
    private String productCardCode;

    /**
     * 设备表id
     */
    private Long deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 工序类型
     */
    private String craftType;

    /**
     * 机构编号
     */
    private String orgCode;

    /**
     * 工艺编码
     */
    private String craftCode;

    /**
     * 工艺名称
     */
    private String craftName;

    /**
     * 客户编号
     */
    private String customerNo;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 颜色色号
     */
    private String colorNo;

    /**
     * 颜色名称
     */
    private String color;

    /**
     * 坯布编号
     */
    private String fabricNo;

    /**
     * 坯布名称
     */
    private String fabricName;

    /**
     * 成品门幅
     */
    private String fabricWidth;

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
     * 客户要求
     */
    private String customerRequest;

    /**
     * 实际匹数
     */
    private String matches;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 前车状态(0.待办/待排产 1.已处理/生产中 2.已完成)
     */
    private Integer preStatus;

    /**
     * 中车开始时间
     */
    private LocalDateTime startTime;

    /**
     * 中车结束时间
     */
    private LocalDateTime endTime;

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
