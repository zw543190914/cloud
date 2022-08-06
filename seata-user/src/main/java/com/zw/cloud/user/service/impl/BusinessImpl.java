package com.zw.cloud.user.service.impl;

import com.zw.cloud.user.service.IAccountService;
import com.zw.cloud.user.service.IBusinessService;
import com.zw.cloud.user.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessImpl implements IBusinessService {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAccountService accountService;

    @Override
    @GlobalTransactional(name = "order")
    public void createOrderAT(String userId, String commodityCode, Integer count, Integer money) {
        log.info("[BusinessImpl][createOrder] createOrderAT ");

        orderService.insert(userId, commodityCode, count, money);
        accountService.update(userId, money);

        //throw new RuntimeException("error");
    }

    @Override
    @GlobalTransactional(name = "orderTcc")
    public void createOrderTcc(String userId, String commodityCode, Integer count, Integer money) {
        log.info("[BusinessImpl][createOrder] createOrderTcc ");

        orderService.insertTcc(userId, commodityCode, count, money);
        accountService.updateTcc(userId, money);

        //throw new RuntimeException("error");
    }
}
