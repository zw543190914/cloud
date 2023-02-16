package com.zw.cloud.mybatis.plus.entity;

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
 * 生产记录关联表
 * </p>
 *
 * @author zw
 * @since 2023-02-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductRecordDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 生产记录ID
     */
    private Long productRecordId;

    /**
     * 机构编号
     */
    private String orgCode;

    /**
     * 异常更新时间
     */
    private LocalDateTime exceptionUpdateTime;

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
