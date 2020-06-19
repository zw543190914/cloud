package com.zw.cloud.order.service;

import com.zw.cloud.order.entity.OrderTbl;

import java.util.List;

public interface IOrderService {

    void insert(String userId,String commodityCode,Integer count, Integer money);

    List<OrderTbl> query();
}
