package com.zw.cloud.tools.config.ocr;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OcrConfig {

    @Value("${logging.config}")
    private String logFile;

    @Bean
    public AipOcr aipOcr() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr("25932253", "C0QTzsRWI4WwtshcI4ehtWH3", "KTWoTTkV6N9dMnCnjV16XtOj614cB7QV");

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", logFile);
        return client;
    }

}
