package com.zw.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class CompressUtils {

    /**
     * 先压缩再base64
     */
    public static String compressString(String str) throws IOException {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            byte[] compress = compress(str.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(compress), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("[CompressUtils][compressString] compress error ",e);
            throw e;
        }
    }

    /**
     * 先base64  再解压
     */
    public static String uncompressString(String str) throws IOException {
        try {
            byte[] decode = Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8));
            return new String(uncompress(decode));
        }catch (Exception e) {
            log.error("[CompressUtils][uncompressString] uncompress error ",e);
            throw e;
        }
    }

    public static byte[] compress(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (out;GZIPOutputStream gzip = new GZIPOutputStream(out)){
            gzip.write(bytes);
        }
        return out.toByteArray();
    }

    public static byte[] uncompress(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try (out;in;GZIPInputStream ungzip = new GZIPInputStream(in)){
            byte[] buffer = new byte[1024];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        }
        return out.toByteArray();
    }

    public static void main(String[] args) throws IOException {
        String str = testData();
        System.out.println(str.getBytes(StandardCharsets.UTF_8).length);
        String compressString = compressString(str);
        System.out.println(compressString.getBytes(StandardCharsets.UTF_8).length);
        System.out.println(compressString);
        System.out.println(uncompressString(compressString));
    }

    private static String testData() {
        return "{\n" +
                "    \"success\":true,\n" +
                "    \"data\":{\n" +
                "        \"records\":[\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955398\",\n" +
                "                \"name\":\"test9999\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":37,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9999\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955397\",\n" +
                "                \"name\":\"test9998\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":84,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9998\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955396\",\n" +
                "                \"name\":\"test9997\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":59,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9997\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955395\",\n" +
                "                \"name\":\"test9996\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":86,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9996\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955394\",\n" +
                "                \"name\":\"test9995\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":23,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9995\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955393\",\n" +
                "                \"name\":\"test9994\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":67,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9994\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955392\",\n" +
                "                \"name\":\"test9993\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":99,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9993\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955391\",\n" +
                "                \"name\":\"test9992\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":46,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9992\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955390\",\n" +
                "                \"name\":\"test9991\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":16,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9991\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1588093790975955389\",\n" +
                "                \"name\":\"test9990\",\n" +
                "                \"bir\":\"2022-11-03\",\n" +
                "                \"age\":57,\n" +
                "                \"updateTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"createTime\":\"2022-11-03 17:00:41\",\n" +
                "                \"description\":null,\n" +
                "                \"other\":\"[{\\\"date\\\": \\\"2022-11-03 17:00:39\\\", \\\"nickName\\\": \\\"test9990\\\"}]\",\n" +
                "                \"deleted\":0,\n" +
                "                \"orgCode\":\"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"total\":\"10000\",\n" +
                "        \"size\":\"10\",\n" +
                "        \"current\":\"1\",\n" +
                "        \"orders\":[\n" +
                "\n" +
                "        ],\n" +
                "        \"optimizeCountSql\":true,\n" +
                "        \"searchCount\":true,\n" +
                "        \"countId\":null,\n" +
                "        \"maxLimit\":null,\n" +
                "        \"pages\":\"1000\"\n" +
                "    },\n" +
                "    \"errorCode\":null,\n" +
                "    \"errorMsg\":null\n" +
                "}";
    }
}
