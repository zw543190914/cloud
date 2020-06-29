package com.zw.cloud.activiti.controller;

import com.zw.cloud.activiti.service.api.IProcessInstanceService;
import com.zw.cloud.common.utils.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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

    @GetMapping("/taskQueryByUserId")
    public WebResult taskQueryByUserId(@RequestParam String userId) {
        return WebResult.success().withData(processInstanceService.taskQueryByUserId(userId));
    }

    @GetMapping("/completeTask")
    public void completeTask(@RequestParam String taskId,@RequestParam String result){
        processInstanceService.completeTask(taskId, result);
    }

    @GetMapping("/queryHistoryTask")
    public WebResult queryHistoryTask(@RequestParam String processInstanceId) {
        return WebResult.success().withData(processInstanceService.queryHistoryTask(processInstanceId));
    }
}
