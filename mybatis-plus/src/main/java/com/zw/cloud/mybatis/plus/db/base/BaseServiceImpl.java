package com.zw.cloud.mybatis.plus.db.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.zw.cloud.mybatis.plus.db.MybatisPlusUtil;
import com.zw.cloud.mybatis.plus.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<E>, T, E> {

    @Autowired
    protected M mapper;

    protected Class<T> dtoClass = currentDtoClass();
    protected Class<E> entityClass = currentEntityClass();

    protected Class<M> currentMapperClass() {
        return (Class<M>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }

    protected Class<T> currentDtoClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    protected Class<E> currentEntityClass() {
        return (Class<E>) ReflectionKit.getSuperClassGenericType(getClass(), 2);
    }

    public T saveOrUpdate(T dto) {
        E entity = BeanUtil.copy(dto, entityClass);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
        Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
        if (idVal != null) {
            mapper.updateById(entity);
        } else {
            mapper.insert(entity);
        }
        BeanUtil.copy(entity, dto);
        return dto;
    }

    public T get(Long id) {
        E entity = mapper.selectById(id);
        return BeanUtil.copy(entity, dtoClass);
    }

    public void delete(Long id) {
        mapper.deleteById(id);
    }

    public List<T> selectList(Object queryTO) {
        QueryWrapper<E> queryWrapper = MybatisPlusUtil.wrapperFrom(queryTO, entityClass);
        return BeanUtil.copyList(mapper.selectList(queryWrapper), dtoClass);
    }
}

