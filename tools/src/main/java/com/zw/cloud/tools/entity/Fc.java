package com.zw.cloud.tools.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fc implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ExcelIgnore
    private LocalDateTime updateTime;


}
