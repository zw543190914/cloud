package com.zw.cloud.user.service;

public interface IBusinessService {

    void createOrder(String userId,String commodityCode,Integer count, Integer money);
}
