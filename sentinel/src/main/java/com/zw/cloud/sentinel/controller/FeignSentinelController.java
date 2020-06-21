package com.zw.cloud.sentinel.controller;

import com.zw.cloud.common.utils.DateTimeUtils;
import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.sentinel.service.ISentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel/feign")
public class FeignSentinelController {
    @Autowired
    private ISentinelService sentinelService;

    @GetMapping("/{pageNo}/{pageSize}")
    //http://localhost:8090/sentinel/feign/1/10
    public WebResult queryAllUser(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return sentinelService.queryAllUser(pageNo, pageSize);
    }
}
