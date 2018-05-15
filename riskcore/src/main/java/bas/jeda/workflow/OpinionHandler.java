/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import bas.jeda.core.JedaService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用于处理审批意见的类
 * @author menghui
 */
public class OpinionHandler {
    /**
     * 访问数据库的接口
     */
    @Autowired
    protected JedaService jService;
    /**
     * 审批意见在流程过程中的变量名，该变量名在流程的doc中设定，默认为commit
     */
    private String varName="commit";

    /**
     * @return the varName
     */
    public String getVarName() {
        return varName;
    }

    /**
     * @param varName the varName to set
     */
    public void setVarName(String varName) {
        this.varName = varName;
    }
    /**
     * 保存审批意见
     * @param opinion 审批意见
     * @param ut 用户任务
     * @param currentUserID 操作的用户ID
     */
    public void saveOpinion(String opinion,UserTask ut,String currentUserID){
        
    }
            
}
