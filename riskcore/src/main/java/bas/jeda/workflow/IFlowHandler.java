/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author menghui
 */
public interface IFlowHandler {
    /**
     * 返回已办任务列表
     * @param userID
     * @return
     */
    @Deprecated
    List<TakenTask> getTAKENTask(String userID);

    /**
     * 向流程实例发送消息
     *
     * @param processInstanceId 流程实例ID
     * @param msg 消息名称
     */
    void SendMessage(String processInstanceId, String msg);

    void SendMessage(String processInstanceId, String executionId, String msg,Map<String, Object> processVariables);
    /**
     * <p>
     * 完成用户任务</p>
     * <p>
     * 本地变量和流程实例变量都是可以在流程引擎中保存的变量，都是hashMap格式，区别是本地变量只保存在当前任务中，实例变量则为整个实例共享之变量，
     * 各个节点之间的变量传递采用流程变量保存。</p>
     *
     * @param taskID 任务id
     * @param localVar 本地变量
     * @param processVar 流程实例变量
     */
    void completeTask(String taskID, Map<String, Object> localVar, Map<String, Object> processVar);

    /**
     * 返回流程实例的历史任务
     *
     * @param processInstanceId 流程实例ID
     * @return 任务实例列表{@link bas.jeda.workflow.MyHisActivity}
     *
     */
    List<MyHisActivity> findHistoryActiviti(String processInstanceId);

    /**
     * 返回一个流程的启动节点信息
     * @param processInstanceId 流程实例ID
     * @return
     */
    MyHisActivity findStartNode(String processInstanceId);

    /**
     * <p>
     * 查找流程实例的消息等待任务</p>
     * <p>
     * 专门查询intermediateMessageCatch类型的活动，根据流程实例ID和任务名称查询</p>
     *
     * @param processInstanceId 流程实例ID
     * @param activityName 活动名称
     * @return 活动实例{@link bas.jeda.workflow.MyHisActivity}
     */
    MyHisActivity findWaitforMessage(String processInstanceId, String activityName);

    List<MyProcessDefinition> getAllProcessDefinition();

    /**
     * 获取当前运行的流程实例
     *
     * @return 流程实例列表{@link bas.jeda.workflow.MyProcessInstance}
     */
    List<MyProcessInstance> getAllProcessInstance();

    /**
     * <p>
     * 根据流程的定义ID返回全部用户任务</p>
     * <p>
     * </p> @param processDefineID 流程定义ID
     *
     * @return 用户任务列表{@link bas.jeda.workflow.IUserActicity}
     */
    List<IUserActicity> getAllUserActivity(String processDefineID);
//
//    /**
//     * @return the anchor
//     */
//    FlowAnchor getAnchor();

    /**
     * 根据ProcessID返回当前活动
     *
     * @param processID 流程实例ID
     * @return 用户任务列表{@link bas.jeda.workflow.IUserTask}
     */
    List<IUserTask> getCurrentActivity(String processID);

    /**
     * 返回历史活动的参数
     *
     * @param processID 流程实例ID
     * @param taskID 任务ID
     * @return 变量键值对
     */
    Map<String, Object> getHitLocalVarsByTask(String processID, String taskID);

    /**
     * 返回历史活动的参数
     *
     * @param processID 流程实例ID
     * @return
     */
    Map<String, Object> getHitProcessVars(String processID);

    /**
     * 返回历史活动的参数
     *
     * @param processID 流程实例ID
     * @param taskID 任务ID
     * @return 变量键值对
     */
    Map<String, Object> getHitVarByTask(String processID, String taskID);

    /**
     * 返回流程定义
     *
     * @param processDefineID 流程定义ID
     * @return 流程定义实体类{@link bas.jeda.workflow.MyProcessDefinition}
     */
    MyProcessDefinition getProcessDefinition(String processDefineID);

    InputStream getProcessImg(String processid);

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
    List<UserTaskInfo> getTODOtask(String UserID);

