/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import org.activiti.engine.history.HistoricTaskInstance;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author menghui
 */
public class TakenTaskForList extends UserTaskForList {

    private String actionBtnName;
    private String actionBtnValue;
    private Date endTime;

    public TakenTaskForList(HistoricTaskInstance task, IFlowHandler handler) {
        super(task, handler);
        this.endTime=task.getEndTime();
        List<TaskDocument.ButtonValue> btns = this.docConfig.getButtonValues();
        for (int i = 0; i < btns.size(); i++) {
            TaskDocument.ButtonValue btn = btns.get(i);
            if (this.getLocalVariables().get(btn.getVarName()) != null) {
                if(this.getLocalVariables().get("approvebtn")!=null&&!"".equals(this.getLocalVariables().get("approvebtn"))){
                    this.setActionBtnValue(this.getLocalVariables().get("approvebtn").toString());
                    this.setActionBtnName("同意");
                    break;
                }
                if(this.getLocalVariables().get("rejectbtn")!=null&&!"".equals(this.getLocalVariables().get("rejectbtn"))){
                    this.setActionBtnValue(this.getLocalVariables().get("rejectbtn").toString());
                    this.setActionBtnName("驳回或者回退");
                    break;
                }
                if(this.getLocalVariables().get("votedBtn")!=null&&!"".equals(this.getLocalVariables().get("votedBtn"))){
                    this.setActionBtnValue(this.getLocalVariables().get("votedBtn").toString());
                    this.setActionBtnName("否决");
                    break;
                }
            }
            else{
                this.setActionBtnName("发送");
                this.setActionBtnValue("1");
            }
        }
    }
    @Override
    protected Map<String, Object> getVariables(String processId,String taskID){
//        this.localVariables.putAll(handler.getHitVarByTask(this.processid, this.taskID));
        return handler.getHitProcessVars(processId);
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
