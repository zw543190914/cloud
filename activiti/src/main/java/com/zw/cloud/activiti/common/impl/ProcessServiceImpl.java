package com.zw.cloud.activiti.common.impl;

import com.zw.cloud.activiti.common.api.IProcessService;
import com.zw.cloud.activiti.dao.ActHiActinstMapper;
import com.zw.cloud.activiti.dao.ActRuTaskMapper;
import com.zw.cloud.activiti.entity.ActHiActinstExample;
import com.zw.cloud.activiti.entity.ActRuTaskExample;
import com.zw.cloud.common.utils.WebResult;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProcessServiceImpl implements IProcessService {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ActRuTaskMapper taskMapper;
    @Autowired
    private ActHiActinstMapper hiActinstMapper;

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

    @Override
    public WebResult queryNextTaskByProcInstId(String procInstId){
        ActRuTaskExample taskExample = new ActRuTaskExample();
        taskExample.createCriteria().andProcInstIdEqualTo(procInstId);
        return WebResult.success().withData(taskMapper.selectByExample(taskExample));
    }

    @Override
    public WebResult queryHiActinst(String procInstId){
        ActHiActinstExample example = new ActHiActinstExample();
        example.createCriteria().andProcInstIdEqualTo(procInstId);
        return WebResult.success().withData(hiActinstMapper.selectByExample(example));

    }
}
