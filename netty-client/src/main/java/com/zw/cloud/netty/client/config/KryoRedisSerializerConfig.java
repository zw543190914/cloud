package com.zw.cloud.netty.client.config;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
@NoArgsConstructor
public class KryoRedisSerializerConfig<T> implements RedisSerializer<T> {
    /**
     * 由于 Kryo 不是线程安全的。每个线程都应该有自己的 Kryo，Input 或 Output 实例。
     * 所以，使用 ThreadLocal 存放 Kryo 对象
     * 这样减少了每次使用都实例化一次 Kryo 的开销又可以保证其线程安全
     */
    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        // 设置循环引用
        kryo.setReferences(true);
        // 设置序列化时对象是否需要设置对象类型
        kryo.setRegistrationRequired(false);
        return kryo;
    });

    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return EMPTY_BYTE_ARRAY;
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Output output = new Output(baos)) {
            Kryo kryo = KRYO_THREAD_LOCAL.get();
            // 对象的 Class 信息一起序列化
            kryo.writeClassAndObject(output, t);
            KRYO_THREAD_LOCAL.remove();
            return output.toBytes();
        } catch (Exception e) {
            throw new SerializationException("Could not write byte[]: " + e.getMessage(), e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             Input input = new Input(bais)) {
            Kryo kryo = KRYO_THREAD_LOCAL.get();
            // 通过存储在字节数组中的 Class 信息来确定反序列的类型
            Object object = kryo.readClassAndObject(input);
            KRYO_THREAD_LOCAL.remove();
            return (T) object;
        } catch (IOException e) {
            throw new SerializationException("Could not read byte[]: " + e.getMessage(), e);
        }
    }
}
