/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import java.util.Map;

/**
 *
 * @author menghui
 */
public class MyProcessInstance  {
    private String processDefinitionId;
    private String processDefinitionName;
    private String businessKey;
    private boolean isSuspended;
    private boolean isEnded;
    private Map<String, Object> processVariables;
    private String name;
    private String processInstanceId;
    /**
     * @return the processDefinitionId
     */
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    /**
     * @param processDefinitionId the processDefinitionId to set
     */
    protected void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    /**
     * @return the processDefinitionName
     */
    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    /**
     * @param processDefinitionName the processDefinitionName to set
     */
    protected void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    /**
     * @return the businessKey
     */
    public String getBusinessKey() {
        return businessKey;
    }

    /**
     * @param businessKey the businessKey to set
     */
    protected void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    /**
     * @return the isSuspended
     */
    public boolean isIsSuspended() {
        return isSuspended;
    }

    /**
     * @param isSuspended the isSuspended to set
     */
    protected void setIsSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    /**
     * @return the processVariables
     */
    public Map<String, Object> getProcessVariables() {
        return processVariables;
    }

    /**
     * @param processVariables the processVariables to set
     */
    protected void setProcessVariables(Map<String, Object> processVariables) {
        this.processVariables = processVariables;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * @return the isEnded
     */
    public boolean isIsEnded() {
        return isEnded;
    }

    /**
     * @param isEnded the isEnded to set
     */
    public void setIsEnded(boolean isEnded) {
        this.isEnded = isEnded;
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

    
}
