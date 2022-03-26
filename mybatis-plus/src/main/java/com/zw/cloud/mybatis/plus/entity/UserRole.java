package com.zw.cloud.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表角色关联表
 * </p>
 *
 * @author zw
 * @since 2022-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 修改用户
     */
    private String updateUser;


}
