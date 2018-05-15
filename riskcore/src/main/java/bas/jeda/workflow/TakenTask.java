/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.TaskInfo;

/**
 *
 * @author menghui
 */
public class TakenTask extends UserTaskInfo {

    private String actionBtnName;
    private String actionBtnValue;
    private Date endTime;

    public TakenTask(HistoricTaskInstance task, IFlowHandler handler) {
        super(task, handler);
        this.endTime=task.getEndTime();
        List<TaskDocument.ButtonValue> btns = this.docConfig.getButtonValues();
        for (int i = 0; i < btns.size(); i++) {
            TaskDocument.ButtonValue btn = btns.get(i);
            if (this.getLocalVariables().get(btn.getVarName()) != null) {
                this.setActionBtnName(btn.getBtnName());
                this.setActionBtnValue(btn.getVarValue());
                break;
            }
        }
    }

    @Override
    protected void fillVariables() {
        this.localVariables.putAll(handler.getHitVarByTask(this.processid, this.taskID));
        this.variables.putAll(handler.getHitProcessVars(this.processid));
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
}
