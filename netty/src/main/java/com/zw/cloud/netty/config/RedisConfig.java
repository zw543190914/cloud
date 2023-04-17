package com.zw.cloud.netty.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // key序列化
        RedisSerializer<?> keySerializer = new StringRedisSerializer();
        // value序列化
        KryoRedisSerializerConfig<Object> valueSerializer = new KryoRedisSerializerConfig<>();
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        // key序列化
        redisTemplate.setKeySerializer(keySerializer);
        // value序列化
        redisTemplate.setValueSerializer(valueSerializer);
        // Hash key序列化
        redisTemplate.setHashKeySerializer(keySerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
