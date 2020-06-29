package com.zw.cloud.activiti.service.impl;

import com.zw.cloud.activiti.service.api.IProcessInstanceService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessInstanceImpl implements IProcessInstanceService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessEngine processEngine;

    private Logger logger = LoggerFactory.getLogger(ProcessInstanceImpl.class);

    /**
     * 流程部署
     * @return
     */
    @Override
    public Deployment deploy(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //创建一个部署对象
        Deployment deployment = repositoryService
                .createDeployment().name("event")
                //.key("event")
                .addClasspathResource("processes/event.bpmn")
                .deploy();

        //deployment.getKey()
        //打印信息 3d86b719-ba20-11ea-a163-a0a4c5f4cb40
        System.out.println("部署ID："+deployment.getId());
        System.out.println("部署名称："+deployment.getName());
        return deployment;
    }

    /**
     * 启动一个实例
     */
    @Override
    public ProcessInstance startProcessInstance(String processDefinitionKey,String businessId,String permissionUserIds) {
        logger.info("[ProcessInstanceImpl][startProcessInstance] Number of process definitions is {}",repositoryService.createProcessDefinitionQuery().count());
        logger.info("[ProcessInstanceImpl][startProcessInstance] Number of tasks is {}",taskService.createTaskQuery().count());
        Map<String,Object> map = new HashMap<>();
        map.put("businessId",businessId);
        map.put("user","001");
        return runtimeService.startProcessInstanceByKey(processDefinitionKey,businessId, map);
    }

    /**
     * 处理个人认为
     */
    @Override
    public void handlerPersonalTask(String processInstanceId,String userId,String result,String permissionUserIds) {
        //根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("event")
                .taskAssignee(userId)
                .list();

        if(CollectionUtils.isNotEmpty(taskList)){
            for(Task task : taskList){
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间:"+task.getCreateTime());
                System.out.println("任务的办理人:"+task.getAssignee());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
                System.out.println("getOwner:"+task.getOwner());
                System.out.println("getCategory:"+task.getCategory());
                System.out.println("getDescription:"+task.getDescription());
                System.out.println("getFormKey:"+task.getFormKey());
                Map<String, Object> map = task.getProcessVariables();
                for (Map.Entry<String, Object> m : map.entrySet()) {
                    System.out.println("key:" + m.getKey() + " value:" + m.getValue());
                }
                for (Map.Entry<String, Object> m : task.getTaskLocalVariables().entrySet()) {
                    System.out.println("key:" + m.getKey() + " value:" + m.getValue());
                }

            }
        }
    }

    /**
     * 查询用户的任务列表
     */
    @Override
    public List<Task> taskQueryByUserId(String userId) {
        //根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("event")
                .taskAssignee(userId)
                .list();

        if(CollectionUtils.isNotEmpty(taskList)){
            for(Task task : taskList){
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间:"+task.getCreateTime());
                System.out.println("任务的办理人:"+task.getAssignee());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
                System.out.println("getOwner:"+task.getOwner());
                System.out.println("getCategory:"+task.getCategory());
                System.out.println("getDescription:"+task.getDescription());
                System.out.println("getFormKey:"+task.getFormKey());
                Map<String, Object> map = task.getProcessVariables();
                for (Map.Entry<String, Object> m : map.entrySet()) {
                    System.out.println("key:" + m.getKey() + " value:" + m.getValue());
                }
                for (Map.Entry<String, Object> m : task.getTaskLocalVariables().entrySet()) {
                    System.out.println("key:" + m.getKey() + " value:" + m.getValue());
                }

            }
        }
        return taskList;
    }

    /**
     * 完成任务
     */
    @Override
    public void completeTask(String taskId,String result){
        Map<String,Object> map = new HashMap<>();
        map.put("agree",result);
        map.put("user","001");
        //任务ID
        taskService.complete(taskId,map);
    }

    /**
     * 历史活动实例查询
     */
    @Override
    public List<HistoricTaskInstance> queryHistoryTask(String processInstanceId) {
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery() // 创建历史活动实例查询
                .processInstanceId(processInstanceId) // 执行流程实例id
                .orderByTaskCreateTime()
                .asc()
                .list();
        for (HistoricTaskInstance hai : historicTaskInstanceList) {
            System.out.println("活动ID:" + hai.getId());
            System.out.println("流程实例ID:" + hai.getProcessInstanceId());
            System.out.println("活动名称：" + hai.getName());
            System.out.println("办理人：" + hai.getAssignee());
            System.out.println("开始时间：" + hai.getStartTime());
            System.out.println("结束时间：" + hai.getEndTime());
        }
        return historicTaskInstanceList;
    }

}
