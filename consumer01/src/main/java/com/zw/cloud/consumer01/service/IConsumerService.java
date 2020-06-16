package com.zw.cloud.consumer01.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("provider01")
@RequestMapping("/provider")
public interface IConsumerService {
    @GetMapping("/test/{msg}")
    String detail(@PathVariable String msg);
}
