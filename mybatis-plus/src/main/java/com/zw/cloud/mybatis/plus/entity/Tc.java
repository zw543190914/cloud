package com.zw.cloud.mybatis.plus.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * 
 * </p>
 *
 * @author zw
 * @since 2022-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Tc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Excel(name = "期号",needMerge = true, orderNum = "0")
    private Integer id;

    @Excel(name = "one",needMerge = true)
    private Integer one;

    @Excel(name = "two",needMerge = true)
    private Integer two;

    @Excel(name = "three",needMerge = true)
    private Integer three;

    @Excel(name = "four",needMerge = true)
    private Integer four;

    @Excel(name = "five",needMerge = true)
    private Integer five;

    @Excel(name = "six",needMerge = true)
    private Integer six;

    @Excel(name = "seven",needMerge = true)
    private Integer seven;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
