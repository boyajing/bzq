/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author menghui
 */
public interface IUserTaskInfo {

    Date getAcceptTime();

    /**
     * @return the businessKey
     */
    String getBusinessKey();

    /**
     * @return the businessURL
     */
    String getBusinessURL();

    String getBusinessname();

    /**
     * @return the docConfig
     */
    TaskDocument getDocConfig();

    /**
     * @return the formID
     */
    String getFormID();

    /**
     * @return the localVariables
     */
    Map<String, Object> getLocalVariables();

    /**
     * @return the processDefinitionId
     */
    String getProcessDefinitionId();

    /**
     * @return the processName
     */
    String getProcessName();

    /**
     * @return the processid
     */
    String getProcessid();

    /**
     * @return the startDate
     */
    Date getStartDate();

    /**
     * @return the startDocConfig
     */
    StartActivityDocConfig getStartDocConfig();

    /**
     * @return the startUser
     */
    String getStartUser();

    /**
     * @return the taskDefinitionKey
     */
    String getTaskDefinitionKey();

    /**
     * @return the taskID
     */
    String getTaskID();

    /**
     * @return the taskMemo
     */
    String getTaskMemo();

    /**
     * @return the taskName
     */
    String getTaskName();

    /**
     * @return the userID
     */
    String getUserID();

    String getUserTaskID();

    /**
     * @return the variables
     */
    Map<String, Object> getVariables();
    
}
