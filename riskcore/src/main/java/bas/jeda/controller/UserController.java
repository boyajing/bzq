/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import bas.jeda.core.LoggerUtils;
import bas.jeda.core.SLogWeb;
import bas.jeda.dao.JedaOrg;
import bas.jeda.dao.JedaOrgExample;
import bas.jeda.dao.JedaOrgMapper;
import bas.jeda.dao.JedaRoleUserExample;
import bas.jeda.dao.JedaRoleUserMapper;
import bas.jeda.dao.JedaUser;
import bas.jeda.dao.JedaUserExample;
import bas.jeda.dao.JedaUserMapper;
import com.nantian.utils.StringUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户管理页面控制器 对应JSP页面： jeda/user/*.jsp
 *
 * @author menghui
 */
@Controller
public class UserController extends JedaController {

    @Autowired
    private Md5PasswordEncoder passwordEncoder;

    @Value("#{configProperties['PAGESIZE']}")
    private int PAGESIZE;

    private String orgid;

    /**
     * 根据Example查询用户信息并且分页传递到视图
     *
     * @param pageIndex
     * @param pageSize
     * @param example
     * @return
     */
    @LoginRequired
    private ModelAndView queryUser(JedaUser currentUser, int pageIndex, int pageSize, String order, String org, JedaUserExample example, String userID, String userName, String userTel, String userMobile) {
        ModelAndView mv = new ModelAndView();
        JedaUserMapper ju = jService.getMapper(JedaUserMapper.class);
        if (!StringUtils.isEmpty(order)) {
            example.setOrderByClause(order);
            mv.addObject("ordercase", order);
        } else {
            mv.addObject("ordercase", "");
        }
        JedaOrgMapper jom = jService.getMapper(JedaOrgMapper.class);
        JedaOrg o = new JedaOrg();
        if (!StringUtils.isEmpty(org)) {
            o = jom.selectByPrimaryKey(org);
        } else {
            o = jom.selectByPrimaryKey(currentUser.getOrgId());
        }
        mv.addObject("orgbean", o);
        if (o != null) {
            JedaOrgExample oex = new JedaOrgExample();
            if (o.getOrgId().length() == 4) {
                oex.createCriteria().andOrgPathLike(o.getOrgPath() + "%");
            } else {
//                if (o.getOrgId().substring(4, 6).equals("00")) {
//                    oex.createCriteria().andOrgPathLike(o.getOrgPath() + "%");
//                } else {
//                    oex.createCriteria().andOrgIdEqualTo(o.getOrgId());
                        oex.createCriteria().andOrgIdLike(o.getOrgId() +"%");
//                }
            }
            List<JedaOrg> Lo = jom.selectByExample(oex);
            //下级机构
            List<String> ids = new ArrayList<String>(Lo.size());
            for (int i = 0; i < Lo.size(); i++) {
                ids.add(Lo.get(i).getOrgId());
            }
            if (!ids.isEmpty()) {
                example.createCriteria().andOrgIdIn(ids);
            }

        }

        List<JedaUser> us = ju.selectByExample(example);
        int pc = 0;
        if (us.size() != 0 && pageSize != 0) {
            /*没有返回结果*/
            pageCutter.setPageIndex(pageIndex);
            pageCutter.setPageSize(pageSize);
            pageCutter.setUs(us);
            pc = pageCutter.pageCount();
            us = this.pageCutter.cutList();
        }
        mv.addObject("org", org);
        mv.addObject("pagecount", pc);
        mv.addObject("pagesize", pageSize);
        mv.addObject("userID", userID);
        mv.addObject("userName", userName);
        mv.addObject("userTel", userTel);
        mv.addObject("userMobile", userMobile);
        mv.addObject("pageindex", pageCutter.getPageIndex());
        mv.addObject("result", us);

        mv.addObject("returnview", StringUtils.encodeStr("jeda/user/query?pi=" + String.valueOf(pageIndex) + "&ps=" + String.valueOf(pageSize)));

        mv.setViewName("jeda/user/query");
        return mv;
    }

