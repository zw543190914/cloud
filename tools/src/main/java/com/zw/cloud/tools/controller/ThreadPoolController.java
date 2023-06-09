package com.zw.cloud.tools.controller;


import com.zw.cloud.tools.service.impl.ScheduleService;
import com.zw.cloud.tools.utils.CustomerExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/tools/test")
public class ThreadPoolController {

    @Autowired
    private ScheduleService scheduleService;

    @Resource(name = "ioThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor ioThreadPoolTaskExecutor;

    private Logger logger = LoggerFactory.getLogger(ThreadPoolController.class);


    @GetMapping("/query")
    //http://localhost:9040/tools/test/query?id=1
    public String query(@RequestParam("id") String id)throws Exception{
        logger.info("[query]id is {}, thread name is {}", id,Thread.currentThread().getName());
        Future<String> future = CustomerExecutorService.pool.submit(() -> queryData(id));

        //阻塞
        String result = future.get();
        logger.info("[query]id is {}, thread name is {},result is {}", id,Thread.currentThread().getName(),result);
        return result;
    }

    @GetMapping("/testAsync1")
    //http://localhost:9040/tools/test/testAsync1
    public void testAsync1() throws Exception{
        scheduleService.test();
    }


    private String queryData(String id){
        logger.info("[queryData]id is {}, thread name is {}", id,Thread.currentThread().getName());
        return "test:" + id;
    }

}
