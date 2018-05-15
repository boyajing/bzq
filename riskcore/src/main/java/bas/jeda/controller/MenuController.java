/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;
import bas.jeda.core.JedaService;
import bas.jeda.dao.JedaMenu;
import bas.jeda.dao.JedaMenuExample;
import bas.jeda.dao.JedaMenuMapper;
import bas.jeda.dao.JedaRoleMapper;
import bas.jeda.dao.JedaRoleMenuExample;
import bas.jeda.dao.JedaRoleMenuKey;
import bas.jeda.dao.JedaRoleMenuMapper;
import bas.jeda.dao.JedaUser;
import com.nantian.utils.StringUtils;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *  菜单控制器
 * @author JTX
 */
@Controller
public class MenuController extends JedaController{

    @Value("#{configProperties['PAGESIZE']}")
    private int PAGESIZE;
    private String parentmenu;
    /**
     * 菜单模块主页面
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/menu/index", method = RequestMethod.GET)
    public ModelAndView menu(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jeda/menu/index");
        return mv;
    }
    /**
     * 返回菜单树
     * @return 
     */
    @LoginRequired  
    @RequestMapping(value = "/jeda/menu/menutree", method = RequestMethod.GET)
    public ModelAndView menuTree(HttpServletResponse response){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("jeda/menu/menutree");
        return mv;
    }
    
    @LoginRequired  
    @RequestMapping(value = "/jeda/menu/menuselect", method = RequestMethod.GET)
    public ModelAndView menuSelect(HttpServletResponse response,
            @RequestParam(value = "po", required = true, defaultValue = "") String po,
            @RequestParam(value="role", required = false, defaultValue = "") String role){
        ModelAndView mv=new ModelAndView();
        
        if(role!=null&&!role.isEmpty()){
            JedaRoleMenuMapper jm=jService.getMapper(JedaRoleMenuMapper.class);
            JedaRoleMenuExample example=new JedaRoleMenuExample();
            example.createCriteria().andRoleIdEqualTo(role);
            List<JedaRoleMenuKey> list=jm.selectByExample(example);
            mv.addObject("menus", list);
            if(!po.contains("?")){
                po+="?role="+role;
            }else{
                po+="&role="+role;
            }
        }
        mv.addObject("posturl", po);
        mv.setViewName("jeda/menu/menuselect");
        return mv;
    }
     
    /**
     * 返回菜单树JSON
     * @return 
     */
    @LoginRequired  
    @RequestMapping(value = "/jeda/menu/gettree", method = RequestMethod.GET)
    public void getMenuTree(HttpServletResponse response,            
            @RequestParam(value = "root", required = true, defaultValue = "source") String root,
            @RequestParam(value = "location", required = true, defaultValue = "source") String location) {
        JedaMenuExample example;
        example = new JedaMenuExample();
        JedaMenuExample.Criteria cri = example.createCriteria();
        if("source".equals(root)){
            cri.andParentMenuIdIsNull();
        }else{
            cri.andParentMenuIdEqualTo(root);
        }
        example.setOrderByClause("MENU_ID ASC");
        JedaMenuMapper jom=this.jService.getMapper(JedaMenuMapper.class);
        ModelAndView mv = new ModelAndView();
        List<JedaMenu> list=jom.selectByExample(example);
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(int i=0;i<list.size();i++){
            sb.append("{");
            sb.append("\"text\":\"").append(list.get(i).getMenuName()).append("\",");
            sb.append("\"id\":\"").append(list.get(i).getMenuId()).append("\",");
            sb.append("	\"hasChildren\": true}");
            if(i!=list.size()-1){
                sb.append(",");
            }
        }
        sb.append("]");
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 查询页面
     * @param request
     * @param pageIndex
     * @param order
     * @param pmenu
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/menu/query", method = RequestMethod.GET)
    public ModelAndView query(HttpServletRequest request,HttpServletResponse response,
           // @RequestParam(value = "menuId", required = true, defaultValue = "") String menuId,
            @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
            @RequestParam(value = "o", required = true, defaultValue = "") String order,
            @RequestParam(value = "pmenu", required = true, defaultValue = "") String pmenu) {
        JedaMenuExample example;
        example = Constants.getSessionObject(JedaMenuExample.class, request.getSession(true), "querymenukey", true);
        if (pageIndex == -1) {
            example.clear();
        }
        return this.queryMenu(pageIndex, PAGESIZE, order,pmenu, example);
    }
    /**
     * 查询页面动作
     * @param request
     * @param pageIndex
     * @param menuName
     * @param menuId
     * @param order
     * @param pm
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/menu/query", method = RequestMethod.POST)
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "pi", required = true, defaultValue = "1") int pageIndex,
            @RequestParam(value = "menuName", required = false) String menuName,
            @RequestParam(value = "menuId", required = false) String menuId,
            @RequestParam(value = "o", required = true, defaultValue = "") String order,
            @RequestParam(value = "pm", required = true, defaultValue = "") String pm) {
        JedaMenuExample example;
        example = Constants.getSessionObject(JedaMenuExample.class, request.getSession(true), "queryjedamenukey", true);
        example.clear();

        JedaMenuExample.Criteria cri = example.createCriteria();
        
        if (!StringUtils.isEmpty(menuId)) {
            cri.andMenuIdLike("%" + menuId + "%");
        }
        if (!StringUtils.isEmpty(menuName)) {
            cri.andMenuNameLike("%" + menuName + "%");
        }

        return this.queryMenu(pageIndex, PAGESIZE, order, pm, example);
    }
     private ModelAndView queryMenu(int pageIndex, int pageSize, String order, String pm,JedaMenuExample example) {
        ModelAndView mv = new ModelAndView();
        //example.clear();
        JedaMenuMapper jmm = jService.getMapper(JedaMenuMapper.class);

        JedaMenuExample.Criteria cri = example.createCriteria();
        if (!StringUtils.isEmpty(order)) {
            example.setOrderByClause(order);
            mv.addObject("ordercase", order);
        } else {
            mv.addObject("ordercase", "");
        }
        if(!StringUtils.isEmpty(pm)){
            cri.andParentMenuIdEqualTo(pm);
        }
        List<JedaMenu> us = jmm.selectByExample(example);
        int pc = 0;
        if (us.size() != 0) {
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
        mv.addObject("menubean", jmm.selectByPrimaryKey(pm));
        mv.addObject("returnview", StringUtils.encodeStr("jeda/menu/query?pi=" + String.valueOf(pageIndex) + "&ps=" + String.valueOf(pageSize)));
         mv.setViewName("jeda/menu/query");
        return mv;
    }
     /**
      * 新建菜单
      * @param request
      * @param returnview
      * @return 
      */
    @LoginRequired
    @RequestMapping(value = "/jeda/menu/new", method = RequestMethod.GET)
    public ModelAndView newMenu(HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "r", required = true, defaultValue = "") String returnview,
            @RequestParam(value = "pm", required = true, defaultValue = "") String pm) {
        ModelAndView mv = new ModelAndView();
        parentmenu=pm;
        mv.addObject("pm", parentmenu);
        mv.addObject("returnview", StringUtils.decodeStr(returnview));
        mv.setViewName("jeda/menu/addnew");
        return mv;
    }
    
