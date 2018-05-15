/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author menghui
 */
@JsonIgnoreProperties(value = {"activitTask"})
public class UserTaskForList implements IUserTaskInfo {

    protected Logger logger = Logger.getLogger(UserTaskForList.class);
    protected IFlowHandler handler;
    protected TaskInfo activitTask;
    protected String taskName;
    protected String taskID;
    protected String taskMemo;
    protected String formID;
    protected String userID;

    protected Map<String, Object> variables;
    protected Map<String, Object> localVariables;
    protected String processid;
    protected String processDefinitionId;
    protected String taskDefinitionKey;
    protected String processName;
    protected String startUser;
    protected Date startDate;
    protected TaskDocument docConfig;
    protected String businessKey;
    protected String businessURL;
    protected String businessname; //记录业务名称 入项目名称 会议名称等
    protected Date acceptTime;
    private String executionId;
    private StartActivityDocConfig startDocConfig;

//    protected abstract void fillVariables();

    protected UserTaskForList() {

    }

    protected Map<String, Object> getVariables(String processid ,String taskID){
        return this.handler.getLocalVar(taskID);
    }

    protected UserTaskForList(TaskInfo task, IFlowHandler handler) {
        this.handler = handler;

//        this.variables = this.getVariables(task.getProcessInstanceId(),task.getId());
        this.variables = task.getProcessVariables();
        this.localVariables = task.getTaskLocalVariables();
        this.activitTask = task;
        this.executionId = task.getExecutionId();
        this.taskID = task.getId();
        this.taskName = task.getName();
        this.taskMemo = task.getDescription();
        this.acceptTime = task.getCreateTime();
        this.userID = task.getAssignee();
        Gson gson = new Gson();
        try {
            TaskDocument tss = gson.fromJson(task.getDescription().replace("<![CDATA[", "").replace("]]>", ""), TaskDocument.class);
            this.taskMemo = tss.getDocument();
            this.docConfig = tss;
        } catch (Exception ex) {
            logger.error("==== create UserTaskInfo error!");
            logger.error("==== task config document json error!");
            logger.error("==== " + task.getDescription());
            logger.error(ex);
        }
        this.formID = this.activitTask.getFormKey();

        this.processid = task.getProcessInstanceId();
        this.processDefinitionId = task.getProcessDefinitionId();
        taskDefinitionKey = this.getActivitTask().getTaskDefinitionKey();

//        MyHisActivity hisAct = this.handler.findStartNode(this.processid);
//        this.startDate = hisAct.getStartTime();

        this.startDate = this.getActivitTask().getCreateTime();//startDate改成执行 任务接收时间，不用流程开始时间
//流程名字这里进行修改,把每个流程定义的流程定义信息放入常量中，避免重复访问数据库。
        MyProcessDefinition myProcessDefinition = FlowConstant.getMyProcessDefinitionById(processDefinitionId);
        if(myProcessDefinition ==null ){
            myProcessDefinition = this.handler.getProcessDefinition(processDefinitionId);
            FlowConstant.getProcessDefinitionMap().put(processDefinitionId,myProcessDefinition);
        }
        this.processName = myProcessDefinition.getName();
//        this.startUser = task.init

//        fillVariables();
//        UserActivity a = this.handler.getStartActivity(this.processDefinitionId);
//        if (a.getMemo() != null) {
//            try {
//                StartActivityDocConfig tss = gson.fromJson(a.getMemo().replace("<![CDATA[", "").replace("]]>", ""), StartActivityDocConfig.class);
//                this.startDocConfig = tss;
//            } catch (Exception ex) {
//                logger.error("==== create UserTaskInfo error!");
//                logger.error("==== task config document json error!");
//                logger.error("==== " + a.getMemo());
//                logger.error(ex);
//            }
//        }
        Object obj = this.variables.get("taskApplyUser");
        if (obj != null) {
            this.startUser = (String) obj;
        }
        obj = this.variables.get("businessurl");
        if (obj != null) {
            this.businessURL = (String) obj;
        }

        obj = this.variables.get("businessname");
        if (obj != null) {
            this.businessname = (String) obj;
        }

//        this.businessKey = this.handler.getBusinessKey(processid);
    }

    @Override
    public String getUserTaskID() {
        return this.processDefinitionId + ":" + this.processid + ":" + this.taskID;
    }

    /**
     * @return the activitTask
     */
    protected TaskInfo getActivitTask() {
        return activitTask;
    }

    /**
     * @return the formID
     */
    @Override
    public String getFormID() {
        return formID;
    }

    /**
     * @return the userID
     */
    @Override
    public String getUserID() {
        return userID;
    }

    /**
     * @return the taskName
     */
    @Override
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the taskID
     */
    @Override
    public String getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    /**
     * @return the taskMemo
     */
    @Override
    public String getTaskMemo() {
        return taskMemo;
    }

    /**
     * @param taskMemo the taskMemo to set
     */
    public void setTaskMemo(String taskMemo) {
        this.taskMemo = taskMemo;
    }

    /**
     * @return the processid
     */
    @Override
    public String getProcessid() {
        return processid;
    }

    /**
     * @param processid the processid to set
     */
    public void setProcessid(String processid) {
        this.processid = processid;
    }

    /**
     * @return the processDefinitionId
     */
    @Override
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
     * @return the docConfig
     */
    @Override
    public TaskDocument getDocConfig() {
        return docConfig;
    }

    /**
     * @param docConfig the docConfig to set
     */
    public void setDocConfig(TaskDocument docConfig) {
        this.docConfig = docConfig;
    }

    /**
     * @return the processName
     */
    @Override
    public String getProcessName() {
        return processName;
    }

    /**
     * @param processName the processName to set
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    /**
     * @return the businessKey
     */
    @Override
    public String getBusinessKey() {
        return businessKey;
    }

    /**
     * @param businessKey the businessKey to set
     */
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    /**
     * @return the startUser
     */
    @Override
    public String getStartUser() {
        return startUser;
    }

    /**
     * @param startUser the startUser to set
     */
    public void setStartUser(String startUser) {
        this.startUser = startUser;
    }

    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the businessURL
     */
    @Override
    public String getBusinessURL() {
        return businessURL;
    }

    /**
     * @param businessURL the businessURL to set
     */
    public void setBusinessURL(String businessURL) {
        this.businessURL = businessURL;
    }

    @Override
    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    @Override
    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public void setActivitTask(Task activitTask) {
        this.activitTask = activitTask;
    }

    /**
     * @return the taskDefinitionKey
     */
    @Override
    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    /**
     * @param taskDefinitionKey the taskDefinitionKey to set
     */
    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    /**
     * @return the variables
     */
    @Override
    public Map<String, Object> getVariables() {
        return variables;
    }

    /**
     * @return the localVariables
     */
    @Override
    public Map<String, Object> getLocalVariables() {
        return localVariables;
    }

    /**
     * @return the startDocConfig
     */
    @Override
    public StartActivityDocConfig getStartDocConfig() {
        return startDocConfig;
    }

    /**
     * @param startDocConfig the startDocConfig to set
     */
    public void setStartDocConfig(StartActivityDocConfig startDocConfig) {
        this.startDocConfig = startDocConfig;
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

}
