/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.core;

import bas.jeda.core.JedaService;
import bas.jeda.dao.JedaUser;
import bas.jeda.dao.SLog;
import bas.jeda.dao.SLogMapper;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zhixiangfei
 */
 public class SLogWeb { 
    /**
     * 
     * @param request      请求
     * @param user         登录用户
     * @param operType     操作类型
     * @param operMenu     操作对象
     * @param operResult   操作结果
     */
    public static void addSLog(JedaService JedaService,HttpServletRequest request,
            JedaUser user,String operType,String operMenu,String operResult){
        SLog sLog = new SLog();
        sLog.setIpadress(request.getRemoteAddr());     //ip地址
        sLog.setUserid(user.getUserId());
        sLog.setUsername(user.getUserName());
        
        sLog.setOtype(operType);
        sLog.setOappmenu(operMenu);                     //操作对象
        sLog.setRenum(operResult);
        sLog.setOtime(new Date());
        //sLogService.addSLog(sLog);
        SLogMapper slm = JedaService.getMapper(SLogMapper.class);
        sLog.setLogid(UUID.randomUUID().toString());
        slm.insert(sLog);
    }
    
}