    /**
     * 删除
     *
     * @param request
     * @param userID
     * @param pageIndex
     * @param order
     * @param org
     * @return
     */
    @LoginRequired
    @AutoMvcLog
    @RequestMapping(value = "/jeda/user/delete", method = RequestMethod.GET)
    public ModelAndView delete(JedaUser currentUser, HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "userID", required = true, defaultValue = "") String[] userID,
            @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
            @RequestParam(value = "o", required = true, defaultValue = "") String order,
            @RequestParam(value = "org", required = true, defaultValue = "") String org) {
        JedaUserExample example;
        JedaRoleUserExample exam;
        JedaUserMapper ju = jService.getMapper(JedaUserMapper.class);
        JedaRoleUserMapper jru = jService.getMapper(JedaRoleUserMapper.class);
        example = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "deleteuserkey", true);
        exam = Constants.getSessionObject(JedaRoleUserExample.class, request.getSession(true), "deleterloeuserkey", true);
        example.clear();
        exam.clear();
        JedaUserExample.Criteria cri = example.createCriteria();

        if (!StringUtils.isEmpty(org)) {
//            cri.andOrgIdEqualTo(org);
            cri.andOrgIdLike(org +"%");
        }
        JedaRoleUserExample.Criteria cri1 = exam.createCriteria();
        for (int i = 0; i < userID.length; i++) {
            ju.deleteByPrimaryKey(userID[i]);
        }
        List idlist = new ArrayList();
        for (int i = 0; i < userID.length; i++) {
            idlist.add(userID[i]);
        }
        cri1.andUserIdIn(idlist);
        jru.deleteByExample(exam);
        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionDELETE, LoggerUtils.MODEL_USER, LoggerUtils.ASSERT_SUCCEED, userID);
        return this.queryUser(currentUser, pageIndex, PAGESIZE, order, org, example, "", "", "", "");
    }

    /**
     * 启用
     *
     * @param request
     * @param e
     * @param userId
     * @param pageIndex
     * @param order
     * @param org
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/user/able", method = RequestMethod.GET)
    public ModelAndView able(HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,
            @RequestParam(value = "e", required = true, defaultValue = "") BigDecimal e,
            @RequestParam(value = "userId", required = true, defaultValue = "") String userId,
            @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
            @RequestParam(value = "o", required = true, defaultValue = "") String order,
            @RequestParam(value = "org", required = true, defaultValue = "") String org) {
        JedaUserExample example;
        JedaUserMapper ju = jService.getMapper(JedaUserMapper.class);
        example = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "deleteuserkey", true);
        example.clear();
        JedaUser user = ju.selectByPrimaryKey(userId);
        user.setUserEnabled(e);
        user.setUserModified(new Date());
        user.setUserModifier(currentUser.getUserId());
        ju.updateByPrimaryKey(user);
        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionSTATECHANGE, LoggerUtils.MODEL_USER, LoggerUtils.ASSERT_SUCCEED, user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageIndex", pageIndex);
        mv.addObject("order", order);
        mv.addObject("org", org);
        mv.addObject("isdelete", "1");
        mv.setViewName("redirect:/jeda/user/query");
        return mv;
    }

    /**
     * 返回用户详细信息
     *
     * @param userID
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/jeda/user/getuser", method = RequestMethod.GET)
    public ModelAndView getUser(HttpServletResponse response, @RequestParam(value = "userID", required = true, defaultValue = "") String userID) {
        ModelAndView mv = new ModelAndView();
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        mv.addObject("result", ju.selectByPrimaryKey(userID));
//        mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        return mv;
    }

    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/jeda/user/resetPwd", method = RequestMethod.GET)
    public ModelAndView resetPwd(HttpServletResponse response, @RequestParam(value = "userID", required = true, defaultValue = "") String userID) {
        ModelAndView mv = new ModelAndView();
        if ("".equals(userID)) {
            return mv;
        }
        String[] users = userID.substring(0, userID.length() - 1).split(";");
        ArrayList<String> us = new ArrayList<String>();
        for (int i = 0; i < users.length; i++) {
            us.add(users[i]);
        }
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        String pwd = passwordEncoder.encodePassword(Constants.DEFAULT_PASSWORD, "");
        JedaUser record = new JedaUser();
        record.setUserPassword(pwd);
        JedaUserExample example = new JedaUserExample();
        example.createCriteria().andUserIdIn(us);
        ju.updateByExampleSelective(record, example);
        mv.addObject("result", "succeed");
//        mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        return mv;
    }

    /**
     * 编译页面控制
     *
     * @param request
     * @param returnview
     * @param userID
     * @return
     */
    @LoginRequired

    @RequestMapping(value = "/jeda/user/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "r", required = true, defaultValue = "") String returnview,
            @RequestParam(value = "userID", required = true, defaultValue = "") String userID) {
        ModelAndView mv = new ModelAndView();
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        JedaOrgMapper jo = this.jService.getMapper(JedaOrgMapper.class);
        //mv.addObject("returnview", "/");
        JedaUser user = ju.selectByPrimaryKey(userID);
        JedaOrg joe = jo.selectByPrimaryKey(user.getOrgId());
        mv.addObject("orgName", joe.getOrgName());
        mv.addObject("returnview", (returnview));
        mv.addObject("user", user);
        mv.setViewName("jeda/user/edit");
        return mv;
    }

    /**
     * 用户编辑写入数据库
     *
     * @param request
     * @param response
     * @param user
     */
    @LoginRequired
    @AutoMvcLog
    @RequestMapping(value = "/jeda/user/edit", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody
    void edit(JedaUser currentUser, HttpServletRequest request, HttpServletResponse response, @RequestBody JedaUser user) {
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        user.setUserModified(new Date());
        user.setUserModifier(currentUser.getUserId());
        ju.updateByPrimaryKeySelective(user);
        HttpSession session = request.getSession();
        session.setAttribute("result", user);
        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionEDIT, LoggerUtils.MODEL_USER, LoggerUtils.ASSERT_SUCCEED, user);
        this.addSLog(request, user, "修改", "用户管理", "成功");

    }

    /**
     * 查询页面控制
     *
     * @param request
     * @param pageIndex
     * @param order
     * @param org
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/user/query", method = RequestMethod.GET)
    public ModelAndView query(JedaUser currentUser, HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
            @RequestParam(value = "o", required = true, defaultValue = "user_created desc") String order,
            @RequestParam(value = "org", required = true, defaultValue = "") String org,
            @RequestParam(value = "isdelete", required = true, defaultValue = "0") String isdelete
    ) {

        JedaUserExample example;
        if ("0".equals(isdelete)) {
            example = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "queryuserkey", true);
        } else {
            example = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "deleteuserkey", true);

        }
        if (pageIndex == -1) {
            example.clear();
        }

        return this.queryUser(currentUser, pageIndex, PAGESIZE, order, org, example, "", "", "", "");

    }

    /**
     * 选定用户页面（独立页面共其他模块需要选择用户时使用）控制
     *
     * @param request
     * @param response
     * @param pageIndex
     * @param order
     * @param org
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/user/selectuser", method = RequestMethod.GET)
    public ModelAndView selectuser(JedaUser currentUser, HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "po", required = true, defaultValue = "") String po,
            @RequestParam(value = "role", required = true, defaultValue = "") String role,
            @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
            @RequestParam(value = "o", required = true, defaultValue = "") String order,
            @RequestParam(value = "org", required = true, defaultValue = "") String org
    ) {

        JedaUserExample example;
        example = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "queryuserkey", true);
        if (pageIndex == -1) {
            example.clear();

        }

        ModelAndView mv = this.queryUser(currentUser, pageIndex, PAGESIZE, order, org, example, "", "", "", "");
        mv.addObject("posturl", po);
        mv.addObject("role", role);
        mv.setViewName("jeda/user/selectuser");
        if (role != null && !role.isEmpty()) {
            mv.addObject("usersList", this.jService.getRoleUser(role));
            /*mv.addObject("result", this.jService.getRoleUser(role));*/
        }
        return mv;

    }

    /**
     * 选定用户的查询页面
     *
     * @param request
     * @param pageIndex
     * @param userName
     * @param userID
     * @param loginName
     * @param order
     * @param org
     * @return
     */
    @AutoMvcLog
    @LoginRequired
    @RequestMapping(value = "/jeda/user/selectuser", method = RequestMethod.POST)
    public ModelAndView selectuser(JedaUser currentUser, HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "pi", required = true, defaultValue = "1") int pageIndex,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "userID", required = false) String userID,
            @RequestParam(value = "loginName", required = false) String loginName,
            @RequestParam(value = "o", required = true, defaultValue = "") String order,
            @RequestParam(value = "org", required = true, defaultValue = "") String org) {
        JedaUserExample example;
        example = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "queryuserkey", true);
        example.clear();

        JedaUserExample.Criteria cri = example.createCriteria();

        if (!StringUtils.isEmpty(userName)) {
            cri.andUserNameLike("%" + userName + "%");
            example.or().andUserIdLike("%" + userName + "%");
            example.or().andLoginNameLike("%" + userName + "%");
        }
        ModelAndView mv = this.queryUser(currentUser, pageIndex, PAGESIZE, order, org, example, "", "", "", "");
        mv.setViewName("jeda/user/selectuser");
        return mv;
    }

    /**
     * 执行查询操作
     *
     * @param request
     * @param pageIndex
     * @param userName
     * @param userID
     * @param
     * @param order
     * @param org
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/user/query", method = RequestMethod.POST)
    public ModelAndView index(JedaUser currentUser, HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "pi", required = true, defaultValue = "1") int pageIndex,
            @RequestParam(value = "userID", required = false) String userID,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "loginName", required = false) String loginName,
            @RequestParam(value = "userTel", required = false) String userTel,
            @RequestParam(value = "userMobile", required = false) String userMobile,
            @RequestParam(value = "o", required = true, defaultValue = "user_created desc") String order,
            @RequestParam(value = "org", required = true, defaultValue = "") String org) {
        JedaUserExample example;
        example = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "queryuserkey", true);
        example.clear();
        JedaUserExample.Criteria cri = example.createCriteria();
        if (!StringUtils.isEmpty(loginName)) {
            cri.andUserIdLike("%" + loginName + "%");
        }
        if (!StringUtils.isEmpty(userID)) {
            cri.andUserIdLike("%" + userID + "%");
        }
        if (!StringUtils.isEmpty(userName)) {
            cri.andUserNameLike("%" + userName + "%");
        }
        if (!StringUtils.isEmpty(userTel)) {
            cri.andUserTelLike("%" + userTel + "%");
        }
        if (!StringUtils.isEmpty(userMobile)) {
            cri.andUserMobileLike("%" + userMobile + "%");
        }
        return this.queryUser(currentUser, pageIndex, PAGESIZE, order, org, example, userID, userName, userTel, userMobile);
    }

    /**
     * 用户模块主页面
     *
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/user/index", method = RequestMethod.GET)
    public ModelAndView orgTree(HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("jeda/user/index");
        return mv;
    }

    /**
     * 新建用户
     *
     * @param request
     * @param returnview
     * @param orgId
     * @param
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/user/new", method = RequestMethod.GET)
    public ModelAndView newUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "r", required = true, defaultValue = "") String returnview,
            @RequestParam(value = "orgId", required = true, defaultValue = "") String orgId) {
        ModelAndView mv = new ModelAndView();
        JedaOrgMapper jo = this.jService.getMapper(JedaOrgMapper.class);
        String orgName = jo.selectByPrimaryKey(orgId).getOrgName();
        if (orgId != null && !orgId.equals("")) {
            orgid = orgId;
        } else {
            orgid = "6400001000000000";
        }
        mv.addObject("returnview", (returnview));
        mv.addObject("orgId", orgId);
        mv.addObject("orgName", orgName);
        mv.setViewName("jeda/user/addnew");
        return mv;

    }

    /**
     * 新建用户写入数据库
     *
     * @param user
     * @param request
     * @param
     * @return
     */
    @AutoMvcLog
    @LoginRequired
    @RequestMapping(value = "/jeda/user/new", method = RequestMethod.POST)
    public @ResponseBody
    JedaUser newUserin(JedaUser currentUser, HttpServletResponse response, @RequestBody JedaUser user, HttpServletRequest request) {

        //System.out.println("用户名："+user.getUserId());
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        user.setUserPassword(passwordEncoder.encodePassword(Constants.DEFAULT_PASSWORD, ""));
        user.setOrgId(orgid);
        user.setLoginName(user.getUserId());
        user.setUserCreator(currentUser.getUserId());
        user.setUserCreated(new Date());
        user.setUserEnabled(BigDecimal.ZERO);
        ju.insert(user);

        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionADD, LoggerUtils.MODEL_USER, LoggerUtils.ASSERT_SUCCEED, user);

        return user;
    }

    /**
     * 返回用户信息
     *
     * @param request
     * @param returnview
     * @param userId
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/user/view", method = RequestMethod.GET)
    public ModelAndView view(HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "r", required = true, defaultValue = "") String returnview,
            @RequestParam(value = "userId", required = true, defaultValue = "") String userId) {
        ModelAndView mv = new ModelAndView();
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        JedaOrgMapper jo = this.jService.getMapper(JedaOrgMapper.class);
        JedaUser user = ju.selectByPrimaryKey(userId);
        mv.addObject("orgName",
                jo.selectByPrimaryKey(user.getOrgId()).getOrgName());
        mv.addObject("returnview", (returnview));
        mv.addObject("user", ju.selectByPrimaryKey(userId));
        mv.setViewName("jeda/user/view");
        return mv;
    }

    /**
     * 修改个人信息页面控制
     *
     * @param request
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/user/selfedit", method = RequestMethod.GET)
    public ModelAndView selfedit(JedaUser currentUser, HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        //JedaUser user = currentUser;
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        JedaUser user = ju.selectByPrimaryKey(currentUser.getUserId());
        JedaOrgMapper jo = this.jService.getMapper(JedaOrgMapper.class);
        mv.addObject("orgName",
                jo.selectByPrimaryKey(user.getOrgId()).getOrgName());
        mv.addObject("user", user);
        mv.setViewName("jeda/user/selfedit");
        return mv;
    }

    /**
     * 修改个人信息写入数据库
     *
     * @param request
     * @param response
     * @param user
     */
    @AutoMvcLog
    @LoginRequired
    @RequestMapping(value = "/jeda/user/selfedit", method = RequestMethod.POST)
    public @ResponseBody
    void selfedit(HttpServletRequest request, HttpServletResponse response, @RequestBody JedaUser user) {
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        user.setUserModified(new Date());
        ju.updateByPrimaryKeySelective(user);
        HttpSession session = request.getSession();
        session.setAttribute("result", user);
        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionEDIT, LoggerUtils.MODEL_USER, LoggerUtils.ASSERT_SUCCEED, user);

    }

    /**
     * 检查用户ID是否重复
     *
     * @param a
     * @return
     */
    @AutoMvcLog
    @LoginRequired
    @RequestMapping(value = "/jeda/user/checkid", method = RequestMethod.POST)
    public @ResponseBody
    int Userch(HttpServletResponse response, @RequestParam(value = "a", required = false) String a) {
        int i = 0;
        //System.out.println("用户名："+user.getUserId());
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        JedaUser user = ju.selectByPrimaryKey(a);
        if (user != null) {
            i = 1;
        }
        return i;
    }

    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/user/selfpwd", method = RequestMethod.GET)
    public ModelAndView selfpwd(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jeda/user/selfpwd");
        return mv;
    }

    /**
     * 修改密码写入数据库
     *
     * @param request
     * @param pwd
     */
    @AutoMvcLog
    @LoginRequired
    @RequestMapping(value = "/jeda/user/selfpwd", method = RequestMethod.POST)
    public @ResponseBody
    void selfpwd(JedaUser user ,HttpServletResponse response, HttpServletRequest request, @RequestBody String pwd) {
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
//        JedaUser user = currentUser;
        user.setUserModified(new Date());
        user.setUserPassword(passwordEncoder.encodePassword(pwd, ""));
        ju.updateByPrimaryKey(user);
        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionEDIT, LoggerUtils.MODEL_USER, LoggerUtils.ASSERT_SUCCEED, user);

    }

    /**
     * 重置密码密码写入数据库
     *
     * @param request
     *
     */
    @AutoMvcLog
    @LoginRequired
    @RequestMapping(value = "/jeda/user/resetpwd", method = RequestMethod.POST)
    public @ResponseBody
    void resetpwd(HttpServletResponse response, HttpServletRequest request) {
        String pwd = request.getParameter("pwd");
        String userid = request.getParameter("userid");
        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        JedaUser user = ju.selectByPrimaryKey(userid);
        user.setUserModified(new Date());
        user.setUserPassword(passwordEncoder.encodePassword(pwd, ""));
        ju.updateByPrimaryKey(user);
        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionEDIT, LoggerUtils.MODEL_USER, LoggerUtils.ASSERT_SUCCEED, user);

    }

    /**
     * 密码监测
     *
     * @param a
     * @return
     */
    @AutoMvcLog
    @LoginRequired
    @RequestMapping(value = "/jeda/user/checkpwd", method = RequestMethod.POST)
    public @ResponseBody
    int Pwdch(JedaUser currentUser, HttpServletResponse response, @RequestBody String a) {
        int i = 0;
        if (passwordEncoder.encodePassword(a, "").equals(currentUser.getUserPassword())) {
            i = 1;
        }
        return i;
    }

    @RequestMapping(value = "/jeda/user/selectsend2user", method = RequestMethod.GET)
    public ModelAndView selectsend2user(@RequestParam(value = "role", required = false, defaultValue = "") String role,
            @RequestParam(value = "org", required = false, defaultValue = "") String org) {
        ModelAndView mv = new ModelAndView();
        SqlSessionTemplate session = this.jService.getSqlSession();
        String[] roles;
        if ("".equals(role)) {
            roles = null;
        } else {
            roles = role.split(",");
            for(int i=0;i<roles.length;i++){
                roles[i]=roles[i].trim();
            }
        }
        String[] orgs;
        if ("".equals(org)) {
            orgs = null;
        } else {
            orgs = org.split(",");
            for(int i=0;i<orgs.length;i++){
                orgs[i]=orgs[i].trim();
            }
        }
        HashMap<String, Object> v = new HashMap<String, Object>();
        v.put("roleid", roles);
        v.put("orgid", orgs);
        mv.addObject("result", session.selectList("bas.jeda.dao.sqlmap.selectUserByRole", v));
        return mv;
    }

}
