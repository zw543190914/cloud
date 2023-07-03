package com.zw.cloud.mybatis.plus.entity.dto;

import cn.hutool.db.Page;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class ProductRecordReportQueryDTO extends Page {

    /**
     * 机构编号
     */
    private String orgCode;

    /**
     * 流转卡号
     */
    private String productCardCode;

    /**
     * 客户名称
     */
    private List<String> customerName;

    /**
     * 坯布名称
     */
    private List<String> fabricName;

    /**
     * 坯布编码
     */
    private List<String> fabricNo;

    /**
     * 车号
     */
    private List<String> carNumber;

    /**
     * 机台
     */
    private List<Long> deviceIdList;

    /**
     * 操作员
     */
    private List<String> operator;

    /**
     * 班组
     */
    private List<String> teamGroup;

    /**
     * 助剂
     */
    private List<String> assistant;

    /**
     * 疵点
     */
    private List<String> defect;

    /**
     * 处理结果：1-拉掉 2-放行 3-检验
     */
    private List<String> result;

    /**
     * 加价要求
     */
    private List<String> markupCraft;

    /**
     * 创建类型
     */
    private List<String> sourceList;

    /**
     * 异常类型
     */
    private List<String> exceptionTypeList;

    /**
     * 异常类型转换
     */
    private List<Integer> exceptionConvertList;

    /**
     * 助剂异常类型转换
     */
    List<Integer> convertAssistantList;


    /**
     * 1 exception_type 有异常
     * 2 assistant_exception_type 有助剂异常
     * 3 craft_exception_type 有工艺异常
     */
    private Integer sqlExceptionType;

    /**
     * 工艺异常类型
     */
    private Integer craftExceptionType;

    /**
     * 工序类型
     */
    private List<Long> craftTypeList;

    /**
     * 创建类型转换
     */
    private List<Integer> sourceConvertList;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 历史生产信息表id
     */
    private List<Long> historyProductInfoIdList;
    /**
     * 排序字段
     */
    private String orderByCase;

    private int start;

    /**
     * 中车操作员
     */
    private List<String> preOperator;

    /**
     * 前车操作员
     */
    private List<String> frontOperator;


    /**
     * 车间id
     */
    private Long workshopId;

    private List<Long> idList;

    /**
     * 导出的列
     */
    private List<RowTitleDTO>  rowTitleDTOList;

}
