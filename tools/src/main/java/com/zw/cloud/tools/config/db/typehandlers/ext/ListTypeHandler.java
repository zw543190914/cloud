package com.zw.cloud.tools.config.db.typehandlers.ext;

import com.zw.cloud.tools.config.db.typehandlers.JsonTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

/**
 * 自动处理java.util.List
 * <br>CreateDate 2021/3/25
 *
 * @author relam
 */
@MappedTypes({List.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListTypeHandler extends JsonTypeHandler {

    public ListTypeHandler(Class<?> type) {
        super(type);
    }

}
