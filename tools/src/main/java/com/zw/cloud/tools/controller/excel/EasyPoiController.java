package com.zw.cloud.tools.controller.excel;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.entity.dto.ShipmentOrderDTO;
import com.zw.cloud.tools.utils.excel.ExcelExportUtils;
import com.zw.cloud.tools.utils.excel.ExcelImportUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.util.List;

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
        Workbook workbook = ExcelExportUtils.export(null, "发货通知单", 0, shipmentOrderDTOS, ShipmentOrderDTO.class, null);

        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(title, StandardCharsets.UTF_8));
        workbook.write(response.getOutputStream());
    }

    @PostMapping("/importData")
    //http://localhost:9040/easy-poi/importData
    public void importData(MultipartFile file) throws Exception {
        List<ShipmentOrderDTO> shipmentOrderDTOList = ExcelImportUtils.importExcel(file, 0, 1, ShipmentOrderDTO.class);
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
