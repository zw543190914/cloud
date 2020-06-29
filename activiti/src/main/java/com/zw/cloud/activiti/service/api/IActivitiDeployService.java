package com.zw.cloud.activiti.service.api;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IActivitiDeployService {

    /**
     *流程部署和定义
     * @param filePath  上传bpmn文件返回的filePath
     * @param deployName  流程名称
     * @return
     * @throws Exception
     */
    String defineProcess(String filePath, String deployName)throws Exception;

    /**
     *
     * @param multipartFile
     * @param deployName
     * @return
     */
    boolean defineProcessByZip(MultipartFile multipartFile, String deployName);

    /**
     * 删除流程部署和定义
     * @param deployId
     * @throws Exception
     */
    void deleteDeploy(String deployId)throws Exception;

    /**
     * 查询流程定义
     * @param deployName
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    List<ProcessDefinition> queryDefined(String deployName, Integer pageNo, Integer pageSize)throws Exception;

    /**
     * 根据流程部署name查询流程图
     * @param deployId
     * @param response
     * @throws Exception
     */
    void queryImage(String deployId, HttpServletResponse response)throws Exception;

}
