package com.zw.cloud.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.activiti.entity.ParamVO;
import com.zw.cloud.activiti.service.api.IActivitiProcessService;
import com.zw.cloud.common.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * https://blog.csdn.net/zhouchenjun001/article/details/103629559
 * https://blog.csdn.net/hj7jay/article/details/51302829?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
 */
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
    @GetMapping("/startProcessInstance")
    //http://localhost:9020/activiti/process/startProcessInstance?processDefinitionKey=event&businessId=001&permissionUserIds=001
    public WebResult startProcessInstance(@RequestParam String processDefinitionKey,
                                          @RequestParam String businessId,@RequestParam String permissionUserIds) {
        return WebResult.success().withData(activitiProcessService.startProcessInstance(processDefinitionKey, businessId, permissionUserIds));
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
    //http://localhost:9020/activiti/process/queryComment?username=&processInstanceId=2501
    public WebResult queryComment(String processInstanceId) throws Exception{
        return WebResult.success().withData(activitiProcessService.queryComment(processInstanceId));
    }

    @GetMapping("/queryHistoryTask")
    //http://localhost:9020/activiti/process/queryHistoryTask?username=&processInstanceId=2501
    public WebResult queryHistoryTask(String username, String processInstanceId){
        // 查询 历史流程信息
        return WebResult.success().withData(activitiProcessService.queryHistoryTask(username, processInstanceId));
    }

    @GetMapping("/queryHistoricDetail")
    //http://localhost:9020/activiti/process/queryHistoricDetail?processInstanceId=7505
    public WebResult queryHistoricDetail(String processInstanceId){
        return WebResult.success().withData(activitiProcessService.queryHistoricDetail(processInstanceId));
    }

    @GetMapping("/queryTaskUser")
    //http://localhost:9020/activiti/process/queryTaskUser?processInstanceId=5005
    public WebResult queryTaskUser(String processInstanceId){
        return WebResult.success().withData(activitiProcessService.queryTaskUser(processInstanceId));
    }

}
