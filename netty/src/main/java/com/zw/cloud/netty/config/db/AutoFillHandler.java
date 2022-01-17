package com.zw.cloud.netty.config.db;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 可以自动赋值的字段处理
 */
@Slf4j
public abstract class AutoFillHandler implements MetaObjectHandler {

    private static final String CREATE_USER = "createUser";
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_USER = "updateUser";
    private static final String UPDATE_TIME = "updateTime";
    private List<String> alwaysSetFields = Arrays.asList(CREATE_TIME, UPDATE_TIME);

    public abstract String getUserId();

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            this.fillStrategy(metaObject, CREATE_TIME, LocalDateTime.now());
            this.fillStrategy(metaObject, CREATE_USER, getUserId());
        } catch (Exception e) {
            log.error("未获取到公共字段信息,{}", e);
        }
        // 更新相关字段也一起初始化
        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            this.fillStrategy(metaObject, UPDATE_TIME, LocalDateTime.now());
            this.fillStrategy(metaObject, UPDATE_USER, getUserId());
        } catch (Exception e) {
            log.error("未获取到公共字段信息,{}", e);
        }
    }

    @Override
    public MetaObjectHandler fillStrategy(MetaObject metaObject, String fieldName, Object fieldVal) {
        // 除了创建和修改时间，其他策略均为:如果属性有值则不覆盖,如果填充值为null则不填充
        if (alwaysSetFields.contains(fieldName)) {
            setFieldValByName(fieldName, fieldVal, metaObject);
        } else if (getFieldValByName(fieldName, metaObject) == null) {
            setFieldValByName(fieldName, fieldVal, metaObject);
        }
        return this;
    }

    public void setAlwaysSetFields(List<String> alwaysSetFields) {
        this.alwaysSetFields = alwaysSetFields;
    }
}