    /**
     * 返回任务
     * <p>
     * 根据任务的ID返回任务实体类{@link bas.jeda.workflow.UserTask}</p>
     *
     * @param id 任务ID
     * @return 用户任务 {@link bas.jeda.workflow.UserTask}
     */
    UserTask getUserTask(String id);

    /**
     * @param anchor the anchor to set
     */
    void setAnchor(FlowAnchor anchor);

    /**
     * 启动流程
     *
     * @param flowid 流程ID
     * @param userid 启动的用户ID
     * @param businessurl 业务页面的URL
     * @param businesskey 业务主键
     * @param variables 参数
     * @return 启动的流程实例ID
     */
    String startFlowByID(String flowid, String userid, String businessurl, String businesskey, HashMap<String, Object> variables);
    /**
     * 根据流程ID返回变量值
     * @param processID 流程ID
     * @param varName   变量名
     * @return
     */
    Object getHitVarByProcessId(String processID, String varName);

    /**
     * 根据用户名查询用户参与的流程信息。
     * @param username
     * @return
     */
     List findHisByUserName(String username);

     void setLocalVar(String taskID, Map<String, Object> localVar);

     Map<String,Object> getLocalVar(String taskID);

     Map<String,Object> getProcessVar(String taskID);

     String getBusinessKey(String processid);

     TakenTask getHisUserTask(String id);

     UserActivity getEndtActivity(String processDefineID) ;

     UserActivity getStartActivity(String processDefineID);

     List<Map<String, Object>> getProcessTrace(String wfKey) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException ;

     UserTaskInfo createMeetingTask(String userID);

     UserTask getUserTaskInfoBypricessId(String userid ,String processId);

     UserTask getUserTaskInfoBypricessId(String processId);
    /**
     * <p>
     * 根据用户返回待办任务</p>
     * <p>
     * {@link bas.jeda.workflow.UserTask}是代办任务的实体类。</p>
     *
     * @param UserID 用户ID
     * @return 用户任务的列表{@link bas.jeda.workflow.UserTask}
     */
    List<UserTaskForList> getTODOtaskList(String UserID,int pagestart, int pagesize);

    /**
     * 返回已办任务列表
     * @param userID
     * @return
     */
    List<TakenTaskForList> getTAKENTaskList(String userID ,int pagestart,int pagesize);

    List<UserTaskForList> gettodoList(String businesscode, String businessname,String UserID,int pagestart, int pagesize);
    List<UserTaskForList> gettodoList(String businesscode, String businessname,String productName,String parent,String taskName,String userID,int pagestart, int pagesize);

    List<TakenTaskForList> gettakenlistList(String businesscode, String businessname,String UserID,int pagestart, int pagesize);
    /**
     * 返回用户待办任务数量
     *
     * @param UserID 用户ID
     */
    Long countTODOtask(String UserID) ;


    /**
     * 返回用户的已办任务 数量
     *
     * @param userID
     */
    Long countTAKENTask(String userID);
    /**
     * 返回用户的待办任务 数量
     *
     * @param userID
     */
    Long countTODOTask(String userID);

    Long countTODOTask(String userID,String businesscode, String businessname);
    Long countTODOTask(String businesscode,String businessname,String productName,String parent,String taskName,String userID);

    Long countTask(String userID,String businesscode, String businessname);

    List<UserTaskForList> getStartFlow(String userID, List flowkey,int pagestart, int pagesize);
    long countStartFlow(String userID, List flowkey);
    /**
     * 根据流程定义取出流程描述信息
     */
    ProcressDescription getProcessDescription(String processDefineID) ;


    /**
     * <p>
     * 根据流程的定义ID返回全部用户任务, 不进行排序</p>
     * @param processDefineID 流程定义ID
     */
    List<IUserActicity> getAllUserActivityNoOrder(String processDefineID);


    /**
     * 根据ProcessID返回当前活动 任务id 默认取最新一个（如果流程有分支改方法可能会有问题）
     *
     * @param processID 流程实例ID
     * @return
     */
    public String getCurrentTaskID(String processID) ;



}
