package com.zw.cloud.activiti.common.service.api;

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

    WebResult claimTask(String workId,String taskId);

    WebResult addTaskUser(String nodeCode, String processInstanceId, String taskUser,boolean isAdd);

    WebResult updateAssignee(String taskId, String userId);

    WebResult updateVariables(String taskId,  Map<String, Object> variables);

    WebResult queryNextTaskByProcInstId(String procInstId);

    WebResult taskQueryByWorkId(String workId);

    WebResult queryHiActinst(String procInstId);

    WebResult queryHiComment(String procInstId);
}
