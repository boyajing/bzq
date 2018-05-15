/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import bas.jeda.workflow.IUserActicity;
import bas.jeda.workflow.IUserTask;

/**
 * 定义流程中的用户任务
 *
 * @author menghui
 */
public class UserActivity implements IUserActicity,IUserTask {
    private String id;
    private String name;
    private String memo;
    private String assignee;
    private String formKey;

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the memo
     */
    @Override
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the assignee
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * @param assignee the assignee to set
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * @return the formKey
     */
    public String getFormKey() {
        return formKey;
    }

    /**
     * @param formKey the formKey to set
     */
    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
