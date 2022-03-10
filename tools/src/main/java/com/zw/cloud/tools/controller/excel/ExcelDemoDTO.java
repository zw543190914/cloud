package com.zw.cloud.tools.controller.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ExcelDemoDTO {


    /**
     * 第一列
     */
    @Excel(name = "第一", needMerge = true, orderNum = "1")
    private Integer first;

    /**
     * 第二列
     */
    @Excel(name = "第二", needMerge = true, orderNum = "2")
    private BigDecimal second;

    /**
     * 第三List合并项
     */
    @ExcelCollection(id = "third", name = "", orderNum = "3")
    private List<ThirdChild> third;

    @Data
    @ExcelTarget(value = "third")
    public static class ThirdChild {

        /**
         * 姓名
         */
        @Excel(name = "姓名", needMerge = true, orderNum = "1")
        private String name;

        /**
         * 个数
         */
        @Excel(name = "个数", needMerge = true, orderNum = "2")
        private Integer quantity;

        /**
         * 详情
         */
        @ExcelCollection(id = "details", name = "", orderNum = "4")
        private List<Detail> details;
    }


    @Data
    @ExcelTarget(value = "details")
    public static class Detail {
        /**
         * 个数
         */
        @Excel(name = "个数", needMerge = true, orderNum = "5")
        private Integer quantity;

        /**
         * 分数
         */
        @Excel(name = "分数", needMerge = true, orderNum = "6")
        private BigDecimal fraction;
    }
}
