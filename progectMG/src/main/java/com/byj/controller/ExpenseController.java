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
    public ModelAndView indexTool(TSalary salary, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                                  @RequestParam(value = "o", required = true, defaultValue = "EXPENSE_DATE DESC") String order,
                                  ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                                  JedaUser currentUser, String select) throws Exception {
        try {
            mv = businessOperationQueryTool(salary, request, pageIndex, order, pageSize, mv, currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("expense/tool/index");
        return mv;
    }

    @Transactional
    private ModelAndView businessOperationQueryTool(TSalary salary, HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser) throws ParseException {
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
        criteria.andIsValidEqualTo("0").andExpenseTypeEqualTo("2");

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
    @RequestMapping(value = "/saveTool", method = RequestMethod.POST)
    public void saveTool(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,ExpenseVo expense,String edit)throws Exception{
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
                        if(null!=detail.getToolNo()){
                            detail.setExpenseType("2");
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
}
