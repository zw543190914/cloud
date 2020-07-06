package com.zw.cloud.tools.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.zw.cloud.db.entity.User;
import com.zw.cloud.tools.utils.AliyunAiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@RequestMapping("/tools/ai/aliyun")
public class AliyunAiController {

    @Autowired
    private AliyunAiUtils aliyunAiUtils;

    @PostMapping
    //http://localhost:9040//tools/ai/aliyun
    public boolean addFace(String id,String name,MultipartFile file)throws Exception{
        String image = Base64.getEncoder().encodeToString(file.getBytes());
        return aliyunAiUtils.addFace(id,name,image);
    }

    @DeleteMapping
    //http://localhost:9040//tools/ai/aliyun?id=&name=
    public boolean deleteFace(String id,String name){
        return aliyunAiUtils.deleteFace(id, name);
    }

    @PostMapping("/recognizeFace")
    // 查找注册库中的人脸
    //http://localhost:9040//tools/ai/aliyun/recognizeFace
    public CommonResponse recognizeFace(MultipartFile file) throws Exception{
        String image = Base64.getEncoder().encodeToString(file.getBytes());
        return aliyunAiUtils.recognizeFace(image);
    }

    @PostMapping("/checkFace")
    //http://localhost:9040//tools/ai/aliyun/checkFace
    //人脸属性识别 face_num  int  检测出来的人脸个数
    public String checkFace(MultipartFile file)throws Exception{
        String image = Base64.getEncoder().encodeToString(file.getBytes());
        return aliyunAiUtils.checkFace(image);
    }

    @GetMapping
    //http://localhost:9040//tools/ai/aliyun
    public CommonResponse listFace()throws Exception{
        return aliyunAiUtils.listFace();
    }
}
