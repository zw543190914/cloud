package com.zw.cloud.activiti.controller;


import com.zw.cloud.activiti.service.api.IActivitiDeployService;
import com.zw.cloud.common.utils.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/activiti/defineProcess")
public class DefineProcessController {

    @Autowired
    private IActivitiDeployService activitiDeployService;


    /**
     * 流程部署和定义
     * @param filePath  上传bpmn文件返回的filePath
     * @param deployName  流程名称
     */
    //http://localhost:9001/defineProcess?filePath=event01.bpmn&deployName=event
    @RequestMapping("/defineProcess")
    public WebResult defineProcess(String filePath, String deployName)throws Exception{
        return WebResult.success().withData(activitiDeployService.defineProcess(filePath, deployName));
    }

    @RequestMapping("/defineProcessByZip")
    //http://localhost:9001/defineProcessByZip
    public WebResult defineProcessByZip(MultipartFile multipartFile, String deployName){
        return WebResult.success().withData(activitiDeployService.defineProcessByZip(multipartFile, deployName));
    }

    /**
     * 删除流程部署和定义
     * @param deployId
     * @return
     */
    @RequestMapping("/deleteDeploy")
    //http://localhost:9001/deleteDeploy?deployId=2501
    public WebResult deleteDeploy(String deployId)throws Exception{
        activitiDeployService.deleteDeploy(deployId);
        return WebResult.success();
    }

    /**
     * 查询流程定义
     * http://localhost:9001/queryDefined?pageNo=1&pageSize=10&deployName=event
     * @param deployName
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/queryDefined")
    public WebResult queryDefined(String deployName,Integer pageNo,Integer pageSize)throws Exception{
        return WebResult.success().withData(activitiDeployService.queryDefined(deployName, pageNo, pageSize));
    }

    /**
     * 根据流程部署name查询流程图
     * http://localhost:9001/queryImage?deployName=te
     * @param deployId
     * @param response
     */
    @RequestMapping("/queryImage")
    public void queryImage(String deployId, HttpServletResponse response)throws Exception{
        activitiDeployService.queryImage(deployId, response);
    }

}