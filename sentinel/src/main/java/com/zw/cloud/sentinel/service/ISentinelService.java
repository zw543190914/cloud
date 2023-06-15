package com.zw.cloud.sentinel.service;

import com.zw.cloud.global.response.wrapper.entity.WebResult;
import com.zw.cloud.sentinel.service.impl.FeignProviderCallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用 @RequestMapping 报错
 */
@FeignClient(value = "feign-provider",fallback = FeignProviderCallbackServiceImpl.class)
public interface ISentinelService {

    @GetMapping("/user/queryAllUser/{pageNo}/{pageSize}")
    WebResult queryAllUser(@PathVariable Integer pageNo, @PathVariable Integer pageSize);
}
