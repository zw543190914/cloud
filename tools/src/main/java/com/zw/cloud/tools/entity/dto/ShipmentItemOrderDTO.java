package com.zw.cloud.tools.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 发货通知单明细表
 * </p>
 *
 * @author zhengwei
 * @since 2022-03-09
 */
@Getter
@Setter
@ExcelTarget(value = "items")
public class ShipmentItemOrderDTO implements Serializable {


    /**
     * 主键
     */
    private Long id;

    /**
     * 发货通知单号
     */
    private String shipmentNoticeNo;

    /**
     * 云智造订单号/生产单号
     */
    @NotBlank(message = "云智造订单号/生产单号为空")
    @Excel(name = "云智造订单号", needMerge = true, orderNum = "11")
    private String produceOrderNo;

    /**
     * 发货数量
     */
    @Excel(name = "发货数量", needMerge = true, orderNum = "12")
    private BigDecimal shipmentNumber;

    /**
     * 发货匹数
     */
    private BigDecimal shipmentBolt;

    /**
     * 批号-成品批号
     */
    private String finishedGoodsNo;

    /**
     * 染厂订单号
     */
    private String erpProduceOrderNo;

    /**
     * 单位
     */
    private String unit;

    /**
     * 成品名称/商品名称
     */
    private String goodsName;

    /**
     * 颜色
     */
    private String color;

    /**
     * 色号
     */
    private String colorNo;

    /**
     * 批号
     */
    private String batchNumber;

    /**
     * 原料名称
     */
    private String materialName;

    /**
     * 工厂id
     */
    private String orgCode;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建系统
     */
    private String createSystem;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改用户
     */
    private String updateUser;

    /**
     * 修改系统
     */
    private String updateSystem;

    /**
     * 是否删除：0未删除，1已删除
     */
    private Integer isDeleted;


}
