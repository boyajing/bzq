/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import bas.jeda.core.LoggerUtils;
import bas.jeda.dao.*;
import com.nantian.utils.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 组织机构控制器
 *
 * @author menghui
 */
@Controller
public class OrgController extends JedaController {
    private String orgid;

    @Value("#{configProperties['PAGESIZE']}")
    private int PAGESIZE;
    /**
     * p==“”时查询parent为null的节点
     *
     * @param request
     * @param parent
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/jeda/org/get", method = RequestMethod.GET)
    public ModelAndView getOrg(HttpServletRequest request,HttpServletResponse response,
                               @RequestParam(value = "p", required = true, defaultValue = "") String parent) {
        JedaOrgExample example;
        example = new JedaOrgExample();
        JedaOrgExample.Criteria cri = example.createCriteria();
        if (StringUtils.isEmpty(parent)) {
            cri.andParentOrgIdIsNull();
        } else {
            cri.andParentOrgIdEqualTo(parent);
        }
        example.setOrderByClause("ORG_ORDER ASC");
        JedaOrgMapper jom = this.jService.getMapper(JedaOrgMapper.class);

        ModelAndView mv = new ModelAndView();
        List<JedaOrg> list = null;
        try {
            list = jom.selectByExample(example);
        } catch (Exception e) {
            System.out.println(e);
        }
        mv.addObject("result", list);
//        mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        return mv;
    }

    /**
     * 返回机构树页面
     *
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/org/orgtree", method = RequestMethod.GET)
    public ModelAndView orgTree(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jeda/org/orgtree");
        return mv;
    }

    /**
     * 返回机构JSON格式
     *
     * @param response
     * @param root
     * @param location
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/org/gettree", method = RequestMethod.GET)
    public void getOrgTree(HttpServletResponse response,JedaUser currentUser,
                           @RequestParam(value = "location", required = true, defaultValue = "") String location,
                           @RequestParam(value = "root", required = true, defaultValue = "root") String root) {
        JedaOrgExample example;
        String useroid = currentUser.getOrgId();
        example = new JedaOrgExample();
        JedaOrgExample.Criteria cri = example.createCriteria();
        if ("root".equals(root) && "userorg".equals(location)) {
            cri.andParentOrgIdEqualTo(useroid);
        } else if (!"root".equals(root)) {
            cri.andParentOrgIdEqualTo(root);
        }
        example.setOrderByClause("ORG_ORDER ASC");
        JedaOrgMapper jom = this.jService.getMapper(JedaOrgMapper.class);

        ModelAndView mv = new ModelAndView();
        List<JedaOrg> list = null;
        try {
            list = jom.selectByExample(example);
        } catch (Exception e) {
            System.out.println(e);
        }
        StringBuilder sb = new StringBuilder();
        /*	{
         "text": "3.6 The Onion: How to add features without adding features (20 min)",
         "id": "36",
         "hasChildren": true
         },*/
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append("{");
            sb.append("\"text\":\"").append(list.get(i).getOrgName()).append("\",");
            sb.append("\"id\":\"").append(list.get(i).getOrgId()).append("\",");
            sb.append("	\"hasChildren\": true}");
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(OrgController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * 查询页面
     *
     * @param request
     * @param pageIndex
     * @param order
     * @param org
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/org/query", method = RequestMethod.GET)
    public ModelAndView query(HttpServletRequest request,HttpServletResponse response,JedaUser currentUser,
                              @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                              @RequestParam(value = "o", required = true, defaultValue = "org_Id") String order,
                              @RequestParam(value = "org", required = true, defaultValue = "0") String org
    ) {

        JedaOrgExample example;
        example = Constants.getSessionObject(JedaOrgExample.class, request.getSession(true), "queryorgkey", true);
        if (pageIndex == -1) {
            example.clear();
        }

        return this.queryOrg(currentUser,pageIndex, PAGESIZE, order, org, example);

    }

    /**
     * 私有的查询动作
     *
     * @param pageIndex
     * @param pageSize
     * @param order
     * @param org
     * @param example
     * @return
     */
    @LoginRequired
    private ModelAndView queryOrg(JedaUser currentUser,int pageIndex, int pageSize, String order, String org, JedaOrgExample example) {
        ModelAndView mv = new ModelAndView();
        //example.clear();
        JedaOrgMapper jom = jService.getMapper(JedaOrgMapper.class);

        if (!StringUtils.isEmpty(org)) {
            mv.addObject("orgbean", jom.selectByPrimaryKey(org));
        } else {
            mv.addObject("orgbean", jom.selectByPrimaryKey(currentUser.getOrgId()));
//       org=currentUser.getOrgId()+"00";
        }

        JedaOrgExample.Criteria cri = example.createCriteria();
        JedaOrgExample.Criteria cri2 = example.createCriteria();
//        if(!org.equals("")&&!org.substring(4).equals("00"))
//            cri2.andOrgIdEqualTo(org);
        if (!StringUtils.isEmpty(order)) {
            example.setOrderByClause(order);
            mv.addObject("ordercase", order);
        } else {
            mv.addObject("ordercase", "");
        }
        if (!StringUtils.isEmpty(org)) {
            cri.andParentOrgIdEqualTo(org);
        }
        example.or(cri2);
        List<JedaOrg> us = null;
        int pc = 0;
        try {
            us = jom.selectByExample(example);
            if (us.size() != 0) {
            /*没有返回结果*/
                pageCutter.setPageIndex(pageIndex);
                pageCutter.setPageSize(pageSize);
                pageCutter.setUs(us);
                pc = pageCutter.pageCount();
                us = this.pageCutter.cutList();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        mv.addObject("org", org);
        mv.addObject("pagecount", pc);
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageCutter.getPageIndex());
        mv.addObject("result", us);

        mv.addObject("returnview", StringUtils.encodeStr("jeda/org/query?pi=" + String.valueOf(pageIndex) + "&ps=" + String.valueOf(pageSize)));

        mv.setViewName("jeda/org/query");
        return mv;
    }

    /**
     * 删除机构
     *
     * @param request
     * @param orgID
     * @param pageIndex
     * @param order
     * @param org
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/org/delete", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,JedaUser currentUser,
                               @RequestParam(value = "orgID", required = true, defaultValue = "") String orgID,
                               @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                               @RequestParam(value = "o", required = true, defaultValue = "") String order,
                               @RequestParam(value = "org", required = true, defaultValue = "") String org) {
        JedaOrgExample example;
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        JedaOrgMapper ju = jService.getMapper(JedaOrgMapper.class);
        example = Constants.getSessionObject(JedaOrgExample.class, request.getSession(true), "deleteorgkey", true);
        example.clear();
        this.addSLog(request, u, "删除", "本行机构管理", "删除");
        ju.deleteByPrimaryKey(orgID);//cascade 
        return this.queryOrg(currentUser,pageIndex, PAGESIZE, order, org, example);
    }

    /**
     * 返回机构的JSON
     *
     * @param orgID
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/jeda/org/getorg", method = RequestMethod.GET)
    public ModelAndView getorg(HttpServletResponse response,@RequestParam(value = "orgID", required = true, defaultValue = "") String orgID) {
        ModelAndView mv = new ModelAndView();
        JedaOrgMapper ju = this.jService.getMapper(JedaOrgMapper.class);
        mv.addObject("result", ju.selectByPrimaryKey(orgID));
        //mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        return mv;
    }

    /**
     * 编辑机构页面
     *
     * @param request
     * @param returnview
     * @param orgID
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/org/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request,HttpServletResponse response,
                             @RequestParam(value = "r", required = true, defaultValue = "") String returnview,
                             @RequestParam(value = "org", required = true, defaultValue = "") String org,
                             @RequestParam(value = "orgID", required = true, defaultValue = "") String orgID) {
        ModelAndView mv = new ModelAndView();
        JedaOrgMapper ju = this.jService.getMapper(JedaOrgMapper.class);
        mv.addObject("returnview", StringUtils.decodeStr(returnview));
        mv.addObject("org", ju.selectByPrimaryKey(orgID));
        mv.addObject("orgid", org);
        mv.setViewName("jeda/org/edit");
        return mv;
    }

    /**
     * 编辑机构入库
     *
     * @param request
     * @param response
     * @param org
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/jeda/org/edit", method = RequestMethod.POST)
    public void edit(JedaUser currentUser,HttpServletRequest request, HttpServletResponse response, @RequestBody JedaOrg org) {
        JedaOrgMapper ju = this.jService.getMapper(JedaOrgMapper.class);
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        org.setOrgModifier(currentUser.getUserId());
        org.setOrgModified(new Date());
        int a = ju.updateByPrimaryKeySelective(org);
        HttpSession session = request.getSession();
        this.addSLog(request, u, "修改", "本行机构管理", "修改");
        session.setAttribute("result", org);
    }

    /**
     * 查询动作
     *
     * @param request
     * @param pageIndex
     * @param orgName
     * @param order
     * @param org
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/org/query", method = RequestMethod.POST)
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response,JedaUser currentUser,
                              @RequestParam(value = "pi", required = true, defaultValue = "1") int pageIndex,
                              @RequestParam(value = "orgName", required = false) String orgName,
                              @RequestParam(value = "o", required = true, defaultValue = "") String order,
                              @RequestParam(value = "org", required = true, defaultValue = "") String org) {
        JedaOrgExample example;
        example = Constants.getSessionObject(JedaOrgExample.class, request.getSession(true), "queryorgkey", true);
        example.clear();

        JedaOrgExample.Criteria cri = example.createCriteria();
        if (!StringUtils.isEmpty(orgName)) {
            cri.andOrgNameLike("%" + orgName + "%");
        }
        return this.queryOrg(currentUser,pageIndex, PAGESIZE, order, org, example);
    }

    /**
     * 机构模块主页没
     *
     * @return
     */
    @RequestMapping(value = "/jeda/org/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jeda/org/index");
        return mv;
    }

    /**
     * 新建机构页面
     *
     * @param request
     * @param returnview
     * @param orgId
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/org/new", method = RequestMethod.GET)
    public ModelAndView newOrg(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "r", required = true, defaultValue = "") String returnview,
                               @RequestParam(value = "orgId", required = true, defaultValue = "") String orgId)throws Exception {
        ModelAndView mv = new ModelAndView();
        if (orgId != null && !orgId.equals("")) {
            orgid = orgId;
        } else {
            orgid = "root";
        }
        if(orgId.length()==4){
            mv.addObject("orgDescription", orgId);
        }
        mv.addObject("orgid", orgid);
        mv.addObject("returnview", StringUtils.decodeStr(returnview));
        mv.setViewName("jeda/org/addnew");
        return mv;

    }

    /**
     * 新建机构动作
     *
     * @param request
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/jeda/org/new", method = RequestMethod.POST)
    public JedaOrg newOrgin(JedaUser currentUser,@RequestBody JedaOrg org,HttpServletResponse response, HttpServletRequest request) {
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        JedaOrgMapper ju = this.jService.getMapper(JedaOrgMapper.class);
        try {
            org.setOrgId(org.getOrgDescription());
            org.setParentOrgId(orgid);
            org.setOrgCreator(currentUser.getUserId());
            org.setOrgCreated(new Date());
            org.setOrgEnabled(BigDecimal.ZERO);

            this.addSLog(request, u, "新增", "本行机构管理", "新增");
        } catch (Exception e) {
            System.out.println(e);
        }
        ju.insert(org);
        return org;
    }

    /**
     * 查看机构
     *
     * @param request
     * @param returnview
     * @param orgID
     * @return
     */
    @RequestMapping(value = "/jeda/org/view", method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request,HttpServletResponse response,
                             @RequestParam(value = "org", required = true, defaultValue = "") String org,
                             @RequestParam(value = "r", required = true, defaultValue = "") String returnview,
                             @RequestParam(value = "orgID", required = true, defaultValue = "") String orgID) {
        ModelAndView mv = new ModelAndView();
        JedaOrgMapper ju = this.jService.getMapper(JedaOrgMapper.class);
        mv.addObject("returnview", StringUtils.decodeStr(returnview));
        mv.addObject("porg", org);
        mv.addObject("org", ju.selectByPrimaryKey(orgID));
        mv.setViewName("jeda/org/view");
        return mv;
    }

    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/jeda/org/updateorguser", method = RequestMethod.GET)
    public ModelAndView updateorguser(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "orgID", required = true, defaultValue = "") String orgID, @RequestParam(value = "us", required = true, defaultValue = "") String us) {

        JedaUserMapper ju = this.jService.getMapper(JedaUserMapper.class);
        ModelAndView mv = new ModelAndView();
//        mv.setView(new org.springframework.web.servlet.view.json.MappingJackson2JsonView());
        if ("".equals(us)) {
            mv.addObject(Constants.DEFAULT_RECORD_MODEL_KEY, "false");
            return mv;
        }

        String[] users = us.split(",");
        for (String user : users) {
            JedaUser u = ju.selectByPrimaryKey(user);
            u.setOrgId(orgID);
            ju.updateByPrimaryKey(u);
        }
        mv.addObject(Constants.DEFAULT_RECORD_MODEL_KEY, "succeed");
        return mv;
    }

