/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import bas.jeda.core.LoggerUtils;
import bas.jeda.dao.JedaUser;
import bas.jeda.exception.AutoLogAspectLog;
import bas.jeda.exception.JEDAException;
import com.nantian.utils.GuidCreator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
/**
 * 自动日志记录
 * 使用切面自动记录Controller的操作，在Controller的方法上添加
 * AutoMvcLog注解
 * 激活自动日志记录，自动日志依赖AutoLogAspect类中的切面
 * public Object AutoLog(ProceedingJoinPoint jp) throws Exception 
 * 实现。
 * 
 * 记录的内容包括：
 *  当前用户信息，前提是Coltroller必须派生自JedaController类
 *  当前Request的地址、参数、方法
 *  当前方法传入的参数，只记录简单参数（数字、字符、字符串）
 *  当前方法返回的运行结果
 *      识别Controller返回值为String、Model、Map、ModelMap、View、ModelAndView这几种。
 *  日志记录格式为:
 [15/03/16 17:42:04:341] #2192518d0a241802f4f405f9790dcaba:user:admin	bas.bondanalysesys.jeda.UserController	orgTree	
 [15/03/16 17:42:04:342] #472fc3c4ea04941d82f43560c9d245b8:user:admin	bas.bondanalysesys.jeda.UserController	orgTree	
 [15/03/16 17:42:04:353] #472fc3c4ea04941d82f43560c9d245b8:Return view:jeda/user/index	Return Map:{}
 [15/03/16 17:42:04:354] #2192518d0a241802f4f405f9790dcaba:Return view:jeda/user/index	Return Map:{}
 * 
 * 上面#2192518d0a241802f4f405f9790dcaba:中#：之间的编码为请求ID用于标识一个请求，先出现的是Request后出现的是response
 * user:为操作的用户ID
 * bas.bondanalysesys.jeda.UserController	orgTree分别是controller的名字和方法
 * Return view:jeda/user/index	Return Map:{}   分别是返回的视图名称和返回的数据，如果识别到数据格式则自动转换为json字符串。
 * 
 * 自动日志记录在jedalogger日志文件中。
 * 
 * mvc.xml中声明
 * <bean class="bas.bondanalysesys.jeda.AutoLogAspect">
        <property name="enabled" value="true"/>
   </bean>
 *
 * 其中enabled为true时开启自动日志，其他值关闭自动日志。
 */
@Component
@Aspect
public class AutoLogAspect {

    private String enabled = "true";

    /**
     * 填充参数
     *
     * @param sb
     * @param r
     */
    private void fillRequest(StringBuilder sb, HttpServletRequest r) {
        if (r.getSession() != null) {
            sb.append("req:").append(r.getSession().getId()).append("\t");
        }
        sb.append(r.getRequestURI()).append("\t");
        sb.append(r.getMethod()).append("\t");
        sb.append(r.getRemoteAddr()).append("(").append(r.getRemoteHost()).append(")\t");
        Enumeration<String> names = r.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = r.getParameter(name);
            sb.append("R_").append(name).append("=").append(value).append("\t");
        }
    }

    private void fillModel(StringBuilder sb, Map m) {
        Iterator it = m.keySet().iterator();
        sb.append("Return Map:");
//        while (it.hasNext()) {
//            Object k = it.next();
//            if (isSimpleType(m.get(k))) {
//                sb.append(String.valueOf(k)).append("=").append(String.valueOf(m.get(k))).append("\t");
//            }
//        }
        sb.append(LoggerUtils.getJson(m));
    }

    private boolean isSimpleType(Object obj) {
        return obj instanceof String
                || obj instanceof Number
                || obj instanceof Boolean
                || obj instanceof Character;
    }

    @Around("@annotation(bas.jeda.controller.AutoMvcLog)")
    public Object AutoLog(ProceedingJoinPoint jp) throws Exception {
        Object[] arg = jp.getArgs();
        if (!"true".equalsIgnoreCase(this.enabled)) {
            try {
                Object obj = jp.proceed(arg);
                return obj;
            } catch (Throwable ex) {
                LoggerUtils.errorLog.error("####UMap Nantian#004#### AutoLogAspect error", ex);
                throw (new AutoLogAspectLog());
            }
        }
        MethodSignature ms = (MethodSignature) jp.getSignature();

        AutoMvcLog loganno = (AutoMvcLog) ms.getMethod().getAnnotation(AutoMvcLog.class);
        String id = (new GuidCreator()).createNewGuid(GuidCreator.AfterMD5);
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(id).append(":");

        if (jp.getTarget() instanceof JedaController) {
            JedaUser user = ((JedaController) jp.getTarget()).getCurrentUser();
            if (user != null) {
                sb.append("user:").append(user.getUserId()).append("\t");
            }
        }
        
        sb.append(ms.getMethod().getDeclaringClass().getName()).append("\t");   //类名
        sb.append(ms.getMethod().getName()).append("\t");   //方法名

        for (int i = 0; i < arg.length; i++) {
            //添加HTTPRequest的内容
            if (arg[i] instanceof HttpServletRequest) {
                fillRequest(sb, (HttpServletRequest) arg[i]);
            }

            if (isSimpleType(arg[i])) {
                //添加controller中的参数的内容
                sb.append("P_").append(i).append("=").append(String.valueOf(arg[i])).append("\t");
            }
        }
        LoggerUtils.jedaLog.info(sb.toString());
        sb = null;
        sb = new StringBuilder();
        sb.append("#").append(id).append(":");

        try {
            Object obj = jp.proceed(arg);
            if (obj != null) {
                //添加返回值
                if (obj instanceof ModelAndView) {
                    ModelAndView v = (ModelAndView) obj;
                    sb.append("Return view:");
                    if (v.getView() != null) {
                        sb.append(v.getView().getClass().getCanonicalName()).append("\t");
                    }

                    sb.append(v.getViewName()).append("\t");
                    this.fillModel(sb, v.getModel());
                }
                if (obj instanceof Model) {
                    Map m = ((Model) obj).asMap();
                    this.fillModel(sb, m);
                }

                if (obj instanceof Map) {
                    Map m = (Map) obj;
                    this.fillModel(sb, m);
                }

                if (obj instanceof View) {

                    sb.append("Return view:").append(((View) obj).getClass().getCanonicalName());
                }

                if (obj instanceof String) {
                    sb.append("Return view:").append(String.valueOf(obj));
                }
                LoggerUtils.jedaLog.info(sb.toString());
            }
            return obj;
        } catch (Throwable ex) {

            LoggerUtils.errorLog.error("####UMap Nantian#004#### AutoLogAspect error", ex);
            throw (new AutoLogAspectLog());

        }
    }

    /**
     * @return the enabled
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
