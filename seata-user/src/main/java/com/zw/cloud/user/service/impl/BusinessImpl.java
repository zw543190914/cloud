package com.zw.cloud.user.service.impl;

import com.zw.cloud.user.service.IAccountService;
import com.zw.cloud.user.service.IBusinessService;
import com.zw.cloud.user.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessImpl implements IBusinessService {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAccountService accountService;

    private Logger logger = LoggerFactory.getLogger(BusinessImpl.class);
    @Override
    @GlobalTransactional(name = "order")
    public void createOrder(String userId, String commodityCode, Integer count, Integer money) {
        try {
            orderService.insert(userId, commodityCode, count, money);
            accountService.update(userId, money);
        } catch (Exception e) {
            logger.error("[BusinessImpl][createOrder] error is {}",e);
            throw e;
        }
        //throw new RuntimeException("error");
    }
}
