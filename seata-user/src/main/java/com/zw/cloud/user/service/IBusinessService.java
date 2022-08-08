package com.zw.cloud.user.service;

public interface IBusinessService {

    void createOrderAT(String userId,String commodityCode,Integer count, Integer money);

    void createOrderTcc(String userId, String commodityCode, Integer count, Integer money);
}
