package com.zw.cloud.tools.utils;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.export.ExcelBatchExportService;
import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import com.github.pagehelper.util.StringUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * EXCEL导出工具类
 */
public abstract class ExcelExportUtil {

    public static Workbook export(String title, String sheetName, Integer headerRows, Collection<?> dataSet, Class<?> pojoClass) {
        return export(title, sheetName, headerRows, dataSet, pojoClass, null);
    }

    public static Workbook export(String title, String sheetName, Integer headerRows, Collection<?> dataSet, Class<?> pojoClass, IExcelDictHandler dictHandler) {
        ExcelBatchExportService service = new ExcelBatchExportService();
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setDictHandler(dictHandler);
        service.init(exportParams, pojoClass);
        service.appendData(dataSet);
        Workbook workbook = service.closeExportBigExcel();
        /*导出自适应宽度、高度*/
        autoColumnWidth((SXSSFSheet) workbook.getSheetAt(0), headerRows);
        autoRowHeight((SXSSFSheet) workbook.getSheetAt(0), headerRows);
        return workbook;
    }

    private static void autoColumnWidth(SXSSFSheet sheet, Integer headerRows) {
        sheet.trackAllColumnsForAutoSizing();
        for (int columnNum = 0; columnNum < sheet.getRow(sheet.getLastRowNum()).getPhysicalNumberOfCells(); columnNum++) {
            sheet.autoSizeColumn(columnNum);
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = headerRows; rowNum <= sheet.getLastRowNum(); rowNum++) {
                if (sheet.getRow(rowNum) == null) {
                    continue;
                }
                SXSSFRow currentRow = sheet.getRow(rowNum);
                SXSSFCell currentCell = currentRow.getCell(columnNum);
                if (null == currentCell) {
                    continue;
                }
                if (currentCell.getCellType() != STRING) {
                    continue;
                }
                int length = currentCell.getStringCellValue().getBytes().length;
                if (columnWidth < length) {
                    columnWidth = length;
                }
            }
            sheet.setColumnWidth(columnNum, max(15, (min(columnWidth, 90))) * 256);
        }
    }

    private static void autoRowHeight(SXSSFSheet sheet, Integer headerRows) {
        for (int rowNum = headerRows; rowNum <= sheet.getLastRowNum(); rowNum++) {
            if (sheet.getRow(rowNum) == null) {
                continue;
            }
            SXSSFRow currentRow = sheet.getRow(rowNum);
            short rowHeight = currentRow.getHeight();
            for (int columnNum = currentRow.getFirstCellNum(); columnNum < currentRow.getPhysicalNumberOfCells(); columnNum++) {
                SXSSFCell currentCell = currentRow.getCell(columnNum);
                if (null == currentCell) {
                    continue;
                }
                if (currentCell.getCellType() != STRING) {
                    continue;
                }
                String cellContent = currentCell.getStringCellValue();
                if (StringUtil.isEmpty(cellContent)) {
                    continue;
                }
                int cellContentWidth = cellContent.getBytes().length * 256;
                int columnWidth = sheet.getColumnWidth(columnNum);
                int stringNeedsRows = cellContentWidth / columnWidth;
                stringNeedsRows = (cellContentWidth % columnWidth == 0) ? stringNeedsRows : stringNeedsRows + 1;
                int stringNeedsHeight = rowHeight * stringNeedsRows;
                currentRow.setHeight((short) stringNeedsHeight);
            }
        }
    }


    public static <T> byte[] exportExcel(String title, String sheetName, Integer headerRows, Collection<?> dataSet, Class<?> pojoClass, IExcelDictHandler dictHandler) {
        Workbook wb = export(title, sheetName, headerRows, dataSet,pojoClass,dictHandler);
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
            data = bos.toByteArray();
        } catch (
                IOException exc) {

        } finally {
            try {
                wb.close();
            } catch (IOException exc1) {
            }
            try {
                bos.close();
            } catch (IOException exc2) {
            }
        }
        return data;
    }


}
