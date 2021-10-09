package com.zw.cloud.tools.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-08-29 21:02:42
 */
public class User implements Serializable {
    private static final long serialVersionUID = 665809211608969869L;

    @ExcelProperty(value = "id")
    private Long id;

    @ExcelProperty(value = "姓名")
    @NotBlank(message = "name is blank")
    private String name;

    @ExcelProperty(value = "年龄")
    @NotNull(message = "age is null")
    @Range(max = 100,min = 10,message = "age is error")
    private Byte age;

    @ExcelProperty(value = "描述")
    private String description;

    @ExcelProperty(value = "生日")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date bir;

    @ExcelIgnore
    private String image;
    @ExcelIgnore
    private Date gmtCreate;

    @ExcelIgnore
    @Valid //嵌套验证
    @NotNull
    private Tc tc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Tc getTc() {
        return tc;
    }

    public void setTc(Tc tc) {
        this.tc = tc;
    }
}