package com.zw.cloud.provider01.controller;

import com.zw.cloud.provider01.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {
   @Autowired
   private IProviderService providerService;

    @GetMapping("/test/{msg}")
    //http://127.0.0.1:8010/provider/test/sss
    public String test(@PathVariable String msg){
        return providerService.detail(msg);
    }
}