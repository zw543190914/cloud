package com.zw.cloud.feignconsumer.service;

import com.zw.cloud.common.utils.WebResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用 @RequestMapping 报错
 */
@FeignClient(value = "feign-provider")
public interface IFeignConsumerService {

    @GetMapping("/user/queryAllUser/{pageNo}/{pageSize}")
    WebResult queryAllUser(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize);
}
