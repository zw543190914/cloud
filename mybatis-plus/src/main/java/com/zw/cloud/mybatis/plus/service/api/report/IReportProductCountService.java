package com.zw.cloud.mybatis.plus.service.api.report;

import com.zw.cloud.mybatis.plus.entity.report.ReportProductCount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportCountQueryDTO;
import com.zw.cloud.mybatis.plus.entity.report.vo.ReportProductCountVO;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


/**
 * <p>
 * 定型产量统计报表 服务类
 * </p>
 *
 * @author zw
 * @since 2023-06-25
 */
public interface IReportProductCountService extends IService<ReportProductCount> {

    /**
     * 定型产量统计
     */
    ReportProductCountVO queryProductReportCount(ProductReportCountQueryDTO productReportCountQueryDTO);

    /**
     * 定型产量统计导出
     */
    SXSSFWorkbook exportProductReportCount(ProductReportCountQueryDTO productReportCountQueryDTO);
}
