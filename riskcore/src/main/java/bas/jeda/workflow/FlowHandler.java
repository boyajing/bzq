/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import com.google.gson.Gson;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.impl.DefaultProcessDiagramCanvas;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 流程处理器</p>
 * <p>
 * 为了实现流程引擎和业务系统隔离，业务系统不直接调用流程引擎的API，
 * 所有API的调用都集中在此类实现。此类不会返回流程引擎的实体类，所有实体类均进行封装和改写，实现业务和流程的完全隔离。</p>
 * <p>
 * 此类依赖于{@link bas.jeda.workflow.FlowAnchor},该类是一个流程引擎和FlowHandler的链接点。使用Spring自动注入该类依赖</p>
 *
 * @author menghui
 */
public class FlowHandler implements IFlowHandler {

    @Autowired
    private FlowAnchor anchor;

    @Override
    public InputStream getProcessImg(String processid) {
        Command<InputStream> cmd = new ProcessInstanceDiagramCmd(processid,
                this.getAnchor().getRuntimeService(),
                this.getAnchor().getRepositoryService(),
                this.getAnchor().getEngine(),
                this.getAnchor().getHistoryService());
        return this.getAnchor().getManagementService().executeCommand(cmd);
    }

    /**
     * 启动流程
     *
     * @param flowid      流程ID
     * @param userid      启动的用户ID
     * @param businessurl 业务页面的URL
     * @param businesskey 业务主键
     * @param variables   参数
     * @return 启动的流程实例ID
     */
    @Override
    public String startFlowByID(String flowid, String userid, String businessurl, String businesskey, HashMap<String, Object> variables) {
        this.getAnchor().getIdentityService().setAuthenticatedUserId(userid);
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("taskApplyUser", userid);
        vars.put("businessurl", businessurl);
        vars.put("businesskey", businesskey);
        vars.put("startDate", new Date());
        if (variables != null) {
            vars.putAll(variables);
        }
        ProcessInstance processInstance = getAnchor().getRuntimeService().startProcessInstanceByKey(flowid, businesskey, vars);
//        processInstance.getActivityId()
        return processInstance.getProcessInstanceId();
    }

