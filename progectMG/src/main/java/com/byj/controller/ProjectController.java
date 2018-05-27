package com.byj.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import com.byj.dao.*;
import com.byj.daoExtend.ProjectDetailVo;
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
        mv.addObject("today",new Date());
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

    @LoginRequired
    @RequestMapping(value = "/recordEnd", method = RequestMethod.GET)
    public ModelAndView recordEnd(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TProjectBase project)throws Exception{
        int result=0;
        try{
            if(null!=project.getProjectNo()&& !"".equals(project.getProjectNo())){
                project=projectService.selectByPrimaryKey(project.getProjectNo());
                TProjectDetailExample example = new TProjectDetailExample();
                TProjectDetailExample.Criteria criteria=example.createCriteria();
                criteria.andProjectNoEqualTo(project.getProjectNo()).andIsValidEqualTo("0");
                List<TProjectDetail> details = projectService.selectByExample(example);
                mv.addObject("details",details);
                mv.addObject("project",project);
                mv.addObject("edit",2);
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        mv.setViewName("project/intime/recordEnd");
        return mv;
    }
    @LoginRequired
    @RequestMapping(value = "/recordEnd", method = RequestMethod.POST)
    public void recordEnd(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser, ProjectVo vo)throws Exception{
        int result=0;
        try{
            if( null!=vo.getDetails() && vo.getDetails().size()>0){
                List<TProjectDetail> details=vo.getDetails();
                String projectNo=projectService.selectByPrimaryKey(details.get(0).getId()).getProjectNo();
                for (TProjectDetail detail : details) {
                    if(null!=detail.getId()){
                        TProjectDetail projectDetail=projectService.selectByPrimaryKey(detail.getId());
                        //完工记录
                        if(null!=detail.getEndQuantity() && detail.getEndQuantity().compareTo(new BigDecimal(0))>0){
                            projectService.insertTflowRecordEnd(detail,currentUser);
                            if(null==projectDetail.getEndQuantity()){
                                projectDetail.setEndQuantity(new BigDecimal(0));
                            }
                            //计算总完工数
                            detail.setEndQuantity(detail.getEndQuantity().add(projectDetail.getEndQuantity()));
                            projectDetail.setEndQuantity(detail.getEndQuantity());
                        }
                        if(null!=detail.getPickupQuantity()&& detail.getPickupQuantity().compareTo(new BigDecimal(0))>0){
                            projectService.insertTflowPickup(detail,currentUser);
                            if(null==projectDetail.getPickupQuantity()){
                                projectDetail.setPickupQuantity(new BigDecimal(0));
                            }
                            //计算总提货数
                            detail.setPickupQuantity(detail.getPickupQuantity().add(projectDetail.getPickupQuantity()));
                            projectDetail.setPickupQuantity(detail.getPickupQuantity());
                        }
                        if(null==projectDetail.getEndQuantity()){
                            projectDetail.setEndQuantity(new BigDecimal(0));
                        }
                        if(null==projectDetail.getPickupQuantity()){
                            projectDetail.setPickupQuantity(new BigDecimal(0));
                        }
                        BigDecimal total = projectDetail.getQuantity();
                        BigDecimal endQuentity=projectDetail.getEndQuantity();
                        BigDecimal pickupQuentity=projectDetail.getPickupQuantity();
                        //部分完工未提货
                        if(endQuentity.compareTo(total)<0 && pickupQuentity.compareTo(new BigDecimal(0))==0){
                            detail.setStatus("3");
                        }
                        //部分完工部分提货
                        if(endQuentity.compareTo(total)<0 && pickupQuentity.compareTo(new BigDecimal(0))>0 ){
                            detail.setStatus("4");
                        }
                        //全部完工未提货
                        if(endQuentity.compareTo(total)==0 && pickupQuentity.compareTo(new BigDecimal(0))==0 ){
                            detail.setStatus("5");
                        }
                        //全部完工部分提货
                        if(endQuentity.compareTo(total)==0 && pickupQuentity.compareTo(new BigDecimal(0))>0 && pickupQuentity.compareTo(total)<0){
                            detail.setStatus("6");
                        }
                        if(endQuentity.compareTo(total)==0 && pickupQuentity.compareTo(total)==0){
                            detail.setStatus("7");
                        }
                        projectService.update(detail);
                    }
                }
                TProjectBase projectBase=new TProjectBase();
                projectBase.setProjectNo(projectNo);
                TProjectDetail sumDetail=projectService.sumByProject(projectNo);
                //部分完工未提货
                if(sumDetail.getEndQuantity().compareTo(sumDetail.getQuantity())<0 && sumDetail.getPickupQuantity().compareTo(new BigDecimal(0))==0){
                    projectBase.setStatus("3");
                }
                //部分完工部分提货
                if(sumDetail.getEndQuantity().compareTo(sumDetail.getQuantity())<0 && sumDetail.getPickupQuantity().compareTo(new BigDecimal(0))>0){
                    projectBase.setStatus("4");
                }
                //全部完工未提货
                if(sumDetail.getEndQuantity().compareTo(sumDetail.getQuantity())==0 && sumDetail.getPickupQuantity().compareTo(new BigDecimal(0))==0){
                    projectBase.setStatus("5");
                }
                //全部完工部分提货
                if(sumDetail.getEndQuantity().compareTo(sumDetail.getQuantity())==0 && sumDetail.getPickupQuantity().compareTo(new BigDecimal(0))>0 && sumDetail.getPickupQuantity().compareTo(sumDetail.getQuantity())<0){
                    projectBase.setStatus("6");
                }
                //全部完工部分提货
                if(sumDetail.getEndQuantity().compareTo(sumDetail.getQuantity())==0 && sumDetail.getPickupQuantity().compareTo(sumDetail.getQuantity())==0){
                    projectBase.setStatus("7");
                }
                projectService.update(projectBase);
            }
            result=1;
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

    @LoginRequired
    @RequestMapping(value = "/startProject", method = RequestMethod.POST)
    public void startProject(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser, TProjectBase project)throws Exception{
        int result=0;
        try{
            if(null!=project.getProjectNo() && null!=project.getProjectNo() && null!=project.getBeginDate()){
                //合同流水
                TProjectTflow tflow = new TProjectTflow();
                tflow.setProjectNo(project.getProjectNo());
                tflow.setIsValid("0");
                tflow.setBeginDate(project.getBeginDate());
                tflow.setActDate(new Date());
                tflow.setType("1");//设置开始日期的流水
                tflow.setApplyOpr(currentUser.getUserId());
                tflow.setApplyDate(new Date());
                projectService.insert(tflow);
                project.setStatus("2");
                projectService.update(project);
                result=1;
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }

    @LoginRequired
    @RequestMapping(value = "/proceed", method = RequestMethod.POST)
    public void proceed(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser, TProjectBase project)throws Exception{
        int result=0;
        try{
            if(null!=project.getProjectNo() && null!=project.getProjectNo() && null!=project.getActAmt() && null!=project.getEndDate()){
                //合同流水
                TProjectTflow tflow = new TProjectTflow();
                tflow.setProjectNo(project.getProjectNo());
                tflow.setActDate(project.getEndDate());
                tflow.setIsValid("0");
                tflow.setType("4");//设置状态实际收款
                tflow.setApplyOpr(currentUser.getUserId());
                tflow.setApplyDate(new Date());
                projectService.insert(tflow);
                TProjectTflow tflow2 = new TProjectTflow();
                tflow2.setProjectNo(project.getProjectNo());
                tflow2.setIsValid("0");
                tflow2.setEndDate(new Date());
                tflow2.setActDate(new Date());
                tflow2.setType("5");//设置状态合同结束
                tflow2.setApplyOpr(currentUser.getUserId());
                tflow2.setApplyDate(new Date());
                projectService.insert(tflow2);
                project.setStatus("8");
                projectService.update(project);
                result=1;
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }

    @LoginRequired
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView details(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TProjectBase project)throws Exception{
        if(null!=project && null!=project.getProjectNo()){
            project=projectService.selectByPrimaryKey(project.getProjectNo());
            TProjectDetailExample example = new TProjectDetailExample();
            example.setOrderByClause("ID");
            TProjectDetailExample.Criteria criteria=example.createCriteria();
            criteria.andProjectNoEqualTo(project.getProjectNo()).andIsValidEqualTo("0");
            List<TProjectDetail> details = projectService.selectByExample(example);
            for (TProjectDetail detail : details) {
                TProjectTflowExample tflowExample=new TProjectTflowExample();
                tflowExample.setOrderByClause("ACT_DATE");
                TProjectTflowExample.Criteria criteria1=tflowExample.createCriteria();
                criteria1.andIsValidEqualTo("0").andDetailIdEqualTo(detail.getId());
                List<TProjectTflow> tflows= projectService.selectByExample(tflowExample);
                detail.setTflows(tflows);
            }
            mv.addObject("details",details);
            mv.addObject("project",project);
            mv.setViewName("project/intime/projectDetail");
        }else{
            mv.addObject("msg","此合作商不存在！");
            mv.setViewName("project/new/index");
        }
        return mv;
    }
}
