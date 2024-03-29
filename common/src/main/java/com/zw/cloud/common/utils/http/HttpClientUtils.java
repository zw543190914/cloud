package com.zw.cloud.common.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class HttpClientUtils {
    private static CloseableHttpClient client;
    static {
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
        //检测有效连接的间隔
        clientConnectionManager.setValidateAfterInactivity(2000);
        //设定连接池最大数量
        clientConnectionManager.setMaxTotal(200);
        //设定默认单个路由的最大连接数（由于本处只使用一个路由地址所以设定为连接池大小）
        clientConnectionManager.setDefaultMaxPerRoute(50);
        client = HttpClients.createMinimal(clientConnectionManager);
    }
    public static String doGet(String path, Map<String, String> param, Map<String, String> headers) {
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = wrapClient(path);
        // 创建uri
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(path);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            httpGet = new HttpGet(uri);
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            log.error("[HttpClientUtils][doGet] error is ",e);
            throw new RuntimeException("[发送Get请求错误：]" + e.getMessage());
        } finally {
            try {
                if (Objects.nonNull(httpGet)) {
                    httpGet.releaseConnection();
                }
                if (Objects.nonNull(response)) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("[HttpClientUtils][doGet]releaseConnection error is ",e);
            }
        }
        return null;
    }

    public static String doPostJson(String url, String jsonParam, Map<String, String> headers) {
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = wrapClient(url);
        try {
            httpPost = new HttpPost(url);
            //addHeader，如果Header没有定义则添加，已定义则不变，setHeader会重新赋值
            httpPost.addHeader("Content-type","application/json;charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            StringEntity entity = new StringEntity(jsonParam, StandardCharsets.UTF_8);
//            entity.setContentType("text/json");
//            entity.setContentEncoding(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
            httpPost.setEntity(entity);
            //是否有header
            if (MapUtils.isNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }

        } catch (Exception e) {
            log.error("[HttpClientUtils][doPostJson] error is ",e);
            throw new RuntimeException("[发送POST请求错误：]" + e.getMessage());
        } finally {
            try {
                if (Objects.nonNull(httpPost)) {
                    httpPost.releaseConnection();
                }
                if (Objects.nonNull(response)) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("[HttpClientUtils][doPostJson]releaseConnection error is ",e);
            }
        }
        return null;
    }

    private static CloseableHttpClient wrapClient(String url) {
        if (url.startsWith("https")) {
            client = getCloseableHttpsClients();
        }
        return client;
    }
    private static CloseableHttpClient getCloseableHttpsClients() {
        // 采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext)).build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);
        // 创建自定义的httpsclient对象
        return HttpClients.custom().setConnectionManager(connManager).build();
    }
    private static SSLContext createIgnoreVerifySSL() {
        // 创建套接字对象
        SSLContext sslContext ;
        try {
            //指定TLS版本
            sslContext = SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            log.error("[HttpClientUtils][doPostJson]createIgnoreVerifySSL error is ",e);
            throw new RuntimeException("[创建套接字失败:] " + e.getMessage());
        }
        // 实现X509TrustManager接口，用于绕过验证
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        try {
            //初始化sslContext对象
            sslContext.init(null, new TrustManager[]{trustManager}, null);
        } catch (KeyManagementException e) {
            log.error("[HttpClientUtils][doPostJson]sslContext.init error is ",e);
            throw new RuntimeException("[初始化套接字失败:] " + e.getMessage());
        }
        return sslContext;
    }
}
