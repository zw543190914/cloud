package com.zw.cloud.mybatis.plus.db;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.zw.cloud.mybatis.plus.db.annotations.MpOperator;
import com.zw.cloud.mybatis.plus.db.annotations.MpOrder;
import org.apache.commons.lang3.StringUtils;


import java.lang.reflect.Field;
import java.util.*;

/**
 * mybatis-plus动态条件
 */
public class MpCondition {

    public static final String OP_EQ = "equal";
    /**
     * 模糊查询，用于字符串
     */
    public static final String OP_LIKE = "like";
    public static final String OP_LIKE_LEFT = "likeLeft";
    public static final String OP_LIKE_RIGHT = "likeRight";
    /**
     * 不等于
     */
    public static final String OP_NOT_EQ = "ne";
    /**
     * 在两者范围内，仅注解于集合或数组
     */
    public static final String OP_RANGE = "range";
    /**
     * 大于
     */
    public static final String OP_GT = "getterThan";
    /**
     * 大于等于
     */
    public static final String OP_GTE = "getterEqualThan";
    /**
     * 小于
     */
    public static final String OP_LT = "lessThan";
    /**
     * 小于等于
     */
    public static final String OP_LTE = "lessEqualThan";
    public static final String OP_IS_NULL = "isNull";
    public static final String OP_IS_NOT_NULL = "isNotNull";
    public static final String OP_IN = "in";
    public static final String OP_NOT_IN = "notIn";

    public static final String ORDER_ASC = "asc";
    public static final String ORDER_DESC = "desc";

    private static String getColumnNameByField(Class clz, String fieldName) {
        return Optional.ofNullable(LambdaUtils.getColumnMap(clz))
                .map(colMap -> colMap.get(LambdaUtils.formatKey(fieldName)))
                .map(cc -> cc.getColumn())
                .orElseThrow(() -> new RuntimeException(String.format("对应的表或字段不存在，class:%s, fieldName:%s", clz.getSimpleName(), fieldName)));
    }

    public static void populateWrapper(AbstractWrapper wrapper, MpOperator mo, Class clz, Field field, Object fieldValue) {
        if (mo == null || StringUtils.isEmpty(mo.value())) {
            return;
        }

        String op = mo.value();
        String columnName = StringUtils.isEmpty(mo.columnName()) ? getColumnNameByField(clz, field.getName()) : mo.columnName();
        switch (op) {
            case OP_LIKE:
                wrapper.like(columnName, fieldValue);
                break;
            case OP_LIKE_LEFT:
                wrapper.likeLeft(columnName, fieldValue);
                break;
            case OP_LIKE_RIGHT:
                wrapper.likeRight(columnName, fieldValue);
                break;
            case OP_NOT_EQ:
                wrapper.ne(columnName, fieldValue);
                break;
            case OP_RANGE:
                List<Object> list;
                if (field.getType().isArray()) {
                    list = Arrays.asList(fieldValue);
                } else if (fieldValue instanceof Collection) {
                    list = new ArrayList<>((Collection) fieldValue);
                } else {
                    list = Collections.EMPTY_LIST;
                }

                if (list.size() >= 1 && list.get(0) != null && StringUtils.isNotEmpty(list.get(0).toString())) {
                    wrapper.ge(columnName, list.get(0));
                }
                if (list.size() >= 2 && list.get(1) != null && StringUtils.isNotEmpty(list.get(0).toString())) {
                    wrapper.le(columnName, list.get(1));
                }
                break;
            case OP_GT:
                wrapper.gt(columnName, fieldValue);
                break;
            case OP_GTE:
                wrapper.ge(columnName, fieldValue);
                break;
            case OP_LT:
                wrapper.lt(columnName, fieldValue);
                break;
            case OP_LTE:
                wrapper.le(columnName, fieldValue);
                break;
            case OP_IS_NOT_NULL:
                wrapper.isNotNull(columnName);
                break;
            case OP_IS_NULL:
                wrapper.isNull(columnName);
                break;
            case OP_IN:
                Assert.notNull(fieldValue, "in条件不能为空");
                Assert.isTrue(fieldValue instanceof Collection || fieldValue instanceof Object[], "in条件仅支持数组或Collection");
                if (fieldValue instanceof Collection) {
                    wrapper.in(columnName, ((Collection) fieldValue).toArray(new Object[]{}));
                } else {
                    wrapper.in(columnName, fieldValue);
                }
                break;
            case OP_NOT_IN:
                Assert.notNull(fieldValue, "not in条件不能为空");
                Assert.isTrue(fieldValue instanceof Collection || fieldValue instanceof Object[], "not in条件仅支持数组或Collection");
                if (fieldValue instanceof Collection) {
                    wrapper.notIn(columnName, ((Collection) fieldValue).toArray(new Object[]{}));
                } else {
                    wrapper.notIn(columnName, fieldValue);
                }
                break;
            default:
                wrapper.eq(columnName, fieldValue);
        }

    }

    public static <E> void populateWrapper(AbstractWrapper wrapper, MpOrder mpo, Class<E> clz, Field field) {
        if (mpo == null) {
            return;
        }

        String columnName = StringUtils.isEmpty(mpo.columnName()) ? getColumnNameByField(clz, field.getName()) : mpo.columnName();
        if (ORDER_DESC.equals(mpo.order())) {
            wrapper.orderByDesc(columnName);
        } else {
            wrapper.orderByAsc(columnName);
        }
    }

    public static <E> void populateWrapperOrder(AbstractWrapper wrapper, Class<E> clz, Field field, boolean isDesc) {
        String columnName = null;
        if (field.isAnnotationPresent(MpOrder.class)) {
            columnName = field.getAnnotation(MpOrder.class).columnName();
        }
        if (StringUtils.isEmpty(columnName)) {
            columnName = getColumnNameByField(clz, field.getName());
        }

        if (isDesc) {
            wrapper.orderByDesc(columnName);
        } else {
            wrapper.orderByAsc(columnName);
        }
    }

}
