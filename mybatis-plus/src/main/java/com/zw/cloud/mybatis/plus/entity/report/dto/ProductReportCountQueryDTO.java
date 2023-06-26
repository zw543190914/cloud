package com.zw.cloud.mybatis.plus.entity.report.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ProductReportCountQueryDTO implements Serializable {
    /**
     * 车间id
     */
    @NotNull(message = "车间ID不能为空")
    private Long workshopId;

    /**
     * 时间维度 1:按天 2:按周 3:按月
     */
    @NotNull(message = "时间维度不能为空")
    @Range(max = 3,min = 1)
    private Integer timeType;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    private LocalDate startTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;

    private String orgCode;
}
