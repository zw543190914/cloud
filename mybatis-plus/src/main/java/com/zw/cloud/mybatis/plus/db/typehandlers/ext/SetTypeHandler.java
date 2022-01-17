package com.zw.cloud.mybatis.plus.db.typehandlers.ext;

import com.zw.cloud.mybatis.plus.db.typehandlers.JsonTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.Set;


@MappedTypes({Set.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class SetTypeHandler extends JsonTypeHandler {

    public SetTypeHandler(Class<?> type) {
        super(type);
    }

}
