/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.core;

import bas.jeda.dao.JedaUser;
import bas.jeda.controller.JedaController;
import com.google.gson.Gson;
import com.nantian.utils.GuidCreator;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *  日志记录工具
 *  静态类，可以通过errorLog、debugLog、jedaLog、trackLog获取相应的记录器。
 *  为了方便跟踪日志的分析，跟踪日志需要采用固定的格式记录：
 *     public static void tracklog(Object sender, HttpServletRequest request,String operation, String target, String assertMsg, Object obj) {
 *  调用该方法记录跟踪日志。
 *  #ff93cbb64df95473fcee88930a7567e9:user:admin	remo:127.0.0.1(127.0.0.1)	sid:44229AB4EC698B83EC73BBD128E5C62B	query	USER	SUCCEED	json
 */
final public class LoggerUtils {

    /**
     * 错误日志
     */
    public static Logger errorLog = Logger.getLogger("PT_ERROR");
    /**
     * 调试日志
     */
    public static Logger debugLog = Logger.getLogger("PT_DEBUG");
    /**
     * Jeda系统日志，用于记录框架信息和记录jeda的自动日志
     */
    public static Logger jedaLog = Logger.getLogger("PT_JEDA");
    /**
     * 跟踪日志，用于记录系统的操作审计信息
     */
    public static Logger trackLog = Logger.getLogger("PT_TRACK");
    ////////////////////////////////////////////////////////////////////////////
    //这里记录系统的跟踪日志的模块信息
    //模块信息用于描述操作的对象，根据情况进行扩展
    public static final String MODEL_USER = "USER";
    public static final String MODEL_ROLE = "ROLE";
    public static final String MODEL_ORG = "ORG";
    public static final String MODEL_AUTHPRIZATION = "AUTHPRIZATION";
    public static final String MODEL_MENU = "MENU";
    
    ////////////////////////////////////////////////////////////////////////////
    //记录跟踪日志的断言信息，即操作的结果
    //可以根据情况扩展
    public static final String ASSERT_SUCCEED = "SUCCEED";
    public static final String ASSERT_FAILURE = "FAILURE";
    
    ////////////////////////////////////////////////////////////////////////////
    //记录跟踪日志的标准化动词
    //可以根据情况扩展
    /**
     * 添加 or 新建
     */
    public static final String ActionADD = "add";
    /**
     * 删除
     */
    public static final String ActionDELETE = "delete";
    /**
     * 修改 or 更新
     */
    public static final String ActionEDIT = "edit";
    /**
     * 上传
     */
    public static final String ActionDOWNLOAD = "download";
    /**
     * 下载
     */
    public static final String ActionUPLOAD = "upload";
    /**
     * 改变状态
     */
    public static final String ActionSTATECHANGE = "statechange";

    public static final String ActionQUERY = "query";
    /**
     * 更新（创建新的）
     */
    public static final String ActionRENEW = "renew";
    

    private static Gson gson = new Gson();

    public static String getJson(Object obj) {
        return gson.toJson(obj);
    }

    
    
    public static String tracklog(String logid,Object sender, HttpServletRequest request,
            String operation, String target, String assertMsg, Object obj) {
        StringBuilder sb = new StringBuilder();
        if(logid==null||"".equals(logid)){
            logid=(new GuidCreator()).createNewGuid(GuidCreator.AfterMD5);
        }
        sb.append("#").append(logid).append(":");
        if (sender instanceof JedaController) {
            JedaUser user = ((JedaController) sender).getCurrentUser();
            if (user != null) {
                sb.append("user:").append(user.getUserId()).append("\t");
            }
        } else {
            if (sender != null) {
                sb.append("class:").append(sender.getClass().getCanonicalName());
            }
        }
        
        if(request !=null){
            sb.append("remo:").append(request.getRemoteAddr()).append("(").append(request.getRemoteHost()).append(")\t");
            if(request.getSession()!=null){
                sb.append("sid:").append(request.getSession().getId()).append("\t");
            }
        }
        
        sb.append(operation).append("\t");
        sb.append(target).append("\t");
        sb.append(assertMsg).append("\t");
        sb.append("json:").append(getJson(obj)).append("\t");

        trackLog.info(sb.toString());
        
        return logid;
    }
}
