package com.zw.cloud.sentinel.service.impl;

import com.zw.cloud.global.response.wrapper.entity.WebResult;
import com.zw.cloud.sentinel.service.ISentinelService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class FeignProviderCallbackServiceImpl implements ISentinelService {
    @Override
    public WebResult queryAllUser(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return WebResult.fail("服务降级返回。。。");
    }
}
