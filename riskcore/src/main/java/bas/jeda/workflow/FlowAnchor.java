/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import bas.jeda.workflow.FlowAnchor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 流程引擎链接锚点</p>
 * <p>
 * 依赖于Spring配置的org.activiti.spring.ProcessEngineFactoryBean</p>
 * <p>
 * </p> @author menghui
 */
public class FlowAnchor {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FlowAnchor.class);
    @Autowired
    private org.activiti.spring.ProcessEngineFactoryBean engine;

    public org.activiti.spring.ProcessEngineFactoryBean getEngine() {
        return engine;
    }

    public ManagementService getManagementService() {
        try {
            return engine.getObject().getManagementService();
        } catch (Exception ex) {
            logger.error("==== 获取流程实例RepositoryService错误", ex);
            return null;
        }
    }

    public RepositoryService getRepositoryService() {
        try {
            return engine.getObject().getRepositoryService();
        } catch (Exception ex) {
            logger.error("==== 获取流程实例RepositoryService错误", ex);
            return null;
        }
    }

    public RuntimeService getRuntimeService() {
        try {
            return engine.getObject().getRuntimeService();
        } catch (Exception ex) {
            logger.error("==== 获取流程实例RuntimeService错误", ex);
            return null;
        }
    }

    public FormService getFormService() {
        try {
            return engine.getObject().getFormService();
        } catch (Exception ex) {
            logger.error("==== 获取流程实例FormService错误", ex);
            return null;
        }
    }

    public TaskService getTaskService() {
        try {
            return engine.getObject().getTaskService();
        } catch (Exception ex) {
            logger.error("==== 获取流程实例TaskService错误", ex);
            return null;
        }
    }

    public HistoryService getHistoryService() {
        try {
            return engine.getObject().getHistoryService();
        } catch (Exception ex) {
            logger.error("==== 获取流程实例HistoryService错误", ex);
            return null;
        }
    }

    public IdentityService getIdentityService() {
        try {
            return engine.getObject().getIdentityService();
        } catch (Exception ex) {
            logger.error("==== 获取流程实例IdentityService错误", ex);
            return null;
        }
    }

  

    public DynamicBpmnService getDynamicBpmnService() {
        try {
            return engine.getObject().getDynamicBpmnService();
        } catch (Exception ex) {
            logger.error("==== 获取流程实例DynamicBpmnService错误", ex);
            return null;
        }
    }

    public ProcessEngineConfiguration getProcessEngineConfiguration() {
        try {
            return engine.getObject().getProcessEngineConfiguration();
        } catch (Exception ex) {
            logger.error("==== 获取流程实例DynamicBpmnService错误", ex);
            return null;
        }
    }

}
