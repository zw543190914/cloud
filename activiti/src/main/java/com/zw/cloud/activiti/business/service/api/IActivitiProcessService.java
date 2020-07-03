package com.zw.cloud.activiti.business.service.api;

import com.zw.cloud.activiti.entity.ActHiProcinst;
import com.zw.cloud.common.utils.WebResult;

import java.util.Map;

public interface IActivitiProcessService {

    /**
     * 启动流程
     * @param processDefinitionKey
     * @param businessId
     * @param permissionUserIds
     * @return
     * @throws Exception
     */
    ActHiProcinst startProcessInstance(String processDefinitionKey, String businessId, String permissionUserIds);

    /**
     * 流程接入和审批 (组任务)
     * @param workId
     * @param nodeCode 通过nodeCode做验证，防止同一任务 多人重复点击，后面流程如果操作人都被注入，流程会被推动多步
     * @param processInstanceId
     * @param paramMap （流程变量)下一步执行人，判断条件等
     * @return
     * @throws Exception
     */
    boolean confirmNodeProcess(String workId, String nodeCode, String processInstanceId, Map<String, Object> paramMap) throws Exception;

    /**
     * 流程接入和审批 （组任务）针对 并行网关 使用（不需要指定下一步nodeNode）,
     *                           非并行网关，可能造成多次点击流程被推动多步
     * @param workId
     * @param processInstanceId
     * @param paramMap
     * @return
     * @throws Exception
     */
    boolean confirmNodeProcess(String workId, String processInstanceId, Map<String, Object> paramMap) throws Exception;

    /**
     * 流程接入和审批 (个人任务)
     * @param workId
     * @param nodeCode 通过nodeCode做验证，防止同一任务 多人重复点击,后面流程如果操作人都被注入，流程会被推动多步
     * @param processInstanceId
     * @param paramMap
     * @return
     * @throws Exception
     */
    boolean confirmNodeProcessAssignee(String workId, String nodeCode, String processInstanceId, Map<String, Object> paramMap) throws Exception;

    /**
     * 流程接入和审批 (个人任务) 针对并行网关 个人任务使用（不需要指定下一步nodeNode）
     *                         非并行网关，可能造成多次点击流程被推动多步(无nodeCode作为验证)
     * @param workId
     * @param processInstanceId
     * @param paramMap
     * @return
     * @throws Exception
     */
    boolean confirmNodeProcessAssignee(String workId, String processInstanceId, Map<String, Object> paramMap) throws Exception;

    /**
     * 流程接入和审批 ---加签时使用(加签需要至少两个以上人员同意才进入下一步，如果只需一个可使用confirmNodeProcess()方法)
     * @param workId
     * @param result 审批人意见 （agree/disagree）
     * @param processInstanceId
     * @param paramMap
     * @param key 流程变量中 任务处理人对应的key
     * @return
     * @throws Exception
     */
    boolean confirmNodeAdditionalSignature(String workId, String result, String processInstanceId, Map<String, Object> paramMap, String key) throws Exception;


    /**
     * 向任务中添加/删除人员
     * @param nodeCode
     * @param processInstanceId
     * @param taskUser  每次仅能添加或删除一人
     * @param isAdd true为添加，false 为删除
     * @return
     * @throws Exception
     */
    WebResult addTaskUser(String nodeCode, String processInstanceId, String taskUser, boolean isAdd) throws Exception;

    WebResult updateAssignee(String taskId, String userId);
    /**
     * 查询个人任务或相关组任务
     * @param workId
     * @return
     * @throws Exception
     */
    WebResult queryTaskByWorkId(String workId) throws Exception;
    /**
     *查询批注信息
     * @param processInstanceId
     * @return
     */
    WebResult queryComment(String processInstanceId);


    WebResult queryActinst(String processInstanceId);

    /**
     * 查询下一步执行人
     * @param processInstanceId
     * @return
     */
    WebResult queryTaskUser(String processInstanceId);

}
