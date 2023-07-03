package com.zw.cloud.mybatis.plus.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.zw.cloud.mybatis.plus.db.typehandlers.JsonTypeHandler;
import lombok.Data;

/**
 * <p>
 * 生产记录表
 * </p>
 *
 * @author zw
 * @since 2022-09-29
 */
@Data
@TableName(value = "product_record", autoResultMap = true)
public class ProductRecord implements Serializable {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 历史生产信息表id
     */
    private Long productInfoId;

    /**
     * 机构编号
     */
    @TableField(fill = FieldFill.INSERT)
    private String orgCode;

    /**
     * 设备表id
     */
    private Long deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 流转卡号
     */
    private String productCardCode;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 客户id
     */
    private String customerNo;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 颜色色号
     */
    private String colorNo;

    /**
     * 颜色名称
     */
    private String color;

    /**
     * 坯布编号
     */
    private String fabricNo;

    /**
     * 坯布名称
     */
    private String fabricName;

    /**
     * 坯布批次
     */
    private String batchNo;

    /**
     * 成品门幅
     */
    private String fabricWidth;

    /**
     * 白坯克重
     */
    private BigDecimal gramWeight;

    /**
     * 成品克重
     */
    private Integer gramHeft;


    /**
     * 纬密
     */
    private String weftDensity;

    /**
     * 计划长度(米数)
     */
    private BigDecimal planMeters;

    /**
     * 疵点
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object defect;

    /**
     * 出疵匹数
     */
    private Integer defectsNumber;

    /**
     * 严重程度
     */
    private String severity;

    /**
     * 处理结果：1-拉掉 2-放行 3-检验
     */
    private String result;

    /**
     * 定后布车号
     */
    private String carNumber;

    /**
     * 上机门幅
     */
    private Double upperDoorWidth;

    /**
     * 下机门幅
     */
    private Double lowerDoorWidth;

    /**
     * 扫描时间
     */
    private LocalDateTime scanTime;

    /**
     * 前车状态(0.待办/待排产 1.已处理/生产中 2.已完成)
     */
    private String preStatus;

    /**
     * 后车状态(0.待办 1.已处理)
     */
    private String afterStatus;

    /**
     * 处理时间
     */
    private LocalDateTime handleTime;

    /**
     * 保存时间
     */
    private LocalDateTime saveTime;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 数据来源(0.扫码 1.排产 2.扫描转排产)
     */
    private Integer source;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建用户
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建系统
     */
    @TableField(fill = FieldFill.INSERT)
    private String createSystem;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改用户
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * 修改系统
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateSystem;

    /**
     * 是否删除：0未删除，1已删除
     */
    private Integer isDeleted;

    /**
     * 配桶数量
     */
    private BigDecimal ptQuantity;
    /**
     * 合缸生产批号
     */
    private Long productNo;

    /**
     * 合缸生产序号
     */
    private Long productSort;


    /**
     * 业务员
     */
    private String employeeName;

    /**
     * 加价要求
     * 加价工艺
     */
    private String markupCraft;

    /**
     * 班组
     */
    private String teamGroup;

    /**
     * 平均车速
     */
    private BigDecimal avgCarSpeed;

    /**
     * 平均温度
     */
    private BigDecimal avgTemperature;

    /**
     * 平均风速
     */
    private BigDecimal avgWindSpeed;

    /**
     * 平均上超喂
     */
    private BigDecimal avgTopFeed;

    /**
     * 染色车间
     * 车间名称
     */
    private String deptName;

    /**
     * 助剂(中控结束时助剂信息)
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object assistant;

    /**
     * 排班日期
     */
    private LocalDate scheduleDate;

    /**
     * 班组id
     */
    private Long teamGroupId;

    /**
     * 班组名称
     */
    private String teamGroupName;

    /**
     * 班次ID
     */
    private Long teamShiftId;

    /**
     * 班次名称
     */
    private String teamShiftName;

    /**
     * 车间id
     */
    private Long workshopId;

    /**
     * 车间名称
     */
    private String workshopName;

    /**
     * 班次开始时间
     */
    private LocalDateTime scheduleStartTime;

    /**
     * 班次结束时间
     */
    private LocalDateTime scheduleEndTime;

    /**
     * 统计日期，格式 yyyy-MM-dd
     */
    private LocalDate calcDay;

    /**
     * 工序类型
     */
    private Long craftType;

    /**
     * 待生产列表排序值
     */
    private Integer preSort;
    /**
     * 定前布车号
     */
    private String preCarNumber;

    /**
     * 规格
     */
    private String specification;

    /**
     * 风格
     */
    private String style;

    /**
     * 操作人信息
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object operatorInfo;

    /**
     * 中车操作员
     */
    private String preOperator;

    /**
     * 前车操作员中文名称
     */
    private String frontOperator;

    /**
     * 中车开始时间
     */
    private LocalDateTime startTime;

    /**
     * 中车结束时间
     */
    private LocalDateTime endTime;

    /**
     * 实际长度
     */
    private BigDecimal actualMeters;

    /**
     * 实际匹数
     */
    private String matches;

    /**
     * 预计执行时长
     */
    private String expectedTime;

    /**
     * 实际执行时长
     */
    private String actualTime;

    /**
     * 异常类型：0-正常数据;1-质量异常;2-操作异常;3-质量异常和操作异常
     */
    private Integer exceptionType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 成品克重最低
     */
    private String productWeightMin;
    /**
     * 成品克重最高
     */
    private String productWeightMax;

    /**
     * 是否批量排产：0否，1是
     */
    private Integer isBatch;

    /**
     * 工序名称
     */
    private String processName;

    /**
     * 质量状态：不合格，合格
     */
    private String qualityStatus;

    /**
     * 排产导入(0:否,1:是)
     */
    private Integer scheduleImport;

    /**
     * 工序code
     */
    private String processCode;

    /**
     * 数据来源平台(0:定型排产,1:aps排产)
     */
    private Integer platform;
    /**
     * 客户品名
     */
    private String productCode;
    /**
     * 任务号
     */
    private String taskNo;

    /**
     * 助剂异常类型，0-正常，1-异常
     */
    private Integer assistantExceptionType;

    /**
     * 客户要求
     */
    private String customerRequest;

    /**
     * 定型要求
     */
    private String stereotypeRequirement;

    /**
     * 工艺匹配度
     */
    private BigDecimal craftSuitability;

    /**
     * 缩率
     */
    private BigDecimal shrinkage;

    /**
     * 工艺异常类型，0-正常，1-异常
     */
    private Integer craftExceptionType;

    /**
     * 成品布克重(成品克重)
     */
    private String productWeight;

    /**
     * 厚度
     */
    private String thickness;

    /**
     * 是否缺少工艺;1-是,0-否
     */
    private Integer isHaveCraft;

    /**
     * 是否缺少工艺;1-是,0-否
     */
    @TableField(exist = false)
    private String isHaveCraftStr;

    /**
     * 报工标识(0:历史数据10: 不可报工,20:未报工,30:已报工)
     */
    private Integer processReportType;
}
