package com.zw.cloud.tools.controller;

import com.zw.cloud.tools.utils.BaiduAiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/tools/ai")
public class AiController {

    @Autowired
    private BaiduAiUtil baiduAiUtil;

    //人脸注册    
   /* @GetMapping(value = "/register/face")
    public Boolean registerFace(@RequestParam(name = "fid") String fid) throws Exception {
        SysFile sysFile = fileService.findById(fid);    
        String path = uploadPath + "/" + sysFile.getPath() + "/" + sysFile.getUuidName();    
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        Boolean isSuc;
        String image = Base64Utils.encodeToString(bytes);
        isSuc = userService.checkFace(image);
        if (isSuc) {
            isSuc = baiduAiUtil.faceRegister("1", userId, image);
        }
        return isSuc;
    }*/

}
