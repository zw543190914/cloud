package com.zw.cloud.mybatis.controller;

import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageInfo;
import com.zw.cloud.global.response.wrapper.entity.WebResult;
import com.zw.cloud.mybatis.entity.ProductRecord;
import com.zw.cloud.mybatis.service.api.IProductRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product-record")
@Slf4j
public class ProductRecordController {

    @Autowired
    private IProductRecordService productRecordService;

    @PostMapping("/queryList")
    //http://localhost:8080/product-record/queryList
    public WebResult<PageInfo<ProductRecord>> queryList(@RequestBody ProductRecord productRecord) {
        return WebResult.success(productRecordService.queryList(productRecord));
    }

    @GetMapping("/insert")
    //http://localhost:8080/product-record/insert
    public WebResult<ProductRecord> insert() {
        return WebResult.success(productRecordService.insert(buildProductRecord()));
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

}