    /**
     * <p>
     * 根据用户返回待办任务</p>
     * <p>
     * {@link bas.jeda.workflow.UserTask}是代办任务的实体类。</p>
     *
     * @param UserID 用户ID
     * @return 用户任务的列表{@link bas.jeda.workflow.UserTask}
     */
    @Deprecated
    @Override
    public List<UserTaskInfo> getTODOtask(String UserID) {
        List<Task> tasks = this.getAnchor().getTaskService().createTaskQuery().taskAssignee(UserID).includeProcessVariables().list();
        List<UserTaskInfo> uTasks = new ArrayList<UserTaskInfo>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            UserTask ut = new UserTask(t, this);
            uTasks.add(ut);
        }
        return uTasks;
    }

    /**
     * 返回用户的已办任务
     *
     * @param userID
     */
    @Deprecated
    @Override
    public List<TakenTask> getTAKENTask(String userID) {
        List<HistoricTaskInstance> hts = this.getAnchor().getHistoryService()
                .createHistoricTaskInstanceQuery().taskAssignee(userID).list();
        List<TakenTask> result = new ArrayList<TakenTask>();
        for (int i = 0; i < hts.size(); i++) {
            if (hts.get(i).getEndTime() == null) {
                continue;
            }
            TakenTask takenTask = new TakenTask(hts.get(i), this);
            result.add(takenTask);
        }
        return result;
    }

    /**
     * 返回任务
     * <p>
     * 根据任务的ID返回任务实体类{@link bas.jeda.workflow.UserTask}</p>
     *
     * @param id 任务ID
     * @return 用户任务 {@link bas.jeda.workflow.UserTask}
     */
    @Override
    public UserTask getUserTask(String id) {
        Task t = this.getAnchor().getTaskService().createTaskQuery().taskId(id).includeProcessVariables().includeTaskLocalVariables().singleResult();
        if (t == null) {
            return null;
        }
        return new UserTask(t, this);
    }

    @Override
    public List<MyProcessDefinition> getAllProcessDefinition() {
        List<ProcessDefinition> items;
        items = (List<ProcessDefinition>) this.getAnchor().getRepositoryService().createProcessDefinitionQuery().list();
        List<MyProcessDefinition> result = new ArrayList<MyProcessDefinition>();
        for (int i = 0; i < items.size(); i++) {
            MyProcessDefinition o = new MyProcessDefinition();
            o.setDocument(items.get(i).getDescription());
            o.setName(items.get(i).getName());
            o.setId(items.get(i).getId());
            result.add(o);
        }
        return result;
    }

    /**
     * 返回流程定义
     *
     * @param processDefineID 流程定义ID
     * @return 流程定义实体类{@link bas.jeda.workflow.MyProcessDefinition}
     */
    @Override
    public MyProcessDefinition getProcessDefinition(String processDefineID) {

        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.getAnchor().getRepositoryService())
                .getDeployedProcessDefinition(processDefineID);
        MyProcessDefinition o = new MyProcessDefinition();
        o.setDocument(def.getDescription());
        o.setName(def.getName());
        o.setId(def.getId());
        return o;
    }

    /**
     * 获取当前运行的流程实例
     *
     * @return 流程实例列表{@link bas.jeda.workflow.MyProcessInstance}
     */
    @Override
    public List<MyProcessInstance> getAllProcessInstance() {
        List<ProcessInstance> lists = this.getAnchor().getRuntimeService().createProcessInstanceQuery().list();
        List<MyProcessInstance> rs = new ArrayList<MyProcessInstance>(lists.size());
        for (ProcessInstance item : lists) {
            MyProcessInstance mp = new MyProcessInstance();
            mp.setProcessInstanceId(item.getProcessInstanceId());
            mp.setBusinessKey(item.getBusinessKey());
            mp.setIsSuspended(item.isSuspended());
            mp.setName(item.getName());
            mp.setProcessDefinitionId(item.getProcessDefinitionId());
            mp.setProcessDefinitionName(item.getProcessDefinitionName());
            Map<String, Object> processVariables = new HashMap<String, Object>();
            processVariables.putAll(item.getProcessVariables());
            mp.setProcessVariables(processVariables);
            mp.setIsEnded(item.isEnded());
            rs.add(mp);
        }
        return rs;
    }

    public UserActivity getEndtActivity(String processDefineID) {
        UserActivity userActivity = new UserActivity();
        ActivityImpl ai = getEndActivityInner(processDefineID);
        userActivity.setId(ai.getId());
        userActivity.setName("结束");
        userActivity.setMemo((String) ai.getProperty("documentation"));
        return userActivity;
    }

    public UserActivity getStartActivity(String processDefineID) {
        UserActivity userActivity = new UserActivity();
        ActivityImpl ai = getStartActivityInner(processDefineID);
        userActivity.setId(ai.getId());
        userActivity.setName("开始");
        userActivity.setMemo((String) ai.getProperty("documentation"));
        return userActivity;
    }

    private ActivityImpl findActivityByType(String processDefineID, String type) {
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.getAnchor().getRepositoryService())
                .getDeployedProcessDefinition(processDefineID);

        List<ActivityImpl> activitiList = def.getActivities();
        ActivityImpl activity = null;
        for (ActivityImpl item : activitiList) {
            if (item.getProperty("type").equals(type)) {
                activity = item;

                break;
            }
        }
        return activity;
    }

    private ActivityImpl getEndActivityInner(String processDefineID) {

        return findActivityByType(processDefineID, "endEvent");
    }

    private ActivityImpl getStartActivityInner(String processDefineID) {
        return findActivityByType(processDefineID, "startEvent");

    }

    /**
     * <p>
     * 根据流程的定义ID返回全部用户任务</p>
     * <p>
     * </p> @param processDefineID 流程定义ID
     *
     * @return 用户任务列表{@link bas.jeda.workflow.IUserActicity}
     */
    @Override
    public List<IUserActicity> getAllUserActivity(String processDefineID) {
        /*  因为在系统中保存的活动列表是按照编辑流程时创建的顺序保存的，所以不能直接从列表中获取用户任务的清单
            下面采用的办法是先找到起始节点，然后根据流程获取用户任务清单，但是考虑流程的复杂性，下面的程序可能存在逻辑问题。
         */

        ActivityImpl startActivity = this.getStartActivityInner(processDefineID);
        List<IUserActicity> rs = new ArrayList<IUserActicity>();
        Set<ActivityImpl> buffer = new HashSet<ActivityImpl>();

        this.addActivity2list(startActivity, rs, buffer);
        return rs;
    }


    /**
     * 根据流程定义取出流程描述信息
     */
    @Override
    public ProcressDescription getProcessDescription(String processDefineID) {

        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.getAnchor().getRepositoryService())
                .getDeployedProcessDefinition(processDefineID);

        Gson gson = new Gson();
        if(def.getDescription() == null || def.getDescription()==""){
            return null;
        }else{
            return gson.fromJson(def.getDescription().replace("<![CDATA[", "").replace("]]>", ""),ProcressDescription.class );
        }

    }

    private UserActivity getUserActivity(ActivityImpl act) {
        UserActivity userActivity = new UserActivity();
        userActivity.setId(act.getId());
        userActivity.setName((String) act.getProperty("name"));
        userActivity.setMemo((String) act.getProperty("document"));
        return userActivity;
    }

    private void addActivity2list(ActivityImpl actItem, List<IUserActicity> rs, Set<ActivityImpl> buffer) {

        if (actItem.getProperty("type").equals("endEvent")) {

            return;
        }
        List<PvmTransition> ps = actItem.getOutgoingTransitions();
        for (int i = 0; i < ps.size(); i++) {
            PvmActivity p = ps.get(i).getDestination();
            if (p instanceof ActivityImpl) {
                ActivityImpl act = (ActivityImpl) p;
                if (buffer.contains(act)) {
                    continue;
                }
                if (act.getProperty("type").equals("userTask")) {
                    rs.add(this.getUserActivity(act));
                    buffer.add(act);
                }
            }
        }
        for (int i = 0; i < ps.size(); i++) {
            PvmActivity p = ps.get(i).getDestination();
            if (p instanceof ActivityImpl) {
                this.addActivity2list((ActivityImpl) p, rs, buffer);
            }

        }
    }

    /**
     * 根据ProcessID返回当前活动
     *
     * @param processID 流程实例ID
     * @return 用户任务列表{@link bas.jeda.workflow.IUserTask}
     */
    @Override
    public List<IUserTask> getCurrentActivity(String processID) {
        List<Task> tasks = this.getAnchor().getTaskService().createTaskQuery().processInstanceId(processID).list();
        List<IUserTask> rs = new ArrayList<IUserTask>(tasks.size());
        for (int j = 0; j < tasks.size(); j++) {
            UserActivity userActivity = new UserActivity();
            userActivity.setAssignee(tasks.get(j).getAssignee());
            userActivity.setFormKey(tasks.get(j).getFormKey());
            userActivity.setMemo(tasks.get(j).getDescription());
            userActivity.setName(tasks.get(j).getName());
            rs.add(userActivity);
        }
        return rs;
    }

    /**
     * <p>
     * 完成用户任务</p>
     * <p>
     * 本地变量和流程实例变量都是可以在流程引擎中保存的变量，都是hashMap格式，区别是本地变量只保存在当前任务中，实例变量则为整个实例共享之变量，
     * 各个节点之间的变量传递采用流程变量保存。</p>
     *
     * @param taskID     任务id
     * @param localVar   本地变量
     * @param processVar 流程实例变量
     */
    @Override
    public void completeTask(String taskID, Map<String, Object> localVar, Map<String, Object> processVar) {
        UserTask uTask = this.getUserTask(taskID);
        this.getAnchor().getTaskService().setVariablesLocal(uTask.getTaskID(), localVar);
        this.getAnchor().getTaskService().complete(uTask.getTaskID(), processVar);
    }

    public void setLocalVar(String taskID, Map<String, Object> localVar) {

        this.getAnchor().getTaskService().setVariablesLocal(taskID, localVar);
    }

    /**
     * 返回历史活动的参数
     *
     * @param processID 流程实例ID
     * @return
     */
    @Override
    public Map<String, Object> getHitProcessVars(String processID) {
        return this.getHitVarByTask(processID, null);
    }

    /**
     * 返回历史活动的参数
     *
     * @param processID 流程实例ID
     * @param taskID    任务ID
     * @return 变量键值对
     */
    @Override
    public Map<String, Object> getHitLocalVarsByTask(String processID, String taskID) {
        return this.getHitVarByTask(processID, taskID);
    }

    /**
     * 根据流程ID返回变量值
     *
     * @param processID 流程ID
     * @param varName   变量名
     * @return
     */
    public Object getHitVarByProcessId(String processID, String varName) {
        List<HistoricVariableInstance> list = this.getAnchor().getHistoryService()//与历史数据（历史表）相关的Service
                .createHistoricVariableInstanceQuery()//创建历史任务实例查询
                .processInstanceId(processID)//                    
                .variableName(varName)
                .list();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0).getValue();
    }

    /**
     * 返回历史活动的参数
     *
     * @param processID 流程实例ID
     * @param taskID    任务ID
     * @return 变量键值对
     */
    @Override
    public Map<String, Object> getHitVarByTask(String processID, String taskID) {
        List<HistoricVariableInstance> list;
        if (taskID == null) {
            list = this.getAnchor().getHistoryService()//与历史数据（历史表）相关的Service
                    .createHistoricVariableInstanceQuery()//创建历史任务实例查询
                    .processInstanceId(processID)//                    
                    .list();
        } else {
            list = this.getAnchor().getHistoryService()//与历史数据（历史表）相关的Service
                    .createHistoricVariableInstanceQuery()//创建历史任务实例查询
                    .processInstanceId(processID)//
                    .taskId(taskID)
                    .list();
        }
        Map<String, Object> r = new HashMap<String, Object>();
        for (HistoricVariableInstance hti : list) {
            if (taskID == null) {
                if (hti.getTaskId() == null) {
                    r.put(hti.getVariableName(), hti.getValue());
                }
            } else {
                if (taskID.equals(hti.getTaskId())) {
                    r.put(hti.getVariableName(), hti.getValue());
                }
            }
        }
        return r;
    }

    /**
     * <p>
     * 查找流程实例的消息等待任务</p>
     * <p>
     * 专门查询intermediateMessageCatch类型的活动，根据流程实例ID和任务名称查询</p>
     *
     * @param processInstanceId 流程实例ID
     * @param activityName      活动名称
     * @return 活动实例{@link bas.jeda.workflow.MyHisActivity}
     */
    @Override
    public MyHisActivity findWaitforMessage(String processInstanceId, String activityName) {
        List<HistoricActivityInstance> list = this.getAnchor().getHistoryService()//
                .createHistoricActivityInstanceQuery()//创建历史活动实例的查询
                .processInstanceId(processInstanceId)//
                .activityName(activityName)
                .activityType("intermediateMessageCatch")
                .orderByHistoricActivityInstanceStartTime().asc()//
                .list();
        if (list != null && list.size() > 0) {
            return copyHisActivity(list.get(0));
        }
        return null;
    }

    /**
     * 向流程实例发送消息
     *
     * @param processInstanceId 流程实例ID
     * @param msg               消息名称
     */
    @Override
    public void SendMessage(String processInstanceId, String msg) {
        Execution execution = this.getAnchor().getRuntimeService().createExecutionQuery().processInstanceId(processInstanceId).messageEventSubscriptionName(msg).singleResult();
        this.getAnchor().getRuntimeService().messageEventReceived(msg, execution.getId(), null);
    }

    @Override
    public void SendMessage(String processInstanceId, String executionId, String msg, Map<String, Object> processVariables) {
        this.getAnchor().getRuntimeService().messageEventReceived(msg, executionId, processVariables);
    }

    /**
     * 将{@link class#}类复制为{@link class#}类
     *
     * @param hai {@link class#}
     * @return 活动实例{@link bas.jeda.workflow.MyHisActivity}
     */
    private MyHisActivity copyHisActivity(HistoricActivityInstance hai) {
        MyHisActivity a = new MyHisActivity();
        a.setId(hai.getId());
        a.setActivityId(hai.getActivityId());
        a.setActivityName(hai.getActivityName());
        a.setActivityType(hai.getActivityType());
        a.setProcessDefinitionId(hai.getProcessDefinitionId());
        a.setProcessInstanceId(hai.getProcessInstanceId());
        a.setExecutionId(hai.getExecutionId());
        a.setTaskId(hai.getTaskId());
        a.setCalledProcessInstanceId(hai.getCalledProcessInstanceId());
        a.setAssignee(hai.getAssignee());
        a.setStartTime(hai.getStartTime());
        a.setEndTime(hai.getEndTime());
        a.setDurationInMillis(hai.getDurationInMillis());
        a.setTenantId(hai.getTenantId());
        return a;
    }

    /**
     * 返回一个流程的启动节点信息
     *
     * @param processInstanceId 流程实例ID
     * @return
     */
    @Override
    public MyHisActivity findStartNode(String processInstanceId) {
        List<MyHisActivity> list = this.findHistoryActiviti(processInstanceId);
        for (MyHisActivity item : list) {
            if ("startEvent".equals(item.getActivityType())) {
                return item;
            }
        }
        return null;
    }

    /**
     * 返回流程实例的历史任务
     *
     * @param processInstanceId 流程实例ID
     * @return 任务实例列表{@link bas.jeda.workflow.MyHisActivity}
     */
    @Override
    public List<MyHisActivity> findHistoryActiviti(String processInstanceId) {
        //"7519";
        List<HistoricActivityInstance> list = this.getAnchor().getHistoryService()//
                .createHistoricActivityInstanceQuery()//创建历史活动实例的查询
                .processInstanceId(processInstanceId)//
                .orderByHistoricActivityInstanceStartTime().asc()//
                .list();
        List<MyHisActivity> result = new ArrayList<MyHisActivity>();
        if (list != null && list.size() > 0) {
            for (HistoricActivityInstance hai : list) {

                MyHisActivity myhis = copyHisActivity(hai);
                //判断把并行网关和 判断网关在流程历史中移除
                if(hai.getActivityId().indexOf("parallelgateway") !=-1 || hai.getActivityId().indexOf("exclusivegateway" ) !=-1){
                    continue;
                }

                if ("startEvent".equals(myhis.getActivityType())) {
                    //启动节点，设置启动节点的人
                    Object obj = this.getHitVarByProcessId(processInstanceId, "taskApplyUser");
                    if (obj != null) {
                        myhis.setAssignee((String) obj);
                    }
                } else {
                    if (myhis.getTaskId() != null && (!"".equals(myhis.getTaskId()))) {
                        if (myhis.getEndTime() != null) {
                            //已经完成的任务
                            myhis.setVariablesLocal(this.getHitVarByTask(processInstanceId, myhis.getTaskId()));
                            HistoricTaskInstance hts = (HistoricTaskInstance) this.getAnchor().getHistoryService()
                                    .createHistoricTaskInstanceQuery().taskId(myhis.getTaskId()).singleResult();

                            Gson gson = new Gson();
                            try {
                                TaskDocument tss = gson.fromJson(hts.getDescription().replace("<![CDATA[", "").replace("]]>", ""), TaskDocument.class);
                                List<TaskDocument.ButtonValue> btns = tss.getButtonValues();
                                for (int i = 0; i < btns.size(); i++) {
                                    TaskDocument.ButtonValue btn = btns.get(i);
                                    if (myhis.getVariablesLocal().get(btn.getVarName()) != null) {
                                        myhis.setActionBtnName(btn.getBtnName());
                                        myhis.setActionBtnValue(btn.getVarValue());
                                        break;
                                    }
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        } else {
                            myhis.setVariablesLocal(this.getAnchor().getTaskService().getVariablesLocal(myhis.getTaskId()));
                        }

                    }
                }
                result.add(myhis);
            }
        }
        return result;
    }

    /**
     * @return the anchor
     */
    public FlowAnchor getAnchor() {
        return anchor;
    }

    /**
     * @param anchor the anchor to set
     */
    @Override
    public void setAnchor(FlowAnchor anchor) {
        this.anchor = anchor;
    }

    /**
     * 根据用户名查询用户参与的流程信息。
     *
     * @param username
     * @return
     */
    @Override
    public List findHisByUserName(String username) {

        long count = this.getAnchor().getHistoryService().createHistoricProcessInstanceQuery().involvedUser(username)
                .orderByProcessInstanceStartTime().desc().count();
        //查询指定用户参与的流程信息 （流程历史  用户参与 ）
        List<HistoricProcessInstance> historicProcessInstances = this.getAnchor().getHistoryService().createHistoricProcessInstanceQuery().involvedUser(username)
                .orderByProcessInstanceStartTime().desc().list();//.listPage(1, 100);
        return historicProcessInstances;
    }

    public ActivityImpl findActivityByID(String processDefineID, String activityID) {
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.getAnchor().getRepositoryService())
                .getDeployedProcessDefinition(processDefineID);
        return def.findActivity(activityID);
    }

    public String getBusinessKey(String processid) {
        ProcessInstance pi = this.getAnchor().getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processid).singleResult();
        if (pi == null) {
            //已经结束的流程
            HistoricProcessInstance his = this.anchor.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processid).singleResult();
            return his.getBusinessKey();
        } else {
            //正在运行的流程
            return pi.getBusinessKey();

        }

    }

    /**
     * 返回历史任务
     * <p>
     * 根据任务的ID返回任务实体类{@link bas.jeda.workflow.UserTask}</p>
     *
     * @param id 任务ID
     * @return 用户任务 {@link bas.jeda.workflow.UserTask}
     */
    @Override
    public TakenTask getHisUserTask(String id) {
        HistoricTaskInstance his = this.anchor.getHistoryService().createHistoricTaskInstanceQuery().includeProcessVariables().taskId(id).singleResult();

        if (his == null) {
            return null;
        }
        return new TakenTask(his, this);
    }

    public Map<String, Object> getLocalVar(String taskID) {
        return this.getAnchor().getTaskService().getVariables(taskID);
    }

    public Map<String, Object> getProcessVar(String taskID) {
        return this.getAnchor().getTaskService().getVariablesLocal(taskID);
    }

    public List<Map<String, Object>> getProcessTrace(String wfKey) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();
        RepositoryService repositoryService = this.anchor.getRepositoryService();
