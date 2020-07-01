package com.zw.cloud.activiti.common.api;

import com.zw.cloud.common.utils.WebResult;

import java.util.Map;

public interface IProcessService {

    WebResult doTask(String workId,String taskId, Map<String, Object> variables);

    WebResult doTaskWithoutPermissionCheck(String taskId, Map<String, Object> variables);

    WebResult queryNextTaskByProcInstId(String procInstId);

    WebResult queryHiActinst(String procInstId);
}