    /**
     * 新建菜单入库
     * @param request
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/menu/new", method = RequestMethod.POST)
    public  @ResponseBody JedaMenu newMenuin(JedaUser currentUser,HttpServletRequest request,HttpServletResponse response,@RequestBody JedaMenu menu) {
        //System.out.println("用户名："+org.getorgId());
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        JedaMenuMapper ju = this.jService.getMapper(JedaMenuMapper.class);
        menu.setMenuCreated(new Date());
        menu.setMenuCreator(currentUser.getUserId());
        menu.setParentMenuId(parentmenu);
        this.addSLog(request, u, "新增", "菜单管理", "新增");
        ju.insert(menu);
        return menu;
    }
    
    /**
     * 修改菜单页面
     * @param request
     * @param returnview
     * @param menuId
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/menu/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "r", required = true, defaultValue = "") String returnview,
                               @RequestParam(value = "menuId", required = true, defaultValue = "") String menuId ) {
        ModelAndView mv = new ModelAndView();
        JedaMenuMapper ju = this.jService.getMapper(JedaMenuMapper.class);
        mv.addObject("returnview", StringUtils.decodeStr(returnview));
        mv.addObject("menu", ju.selectByPrimaryKey(menuId));
        mv.setViewName("jeda/menu/edit");
        return mv;
    }
    /**
     * 修改菜单入库
     * @param request
     * @param response
     * @param menu 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/menu/edit", method = RequestMethod.POST)
    public @ResponseBody void edit(JedaUser currentUser,HttpServletRequest request,HttpServletResponse response,@RequestBody JedaMenu menu) {
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        JedaMenuMapper ju = this.jService.getMapper(JedaMenuMapper.class);
        menu.setMenuModified(new Date());
        menu.setMenuModifier(currentUser.getUserId());
        ju.updateByPrimaryKeySelective(menu);
        HttpSession session=request.getSession();
        this.addSLog(request, u, "修改", "菜单管理", "修改");
        session.setAttribute("result", menu);
    }
    /**
     * 查看
     * @param request
     * @param returnview
     * @param menuId
     * @return 
     */
    @RequestMapping(value = "/jeda/menu/view", method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "r", required = true, defaultValue = "") String returnview,
    @RequestParam(value = "menuId", required = true, defaultValue = "") String menuId) {
        ModelAndView mv = new ModelAndView();
        JedaMenuMapper ju = this.jService.getMapper(JedaMenuMapper.class);
        mv.addObject("returnview",returnview);
        mv.addObject("menu", ju.selectByPrimaryKey(menuId));
        mv.setViewName("jeda/menu/view");
        return mv;
    }
    /**
     * 删除菜单
     * @param request
     * @param menuId
     * @param pageIndex
     * @param order
     * @param menu
     * @return 
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/menu/delete", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "menuId", required = true, defaultValue = "") String menuId,
            @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
            @RequestParam(value = "o", required = true, defaultValue = "") String order,
            @RequestParam(value = "menu", required = true, defaultValue = "") String menu) {
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        JedaMenuExample example;
        JedaMenuMapper ju = jService.getMapper(JedaMenuMapper.class);
        example = Constants.getSessionObject(JedaMenuExample.class, request.getSession(true), "deletemenukey", true);
        example.clear();
        this.addSLog(request, u, "删除", "菜单管理", "删除");
        ju.deleteByPrimaryKey(menuId);//cascade 
        return this.queryMenu(pageIndex, PAGESIZE, order, menu, example);
    }
    /**
     * 检查菜单ID是否重复
     * @param a
     * @return 
     */ 
    @RequestMapping(value = "/jeda/menu/checkid", method = RequestMethod.POST)
    public @ResponseBody int Menuch(@RequestBody String a) {
        int i=0;
     
        JedaMenuMapper ju = this.jService.getMapper(JedaMenuMapper.class);
        JedaMenu menu =ju.selectByPrimaryKey(a);
        if(menu!=null){
            i=1;
        }
        return i;
    }
}