//        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(wfKey).latestVersion().singleResult();
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(wfKey).singleResult();
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(pd.getId());
        List<ActivityImpl> activitiList = processDefinition.getActivities();
        InputStream xmlIs = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());
        BpmnModel bm = new BpmnXMLConverter().convertToBpmnModel(new InputStreamSource(xmlIs), false, true);

        Class<?> clazz = Class.forName("org.activiti.image.impl.DefaultProcessDiagramGenerator");
        //BpmnModel bpmnModel, String imageType,
        //String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader
        Method method = clazz.getDeclaredMethod("initProcessDiagramCanvas"
                , BpmnModel.class
                , String.class
                , String.class
                , String.class
                , String.class
                , ClassLoader.class);
        method.setAccessible(true);

        DefaultProcessDiagramCanvas pdc = (DefaultProcessDiagramCanvas) method.invoke(clazz.newInstance()
                , bm
                , "png"
                , this.anchor.getEngine().getProcessEngineConfiguration().getActivityFontName()
                , this.anchor.getEngine().getProcessEngineConfiguration().getLabelFontName()
                , this.anchor.getEngine().getProcessEngineConfiguration().getAnnotationFontName()
                , this.anchor.getEngine().getProcessEngineConfiguration().getClassLoader()); // 调用方法

        clazz = Class.forName("org.activiti.image.impl.DefaultProcessDiagramCanvas");
        Field minXField = clazz.getDeclaredField("minX"); // 得到minX字段  
        Field minYField = clazz.getDeclaredField("minY");
        minXField.setAccessible(true);
        minYField.setAccessible(true);


        for (ActivityImpl activity : activitiList) {
            Map<String, Object> activityInfo = new HashMap<String, Object>();
            activityInfo.put("width", activity.getWidth());
            activityInfo.put("height", activity.getHeight());
            activityInfo.put("x", activity.getX());
            activityInfo.put("y", activity.getY());
            activityInfo.put("actId", activity.getId());
            activityInfo.put("name", activity.getProperty("name"));
            activityInfos.add(activityInfo);
        }
        return activityInfos;
    }

    public UserTaskInfo createMeetingTask(String userID) {
        return new MeetingTask(userID);
    }

    /**
     * 根据流程实例id 获取活动的用户任务 只查询最新的一个任务
     *
     * @return
     */
    @Override
    public UserTask getUserTaskInfoBypricessId(String userid, String processId) {
        List<Task> list = this.getAnchor().getTaskService().createTaskQuery().taskAssignee(userid).includeProcessVariables().processInstanceId(processId).orderByTaskCreateTime().desc().list();
        UserTask ut = new UserTask(list.get(0), this);
        return ut;
    }

    /**
     * 根据流程实例id 获取活动的用户任务 只查询最新的一个任务
     *
     * @return
     */
    @Override
    public UserTask getUserTaskInfoBypricessId(String processId) {
        List<Task> list = this.getAnchor().getTaskService().createTaskQuery().processInstanceId(processId).includeProcessVariables().orderByTaskCreateTime().desc().list();
        if (list != null && list.size() > 0) {

            return new UserTask(list.get(0), this);
        }
        return null;
    }

    /**
     * <p>
     * 根据用户返回待办任务</p>
     * <p>
     * {@link bas.jeda.workflow.UserTask}是代办任务的实体类。</p>
     *
     * @param userID 用户ID
     * @return 用户任务的列表{@link bas.jeda.workflow.UserTask}
     */
    @Override
    public List<UserTaskForList> getTODOtaskList(String userID, int pagestart, int pagesize) {
        TaskQuery taskQuery = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables();//.taskAssignee(UserID).orderByTaskCreateTime().desc().listPage(pagestart, pagesize);
        if(!"".equals(userID) & userID !=null){
            taskQuery.taskAssignee(userID);
        }
        List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(pagestart, pagesize);
        List<UserTaskForList> uTasks = new ArrayList<UserTaskForList>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            UserTaskForList ut = new UserTaskForList(t, this);
            uTasks.add(ut);
        }
        return uTasks;
    }
    /**
     * <p>
     * 待办任务查询条件  zhujianhu</p>
     * <p>
     * {@link bas.jeda.workflow.UserTask}是代办任务的实体类。</p>
     *
     * @param
     * @return 用户任务的列表{@link bas.jeda.workflow.UserTask}
     */
    @Override
    public List<UserTaskForList> gettodoList(String businesscode, String businessname,String UserID,int pagestart, int pagesize) {

        //List<Task> tasks = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables().taskAssignee(UserID).orderByTaskCreateTime().desc().list()
        TaskQuery taskQuery = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables().taskAssignee(UserID);
        if(businesscode!=null && !"".equals(businesscode)){
            taskQuery.processVariableValueLike("businesscode", "%"+businesscode+"%");
        }
        if(businessname!=null && !"".equals(businessname)){
            taskQuery.processVariableValueLike("businessname", "%"+businessname+"%");
        }

        List<Task> tasks =taskQuery .orderByTaskCreateTime().desc().listPage(pagestart, pagesize);
//        List<Task> tasksname = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables().processVariableValueLike(businessname, businessname).orderByTaskCreateTime().desc().list();



        List<UserTaskForList> uTasks = new ArrayList<UserTaskForList>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            UserTaskForList ut = new UserTaskForList(t, this);
            uTasks.add(ut);
        }
        return uTasks;


    }


    /**
     * <p>
     * 审批中的项目  zhujianhu</p>
     * <p>
     * {@link bas.jeda.workflow.UserTask}是代办任务的实体类。</p>
     *
     * @param
     * @return 用户任务的列表{@link bas.jeda.workflow.UserTask}
     */
    @Override
    public List<UserTaskForList> gettodoList(String businesscode, String businessname,String productName,String parent,String taskName,String userID,int pagestart, int pagesize) {

        //List<Task> tasks = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables().taskAssignee(UserID).orderByTaskCreateTime().desc().list()
        TaskQuery taskQuery = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables();
        if(businesscode!=null && !"".equals(businesscode)){
            taskQuery.processVariableValueLike("businesscode", "%"+businesscode+"%");
        }
        if(businessname!=null && !"".equals(businessname)){
            taskQuery.processVariableValueLike("businessname", "%"+businessname+"%");
        }
        if(productName!=null && !"".equals(productName)){
            taskQuery.processVariableValueLike("productName", "%"+productName+"%");
        }
        if(parent!=null && !"".equals(parent)){
            taskQuery.processVariableValueLike("parent", "%"+parent+"%");
        }
        if(taskName!=null && !"".equals(taskName)){
            taskQuery.processVariableValueLike("taskName", "%"+taskName+"%");
        }
        if(userID!=null && !"".equals(userID)){
            taskQuery.processVariableValueLike("userID", "%"+userID+"%");
        }

        List<Task> tasks =taskQuery .orderByTaskCreateTime().desc().listPage(pagestart, pagesize);
//        List<Task> tasksname = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables().processVariableValueLike(businessname, businessname).orderByTaskCreateTime().desc().list();



        List<UserTaskForList> uTasks = new ArrayList<UserTaskForList>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            UserTaskForList ut = new UserTaskForList(t, this);
            uTasks.add(ut);
        }
        return uTasks;


    }

    /**
     * <p>
     * 已办任务查询条件  zhujianhu</p>
     * <p>
     * {@link bas.jeda.workflow.UserTask}是代办任务的实体类。</p>
     *
     * @param
     * @return 用户任务的列表{@link bas.jeda.workflow.UserTask}
     */
    @Override
    public List<TakenTaskForList> gettakenlistList(String businesscode, String businessname,String UserID,int pagestart, int pagesize) {
        /*List<HistoricTaskInstance> hts = this.getAnchor().getHistoryService()
                .createHistoricTaskInstanceQuery().taskAssignee(UserID).includeProcessVariables().finished().orderByTaskCreateTime().desc().listPage(pagestart,pagesize);*/
        HistoricTaskInstanceQuery finished = this.getAnchor().getHistoryService()
                .createHistoricTaskInstanceQuery().taskAssignee(UserID).includeProcessVariables().finished();




        if(businesscode!=null && !"".equals(businesscode)){
            finished.processVariableValueLike("businesscode", "%"+businesscode+"%");
        }
        if(businessname!=null && !"".equals(businessname)){
            finished.processVariableValueLike("businessname", "%"+businessname+"%");
        }

        List<HistoricTaskInstance> tasks =finished .orderByTaskCreateTime().desc().listPage(pagestart,pagesize);
//        List<Task> tasksname = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables().processVariableValueLike(businessname, businessname).orderByTaskCreateTime().desc().list();


        List<TakenTaskForList> result = new ArrayList<TakenTaskForList>();
        for (int i = 0; i < tasks.size(); i++) {

            TakenTaskForList takenTask = new TakenTaskForList(tasks.get(i), this);
            result.add(takenTask);
        }
        return result;

    }
    /**
     * 返回用户的已办任务
     *
     * @param userID
     */
    @Override
    public List<TakenTaskForList> getTAKENTaskList(String userID, int pagestart, int pagesize) {
        List<HistoricTaskInstance> hts = this.getAnchor().getHistoryService()
                .createHistoricTaskInstanceQuery().taskAssignee(userID).includeProcessVariables().includeTaskLocalVariables().finished().orderByTaskCreateTime().desc().listPage(pagestart,pagesize);
        List<TakenTaskForList> result = new ArrayList<TakenTaskForList>();
        for (int i = 0; i < hts.size(); i++) {
//            if (hts.get(i).getEndTime() == null) {
//                continue;
//            }
            TakenTaskForList takenTask = new TakenTaskForList(hts.get(i), this);



            result.add(takenTask);
        }
        return result;
    }

    /**
     * 返回用户待办任务数量
     *
     * @param UserID 用户ID
     */
    @Override
    public Long countTODOtask(String UserID) {
        return this.getAnchor().getTaskService().createTaskQuery().taskAssignee(UserID).count();
    }

    /**
     * 返回用户的待办任务 数量
     *
     * @param userID
     */
    @Override
    public Long countTODOTask(String userID) {
        TaskQuery taskQuery = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables();
        if(!"".equals(userID) & userID !=null){
            taskQuery.taskAssignee(userID);
        }
        return taskQuery.count();
    }

    /**
     * 返回用户的已办任务 数量
     *
     * @param userID
     */
    @Override
    public Long countTAKENTask(String userID) {
        return this.getAnchor().getHistoryService().createHistoricTaskInstanceQuery().finished().taskAssignee(userID).count();
    }

    /**
     * 返回用户的已办任务 数量
     *zhujianhu
     * @param userID
     */
    @Override
    public Long countTask(String userID,String businesscode, String businessname) {
        HistoricTaskInstanceQuery historicTaskInstanceQuery = this.getAnchor().getHistoryService().createHistoricTaskInstanceQuery().finished().taskAssignee(userID);

        if(businesscode!=null && !"".equals(businesscode)){
            historicTaskInstanceQuery.processVariableValueLike("businesscode", "%"+businesscode+"%");
        }
        if(businessname!=null && !"".equals(businessname)){
            historicTaskInstanceQuery.processVariableValueLike("businessname", "%"+businessname+"%");
        }

        return historicTaskInstanceQuery.count();


    }


    /**
     * 返回用户的待办任务 数量
     *zhujianhu
     * @param userID
     */
    @Override
    public Long countTODOTask(String businesscode,String businessname,String productName,String parent,String taskName,String userID) {
        TaskQuery taskQuery = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables();

        if(businesscode!=null && !"".equals(businesscode)){
            taskQuery.processVariableValueLike("businesscode", "%"+businesscode+"%");
        }
        if(businessname!=null && !"".equals(businessname)){
            taskQuery.processVariableValueLike("businessname", "%"+businessname+"%");
        }
        if(productName!=null && !"".equals(productName)){
            taskQuery.processVariableValueLike("productName", "%"+productName+"%");
        }
        if(parent!=null && !"".equals(parent)){
            taskQuery.processVariableValueLike("parent", "%"+parent+"%");
        }
        if(taskName!=null && !"".equals(taskName)){
            taskQuery.processVariableValueLike("taskName", "%"+taskName+"%");
        }
        if(userID!=null && !"".equals(userID)){
            taskQuery.processVariableValueLike("userID", "%"+userID+"%");
        }
        return taskQuery.count();


    }



    /**
     * 返回审批中项目 数量
     *zhujianhu
     * @param userID
     */
    @Override
    public Long countTODOTask(String userID,String businesscode, String businessname) {
        TaskQuery taskQuery = this.getAnchor().getTaskService().createTaskQuery().includeProcessVariables().taskAssignee(userID);

        if(businesscode!=null && !"".equals(businesscode)){
            taskQuery.processVariableValueLike("businesscode", "%"+businesscode+"%");
        }
        if(businessname!=null && !"".equals(businessname)){
            taskQuery.processVariableValueLike("businessname", "%"+businessname+"%");
        }

        return taskQuery.count();


    }

    /**
     * 返回发起的审批中的流程信息
     *
     * @param userID
     */
    @Override
    public List<UserTaskForList> getStartFlow(String userID,List flowkey ,int pagestart, int pagesize) {

        List<Task> tasks = this.getAnchor().getTaskService().createTaskQuery().processDefinitionKeyIn(flowkey).includeProcessVariables().processVariableValueEquals("taskApplyUser", userID).orderByTaskCreateTime().desc().listPage(pagestart, pagesize);

        List<UserTaskForList> uTasks = new ArrayList<UserTaskForList>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            UserTaskForList ut = new UserTaskForList(t, this);
            uTasks.add(ut);
        }

