package com.zw.cloud.feignprovider02.controller;

import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.feignproviderapi.service.IFeignProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class FeignProviderController {
    @Autowired
    private IFeignProviderService providerService;

    @GetMapping("/queryAllUser/{pageNo}/{pageSize}")
    //http://localhost:9000/user/queryAllUser/1/10
    public WebResult queryAllUser(@PathVariable Integer pageNo, @PathVariable Integer pageSize){
        return providerService.queryAllUser(pageNo, pageSize);
    }
}
