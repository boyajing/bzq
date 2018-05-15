/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import com.google.gson.Gson;
import com.nantian.utils.GuidCreator;
import java.util.HashMap;
import org.activiti.engine.task.TaskInfo;

/**
 * 虚拟的上会任务
 * 当会议秘书有上会的待办任务的时候自动创建虚拟的上会任务
 * 该任务不会与任何一个流程实例关联
 * @author menghui
 */
public class MeetingTask extends UserTaskInfo {
    private String createmeetingID(){
        GuidCreator G=new GuidCreator();
        
        return G.createNewGuid(GuidCreator.AfterMD5);
    }
    /**
     * 
     * @param task
     * @param handler
     */
    public MeetingTask(String userID) {
        super();
        this.formID="meetingform";
        this.businessURL="meetingformurl";
        this.taskName="会议准备";
        this.taskMemo="会议秘书组织会议";
        this.userID=userID;
        this.taskID=this.createmeetingID();
    }

    @Override
    protected void fillVariables() {
        
    }

}
