package com.zw.cloud.mybatis.plus.controller;


import com.alibaba.fastjson.JSON;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.common.utils.DateTimeUtils;
import com.zw.cloud.mybatis.plus.entity.ProductRecord;
import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordDTO;
import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordReportQueryDTO;
import com.zw.cloud.mybatis.plus.entity.dto.RowTitleDTO;
import com.zw.cloud.mybatis.plus.service.api.IProductRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 生产记录表 前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-09-29
 */
@RestController
@RequestMapping("/base-product-record")
@Slf4j
public class ProductRecordController {

    @Autowired
    private IProductRecordService baseProductRecordService;

    @PostMapping("/insert")
    //http://localhost:8080/base-product-record/insert
    public void insert(@RequestBody ProductRecord productRecord) {
        baseProductRecordService.insertProductRecord(productRecord);
    }

    @GetMapping("/batchInsert/{count}")
    //http://localhost:8080/base-product-record/batchInsert/20000
    public void batchInsert(@PathVariable int count) {
        List<ProductRecord> productRecordList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            ProductRecord productRecord = buildProductRecord();
            productRecordList.add(productRecord);
        }
        baseProductRecordService.batchInsertProductRecord(productRecordList);
    }

    @GetMapping("/batchInsertByMybatisPlus/{count}")
    //http://localhost:8080/base-product-record/batchInsertByMybatisPlus/20000
    public void batchInsertByMybatisPlus(@PathVariable int count) {
        List<ProductRecord> productRecordList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            ProductRecord productRecord = buildProductRecord();
            productRecordList.add(productRecord);
        }
        long start = System.currentTimeMillis();
        baseProductRecordService.saveBatch(productRecordList,count);
        //2万 16987 17328
        log.info("[ProductRecordServiceImpl][batchInsertByMybatisPlus] use time is {}",System.currentTimeMillis() - start);

    }

    @PostMapping("/queryList")
    //http://localhost:8080/base-product-record/queryList
    public List<ProductRecord> queryList(@RequestBody ProductRecord productRecord) {
        return baseProductRecordService.queryList(productRecord);
    }

    @PostMapping("/queryAllFinishedProductForReport")
    //http://localhost:8080/base-product-record/queryAllFinishedProductForReport
    public List<ProductRecordDTO> queryAllFinishedProductForReport(@RequestBody ProductRecordReportQueryDTO productRecordReportQueryDTO) {
        return baseProductRecordService.queryAllFinishedProductForReport(productRecordReportQueryDTO);
    }

    @PostMapping("/pageQueryAllFinishedProduct")
    //http://localhost:8080/base-product-record/pageQueryAllFinishedProduct
    public Map<String, Object> pageQueryAllFinishedProduct(@RequestBody ProductRecordReportQueryDTO productRecordReportQueryDTO) {
        return baseProductRecordService.pageQueryAllFinishedProduct(productRecordReportQueryDTO);
    }

    @GetMapping("/exportFinishedProduct")
    //http://localhost:8080/base-product-record/exportFinishedProduct
    public void exportFinishedProduct(HttpServletResponse response) throws Exception {
        ProductRecordReportQueryDTO productRecordReportQueryDTO = buildProductRecordReportQueryDTO();
        productRecordReportQueryDTO.setOrgCode("devController");
        List<RowTitleDTO> rowTitleDTOList = productRecordReportQueryDTO.getRowTitleDTOList();
        if (CollectionUtils.isEmpty(rowTitleDTOList)) {
            throw new BizException("导出的列为空");
        }
        List<ProductRecordDTO> productRecordDTOList = baseProductRecordService.queryAllFinishedProductForReport(productRecordReportQueryDTO);
        if (CollectionUtils.isEmpty(productRecordDTOList)) {
            throw new BizException("暂无数据");
        }
        //创建HSSFWorkbook对象(excel的文档对象)
        SXSSFWorkbook workbook = new SXSSFWorkbook(productRecordDTOList.size());
        //建立新的sheet对象（excel的表单）
        SXSSFSheet sheet = workbook.createSheet("定型生产记录");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        //在sheet里创建第一行，参数为行索引(excel的行)
        SXSSFRow row1 = sheet.createRow(0);
        //创建单元格并设置单元格内容
        int i = 0;
        for (RowTitleDTO rowTitleDTO : rowTitleDTOList) {
            row1.createCell(i).setCellValue(rowTitleDTO.getLabel());
            i ++;
        }
        int rowNum = 1;
        Class<ProductRecordDTO> productRecordDTOClass = ProductRecordDTO.class;
        for (ProductRecordDTO productRecordDTO : productRecordDTOList){
            SXSSFRow row = sheet.createRow(rowNum);
            for (int cellNo = 0; cellNo < rowTitleDTOList.size(); cellNo++) {
                SXSSFCell cell = row.createCell(cellNo);
                String fieldName = rowTitleDTOList.get(cellNo).getKey();

                // 异常类型
                if (StringUtils.endsWithIgnoreCase("exceptionType",fieldName)) {
                    String exceptionTypeName = Optional.ofNullable(productRecordDTO.getExceptionTypeName()).orElse("");
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(exceptionTypeName)) {
                        exceptionTypeName = exceptionTypeName.replace("正常数据","");
                    }
                    if (StringUtils.isNotBlank(productRecordDTO.getAssistantExceptionName())) {
                        exceptionTypeName = exceptionTypeName + "\n"+ productRecordDTO.getAssistantExceptionName();
                    }
                    if (Objects.nonNull(productRecordDTO.getCraftExceptionType()) && 1 == productRecordDTO.getCraftExceptionType()) {
                        exceptionTypeName = exceptionTypeName + "\n"+ productRecordDTO.getCraftExceptionTypeName();
                    }
                    cell.setCellStyle(cellStyle);
                    exceptionTypeName = org.apache.commons.lang3.StringUtils.equals("",exceptionTypeName) ? "-" : exceptionTypeName;
                    cell.setCellValue(exceptionTypeName);
                    continue;
                }
                // 配桶数量
                if (StringUtils.endsWithIgnoreCase("ptQuantity",fieldName)) {
                    BigDecimal ptQuantity = productRecordDTO.getPtQuantity();
                    if (Objects.nonNull(ptQuantity)) {
                        cell.setCellValue(ptQuantity.stripTrailingZeros().toPlainString());
                    }
                    continue;
                }

                Field declaredField = null;
                try {
                    declaredField = productRecordDTOClass.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    log.warn("[exportFinishedProduct]NoSuchFieldException is ",e);
                    continue;
                }
                declaredField.setAccessible(true);
                Object value = declaredField.get(productRecordDTO);
                if (Objects.isNull(value)) {
                    continue;
                }
                if (value instanceof BigDecimal) {
                    BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));
                    cell.setCellValue(bigDecimal.stripTrailingZeros().toPlainString());
                    continue;
                }
                if (value instanceof Double) {
                    BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));
                    cell.setCellValue(bigDecimal.stripTrailingZeros().toPlainString());
                    continue;
                }
                if (value instanceof LocalDateTime) {
                    String timeStr = DateTimeUtils.parse2Str((LocalDateTime) value, DateTimeUtils.dateTimePattern);
                    cell.setCellValue(timeStr);
                    continue;
                }
                cell.setCellType(CellType.STRING);
                cell.setCellValue(String.valueOf(value));

            }
            rowNum ++;
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String( "定型生产记录.xlsx".getBytes(), StandardCharsets.ISO_8859_1));
        workbook.write(response.getOutputStream());
    }

    private ProductRecord buildProductRecord() {
        String s = "{\n" +
                "    \"orgCode\":\"devController\",\n" +
                "    \"deviceId\":\"1499331094789685249\",\n" +
                "    \"deviceName\":\"18hao设备5\",\n" +
                "    \"productCardCode\":\"140600599\",\n" +
                "    \"orderId\":\"\",\n" +
                "    \"customerNo\":\"WJYF2\",\n" +
                "    \"customerName\":\"G吴江元纺\",\n" +
                "    \"colorNo\":\"J1406070005\",\n" +
                "    \"color\":\"酒红\",\n" +
                "    \"fabricNo\":\"GPB060046\",\n" +
                "    \"fabricName\":\"(G)350T半光涤塔夫\",\n" +
                "    \"batchNo\":\"X4060720\",\n" +
                "    \"fabricWidth\":\"客到\",\n" +
                "    \"gramWeight\":76,\n" +
                "    \"planMeters\":2120,\n" +
                "    \"preStatus\":\"2\",\n" +
                "    \"afterStatus\":\"0\",\n" +
                "    \"sort\":0,\n" +
                "    \"source\":1,\n" +
                "    \"createTypeName\":\"手动排产\",\n" +
                "    \"createTime\":\"2023-03-03 14:31:21\",\n" +
                "    \"createUser\":\"E5F887E61DED452E9AD28E84746D49E9\",\n" +
                "    \"createSystem\":\"B4263529337148489E88A215BE562CF8\",\n" +
                "    \"updateTime\":\"2023-03-03 14:39:18\",\n" +
                "    \"updateUser\":\"System\",\n" +
                "    \"updateSystem\":\"B4263529337148489E88A215BE562CF8\",\n" +
                "    \"isDeleted\":0,\n" +
                "    \"ptQuantity\":2120,\n" +
                "    \"productSort\":\"0\",\n" +
                "    \"employeeName\":\"业务员名称\",\n" +
                "    \"avgCarSpeed\":66,\n" +
                "    \"avgTemperature\":250,\n" +
                "    \"avgWindSpeed\":75,\n" +
                "    \"avgTopFeed\":75,\n" +
                "    \"assistant\":[\n" +
                "        {\n" +
                "            \"id\":\"79\",\n" +
                "            \"name\":\"防水剂880\",\n" +
                "            \"value\":\"7\",\n" +
                "            \"parentId\":\"74\",\n" +
                "            \"parentName\":\"防水类\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"startTime\":\"2023-03-03 14:31:35\",\n" +
                "    \"endTime\":\"2023-03-03 14:36:47\",\n" +
                "    \"workshopId\":\"1466599063445401602\",\n" +
                "    \"calcDay\":\"2023-03-03\",\n" +
                "    \"craftType\":\"293\",\n" +
                "    \"craftTypeName\":\"成定\",\n" +
                "    \"preSort\":0,\n" +
                "    \"specification\":\"测试规格001\",\n" +
                "    \"style\":\"测试风格001\",\n" +
                "    \"preOperator\":\"测试\",\n" +
                "    \"matches\":\"20\",\n" +
                "    \"actualMeters\":2120,\n" +
                "    \"expectedTime\":\"177\",\n" +
                "    \"actualTime\":\"6\",\n" +
                "    \"exceptionType\":2,\n" +
                "    \"exceptionTypeName\":\"操作异常\",\n" +
                "    \"productWeightMin\":\"\",\n" +
                "    \"productWeightMax\":\"\",\n" +
                "    \"isBatch\":0,\n" +
                "    \"scheduleImport\":0,\n" +
                "    \"processCode\":\"\",\n" +
                "    \"platform\":0,\n" +
                "    \"productCode\":\"\",\n" +
                "    \"assistantExceptionType\":1,\n" +
                "    \"planAssistant\":[\n" +
                "        {\n" +
                "            \"id\":\"100\",\n" +
                "            \"name\":\"抗静电剂\",\n" +
                "            \"value\":11,\n" +
                "            \"parentId\":\"76\",\n" +
                "            \"parentName\":\"其它\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"98\",\n" +
                "            \"name\":\"环保树脂6350\",\n" +
                "            \"value\":33,\n" +
                "            \"parentId\":\"76\",\n" +
                "            \"parentName\":\"其它\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"assistantExceptionName\":\"助剂异常\",\n" +
                "    \"customerRequest\":\"划水\",\n" +
                "    \"stereotypeRequirement\":\"\",\n" +
                "    \"craftSuitability\":9.01,\n" +
                "    \"shrinkage\":10,\n" +
                "    \"craftExceptionType\":1,\n" +
                "    \"craftExceptionTypeName\":\"工艺异常\",\n" +
                "    \"productWeight\":\"10\",\n" +
                "    \"thickness\":\"10\",\n" +
                "    \"isHaveCraft\":0,\n" +
                "    \"processReportType\":20,\n" +
                "    \"colorSystem\":\"\",\n" +
                "    \"isFirstProduct\":0,\n" +
                "    \"isRepairCard\":0,\n" +
                "    \"cardSort\":0\n" +
                "}";
        return JSON.parseObject(s,ProductRecord.class);
    }

    private ProductRecordReportQueryDTO buildProductRecordReportQueryDTO() {
        String s = "{\n" +
                "    \"workshopId\":0,\n" +
                "    \"workshopType\":\"workshopStereotype\",\n" +
                "    \"startTime\":\"2023-03-06 10:58:00\",\n" +
                "    \"endTime\":\"2023-03-07 14:36:57\",\n" +
                "    \"isShowExceptions\":false,\n" +
                "    \"rowTitleDTOList\":[\n" +
                "        {\n" +
                "            \"key\":\"productCardCode\",\n" +
                "            \"label\":\"流转卡号\",\n" +
                "            \"isFixed\":true\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"isRepairCard\",\n" +
                "            \"label\":\"回修\",\n" +
                "            \"isFixed\":true\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"customerName\",\n" +
                "            \"label\":\"客户名称\",\n" +
                "            \"isFixed\":true\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"fabricNo\",\n" +
                "            \"label\":\"坯布编码\",\n" +
                "            \"isFixed\":true\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"fabricName\",\n" +
                "            \"label\":\"坯布名称\",\n" +
                "            \"isFixed\":true\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"color\",\n" +
                "            \"label\":\"颜色名称\",\n" +
                "            \"isFixed\":true\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"colorNo\",\n" +
                "            \"label\":\"颜色编号\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"craftTypeName\",\n" +
                "            \"label\":\"工序类型\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"colorSystem\",\n" +
                "            \"label\":\"色系\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"batchNo\",\n" +
                "            \"label\":\"批次号\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"employeeName\",\n" +
                "            \"label\":\"业务员\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"ptQuantity\",\n" +
                "            \"label\":\"配桶数量(kg)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"actualMeters\",\n" +
                "            \"label\":\"长度\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"matches\",\n" +
                "            \"label\":\"匹数\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"teamGroupAndReportMsgList\",\n" +
                "            \"label\":\"报工信息(匹)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"shrinkage\",\n" +
                "            \"label\":\"缩率(%)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"gramWeight\",\n" +
                "            \"label\":\"白坯克重(g/m²)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"fabricWidth\",\n" +
                "            \"label\":\"成品门幅\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"productWeight\",\n" +
                "            \"label\":\"成品克重\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"markupCraft\",\n" +
                "            \"label\":\"加价要求\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"deptName\",\n" +
                "            \"label\":\"染色车间\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"deviceName\",\n" +
                "            \"label\":\"机台\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"operatorInfo\",\n" +
                "            \"label\":\"后车操作员\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"preOperator\",\n" +
                "            \"label\":\"中车操作员\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"frontOperator\",\n" +
                "            \"label\":\"前车操作员\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"teamGroup\",\n" +
                "            \"label\":\"班组\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"preCarNumber\",\n" +
                "            \"label\":\"定前车号\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"carNumber\",\n" +
                "            \"label\":\"定后车号\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"preWeight\",\n" +
                "            \"label\":\"定前克重(g/m²)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"gramHeft\",\n" +
                "            \"label\":\"定后克重(g/m²)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"weftDensity\",\n" +
                "            \"label\":\"纬密(根/cm)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"upperDoorWidth\",\n" +
                "            \"label\":\"上机门幅(cm)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"lowerDoorWidth\",\n" +
                "            \"label\":\"下机门幅(cm)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"reductionWeight\",\n" +
                "            \"label\":\"还原克重(g/m²)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"reductionAmplitude\",\n" +
                "            \"label\":\"还原门幅(cm)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"defect\",\n" +
                "            \"label\":\"疵点记录\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"result\",\n" +
                "            \"label\":\"处理结果\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"severity\",\n" +
                "            \"label\":\"严重程度\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"avgCarSpeed\",\n" +
                "            \"label\":\"平均车速(米/分)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"avgTemperature\",\n" +
                "            \"label\":\"平均温度(℃)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"avgWindSpeed\",\n" +
                "            \"label\":\"平均风速(r/min)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"avgTopFeed\",\n" +
                "            \"label\":\"平均上超喂(%)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"assistant\",\n" +
                "            \"label\":\"助剂情况\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"actualTime\",\n" +
                "            \"label\":\"实际执行时长(min)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"expectedTime\",\n" +
                "            \"label\":\"预计执行时长(min)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"craftSuitability\",\n" +
                "            \"label\":\"工艺匹配度(%)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"exceptionType\",\n" +
                "            \"label\":\"异常类型\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"remark\",\n" +
                "            \"label\":\"备注\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"createTypeName\",\n" +
                "            \"label\":\"创建类型\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"startTime\",\n" +
                "            \"label\":\"中车开始时间\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"endTime\",\n" +
                "            \"label\":\"中车结束时间\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"handleTime\",\n" +
                "            \"label\":\"后车结束时间\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return JSON.parseObject(s,ProductRecordReportQueryDTO.class);
    }
}
