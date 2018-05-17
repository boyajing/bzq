package com.byj.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import com.byj.dao.TProjectBase;
import com.byj.dao.TProjectBaseExample;
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
    private TSysSequenceService tSysSequenceService;
    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;

    @LoginRequired
    @RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(TProjectBase project, @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
                              @RequestParam(value = "o", required = true, defaultValue = "PROJECT_NO desc") String order,
                              ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
                              JedaUser currentUser)throws Exception{
        try {
            mv=businessOperationQuery(project,request,pageIndex,order,pageSize,mv,currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mv.setViewName("project/new/index");
        mv.addObject("pi",pageIndex);
        return mv;
    }
    @Transactional
    private ModelAndView businessOperationQuery(TProjectBase project,  HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv, JedaUser currentUser) {
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
}
