package com.zw.cloud.feignconsumer.service.impl;

import com.zw.cloud.feignconsumer.entity.Type;
import com.zw.cloud.feignconsumer.entity.Type2;
import com.zw.cloud.feignconsumer.entity.Type2Example;
import com.zw.cloud.feignconsumer.entity.TypeExample;
import com.zw.cloud.feignconsumer.test.dao.TypeMapper;
import com.zw.cloud.feignconsumer.test2.dao.Type2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DatesorceConsumerImpl {

    @Autowired
    private Type2Mapper type2Mapper;
    @Autowired
    private TypeMapper typeMapper;

    @Transactional(transactionManager = "test1TransactionManager")
    public void insertType(Type type) {
        typeMapper.insertSelective(type);
    }

    public List<Type> queryAllTypes() {
        return typeMapper.selectByExample(new TypeExample());
    }

    @Transactional(transactionManager = "test2TransactionManager")
    public void insertType2(Type2 type) {
        type2Mapper.insertSelective(type);
    }

    public List<Type2> queryAllType2() {
        return type2Mapper.selectByExample(new Type2Example());
    }

}
