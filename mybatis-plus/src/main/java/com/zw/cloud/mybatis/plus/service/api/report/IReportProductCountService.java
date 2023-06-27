package com.zw.cloud.mybatis.plus.service.api.report;

import com.zw.cloud.mybatis.plus.entity.report.ReportProductCount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportCountQueryDTO;
import com.zw.cloud.mybatis.plus.entity.report.vo.ReportProductCountVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 定型产量统计报表 服务类
 * </p>
 *
 * @author zw
 * @since 2023-06-25
 */
public interface IReportProductCountService extends IService<ReportProductCount> {

    ReportProductCountVO queryProductReportCount(ProductReportCountQueryDTO productReportCountQueryDTO);

    void exportProductReportCount(ProductReportCountQueryDTO productReportCountQueryDTO, HttpServletResponse response) throws IOException;
}
