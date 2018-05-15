/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow.listener;

import bas.jeda.workflow.FlowHandler;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 监听器基类
 */
public abstract class ExecutionBaseListener implements ExecutionListener {

    protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ExecutionBaseListener.class);

    @Autowired
    private FlowHandler flowHandler;


    /**
     *启动时触发事件
     */
    public abstract void StartNotify(DelegateExecution de) throws Exception;

    /**
     *结束时触发事件
     */
    public abstract void EndNotify(DelegateExecution de) throws Exception;

    public abstract void TakeNotify(DelegateExecution de) throws Exception;

    @Override
    public void notify(DelegateExecution de) throws Exception {

        logger.info("===== BaseListener =====");
//        this.activityID = de.getCurrentActivityId();
//        this.activityName = de.getCurrentActivityName();
//        this.definitionId = de.getProcessDefinitionId();
//        this.processID = de.getProcessInstanceId();
//        this.processVar = de.getVariables();
//        this.processVarLocal = de.getVariablesLocal();
//        this.eventName = de.getEventName();
//        ActivityImpl act = this.getFlowHandler().findActivityByID(getDefinitionId(), getActivityID());
//        this.activityType = (String) act.getProperty("type");
        if(de.getEventName().equals(ExecutionListener.EVENTNAME_START)){
            this.StartNotify(de);
        }
        if(de.getEventName().equals(ExecutionListener.EVENTNAME_END)){
            this.EndNotify(de);
        }
        if(de.getEventName().equals(ExecutionListener.EVENTNAME_TAKE)){
            this.TakeNotify(de);
        }
        logger.info("===== BaseListener End =====");

    }

    /**
     * @return the flowHandler
     */
    public FlowHandler getFlowHandler() {
        return flowHandler;
    }



}
