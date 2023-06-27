package com.zw.cloud.mybatis.plus.controller.report;


import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportCountQueryDTO;
import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportCraftCountDTO;
import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportProductCountDTO;
import com.zw.cloud.mybatis.plus.entity.report.vo.ReportProductCaleDateCountVO;
import com.zw.cloud.mybatis.plus.entity.report.vo.ReportProductCountVO;
import com.zw.cloud.mybatis.plus.service.api.report.IReportProductCountService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 定型产量统计报表 前端控制器
 * </p>
 *
 * @author zw
 * @since 2023-06-25
 */
@RestController
@RequestMapping("/report/report-product-count")
public class ReportProductCountController {

    @Resource
    private IReportProductCountService reportProductCountService;

    /**
     * 定型产量统计
     */
    @PostMapping("/queryProductReportCount")
    //http://127.0.0.1:8082/report/report-product-count/queryProductReportCount
    public ReportProductCountVO queryProductReportCount(@Validated @RequestBody ProductReportCountQueryDTO productReportCountQueryDTO) {
        return reportProductCountService.queryProductReportCount(productReportCountQueryDTO);
    }

    @PostMapping("/exportProductReportCount")
    //http://127.0.0.1:8082/report/report-product-count/exportProductReportCount
    public void exportProductReportCount(@Validated @RequestBody ProductReportCountQueryDTO productReportCountQueryDTO, HttpServletResponse response) throws IOException {
        reportProductCountService.exportProductReportCount(productReportCountQueryDTO, response);
    }


}
