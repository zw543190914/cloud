package com.zw.cloud.feignprovider.impl;

import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.feignproviderapi.service.IFeignProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class FeignProviderImpl implements IFeignProviderService {

    @Value("${server.port}")
    private int port;

    private Logger logger = LoggerFactory.getLogger(FeignProviderImpl.class);

    @Override
    public WebResult queryAllUser(Integer pageNo, Integer pageSize) {
        logger.info("[FeignProviderImpl][queryAllUser] server.port is {}",port);
        return WebResult.success().withData("FeignProvider02");
    }
}
