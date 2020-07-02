package com.zw.cloud.activiti.controller.activiti.business;

import com.zw.cloud.activiti.common.api.IActivitiCommonDeployService;
import com.zw.cloud.common.utils.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/activiti/defineProcess")
public class DefineProcessController {

    @Autowired
    private IActivitiCommonDeployService commonDeployService;


    /**
     * 流程部署和定义
     * @param deployName  流程名称
     */
    @GetMapping("/deploy")
    //http://localhost:9020/activiti/defineProcess/deploy?deployName=event2
    public WebResult deploy(String deployName){
        return WebResult.success().withData(commonDeployService.deploy( deployName));
    }


    @PostMapping("/defineProcessByZip")
    public WebResult defineProcessByZip(MultipartFile multipartFile, String deployName){
        return WebResult.success().withData(commonDeployService.defineProcessByZip(multipartFile, deployName));
    }

    /**
     * 删除流程部署和定义
     * @param deployId
     * @return
     */
    @GetMapping("/deleteDeploy")
    //http://localhost:9020/activiti/defineProcess/deleteDeploy?deployId=3d86b719-ba20-11ea-a163-a0a4c5f4cb40
    public WebResult deleteDeploy(String deployId)throws Exception{
        commonDeployService.deleteDeploy(deployId);
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
        return WebResult.success().withData(commonDeployService.queryDefined(key, pageNo, pageSize));
    }

    /**
     * 查询流程图
     * @param deployId
     * @param response
     */
    @GetMapping("/queryImage")
    //http://localhost:9020/activiti/defineProcess/queryImage?deployId=event:1:61656bd4-bc7a-11ea-b4f8-a0a4c5f4cb40
    public void queryImage(String deployId, HttpServletResponse response)throws Exception{
        commonDeployService.queryImage(deployId, response);
    }


}