/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import bas.jeda.workflow.MyHisActivity;
import bas.jeda.workflow.TaskDocument;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * 用于封装流程引擎的UserTask 包括：表单的定义、按钮的定义、预定义参数等内容
 *
 * @author menghui
 */
@JsonIgnoreProperties(value = {"activitTask"})
public class UserTask extends UserTaskInfo {

    /**
     *
     * @param task
     * @param handler
     */
    public UserTask(org.activiti.engine.task.TaskInfo task, IFlowHandler handler) {
        super(task, handler);
    }

    @Override
    protected void fillVariables() {
        this.variables.putAll(this.handler.getLocalVar(this.taskID));
        this.localVariables.putAll(this.handler.getProcessVar(this.taskID));
        
    }
}
