/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import bas.jeda.core.LoggerUtils;

import bas.jeda.core.JedaService;
import bas.jeda.dao.*;
import com.nantian.utils.StringUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基础框架页面控制器
 *
 * @author menghui
 */
@Controller
public class PageController {

    /**
     * jeda dao 服务
     */
    @Autowired
    private JedaService jService;
    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;

    /**
     * 密码加密类
     */
    @Autowired
    private Md5PasswordEncoder passwordEncoder;
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PageController.class);

    private JedaUser currentUser;

    @CurrentUserAnno
    public void setCurrentUser(JedaUser user) {
        this.currentUser = user;
    }

    /**
     * 加载首页
     *
     * @param response
     * @param
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(JedaUser currentUser,HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        //List<MenuItem>
        HashMap<String,String> aus=Constants.getApplicationObject(Constants.HASHMAPSTRSTRCLASS, null, Constants.ALREADYLOGINEDUSERS, true);
        mv.addObject("num", aus.size());//在线人数
        mv.addObject("MENU", this.jService.gettop2LeveMenuByUser(currentUser.getUserId()));
        mv.addObject("userid",currentUser.getUserId());
        mv.addObject("CUSER", currentUser);
        session.setAttribute("CUSER1", currentUser);
        //用户机构
        JedaOrgMapper jo = this.jService.getMapper(JedaOrgMapper.class);
        //mv.addObject("returnview", "/");
        JedaOrg jObj = jo.selectByPrimaryKey(currentUser.getOrgId());
        String orgName = jObj.getOrgName();

        //系统日期
        String sysDate = this.getSysDate();
        mv.addObject("orgName", orgName);
        mv.addObject("systemDate", sysDate);
        mv.setViewName("index");
        return mv;
    }
     /**
      *
      * @return 系统日期
      */
     protected String getSysDate(){
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         return sdf.format(new Date());
     }
    /**
     * 系统状态 汉字 编码对照
     * @return
     */
    protected List<SCode> getSysStatusZH(){
        SCodeExample sCodeExample = new SCodeExample();
        sCodeExample.setDistinct(true);
        sCodeExample.createCriteria();

        SCodeExample.Criteria c = sCodeExample.createCriteria();
        c.andParentcodenoEqualTo("SystemStatus");
         sCodeExample.or(c);

        SCodeMapper scm = this.jService.getMapper(SCodeMapper.class);

        List<SCode> sc  = scm.selectByExample(sCodeExample);
        return sc;
    }
    /**
     * 根据Base64编码后的地址进行页面转向
     *
     * @param returnview
     * @param response
     */
    @RequestMapping(value = "/r", method = RequestMethod.GET)
    public void redirect(@RequestParam(value = "r", required = true, defaultValue = "") String returnview, HttpServletResponse response) {
        try {
            response.sendRedirect(StringUtils.decodeStr(returnview));
        } catch (IOException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 根据menuid获取路径
     *
     * @param menuid
     * @param response
     * @return
     */
    @RequestMapping(value = "/nav", method = RequestMethod.GET)
    public ModelAndView getBreadcrumb(@RequestParam(value = "mid", required = true, defaultValue = "") String menuid, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        JedaMenuMapper map = this.jService.getMapper(JedaMenuMapper.class);

        List<JedaMenu> menus = new ArrayList<JedaMenu>();
        this.getNav(map, menuid, menus);
        mv.addObject("result", menus);
        //mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        return mv;
    }

    private void getNav(JedaMenuMapper map, String mid, List<JedaMenu> nav) {
        JedaMenu menu = map.selectByPrimaryKey(mid);
        if (menu != null) {
            nav.add(menu);
            if (menu.getParentMenuId() != null && (!"".equals(menu.getParentMenuId()))) {
                this.getNav(map, menu.getParentMenuId(), nav);
            }
        }
    }
//ajax 获取系统当前的状态
    @RequestMapping(value = "/page/getStatus", method = RequestMethod.GET)
    public @ResponseBody  String ajaxCurStatus(HttpServletResponse response) {
        String status = this.getCurSysStatus();
        return status;
    }
    @RequestMapping(value = "/page/setStatus", method = RequestMethod.GET)
    public @ResponseBody  String setStatus(HttpServletResponse response,String status) {
        SSystemparamMapper ssmapper = this.jService.getMapper(SSystemparamMapper.class);
        SSystemparam       ss  = ssmapper.selectByPrimaryKey("systemstatus");
        ss.setParamvalue(status);
        ssmapper.updateByPrimaryKey(ss);
        String getstatus = this.getCurSysStatus();
        return getstatus;
    }

    protected String getCurSysStatus(){
        //系统日期
        SSystemparamMapper ssm = this.jService.getMapper(SSystemparamMapper.class);
        SSystemparam       ss  = ssm.selectByPrimaryKey("systemstatus");
        logger.info(ss.getParamvalue());
        return ss.getParamvalue();
    }
    //ajax 获取系统日期
    @RequestMapping(value = "/page/getSysDate", method = RequestMethod.GET)
    public @ResponseBody  String ajaxCurSysDate(HttpServletResponse response) {
        String status = this.getSysDate();
        return status;
    }
    /**
     * 登出逻辑
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletResponse response,HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        //退出时清理application
        HashMap<String,String> auss = Constants.getApplicationObject(Constants.HASHMAPSTRSTRCLASS,session.getServletContext(), Constants.ALREADYLOGINEDUSERS, true);
        JedaUser user = (JedaUser) session.getAttribute(Constants.USERSESSIONID);
        if(user!=null)
            auss.remove(user.getUserId());
        //add by lixinquan 2016-11-23 退出时把用户名Cookie清除
        Cookie cookie = new Cookie("LOGINUSER", "");
    	cookie.setPath("/");
		response.addCookie(cookie);
        session.removeAttribute(Constants.USERSESSIONID);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jeda/login");
        return mv;
    }

    /**
     * 登入逻辑
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginpost(
            @RequestParam(value = "u", required = true) String username,
            @RequestParam(value = "p", required = true) String password,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        JedaUserMapper umapr = this.jService.getMapper(JedaUserMapper.class);
        JedaUserExample example = new JedaUserExample();
        example.createCriteria().andLoginNameEqualTo(username);
        List<JedaUser> L = umapr.selectByExample(example);

        mv.addObject("userName", username);
        //用户不存在
        if (L.size() == 0) {
            mv.addObject("EMSG", Constants.EMSG_USERNAMEFAIL);
            mv.setViewName("jeda/login");
            return mv;
        } else {
            //用户禁用
            if(L.get(0).getUserEnabled().intValue()==0){
                 mv.addObject("EMSG", Constants.EMSG_USERSTOP);
                 mv.setViewName("jeda/login");
                 return mv;
            }


            if (this.passwordEncoder.isPasswordValid(L.get(0).getUserPassword(), password, "")) {
            	//add by lixinquan 2016-11-23 给尽职调查系统用来从cookie中取用户名 
            	Cookie cookie = new Cookie("LOGINUSER", username);
            	cookie.setPath("/");
				response.addCookie(cookie);
                /*用户未修改初始密码
                if(password.equals(Constants.DEFAULT_PASSWORD)){
                    mv.addObject("EMSG", Constants.EMSG_PWDSTOP);
                    mv.setViewName("jeda/login");
                    return mv;
                }*/
                 //用户无角色　2015.6.28 用户未分配角色不能登陆功能实现
//                JedaRoleUserMapper jum   = this.jService.getMapper(JedaRoleUserMapper.class);
//                JedaRoleUserExample jedaRoleUserExample= new JedaRoleUserExample();
//                JedaRoleUserExample.Criteria cri = jedaRoleUserExample.or();
//                cri.andUserIdEqualTo(L.get(0).getUserId());
//              List<JedaRoleUserKey> jList =   jum.selectByExample(jedaRoleUserExample);
//              //非管理员，判断是否有角色
//                 if(!L.get(0).getUserId().equals("admin") &&
//                         (jList==null || jList.size()==0)){
//                     mv.addObject("EMSG", Constants.EMSG_NOROLE);
//                     mv.setViewName("jeda/login");
//                     return mv;
//                } 2015.6.28 实现 暂时不放开此功能 放开后 除了管理员其他用户不能登陆
                HttpSession session = request.getSession(true);
                session.setAttribute(Constants.USERSESSIONID, L.get(0));
                //Login Succeed
                
                HashMap<String,String> aus=Constants.getApplicationObject(Constants.HASHMAPSTRSTRCLASS, null, Constants.ALREADYLOGINEDUSERS, true);
                aus.put(L.get(0).getUserId(),session.getId());
                
                String r = request.getParameter("r");
                if (!StringUtils.isEmpty(r)) {
                    try {
                        response.sendRedirect(StringUtils.decodeStr(r));
                        return null;
                    } catch (IOException ex) {
                        mv.addObject("EMSG", Constants.EMSG_LOGINFAIL);
                        mv.setViewName("jeda/login");
                        return mv;
                    }
                } else {
                    try {
                        response.sendRedirect(request.getContextPath() + "/");
                    } catch (IOException ex) {
                        Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return null;
                }
            } else {
                //mv.addObject("EMSG", Constants.EMSG_LOGINFAIL);
                mv.addObject("EMSG", Constants.EMSG_PASSWORDFAIL);
                mv.setViewName("jeda/login");
                return mv;
            }
        }

    }

    /**
     * 登入界面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jeda/login");
        return mv;
    }

    /**
     * 返回用户的菜单
     *
     * @param parent
     * @param
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView menu(JedaUser currentUser,@RequestParam(value = "parent") String parent, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        if ("".equals(parent)) {
            parent = null;
        }
//        mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        mv.addObject("result", this.jService.getMenuByUser(currentUser.getUserId(), parent));
        return mv;
    }

    /**
     *   首页-查看在线人数
     */
    @LoginRequired
    @RequestMapping(value = "/pageController/viewUser", method = RequestMethod.GET)
    public ModelAndView viewUser(HttpServletResponse response,
                                 @RequestParam(value = "pi", required = true, defaultValue = "1") int pageIndex)throws Exception{
        ModelAndView mv = new ModelAndView();
        HashMap<String,String> aus=Constants.getApplicationObject(Constants.HASHMAPSTRSTRCLASS, null, Constants.ALREADYLOGINEDUSERS, true);
        List<JedaUserExpland> list = new ArrayList();
        int i=0;
        for(String key : aus.keySet())
        {
            JedaUserMapperExpland ju = jService.getMapper(JedaUserMapperExpland.class);
            JedaUserExpland jedaUserExpland = ju.selectUserByUserId(key);
            list.add(i,jedaUserExpland);
            i++;
        }
        //分页
        int startIndex = pageSize * (pageIndex - 1) + 1;
        int endIndex = pageSize * pageIndex;
        List<JedaUserExpland> result2 = new ArrayList<JedaUserExpland>();
        for (int j = startIndex; j <=endIndex; j++) {
            if (j > list.size()) {
                break;
            }
            result2.add(list.get(j - 1));
        }

        mv.addObject("pagecount", list.size() / pageSize + (list.size() % pageSize == 0 ? 0 : 1));
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageIndex == 0 ? 1 : pageIndex);
        mv.addObject("list", result2);
        mv.setViewName("jeda/user/onlineusers");
        return mv;
    }

    /**
     * 实时刷新在线人数 10分钟刷新一次
     * @param response
     * @param
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/pageController/updateUser", method = RequestMethod.POST)
    public String updateUser(HttpServletResponse response)throws Exception{
        try {
            HashMap<String,String> aus=Constants.getApplicationObject(Constants.HASHMAPSTRSTRCLASS, null, Constants.ALREADYLOGINEDUSERS, true);
            response.setContentType("text/html;utf-8");
            response.getWriter().print(aus.size()+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
