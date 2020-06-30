package com.zw.cloud.activiti.service.api;

import com.zw.cloud.activiti.entity.*;

import java.util.List;

public interface IProcessInstanceService {

    ActReDeployment deploy();

    List<ActHiProcinst> startProcessInstance(String processDefinitionKey, String businessId, String permissionUserIds);

    void handlerPersonalTask(String processInstanceId,String userId,String result,String nextStepPerson);

    List<ActRuTask> taskQueryByProcInstId(String procInstId);

    void completeTask(String taskId,String result);

    List<ActHiActinst> queryHistoryTask(String processInstanceId);
}
