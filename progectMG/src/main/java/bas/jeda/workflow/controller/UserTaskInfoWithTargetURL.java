/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow.controller;

import bas.jeda.workflow.IUserTaskInfo;
import bas.jeda.workflow.StartActivityDocConfig;
import bas.jeda.workflow.TaskDocument;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author menghui
 */
public class UserTaskInfoWithTargetURL implements IUserTaskInfo {
    private final IUserTaskInfo userTask;
    private String targetURL;
    public UserTaskInfoWithTargetURL(IUserTaskInfo userTask,String targetURL) {
        this.userTask=userTask;
        this.targetURL=targetURL;
    }

    @Override
    public Date getAcceptTime() {
        return this.userTask.getAcceptTime();
    }

    @Override
    public String getBusinessKey() {
        return this.userTask.getBusinessKey();
    }

    @Override
    public String getBusinessURL() {
        return this.userTask.getBusinessURL();
    }

    @Override
    public String getBusinessname() {
        return this.userTask.getBusinessname();
    }

    @Override
    public TaskDocument getDocConfig() {
        return this.userTask.getDocConfig();
    }

    @Override
    public String getFormID() {
        return this.userTask.getFormID();
    }

    @Override
    public Map<String, Object> getLocalVariables() {
        return this.userTask.getLocalVariables();
    }

    @Override
    public String getProcessDefinitionId() {
        return this.userTask.getProcessDefinitionId();
    }

    @Override
    public String getProcessName() {
        return this.userTask.getProcessName();
    }

    @Override
    public String getProcessid() {
        return this.userTask.getProcessid();
    }

    @Override
    public Date getStartDate() {
        return this.userTask.getStartDate();
    }

    @Override
    public StartActivityDocConfig getStartDocConfig() {
        return this.userTask.getStartDocConfig();
    }

    @Override
    public String getStartUser() {
        return this.userTask.getStartUser();
    }

    @Override
    public String getTaskDefinitionKey() {
        return this.userTask.getTaskDefinitionKey();
    }

    @Override
    public String getTaskID() {
        return this.userTask.getTaskID();
    }

    @Override
    public String getTaskMemo() {
        return this.userTask.getTaskMemo();
    }

    @Override
    public String getTaskName() {
        return this.userTask.getTaskName();
    }

    @Override
    public String getUserID() {
        return this.userTask.getUserID();
    }

    @Override
    public String getUserTaskID() {
        return this.userTask.getUserTaskID();
    }

    @Override
    public Map<String, Object> getVariables() {
        return this.userTask.getVariables();
    }

    /**
     * @return the targetURL
     */
    public String getTargetURL() {
        return targetURL;
    }

    /**
     * @param targetURL the targetURL to set
     */
    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

}
