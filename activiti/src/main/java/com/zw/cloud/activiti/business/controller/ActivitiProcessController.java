package com.zw.cloud.activiti.business.controller;

import com.zw.cloud.activiti.business.service.api.IActivitiProcessService;
import com.zw.cloud.activiti.common.service.api.IActivitiCommonProcessService;
import com.zw.cloud.activiti.entity.ParamVO;
import com.zw.cloud.activiti.entity.VariableVO;
import com.zw.cloud.common.utils.WebResult;
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
    @Autowired
    private IActivitiCommonProcessService commonProcessService;


    /**
     * 启动流程实例
     * 根据流程定义 启动一个流程实例
     * 根据流程定义的一次具体执行过程，就是一个流程实例
     */
    @GetMapping("/startProcessInstance")
    //http://localhost:9020/activiti/process/startProcessInstance?processDefinitionKey=test&businessId=001&permissionUserIds=a,b,c
    public WebResult startProcessInstance(@RequestParam String processDefinitionKey,
                                          @RequestParam String businessId,@RequestParam String permissionUserIds) {
        return WebResult.success().withData(activitiProcessService.startProcessInstance(processDefinitionKey, businessId, permissionUserIds));
    }

    @PostMapping("/confirmNodeProcess")
    //http://localhost:9020/activiti/process/confirmNodeProcess
    public WebResult confirmNodeProcess(@RequestBody ParamVO paramVO) throws Exception{
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
    //http://localhost:9020/activiti/process/addTaskUser?nodeCode=handing&processInstanceId=54ca4514-bc7c-11ea-9b9e-a0a4c5f4cb40&taskUser=002&isAdd=true
    public WebResult addTaskUser(String nodeCode, String processInstanceId, String taskUser,boolean isAdd) throws Exception{
        activitiProcessService.addTaskUser(nodeCode, processInstanceId, taskUser, isAdd);
        return WebResult.success();
    }
    @GetMapping("/updateAssignee")
    //http://localhost:9020/activiti/process/updateAssignee?taskId=bb36e5a4-bc89-11ea-ab10-a0a4c5f4cb40&userId=as
    public WebResult updateAssignee(String taskId, String userId) {
        return activitiProcessService.updateAssignee(taskId, userId);
    }

    @PostMapping("/updateVariables")
    public WebResult updateVariables(@RequestBody VariableVO variables){
        return commonProcessService.updateVariables(variables.getTaskId(), variables.getVariables());
    }

    @GetMapping("/queryTaskByWorkId")
    //http://localhost:9020/activiti/process/queryTaskByWorkId?workId=d,e
    public WebResult queryTaskByWorkId(String workId) throws Exception{
        return activitiProcessService.queryTaskByWorkId(workId);
    }

    @GetMapping("/queryComment")
    //http://localhost:9020/activiti/process/queryComment?processInstanceId=d21a3108-bc87-11ea-ab43-a0a4c5f4cb40
    public WebResult queryComment(String processInstanceId) throws Exception{
        return activitiProcessService.queryComment(processInstanceId);
    }

    @GetMapping("/queryActinst")
    //http://localhost:9020/activiti/process/queryActinst?processInstanceId=ee65c4f6-bc94-11ea-a85c-a0a4c5f4cb40
    public WebResult queryActinst(String processInstanceId){
        return WebResult.success().withData(activitiProcessService.queryActinst(processInstanceId));
    }

    @GetMapping("/queryTaskUser")
    //http://localhost:9020/activiti/process/queryTaskUser?processInstanceId=ee65c4f6-bc94-11ea-a85c-a0a4c5f4cb40
    public WebResult queryTaskUser(String processInstanceId){
        return activitiProcessService.queryTaskUser(processInstanceId);
    }

}
