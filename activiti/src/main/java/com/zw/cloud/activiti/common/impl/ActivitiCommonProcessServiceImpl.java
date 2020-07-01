package com.zw.cloud.activiti.common.impl;

import com.google.common.base.Preconditions;
import com.zw.cloud.activiti.common.api.IActivitiCommonProcessService;
import com.zw.cloud.activiti.dao.ActHiActinstMapper;
import com.zw.cloud.activiti.dao.ActRuTaskMapper;
import com.zw.cloud.activiti.entity.ActHiActinstExample;
import com.zw.cloud.activiti.entity.ActHiProcinst;
import com.zw.cloud.activiti.entity.ActRuTask;
import com.zw.cloud.activiti.entity.ActRuTaskExample;
import com.zw.cloud.common.utils.WebResult;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActivitiCommonProcessServiceImpl implements IActivitiCommonProcessService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ActRuTaskMapper taskMapper;
    @Autowired
    private ActHiActinstMapper hiActinstMapper;

    @Override
    public ActHiProcinst startProcessInstance(String processDefinitionKey, Map<String, Object> variables) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        ActHiProcinst procinst = new ActHiProcinst();
        procinst.setProcDefId(processInstance.getProcessDefinitionId());
        procinst.setProcInstId(processInstance.getProcessInstanceId());
        procinst.setTenantId(processInstance.getTenantId());
        return procinst;
    }
    @Override
    @Transactional
    public WebResult doTask(String workId,String taskId, Map<String, Object> variables){
        List<Task> taskList = taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(workId).list();
        if (CollectionUtils.isNotEmpty(taskList)){
            taskList.forEach(task -> taskService.complete(taskId, variables));
        }
        return WebResult.success();
    }

    @Override
    public WebResult doTaskWithoutPermissionCheck(String taskId, Map<String, Object> variables){
        taskService.complete(taskId,variables);
        return WebResult.success();
    }

    //向任务中添加/删除人员
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public WebResult addTaskUser(String workId,String nodeCode, String processInstanceId, String taskUser,boolean isAdd){
        Preconditions.checkNotNull(workId, "workId can not be null");
        Preconditions.checkNotNull(nodeCode,"nodeCode can not be null");
        Preconditions.checkNotNull(processInstanceId, "processInstanceId can not be null");
        Preconditions.checkNotNull(taskUser, "taskUser can not be null");
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        if (isAdd){
            for (Task task : taskList){
                Preconditions.checkArgument(nodeCode.equals(task.getName()),"任务进度不匹配");

                //identityService.setAuthenticatedUserId(workId);
                //taskService.addCandidateGroup(task.getId(),taskUser );
                taskService.addCandidateUser(task.getId(),taskUser );
                taskService.addComment(task.getId(),processInstanceId ,"添加人员");
            }
        } else {
            for (Task task : taskList){
                Preconditions.checkArgument(nodeCode.equals(task.getName()),"任务进度不匹配");

                //identityService.setAuthenticatedUserId(workId);
                taskService.deleteCandidateUser(task.getId(),taskUser );
                //taskService.deleteCandidateGroup(task.getId(),taskUser );
                taskService.addComment(task.getId(),processInstanceId ,"删除人员");
            }
        }
        return WebResult.success();
    }

    @Override
    public WebResult queryNextTaskByProcInstId(String procInstId){
        ActRuTaskExample taskExample = new ActRuTaskExample();
        taskExample.createCriteria().andProcInstIdEqualTo(procInstId);
        return WebResult.success().withData(taskMapper.selectByExample(taskExample));
    }

    /**
     * 查询用户的任务列表
     */
    @Override
    public WebResult taskQueryByWorkId(String workId) {
        List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(workId).orderByTaskCreateTime().desc().list();
        if (CollectionUtils.isEmpty(taskList)){
            return WebResult.success().withData(Collections.EMPTY_LIST);
        }
        List<ActRuTask> ruTaskList = taskList.stream().map(task -> {
            ActRuTask ruTask = new ActRuTask();
            BeanUtils.copyProperties(task, ruTask);
            return ruTask;
        }).collect(Collectors.toList());
        return WebResult.success().withData(ruTaskList);
    }

    @Override
    public WebResult queryHiActinst(String procInstId){
        ActHiActinstExample example = new ActHiActinstExample();
        example.createCriteria().andProcInstIdEqualTo(procInstId);
        return WebResult.success().withData(hiActinstMapper.selectByExample(example));

    }
}