//        System.out.println(uTasks);
        return uTasks;


    }

    /**
     * 统计用户发起的审批中的流程
     * @param userID
     * @param flowkey
     * @return
     */
    @Override
    public long countStartFlow(String userID,List flowkey ) {
       return this.getAnchor().getTaskService().createTaskQuery().processDefinitionKeyIn(flowkey).processVariableValueEquals("taskApplyUser", userID).count();
    }

    /**
     * <p>
     * 根据流程的定义ID返回全部用户任务</p>
     * <p>
     * </p> @param processDefineID 流程定义ID
     *
     * @return 用户任务列表{@link bas.jeda.workflow.IUserActicity}
     */
    @Override
    public List<IUserActicity> getAllUserActivityNoOrder(String processDefineID) {

        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.anchor.getRepositoryService()).getDeployedProcessDefinition(processDefineID);
        List<ActivityImpl> activitiList = processDefinition.getActivities();
        List<IUserActicity> rs = new ArrayList<IUserActicity>();

        for (int i = 0; i < activitiList.size() ; i++) {
            ActivityImpl act = activitiList.get(i);

            if (act.getProperty("type").equals("userTask")) {
                UserActivity userActivity = new UserActivity();
                userActivity.setId(act.getId());
                userActivity.setName((String) act.getProperty("name"));
                userActivity.setMemo((String) act.getProperty("document"));

                rs.add(userActivity);
            }

        }

        return rs;
    }

    /**
     * 根据ProcessID返回当前活动 任务id 默认取最新一个（如果流程有分支改方法可能会有问题）
     *
     * @param processID 流程实例ID
     * @return
     */
    @Override
    public String getCurrentTaskID(String processID) {
        String taskid ="";
        List<Task> tasks = this.getAnchor().getTaskService().createTaskQuery().processInstanceId(processID).orderByTaskCreateTime().desc().list();
        if(tasks !=null && tasks.size() >0){
            taskid = tasks.get(0).getId();
        }
        return taskid;
    }
}
