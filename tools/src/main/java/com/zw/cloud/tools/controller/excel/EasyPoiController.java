package com.zw.cloud.tools.controller.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.entity.dto.ShipmentOrderDTO;
import com.zw.cloud.tools.utils.excel.ExcelExportUtils;
import com.zw.cloud.tools.utils.excel.ExcelImportUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.cnblogs.com/huanghuanghui/p/9365849.html
 */
@RestController
@RequestMapping("/easy-poi")
@Slf4j
public class EasyPoiController {

    @GetMapping("/export")
    //http://localhost:9040/easy-poi/export
    public void export(HttpServletResponse response) throws Exception {
        List<ShipmentOrderDTO> shipmentOrderDTOS = buildData();
        String title;
        if (shipmentOrderDTOS.size() == 1){
            title = "发货通知单+" + System.currentTimeMillis() + ".xlsx";
        } else {
            title = System.currentTimeMillis() + "+" + shipmentOrderDTOS.size() + ".xlsx";
        }
        // 单个sheet 导出
        //Workbook workbook = ExcelExportUtils.export(null, "发货通知单", 0, shipmentOrderDTOS, ShipmentOrderDTO.class, null);

        // https://blog.csdn.net/baidu_36821021/article/details/85216855
        ExportParams sheet1Params = new ExportParams();
        // 设置sheet得名称
        sheet1Params.setSheetName("sheet1");
        // 创建sheet1使用得map
        Map<String, Object> sheet1ExportMap = new HashMap<>();
        // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
        sheet1ExportMap.put("title", sheet1Params);
        // 模版导出对应得实体类型
        sheet1ExportMap.put("entity", ShipmentOrderDTO.class);
        // sheet中要填充得数据
        sheet1ExportMap.put("data", shipmentOrderDTOS);

        ExportParams sheet2Params = new ExportParams();
        sheet2Params.setSheetName("sheet2");
        // 创建sheet2使用得map
        Map<String, Object> sheet2ExportMap = new HashMap<>();
        sheet2ExportMap.put("title", sheet2Params);
        sheet2ExportMap.put("entity", ShipmentOrderDTO.class);
        sheet2ExportMap.put("data", buildData());

        // 将sheet1、sheet2、sheet3使用得map进行包装
        List<Map<String, Object>> sheetsList = new ArrayList<>();
        sheetsList.add(sheet1ExportMap);
        sheetsList.add(sheet2ExportMap);

        Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);

        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(title, StandardCharsets.UTF_8));
        workbook.write(response.getOutputStream());
    }

    @PostMapping("/importData")
    //http://localhost:9040/easy-poi/importData
    public void importData(MultipartFile file) throws Exception {
        // 单个sheet
        //List<ShipmentOrderDTO> shipmentOrderDTOList = ExcelImportUtils.importExcel(file, 0, 1, ShipmentOrderDTO.class);
        String fileName = file.getOriginalFilename();
        Workbook hssfWorkbook ;
        if (fileName.endsWith("xlsx")){
            hssfWorkbook = new HSSFWorkbook(file.getInputStream());//Excel 2007
        } else if (fileName.endsWith("xls")){
            hssfWorkbook = new XSSFWorkbook(file.getInputStream());//Excel 2003
        } else {
            return;
        }
        ImportParams params = new ImportParams();
        List<ShipmentOrderDTO> shipmentOrderDTOList = new ArrayList<>();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            //表头在第几行
            params.setTitleRows(0);
            //距离表头中间有几行不要的数据
            //params.setStartRows(1);
            //第几个sheet页
            params.setStartSheetIndex(numSheet);
            ExcelImportResult<ShipmentOrderDTO> objectExcelImportResult = ExcelImportUtil.importExcelMore(file.getInputStream(), ShipmentOrderDTO.class, params);
            List<ShipmentOrderDTO> list = objectExcelImportResult.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                shipmentOrderDTOList.addAll(list);
            }
        }

        log.info("[EasyPoiController][importData] shipmentOrderDTOList is {}",JSON.toJSONString(shipmentOrderDTOList));
    }

    @GetMapping("/test")
    //http://localhost:9040/easy-poi/test
    public void test(HttpServletResponse response) throws Exception {
        // 转换DTO
        ExcelDemoDTO excelDemoDTO = new ExcelDemoDTO();
        excelDemoDTO.setFirst(1);
        excelDemoDTO.setSecond(BigDecimal.TEN);
        ExcelDemoDTO.ThirdChild thirdChild1 = new ExcelDemoDTO.ThirdChild();
        thirdChild1.setName("hello world");
        thirdChild1.setQuantity(1);
        ExcelDemoDTO.Detail detail1 = new ExcelDemoDTO.Detail();
        detail1.setQuantity(1);
        detail1.setFraction(BigDecimal.ZERO);
        ExcelDemoDTO.Detail detail2 = new ExcelDemoDTO.Detail();
        detail2.setQuantity(2);
        detail2.setFraction(BigDecimal.TEN);
        List<ExcelDemoDTO.Detail> detailList1 = new ArrayList<>();
        detailList1.add(detail1);
        detailList1.add(detail2);
        thirdChild1.setDetails(detailList1);
        ExcelDemoDTO.ThirdChild thirdChild2 = new ExcelDemoDTO.ThirdChild();
        thirdChild2.setName("hello world2");
        thirdChild2.setQuantity(2);
        ExcelDemoDTO.Detail detail3 = new ExcelDemoDTO.Detail();
        detail3.setQuantity(3);
        detail3.setFraction(BigDecimal.TEN);
        ExcelDemoDTO.Detail detail4 = new ExcelDemoDTO.Detail();
        detail4.setQuantity(4);
        detail4.setFraction(BigDecimal.ZERO);
        List<ExcelDemoDTO.Detail> detailList2 = new ArrayList<>();
        detailList2.add(detail3);
        detailList2.add(detail4);
        thirdChild2.setDetails(detailList2);
        List<ExcelDemoDTO.ThirdChild > childList = new ArrayList<>();
        childList.add(thirdChild1);
        childList.add(thirdChild2);
        excelDemoDTO.setThird(childList);

        LocalDateTime.now();
        String title = "文件名称.xlsx";

        // 导出workbook
        int headerRows = 1;
        Workbook workbook = ExcelExportUtils.export(title, title, headerRows, Lists.newArrayList(excelDemoDTO),
                ExcelDemoDTO.class, new IExcelDictHandler() {
                    @Override
                    public String toName(String dict, Object obj, String name, Object value) {
                        return null;
                    }

                    @Override
                    public String toValue(String dict, Object obj, String name, Object value) {
                        return null;
                    }
                });

       /* String tempFilePath = "D:\\tmp\\" + title + ".xls";
        OutputStream os = new FileOutputStream(tempFilePath);
        os.write(data);
        os.close();
        System.out.println(data);*/
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(title, StandardCharsets.UTF_8));
        workbook.write(response.getOutputStream());

    }

    private List<ShipmentOrderDTO> buildData(){
        String s = "{\n" +
                "        \"id\": \"1501818312755785730\",\n" +
                "        \"shipmentNoticeNo\": \"FJ004\",\n" +
                "        \"orderStatus\": 10,\n" +
                "        \"planShipmentDate\": \"2022-03-10 14:18:32\",\n" +
                "        \"shipmentNumber\": 100.00,\n" +
                "        \"horsesNumber\": 11.00,\n" +
                "        \"outboundNumber\": 12.00,\n" +
                "        \"outboundBoltNumber\": 13.00,\n" +
                "        \"shipmentAddress\": \"上海市嘉定区马陆镇哈哈哈\",\n" +
                "        \"noticeIssuingTime\": \"2022-03-10 14:18:32\",\n" +
                "        \"confirmShipmentTime\": \"2022-03-10 14:18:32\",\n" +
                "        \"isConfirmed\": 0,\n" +
                "        \"version\": 1,\n" +
                "        \"lastVersion\": 1,\n" +
                "        \"remark\": \"备注\",\n" +
                "        \"issuer\": \"张三\",\n" +
                "        \"phone\": \"15937699668\",\n" +
                "        \"principalA\": \"甲方负责人\",\n" +
                "        \"partA\": \"甲方公司名称\",\n" +
                "        \"principalB\": \"乙方负责人\",\n" +
                "        \"partB\": \"乙方公司名称\",\n" +
                "        \"bizOrgCode\": \"业务方名称\",\n" +
                "        \"bizName\": \"业务方orgCode\",\n" +
                "        \"orgCode\": \"devController\",\n" +
                "        \"createTime\": \"2022-03-10 15:12:43\",\n" +
                "        \"createUser\": \"E5F887E61DED452E9AD28E84746D49E9\",\n" +
                "        \"createSystem\": \"B4263529337148489E88A215BE562CF8\",\n" +
                "        \"updateTime\": \"2022-03-10 15:12:43\",\n" +
                "        \"isDeleted\": 0,\n" +
                "        \"shipmentItemOrderDTOList\": [\n" +
                "            {\n" +
                "                \"id\": \"1501818313682726913\",\n" +
                "                \"shipmentNoticeNo\": \"FJ004\",\n" +
                "                \"produceOrderNo\": \"0004\",\n" +
                "                \"shipmentNumber\": 10.00,\n" +
                "                \"shipmentBolt\": 10.00,\n" +
                "                \"finishedGoodsNo\": \"批号-成品批号\",\n" +
                "                \"erpProduceOrderNo\": \"染厂订单号\",\n" +
                "                \"unit\": \"单位\",\n" +
                "                \"goodsName\": \"成品名称/商品名称\",\n" +
                "                \"color\": \"颜色\",\n" +
                "                \"colorNo\": \"色号\",\n" +
                "                \"batchNumber\": \"批号\",\n" +
                "                \"materialName\": \"原料名称\",\n" +
                "                \"orgCode\": \"devController\",\n" +
                "                \"createTime\": \"2022-03-10 15:12:43\",\n" +
                "                \"createUser\": \"E5F887E61DED452E9AD28E84746D49E9\",\n" +
                "                \"createSystem\": \"B4263529337148489E88A215BE562CF8\",\n" +
                "                \"updateTime\": \"2022-03-10 15:12:43\",\n" +
                "                \"updateUser\": \"E5F887E61DED452E9AD28E84746D49E9\",\n" +
                "                \"updateSystem\": \"B4263529337148489E88A215BE562CF8\",\n" +
                "                \"isDeleted\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"1501818313682726914\",\n" +
                "                \"shipmentNoticeNo\": \"FJ004\",\n" +
                "                \"produceOrderNo\": \"0005\",\n" +
                "                \"shipmentNumber\": 10.00,\n" +
                "                \"shipmentBolt\": 10.00,\n" +
                "                \"finishedGoodsNo\": \"批号-成品批号\",\n" +
                "                \"erpProduceOrderNo\": \"染厂订单号\",\n" +
                "                \"unit\": \"单位\",\n" +
                "                \"goodsName\": \"成品名称/商品名称\",\n" +
                "                \"color\": \"颜色\",\n" +
                "                \"colorNo\": \"色号\",\n" +
                "                \"batchNumber\": \"批号\",\n" +
                "                \"materialName\": \"原料名称\",\n" +
                "                \"orgCode\": \"devController\",\n" +
                "                \"createTime\": \"2022-03-10 15:12:43\",\n" +
                "                \"createUser\": \"E5F887E61DED452E9AD28E84746D49E9\",\n" +
                "                \"createSystem\": \"B4263529337148489E88A215BE562CF8\",\n" +
                "                \"updateTime\": \"2022-03-10 15:12:43\",\n" +
                "                \"updateUser\": \"E5F887E61DED452E9AD28E84746D49E9\",\n" +
                "                \"updateSystem\": \"B4263529337148489E88A215BE562CF8\",\n" +
                "                \"isDeleted\": 0\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
         return Lists.newArrayList(JSON.parseObject(s, ShipmentOrderDTO.class));
    }
}
