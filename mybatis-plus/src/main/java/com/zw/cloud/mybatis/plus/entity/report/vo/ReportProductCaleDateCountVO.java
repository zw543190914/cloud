package com.zw.cloud.mybatis.plus.entity.report.vo;


import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportProductCountDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ReportProductCaleDateCountVO implements Serializable {
    /**
     * 统计日期
     */
    private String caleDateStr;
    /**
     * 产量统计数据
     */
    private List<ProductReportProductCountDTO> reportProductCountDTOList;
}
