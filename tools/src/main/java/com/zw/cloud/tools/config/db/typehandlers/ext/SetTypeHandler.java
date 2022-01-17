package com.zw.cloud.tools.config.db.typehandlers.ext;

import com.zw.cloud.tools.config.db.typehandlers.JsonTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.Set;

/**
 * 自动处理java.util.Set
 * <br>CreateDate 2021/3/25
 *
 * @author relam
 */
@MappedTypes({Set.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class SetTypeHandler extends JsonTypeHandler {

    public SetTypeHandler(Class<?> type) {
        super(type);
    }

}
