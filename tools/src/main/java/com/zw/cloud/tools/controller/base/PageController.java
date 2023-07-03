package com.zw.cloud.tools.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class PageController {

    @GetMapping("/ajaxIndex")
    //http://localhost:9040/ajaxIndex
    public String testPage(){
        return "ajaxIndex";
    }

    @GetMapping("/chatGPT")
    //http://localhost:9040/chatGPT
    public String chatGPT(){
        return "index3";
    }
}
