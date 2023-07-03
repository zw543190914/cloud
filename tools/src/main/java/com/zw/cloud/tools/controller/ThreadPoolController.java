package com.zw.cloud.tools.controller;


import com.zw.cloud.tools.service.impl.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tools/test")
public class ThreadPoolController {

    @Autowired
    private AsyncService asyncService;

    @Resource(name = "ioThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor ioThreadPoolTaskExecutor;

    private Logger logger = LoggerFactory.getLogger(ThreadPoolController.class);


    @GetMapping("/query")
    //http://localhost:9040/tools/test/query?id=1
    public void query(@RequestParam("id") Integer id) {
        logger.info("[query]id is {}, thread name is {}", id,Thread.currentThread().getName());
        //Future<String> future = CustomerExecutorService.pool.submit(() -> queryData(id));
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            String result = queryData(id);
            logger.info("[query]id is {}, thread name is {},result is {}", id, Thread.currentThread().getName(), result);
            if (Objects.equals(0,id)) {
                int num = 1/0;
            }
            return result;
        }, ioThreadPoolTaskExecutor).whenComplete((result, ex) -> {
            if (Objects.nonNull(ex)) {
                logger.error("[query]id is {}, thread name is {},result is {},ex is ", id, Thread.currentThread().getName(), result, ex);
            }
        });
    }

    @GetMapping("/asyncTask")
    //http://localhost:9040/tools/test/asyncTask
    public void asyncTask() {
        asyncService.asyncTask();
    }


    private String queryData(Integer id){
        logger.info("[queryData]id is {}, thread name is {}", id,Thread.currentThread().getName());
        return "test:" + id;
    }

}
