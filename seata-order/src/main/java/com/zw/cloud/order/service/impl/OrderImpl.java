package com.zw.cloud.order.service.impl;

import com.zw.cloud.order.dao.OrderTblMapper;
import com.zw.cloud.order.entity.OrderTbl;
import com.zw.cloud.order.entity.OrderTblExample;
import com.zw.cloud.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
@Service
public class OrderImpl implements IOrderService {
    @Autowired
    private OrderTblMapper mapper;

    @Override
    @GlobalTransactional
    public void insert(String userId,String commodityCode,Integer count, Integer money){
        OrderTbl order = new OrderTbl();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(money);
        mapper.insertSelective(order);
    }

    @Override
    public List<OrderTbl> query(){
        return mapper.selectByExample(new OrderTblExample());
    }
}
