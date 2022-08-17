package com.zw.cloud.feignproviderapi.service;

import com.zw.cloud.common.utils.WebResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IFeignProviderService {

    @GetMapping("/user/queryAllUser/{pageNo}/{pageSize}")
    WebResult queryAllUser(@PathVariable Integer pageNo, @PathVariable Integer pageSize);
}
