//package com.zw.cloud.mybatis.plus.config.sharding.jdbc;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.MappedJdbcTypes;
//import org.apache.ibatis.type.MappedTypes;
//import org.springframework.stereotype.Component;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
///**
// * sharding-jdbc LocalDate转换问题
// */
//@Component
////定义转换器支持的JAVA类型
//@MappedTypes(LocalDate.class)
////定义转换器支持的数据库类型
//@MappedJdbcTypes(value = JdbcType.DATE, includeNullJdbcType = true)
//public class LocalDateTypeHandle extends BaseTypeHandler<LocalDate> {
//    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType)
//            throws SQLException {
//        ps.setObject(i, parameter);
//    }
//
//    @Override
//    public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        String target = rs.getString(columnName);
//        if (StringUtils.isEmpty(target)) {
//            return null;
//        }
//        return LocalDate.parse(target, dateTimeFormatter);
//    }
//
//    @Override
//    public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        String target = rs.getString(columnIndex);
//        if (StringUtils.isEmpty(target)) {
//            return null;
//        }
//        return LocalDate.parse(target, dateTimeFormatter);
//    }
//
//    @Override
//    public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        String target = cs.getString(columnIndex);
//        if (StringUtils.isEmpty(target)) {
//            return null;
//        }
//        return LocalDate.parse(target, dateTimeFormatter);
//    }
//}
//
