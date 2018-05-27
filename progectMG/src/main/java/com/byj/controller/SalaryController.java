package com.byj.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import bas.jeda.dao.JedaUserExample;
import com.byj.dao.*;
import com.byj.daoExtend.ProjectVo;
import com.byj.daoExtend.UserVo;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/24 0024.
 */
@Controller
@RequestMapping(value = "salary")
public class SalaryController extends JedaController {
    @Autowired
    private TSysSequenceService tSysSequenceService;
    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;
    @Autowired
    private SalaryService salaryService;

    @LoginRequired
    @RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(TSalary salary, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                              @RequestParam(value = "o", required = true, defaultValue = "USER_ID asc") String order,
                              ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                              JedaUser currentUser, String select)throws Exception{
        try {
            pageSize=5;
            mv=businessOperationQuery(salary,request,pageIndex,order,pageSize,mv,currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("salary/index");
        return mv;
    }

    @Transactional
    private ModelAndView businessOperationQuery(TSalary salary, HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser) {
        JedaUserExample userExample = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "JedaUserExample", true);
        if (!"".equals(order))
            userExample.setOrderByClause(order);
        JedaUserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andUserIdNotEqualTo("admin");
        List<UserVo> userlist = salaryService.selectByExample(userExample);
        Date today = new Date();
        DateFormats formats=new DateFormats();
        String todayString=formats.dateToStrD(today);
        int year=Integer.parseInt(todayString.split("-")[0]);
        int month=Integer.parseInt(todayString.split("-")[1])-1;
        int day=Integer.parseInt(todayString.split("-")[2]);
        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month,1);
        Calendar endCalendar=Calendar.getInstance();
        endCalendar.set(year,month+1,1);
        Long startTime=calendar.getTimeInMillis();
        Long endTime=endCalendar.getTimeInMillis();
        Long oneDay=(long) (1000*60*60*24);
        List<Date> days=new ArrayList<Date>();
        Long time=startTime;
        while (time < endTime) {
            Date d = new Date(time);
            days.add(d);
            time += oneDay;
        }
        for (UserVo userVo : userlist) {
            List<TSalary> salaries=new ArrayList<TSalary>();
            for (Date date : days) {
                TSalaryExample example=new TSalaryExample();
                TSalaryExample.Criteria criteria = example.createCriteria();
                criteria.andIsValidEqualTo("0").andUserIdEqualTo(userVo.getUserId()).andSalaryDateEqualTo(date);
                List<TSalary> tSalaryList=salaryService.selectByExample(example);
                if(tSalaryList.size()>0){
                    salaries.add(tSalaryList.get(0));
                }else{
                    TSalary salary1=new TSalary();
                    salary1.setSalaryDate(date);
                    salaries.add(salary1);
                }
            }
            userVo.setSalaryList(salaries);

        }

        mv.addObject("users",userlist);
        mv.addObject("today",new Date());
        return mv;
    }

    @LoginRequired
    @RequestMapping(value = "/recordSalary", method = RequestMethod.GET)
    public ModelAndView recordSalary(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser,TCustomer customer,String edit)throws Exception{
        int result=0;
        try{
            JedaUserExample userExample = Constants.getSessionObject(JedaUserExample.class, request.getSession(true), "JedaUserExample", true);
            userExample.setOrderByClause("USER_ID ASC");
            JedaUserExample.Criteria userCriteria = userExample.createCriteria();
            userCriteria.andUserIdNotEqualTo("admin");
            List<UserVo> userlist = salaryService.selectByExample(userExample);
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            Date date = calendar.getTime();
            List<TSalary> salaries=new ArrayList<TSalary>();
            for (UserVo userVo : userlist) {
                TSalaryExample example = new TSalaryExample();
                example.setOrderByClause("SALARY_DATE DESC");
                TSalaryExample.Criteria criteria = example.createCriteria();
                criteria.andSalaryDateLessThanOrEqualTo(date).andIsValidEqualTo("0").andUserIdEqualTo(userVo.getUserId());
                List<TSalary> salaryList=salaryService.selectByExample(example);
                if(salaryList.size()>0){
                    salaries.add(salaryList.get(0));
                }else{
                    TSalary salary=new TSalary();
                    salary.setUserId(userVo.getUserId());
                    salaries.add(salary);
                }
            }
            mv.addObject("salaries",salaries);
            mv.addObject("today",new Date());
            mv.setViewName("salary/recordSalary");
        }catch (Exception  e){
            e.printStackTrace();
        }
        return mv;
    }
    @LoginRequired
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveCustomer(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, JedaUser currentUser, ProjectVo userVo)throws Exception{
        int result=0;
        try{
            if(null!=userVo && null!=userVo.getSalaryList()){
                List<TSalary> salaries=userVo.getSalaryList();
                if(salaries.size()>0){
                    for (TSalary salary : salaries) {
                        if(null!=salary){
                            TSalaryExample example = new TSalaryExample();
                            TSalaryExample.Criteria criteria = example.createCriteria();
                            criteria.andSalaryDateEqualTo(salary.getSalaryDate()).andIsValidEqualTo("0").andUserIdEqualTo(salary.getUserId());
                            List<TSalary> salaryList=salaryService.selectByExample(example);
                            if(salaryList.size()>0){
                                salary.setId(salaryList.get(0).getId());
                                salaryService.updateByPrimaryKey(salary);
                            }else{
                                //新增
                                salary.setIsValid("0");
                                salary.setStatus("1");
                                salary.setApplyDate(new Date());
                                salary.setApplyOpr(currentUser.getUserId());
                                salaryService.insert(salary);
                            }
                        }
                    }
                    result=1;
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

}
