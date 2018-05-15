package com.nantian.utils;

import bas.jeda.controller.Constants;
import bas.jeda.dao.JedaUser;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CountLineListener implements HttpSessionListener{

    /**
     * 2017.1.11 段哲  此方法通过查询session获取user 删除application中的user(因为页面在线人数是通过application统计的)
     */
    private SimpleDateFormat sldf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    /***********
     * 创建session时调用
     */
    public void sessionCreated(HttpSessionEvent event) {
        //event.getSession().setMaxInactiveInterval(55);
    }

    /************
     * 销毁session时调用
     */
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();

        // 取得登录的用户名
        JedaUser username = (JedaUser) session.getAttribute(Constants.USERSESSIONID);
        if(username!=null) {
            // 从在线列表中删除用户名
            HashMap<String, String> auss = Constants.getApplicationObject(Constants.HASHMAPSTRSTRCLASS, application, Constants.ALREADYLOGINEDUSERS, true);
            auss.remove(username.getUserId());
            System.out.println(sldf.format(new Date())+","+username.getUserName() + "超时退出。");
        }

    }

}