package com.zw.cloud.activiti.common.api;

import com.zw.cloud.activiti.entity.ActHiProcinst;
import com.zw.cloud.common.utils.WebResult;

import java.util.Map;

public interface IActivitiCommonProcessService {

    /**
     * 启动流程
     * @param processDefinitionKey
     * @param variables
     * @return
     * @throws Exception
     */
    ActHiProcinst startProcessInstance(String processDefinitionKey, Map<String, Object> variables);

    WebResult doTask(String workId,String taskId, Map<String, Object> variables);

    WebResult doTaskWithoutPermissionCheck(String taskId, Map<String, Object> variables);

    WebResult addTaskUser(String workId,String nodeCode, String processInstanceId, String taskUser,boolean isAdd);

    WebResult queryNextTaskByProcInstId(String procInstId);

    WebResult taskQueryByWorkId(String workId);

    WebResult queryHiActinst(String procInstId);
}
