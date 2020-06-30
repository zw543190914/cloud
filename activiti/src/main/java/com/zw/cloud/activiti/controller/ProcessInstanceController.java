package com.zw.cloud.activiti.controller;

import com.zw.cloud.activiti.service.api.IProcessInstanceService;
import com.zw.cloud.common.utils.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://blog.csdn.net/zhouchenjun001/article/details/103629559
 * https://blog.csdn.net/hj7jay/article/details/51302829?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
 */
@RestController
@RequestMapping("/activiti/processInstance")
public class ProcessInstanceController {

    @Autowired
    private IProcessInstanceService processInstanceService;

    @GetMapping("/deploy")
    //http://localhost:9020/activiti/processInstance/deploy
    public WebResult deploy(){
        return WebResult.success().withData(processInstanceService.deploy());
    }

    @GetMapping("/startProcessInstance")
    //http://localhost:9020/activiti/processInstance/startProcessInstance?processDefinitionKey=event&businessId=001&permissionUserIds=001
    public WebResult startProcessInstance(@RequestParam String processDefinitionKey,
                                                @RequestParam String businessId,@RequestParam String permissionUserIds) {
        return WebResult.success().withData(processInstanceService.startProcessInstance(processDefinitionKey, businessId, permissionUserIds));
    }

    @GetMapping("/handlerPersonalTask")
    public void handlerPersonalTask(@RequestParam String processInstanceId,@RequestParam String userId,
                                     @RequestParam String result,@RequestParam String permissionUserIds) {
        processInstanceService.handlerPersonalTask(processInstanceId, userId, result, permissionUserIds);
    }

    @GetMapping("/taskQueryByProcInstId")
    //http://localhost:9020/activiti/processInstance/queryHistoryTask?processInstanceId=924e0e4d-ba20-11ea-bed8-a0a4c5f4cb40
    public WebResult taskQueryByProcInstId(@RequestParam String procInstId) {
        return WebResult.success().withData(processInstanceService.taskQueryByProcInstId(procInstId));
    }

    @GetMapping("/completeTask")
    public void completeTask(@RequestParam String taskId,@RequestParam String result){
        processInstanceService.completeTask(taskId, result);
    }

    @GetMapping("/queryHistoryTask")
    //http://localhost:9020/activiti/processInstance/queryHistoryTask?processInstanceId=924e0e4d-ba20-11ea-bed8-a0a4c5f4cb40
    public WebResult queryHistoryTask(@RequestParam String processInstanceId) {
        return WebResult.success().withData(processInstanceService.queryHistoryTask(processInstanceId));
    }
}
