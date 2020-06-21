package com.zw.cloud.feignconsumer.controller;

import com.zw.cloud.feignconsumer.entity.Type;
import com.zw.cloud.feignconsumer.entity.Type2;
import com.zw.cloud.feignconsumer.service.impl.DatesorceConsumerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer/datesource")
public class DatesorceConsumerController {

    @Autowired
    private DatesorceConsumerImpl datesorceConsumerService;

    @PostMapping("/type")
    //http://localhost:9010/consumer/datesource/type
    public void insertUser(@RequestBody Type type) {
        datesorceConsumerService.insertType(type);
    }

    @GetMapping("/type")
    //http://localhost:9010/consumer/datesource/type
    public List<Type> queryAllTypes() {
        return datesorceConsumerService.queryAllTypes();
    }


    @PostMapping("/type2")
    //http://localhost:9010/consumer/datesource/user
    public void insertUser(@RequestBody Type2 type2) {
        datesorceConsumerService.insertType2(type2);
    }



    @GetMapping("/type2")
    //http://localhost:9010/consumer/datesource/user
    public List<Type2> queryAllUsers() {
        return datesorceConsumerService.queryAllType2();
    }


}
