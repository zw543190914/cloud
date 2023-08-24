package com.zw.cloud.tools.controller;

import com.zw.cloud.tools.entity.PostCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/desensitization-serialize")
@Slf4j
public class DesensitizationSerializeController {

    @RequestMapping("/test")
    //http://localhost:9040/desensitization-serialize/test
    public PostCard testDesensitization(){
        PostCard postCard = new PostCard();
        postCard.setName("张三");
        postCard.setTel("13722224444");
        postCard.setTitle("测试其他");
        postCard.setAddress("上海市嘉定区江桥镇莱茵华庭");
        postCard.setEmail("543190914@qq.com");
        return postCard;
    }

}
