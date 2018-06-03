package com.byj.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import bas.jeda.dao.JedaUserExample;
import com.byj.dao.*;
import com.byj.daoExtend.ExpenseVo;
import com.byj.daoExtend.ProjectVo;
import com.byj.daoExtend.UserVo;
import com.byj.service.ExpenseService;
import com.byj.service.SalaryService;
import com.nantian.service.sysmgn.TSysSequenceService;
import com.nantian.utils.Constants;
import com.nantian.utils.DateFormats;
import net.sf.json.JSONArray;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */
@Controller
@RequestMapping(value = "expense")
public class ExpenseController extends JedaController {
    @Autowired
    private TSysSequenceService tSysSequenceService;
    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private ExpenseService expenseService;

    @LoginRequired
    @RequestMapping(value = "/indexSalary", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView indexSalary(TSalary salary, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                                    @RequestParam(value = "o", required = true, defaultValue = "USER_ID asc") String order,
                                    ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                                    JedaUser currentUser, String select) throws Exception {
        try {
            mv = businessOperationQuerySal(salary, request, pageIndex, order, pageSize, mv, currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("expense/salary/index");
        return mv;
    }

    @Transactional
    private ModelAndView businessOperationQuerySal(TSalary salary, HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser) throws ParseException {
        JedaUserExample userExample = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "JedaUserExample", true);
        if (!"".equals(order))
            userExample.setOrderByClause(order);
        JedaUserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andUserIdNotEqualTo("admin");
        List<UserVo> userlist = salaryService.selectByExample(userExample);
        Date today = new Date();
        SimpleDateFormat sdfM = new SimpleDateFormat("MM");
        DateFormats formats = new DateFormats();
        String todayString = formats.dateToStrD(today);
        int year = Integer.parseInt(todayString.split("-")[0]);
        List<String> dateList = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            String month = String.valueOf(i);
            if (i < 10) {
                month = "0" + i;
            }
            String date = year + "-" + month;
            dateList.add(date);
        }
        for (UserVo userVo : userlist) {
            List<TSalary> salaries = new ArrayList<TSalary>();
            for (String date : dateList) {
                TSalary sal = salaryService.sumSalaryByMonth(userVo.getUserId(), date);
                if (null == sal) {
                    sal = new TSalary();
                    sal.setUserId(userVo.getUserId());
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sdf.parse(date + "-" + 1);
                String month = sdfM.format(d);
                sal.setSalaryDate(d);
                sal.setStrDate(date);
                sal.setStrDate(month);
                salaries.add(sal);
            }
            userVo.setSalaryList(salaries);
        }
        Date d = new Date();
        String month = sdfM.format(d);
        mv.addObject("users", userlist);
        mv.addObject("today", d);
        mv.addObject("month", month);
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/paySalary", method = RequestMethod.POST)
    public void paySalary(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser, ProjectVo userVo) throws Exception {
        int result = 0;
        try {
            if (null != userVo && null != userVo.getSalaryList()) {
                List<TSalary> salaries = userVo.getSalaryList();
                if (salaries.size() > 0) {
                    for (TSalary salary : salaries) {
                        if (null != salary && null != salary.getUserId() && null != salary.getStrDate() && null != salary.getSalary()) {
                            TSalaryExample example = new TSalaryExample();
                            TSalaryExample.Criteria criteria = example.createCriteria();
                            criteria.andUserIdEqualTo(salary.getUserId()).andIsValidEqualTo("0");
                            TSalary sal = new TSalary();
                            sal.setStatus("2");
                            sal.setPayDate(new Date());
                            sal.setPayOpr(currentUser.getUserId());
                            salaryService.updateByExampleMonth(sal, example, salary.getStrDate());
                        }
                    }
                    result = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnResult(response, result);
    }

    /**
     * 返回ajax结果
     *
     * @param response
     * @param result
     */
    private void returnResult(HttpServletResponse response, int result) {
        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @LoginRequired
    @RequestMapping(value = "/indexTool", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView indexTool(TExpenseDetail detail, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                                  @RequestParam(value = "o", required = true, defaultValue = "EXPENSE_DATE DESC") String order,
                                  ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                                  JedaUser currentUser, String select) throws Exception {
        try {
            detail.setExpenseType("2");
            mv = businessOperationQueryExpense(detail, request, pageIndex, order, pageSize, mv, currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("expense/tool/index");
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/expenseTool", method = RequestMethod.GET)
    public ModelAndView expenseTool(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,String edit,String id)throws Exception{
        if(null!=edit && null!=id){
            TExpenseDetail detail =  expenseService.selectByPrimaryKey(new BigDecimal(id));
            List<TExpenseDetail> details= new ArrayList<TExpenseDetail>();
            details.add(detail);
            mv.addObject("details",details);
        }
        mv.addObject("edit",edit);
        mv.setViewName("expense/tool/expenseTool");
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/saveExpense", method = RequestMethod.POST)
    public void saveExpense(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,ExpenseVo expense,String edit,String type)throws Exception{
        int result=0;
        try {
            if(null!=expense && null!=expense.getDetails()){
                List<TExpenseDetail> details = expense.getDetails();
                if("1".equals(edit)){
                    for (TExpenseDetail detail : details) {
                        if(null!=detail.getId()){
                            expenseService.update(detail);
                        }
                    }
                }else{
                    for (TExpenseDetail detail : details) {
                        if(null!=detail.getExpenseDate()){
                            detail.setExpenseType(type);
                            detail.setIsValid("0");
                            detail.setApplyDate(new Date());
                            detail.setApplyOpr(currentUser.getUserId());
                            expenseService.insert(detail);
                        }
                    }
                }
                result=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnResult(response,result);
    }

    @LoginRequired
    @RequestMapping(value = "/indexOther", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView indexOther(TExpenseDetail detail, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                                  @RequestParam(value = "o", required = true, defaultValue = "EXPENSE_DATE DESC") String order,
                                  ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                                  JedaUser currentUser, String select) throws Exception {
        try {
            detail.setExpenseType("4");
            mv = businessOperationQueryExpense(detail, request, pageIndex, order, pageSize, mv, currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("expense/other/index");
        return mv;
    }
    @Transactional
    private ModelAndView businessOperationQueryExpense(TExpenseDetail detail, HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser) throws ParseException {
        TExpenseDetailExample example = Constants.getSessionObject(TExpenseDetailExample.class, request.getSession(true), "TExpenseDetailExample", true);
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
        TExpenseDetailExample.Criteria criteria = example.createCriteria();
        criteria.andIsValidEqualTo("0").andExpenseTypeEqualTo(detail.getExpenseType());

        List<TExpenseDetail> list = expenseService.selectByExample(example);

        //分页
        long totalCount = expenseService.countByExample(example);
        long pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        mv.addObject("pagecount", pageCount);
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageIndex == 0 ? 1 : pageIndex);
        mv.addObject("list", list);
        return mv;
    }
    @Transactional
    private ModelAndView businessOperationQueryElec(TExpenseDetail detail, HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser) throws ParseException {
        TExpenseDetailExample example = Constants.getSessionObject(TExpenseDetailExample.class, request.getSession(true), "TExpenseDetailExample", true);
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
        TExpenseDetailExample.Criteria criteria = example.createCriteria();
        criteria.andIsValidEqualTo("0").andExpenseTypeEqualTo(detail.getExpenseType()).andElectricTypeEqualTo("1");
        List<TExpenseDetail> list = expenseService.selectByExample(example);
        for (TExpenseDetail tExpenseDetail : list) {
            tExpenseDetail.setHBquantity(tExpenseDetail.getQuantity());
            tExpenseDetail.setHBtotalPrice(tExpenseDetail.getTotalPrice());
            TExpenseDetailExample eExample= new TExpenseDetailExample();
            TExpenseDetailExample.Criteria eCriteria = eExample.createCriteria();
            eCriteria.andIsValidEqualTo("0").andElectricTypeEqualTo("2").andExpenseDateEqualTo(tExpenseDetail.getExpenseDate())
                    .andAmmeterTotalEqualTo(tExpenseDetail.getAmmeterTotal()).andAmmeterHbEqualTo(tExpenseDetail.getAmmeterHb());
            List<TExpenseDetail> listYC = expenseService.selectByExample(eExample);
            if(listYC.size()>0){
                tExpenseDetail.setYCquantity(listYC.get(0).getQuantity());
                tExpenseDetail.setYCtotalPrice(listYC.get(0).getTotalPrice());
            }
        }

        //分页
        long totalCount = expenseService.countByExample(example);
        long pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        mv.addObject("pagecount", pageCount);
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageIndex == 0 ? 1 : pageIndex);
        mv.addObject("list", list);
        return mv;
    }
    @LoginRequired
    @RequestMapping(value = "/expenseOther", method = RequestMethod.GET)
    public ModelAndView expenseOther(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,String edit,String id)throws Exception{
        if(null!=edit && null!=id){
            TExpenseDetail detail =  expenseService.selectByPrimaryKey(new BigDecimal(id));
            List<TExpenseDetail> details= new ArrayList<TExpenseDetail>();
            details.add(detail);
            mv.addObject("details",details);
        }
        mv.addObject("edit",edit);
        mv.setViewName("expense/other/expenseOther");
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,String id)throws Exception{
        int result=0;
        try {
            if(null!=id && !"".equals(id)){
                expenseService.delete(new BigDecimal(id));
                result=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnResult(response,result);
    }

    @LoginRequired
    @RequestMapping(value = "/indexElec", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView indexElec(TExpenseDetail detail, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                                  @RequestParam(value = "o", required = true, defaultValue = "EXPENSE_DATE DESC") String order,
                                  ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                                  JedaUser currentUser, String select) throws Exception {
        try {
            detail.setExpenseType("1");
            mv = businessOperationQueryElec(detail, request, pageIndex, order, pageSize, mv, currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("expense/electric/index");
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/expenseElec", method = RequestMethod.GET)
    public ModelAndView expenseElec(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,String edit,String id)throws Exception{
        if(null!=edit && null!=id){
            TExpenseDetail detail =  expenseService.selectByPrimaryKey(new BigDecimal(id));
            TExpenseDetailExample example = new TExpenseDetailExample();
            TExpenseDetailExample.Criteria criteria=example.createCriteria();
            criteria.andAmmeterTotalEqualTo(detail.getAmmeterTotal()).andAmmeterHbEqualTo(detail.getAmmeterHb()).andIsValidEqualTo("0");
            List<TExpenseDetail> details=expenseService.selectByExample(example);
            mv.addObject("detail",detail);
            if(details.size()>0){
                for (TExpenseDetail tExpenseDetail : details) {
                    if("1".equals(tExpenseDetail.getElectricType())){
                        mv.addObject("HBdetail",tExpenseDetail);
                    }else{
                        mv.addObject("YCdetail",tExpenseDetail);
                    }
                }
            }
            mv.addObject("id",id);
        }else{
            TExpenseDetail detailMax = expenseService.selectMaxElec();
            TExpenseDetail detail=new TExpenseDetail();
            detail.setUnitPrice(detailMax.getUnitPrice());
            mv.addObject("lastDate",detailMax.getExpenseDate());
            mv.addObject("detail",detail);
        }
        mv.addObject("edit",edit);
        mv.setViewName("expense/electric/expenseElectric");
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/calcuteElec", method = RequestMethod.GET)
    public void calcuteElec(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, TExpenseDetail detail,String edit,String id)throws Exception{
        if(null!=detail.getAmmeterTotal() && null!=detail.getAmmeterHb() && null!=detail.getUnitPrice()){
            BigDecimal ammeterTotal=detail.getAmmeterTotal();
            BigDecimal ammeterHB=detail.getAmmeterHb();
            BigDecimal unitPrice=detail.getUnitPrice();
            TExpenseDetail detailMax;
            if(null!=edit && "1".equals(edit)){
                TExpenseDetail oldDetail=expenseService.selectByPrimaryKey(new BigDecimal(id));
                TExpenseDetailExample example=new TExpenseDetailExample();
                example.setOrderByClause("EXPENSE_DATE DESC");
                TExpenseDetailExample.Criteria criteria=example.createCriteria();
                criteria.andIsValidEqualTo("0").andIdLessThan(new BigDecimal(id)).andElectricTypeEqualTo(oldDetail.getElectricType());
                List<TExpenseDetail> detailList=expenseService.selectByExample(example);
                detailMax=detailList.get(0);
            }else{
                detailMax = expenseService.selectMaxElec();
            }
            BigDecimal lastTotal=detailMax.getAmmeterTotal();
            BigDecimal lastHB=detailMax.getAmmeterHb();
            if(ammeterTotal.compareTo(lastTotal)>0){
                ammeterTotal=ammeterTotal.subtract(lastTotal);
            }
            if(ammeterHB.compareTo(lastHB)>0){
                ammeterHB=ammeterHB.subtract(lastHB);
            }
            BigDecimal ammeterYC=ammeterTotal.subtract(ammeterHB);
            TExpenseDetail HBdetail= new TExpenseDetail();
            HBdetail.setQuantity(ammeterHB);
            HBdetail.setTotalPrice(ammeterHB.multiply(unitPrice));
            TExpenseDetail YCdetail= new TExpenseDetail();
            YCdetail.setQuantity(ammeterYC);
            YCdetail.setTotalPrice(ammeterYC.multiply(unitPrice));
            List<TExpenseDetail> list=new ArrayList<TExpenseDetail>();
            list.add(HBdetail);
            list.add(YCdetail);
            response.getWriter().print(JSONArray.fromObject(list));
        }
    }

    @LoginRequired
    @RequestMapping(value = "/saveElec", method = RequestMethod.POST)
    public void saveElec(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TExpenseDetail detail,String edit)throws Exception{
        int result=0;
        try {
            if(null!=detail){
                List<TExpenseDetail> details=new ArrayList<TExpenseDetail>();
                TExpenseDetail HBdetail=new TExpenseDetail();
                HBdetail.setQuantity(detail.getHBquantity());
                HBdetail.setTotalPrice(detail.getHBtotalPrice());
                HBdetail.setElectricType("1");
                TExpenseDetail YCdetail=new TExpenseDetail();
                YCdetail.setQuantity(detail.getYCquantity());
                YCdetail.setTotalPrice(detail.getYCtotalPrice());
                YCdetail.setElectricType("2");
                details.add(HBdetail);
                details.add(YCdetail);
                for (TExpenseDetail tExpenseDetail : details) {
                    tExpenseDetail.setExpenseDate(detail.getExpenseDate());
                    tExpenseDetail.setUnitPrice(detail.getUnitPrice());
                    tExpenseDetail.setAmmeterTotal(detail.getAmmeterTotal());
                    tExpenseDetail.setAmmeterHb(detail.getAmmeterHb());
                    tExpenseDetail.setIsValid("0");
                    tExpenseDetail.setExpenseType("1");
                    tExpenseDetail.setApplyOpr(currentUser.getUserId());
                    tExpenseDetail.setApplyDate(new Date());
                    expenseService.insert(tExpenseDetail);
                }
                result=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnResult(response,result);
    }

    @LoginRequired
    @RequestMapping(value = "/beforeEditElec", method = RequestMethod.GET)
    public void beforeEditElec(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,String id)throws Exception{
        int result=0;
        try {
            if(null!=id){
                TExpenseDetail oldDetail=expenseService.selectByPrimaryKey(new BigDecimal(id));
                TExpenseDetailExample example=new TExpenseDetailExample();
                TExpenseDetailExample.Criteria criteria=example.createCriteria();
                criteria.andIsValidEqualTo("0").andElectricTypeEqualTo(oldDetail.getElectricType()).andIdGreaterThan(new BigDecimal(id));
                List<TExpenseDetail> detailList=expenseService.selectByExample(example);
                if(detailList.size()>0){
                    result=1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnResult(response,result);
    }
    @LoginRequired
    @RequestMapping(value = "/deleteElec", method = RequestMethod.GET)
    public void deleteElec(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,String id)throws Exception{
        int result=0;
        try {
            if(null!=id){
                TExpenseDetail oldDetail=expenseService.selectByPrimaryKey(new BigDecimal(id));
                TExpenseDetailExample example=new TExpenseDetailExample();
                TExpenseDetailExample.Criteria criteria=example.createCriteria();
                criteria.andIsValidEqualTo("0").andExpenseDateEqualTo(oldDetail.getExpenseDate())
                        .andAmmeterTotalEqualTo(oldDetail.getAmmeterTotal()).andAmmeterHbEqualTo(oldDetail.getAmmeterHb());
                List<TExpenseDetail> detailList=expenseService.selectByExample(example);
                if(detailList.size()>0){
                    for (TExpenseDetail detail : detailList) {
                        expenseService.delete(detail.getId());
                    }
                    result=1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnResult(response,result);
    }

}
