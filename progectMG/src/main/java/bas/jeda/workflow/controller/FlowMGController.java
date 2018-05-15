package bas.jeda.workflow.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import bas.jeda.workflow.FlowHandler;
import bas.jeda.workflow.ProcessTurnCore;
import bas.jeda.workflow.UserTaskForList;
//import bas.jeda.workflow.service.MeetingFlowService;
//import com.nantian.dao.TMeetingIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程管理类
 */
@RequestMapping(value = "/flowMG")
@Controller
public class FlowMGController extends JedaController {
    /**
     * 流程处理器 {@link FlowHandler}
     */
    @Autowired
    private FlowHandler flowHandler;
    /**
     * 流转控制器
     */
    @Autowired
    private ProcessTurnCore ptc;

    @Value("#{configProperties['windowpath']}")
    private String windowpath;

    @Value("#{configProperties['PAGESIZE']}")
    private int PAGESIZE;
//    @Autowired
//    private MeetingFlowService meetingFlowService;

    /**
     * 根据用户ID返回待办任务
     *
     * @param currentUser
     *
     * @return json实体类{@link bas.jeda.work.UserTask}
     */
//    @LoginRequired
//    @RequestMapping(value = "/todolist", method = RequestMethod.GET)
//    public ModelAndView todolist_get(JedaUser currentUser, HttpServletRequest request,
//                                     @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
//                                     @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize) {
//        ModelAndView mv = new ModelAndView();
//        //在此处加入分页，不然流程太慢
//        int pagestart = 0;
//        if(pageSize == 0 || pageSize < 0){
//            pageSize = this.PAGESIZE;
//        }
//        if(pi > 0){
//            pagestart = (pi - 1) * pageSize ;
//        }else {
//            pi = 1;
//        }
//
//        Long pagecount = this.flowHandler.countTODOTask("") / pageSize +1;
//        List<UserTaskForList> ts = this.flowHandler.getTODOtaskList("",pagestart, pageSize);
//
//        //这里判断是议题表中已经有该任务 把该任务移除
//        for (int i = ts.size() - 1; i >= 0; i--) {
//            if (ts.get(i).getDocConfig() == null || (!"true".equals(ts.get(i).getDocConfig().getMeetingtask()))) {
//
//            }else{
//                //判断议题信息中有这条数据
//                TMeetingIssue tMeetingIssue = meetingFlowService.findTMeetingIssue(ts.get(i).getTaskID());
//                if(tMeetingIssue != null){
//                    ts.remove(i);
//                }
//
//            }
//        }
//
//        mv.addObject("pagecount",pagecount);
//        mv.addObject("tasks", ts);
//        mv.addObject("pageindex",pi);
//        mv.addObject("pageSize",pageSize);
//        mv.setViewName("flows/manage/todolist");
//        return mv;
//    }
    /**
     * 根据用户ID返回待办任务
     *
     * @param currentUser
     *
     * @return json实体类{@link bas.jeda.work.UserTask}
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/transferAssignee/{taskid}/{userid}", method = RequestMethod.POST)
    public ModelAndView transferAssignee(JedaUser currentUser,HttpServletRequest request,
                                         @PathVariable("taskid") String taskid,@PathVariable("userid") String userid) {
        ModelAndView mv = new ModelAndView();

        this.flowHandler.getAnchor().getTaskService().setAssignee(taskid,userid);
        mv.addObject("result","sucess");
        this.addSLog(request, currentUser, "流程管理", "设定用户", "taskId:" + taskid + ",userId:"+ userid);
        return mv;
    }


    @LoginRequired
    @RequestMapping(value = "/ProcessJump", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView backTask_get1(JedaUser currentUser,String taskid,String activitid,String commit) throws Exception {
        ModelAndView mv = new ModelAndView();
// 检查任务是否存在
        if(taskid ==null || "".equals(taskid)){
            mv.addObject("result","2");//
            return mv;
        }

        Map<String, Object> localVar = new HashMap<String, Object>();
        Map<String, Object> processVar = new HashMap<String, Object>();

        localVar.put("commit", commit);
        //设置本地变量
        this.flowHandler.setLocalVar(taskid, localVar);
        ptc.backProcess(taskid, activitid, processVar);
        mv.addObject("result","sucess");
        return mv;
    }
//    @LoginRequired
//    @RequestMapping(value = "/Process", method = {RequestMethod.POST,RequestMethod.GET})
//    public ModelAndView backTask(ModelAndView mv,JedaUser currentUser,HttpServletRequest request,
//                                         @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
//                                         @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize) throws Exception {
//        //在此处加入分页，不然流程太慢
//        int pagestart = 0;
//        if(pageSize == 0 || pageSize < 0){
//            pageSize = this.PAGESIZE;
//        }
//        if(pi > 0){
//            pagestart = (pi - 1) * pageSize ;
//        }else {
//            pi = 1;
//        }
//
//        Long pagecount = this.flowHandler.countTODOTask("") / pageSize +1;
//        List<UserTaskForList> ts = this.flowHandler.getTODOtaskList("",pagestart, pageSize);
//
//        //这里判断是议题表中已经有该任务 把该任务移除
//        for (int i = ts.size() - 1; i >= 0; i--) {
//            if (ts.get(i).getDocConfig() == null || (!"true".equals(ts.get(i).getDocConfig().getMeetingtask()))) {
//
//            }else{
//                //判断议题信息中有这条数据
//                TMeetingIssue tMeetingIssue = meetingFlowService.findTMeetingIssue(ts.get(i).getTaskID());
//                if(tMeetingIssue != null){
//                    ts.remove(i);
//                }
//
//            }
//        }
//
//        mv.addObject("pagecount",pagecount);
//        mv.addObject("tasks", ts);
//        mv.addObject("pageindex",pi);
//        mv.addObject("pageSize",pageSize);
//            mv.setViewName("ProcessJump");
//        return mv;
//    }

    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/transferAssign/{taskid}/{userid}", method = RequestMethod.POST)
    public ModelAndView transferAssign(JedaUser currentUser,HttpServletRequest request,
                                         @PathVariable("taskid") String taskid,@PathVariable("userid") String userid) {
        ModelAndView mv = new ModelAndView();

        this.flowHandler.getAnchor().getTaskService().setAssignee(taskid,userid);
        mv.addObject("result","sucess");
        this.addSLog(request, currentUser, "流程管理", "设定用户", "taskId:" + taskid + ",userId:"+ userid);
        return mv;
    }

}
