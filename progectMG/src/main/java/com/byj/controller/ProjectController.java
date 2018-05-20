package com.byj.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import com.byj.dao.*;
import com.byj.daoExtend.ProjectVo;
import com.byj.service.CustomerService;
import com.byj.service.ProjectService;
import com.nantian.service.sysmgn.TSysSequenceService;
import com.nantian.utils.Constants;
import com.nantian.utils.DateFormats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */
@Controller
@RequestMapping(value = "project")
public class ProjectController  extends JedaController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private CustomerService customerService;
    private DateFormats dateFormats=new DateFormats();
    @Autowired
    private TSysSequenceService tSysSequenceService;
    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;

    @LoginRequired
    @RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(TProjectBase project, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                              @RequestParam(value = "o", required = true, defaultValue = "PROJECT_NO desc") String order,
                              ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                              JedaUser currentUser,String time)throws Exception{
        try {
            mv=businessOperationQuery(project,request,pageIndex,order,pageSize,mv,currentUser,time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if("1".equals(time)){
            mv.setViewName("project/new/index");
        }
        if("2".equals(time)){
            mv.setViewName("project/intime/index");
        }
        if("3".equals(time)){
            mv.setViewName("project/history/index");
        }
        mv.addObject("time",time);
        mv.addObject("pi",pageIndex);
        return mv;
    }
    @Transactional
    private ModelAndView businessOperationQuery(TProjectBase project,  HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser,String time) {
        TProjectBaseExample example = Constants.getSessionObject(TProjectBaseExample.class, request.getSession(true), "TProjectBaseExample", true);
        if (-1 == pageIndex) {
            example.clear();
            pageIndex = 0;
            example.setLimitStart(pageIndex);
        } else if (0 == pageIndex) {
            pageIndex = 0;
            example.setLimitStart(pageIndex);
        } else {
            example.setLimitStart((pageIndex - 1) * pageSize);
        }
        if (!"".equals(order))
            example.setOrderByClause(order);
        example.setLimitEnd(pageSize);
        TProjectBaseExample.Criteria criteria = example.createCriteria();
        if("1".equals(time)){
            criteria.andStatusEqualTo("1");
        }
        if("2".equals(time)){
            List<String> status=new ArrayList<String>();
            status.add("1");
            status.add("8");
            criteria.andStatusNotIn(status);
        }
        if("3".equals(time)){
            criteria.andStatusEqualTo("8");
        }

//        if (tProductBase.getProNo() != null&& !"".equals(tProductBase.getProNo() ))
//            criteria.andProNoLike("%" + tProductBase.getProNo() + "%");
//        if (tProductBase.getProName() != null&& !"".equals(tProductBase.getProName() ))
//            criteria.andProNameLike("%" + tProductBase.getProName() + "%");
//        if (tProductBase.getProType() != null&& !"".equals(tProductBase.getProType() ))
//            criteria.andProTypeEqualTo(tProductBase.getProType());

        criteria.andIsValidEqualTo("0");//查询所有有效的投资产品

        List<TProjectBase> list = projectService.selectByExample(example);
        for (TProjectBase projectBase : list) {

        }
        //分页
        long totalCount = projectService.countByExample(example);
        long pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        mv.addObject("pagecount", pageCount);
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageIndex == 0 ? 1 : pageIndex);
        mv.addObject("list",list);
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/createProject", method = RequestMethod.GET)
    public ModelAndView createProject(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser)throws Exception{
        String orgId=currentUser.getOrgId().substring(0,2);
        String id = tSysSequenceService.getPrimaryKey(TSysSequenceService.TZ_PROJECT,orgId);
        TProjectBase projectBase=new TProjectBase();
        projectBase.setProjectNo(id);
        projectBase.setApplyDate(new Date());
        projectBase.setApplyOpr(currentUser.getUserId());
        mv.addObject("project",projectBase);
        mv.addObject("edit",0);
        mv.setViewName("project/new/newProject");
        return mv;
    }
    @LoginRequired
    @RequestMapping(value = "/updateProject", method = RequestMethod.GET)
    public ModelAndView updateProject(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TProjectBase project,String edit)throws Exception{
        if(null!=project && null!=project.getProjectNo()){
            project=projectService.selectByPrimaryKey(project.getProjectNo());
            TProjectDetailExample example = new TProjectDetailExample();
            TProjectDetailExample.Criteria criteria=example.createCriteria();
            criteria.andProjectNoEqualTo(project.getProjectNo()).andIsValidEqualTo("0");
            List<TProjectDetail> details = projectService.selectByExample(example);
            mv.addObject("details",details);
            mv.addObject("project",project);
            if(null!=edit){
                mv.addObject("edit",edit);
            }else{
                mv.addObject("edit",1);
            }
            mv.setViewName("project/new/newProject");
        }else{
            mv.addObject("msg","此合作商不存在！");
            mv.setViewName("project/new/index");
        }
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveProject(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser, ProjectVo vo, String edit)throws Exception{
        int result=0;
        try{
            if(null!=vo && null!=vo.getProject() && null!=vo.getDetails()){
                TProjectBase project = vo.getProject();
                List<TProjectDetail> details=vo.getDetails();
                if(null!=project.getProjectNo() && !"".equals(project.getProjectNo())){
                    if(null!=edit && "1".equals(edit)){
                        projectService.update(project);
                        for (TProjectDetail detail:details) {
                            if(null!=detail.getId()){
                                projectService.update(detail);
                            }else{
                                detail.setApplyOpr(currentUser.getUserId());
                                detail=projectService.createDetail(project,detail);
                                projectService.insert(detail);
                            }
                        }
                        result=1;
                    }else{
                        TCustomer customer=customerService.selectByPrimaryKey(project.getCustomerNo());
                        String strD=dateFormats.dateToStrD(new Date());
                        project.setProjectName(customer.getCustomerName()+"-"+strD+"合同");
                        project.setApplyOpr(currentUser.getUserId());
                        project=projectService.createProject(project);
                        projectService.insert(project);
                        for (TProjectDetail detail : details) {
                            if(null!=detail.getWorkpieceNo()){
                                detail.setApplyOpr(currentUser.getUserId());
                                detail=projectService.createDetail(project,detail);
                                projectService.insert(detail);
                            }
                        }
                        result=1;
                    }
                }
            }

        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }
    @LoginRequired
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TProjectBase project)throws Exception{
        int result=0;
        try{
            if(null!=project.getProjectNo()&& !"".equals(project.getProjectNo())){
                project.setIsValid("1");
                projectService.update(project);
                TProjectDetailExample example=new TProjectDetailExample();
                TProjectDetailExample.Criteria criteria=example.createCriteria();
                criteria.andProjectNoEqualTo(project.getProjectNo());
                List<TProjectDetail> detaillist=projectService.selectByExample(example);
                for(TProjectDetail detail:detaillist){
                    detail.setIsValid("1");
                    projectService.update(detail);
                }
                result=1;
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }

    /**
     * 返回ajax结果
     * @param response
     * @param result
     */
    private void returnResult(HttpServletResponse response,int result){
        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
