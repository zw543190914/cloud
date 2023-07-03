package com.zw.cloud.tools.controller;


import com.alibaba.fastjson2.JSON;
import com.zw.cloud.tools.mtop.MtopService;
import com.zw.cloud.tools.mtop.dto.MtopParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tools/mtop")
public class MtopController {
    @Autowired
    private MtopService mtopService;

    @PostMapping
    //http://localhost:9040/tools/mtop
    public Object mtopTest(@RequestBody MtopParamDTO mtopParamDTO) throws Exception {
        return mtopService.mtopAdaptor(mtopParamDTO.getUri(), mtopParamDTO.getArgs(), mtopParamDTO.getWorkId(), mtopParamDTO.getWorkName());
    }

    @GetMapping
    //http://localhost:9040/tools/mtop?mtopParam={"uri":"/user/plus/insert_POST", "args":[], "workId":"1","workName":"a"}
    public Object mtopTest(@RequestParam String mtopParam) throws Exception {
        MtopParamDTO mtopParamDTO = JSON.parseObject(mtopParam, MtopParamDTO.class);
        return mtopService.mtopAdaptor(mtopParamDTO.getUri(), mtopParamDTO.getArgs(), mtopParamDTO.getWorkId(), mtopParamDTO.getWorkName());
    }
}
