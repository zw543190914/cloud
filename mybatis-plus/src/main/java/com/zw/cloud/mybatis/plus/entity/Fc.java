package com.zw.cloud.mybatis.plus.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 体彩
 * </p>
 *
 * @author zw
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ExcelProperty(value = "id",index = 0)
    private Integer id;
    @ExcelProperty(value = "one",index = 2)
    private Integer one;
    @ExcelProperty(value = "two",index = 3)
    private Integer two;
    @ExcelProperty(value = "three",index = 4)
    private Integer three;
    @ExcelProperty(value = "four",index = 5)
    private Integer four;
    @ExcelProperty(value = "five",index = 6)
    private Integer five;
    @ExcelProperty(value = "six",index = 7)
    private Integer six;
    @ExcelProperty(value = "seven",index = 8)
    private Integer seven;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ExcelIgnore
    private LocalDateTime updateTime;


}
