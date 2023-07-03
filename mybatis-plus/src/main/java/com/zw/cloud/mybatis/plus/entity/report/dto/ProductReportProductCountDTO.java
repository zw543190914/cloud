package com.zw.cloud.mybatis.plus.entity.report.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProductReportProductCountDTO implements Serializable {
    /**
     * 设备id
     */
    private Long deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 车间id
     */
    private Long workshopId;

    /**
     * 统计日期，格式 yyyy-MM-dd
     */
    private LocalDate calcDay;

    /**
     * 产量
     */
    private BigDecimal productQuantity;


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

    /**
     * 工序产量统计
     */
    private List<ProductReportCraftCountDTO> craftCountDTOList;
}
