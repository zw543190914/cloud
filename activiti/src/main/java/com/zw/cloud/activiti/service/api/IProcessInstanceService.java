package com.zw.cloud.activiti.service.api;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

public interface IProcessInstanceService {

    Deployment deploy();

    ProcessInstance startProcessInstance(String processDefinitionKey,String businessId,String permissionUserIds);

    void handlerPersonalTask(String processInstanceId,String userId,String result,String nextStepPerson);

    List<Task> taskQueryByUserId(String userId);

    void completeTask(String taskId,String result);

    List<HistoricTaskInstance> queryHistoryTask(String processInstanceId);
}
