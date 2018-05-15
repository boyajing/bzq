/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import bas.jeda.core.JedaService;
import bas.jeda.dao.JedaUser;
import bas.jeda.dao.SLog;
import bas.jeda.dao.SLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Jedacontroller基类
 * @author menghui
 */
public abstract class JedaController {
     /**
     * jeda dao 服务用户，角色，机构，菜单
     */
    @Autowired
    protected JedaService jService;
 
    @Autowired
    protected PageCutter pageCutter;
    protected JedaUser currentUser;

//    @InitBinder
//    public void initBinder(ServletRequestDataBinder binder) {
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
//    }

    public JedaUser getCurrentUser(){
        return this.currentUser;
    }
    /**
     * LoginRequired切面会将当前登录的用户信息赋值到这个属性
     * @param user 
     */
    @CurrentUserAnno
    public void setCurrentUser(JedaUser user) {
        this.currentUser = user;
    }
    public void addSLog(HttpServletRequest request,JedaUser user,String operType,String operMenu,String operResult){
        SLog sLog = new SLog();
        sLog.setLogid(UUID.randomUUID().toString());
        sLog.setIpadress(request.getRemoteAddr());     //ip地址
        if(user ==  null){
              user = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        }
        sLog.setUserid(user.getUserId());
        sLog.setUsername(user.getUserName()); 
        sLog.setOtype(operType);
        sLog.setOappmenu(operMenu);                     //操作对象
        sLog.setRenum(operResult);
        sLog.setOtime(new Date()); 
        SLogMapper slm = jService.getMapper(SLogMapper.class);   
        slm.insert(sLog);
    }     
}
