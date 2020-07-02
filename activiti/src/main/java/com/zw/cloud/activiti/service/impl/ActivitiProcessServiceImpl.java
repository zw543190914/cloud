package com.zw.cloud.activiti.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.zw.cloud.activiti.common.api.IActivitiCommonProcessService;
import com.zw.cloud.activiti.entity.ActHiProcinst;
import com.zw.cloud.activiti.service.api.IActivitiProcessService;
import com.zw.cloud.common.utils.WebResult;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivitiProcessServiceImpl implements IActivitiProcessService {

    @Autowired
    private TaskService taskService;
    @Autowired
    private IActivitiCommonProcessService commonProcessService;

    /**
     * 启动一个实例
     */
    @Override
    public ActHiProcinst startProcessInstance(String processDefinitionKey, String businessId, String permissionUserIds) {
        Map<String,Object> map = new HashMap<>();
        map.put("businessId",businessId);
        map.put("user",permissionUserIds);
        return commonProcessService.startProcessInstance(processDefinitionKey,map);
    }

    //16.16.   任务处理 组任务 通过nodeCode做验证，防止同一任务 多人重复点击
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean confirmNodeProcess(String workId,String nodeCode, String processInstanceId, Map<String, Object> paramMap) throws Exception{
        Preconditions.checkNotNull(workId, "workId can not be null");
        Preconditions.checkNotNull(nodeCode,"nodeCode can not be null");
        Preconditions.checkNotNull(processInstanceId, "processInstanceId can not be null");
        //Preconditions.checkNotNull(paramMap, "paramMap can not be null");
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        List<Task> tasks = taskList.stream().filter(task -> nodeCode.equals(task.getName())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(tasks)){
            throw new Exception("任务进度不匹配");
        }
        //Set<String> taskIds = taskList.stream().map(TaskInfo::getId).collect(Collectors.toSet());
        for (Task task : tasks){
            String taskId = task.getId();
            //taskService.addComment(taskId,processInstanceId ,nodeCode );
            //处理任务
            commonProcessService.claimAnddoTask(workId,taskId,paramMap);

        }
        return true;
    }

    //16.16.   任务处理 个人任务 通过nodeCode做验证，防止同一任务 多人重复点击
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean confirmNodeProcessAssignee(String workId,String nodeCode, String processInstanceId, Map<String, Object> paramMap) throws Exception{
        Preconditions.checkNotNull(workId, "workId can not be null");
        Preconditions.checkNotNull(nodeCode,"nodeCode can not be null");
        Preconditions.checkNotNull(processInstanceId, "processInstanceId can not be null");
        //Preconditions.checkNotNull(paramMap, "paramMap can not be null");
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(workId).processInstanceId(processInstanceId).list();
        if(CollectionUtils.isEmpty(taskList)){
            throw new Exception("没有您的对应审批任务");
        }
        List<Task> tasks = taskList.stream().filter(task -> nodeCode.equals(task.getName())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(tasks)){
            throw new Exception("任务进度不匹配");
        }

        //Set<String> taskIds = taskList.stream().map(TaskInfo::getId).collect(Collectors.toSet());
        for (Task task : tasks){
            String taskId = task.getId();
            //taskService.addComment(taskId,processInstanceId ,nodeCode );
            //处理任务
            commonProcessService.doTaskWithoutPermissionCheck(taskId,paramMap);
        }
        return true;
    }

    //16.16.   任务处理---并行网关组任务使用（不需要指定下一步nodeNode）--非并行网关，可能造成多次点击流程被推动多步
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean confirmNodeProcess(String workId, String processInstanceId, Map<String, Object> paramMap) throws Exception{
        Preconditions.checkNotNull(workId, "workId can not be null");
        Preconditions.checkNotNull(processInstanceId, "processInstanceId can not be null");
        //Preconditions.checkNotNull(paramMap, "paramMap can not be null");
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(workId).processInstanceId(processInstanceId).list();
        if(CollectionUtils.isEmpty(taskList)){
            throw new Exception("没有您的对应审批任务");
        }

        //Set<String> taskIds = taskList.stream().map(TaskInfo::getId).collect(Collectors.toSet());
        for (Task task : taskList){
            String taskId = task.getId();
            //任务拾取
            taskService.claim(taskId, workId);
            // taskId,流程实例 id ,批注内容
            //Authentication.setAuthenticatedUserId(workId);
            taskService.addComment(taskId,processInstanceId ,"拾取组任务 并行网关无nodeCode操作" );
            //处理任务
            taskService.complete(taskId, paramMap);
        }
        return true;
    }

    //16.16.   任务处理---并行网关 个人任务使用（不需要指定下一步nodeNode）
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean confirmNodeProcessAssignee(String workId, String processInstanceId, Map<String, Object> paramMap) throws Exception{
        Preconditions.checkNotNull(workId, "workId can not be null");
        Preconditions.checkNotNull(processInstanceId, "processInstanceId can not be null");
        //Preconditions.checkNotNull(paramMap, "paramMap can not be null");
        List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(workId).processInstanceId(processInstanceId).list();
        if(CollectionUtils.isEmpty(taskList)){
            throw new Exception("没有您的对应审批任务");
        }

        for (Task task : taskList){
            String taskId = task.getId();
            taskService.addComment(taskId,processInstanceId ,"个人任务 并行网关无nodeCode操作" );
            //处理任务
            commonProcessService.doTaskWithoutPermissionCheck(taskId,paramMap);
        }
        return true;
    }
    //16.16.   任务处理--加签操作
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean confirmNodeAdditionalSignature(String workId, String result,String processInstanceId, Map<String, Object> paramMap,String key) throws Exception{
        Preconditions.checkNotNull(workId, "workId can not be null");
        Preconditions.checkNotNull(key,"key can not be null");
        Preconditions.checkNotNull(processInstanceId, "processInstanceId can not be null");
        //Preconditions.checkNotNull(paramMap, "paramMap can not be null");
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(workId).processInstanceId(processInstanceId).list();
        if(CollectionUtils.isEmpty(taskList)){
            throw new Exception("没有您的对应审批任务");
        }
        //List<Task> tasks = taskList.stream().filter(task -> nodeCode.equals(task.getName())).collect(Collectors.toList());

        //Set<String> taskIds = taskList.stream().map(TaskInfo::getId).collect(Collectors.toSet());
        for (Task task : taskList){
            String taskId = task.getId();
            //加签人，所有Map中相关人员都通过，才执行任务-由最后一个人执行
            Map<String, Object> variables = taskService.getVariables(taskId);
            String taskUser = (String) variables.get(key);
            List<String> taskUserList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(taskUser);
            //查找所有已经审批的用户
            List<Comment> taskComments = taskService.getTaskComments(taskId);
            List<Comment> comments = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(taskComments)){
                comments = taskComments.stream().filter(comment -> workId.equals(comment.getUserId()) && "agree".equals(comment.getFullMessage())).collect(Collectors.toList());
            }
            if (comments.size() > 0){
                throw new Exception("您已经审批通过此任务，请其他相关人员审批");
            }
            // taskId,流程实例 id ,批注内容
            //Authentication.setAuthenticatedUserId(workId);
            taskService.addComment(taskId, processInstanceId, result);
            List<Comment> agreeCommentList = taskComments.stream().filter(comment -> "agree".equals(comment.getFullMessage())).collect(Collectors.toList());
            if ("agree".equals(result) && null != agreeCommentList && agreeCommentList.size() == taskUserList.size() - 1){
                //任务拾取
                taskService.claim(taskId, workId);
                //处理任务
                taskService.complete(taskId, paramMap);
            }

        }
        return true;
    }

    @Override
    public WebResult addTaskUser(String nodeCode, String processInstanceId, String taskUser, boolean isAdd) throws Exception {
        return commonProcessService.addTaskUser(nodeCode, processInstanceId, taskUser, isAdd);
    }

    @Override
    public WebResult updateAssignee(String taskId, String userId) {
        return commonProcessService.updateAssignee(taskId,userId);
    }


    //16.13.   查询任务
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public WebResult queryTaskByWorkId(String workId) throws Exception{
        Preconditions.checkNotNull(workId,"workId can not be null");
        return commonProcessService.taskQueryByWorkId(workId);
    }

    //16.14.   查询批注信息----可以根据流程实例或执行实例id查询
    @Override
    public WebResult queryComment(String processInstanceId){
        Preconditions.checkNotNull(processInstanceId,"processInstanceId can not be null");
        return WebResult.success().withData(commonProcessService.queryHiComment(processInstanceId));
    }


    @Override
    public WebResult queryActinst( String processInstanceId){
        Preconditions.checkNotNull(processInstanceId,"processInstanceId can not be null");
        return commonProcessService.queryHiActinst(processInstanceId);

    }

    //查询下一步执行人
    @Override
    public WebResult queryTaskUser(String processInstanceId){
        Preconditions.checkNotNull(processInstanceId,"processInstanceId can not be null");
        return commonProcessService.queryNextTaskByProcInstId(processInstanceId);
    }
}
