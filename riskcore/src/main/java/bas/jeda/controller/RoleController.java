/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import bas.jeda.core.LoggerUtils;
import bas.jeda.core.JedaService;
import bas.jeda.dao.JedaMenu;
import bas.jeda.dao.JedaMenuExample;
import bas.jeda.dao.JedaMenuMapper;
import bas.jeda.dao.JedaRole;
import bas.jeda.dao.JedaRoleExample;
import bas.jeda.dao.JedaRoleMapper;
import bas.jeda.dao.JedaRoleMenuExample;
import bas.jeda.dao.JedaRoleMenuKey;
import bas.jeda.dao.JedaRoleMenuMapper;
import bas.jeda.dao.JedaRoleUserExample;
import bas.jeda.dao.JedaRoleUserKey;
import bas.jeda.dao.JedaRoleUserMapper;
import bas.jeda.dao.JedaUser;
import bas.jeda.dao.JedaUserExample;
import bas.jeda.exception.JEDAException;
import com.nantian.utils.StringUtils;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 角色管理控制器
 * @author JTX
 */
@Controller
public class RoleController extends JedaController {
    /**
     * 角色模块主页面
     * @return 
     */
    @Value("#{configProperties['PAGESIZE']}")
    private int PAGESIZE;
    @LoginRequired
    @RequestMapping(value = "/jeda/role/index", method = RequestMethod.GET)
    public ModelAndView role(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jeda/role/index");
        return mv;
    }
    /**
     * 返回角色列表，JSON格式，包含角色ID和角色名称
     * @return 
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/jeda/role/list", method = RequestMethod.GET)
    public ModelAndView listRole(HttpServletResponse response){
        JedaRoleMapper jmm = jService.getMapper(JedaRoleMapper.class);
        List<JedaRole> result=jmm.selectByExample(new JedaRoleExample());
        ModelAndView mv=new ModelAndView();
        mv.addObject(Constants.DEFAULT_RECORD_MODEL_KEY, result);
//        mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        return mv;
    }
     
    /**
     * 查询页面
     * @param request
     * @param roleId
     * @param roleName
     * @param pageIndex
     * @param order
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/query", method = RequestMethod.GET)
    public ModelAndView query(HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "roleId", required = true, defaultValue = "") String roleId,
            @RequestParam(value = "roleName", required = true, defaultValue = "") String roleName,
            @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
            @RequestParam(value = "o", required = true, defaultValue = "role_created desc") String order) {
        JedaRoleExample example;
        example = Constants.getSessionObject(JedaRoleExample.class, request.getSession(true), "queryrolekey", true);
        JedaRoleExample.Criteria cri = example.createCriteria();
        if (pageIndex == -1) {
            example.clear();
        }
        if (!StringUtils.isEmpty(roleId)) {
            cri.andRoleIdLike("%" + roleId + "%");
        }
        if (!StringUtils.isEmpty(roleName)) {
            cri.andRoleNameLike("%" + roleName + "%");
        }
        return this.queryRole(pageIndex, PAGESIZE, order, example);
    }
    /**
     * 查询动作
     * @param request
     * @param pageIndex
     * @param roleName
     * @param roleID
     * @param loginName
     * @param order
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/query", method = RequestMethod.POST)
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "pi", required = true, defaultValue = "1") int pageIndex,
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "roleID", required = false) String roleID,
            @RequestParam(value = "loginName", required = false) String loginName,
            @RequestParam(value = "o", required = true, defaultValue = "role_created desc") String order) {
        JedaRoleExample example;
        example = Constants.getSessionObject(JedaRoleExample.class, request.getSession(true), "queryrolekey", true);
        example.clear();

        JedaRoleExample.Criteria cri = example.createCriteria();
        if (!StringUtils.isEmpty(roleName)) {
            cri.andRoleNameLike("%" + roleName + "%");
        }
        if (!StringUtils.isEmpty(roleID)) {
            cri.andRoleIdLike("%" + roleID + "%");
        }

        return this.queryRole(pageIndex, PAGESIZE, order, example);
    }
    /**
     * 私有的查询动作
     * @param pageIndex
     * @param pageSize
     * @param order
     * @param example
     * @return 
     */
    private ModelAndView queryRole(int pageIndex, int pageSize, String order, JedaRoleExample example) {
        ModelAndView mv = new ModelAndView();
        //example.clear();
        JedaRoleMapper jmm = jService.getMapper(JedaRoleMapper.class);

        if (!StringUtils.isEmpty(order)) {
            example.setOrderByClause(order);
            mv.addObject("ordercase", order);
        } else {
            mv.addObject("ordercase", "");
        }
        List<JedaRole> us = jmm.selectByExample(example);
        int pc = 0;
        if (us.size() != 0 && pageSize != 0) {
            /*没有返回结果*/
            pageCutter.setPageIndex(pageIndex);
            pageCutter.setPageSize(pageSize);
            pageCutter.setUs(us);
            pc = pageCutter.pageCount();
            us = this.pageCutter.cutList();
        }
        mv.addObject("pagecount", pc);
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageCutter.getPageIndex());
        mv.addObject("result", us);
        mv.addObject("returnview", StringUtils.encodeStr("jeda/role/query?pi=" + String.valueOf(pageIndex) + "&ps=" + String.valueOf(pageSize)));
        mv.setViewName("jeda/role/query");
        return mv;
    }
    
    /**
     * 新建角色页面
     * @param request
     * @param returnview
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/new", method = RequestMethod.GET)
    public ModelAndView newRole(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "r", required = true, defaultValue = "") String returnview) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("returnview", StringUtils.decodeStr(returnview));
        mv.setViewName("jeda/role/addnew");
        return mv;
    }

    /**
     * 新建角色入库
     * @param request
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/new", method = RequestMethod.POST)
    public @ResponseBody
    JedaRole newRolein(JedaUser currentUser,HttpServletRequest request,HttpServletResponse response,@RequestBody JedaRole role) {
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        role.setRoleCreated(new Date());
        role.setRoleCreator(currentUser.getUserId());
        JedaRoleMapper ju = this.jService.getMapper(JedaRoleMapper.class);
        ju.insert(role);
        this.addSLog(request, u, "新增", "角色管理", "新增");
        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionADD, LoggerUtils.MODEL_ROLE, LoggerUtils.ASSERT_SUCCEED, role);
        return role;
    }
    /**
     * 编辑角色页面
     * @param request
     * @param returnview
     * @param roleId
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "r", required = true, defaultValue = "") String returnview,
            @RequestParam(value = "roleId", required = true, defaultValue = "") String roleId) {
        ModelAndView mv = new ModelAndView();
        JedaRoleMapper ju = this.jService.getMapper(JedaRoleMapper.class);
        mv.addObject("returnview", returnview);
        mv.addObject("role", ju.selectByPrimaryKey(roleId));
        mv.setViewName("jeda/role/edit");
        return mv;
    }
    /**
     * 编辑角色入库
     * @param request
     * @param response
     * @param role 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/edit", method = RequestMethod.POST)
    public @ResponseBody
    void edit(JedaUser currentUser,HttpServletRequest request, HttpServletResponse response, @RequestBody JedaRole role) {
        JedaRoleMapper ju = this.jService.getMapper(JedaRoleMapper.class);
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        role.setRoleModified(new Date());
        role.setRoleModifier(currentUser.getUserId());
        ju.updateByPrimaryKeySelective(role);
        HttpSession session = request.getSession();
        session.setAttribute("result", role);
        this.addSLog(request, u, "修改", "角色管理", "修改");
        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionEDIT, LoggerUtils.MODEL_ROLE, LoggerUtils.ASSERT_SUCCEED, role);
    }
    
    @AutoMvcLog
    @Transactional
    @LoginRequired
    @RequestMapping(value = "/jeda/role/updateroleuser2", method = RequestMethod.POST)
    public ModelAndView updateroleuser2(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value="users", required = false, defaultValue = "") String users,
            @RequestParam(value="roles", required = false, defaultValue = "") String roles) {
       
        ModelAndView mv=new ModelAndView();
        System.out.println(users);
        System.out.println(roles);
        
        JedaRoleUserMapper jum=this.jService.getMapper(JedaRoleUserMapper.class);
        JedaUser u1 = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        JedaRoleUserExample example=new JedaRoleUserExample();
        example.createCriteria().andRoleIdEqualTo(roles);
        List<JedaRoleUserKey> list=jum.selectByExample(example);
        for(JedaRoleUserKey one :list){
           int a= jum.deleteByPrimaryKey(one);
           System.out.println(a);
        }
        //int a=jum.deleteByExample(example);
        //System.out.println(a);
        //为角色增加用户
        if( users!= null && !users.isEmpty()){
           String [] us = users.split(",");
            for(String u:us){
                JedaRoleUserKey jru=new JedaRoleUserKey();
                jru.setRoleId(roles);
                jru.setUserId(u);
                int b=jum.insert(jru);
//                System.out.println(b);
            } 
        }
        
        this.addSLog(request, u1, "设定用户", "角色管理", "设定用户");
        mv.setViewName("jeda/role/updateroleuser");
        return mv;
    }
    
    @AutoMvcLog
    @Transactional
    @LoginRequired
    @RequestMapping(value = "/jeda/role/updaterolemenu", method = RequestMethod.POST)
    public ModelAndView updaterolemenu(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value="role", required = false, defaultValue = "") String role) {
        if(role==null||role.isEmpty()){
            throw(new JEDAException("updaterolemenu role is NULL"));
        }
        ModelAndView mv=new ModelAndView();
        Enumeration e = request.getParameterNames();
        JedaRoleMenuMapper jm=jService.getMapper(JedaRoleMenuMapper.class);
        JedaRoleMenuExample example=new JedaRoleMenuExample();
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        example.createCriteria().andRoleIdEqualTo(role);
        jm.deleteByExample(example);
        while(e.hasMoreElements()){            
            String name=(String)e.nextElement();
            if(request.getParameterValues(name)==null){continue;}
            String value=request.getParameterValues(name)[0];
            if("on".equals(value)){
                JedaRoleMenuKey jrm=new JedaRoleMenuKey();
                jrm.setRoleId(role);
                jrm.setMenuId(name);
                jm.insert(jrm);
            }
            //System.out.println(name+" "+request.getParameterValues(name)[0]);
        }
        this.addSLog(request, u, "编辑菜单", "角色管理", "编辑菜单");
        mv.addObject(Constants.DEFAULT_RECORD_MODEL_KEY, "succeed");
        return mv;
    }
    
    
    
    @Transactional
    @LoginRequired
    @RequestMapping(value = "/jeda/role/updateroleuser", method = RequestMethod.POST)
    public ModelAndView updateroleuser(HttpServletRequest request, HttpServletResponse response, 
            @RequestParam(value = "rs", required = true, defaultValue = "")   String rs,
            @RequestParam(value = "us", required = true, defaultValue = "")   String us) {
        ModelAndView mv=new ModelAndView();
//        mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        if("".equals(us)){
            mv.addObject(Constants.DEFAULT_RECORD_MODEL_KEY, "false");            
            return mv;
        }
        
        String [] users=us.split(",");
        String [] roles=rs.split(",");
        this.jService.updateroleuser(roles, users);       
        mv.addObject(Constants.DEFAULT_RECORD_MODEL_KEY, "succeed");
        return mv;
    }
    
    /**
     * 为角色赋予菜单
     * @param request
     * @param response
     * @param RM 逗号分隔，[角色ID],[菜单ID],[菜单ID]
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/roleM", method = RequestMethod.POST)
    public @ResponseBody
    void roleMenuIn(HttpServletRequest request, HttpServletResponse response, @RequestBody String RM) {
        String[] rm = RM.split(",");
        String roleId = rm[0];
        JedaRoleMenuMapper ju = this.jService.getMapper(JedaRoleMenuMapper.class);
        JedaRoleMenuKey roleMenu = null;
        for (int i = 1; i < rm.length; i++) {
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(rm[i]);
            ju.insert(roleMenu);
        }
    }
    /**
     * 更新角色的用户
     * @param request
     * @param response
     * @param RU 逗号分隔，[角色ID],[用户ID],[用户ID]
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/roleU", method = RequestMethod.POST)
    public @ResponseBody
    void roleUserIn(HttpServletRequest request, HttpServletResponse response, @RequestBody String RU) {
        String[] ru = RU.split(",");
        String roleId = ru[0];
        int[] r = null;
        JedaRoleUserMapper ju = this.jService.getMapper(JedaRoleUserMapper.class);
        JedaRoleUserKey roleUser = null;
        for (int i = 1; i < ru.length; i++) {
            roleUser.setRoleId(roleId);
            roleUser.setUserId(ru[i]);
            r[i - 1] = ju.insert(roleUser);
        }
    }
    /**
     * 返回角色相信信息
     * @param request
     * @param returnview
     * @param roleId
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/view", method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "r", required = true, defaultValue = "") String returnview,
            @RequestParam(value = "roleId", required = true, defaultValue = "") String roleId) {
        ModelAndView mv = new ModelAndView();
        JedaRoleMapper ju = this.jService.getMapper(JedaRoleMapper.class);
        mv.addObject("returnview", StringUtils.decodeStr(returnview));
        mv.addObject("role", ju.selectByPrimaryKey(roleId));
        mv.setViewName("jeda/role/view");
        return mv;
    }
    /**
     * 删除角色
     * @param request
     * @param roleID
     * @param pageIndex
     * @param order
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/delete", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "roleID", required = true, defaultValue = "") String roleID,
            @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
            @RequestParam(value = "o", required = true, defaultValue = "") String order) {
        JedaRoleExample example;
        JedaRoleMapper ju = jService.getMapper(JedaRoleMapper.class);
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        example = Constants.getSessionObject(JedaRoleExample.class, request.getSession(true), "deleterolekey", true);
        example.clear();
        JedaRoleUserMapper jum=this.jService.getMapper(JedaRoleUserMapper.class);
        
        JedaRoleUserExample jruExample=new JedaRoleUserExample();
        jruExample.createCriteria().andRoleIdEqualTo(roleID);
        List<JedaRoleUserKey> list=jum.selectByExample(jruExample);
        for(JedaRoleUserKey one :list){
           int a= jum.deleteByPrimaryKey(one);
           System.out.println(a);
        }
        ju.deleteByPrimaryKey(roleID);//cascade 
        this.addSLog(request, u, "删除", "角色管理", "删除");
        return this.queryRole(pageIndex, PAGESIZE, order, example);
    }
    /**
     * 导出excel
     * @param request
     * @param order
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/role/excel", method = RequestMethod.GET)
    public ModelAndView exportExcel(HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "o", required = true, defaultValue = "") String order) {

        JedaRoleExample example;
        example = Constants.getSessionObject(JedaRoleExample.class, request.getSession(true), "queryrolekey", true);
        ModelAndView mv = this.queryRole(-1, 0, order, example);
        Map<String, String> columns = new LinkedHashMap<String, String>();
        columns.put("roleId", "角色ID");
        columns.put("roleName", "角色名");
        columns.put("roleDescription", "角色描述");
 
        mv.addObject(Constants.DEFAULT_EXCEL_COLUMNS_MODEL_KEY, columns);

        mv.setViewName("excelView");
        return mv;
    }
    /**
     * 返回用户的角色
     *
     * @param request
     * @param response
     * @param
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/jeda/role/queryUsersRole", method = RequestMethod.GET)
    public ModelAndView queryUsersRole(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestParam(value = "userid", required = true, defaultValue = "") String userid) {
        ModelAndView mv = new ModelAndView();
        JedaRoleUserMapper ju = this.jService.getMapper(JedaRoleUserMapper.class);
        JedaRoleUserExample example=new JedaRoleUserExample();
        example.createCriteria().andUserIdEqualTo(userid);
        mv.addObject("result",ju.selectByExample(example));
//        mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        return mv;
    }
}
