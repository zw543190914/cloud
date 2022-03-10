package com.zw.cloud.tools.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 发货通知单
 * </p>
 *
 * @author zhengwei
 * @since 2022-03-09
 */
@Getter
@Setter
public class ShipmentOrderDTO implements Serializable {


    /**
     * 主键
     */
    private Long id;

    /**
     * 发货通知单号
     */
    @NotBlank(message = "发货通知单号为空")
    @Excel(name = "发货通知单号",needMerge = true, orderNum = "0")
    private String shipmentNoticeNo;

    /**
     * 状态（10:新建、20:待发货、30:部分发货、40:已完成、50:已取消）
     */
    @Excel(name = "发货状态",needMerge = true, orderNum = "1", replace = {"新建_10", "待发货_20", "部分发货_30", "已完成_40", "已取消_50"})
    private Integer orderStatus;

    /**
     * 计划发货日期
     */
    @Excel(name = "计划发货日期",needMerge = true, exportFormat = "yyyy-MM-dd", orderNum = "2")
    private LocalDateTime planShipmentDate;

    /**
     * 发货数量
     */
    @Excel(name = "发货数量", needMerge = true,orderNum = "3")
    private BigDecimal shipmentNumber;

    /**
     * 匹数
     */
    @Excel(name = "匹数", needMerge = true,orderNum = "4")
    private BigDecimal horsesNumber;

    /**
     * 已发数量
     */
    @Excel(name = "已发数量", needMerge = true,orderNum = "5")
    private BigDecimal outboundNumber;

    /**
     * 已发匹数
     */
    @Excel(name = "已发匹数",needMerge = true, orderNum = "6")
    private BigDecimal outboundBoltNumber;

    /**
     * 发货地址/交货地址
     */
    @Excel(name = "发货地址", needMerge = true,orderNum = "7")
    private String shipmentAddress;

    /**
     * 通知下发时间
     */
    @Excel(name = "通知下发时间", orderNum = "8",needMerge = true,exportFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime noticeIssuingTime;

    /**
     * 确认发货单时间
     */
    @Excel(name = "确认发货单时间", orderNum = "9",needMerge = true,exportFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime confirmShipmentTime;

    /**
     * 是否已确认（0:后,1:是）
     */
    private Integer isConfirmed;

    /**
     * 确认人
     */
    @Excel(name = "确认人",needMerge = true, orderNum = "10")
    private String confirmor;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 是否最新版本(0:否，1:是)
     */
    private Integer lastVersion;


    /**
     * 备注
     */
    private String remark;

    /**
     * 出单人
     */
    private String issuer;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 甲方负责人
     */
    private String principalA;

    /**
     * 甲方公司名称
     */
    private String partA;

    /**
     * 乙方负责人
     */
    private String principalB;

    /**
     * 乙方公司名称
     */
    private String partB;

    /**
     * 业务方orgCode
     */
    @NotBlank
    private String bizOrgCode;

    /**
     * 业务方名称
     */
    @NotBlank
    private String bizName;

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

    /**
     * 发货通知单明细
     */
    @NotEmpty(message = "发货通知单明细为空")
    @Valid
    @ExcelCollection(id = "itmes", name = "", orderNum = "11")
    private List<ShipmentItemOrderDTO> shipmentItemOrderDTOList;
}
