package com.zw.cloud.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.activiti.entity.ParamVO;
import com.zw.cloud.activiti.service.api.IActivitiProcessService;
import com.zw.cloud.common.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activiti/process")
public class ActivitiProcessController {

    @Autowired
    private IActivitiProcessService activitiProcessService;

    private Logger log = LoggerFactory.getLogger(ActivitiProcessController.class);


    /**
     * 启动流程实例
     * 根据流程定义 启动一个流程实例
     * 根据流程定义的一次具体执行过程，就是一个流程实例
     */
    //http://localhost:9001/initNodeProcess
    @PostMapping("/initNodeProcess")
    public WebResult initNodeProcess(ParamVO paramVO)throws Exception{
        log.info("[initNodeProcess]paramVO is {}", JSON.toJSONString(paramVO));
        return WebResult.success().withData(activitiProcessService.initNodeProcess(paramVO.getWorkId(),paramVO.getDeployName(),paramVO.getBusinessKey() ,paramVO.getParamMap()));
    }

    @PostMapping("/confirmNodeProcess")
    public WebResult confirmNodeProcess(ParamVO paramVO) throws Exception{
        activitiProcessService.confirmNodeProcess(paramVO.getWorkId(), paramVO.getNodeCode(), paramVO.getProcessInstanceId(), paramVO.getParamMap());
        return WebResult.success();
    }

    @PostMapping("/parallelConfirmNodeProcess")
    public WebResult parallelConfirmNodeProcess(@RequestBody ParamVO paramVO) throws Exception{
        activitiProcessService.confirmNodeProcess(paramVO.getWorkId(), paramVO.getProcessInstanceId(), paramVO.getParamMap());
        return WebResult.success();
    }

    @PostMapping("/confirmNodeProcessAssignee")
    public WebResult confirmNodeProcessAssignee(@RequestBody ParamVO paramVO) throws Exception{
        activitiProcessService.confirmNodeProcessAssignee(paramVO.getWorkId(), paramVO.getNodeCode(), paramVO.getProcessInstanceId(), paramVO.getParamMap());
        return WebResult.success();
    }

    @PostMapping("/parallelConfirmNodeProcessAssignee")
    public WebResult parallelConfirmNodeProcessAssignee(@RequestBody ParamVO paramVO) throws Exception{
        activitiProcessService.confirmNodeProcessAssignee(paramVO.getWorkId(), paramVO.getProcessInstanceId(), paramVO.getParamMap());
        return WebResult.success();
    }

    @PostMapping("/confirmNodeAdditionalSignature")
    public WebResult confirmNodeAdditionalSignature(@RequestBody ParamVO paramVO) throws Exception{
        activitiProcessService.confirmNodeAdditionalSignature(paramVO.getWorkId(), paramVO.getResult(), paramVO.getProcessInstanceId(), paramVO.getParamMap(),paramVO.getKey());
        return WebResult.success();
    }
    @GetMapping("/addTaskUser")
    public WebResult addTaskUser(String workId,String nodeCode, String processInstanceId, String taskUser,boolean isAdd) throws Exception{
        activitiProcessService.addTaskUser(workId, nodeCode, processInstanceId, taskUser, isAdd);
        return WebResult.success();
    }

    @GetMapping("/queryTaskByWorkId")
    public WebResult queryTaskByWorkId(String workId) throws Exception{
        return WebResult.success().withData(activitiProcessService.queryTaskByWorkId(workId));
    }

    @GetMapping("/queryComment")
    //http://localhost:9001/queryComment?username=&processInstanceId=2501
    public WebResult queryComment(String processInstanceId) throws Exception{
        return WebResult.success().withData(activitiProcessService.queryComment(processInstanceId));
    }

    @GetMapping("/queryHistoryTask")
    //http://localhost:9001/queryHistoryTask?username=&processInstanceId=2501
    public WebResult queryHistoryTask(String username, String processInstanceId){
        // 查询 历史流程信息
        return WebResult.success().withData(activitiProcessService.queryHistoryTask(username, processInstanceId));
    }

    @GetMapping("/queryHistoricDetail")
    //http://localhost:9001/queryHistoricDetail?processInstanceId=7505
    public WebResult queryHistoricDetail(String processInstanceId){
        return WebResult.success().withData(activitiProcessService.queryHistoricDetail(processInstanceId));
    }

    @GetMapping("/queryTaskUser")
    //http://localhost:9001/queryTaskUser?processInstanceId=5005
    public WebResult queryTaskUser(String processInstanceId){
        return WebResult.success().withData(activitiProcessService.queryTaskUser(processInstanceId));
    }

}
