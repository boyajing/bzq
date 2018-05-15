/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import java.util.Date;
import java.util.Map;

/**
 * 历史活动信息，该类复制自Activity的历史活动实体类
 * @author menghui
 */
public class MyHisActivity {
    private String id;
    private String activityId;
    private String activityName;
    private String activityType;
    private String processDefinitionId;
    private String processInstanceId;
    private String executionId;
    private String taskId;
    private String calledProcessInstanceId;
    private String assignee;
    private Date startTime;
    private Date endTime;
    private Long durationInMillis;
    private String tenantId;
    private String actionBtnName;
    private String actionBtnValue;

    private Map<String,Object> variablesLocal;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the activityId
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * @param activityId the activityId to set
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * @return the activityName
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * @param activityName the activityName to set
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * @return the activityType
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * @param activityType the activityType to set
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    /**
     * @return the processDefinitionId
     */
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    /**
     * @param processDefinitionId the processDefinitionId to set
     */
    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    /**
     * @return the processInstanceId
     */
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    /**
     * @param processInstanceId the processInstanceId to set
     */
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    /**
     * @return the executionId
     */
    public String getExecutionId() {
        return executionId;
    }

    /**
     * @param executionId the executionId to set
     */
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the calledProcessInstanceId
     */
    public String getCalledProcessInstanceId() {
        return calledProcessInstanceId;
    }

    /**
     * @param calledProcessInstanceId the calledProcessInstanceId to set
     */
    public void setCalledProcessInstanceId(String calledProcessInstanceId) {
        this.calledProcessInstanceId = calledProcessInstanceId;
    }

    /**
     * @return the assignee
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * @param assignee the assignee to set
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the durationInMillis
     */
    public Long getDurationInMillis() {
        return durationInMillis;
    }

    /**
     * @param durationInMillis the durationInMillis to set
     */
    public void setDurationInMillis(Long durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    /**
     * @return the tenantId
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId the tenantId to set
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 仅在UserTask时有意义，保存环节的局部变量
     * @return the variablesLocal
     */
    public Map<String,Object> getVariablesLocal() {
        return variablesLocal;
    }

    /**
     * 仅在UserTask时有意义，保存环节的局部变量
     * @param variablesLocal the variablesLocal to set
     */
    public void setVariablesLocal(Map<String,Object> variablesLocal) {
        this.variablesLocal = variablesLocal;
    }

    /**
     * @return the actionBtnName
     */
    public String getActionBtnName() {
        return actionBtnName;
    }

    /**
     * @param actionBtnName the actionBtnName to set
     */
    public void setActionBtnName(String actionBtnName) {
        this.actionBtnName = actionBtnName;
    }

    /**
     * @return the actionBtnValue
     */
    public String getActionBtnValue() {
        return actionBtnValue;
    }

    /**
     * @param actionBtnValue the actionBtnValue to set
     */
    public void setActionBtnValue(String actionBtnValue) {
        this.actionBtnValue = actionBtnValue;
    }
    
    
}
