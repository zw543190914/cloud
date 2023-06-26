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

    @PostMapping("/queryProductReportCount")
    //http://127.0.0.1:8082/report/report-product-count/queryProductReportCount
    public ReportProductCountVO queryProductReportCount(@Validated @RequestBody ProductReportCountQueryDTO productReportCountQueryDTO) {
        return reportProductCountService.queryProductReportCount(productReportCountQueryDTO);
    }

    @PostMapping("/exportProductReportCount")
    //http://127.0.0.1:8082/report/report-product-count/exportProductReportCount
    public void exportProductReportCount(@Validated @RequestBody ProductReportCountQueryDTO productReportCountQueryDTO, HttpServletResponse response) throws IOException {
        ReportProductCountVO productCountVO = reportProductCountService.queryProductReportCount(productReportCountQueryDTO);
        List<String> craftNameList = productCountVO.getCraftNameList();

        //创建HSSFWorkbook对象(excel的文档对象)
        SXSSFWorkbook workbook = new SXSSFWorkbook(200);
        //建立新的sheet对象（excel的表单）
        SXSSFSheet sheet = workbook.createSheet("定型生产记录");

        CellStyle cellStyle = getStyleWithWrapText(workbook);
        int rowNum = 0;
        int titleCell = 0;
        SXSSFRow titleRow = sheet.createRow(rowNum ++);
        SXSSFRow secondTitleRow = sheet.createRow(rowNum ++);

        titleRow.createCell(titleCell++).setCellValue("统计日期");
        titleRow.createCell(titleCell++).setCellValue("机台");
        titleRow.createCell(titleCell++).setCellValue("生产记录数");
        titleRow.createCell(titleCell++).setCellValue("产量");

        SXSSFCell wightCell = titleRow.createCell(titleCell);
        wightCell.setCellStyle(cellStyle);
        wightCell.setCellValue("白班");
        SXSSFCell blackCell = titleRow.createCell(titleCell + 3);
        blackCell.setCellStyle(cellStyle);
        blackCell.setCellValue("晚班");
        // 起始行，结束行，起始列，结束列
        CellRangeAddress caleRegion = new CellRangeAddress(0, 1, 0, 0);
        CellRangeAddress deviceRegion = new CellRangeAddress(0, 1, 1, 1);
        CellRangeAddress productNumRegion = new CellRangeAddress(0, 1, 2, 2);
        CellRangeAddress productQuantityRegion = new CellRangeAddress(0, 1, 3, 3);

        CellRangeAddress whiteRegion = new CellRangeAddress(0, 0, 4, 6);
        CellRangeAddress blackRegion = new CellRangeAddress(0, 0, 7, 9);
        sheet.addMergedRegion(caleRegion);
        sheet.addMergedRegion(deviceRegion);
        sheet.addMergedRegion(productNumRegion);
        sheet.addMergedRegion(productQuantityRegion);
        sheet.addMergedRegion(whiteRegion);
        sheet.addMergedRegion(blackRegion);

        if (CollectionUtils.isNotEmpty(craftNameList)) {
            int index = 10;
            int titleSecondCell = 4;
            for (String craftName : craftNameList) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, index, index + 8);
                sheet.addMergedRegion(cellRangeAddress);
                SXSSFCell cell = titleRow.createCell(index);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(craftName);

                index += 9;

                // 表头第二列
                secondTitleRow.createCell(titleSecondCell++).setCellValue("生产记录数");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("产量");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("产量占比");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("生产记录数");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("产量");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("产量占比");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("白班生产记录数");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("白班产量");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("白班产量占比");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("晚班生产记录数");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("晚班产量");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("晚班产量占比");

            }
        }
        List<ReportProductCaleDateCountVO> caleDateCountVOList = productCountVO.getReportProductCaleDateCountVOList();
        if (CollectionUtils.isNotEmpty(caleDateCountVOList)) {
            for (ReportProductCaleDateCountVO caleDateCountVO : caleDateCountVOList) {
                SXSSFRow row = sheet.createRow(rowNum ++);
                int cell = 0;
                // 换行 \n
                SXSSFCell cell1 = row.createCell(cell++);
                cell1.setCellValue(caleDateCountVO.getCaleDateStr().replaceAll(":","\n"));
                CellStyle style = workbook.createCellStyle();
                style.setWrapText(true);
                cell1.setCellStyle(style);
                if (StringUtils.equals("总计",caleDateCountVO.getCaleDateStr())) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 1);
                    sheet.addMergedRegion(cellRangeAddress);
                    cell++;
                }
                List<ProductReportProductCountDTO> reportProductCountDTOList = caleDateCountVO.getReportProductCountDTOList();
                boolean first = true;
                for (ProductReportProductCountDTO productCountDTO : reportProductCountDTOList) {
                    if (first) {
                        first = false;
                        if (!StringUtils.equals("总计",caleDateCountVO.getCaleDateStr())) {
                            CellRangeAddress cellRangeAddress = new CellRangeAddress(rowNum-1 , rowNum + reportProductCountDTOList.size() -2, 0, 0);
                            sheet.addMergedRegion(cellRangeAddress);
                        }
                    } else {
                        row = sheet.createRow(rowNum ++);
                        cell = 1;
                    }
                    String deviceName = productCountDTO.getDeviceName();
                    if (!StringUtils.equals("总计",caleDateCountVO.getCaleDateStr())) {
                        row.createCell(cell++).setCellValue(deviceName);
                    }
                    row.createCell(cell++).setCellValue(productCountDTO.getProductNum());
                    fillBigDecimalCell(productCountDTO.getProductQuantity(),row,cell++,true);

                    row.createCell(cell++).setCellValue(productCountDTO.getWhiteProductNum());
                    fillBigDecimalCell(productCountDTO.getWhiteProductQuantity(),row,cell++,true);
                    fillBigDecimalCell(productCountDTO.getWhiteProductQuantityRate(),row,cell++,false);

                    row.createCell(cell++).setCellValue(productCountDTO.getBlackProductNum());
                    fillBigDecimalCell(productCountDTO.getBlackProductQuantity(),row,cell++,true);
                    fillBigDecimalCell(productCountDTO.getBlackProductQuantityRate(),row,cell++,false);

                    List<ProductReportCraftCountDTO> craftCountDTOList = productCountDTO.getCraftCountDTOList();
                    if (CollectionUtils.isEmpty(craftCountDTOList)) {
                        continue;
                    }
                    for (ProductReportCraftCountDTO productReportCraftCountDTO : craftCountDTOList) {
                        row.createCell(cell++).setCellValue(productReportCraftCountDTO.getProductNum());
                        fillBigDecimalCell(productReportCraftCountDTO.getProductQuantity(),row,cell++,true);
                        fillBigDecimalCell(productReportCraftCountDTO.getProductQuantityRate(),row,cell++,false);

                        row.createCell(cell++).setCellValue(productReportCraftCountDTO.getWhiteProductNum());
                        fillBigDecimalCell(productReportCraftCountDTO.getWhiteProductQuantity(),row,cell++,true);
                        fillBigDecimalCell(productReportCraftCountDTO.getWhiteProductQuantityRate(),row,cell++,false);

                        row.createCell(cell++).setCellValue(productReportCraftCountDTO.getBlackProductNum());
                        fillBigDecimalCell(productReportCraftCountDTO.getBlackProductQuantity(),row,cell++,true);
                        fillBigDecimalCell(productReportCraftCountDTO.getBlackProductQuantityRate(),row,cell++,false);
                    }
                }
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("product_count.xlsx", String.valueOf(StandardCharsets.UTF_8)));
        workbook.write(response.getOutputStream());
    }

    private void fillBigDecimalCell(BigDecimal value,SXSSFRow row,int cellNum,boolean needStripTrailingZeros) {
        if (Objects.nonNull(value)) {
            if (needStripTrailingZeros) {
                row.createCell(cellNum).setCellValue(value.stripTrailingZeros().toPlainString());
                return;
            }
            row.createCell(cellNum).setCellValue(value.toPlainString());
            return;
        }
        row.createCell(cellNum).setCellValue("");
    }

    private CellStyle getStyleWithWrapText(SXSSFWorkbook wb) {
        //创建样式对象
        CellStyle style = wb.createCellStyle();
        //设置样式对齐方式：水平\垂直居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }
}
