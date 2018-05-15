/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import bas.jeda.core.JedaService;
import bas.jeda.dao.JedaUser;
import bas.jeda.exception.JEDAException;
import bas.jeda.exception.KickByOtherUserException;
import bas.jeda.exception.UserNotLoginException;
import com.nantian.utils.StringUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 登录处理切面程序
 *
 */
@Component
@Aspect
public class LoginAspect {

    @Autowired
    private JedaService jService;
//    private CacheManager cacheMgr = CacheManager.create();
//    private Cache resultCache = null;
    protected static String LOCALSESSIONID = "_const_umap_localuser_";
    protected static String LOCALPROFILE = "_const_umap_localprofile_";
    private Logger logger = Logger.getLogger(LoginAspect.class);

    /**
     * 返回设定当前用户的目标方法 添加LoginRequired的类必须包含一个setCurrentUser的方法用于设定当前用户
     *
     * @param target
     * @param object
     * @return
     */
    private Method getTargetMethod(Object target, Class<? extends Annotation> object) {
        Method[] ms = target.getClass().getMethods();
        for (int i = 0; i < ms.length; i++) {
            if (ms[i].getName().equals("setCurrentUser")) {
                return ms[i];
            }
        }
        return null;
    }

    /**
     * 设定当前登录的用户
     *
     * @param target
     * @param user
     */
    private void setCurrentUser(Object target, JedaUser user) {
        Method med = this.getTargetMethod(target, CurrentUserAnno.class);
        if (med != null) {
            try {
                med.invoke(target, user);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(LoginAspect.class.getName()).log(Level.SEVERE, null, ex);
                throw (new RuntimeException("####UMap Nantian#003####  setCurrentUser Error "));
            }
        }
    }

    private List<JedaService.MenuItem> getMenuByUser(final String userid) {
        return this.jService.getMenuByUser(userid, null);
    }

    /**
     * 登录检验切面处理程序
     *
     * @param jp
     * @return
     * @throws Exception
     */
    @Around("@annotation(bas.jeda.controller.LoginRequired)")
    public Object LoginCheck(ProceedingJoinPoint jp) throws Throwable {

        Object[] arg = jp.getArgs();
        //用于获取被注解的方法 ，从而获取该方案的参数中包含的注解
        Signature sig = jp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = jp.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        Annotation[][] parameterAnnotations = currentMethod.getParameterAnnotations();

        //被注解的方法的参数内的注解结束

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        if (u == null) {
            logger.info("USER not login,throw RuntimeException!");
            for (int i = 0; i < arg.length; i++) {
                if (arg[i] instanceof HttpServletResponse) {
                    HttpServletResponse response = (HttpServletResponse) arg[i];
                    String path = request.getRequestURI() + "?" + request.getQueryString();
                    response.sendRedirect(request.getContextPath() + "/login?r=" + StringUtils.encodeStr(path));
                    return null;
                }
            }
            throw (new UserNotLoginException());
            //
        }
        HashMap<String, String> aus = Constants.getApplicationObject(Constants.HASHMAPSTRSTRCLASS, null, Constants.ALREADYLOGINEDUSERS, true);
        Object o = aus.get(u.getUserId());
        if (o != null) {
            if (!((String) o).equals(request.getSession().getId())) {
                logger.info("USER kicked by other user,throw RuntimeException!");
                for (int i = 0; i < arg.length; i++) {
                    if (arg[i] instanceof HttpServletResponse) {
                        HttpServletResponse response = (HttpServletResponse) arg[i];
                        String path = request.getRequestURI() + "?" + request.getQueryString();
                        response.sendRedirect(request.getContextPath() + "/login?r=" + StringUtils.encodeStr(path));
                        return null;
                    }
                }
                throw (new KickByOtherUserException());
            }
        }
        for(int i=0;i<arg.length;i++){
            if (arg[i] instanceof JedaUser) {
                //在此处加标记，是否需要给当前参数赋值当前登录用户 默认需要，如果当前参数包含RequestBody 注解 则不需要给这个参数赋值当前登录用户
                boolean b = true;
                if(parameterAnnotations.length>i&&parameterAnnotations[i].length>0){
                    for(int j=0;j<parameterAnnotations[i].length;j++){
                        if(parameterAnnotations[i][j] instanceof RequestBody){
                           b =false;
                            break;
                        }
                    }
                }
                if(b)
                    arg[i] = u;
            }
        }
//下边三行未知作用， 占时注释掉不用
//        MethodSignature ms = (MethodSignature) jp.getSignature();
//        RequestMapping anns = ms.getMethod().getAnnotation(RequestMapping.class);
        //anns.value()[0]
        try {
            return jp.proceed(arg);
        } catch (Throwable ex) {
            //增加如果抛出 JEDAException 跳转到登录页面 不是则继续往外抛出异常
            if(ex instanceof JEDAException){

                logger.info("Login Aspect Error!", ex);
                logger.info(ex.getMessage());

                HttpServletResponse response = null;
                for (int i = 0; i < arg.length; i++) {
                    if (arg[i] instanceof HttpServletResponse) {
                        response = (HttpServletResponse) arg[i];
                    }
                }
                String path = request.getRequestURI() + "?" + request.getQueryString();
                response.sendRedirect(request.getContextPath() + "/login?r=" + StringUtils.encodeStr(path));

            }else{
                throw ex;
            }
            return null;
        }
    }
}
