package com.zw.cloud.netty.config.db.typehandlers;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 指定使用哪个第三方json包，config可以通过静态属性控制
 * Tips: 不要配置到扫描目录type-handlers-package，扫描目录为子目录ext
 */
@MappedTypes({Object.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonTypeHandler extends BaseTypeHandler<Object> {
    public static boolean isFastJson = false;
    private AbstractJsonTypeHandler<Object> typeHandler;

    public JsonTypeHandler(Class<?> type) {
        if (isFastJson) {
            typeHandler = new FastjsonTypeHandler(type);
        } else {
            typeHandler = new JacksonTypeHandler(type);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        typeHandler.setNonNullParameter(preparedStatement, i, o, jdbcType);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return typeHandler.getNullableResult(resultSet, s);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return typeHandler.getNullableResult(resultSet, i);
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return typeHandler.getNullableResult(callableStatement, i);
    }
}
