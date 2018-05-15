/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

/**
 * 用户任务接口
 * @author menghui
 */
public interface IUserTask {
    /**
     * @return 表单主键，可以保存URL或者用于加载表单的配置信息
     */
    public String getFormKey();
    /**
     * 
     * @return 任务链接的用户
     */
    public String getAssignee();

    /**
     * @return t说明信息
     */
    public String getMemo();

    /**
     * @return 任务名称
     */
    public String getName();

}
