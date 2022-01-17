package com.zw.cloud.netty.web.controller;

import com.zw.cloud.netty.ide.annotation.Ide;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ide")
public class IdeTestController {

    @GetMapping
    @Ide(timeOut = 5)
    //http://localhost:18092/ide?name=test
    public String ideTest(String name){
        return name;
    }
}
