package com.zw.cloud.tools.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.zw.cloud.tools.bindcheckgroup.InsertCheckGroup;
import com.zw.cloud.tools.bindcheckgroup.UpdateCheckGroup;
import com.zw.cloud.tools.excel.converter.LocalDateTimeConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@HeadRowHeight(value = 20)
@ColumnWidth(value = 20)
@EqualsAndHashCode
public class User extends Base implements Serializable {
    @NotNull(message = "id is null",groups = {UpdateCheckGroup.class})
    @ExcelProperty(value = "id",index = 0)
    private Long id;

    @NotBlank(message = "name is empty",groups = {InsertCheckGroup.class,UpdateCheckGroup.class})
    @ExcelProperty(value = "姓名",index = 1)
    private String name;
    @ExcelProperty(value = "年龄",index = 2)
    private Byte age;
    @ExcelProperty(value = "生日",index = 3,converter = LocalDateTimeConverter.class)
    private LocalDateTime bir;
    @ExcelProperty(value = "创建时间",index = 4,converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    @ExcelIgnore
    private Date updateTime;
    @ExcelIgnore
    private Integer deleted;
    @ExcelIgnore
    private String description;

}

class Base {
    @ExcelIgnore
    private Long baseId;
}