    /**
     * 启用
     *
     * @param request
     * @param e
     * @param orgId
     * @param pageIndex
     * @param order
     * @param org
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/jeda/org/able", method = RequestMethod.GET)
    public ModelAndView able(HttpServletRequest request,HttpServletResponse response,JedaUser currentUser,
                             @RequestParam(value = "e", required = true, defaultValue = "") BigDecimal e,
                             @RequestParam(value = "orgId", required = true, defaultValue = "") String orgId,
                             @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                             @RequestParam(value = "o", required = true, defaultValue = "") String order,
                             @RequestParam(value = "org", required = true, defaultValue = "") String org) {
        JedaOrgExample example;
        JedaOrgMapper ju = jService.getMapper(JedaOrgMapper.class);
        JedaUser u = (JedaUser) request.getSession().getAttribute(Constants.USERSESSIONID);
        example = Constants.getSessionObject(JedaOrgExample.class, request.getSession(true), "org", true);
        example.clear();
        JedaOrg oorg = ju.selectByPrimaryKey(orgId);
        oorg.setOrgEnabled(e);
        oorg.setOrgModified(new Date());
        oorg.setOrgModifier(currentUser.getUserId());
        ju.updateByPrimaryKey(oorg);
        BigDecimal bd = new BigDecimal("1");
        if (bd.equals(oorg.getOrgEnabled())) {
            this.addSLog(request, u, "启用", "本行机构管理", "启用");
        } else {
            this.addSLog(request, u, "停用", "本行机构管理", "停用");
        }
        LoggerUtils.tracklog(null, this, request, LoggerUtils.ActionSTATECHANGE, LoggerUtils.MODEL_USER, LoggerUtils.ASSERT_SUCCEED, oorg);
        return this.queryOrg(currentUser,pageIndex, PAGESIZE, order, org, example);
    }

    @ResponseBody
    @RequestMapping(value = "/jeda/org/checknm", method = RequestMethod.POST)
    public int nmch(HttpServletResponse response,@RequestBody String a) {
        JedaOrgMapper ju = this.jService.getMapper(JedaOrgMapper.class);
        JedaOrgExample je = new JedaOrgExample();
        JedaOrgExample.Criteria c = je.createCriteria();
        c.andOrgNameEqualTo(a);
        int i = ju.countByExample(je);
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "/jeda/org/checklnm", method = RequestMethod.POST)
    public int lnmch(HttpServletResponse response,@RequestBody String a) {
        JedaOrgMapper ju = this.jService.getMapper(JedaOrgMapper.class);
        JedaOrgExample je = new JedaOrgExample();
        JedaOrgExample.Criteria c = je.createCriteria();
        c.andOrglnameEqualTo(a);
        int i = ju.countByExample(je);
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "/jeda/org/org_zTree", method = RequestMethod.GET)
    public ModelAndView orgTree(@RequestParam(value = "orgid" ,required = false ,defaultValue = "-1") String orgid) {
        ModelAndView mv = new ModelAndView();
        JedaOrgMapper ju = this.jService.getMapper(JedaOrgMapper.class);
        List<JedaOrg> jedaOrgs = ju.selectByExample(null);
        List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
        if(jedaOrgs !=null ){
            for(int i = 0 ;i<jedaOrgs.size();i++ ){
                Map map= new HashMap<String,Object>();
                if(jedaOrgs.get(i).getOrgId().equals(orgid)){
                    map.put("open",true);
                }
                map.put("id",jedaOrgs.get(i).getOrgId());
                map.put("name",jedaOrgs.get(i).getOrgName());
                map.put("pId",jedaOrgs.get(i).getParentOrgId());
                result.add(map);
            }
        }
        mv.addObject("result",result);
        return  mv;
    }
}