package com.zw.cloud.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.zw.cloud.common.entity.dto.FormulaSettingShowBaseDTO;
import com.zw.cloud.common.entity.dto.ScoreConfigJsonDTO;

import java.util.List;
import java.util.Map;

public class JsonTest {
    public static void main(String[] args) {
        Map<String, List<ScoreConfigJsonDTO>> config = JSON.parseObject(configJson(), new TypeReference<Map<String, List<ScoreConfigJsonDTO>>>() {});
        List<ScoreConfigJsonDTO> open = config.get("open");
        List<ScoreConfigJsonDTO> notOpen = config.get("notOpen");
        System.out.println(JSON.toJSONString(open));
        System.out.println(JSON.toJSONString(notOpen));

        String s = formulaSettingShowBaseJson();
        FormulaSettingShowBaseDTO formulaSettingShowBaseDTO = JSON.parseObject(s, FormulaSettingShowBaseDTO.class);
        System.out.println(JSON.toJSONString(formulaSettingShowBaseDTO));

        ScoreConfigJsonDTO scoreConfigJsonDTO = new ScoreConfigJsonDTO();
        scoreConfigJsonDTO.setTest(true);
        String scoreConfigJsonDTOStr = JSON.toJSONString(scoreConfigJsonDTO);
        // isTest 字段被修改为 test {"test":true}
        System.out.println(scoreConfigJsonDTOStr);
        ScoreConfigJsonDTO scoreConfigJsonDTO1 = JSON.parseObject(scoreConfigJsonDTOStr, ScoreConfigJsonDTO.class);
        System.out.println(scoreConfigJsonDTO1.isTest()); // true
    }

