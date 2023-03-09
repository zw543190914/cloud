package com.zw.cloud.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ProductRecord implements Serializable {
    private Long id;

    private Long productInfoId;

    private String orgCode;

    private Long deviceId;

    private String deviceName;

    private String productCardCode;

    private String orderId;

    private String customerNo;

    private String customerName;

    private String colorNo;

    private String color;

    private String fabricNo;

    private String fabricName;

    private String batchNo;

    private String fabricWidth;

    private Long gramWeight;

    private Integer gramHeft;

    private String weftDensity;

    private BigDecimal planMeters;

    private Integer defectsNumber;

    private String severity;

    private String result;

    private String carNumber;

    private Double upperDoorWidth;

    private Double lowerDoorWidth;

    private Date scanTime;

    private String preStatus;

    private String afterStatus;

    private Date handleTime;

    private Date saveTime;

    private String operator;

    private LocalDateTime createTime;

    private String createUser;

    private String createSystem;

    private LocalDateTime updateTime;

    private String updateUser;

    private String updateSystem;

    private Byte isDeleted;

    private Byte source;

    private Integer sort;

    private BigDecimal ptQuantity;

    private Long productNo;

    private Long productSort;

    private String employeeName;

    private String markupCraft;

    private String teamGroup;

    private BigDecimal avgCarSpeed;

    private BigDecimal avgTemperature;

    private BigDecimal avgWindSpeed;

    private BigDecimal avgTopFeed;

    private String deptName;

    private Date scheduleDate;

    private Long teamGroupId;

    private String teamGroupName;

    private Long teamShiftId;

    private String teamShiftName;

    private Long workshopId;

    private String workshopName;

    private Date scheduleStartTime;

    private Date scheduleEndTime;

    private Date calcDay;

    private String preCarNumber;

    private Long craftType;

    private Integer preSort;

    private String specification;

    private String style;

    private String preOperator;

    private Date startTime;

    private Date endTime;

    private String frontOperator;

    private BigDecimal actualMeters;

    private String matches;

    private String expectedTime;

    private String actualTime;

    private Integer exceptionType;

    private String productWeightMin;

    private String productWeightMax;

    private String remark;

    private Byte isBatch;

    private String processName;

    private Byte scheduleImport;

    private String qualityStatus;

    private String processCode;

    private String productCode;

    private Integer platform;

    private String taskNo;

    private Integer assistantExceptionType;

    private String customerRequest;

    private String stereotypeRequirement;

    private BigDecimal craftSuitability;

    private BigDecimal shrinkage;

    private Integer craftExceptionType;

    private Byte isHaveCraft;

    private String productWeight;

    private String thickness;

    private Byte processReportType;

    private Object defect;

    private Object assistant;

    private Object operatorInfo;

    private static final long serialVersionUID = 1L;

}