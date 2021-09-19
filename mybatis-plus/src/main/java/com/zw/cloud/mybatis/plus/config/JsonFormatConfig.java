package com.zw.cloud.mybatis.plus.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Configuration(proxyBeanMethods = false)
public class JsonFormatConfig {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String dateTimePattern;

    @Value("${spring.jackson.time-format:HH:mm:ss}")
    private String timePattern;

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern));
    }

    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimePattern)) {
            @Override
            public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
                // 支持时间截（秒数)转LocalDateTime
                if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                    long timestamp = Long.parseLong(parser.getText().trim());
                    return Instant.ofEpochSecond(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
                }
                return super.deserialize(parser, context);
            }
        };
    }

    @Bean
    public LocalTimeSerializer localTimeSerializer() {
        return new LocalTimeSerializer(DateTimeFormatter.ofPattern(timePattern));
    }

    @Bean
    public LocalTimeDeserializer localTimeDeserializer() {
        return new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timePattern));
    }

    @Bean
    @Lazy
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(LocalDateTimeSerializer localDateTimeSerializer, LocalDateTimeDeserializer localDateTimeDeserializer,
                                                                                       LocalTimeSerializer localTimeSerializer, LocalTimeDeserializer localTimeDeserializer) {
        return builder -> builder
                .serializerByType(LocalDateTime.class, localDateTimeSerializer)
                .deserializerByType(LocalDateTime.class, localDateTimeDeserializer)
                .serializerByType(LocalTime.class, localTimeSerializer)
                .deserializerByType(LocalTime.class, localTimeDeserializer)
                .serializerByType(Long.TYPE, ToStringSerializer.instance)
                .serializerByType(Long.class, ToStringSerializer.instance);
    }

}
