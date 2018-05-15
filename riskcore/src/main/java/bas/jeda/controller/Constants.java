/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import java.util.HashMap;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 系统常量
 * @author menghui
 */
public class Constants {

    private Constants() {
    }
    public static final String EMSG_LOGINFAIL = "LOGINERROR";
     /*20150506用户名不存在*/
    public static final String EMSG_USERNAMEFAIL = "用户名不存在!";
    /*20150506密码错误*/
    public static final String EMSG_PASSWORDFAIL = "密码不正确!";
    /*20150507用户禁用*/
    public static final String EMSG_USERSTOP = "此用户不可用，请联系管理员";
    /*20150507初始密码*/
    public static final String EMSG_PWDSTOP = "请修改登录密码";
    public static final String EMSG_NOROLE = "未分配角色，请联系管理员";
    /**
     * 登录用户的SessionID
     */
    public static final String USERSESSIONID = "_const_cas_assertion_userinfo_";
    public static final String ALREADYLOGINEDUSERS="LOGINEDUSER";
    public static final Class<? extends HashMap> HASHMAPSTRSTRCLASS=(new HashMap<String,String>()).getClass();
    public static <T extends Object> T getApplicationObject(Class<T> type, ServletContext application, String key, boolean createNew) {
        if(application==null){
//            application=ContextLoader.getCurrentWebApplicationContext().getServletContext();
            WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
            application = context.getServletContext();
        }
        Object obj = application.getAttribute(key);
        if (obj == null) {
            if (createNew) {
                try {
                    obj = type.newInstance();
                    application.setAttribute(key, obj);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return (T) obj;
    }
    /**
     * 从session中返回对象
     * @param <T>   对象类型
     * @param type  对象类型
     * @param session   session
     * @param key   session的key
     * @param createNew 是否创建新的对象
     * @return 
     */
    public static <T extends Object> T getSessionObject(Class<T> type, HttpSession session, String key, boolean createNew) {
        Object obj = session.getAttribute(key);
        if (obj == null) {
            if (createNew) {
                try {
                    obj = type.newInstance();
                    session.setAttribute(key, obj);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return (T) obj;
    }

    public static final String FRAMEWORK_REQUEST_PATH = "jeda";

    public static final String APP_PATH = "gdsl";
    public static final String ROOT_ORG="6400000000";
    public static final String ROOT_MENU="";
    /**
     * excel文件列定义KEY
     */
    public static final String DEFAULT_EXCEL_COLUMNS_MODEL_KEY = "EXCEL_COLUMN_MODEL";

    /**
     * 默认Excel视图
     */
    public static final String DEFAULT_EXCEL_VIEW = "excelView";

    /**
     * 默认写入Excel文件最大记录数量
     */
    public static final int DEFAULT_EXCEL_ROW_LIMIT = 65000;

    /**
     * 默认数据KEY
     */
    public static final String DEFAULT_RECORD_MODEL_KEY = "result";

    /**
     * 默认数量KEY
     */
    public static final String DEFAULT_COUNT_MODEL_KEY = "count";

    // 默认分页开始记录行数
    public static final String DEFAULT_PAGE_START = "0";

    // 默认单页记录数量
    public static final String DEFAULT_PAGE_SIZE = "20";

    // 默认排序字段
    public static final String DEFAULT_SORT_FIELD = "order";

    // 默认排序方向
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    public static final String DEFAULT_PASSWORD = "123";

    /**
     * 树根节点id
     */
    public static final String TREE_ROOT_NODE = "TREE_ROOT_NODE";
    /**
     * 树根节点id
     */
    public static final String TREE_ROOT_NODE2 = "TREE_ROOT_NODE2";

    /**
     * 模块类型:系统安全
     */
    public static final String MODULE_CATEGORY_SECURITY = "security";
    /**
     * 模块类型:系统配置
     */
    public static final String MODULE_CATEGORY_CONFIG = "config";

    /**
     * 模块类型:测试
     */
    public static final String MODULE_CATEGORY_TEST = "test";
}
