package com.zw.cloud.order.service.api;

import com.zw.cloud.order.entity.OrderTbl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-08-05
 */
public interface IOrderTblService extends IService<OrderTbl> {

    void insert(String userId,String commodityCode,Integer count, Integer money);
}
