package com.byj.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import com.byj.dao.*;
import com.byj.service.ExpenseService;
import com.byj.service.ToolService;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */
@Controller
@RequestMapping(value = "tool")
public class ToolController extends JedaController {
    @Autowired
    private ToolService toolService;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private TSysSequenceService tSysSequenceService;
    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;

    @LoginRequired
    @RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(TTool tool, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                              @RequestParam(value = "o", required = true, defaultValue = "TOOL_NO desc") String order,
                              ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                              JedaUser currentUser,String select,String detailTr)throws Exception{
        try {
            mv=businessOperationQuery(tool,request,pageIndex,order,pageSize,mv,currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null!=select && "1".equals(select)){
            mv.addObject("select",select);
            mv.addObject("detailTr",detailTr);
        }
        mv.setViewName("resource/tool/index");
        mv.addObject("pi",pageIndex);
        return mv;
    }
    @Transactional
    private ModelAndView businessOperationQuery(TTool tool, HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser) {
        TToolExample example = Constants.getSessionObject(TToolExample.class, request.getSession(true), "TToolExample", true);
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
        TToolExample.Criteria criteria = example.createCriteria();

//        if (tProductBase.getProNo() != null&& !"".equals(tProductBase.getProNo() ))
//            criteria.andProNoLike("%" + tProductBase.getProNo() + "%");
//        if (tProductBase.getProName() != null&& !"".equals(tProductBase.getProName() ))
//            criteria.andProNameLike("%" + tProductBase.getProName() + "%");
//        if (tProductBase.getProType() != null&& !"".equals(tProductBase.getProType() ))
//            criteria.andProTypeEqualTo(tProductBase.getProType());

        criteria.andIsValidEqualTo("0");//查询所有有效的投资产品

        List<TTool> list = toolService.selectByExample(example);
        for (TTool tTool : list) {
            TExpenseDetail detail = expenseService.sumByTool(tTool.getToolNo());
            tTool.setHisCount(Integer.parseInt(detail.getQuantity().toString()));
            tTool.setHisAmt(detail.getTotalPrice());
        }


        //分页
        long totalCount = toolService.countByExample(example);
        long pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        mv.addObject("pagecount", pageCount);
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageIndex == 0 ? 1 : pageIndex);
        mv.addObject("list",list);
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/createTool", method = RequestMethod.GET)
    public ModelAndView createTool(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser)throws Exception{
        String orgId=currentUser.getOrgId().substring(0,2);
        String id = tSysSequenceService.getPrimaryKey(TSysSequenceService.RECEIPTS,orgId);
        TTool tool=new TTool();
        tool.setToolNo(id);
        tool.setApplyOpr(currentUser.getUserName());
        tool.setApplyDate(new Date());
        tool.setStock(new BigDecimal(0));
        mv.addObject("tool",tool);
        mv.addObject("edit",0);
        mv.setViewName("resource/tool/createTool");
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/updateTool", method = RequestMethod.GET)
    public ModelAndView updateTool(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TTool tool,String edit)throws Exception{
        if(null!=tool && null!=tool.getToolNo()){
            tool=toolService.selectByPrimaryKey(tool.getToolNo());
            mv.addObject("tool",tool);
            if(null!=edit){
                mv.addObject("edit",edit);
            }else{
                mv.addObject("edit",1);
            }
            mv.setViewName("resource/tool/createTool");
        }else{
            mv.addObject("msg","此合作商不存在！");
            mv.setViewName("resource/tool/index");
        }
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveCustomer(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TTool tool,String edit)throws Exception{
        int result=0;
        try{
            if(null!=tool.getToolNo() && !"".equals(tool.getToolNo())){
                if(null!=edit && "1".equals(edit)){
                    result=toolService.update(tool);
                }else{
                    tool.setApplyOpr(currentUser.getUserId());
                    tool.setApplyDate(new Date());
                    tool.setIsValid("0");
                    result=toolService.insert(tool);
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
    public void beforeDelete(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TTool tool)throws Exception{
        int result=0;
        try{
            if(null!=tool.getToolNo() && !"".equals(tool.getToolNo())){
                tool=toolService.selectByPrimaryKey(tool.getToolNo());
                if(null!=tool){
                    result= Integer.parseInt(tool.getStock().toString());
                }
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }

    @LoginRequired
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TTool tool)throws Exception{
        int result=0;
        try{
            if(null!=tool.getToolNo() && !"".equals(tool.getToolNo())){
                tool.setIsValid("1");
                result=toolService.update(tool);
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }
}