    private static String configJson() {
        return "{\n" +
                "    \"notOpen\":[\n" +
                "        {\n" +
                "            \"name\":\"craftType\",\n" +
                "            \"preciseMatch\":20000\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"fabricNo\",\n" +
                "            \"preciseMatch\":10000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"customerNo\",\n" +
                "            \"preciseMatch\":600\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"customerName\",\n" +
                "            \"preciseMatch\":500\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"specification\",\n" +
                "            \"preciseMatch\":500\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"stereotypeRequirement\",\n" +
                "            \"preciseMatch\":500\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"productWeight\",\n" +
                "            \"preciseMatch\":500\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"thickness\",\n" +
                "            \"preciseMatch\":500\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"shrinkage\",\n" +
                "            \"preciseMatch\":500\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"fabricWidth\",\n" +
                "            \"preciseMatch\":500\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"colorSystem\",\n" +
                "            \"preciseMatch\":500\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"deviceBrandMatch\":20,\n" +
                "            \"includeMatch\":500,\n" +
                "            \"name\":\"deviceName\",\n" +
                "            \"preciseMatch\":600\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":5,\n" +
                "            \"name\":\"preOperator\",\n" +
                "            \"preciseMatch\":10\n" +
                "        }\n" +
                "    ],\n" +
                "    \"open\":[\n" +
                "        {\n" +
                "            \"name\":\"craftType\",\n" +
                "            \"preciseMatch\":20000\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"fabricNo\",\n" +
                "            \"preciseMatch\":10000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"customerNo\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"customerName\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"specification\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"stereotypeRequirement\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"productWeight\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"thickness\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"shrinkage\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"fabricWidth\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"name\":\"colorSystem\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        },\n" +
                "        {\n" +
                "            \"allMatch\":50,\n" +
                "            \"deviceBrandMatch\":20,\n" +
                "            \"includeMatch\":500,\n" +
                "            \"name\":\"deviceName\",\n" +
                "            \"preciseMatch\":1000\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    private static String formulaSettingShowBaseJson() {
        return  "{\n" +
                "    \"choose\":[\n" +
                "        {\n" +
                "            \"key\":\"dyestuffTypeCode\",\n" +
                "            \"value\":\"染料编号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"dyestuffTypeName\",\n" +
                "            \"value\":\"染料名称\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"type\",\n" +
                "            \"value\":\"类型\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"cmax\",\n" +
                "            \"value\":\"最大用量\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"cmin\",\n" +
                "            \"value\":\"最小用量\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"fixedDyestuff\",\n" +
                "            \"value\":\"固定染料\",\n" +
                "            \"selected\":0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"result\":[\n" +
                "        {\n" +
                "            \"key\":\"de2000\",\n" +
                "            \"value\":\"DE(lab)主  \",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"deValue\",\n" +
                "            \"value\":\"DE(cmc)主\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"assDe2000\",\n" +
                "            \"value\":\"DE(lab)辅\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"assDeValue\",\n" +
                "            \"value\":\"DE(cmc)辅 \",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"labL\",\n" +
                "            \"value\":\"DL\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"labA\",\n" +
                "            \"value\":\"Da\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"labB\",\n" +
                "            \"value\":\"Db\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"labC\",\n" +
                "            \"value\":\"DC\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"labH\",\n" +
                "            \"value\":\"DH\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"assMi\",\n" +
                "            \"value\":\"光源色变\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"price\",\n" +
                "            \"value\":\"价格\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"ratio\",\n" +
                "            \"value\":\"总浓度\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"overCost\",\n" +
                "            \"value\":\"超成本\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"dyestuffCode\",\n" +
                "            \"value\":\"物料编号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"dyestuffName\",\n" +
                "            \"value\":\"物料名称\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"fabricWeight\",\n" +
                "            \"value\":\"布重\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"bathRatio\",\n" +
                "            \"value\":\"浴比\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"bathAmount\",\n" +
                "            \"value\":\"浴量\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"templateName\",\n" +
                "            \"value\":\"助剂模板\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"blendentProductName\",\n" +
                "            \"value\":\"染色组\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"effectValue\",\n" +
                "            \"value\":\"效应值\",\n" +
                "            \"selected\":0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"colorInfo\":[\n" +
                "        {\n" +
                "            \"key\":\"colorCode\",\n" +
                "            \"value\":\"颜色编号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"bulkNo\",\n" +
                "            \"value\":\"大货色号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"colorCardCode\",\n" +
                "            \"value\":\"色卡号/CR号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"colorName\",\n" +
                "            \"value\":\"颜色名称\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"pentoColor\",\n" +
                "            \"value\":\"潘通\",\n" +
                "            \"selected\":0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"sampleInfo\":[\n" +
                "        {\n" +
                "            \"key\":\"colorCode\",\n" +
                "            \"value\":\"颜色编号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"colorName\",\n" +
                "            \"value\":\"颜色名称\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"fabricCode\",\n" +
                "            \"value\":\"坯布编号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"fabricName\",\n" +
                "            \"value\":\"坯布名称\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"measureEffect\",\n" +
                "            \"value\":\"染色效果 \",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"patternDictId\",\n" +
                "            \"value\":\"花纹\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"dyestuffPattern\",\n" +
                "            \"value\":\"染料模式\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"exhibit\",\n" +
                "            \"value\":\"展示配方\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"pentoColor\",\n" +
                "            \"value\":\"潘通\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"calculDyestuffLimit\",\n" +
                "            \"value\":\"计算染料\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"mainLightCode\",\n" +
                "            \"value\":\"主光源\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"assistLightCode\",\n" +
                "            \"value\":\"辅光源\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"dyeCharaters\",\n" +
                "            \"value\":\"染料特性\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"environmentProducts\",\n" +
                "            \"value\":\"环保要求\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"matchProductId\",\n" +
                "            \"value\":\"配色品名\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"dyeingWayCode\",\n" +
                "            \"value\":\"染色方式\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"orderPrice\",\n" +
                "            \"value\":\"订单价\",\n" +
                "            \"selected\":0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"sampleShow\":0,\n" +
                "    \"recommendGroup\":[\n" +
                "        {\n" +
                "            \"key\":\"dyestuffCode\",\n" +
                "            \"value\":\"物料编号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"dyestuffName\",\n" +
                "            \"value\":\"物料名称\",\n" +
                "            \"selected\":0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"samplePrintInfo\":[\n" +
                "        {\n" +
                "            \"key\":\"fabricName\",\n" +
                "            \"value\":\"坯布名称\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"fabricCode\",\n" +
                "            \"value\":\"坯布编号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"customerCode\",\n" +
                "            \"value\":\"客户编号\",\n" +
                "            \"selected\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\":\"customerName\",\n" +
                "            \"value\":\"客户名称\",\n" +
                "            \"selected\":0\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}
