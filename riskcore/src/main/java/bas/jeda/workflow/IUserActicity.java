/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

/**
 * 用户活动接口
 *
 * @author menghui
 */
public interface IUserActicity {

    String getId();

    /**
     * @return 说明信息
     */
    String getMemo();

    /**
     * @return 活动名称
     */
    String getName();

}
