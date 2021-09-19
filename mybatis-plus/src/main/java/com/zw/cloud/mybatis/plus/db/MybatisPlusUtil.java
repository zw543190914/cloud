package com.zw.cloud.mybatis.plus.db;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zw.cloud.mybatis.plus.db.annotations.MpOperator;
import com.zw.cloud.mybatis.plus.db.annotations.MpOrder;
import com.zw.cloud.mybatis.plus.db.entity.OrderItem;
import com.zw.cloud.mybatis.plus.db.entity.PageInfo;
import com.zw.cloud.mybatis.plus.db.entity.PageRequest;
import com.zw.cloud.mybatis.plus.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class MybatisPlusUtil {

    private static IdentifierGenerator idGenerator = null;

    public static void init(IdentifierGenerator idGen) {
        if (idGenerator == null) {
            idGenerator = idGen;
        }
    }

    /**
     * 转成通过PageTO
     *
     * @param page mybatis plus返回IPage
     * @param <D> 实体类型
     *
     * @return 分页结果
     */
    public static <D> PageInfo<D> toPageInfo(IPage<D> page) {
        PageInfo<D> pageInfo = new PageInfo<>();
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setPageNo((int) page.getCurrent());
        pageInfo.setPageSize((int) page.getSize());
        pageInfo.setList(page.getRecords());
        return pageInfo;
    }

    /**
     * 转成通过PageTO
     *
     * @param page mybatis plus返回IPage
     * @param converter 转换器
     * @param <T> 被转换类型
     * @param <D> 目标类型
     *
     * @return 分页结果
     */
    public static <T, D> PageInfo<D> toPageInfo(IPage<T> page, Function<T, D> converter) {
        PageInfo<D> pageInfo = new PageInfo<>();
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setPageNo((int) page.getCurrent());
        pageInfo.setPageSize((int) page.getSize());
        pageInfo.setList(page.getRecords().stream().map(converter).collect(Collectors.toList()));
        return pageInfo;
    }

    /**
     * 转成通过PageTO
     *
     * @param page mybatis plus返回IPage
     * @param clz 目标类
     * @param <T> 被转换类型
     * @param <D> 目标类型
     *
     * @return 分页结果
     */
    public static <T, D> PageInfo<D> toPageInfo(IPage<T> page, Class<D> clz) {
        PageInfo<D> pageInfo = new PageInfo<>();
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setPageNo((int) page.getCurrent());
        pageInfo.setPageSize((int) page.getSize());
        List<D> list = BeanUtil.copyList(page.getRecords(), clz);
        pageInfo.setList(list);
        return pageInfo;
    }

    /**
     * 根据dto获取mybatis-plus查询wrapper
     *
     * @param dto 查询条件对象
     * @param entityClz 实体类型class
     * @param <T> 条件类型
     * @param <E> 实体类型
     *
     * @return 查询条件
     */
    public static <T, E> QueryWrapper<E> wrapperFrom(T dto, Class<E> entityClz) {
        QueryWrapper queryWrapper = new QueryWrapper<E>();
        populateWrapper(queryWrapper, dto, entityClz);
        return queryWrapper;
    }


    /**
     * 根据dto获取mybatis-plus查询lambdaWrapper
     *
     * @param dto 查询条件对象
     * @param entityClz 实体类型class
     * @param <T> 条件类型
     * @param <E> 实体类型
     *
     * @return 查询条件
     */
    public static <T, E> LambdaQueryWrapper<E> lambdaWrapperFrom(T dto, Class<E> entityClz) {
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<E>();
        populateWrapper(queryWrapper, dto, entityClz);
        return queryWrapper;
    }


    public static <T, E> void populateWrapper(AbstractWrapper wrapper, T dto, Class<E> entityClz) {
        if (dto instanceof PageRequest) {
            PageRequest pageRequest = (PageRequest) dto;
            if (pageRequest.getOrderBy() != null && !pageRequest.getOrderBy().isEmpty()) {
                populateWrapper(wrapper, dto, entityClz, true);
                pageRequest.getOrderBy().stream().forEach(orderItem -> {
                    try {
                        Field f = dto.getClass().getDeclaredField(orderItem.getField());
                        MpCondition.populateWrapperOrder(wrapper, entityClz, f, OrderItem.DESC.equals(orderItem.getOrder()));
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException("dto have no field: " + orderItem.getField());
                    }
                });
            } else {
                populateWrapper(wrapper, dto, entityClz, false);
            }
        } else {
            populateWrapper(wrapper, dto, entityClz, false);
        }
    }

    /**
     * 根据DTO的注解，自动加入mybatis-plus查询条件
     *
     * @param wrapper 查询wrapper
     * @param dto 查询dto条件
     * @param entityClz 实体class
     * @param ignoreOrder 是否忽略排序注解，由调用方自己控制
     * @param <T> dto模板类型
     * @param <E> 实体类模板类型
     */
    public static <T, E> void populateWrapper(AbstractWrapper wrapper, T dto, Class<E> entityClz, boolean ignoreOrder) {
        List<Field> allFields = new ArrayList<>();
        Class tmpClz = dto.getClass();
        while (tmpClz != null && !Object.class.getName().equals(tmpClz.getName())) {
            allFields.addAll(Arrays.asList(tmpClz.getDeclaredFields()));
            tmpClz = tmpClz.getSuperclass();
        }

        Class dtoClz = dto.getClass();
        List<MpSortField> sfList = new ArrayList<>();
        allFields.stream().forEach(f -> {
            if (!ignoreOrder) {
                MpOrder mpo = f.getAnnotation(MpOrder.class);
                if (mpo != null) {
                    sfList.add(new MpSortField(mpo, f));
                }
            }

            MpOperator mo = f.getAnnotation(MpOperator.class);
            if (mo != null) {
                PropertyDescriptor pd = null;
                try {
                    pd = new PropertyDescriptor(f.getName(), dtoClz);
                    Method method = pd.getReadMethod();
                    Object fieldValue = method.invoke(dto);
                    List<String> nullConditions = Arrays.asList(MpCondition.OP_IS_NULL, MpCondition.OP_IS_NOT_NULL);
                    boolean isBlankVal = fieldValue == null || StringUtils.isEmpty(fieldValue.toString());
                    if (!nullConditions.contains(mo.value()) && isBlankVal) {
                        return;
                    }
                    MpCondition.populateWrapper(wrapper, mo, entityClz, f, fieldValue);
                } catch (Exception e) {
                    throw new RuntimeException("构建wrapper异常", e);
                }
            }
        });

        if (sfList.size() > 0) {
            Collections.sort(sfList);
            sfList.forEach(sf -> MpCondition.populateWrapper(wrapper, sf.getMpo(), entityClz, sf.getField()));
        }
    }

    private static class MpSortField implements Comparable<MpSortField> {
        private MpOrder mpo;
        private Field field;

        public MpSortField(MpOrder mpo, Field field) {
            this.mpo = mpo;
            this.field = field;
        }

        public MpOrder getMpo() {
            return mpo;
        }

        public Field getField() {
            return field;
        }

        @Override
        public int compareTo(MpSortField o) {
            return this.mpo.seq() - o.mpo.seq();
        }
    }

    public static Number genNextId() {
        if (idGenerator == null) {
            throw new RuntimeException("please init MybatisPlusUtil");
        }
        return idGenerator.nextId(null);
    }


}
