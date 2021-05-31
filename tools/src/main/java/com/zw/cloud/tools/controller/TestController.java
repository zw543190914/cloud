package com.zw.cloud.tools.controller;


import com.zw.cloud.tools.utils.CustomerExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/tools/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);


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

    @GetMapping("/test")
    //http://localhost:9040/tools/test/test
    public String test1() {
      return "test";
    }

    @GetMapping("/test2")
    //http://localhost:9040/tools/test/test2
    public void test2() {

    }

    private String queryData(String id){
        logger.info("[queryData]id is {}, thread name is {}", id,Thread.currentThread().getName());
        return "test:" + id;
    }

}
