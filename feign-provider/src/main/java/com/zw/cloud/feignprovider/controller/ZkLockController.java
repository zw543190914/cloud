/*
package com.zw.cloud.feignprovider.controller;

import com.zw.cloud.feignprovider.zk.ZkInterProcessMutexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/zk")
public class ZkLockController {

    @Autowired
    private ZkInterProcessMutexService service;

    //http://localhost:9000/provider/zk
    @GetMapping
    public void test() throws Exception{
        service.testZkLock();
    }
}
*/
