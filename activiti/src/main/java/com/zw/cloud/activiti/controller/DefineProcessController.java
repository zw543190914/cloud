package com.zw.cloud.activiti.controller;

import com.zw.cloud.activiti.service.api.IActivitiDeployService;
import com.zw.cloud.common.utils.WebResult;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/activiti/defineProcess")
public class DefineProcessController {

    @Autowired
    private IActivitiDeployService activitiDeployService;


    /**
     * 流程部署和定义
     * @param deployName  流程名称
     */
    @GetMapping("/deploy")
    //http://localhost:9020/activiti/defineProcess/deploy?deployName=event
    public WebResult deploy(String deployName){
        return WebResult.success().withData(activitiDeployService.deploy( deployName));
    }


    @PostMapping("/defineProcessByZip")
    public WebResult defineProcessByZip(MultipartFile multipartFile, String deployName){
        return WebResult.success().withData(activitiDeployService.defineProcessByZip(multipartFile, deployName));
    }

    /**
     * 删除流程部署和定义
     * @param deployId
     * @return
     */
    @GetMapping("/deleteDeploy")
    //http://localhost:9020/activiti/defineProcess/deleteDeploy?deployId=3d86b719-ba20-11ea-a163-a0a4c5f4cb40
    public WebResult deleteDeploy(String deployId)throws Exception{
        activitiDeployService.deleteDeploy(deployId);
        return WebResult.success();
    }

    /**
     * 查询流程定义
     * @param key
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/queryDefined")
    //http://localhost:9020/activiti/defineProcess/queryDefined?key=event&pageNo=1&pageSize=10
    public WebResult queryDefined(String key,Integer pageNo,Integer pageSize)throws Exception{
        return WebResult.success().withData(activitiDeployService.queryDefined(key, pageNo, pageSize));
    }

    /**
     * 查询流程图
     * @param deployId
     * @param response
     */
    @GetMapping("/queryImage")
    //http://localhost:9020/activiti/defineProcess/queryImage?deployId=2325e9ed-bba0-11ea-bd16-a0a4c5f4cb40
    public void queryImage(String deployId, HttpServletResponse response)throws Exception{
        activitiDeployService.queryImage(deployId, response);
    }


}