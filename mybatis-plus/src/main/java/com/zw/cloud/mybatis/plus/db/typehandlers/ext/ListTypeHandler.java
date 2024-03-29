package com.zw.cloud.mybatis.plus.db.typehandlers.ext;

import com.zw.cloud.mybatis.plus.db.typehandlers.JsonTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;


@MappedTypes({List.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListTypeHandler extends JsonTypeHandler {

    public ListTypeHandler(Class<?> type) {
        super(type);
    }

}
