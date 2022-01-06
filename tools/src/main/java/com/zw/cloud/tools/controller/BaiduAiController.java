package com.zw.cloud.tools.controller;

import com.zw.cloud.tools.utils.BaiduAiUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

@RestController
@RequestMapping("/tools/ai/baidu")
public class BaiduAiController {

    @Autowired
    private BaiduAiUtil baiduAiUtil;


    @PostMapping("/faceRegister")
    //http://localhost:9040//tools/ai/baidu/faceRegister
    // 人脸注册 ：将用户照片存入人脸库中
    public Boolean faceRegister(String userId, MultipartFile file) throws Exception {
        String faceImage = Base64.getEncoder().encodeToString(file.getBytes());
        return baiduAiUtil.faceRegister(userId, faceImage);
    }

    @PostMapping("/faceUpdate")
    //http://localhost:9040//tools/ai/baidu/faceUpdate
    // 人脸更新 ：更新人脸库中的用户照片
    public Boolean faceUpdate(String userId, MultipartFile file) throws Exception {
        String faceImage = Base64.getEncoder().encodeToString(file.getBytes());
        return baiduAiUtil.faceUpdate(userId, faceImage);
    }

    @PostMapping("/faceCheck")
    //人脸检测：判断上传图片中是否具有面部头像
    //http://localhost:9040//tools/ai/baidu/faceCheck
    public Boolean faceCheck(MultipartFile file) throws Exception {
        String faceImage = Base64.getEncoder().encodeToString(file.getBytes());
        return baiduAiUtil.faceCheck(faceImage);
    }

    @PostMapping("/faceSearch")
    //人脸查找：查找人脸库中最相似的人脸并返回数据
    // 处理：用户的匹配得分（score）大于80分，即可认为是同一个用户
    public String faceSearch(MultipartFile file) throws Exception {
        String faceImage = Base64.getEncoder().encodeToString(file.getBytes());
        return baiduAiUtil.faceSearch(faceImage);
    }

    @PostMapping("/faceMultSearch")
    // 1：N人脸搜索：也称为1：N识别，在指定人脸集合中，找到最相似的人脸；
    public JSONObject searchByUserId(String usrId, MultipartFile file) throws Exception {
        String faceImage = Base64.getEncoder().encodeToString(file.getBytes());
        return baiduAiUtil.searchByUserId(usrId, faceImage);
    }

    @GetMapping("/faceExit")
    //人脸 是否存在 0存在
    public boolean faceExit(String userId) {
        return baiduAiUtil.faceExit(userId);
    }

    // 删除用户
    @DeleteMapping
    //http://localhost:9040//tools/ai/baidu?groupId=zw_cloud&userId=null
    public JSONObject delete(String groupId,String userId) {
        return baiduAiUtil.delete(groupId, userId);
    }

    @PostMapping("/checkPersonByIdCard")
    //http://localhost:9040//tools/ai/baidu/checkPersonByIdCard
    // 身份验证 推荐阈值0.8，超过即判断为同一人
    public JSONObject checkPersonByIdCard(MultipartFile file, String idCardNumber, String name) throws Exception {
        String faceImage = Base64.getEncoder().encodeToString(file.getBytes());
        return baiduAiUtil.checkPersonByIdCard(faceImage, idCardNumber, name);
    }
}
