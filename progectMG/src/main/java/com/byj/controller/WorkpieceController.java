package com.byj.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import com.byj.dao.TProjectDetail;
import com.byj.dao.TProjectDetailExample;
import com.byj.dao.TWorkpiece;
import com.byj.dao.TWorkpieceExample;
import com.byj.service.ProjectService;
import com.byj.service.WorkpiecService;
import com.nantian.service.sysmgn.TSysSequenceService;
import com.nantian.utils.Constants;
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
@RequestMapping(value = "workpiece")
public class WorkpieceController extends JedaController {
    @Autowired
    private WorkpiecService workpiecService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TSysSequenceService tSysSequenceService;
    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;

    @LoginRequired
    @RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(TWorkpiece workpiece, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                              @RequestParam(value = "o", required = true, defaultValue = "WORKPIECE_NO desc") String order,
                              ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                              JedaUser currentUser,String select,String detailTr)throws Exception{
        try {
            mv=businessOperationQuery(workpiece,request,pageIndex,order,pageSize,mv,currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null!=select && "1".equals(select)){
            mv.addObject("select",select);
            mv.addObject("detailTr",detailTr);
        }
        mv.setViewName("resource/workpiece/index");
        mv.addObject("pi",pageIndex);
        return mv;
    }
    @Transactional
    private ModelAndView businessOperationQuery(TWorkpiece workpiece, HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser) {
        TWorkpieceExample example = Constants.getSessionObject(TWorkpieceExample.class, request.getSession(true), "TWorkpieceExample", true);
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
        TWorkpieceExample.Criteria criteria = example.createCriteria();

//        if (tProductBase.getProNo() != null&& !"".equals(tProductBase.getProNo() ))
//            criteria.andProNoLike("%" + tProductBase.getProNo() + "%");
//        if (tProductBase.getProName() != null&& !"".equals(tProductBase.getProName() ))
//            criteria.andProNameLike("%" + tProductBase.getProName() + "%");
//        if (tProductBase.getProType() != null&& !"".equals(tProductBase.getProType() ))
//            criteria.andProTypeEqualTo(tProductBase.getProType());

        criteria.andIsValidEqualTo("0");//查询所有有效的投资产品

        List<TWorkpiece> list = workpiecService.selectByExample(example);

        for (TWorkpiece tWorkpiece : list) {
            TProjectDetail detail=projectService.sumByWorkpiece(tWorkpiece.getWorkpieceNo());
            if(null!=detail){
                tWorkpiece.setTotalCount(detail.getTotalCount());
                tWorkpiece.setFinishCount(detail.getFinishCount());
                tWorkpiece.setPickupCount(detail.getPickupCount());
            }
        }
        //分页
        long totalCount = workpiecService.countByExample(example);
        long pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        mv.addObject("pagecount", pageCount);
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageIndex == 0 ? 1 : pageIndex);
        mv.addObject("list",list);
        return mv;
    }
    @LoginRequired
    @RequestMapping(value = "/createWorkpiece", method = RequestMethod.GET)
    public ModelAndView createWorkpiece(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser)throws Exception{
        String orgId=currentUser.getOrgId().substring(0,2);
        String id = tSysSequenceService.getPrimaryKey(TSysSequenceService.WORKPIECE,orgId);
        TWorkpiece workpiece=new TWorkpiece();
        workpiece.setWorkpieceNo(id);
        workpiece.setApplyOpr(currentUser.getUserName());
        workpiece.setApplyDate(new Date());
        mv.addObject("workpiece",workpiece);
        mv.addObject("edit",0);
        mv.setViewName("resource/workpiece/createWorkpiece");
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/updateWorkpiece", method = RequestMethod.GET)
    public ModelAndView updateTool(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TWorkpiece workpiece,String edit)throws Exception{
        if(null!=workpiece && null!=workpiece.getWorkpieceNo()){
            workpiece=workpiecService.selectByPrimaryKey(workpiece.getWorkpieceNo());
            mv.addObject("workpiece",workpiece);
            if(null!=edit){
                mv.addObject("edit",edit);
            }else{
                mv.addObject("edit",1);
            }
            mv.setViewName("resource/workpiece/createWorkpiece");
        }else{
            mv.addObject("msg","此工件不存在！");
            mv.setViewName("resource/workpiece/index");
        }
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveCustomer(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TWorkpiece workpiece,String edit)throws Exception{
        int result=0;
        try{
            if(null!=workpiece.getWorkpieceNo() && !"".equals(workpiece.getWorkpieceNo())){
                if(null!=edit && "1".equals(edit)){
                    result=workpiecService.update(workpiece);
                }else{
                    workpiece.setApplyOpr(currentUser.getUserId());
                    workpiece.setApplyDate(new Date());
                    workpiece.setIsValid("0");
                    result=workpiecService.insert(workpiece);
                }
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

    @LoginRequired
    @RequestMapping(value = "/beforeDelete", method = RequestMethod.GET)
    public void beforeDelete(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TWorkpiece workpiece)throws Exception{
        int result=0;
        try{
            if(null!=workpiece.getWorkpieceNo() && !"".equals(workpiece.getWorkpieceNo())){
                TProjectDetailExample example=new TProjectDetailExample();
                TProjectDetailExample.Criteria criteria=example.createCriteria();
                criteria.andWorkpieceNoEqualTo(workpiece.getWorkpieceNo()).andIsValidEqualTo("0");
                result=(int)projectService.countByExample(example);
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }

    @LoginRequired
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TWorkpiece workpiece)throws Exception{
        int result=0;
        try{
            if(null!=workpiece.getWorkpieceNo() && !"".equals(workpiece.getWorkpieceNo())){
                workpiece.setIsValid("1");
                result=workpiecService.update(workpiece);
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }
}
