/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow.listener;

import bas.jeda.workflow.FlowHandler;
import bas.jeda.workflow.UserTask;
import java.util.Map;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author menghui
 */
public abstract class TaskBaseListener implements TaskListener {

    protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TaskBaseListener.class);
    @Autowired
    private FlowHandler flowHandler;

    /**
     *任务被创建时执行的方法
     */
    public abstract void create(DelegateTask dt,UserTask ut);

    /**
     *任务被分配时执行的方法
     */
    public abstract void assignment(DelegateTask dt,UserTask ut);

    /**
     *任务被完成时执行的方法
     */
    public abstract void complete(DelegateTask dt,UserTask ut);

    /**
     *任务被完成时执行的方法
     */
    public abstract void delete(DelegateTask dt,UserTask ut);

    /**
     *所有操作都会执行的方法
     */
    public abstract void all(DelegateTask dt,UserTask ut);

    @Override
    public void notify(DelegateTask dt) {
        logger.info("===== TaskBaseListener =====");
        UserTask userTask = this.flowHandler.getUserTask(dt.getId());
        if (TaskListener.EVENTNAME_ALL_EVENTS.equals(dt.getEventName())) {
            this.all(dt,userTask);
        }
        if (TaskListener.EVENTNAME_ASSIGNMENT.equals(dt.getEventName())) {
            this.assignment(dt,userTask);
        }
        if (TaskListener.EVENTNAME_COMPLETE.equals(dt.getEventName())) {
            this.complete(dt,userTask);
        }
        if (TaskListener.EVENTNAME_CREATE.equals(dt.getEventName())) {
            this.create(dt,userTask);
        }
        if (TaskListener.EVENTNAME_DELETE.equals(dt.getEventName())) {
            this.delete(dt,userTask);
        }

    }

    /**
     * @return the flowHandler
     */
    public FlowHandler getFlowHandler() {
        return flowHandler;
    }
}
