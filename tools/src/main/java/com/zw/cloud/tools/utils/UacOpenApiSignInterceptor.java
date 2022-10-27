package com.zw.cloud.tools.utils;

import org.apache.http.*;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UacOpenApiSignInterceptor implements HttpRequestInterceptor {
    private static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder();
    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
    private static final String QUERY_STRING = "Query-String";
    private static final String HEADER_CLIENT_ID = "Client-Id";
    private static final String HEADER_NONCE = "Nonce";
    private static final String HEADER_DATE = "Date";
    private static final String HEADER_DIGEST = "Digest";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String SIGN_PARAMS;
    private static final Map<String, String> JAVA_SIGN_METHOD = new HashMap<>();
    private final static String bodySignMethod = "SHA-256";

    static {
        SIGN_PARAMS = String.format("%s %s %s", QUERY_STRING, HEADER_DATE, HEADER_DIGEST).toLowerCase();
        JAVA_SIGN_METHOD.put("hmac-sha256", "HmacSHA256");
        JAVA_SIGN_METHOD.put("hmac-sha384", "HmacSHA384");
        JAVA_SIGN_METHOD.put("hmac-sha512", "HmacSHA512");
    }

    private String clientId;
    private String clientSecret;
    private String signMethod = "hmac-sha256";

    public UacOpenApiSignInterceptor(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public UacOpenApiSignInterceptor(String clientId, String clientSecret, String signMethod) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.signMethod = signMethod;
    }

    private static boolean isEmpty(String str) {
        return Objects.isNull(str) || Objects.equals("", str);
    }

    public static String getSortQueryString(String queryString) {
        if (isEmpty(queryString)) {
            return null;
        }

        String[] sa = queryString.trim().split("&");
        TreeMap<String, String> paramMap = new TreeMap<>();
        for (String kv : sa) {
            if (kv == "") {
                continue;
            }
            int i = kv.indexOf("=");
            if (i > 0) {
                paramMap.put(kv.substring(0, i), kv.substring(i + 1));
            } else {
                paramMap.put(kv, "true");
            }
        }
        if (paramMap.size() == 0) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int i = 1, size = paramMap.size();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            if (i >= size) {
                break;
            }
            stringBuilder.append("&");
            i++;
        }
        return stringBuilder.toString();
    }

    private String getSign(LinkedHashMap<String, String> signMap) throws Exception {
        StringBuilder sb = new StringBuilder();
        int i = 1, size = signMap.size();
        for (Map.Entry<String, String> entry : signMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key.toLowerCase()).append(":");
            if (value != null) {
                sb.append(" ").append(value);
            }

            if (i < size) {
                sb.append("\n");
            }
            i++;
        }
        String strToSign = sb.toString();
        String jsm = JAVA_SIGN_METHOD.get(this.signMethod);
        Mac hmac = Mac.getInstance(jsm);
        SecretKeySpec secret = new SecretKeySpec(this.clientSecret.getBytes(StandardCharsets.UTF_8), jsm);
        hmac.init(secret);
        String signature = BASE64_ENCODER.encodeToString(hmac.doFinal(strToSign.getBytes(StandardCharsets.UTF_8)));
        return signature;
    }

    @Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        try {
            HttpRequestWrapper reqWrapper = HttpRequestWrapper.class.cast(request);
            String sortQueryString = getSortQueryString(reqWrapper.getURI().getQuery());

            // 设置应用端ID header
            reqWrapper.addHeader(HEADER_CLIENT_ID, this.clientId);
            // 设置请求ID header
            reqWrapper.addHeader(HEADER_NONCE, UUID.randomUUID().toString());
            // 设置访问时间header
            String dateStr = LocalDateTime.now(ZoneId.of("GMT")).format(DT_FORMAT);
            reqWrapper.addHeader(HEADER_DATE, dateStr);

            // 设置body摘要header
            HttpEntity entity = null;
            if (reqWrapper.getOriginal() instanceof HttpEntityEnclosingRequestBase) {
                entity = ((HttpEntityEnclosingRequestBase) reqWrapper.getOriginal()).getEntity();
            }
            byte[] bodyBytes = null;
            if (entity != null) {
                bodyBytes = EntityUtils.toByteArray(entity);
            }
            MessageDigest md = MessageDigest.getInstance(bodySignMethod);
            md.update(bodyBytes == null || bodyBytes.length == 0 ? "".getBytes() : bodyBytes);
            String bodyDigest = String.format("%s=%s", bodySignMethod, BASE64_ENCODER.encodeToString(md.digest()));
            reqWrapper.addHeader(HEADER_DIGEST, bodyDigest);

            // 生成签名串
            LinkedHashMap<String, String> signParamMap = new LinkedHashMap<>();
            signParamMap.put(QUERY_STRING, sortQueryString);
            signParamMap.put(HEADER_DATE, dateStr);
            signParamMap.put(HEADER_DIGEST, bodyDigest);
            String signature = getSign(signParamMap);

            // 设置认证Header头
            String auth = String.format("hmac algorithm=\"%s\", headers=\"%s\", signature=\"%s\"",
                    this.signMethod, SIGN_PARAMS, signature);
            reqWrapper.addHeader(HEADER_AUTHORIZATION, auth);

        } catch (Exception e) {
            throw new HttpException("sign exception", e);
        }

    }

    public static void main(String[] args) throws Exception {
        test1();
        test1();
    }

    public static void test1() throws Exception {
        HttpPost httpPost=new HttpPost("https://qa-openapi.szzhijing.com/dyeing-stenter/open-api/queryBaseTenterCraftDevice");
        String body="{\n" +
                "    \"fabricNoList\":[\n" +
                "        \"PB100020\",\n" +
                "        \"PB100022\"\n" +
                "    ],\n" +
                "    \"craftTypeNameSet\":[\n" +
                "        \"成定\",\n" +
                "        \"工艺类型\"\n" +
                "    ]\n" +
                "}";
        httpPost.addHeader("Content-Type","application/json");
        httpPost.setEntity(new StringEntity(body,StandardCharsets.UTF_8));
        CloseableHttpClient client = HttpClients.custom().addInterceptorLast(new UacOpenApiSignInterceptor("c8d1e74111df4572a44966c1408497e3", "0c6e4c4ac9144c44b7b19e24bf6de03e")).build();
        HttpResponse response=client.execute(httpPost);
        System.out.println("返回CODE:"+response.getStatusLine().getStatusCode());
        if(response.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = response.getEntity();
            String respContent = EntityUtils.toString(he,StandardCharsets.UTF_8);
            System.out.println("返回内容："+respContent);
        }else {
            HttpEntity he = response.getEntity();
            String respContent = EntityUtils.toString(he,StandardCharsets.UTF_8);
            System.out.println("返回内容："+respContent);
        }
    }

    public static void test2() throws Exception {
        HttpPost httpPost=new HttpPost("https://qa-openapi.szzhijing.com/dyeing-stenter/open-api/queryAllFinishedProduct");
        String body = "{\n" +
                "    \"startTime\":\"2022-10-20 13:00:00\",\n" +
                "    \"endTime\":\"2022-10-25 13:00:59\"\n" +
                "   \n" +
                "}";
        httpPost.addHeader("Content-Type","application/json");
        httpPost.setEntity(new StringEntity(body,StandardCharsets.UTF_8));
        CloseableHttpClient client = HttpClients.custom().addInterceptorLast(new UacOpenApiSignInterceptor("c8d1e74111df4572a44966c1408497e3", "0c6e4c4ac9144c44b7b19e24bf6de03e")).build();
        HttpResponse response=client.execute(httpPost);
        System.out.println("返回CODE:"+response.getStatusLine().getStatusCode());
        if(response.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = response.getEntity();
            String respContent = EntityUtils.toString(he,StandardCharsets.UTF_8);
            System.out.println("返回内容："+respContent);
        }else {
            HttpEntity he = response.getEntity();
            String respContent = EntityUtils.toString(he,StandardCharsets.UTF_8);
            System.out.println("返回内容："+respContent);
        }
    }
}
