/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;
 
 
import bas.jeda.core.SLogService;
import bas.jeda.dao.SLog;
import bas.jeda.dao.SLogExample;
import com.nantian.utils.StringUtils; 
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author zhixiangfei
 */
@Controller
public class SlogController  extends JedaController{
     @Autowired
    public PageCutter pageCutter;
     @Autowired
     public SLogService sLogService;
    @Value("#{configProperties['PAGESIZE']}")
    private int PAGESIZE;
    @LoginRequired
    @RequestMapping(value = "/jeda/slog/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletResponse response,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/jeda/logger/index");
        return mv;
    }
     @LoginRequired
    @RequestMapping(value = "/jeda/slog/query", method = RequestMethod.POST)
     public ModelAndView query(HttpServletRequest request, HttpServletResponse response,
             @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
             @RequestParam(value = "startdate", required = true, defaultValue = "") String startdate,
             @RequestParam(value = "enddate", required = true, defaultValue = "") String enddate,
             @RequestParam(value = "userid", required = true, defaultValue = "") String userid,
             @RequestParam(value = "oappmenu", required = true, defaultValue = "") String oappmenu)throws Exception{
         if (pageIndex <= 1) {
            request.getSession().removeAttribute("startdate");
            request.getSession().removeAttribute("enddate");
            request.getSession().removeAttribute("userid");
            request.getSession().removeAttribute("oappmenu");
            request.getSession().setAttribute("startdate", startdate);
            request.getSession().setAttribute("enddate", enddate);
            request.getSession().setAttribute("userid", userid);
            request.getSession().setAttribute("oappmenu", oappmenu);
         }
         return this.queryOut(pageIndex, PAGESIZE, startdate, enddate, userid, oappmenu);
     }
      @LoginRequired
     @RequestMapping(value = "/jeda/slog/query", method = RequestMethod.GET)
     public ModelAndView queryGet(HttpServletRequest request, HttpServletResponse response,
             @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,
             @RequestParam(value = "startdate", required = true, defaultValue = "") String startdate,
             @RequestParam(value = "enddate", required = true, defaultValue = "") String enddate,
             @RequestParam(value = "userid", required = true, defaultValue = "") String userid,
             @RequestParam(value = "oappmenu", required = true, defaultValue = "") String oappmenu)throws Exception{
         if(pageIndex>1){
             startdate   = (String)request.getSession().getAttribute("startdate");
             enddate   = (String)request.getSession().getAttribute("enddate");
             userid   = (String)request.getSession().getAttribute("userid");
             oappmenu   = (String)request.getSession().getAttribute("oappmenu");
         }
         return this.queryOut(pageIndex, PAGESIZE, startdate, enddate, userid, oappmenu);
     }
     public ModelAndView queryOut( int pageIndex, int pageSize,  String startdate,  String enddate,  String userid,  String oappmenu)throws Exception{

         ModelAndView mv = new ModelAndView();
         SLogExample example=new SLogExample();
         SLogExample.Criteria criteria=example.createCriteria();
         if (oappmenu != null && !oappmenu.equals("")) {
             criteria.andOappmenuEqualTo(oappmenu);
         }
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         if (!"".equals(startdate) && startdate != null && !"".equals(enddate) && enddate != null){
             java.util.Date start = sdf.parse(startdate);
             java.util.Date end = sdf.parse(enddate);
             criteria.andOtimeBetween(start, end);
         }
         if (!"".equals(startdate) && startdate != null && ("".equals(enddate) || enddate == null)) {
             java.util.Date start = sdf.parse(startdate);
             criteria.andOtimeGreaterThanOrEqualTo(start);
         }
         if (!"".equals(enddate) && enddate != null && ("".equals(startdate) || startdate == null)) {
             java.util.Date end = sdf.parse(enddate);
             criteria.andOtimeLessThanOrEqualTo(end);
         }
         if (userid != null && !userid.equals("")) {
             criteria.andUseridLike(userid);
         }
         example.setOrderByClause("otime desc");
         List<SLog> loglist=sLogService.querylogbyexample(example);
         example.clear();
         int pc=0;
         if (loglist.size() != 0) {
            /*没有返回结果*/
             pageCutter.setPageIndex(pageIndex);
             pageCutter.setPageSize(pageSize);
             pageCutter.setUs(loglist);
             pc = pageCutter.pageCount();
             loglist = this.pageCutter.cutList();
         }
         mv.addObject("pagecount", pc);
         mv.addObject("pagesize", pageSize);
         mv.addObject("pageindex", pageCutter.getPageIndex());
         mv.addObject("lSlog",loglist);
        mv.setViewName("/jeda/logger/index");        
        return mv;
    }
}
   
