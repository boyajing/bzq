package com.byj.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import com.byj.dao.TCustomer;
import com.byj.dao.TCustomerExample;
import com.byj.dao.TProjectBase;
import com.byj.dao.TProjectBaseExample;
import com.byj.service.CustomerService;
import com.byj.service.ProjectService;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/15 0015.
 */
@Controller
@RequestMapping(value = "customer")
public class CustomerController extends JedaController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TSysSequenceService tSysSequenceService;
    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;

    @LoginRequired
    @RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(TCustomer customer, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                                    @RequestParam(value = "o", required = true, defaultValue = "CUSTOMER_NO desc") String order,
                                    ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                                    JedaUser currentUser,String select)throws Exception{
        try {
            mv=businessOperationQuery(customer,request,pageIndex,order,pageSize,mv,currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null!=select && "1".equals(select)){
            mv.addObject("select",select);
        }
        mv.setViewName("resource/customer/index");
        mv.addObject("pi",pageIndex);
        return mv;
    }
    @Transactional
    private ModelAndView businessOperationQuery(TCustomer customer, HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser) {
        TCustomerExample example = Constants.getSessionObject(TCustomerExample.class, request.getSession(true), "TCustomerExample", true);
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
        TCustomerExample.Criteria criteria = example.createCriteria();

//        if (tProductBase.getProNo() != null&& !"".equals(tProductBase.getProNo() ))
//            criteria.andProNoLike("%" + tProductBase.getProNo() + "%");
//        if (tProductBase.getProName() != null&& !"".equals(tProductBase.getProName() ))
//            criteria.andProNameLike("%" + tProductBase.getProName() + "%");
//        if (tProductBase.getProType() != null&& !"".equals(tProductBase.getProType() ))
//            criteria.andProTypeEqualTo(tProductBase.getProType());

        criteria.andIsValidEqualTo("0");//查询所有有效的投资产品

        List<TCustomer> list = customerService.selectByExample(example);
        for (TCustomer tCustomer : list) {
            TProjectBase projectBase=projectService.countHisByCustomer(tCustomer.getCustomerNo());
            tCustomer.setHisCount(projectBase.getHisCount());
            tCustomer.setHisAmt(projectBase.getHisAmt());
        }
        //分页
        long totalCount = customerService.countByExample(example);
        long pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        mv.addObject("pagecount", pageCount);
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageIndex == 0 ? 1 : pageIndex);
        mv.addObject("list",list);
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
    public ModelAndView createCustomer(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser)throws Exception{
        String orgId=currentUser.getOrgId().substring(0,2);
        String id = tSysSequenceService.getPrimaryKey(TSysSequenceService.RECEIPTS,orgId);
        TCustomer customer=new TCustomer();
        customer.setCustomerNo(id);
        customer.setApplyDate(new Date());
        customer.setApplyOpr(currentUser.getUserId());
        mv.addObject("customer",customer);
        mv.addObject("edit",0);
        mv.setViewName("resource/customer/createCustomer");
        return mv;
    }
    @LoginRequired
    @RequestMapping(value = "/updateCustomer", method = RequestMethod.GET)
    public ModelAndView updateCustomer(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TCustomer customer,String edit)throws Exception{
        if(null!=customer && null!=customer.getCustomerNo()){
            customer=customerService.selectByPrimaryKey(customer.getCustomerNo());
            mv.addObject("customer",customer);
            if(null!=edit){
                mv.addObject("edit",edit);
            }else{
                mv.addObject("edit",1);
            }
            mv.setViewName("resource/customer/createCustomer");
        }else{
            mv.addObject("msg","此合作商不存在！");
            mv.setViewName("resource/customer/index");
        }
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveCustomer(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TCustomer customer,String edit)throws Exception{
        int result=0;
        try{
            if(null!=customer.getCustomerNo() && !"".equals(customer.getCustomerNo())){
                if(null!=edit && "1".equals(edit)){
                    result=customerService.update(customer);
                }else{
                    customer.setApplyOpr(currentUser.getUserId());
                    customer.setApplyDate(new Date());
                    customer.setIsValid("0");
                    result=customerService.insert(customer);
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
    @RequestMapping(value = "/countProjectByCustomer", method = RequestMethod.GET)
    public void countProjectByCustomer(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TCustomer customer)throws Exception{
        int result=0;
        try{
            if(null!=customer.getCustomerNo() && !"".equals(customer.getCustomerNo())){
                TProjectBaseExample example=new TProjectBaseExample();
                TProjectBaseExample.Criteria criteria=example.createCriteria();
                criteria.andCustomerNoEqualTo(customer.getCustomerNo()).andIsValidEqualTo("0");
                result=(int)projectService.countByExample(example);
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }

    @LoginRequired
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TCustomer customer)throws Exception{
        int result=0;
        try{
            if(null!=customer.getCustomerNo() && !"".equals(customer.getCustomerNo())){
                customer.setIsValid("1");
                result=customerService.update(customer);
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        returnResult(response,result);
    }
}
