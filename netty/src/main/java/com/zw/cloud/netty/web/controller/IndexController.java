package com.zw.cloud.netty.web.controller;


import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class IndexController {


    AtomicInteger atomicInteger = new AtomicInteger(1);

    /*@GetMapping("/index")
    //http://localhost:18092/index
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView("socket");
        mav.addObject("uid", atomicInteger.getAndAdd(1));
        return mav;
    }*/

}

