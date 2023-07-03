package com.zw.cloud.mybatis.plus.entity.report.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductReportCraftCountDTO implements Serializable {

    /**
     * 工艺id
     */
    private Long craftId;
    /**
     * 产量
     */
    private BigDecimal productQuantity;

    /**
     * 产量占比
     */
    private BigDecimal productQuantityRate;


    /**
     * 生产记录数
     */
    private Integer productNum;

    /**
     * 白班产量
     */
    private BigDecimal whiteProductQuantity;

    /**
     * 白班生产记录数
     */
    private Integer whiteProductNum;

    /**
     * 白班产量占比
     */
    private BigDecimal whiteProductQuantityRate;

    /**
     * 晚班产量
     */
    private BigDecimal blackProductQuantity;

    /**
     * 晚班生产记录数
     */
    private Integer blackProductNum;

    /**
     * 晚班产量占比
     */
    private BigDecimal blackProductQuantityRate;

}
