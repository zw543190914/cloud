package com.zw.cloud.mybatis.plus.entity.report.vo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ReportProductCountVO implements Serializable {
    /**
     * 工序数据
     */
    private List<String> craftNameList;
    /**
     * 产量统计数据
     */
    private List<ReportProductCaleDateCountVO> reportProductCaleDateCountVOList;
}
