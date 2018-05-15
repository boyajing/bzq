/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>流程的流转控制器</p>
 * <p>默认的流程控制不适合可以任意驳回的场景,此类为流程增加任意驳回功能，提供默认的通过和驳回逻辑</p>
 * <p>通过逻辑：是指某用户任务节点，用户进行操作后，点击发送，将完成该节点，引擎根据流程定义进入下一个环节。该逻辑为流程引擎的默认逻辑</p>
 * <p>驳回逻辑：某用户任务节点，用户操作后，点击驳回，不完成该节点，而是将流程路径引入上一个节点，是达到驳回的目的。</p>
 * <p>以下的程序逻辑只能处理顺序的链接的人工节点之间的驳回，不能处理子流程、并行网管等情况下的节点驳回。</p>
 * @author menghui
 */
public class ProcessTurnCore {

    @Autowired
    private FlowAnchor anchor;

    /**
     * 审批通过
     *
     * @param taskId 当前任务ID
     * @param variables 流程存储参数
     * @throws Exception
     */
    public void passProcess(String taskId, Map<String, Object> variables)
            throws Exception {
//        List<Task> tasks = taskService.createTaskQuery().parentTaskId(taskId)  
//                .taskDescription("jointProcess").list();  
//        for (Task task : tasks) {// 级联结束本节点发起的会签任务  
//            commitProcess(task.getId(), null, null);  
//        }  
        commitProcess(taskId, variables, null);
    }

    /**
     * 驳回流程
     * 
     * @param taskId 当前任务ID
     * @param activityId 驳回节点ID
     * @param variables 流程存储参数
     * @throws Exception
     */
    public void backProcess(String taskId, String activityId,
            Map<String, Object> variables) throws Exception {
        if ("".equals(activityId) || activityId == null) {
            throw new Exception("驳回目标节点ID为空！");
        }

//      查询本节点发起的会签任务，并结束  
//        List<Task> tasks = this.anchor.getTaskService().createTaskQuery().parentTaskId(taskId)
//                .taskDescription("jointProcess").list();
//        for (Task task : tasks) {
//            commitProcess(task.getId(), null, null);
//        }
        // 查找所有并行任务节点，同时驳回  
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(
                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());
        for (Task task : taskList) {
            commitProcess(task.getId(), variables, activityId);
        }
    }

    /**
     * 根据流程实例ID和任务key值查询所有同级任务集合
     *
     * @param processInstanceId
     * @param key
     * @return
     */
    private List<Task> findTaskListByKey(String processInstanceId, String key) {
        return this.getAnchor().getTaskService().createTaskQuery().processInstanceId(
                processInstanceId).taskDefinitionKey(key).list();
    }

    /**
     * 根据任务ID获取对应的流程实例
     *
     * @param taskId 任务ID
     * @return
     * @throws Exception
     */
    private ProcessInstance findProcessInstanceByTaskId(String taskId)
            throws Exception {
        // 找到流程实例  
        ProcessInstance processInstance = this.getAnchor().getRuntimeService()
                .createProcessInstanceQuery().processInstanceId(
                        findTaskById(taskId).getProcessInstanceId())
                .singleResult();
        if (processInstance == null) {
            throw new Exception("流程实例未找到!");
        }
        return processInstance;
    }

    /**
     * @param taskId 当前任务ID
     * @param variables 流程变量
     * @param activityId 流程转向执行任务节点ID<br>
     * 此参数为空，默认为提交操作
     * @throws Exception
     */
    private void commitProcess(String taskId, Map<String, Object> variables,
            String activityId) throws Exception {
        if (variables == null) {
            variables = new HashMap<String, Object>();
        }
        // 跳转节点为空，默认提交操作  
        if ("".equals(activityId) || activityId == null) {
            this.getAnchor().getTaskService().complete(taskId, variables);
        } else {// 流程转向操作  
            turnTransition(taskId, activityId, variables);
        }
    }

    /**
     * 根据任务ID获得任务实例
     *
     * @param taskId 任务ID
     * @return
     * @throws Exception
     */
    private TaskEntity findTaskById(String taskId) throws Exception {
        TaskEntity task = (TaskEntity) this.getAnchor().getTaskService().createTaskQuery().taskId(
                taskId).singleResult();
        if (task == null) {
            throw new Exception("任务实例未找到!");
        }
        return task;
    }

    /**
     * 根据任务ID获取流程定义
     *
     * @param taskId 任务ID
     * @return
     * @throws Exception
     */
    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
            String taskId) throws Exception {
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.getAnchor().getRepositoryService())
                .getDeployedProcessDefinition(findTaskById(taskId)
                        .getProcessDefinitionId());

        if (processDefinition == null) {
            throw new Exception("流程定义未找到!");
        }

        return processDefinition;
    }

    private ActivityImpl findActivitiImpl(String taskId, String activityId)
            throws Exception {
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

        // 获取当前活动节点ID  
        if ("".equals(activityId) || activityId == null) {
            activityId = findTaskById(taskId).getTaskDefinitionKey();
        }

        // 根据流程定义，获取该流程实例的结束节点  
        if (activityId.toUpperCase().equals("END")) {
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {
                List<PvmTransition> pvmTransitionList = activityImpl
                        .getOutgoingTransitions();
                if (pvmTransitionList.isEmpty()) {
                    return activityImpl;
                }
            }
        }

        // 根据节点ID，获取对应的活动节点  
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
                .findActivity(activityId);

        return activityImpl;
    }

    /**
     * 还原指定活动节点流向
     *
     * @param activityImpl 活动节点
     * @param oriPvmTransitionList 原有节点流向集合
     */
    private void restoreTransition(ActivityImpl activityImpl,
            List<PvmTransition> oriPvmTransitionList) {
        // 清空现有流向  
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        pvmTransitionList.clear();
        // 还原以前流向  
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
    }

    /**
     * 清空指定活动节点流向
     *
     * @param activityImpl 活动节点
     * @return 节点流向集合
     */
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        // 存储当前节点所有流向临时变量  
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
        // 获取当前节点所有流向，存储到临时变量，然后清空  
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        return oriPvmTransitionList;
    }

    private void turnTransition(String taskId, String activityId,
            Map<String, Object> variables) throws Exception {
        // 当前节点  
        ActivityImpl currActivity = findActivitiImpl(taskId, null);
        // 清空当前流向  
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

        // 创建新流向  
        TransitionImpl newTransition = currActivity.createOutgoingTransition();
        // 目标节点  
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
        // 设置新流向的目标节点  
        newTransition.setDestination(pointActivity);

        // 执行转向任务  
        this.getAnchor().getTaskService().complete(taskId, variables);
        // 删除目标节点新流入  
        pointActivity.getIncomingTransitions().remove(newTransition);

        // 还原以前流向  
        restoreTransition(currActivity, oriPvmTransitionList);
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
    public void setAnchor(FlowAnchor anchor) {
        this.anchor = anchor;
    }
}
