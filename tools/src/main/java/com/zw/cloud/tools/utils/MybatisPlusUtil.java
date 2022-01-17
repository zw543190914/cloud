package com.zw.cloud.tools.utils;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

public class MybatisPlusUtil {
    private static IdentifierGenerator idGenerator = null;

    public static void init(IdentifierGenerator idGen) {
        if (idGenerator == null) {
            idGenerator = idGen;
        }
    }
    public static Number genNextId() {
        if (idGenerator == null) {
            throw new RuntimeException("please init MybatisPlusUtil");
        }
        return idGenerator.nextId(null);
    }
}
