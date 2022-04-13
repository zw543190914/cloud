package com.zw.cloud.tools.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhengwei
 * @date 2022/4/13 19:02
 */
@Configuration
public class LocalDateTimeConfig {
    /**
     * 设置localdatetime的序列化与反序列化的方式
     *
     * @return
     */
    @Bean(name = "mapperObject")
    public ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        // "下面两个根据实际情况选择 看要返回什么格式"
        // "返回时间戳 注意这里的LocalDateTimeSerializer是上面自己实现的一个"
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

        // "返回指定字符串格式 这里的LocalDateTimeSerializer是com.fasterxml.jackson.datatype.jsr310.ser下的"
        //javaTimeModule.addSerializer(LocalDateTime.class, new com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 这个反序列化。接受前端传来的格式
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 这两个得加上。不然他俩默认返回了List结构
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        om.registerModule(javaTimeModule);
        return om;
    }

}
