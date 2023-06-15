package com.zw.cloud.activiti.common.service.impl;

import com.google.common.base.Preconditions;
import com.zw.cloud.activiti.common.service.api.IActivitiCommonProcessService;
import com.zw.cloud.activiti.dao.ActHiActinstMapper;
import com.zw.cloud.activiti.dao.ActHiCommentMapper;
import com.zw.cloud.activiti.dao.ActRuTaskMapper;
import com.zw.cloud.activiti.entity.*;
import com.zw.cloud.global.response.wrapper.entity.WebResult;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    private RepositoryService repositoryService;
    @Autowired
    private ActRuTaskMapper taskMapper;
    @Autowired
    private ActHiActinstMapper hiActinstMapper;
    @Autowired
    private ActHiCommentMapper hiCommentMapper;

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
        Task task = taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(workId).singleResult();
        if (null == task){
            throw new RuntimeException("暂无相关任务");
        }
        taskService.setVariables(taskId,variables);
        taskService.complete(taskId);
        return WebResult.success();
    }

    @Override
    public WebResult doTaskWithoutPermissionCheck(String taskId, Map<String, Object> variables){
        taskService.setVariables(taskId,variables);
        taskService.complete(taskId);
        return WebResult.success();
    }

    @Override
    @Transactional
    public WebResult claimTask(String workId,String taskId){
        taskService.claim(taskId,workId);
        return WebResult.success();
    }

    @Override
    public WebResult updateVariables(String taskId,  Map<String, Object> variables){
        taskService.setVariables(taskId,variables);
        return WebResult.success();
    }

    @Override
    public WebResult updateAssignee(String taskId, String userId){
        taskService.setAssignee(taskId,userId);
        return WebResult.success();
    }

    //向任务中添加/删除人员
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public WebResult addTaskUser(String nodeCode, String processInstanceId, String taskUser,boolean isAdd){
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

    /**
     * 激活或挂起 全部流程
     * @param key
     * @return
     */
    @Override
    public WebResult suspendedOrActive(String key){
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).singleResult();
        // 是否暂停
        boolean suspended = processDefinition.isSuspended();
        if (suspended){
            // 激活
            repositoryService.activateProcessDefinitionById(processDefinition.getId(),true,null);
        } else {
            repositoryService.suspendProcessDefinitionById(processDefinition.getId(),true,null);
        }
        return WebResult.success();
    }

    /**
     * 激活或挂起 全部流程
     * @param processInstanceById
     * @return
     */
    @Override
    public WebResult suspendedOrActiveSingle(String processInstanceById){
        runtimeService.activateProcessInstanceById(processInstanceById);
        runtimeService.suspendProcessInstanceById(processInstanceById);
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
        List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(workId).list();
        List<ActRuTask> actRuTaskList = taskList.stream().map(task -> {
            ActRuTask ruTask = new ActRuTask();
            ruTask.setId(task.getId());
            ruTask.setProcInstId(task.getProcessInstanceId());
            ruTask.setProcDefId(task.getProcessDefinitionId());
            ruTask.setAssignee(task.getAssignee());
            return ruTask;
        }).collect(Collectors.toList());
        return WebResult.success().withData(actRuTaskList);
    }

    @Override
    public WebResult queryHiActinst(String procInstId){
        ActHiActinstExample example = new ActHiActinstExample();
        example.createCriteria().andProcInstIdEqualTo(procInstId);
        example.setOrderByClause("START_TIME_ DESC");
        return WebResult.success().withData(hiActinstMapper.selectByExample(example));

    }

    @Override
    public WebResult queryHiComment(String procInstId){
        ActHiCommentExample example = new ActHiCommentExample();
        example.createCriteria().andProcInstIdEqualTo(procInstId);
        return WebResult.success().withData(hiCommentMapper.selectByExample(example));

    }
}
