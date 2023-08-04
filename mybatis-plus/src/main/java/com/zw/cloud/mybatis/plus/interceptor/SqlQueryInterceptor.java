package com.zw.cloud.mybatis.plus.interceptor;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.mybatis.plus.cache.ShareCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.SqlNode;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;

@Intercepts(
        {@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})}
)
@Slf4j
public class SqlQueryInterceptor implements Interceptor {

    private static final String SQL_WHERE = "where";
    private static final String ORG_CODE = "org_code";

    private ShareCache shareCache;

    public SqlQueryInterceptor(ShareCache shareCache) {
        this.shareCache = shareCache;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject
                .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                        new DefaultReflectorFactory());
        // 获取mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        SqlCommandType sqlType = mappedStatement.getSqlCommandType();
        if (!SqlCommandType.SELECT.equals(sqlType)) {
            return invocation.proceed();
        }
        BoundSql boundSql = statementHandler.getBoundSql();
        log.info("boundSql is {}",JSON.toJSONString(boundSql));
        String oriSql = boundSql.getSql();
        try {
            String newSql = installSql(oriSql);
            Field field = boundSql.getClass().getDeclaredField("sql");
            field.setAccessible(true);
            field.set(boundSql, newSql);
        } catch (Exception e) {
            log.error("mybatis sql转换异常:", e);
            throw new BizException("sql转换异常:" + e);
        }

        return invocation.proceed();
    }

    String installSql(String sql) {

        log.info("修改前的sql: {}", sql);
        if (!StringUtils.containsIgnoreCase(sql,SQL_WHERE) || !StringUtils.containsIgnoreCase(sql,ORG_CODE)) {
            return sql;
        }
        String[] sqlParts = sql.split("(?i)where");
        String replace = sqlParts[1].replace("org_code = ?", " (1=1 or org_code = ?) ");
        StringBuilder sqlBuild = new StringBuilder(sqlParts[0]);
        sqlBuild.append(SQL_WHERE).append(" org_code in (");
        // TODO 查询权限工厂
        List<String> orgCodeList = shareCache.queryShareListFromCache("orgCode");
        orgCodeList.forEach(s -> sqlBuild.append("'").append(s).append("'").append(","));

        sql = sqlBuild.substring(0,sqlBuild.length() -1) + ") AND " + replace;
        log.info("修改后的sql: {}", sql);
        return sql;
    }


    public static void main(String[] args) {
        String input = "This is WHERE the string will be split where ee wHEre ignoring case";
        String[] parts = input.split("(?i)where");
        System.out.println(JSON.toJSONString(parts));
    }

}
