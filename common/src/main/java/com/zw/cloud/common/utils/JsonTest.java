package com.zw.cloud.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zw.cloud.common.entity.dto.ScoreConfigJsonDTO;

import java.util.List;
import java.util.Map;

public class JsonTest {
    public static void main(String[] args) {
        String s = "{\n" +
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
        Map<String, List<ScoreConfigJsonDTO>> config = JSON.parseObject(s, new TypeReference<Map<String, List<ScoreConfigJsonDTO>>>() {});
        List<ScoreConfigJsonDTO> open = config.get("open");
        List<ScoreConfigJsonDTO> notOpen = config.get("notOpen");
        System.out.println(JSON.toJSONString(open));
        System.out.println(JSON.toJSONString(notOpen));

    }
}
