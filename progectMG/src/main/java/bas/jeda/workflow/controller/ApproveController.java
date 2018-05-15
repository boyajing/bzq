package bas.jeda.workflow.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import bas.jeda.workflow.*;
//import bas.jeda.workflow.service.MeetingFlowService;
import com.google.gson.Gson;
import com.nantian.dao.*;
import com.nantian.service.sysmgn.ProjectJedaOrgService;
//import com.nantian.service.ProjectVPStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * 处理流程相关请求的服务，调用时添加.json扩展名请求JSON格式的返回信息
 *
 * @author menghui
 */
@RequestMapping(value = "/flow")
@Controller
public class ApproveController extends JedaController {

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



    @Autowired
    private ProjectJedaOrgService projectJedaOrgService;
    @Value("#{configProperties['windowpath']}")
    private String windowpath;

    @Value("#{configProperties['PAGESIZE']}")
    private int PAGESIZE;
//    @Autowired
//    private ProjectVPStatusService vrojectVPStatusService;
//    @Autowired
//    private MeetingFlowService meetingFlowService;

    /**
     * 启动流程
     *
     * @param flowid      流程ID
     * @param businesskey 流程和业务集成的主键信息
     * @param request
     * @return json格式ProcessInstanceId属性是启动的流程实例id
     */
    @RequestMapping(value = "/start/{flowid}/{businesskey}", method = RequestMethod.GET)
    public ModelAndView start_get(@PathVariable String flowid, @PathVariable String businesskey, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        //下面的kaifeitu为当前用户，可以通过切面获得
        String currentUserID = "kafeitu";
        mv.addObject("ProcessInstanceId", flowHandler.startFlowByID(flowid, currentUserID, request.getParameter("businessurl"), businesskey, null));
        return mv;
    }

    /**
     * 启动流程 request中的参数以P_开头的作为流程参数保存在流程实例中
     *
     * @param flowid      流程ID
     * @param businesskey 流程和业务集成的主键信息
     * @param request
     * @param currentUser
     * @return json格式ProcessInstanceId属性是启动的流程实例id
     */
    @LoginRequired
    @RequestMapping(value = "/start/{flowid}/{businesskey}", method = RequestMethod.POST)
    public ModelAndView start_post(@PathVariable String flowid, @PathVariable String businesskey, HttpServletRequest request,
                                   JedaUser currentUser) {
        ModelAndView mv = new ModelAndView();
        Enumeration<String> names = request.getParameterNames();
        HashMap<String, Object> variables = new HashMap<String, Object>();

        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if (name.startsWith("P_")) {
                String pname = name.substring(2);
                variables.put(pname, request.getParameter(name));
            }
        }
        variables.put("orgid", currentUser.getOrgId());

        JedaOrg jedaOrg = projectJedaOrgService.selectByPrimaryKey(currentUser.getOrgId());
        String parent =  jedaOrg.getParentOrgId();

        variables.put("parent", parent);

        //下面的kaifeitu为当前用户，可以通过切面获得
        String currentUserID = currentUser.getUserId();//
        String ProcessInstanceId = flowHandler.startFlowByID(flowid, currentUserID, request.getParameter("businessurl"), businesskey, variables);

        mv.addObject("ProcessInstanceId", ProcessInstanceId);
        mv.addObject("taskId",flowHandler.getCurrentTaskID( ProcessInstanceId));

        return mv;
    }

    /**
     * 打开审批页面
     */
    @RequestMapping(value = "/approve/{taskid}", method = RequestMethod.GET)
    public ModelAndView approve_get(@PathVariable String taskid, HttpServletRequest request) {
        //下面的程序缺少一个逻辑就是判断当前用户是否和任务中的用户匹配。
        ModelAndView mv = new ModelAndView();
        UserTask ut = this.flowHandler.getUserTask(taskid);
        if (ut != null) {
            mv.addObject("result", ut);
            //在这里把候选组人员对象转json 不然前台不能取
            if(ut.getDocConfig() !=null &&ut.getDocConfig().getRecipients() !=null && ut.getDocConfig().getRecipients().size() >0){
                Gson gson = new Gson();
                mv.addObject("recipients",gson.toJson(ut.getDocConfig().getRecipients()));
            }
            mv.setViewName("flows/approve");
        } else {
            mv.setViewName("flows/tasknotfound");
        }
        return mv;
    }

    /**
     * 打开审批页面
     */
    @RequestMapping(value = "/approvemeeting/{taskid}", method = RequestMethod.GET)
    public ModelAndView approvemeeting_get(@PathVariable String taskid, HttpServletRequest request) {
        //下面的程序缺少一个逻辑就是判断当前用户是否和任务中的用户匹配。
        ModelAndView mv = new ModelAndView();
        UserTask ut = this.flowHandler.getUserTask(taskid);
        if (ut != null) {
            mv.addObject("result", ut);
            mv.setViewName("flows/meeting/meetingstart");
        } else {
            mv.setViewName("flows/tasknotfound");
        }
        return mv;
    }

    /**
     * @param taskid      当前任务ID
     * @param activitid   要返回的节点ID
     * @param request
     * @param currentUser
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/backtask/{taskid}/{activitid}", method = RequestMethod.POST)
    public ModelAndView backTask_get(@PathVariable String taskid, @PathVariable String activitid, HttpServletRequest request,
                                     JedaUser currentUser) throws Exception {
        ModelAndView mv = new ModelAndView();

        UserTask uTask = this.flowHandler.getUserTask(taskid);

        Map<String, Object> localVar = new HashMap<String, Object>();
        Map<String, Object> processVar = new HashMap<String, Object>();

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = this.windowpath + uTask.getProcessid() + "/" + uTask.getTaskID();
                    //上传
                    localVar.put("optionpath", path + "/" + file.getOriginalFilename());
                    File targetFile = new File(path, file.getOriginalFilename());
                    File fileParent = targetFile.getParentFile();
                    if (!fileParent.exists()) {
                        fileParent.mkdirs();
                    }
                    file.transferTo(targetFile);
                }
            }
        }


        //获取流程框架页面上面的表单，把表单的值存到流程引擎中
        List<TaskDocument.FormElem> list = uTask.getDocConfig().getForms();
        for (int i = 0; i < list.size(); i++) {
            TaskDocument.FormElem ele = list.get(i);
            localVar.put(ele.getVarName(), request.getParameter(ele.getVarName()));
            //把页面填写的意见写入意见表中
            //feedBackService.saveOpinion(request.getParameter(ele.getVarName()), uTask, currentUser.getUserId());
        }

        //获取用户点击了那个按钮
        List<TaskDocument.ButtonValue> btns = uTask.getDocConfig().getButtonValues();
        for (int i = 0; i < btns.size(); i++) {
            TaskDocument.ButtonValue btn = btns.get(i);
            if (btn.getVarName() != null && (!"".equals(btn.getVarName()))) {
                if (request.getParameter(btn.getVarName()) != null && (!"".equals(request.getParameter(btn.getVarName())))) {
                    localVar.put(btn.getVarName(), btn.getVarValue());
                }
            }
        }
        this.flowHandler.setLocalVar(taskid, localVar);
        ptc.backProcess(taskid, activitid, processVar);
        mv.addObject("result","sucess");
        return mv;
    }

    @RequestMapping(value = "/dotask/{taskid}", method = RequestMethod.GET)
    public ModelAndView doTask_get(@PathVariable String taskid, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserTask uTask = this.flowHandler.getUserTask(taskid);
        Map<String, Object> processVar = new HashMap<String, Object>();
        processVar.put(uTask.getDocConfig().getAssignVarName(), request.getParameter("assignUser"));
        ptc.passProcess(taskid, processVar);
        return mv;
    }

    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/dotask/{taskid}", method = RequestMethod.POST)
    public ModelAndView dotask_post(@PathVariable String taskid, HttpServletRequest request,
                                    JedaUser currentUser) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserTask uTask = this.flowHandler.getUserTask(taskid);
        try {
            Map<String, Object> localVar = new HashMap<String, Object>();
            Map<String, Object> processVar = new HashMap<String, Object>();
            //设置下一环节的用户
            if (uTask.getDocConfig().getAssignVarName() != null && (!"".equals(uTask.getDocConfig().getAssignVarName()))) {
                processVar.put(uTask.getDocConfig().getAssignVarName(), request.getParameter("assignUser"));
            }
            //在这里判断参数，P_开头的是需要放在流程变量中的，放入流程中
            Enumeration<String> names = request.getParameterNames();

            while (names.hasMoreElements()) {
                String name = names.nextElement();
                if (name.startsWith("P_")) {
                    String pname = name.substring(2);
                    processVar.put(pname, request.getParameter(name));
                }
            }

            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if (file != null) {
                        String path = this.windowpath + uTask.getProcessid() + "/" + uTask.getTaskID();
                        //上传
                        localVar.put("optionpath", path + "/" + file.getOriginalFilename());
                        File targetFile = new File(path, file.getOriginalFilename());
                        File fileParent = targetFile.getParentFile();
                        if (!fileParent.exists()) {
                            fileParent.mkdirs();
                        }
                        file.transferTo(targetFile);
                    }
                }
            }
            //获取流程框架页面上面的表单，把表单的值存到流程引擎中
            List<TaskDocument.FormElem> list = uTask.getDocConfig().getForms();
            for (int i = 0; i < list.size(); i++) {
                TaskDocument.FormElem ele = list.get(i);
                localVar.put(ele.getVarName(), request.getParameter(ele.getVarName()));
            }
            //获取用户点击了那个按钮
            List<TaskDocument.ButtonValue> btns = uTask.getDocConfig().getButtonValues();
            for (int i = 0; i < btns.size(); i++) {
                TaskDocument.ButtonValue btn = btns.get(i);
                if (btn.getVarName() != null && (!"".equals(btn.getVarName()))) {
                    if (request.getParameter(btn.getVarName()) != null && (!"".equals(request.getParameter(btn.getVarName())))) {
                        localVar.put(btn.getVarName(), btn.getVarValue());
                    }
                }
            }
            this.flowHandler.completeTask(taskid, localVar, processVar);
            mv.addObject("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("result", "error");
    }
        return mv;
    }



    /**
     * 返回全部用户任务
     *
     * @param taskid 任务ID
     * @param request
     *
     * @return json实体类{@link IUserActicity}
     */
//    @RequestMapping(value = "/activitybytask/{taskid}", method = RequestMethod.GET)
//    public ModelAndView activitybytask_get(@PathVariable String taskid, HttpServletRequest request) {
//        ModelAndView mv = new ModelAndView();
//        UserTaskInfo tu = this.flowHandler.getUserTask(taskid);
//        if (tu == null) {
//            tu = this.flowHandler.getHisUserTask(taskid);
//        }
//
//        Object processDescription = FlowConstant.getProcessDescriptionById(tu.getProcessDefinitionId());
//        if(processDescription == null){
//            //并行方案流程返回这个，目前先写死 原来方式并行报错，其余流程暂时不修改
//            if(tu.getProcessDefinitionId().indexOf("planparallelflow") != -1){
//                processDescription =this.flowHandler.getProcessDescription(tu.getProcessDefinitionId()).getUserActiviti();
//
//            }else{
//                //这个方法会存在问题，暂时，不能显示用户的节点信息。
//                processDescription =  this.flowHandler.getAllUserActivity(tu.getProcessDefinitionId());
//            }
//        }
//
//        mv.addObject("result", processDescription);
//        return mv;
//
//    }

    /**
     * 根据流程定义id 返回所有环节
     *
     * @param processDefinitionId 流程定义id
     * @param request
     *
     * @return json实体类{@link IUserActicity}
     */
    @RequestMapping(value = "/activitybyprocessDefinitionid/{processDefinitionId}", method = RequestMethod.GET)
    public ModelAndView activitybyprocessDefinitionid_get(@PathVariable String processDefinitionId, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        Object processDescription = FlowConstant.getProcessDescriptionById(processDefinitionId);
        Object processDescription1 = FlowConstant.getProcessDescriptionById(processDefinitionId);
        if(processDescription == null){
            //并行方案流程返回这个，目前先写死 原来方式并行报错，其余流程暂时不修改
            if(processDefinitionId.indexOf("planparallelflow") != -1){
                processDescription = this.flowHandler.getProcessDescription(processDefinitionId).getUserActiviti();
            //重大事项风险预警走该流程，用于选择左侧显示节点
            }else if(processDefinitionId.indexOf("importantflow") != -1){
                processDescription = this.flowHandler.getProcessDescription(processDefinitionId).getUserActiviti();
            }else{
                //这个方法会存在问题，暂时，不能显示用户的节点信息。
                processDescription =  this.flowHandler.getAllUserActivity(processDefinitionId);
            }
        }

        mv.addObject("result", processDescription);
        return mv;

    }

    @RequestMapping(value = "/currentbytask/{taskid}", method = RequestMethod.GET)
    public ModelAndView currentbytask_get(@PathVariable String taskid, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        UserTask tu = this.flowHandler.getUserTask(taskid);
        mv.addObject("result", this.flowHandler.getCurrentActivity(tu.getProcessid()));
        return mv;
    }

    /**
     * 根据流程实例ID返回当前任务
     *
     * @param processid 流程实例ID
     * @param request
     * @return json实体类{@link bas.jeda.work.IUserTask}
     */
    @RequestMapping(value = "/currentbyprocess/{processid}", method = RequestMethod.GET)
    public ModelAndView currentbyprocess_get(@PathVariable String processid, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("result", this.flowHandler.getCurrentActivity(processid));
        return mv;
    }

    /**
     * 根据用户ID返回待办任务
     *
     * @param currentUser
     *
     * @return json实体类{@link bas.jeda.work.UserTask}
     */
    @LoginRequired
    @RequestMapping(value = "/todolist", method = RequestMethod.GET)
    public ModelAndView todolist_get(JedaUser currentUser, HttpServletRequest request,
                                     @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
                                     @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize) {
        ModelAndView mv = new ModelAndView();
        //在此处加入分页，不然流程太慢
        int pagestart = 0;
        if(pageSize == 0 || pageSize < 0){
            pageSize = this.PAGESIZE;
        }
        if(pi > 0){
            pagestart = (pi - 1) * pageSize ;
        }else {
            pi = 1;
        }

        Long pagecount = this.flowHandler.countTODOTask(currentUser.getUserId()) / pageSize +1;
        List<UserTaskForList> ts = this.flowHandler.getTODOtaskList(currentUser.getUserId(),pagestart, pageSize);

        //这里判断是议题表中已经有该任务 把该任务移除
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

        mv.addObject("pagecount",pagecount);
        mv.addObject("tasks", ts);
        mv.addObject("pageindex",pi);
        mv.addObject("pageSize",pageSize);
        mv.setViewName("flows/todolist");
        return mv;
    }

    /**
     * mettingToDoList
     *
     * @param currentUser 用户id
     * @return json实体类{@link bas.jeda.work.UserTask}
     */
    @LoginRequired
    @RequestMapping(value = "/meetingtodolist", method = RequestMethod.GET)
    public ModelAndView meetingtodolist_get(JedaUser currentUser, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<UserTaskForList> ts = this.flowHandler.getTODOtaskList(currentUser.getUserId(), 0, 1000);
        for (int i = ts.size() - 1; i >= 0; i--) {
            if (ts.get(i).getDocConfig() == null || (!"true".equals(ts.get(i).getDocConfig().getMeetingtask()))) {
                ts.remove(i);
            }
        }
        mv.addObject("tasks", ts);
        mv.setViewName("flows/todolist");
        return mv;
    }

    /**
     * 已办任务列表
     *
     * @param currentUser
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/takenlist", method = RequestMethod.GET)
    public ModelAndView takenlist_get(JedaUser currentUser ,@RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
                                      @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize) {
        ModelAndView mv = new ModelAndView();
        //在此处加入分页，不然流程太慢
        int pagestart = 0;
        if(pageSize == 0 || pageSize < 0){
            pageSize = this.PAGESIZE;
        }
        if(pi > 0){
            pagestart = (pi - 1) * pageSize ;
        }else {
            pi = 1;
        }

        Long pagecount = this.flowHandler.countTAKENTask(currentUser.getUserId()) / pageSize +1;
        List<TakenTaskForList> ts = this.flowHandler.getTAKENTaskList(currentUser.getUserId(), pagestart, pageSize);
        mv.addObject("pageindex",pi);
        mv.addObject("pageSize",pageSize);
        mv.addObject("pagecount",pagecount);

        mv.addObject("tasks", ts);
        mv.setViewName("flows/takentask");
        return mv;
    }

    @RequestMapping(value = "/processImg/{processid}", method = RequestMethod.GET)
    public void processImg(@PathVariable String processid, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        InputStream imp = this.flowHandler.getProcessImg(processid);

        OutputStream stream = response.getOutputStream();
        int ch;
        while ((ch = imp.read()) != -1) {
            stream.write(ch);
        }

        stream.flush();
        stream.close();
    }

    /**
     * 返回流程定义
     *
     * @param processdefid 流程定义ID
     * @return json实体类{@link bas.jeda.work.MyProcessDefinition}
     */
    @RequestMapping(value = "/processdef/{processdefid}", method = RequestMethod.GET)
    public ModelAndView processdef_get(@PathVariable String processdefid) {
        ModelAndView mv = new ModelAndView();
        //把每个流程定义的流程定义信息放入常量中，避免重复访问数据库。
        MyProcessDefinition myProcessDefinition = FlowConstant.getMyProcessDefinitionById(processdefid);
        if(myProcessDefinition ==null ){
            myProcessDefinition = this.flowHandler.getProcessDefinition(processdefid);
            FlowConstant.getProcessDefinitionMap().put(processdefid,myProcessDefinition);
        }
        mv.addObject("result", myProcessDefinition);
        return mv;
    }

    /**
     * 获取当前运行的流程实例
     *
     * @return 实体类{@link MyProcessInstance}
     */
    @RequestMapping(value = "/processlist", method = RequestMethod.GET)
    public ModelAndView processlist_get() {
        ModelAndView mv = new ModelAndView();
        List items = this.flowHandler.getAllProcessDefinition();
        mv.addObject("alldef", items);
        mv.addObject("result", this.flowHandler.getAllProcessInstance());
        return mv;
    }

    /**
     * 获取已经执行的历史任务
     *
     * @param processid 流程实例ID
     * @param request
     * @return json实体类{@link MyHisActivity}
     */
    @RequestMapping(value = "/processHis/{processid}", method = RequestMethod.GET)
    public ModelAndView processHis(@PathVariable String processid, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

//        mv.addObject("vars", this.flowHandler.getHitProcessVars(processid));
        mv.addObject("result", this.flowHandler.findHistoryActiviti(processid));
        mv.setViewName("flows/history");
        return mv;
    }

    /**
     * 向某实例发送消息
     *
     * @param msg 消息名
     * @param processid 流程实例id
     * @return json，result属性值为succeed
     */
    @RequestMapping(value = "/sendMessage/{processid}", method = RequestMethod.GET)
    public ModelAndView sendMessage(@RequestParam(value = "msg", required = true) String msg, @PathVariable String processid) {
        ModelAndView mv = new ModelAndView();
        this.flowHandler.SendMessage(processid, msg);
        mv.addObject("result", "succeed");
        return mv;
    }

    /**
     * 返回全部用户参与的所有流程
     *
     *
     * @param currentUser
     * @param request
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/participation", method = RequestMethod.GET)
    public ModelAndView findHis(JedaUser currentUser, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("result", this.flowHandler.findHisByUserName(currentUser.getUserId()));
        mv.setViewName("flows/participationlist");
        return mv;
    }

    @RequestMapping(value = "/rejectactivitylist/{processid}/{taskid}", method = RequestMethod.GET)
    public ModelAndView rejectactivitylist(@PathVariable String processid, @PathVariable String taskid) {
        ModelAndView mv = new ModelAndView();
        UserTask uTask = this.flowHandler.getUserTask(taskid);
        String opt = "";
        for (int i = 0; i < uTask.getDocConfig().getButtonValues().size(); i++) {
            if ("rejectbtn".equals(uTask.getDocConfig().getButtonValues().get(i).getVarName())) {
                opt = uTask.getDocConfig().getButtonValues().get(i).getOpt();
                break;
            }
        }
        if ("".equals(opt)) {
            mv.addObject("result", null);
            return mv;
        }
        String[] des = opt.split(",");

        UserTask tu = this.flowHandler.getUserTask(taskid);
        List<IUserActicity> acts = this.flowHandler.getAllUserActivityNoOrder(tu.getProcessDefinitionId());

        for (int i = acts.size() - 1; i >= 0; i--) {

            boolean k = true;
            for (int j = 0; j < des.length; j++) {
                if (des[j].equals(acts.get(i).getId())) {
                    k = false;
                    break;
                }
            }
            if (k) {
                acts.remove(i);
            }
        }
        mv.addObject("result", acts);
        return mv;
    }

    /**
     * 查看已办任务详细信息
     *
     * @param taskid
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/viewtask/{taskid}", method = RequestMethod.GET)
    public ModelAndView viewtask(@PathVariable String taskid, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        TakenTask ut = this.flowHandler.getHisUserTask(taskid);
        if (ut != null) {
            mv.addObject("result", ut);
            mv.setViewName("flows/viewtask");
        } else {
            mv.setViewName("flows/tasknotfound");
        }
        return mv;
    }

    /**
     * 否决，结束当前流程
     *
     * @param taskid
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/votedprocess/{taskid}", method = RequestMethod.POST)
    public ModelAndView votedProcess(@PathVariable String taskid, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();

        UserTask uTask = this.flowHandler.getUserTask(taskid);
        String activitid = this.flowHandler.getEndtActivity(uTask.getProcessDefinitionId()).getId();
        Map<String, Object> localVar = new HashMap<String, Object>();
        Map<String, Object> processVar = new HashMap<String, Object>();

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = this.windowpath + uTask.getProcessid() + "/" + uTask.getTaskID();
                    //上传
                    localVar.put("optionpath", path + "/" + file.getOriginalFilename());
                    File targetFile = new File(path, file.getOriginalFilename());
                    File fileParent = targetFile.getParentFile();
                    if (!fileParent.exists()) {
                        fileParent.mkdirs();
                    }
                    file.transferTo(targetFile);
                }
            }
        }


        //获取流程框架页面上面的表单，把表单的值存到流程引擎中
        List<TaskDocument.FormElem> list = uTask.getDocConfig().getForms();
        for (int i = 0; i < list.size(); i++) {
            TaskDocument.FormElem ele = list.get(i);
            localVar.put(ele.getVarName(), request.getParameter(ele.getVarName()));
        }

        //获取用户点击了那个按钮
        List<TaskDocument.ButtonValue> btns = uTask.getDocConfig().getButtonValues();
        for (int i = 0; i < btns.size(); i++) {
            TaskDocument.ButtonValue btn = btns.get(i);
            if (btn.getVarName() != null && (!"".equals(btn.getVarName()))) {
                if (request.getParameter(btn.getVarName()) != null && (!"".equals(request.getParameter(btn.getVarName())))) {
                    localVar.put(btn.getVarName(), btn.getVarValue());
                }
            }
        }
        this.flowHandler.setLocalVar(taskid, localVar);
        ptc.backProcess(taskid, activitid, processVar);
        mv.addObject("result","sucess");
        return mv;
    }

    @RequestMapping("/getProcessTrace/{taskid}")
    @ResponseBody
    public ModelAndView getProcessTrace(@PathVariable String taskid) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserTask uTask = this.flowHandler.getUserTask(taskid);
        List<Map<String, Object>> result = this.flowHandler.getProcessTrace(uTask.getProcessDefinitionId());

        for (int i = 0; i < result.size(); i++) {

        }
        mv.addObject("result", result);
        return mv;
    }

    /**
     * 当前用户发起的审批中的流程
     * @param flowkey  流程定义的key
     * @param currentUser 当前用户
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/getApprovInfo/{flowkey}",method = RequestMethod.GET)
    public ModelAndView getStatrtFlow(@PathVariable String flowkey,JedaUser currentUser,
                                      @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
                                      @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize){
        ModelAndView mv = new ModelAndView();
        String userid = currentUser.getUserId();
        //在此处加入分页，不然流程太慢
        int pagestart = 0;
        if(pageSize == 0 || pageSize < 0){
            pageSize = this.PAGESIZE;
        }
        if(pi > 0){
            pagestart = (pi - 1) * pageSize ;
        }else {
            pi = 1;
        }
        List<String> flowkeys = new ArrayList<String>();
        String[] split = flowkey.split("@");
        flowkeys= Arrays.asList(split);
        Long pagecount = this.flowHandler.countStartFlow(userid, flowkeys) / pageSize +1;
        List<UserTaskForList> startFlow = this.flowHandler.getStartFlow(userid, flowkeys, pagestart, pageSize);
        mv.addObject("pageindex",pi);
        mv.addObject("pageSize",pageSize);
        mv.addObject("pagecount", pagecount);
        mv.addObject("startFlow", startFlow);
        mv.setViewName("project/indexRunning");
        return mv;
    }

    /**
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/selectuser", method = RequestMethod.GET)
    public ModelAndView selectuser_Vote(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",request.getParameter("role"));

        mv.addObject("org",request.getParameter("org"));
        mv.setViewName("flows/selectuser2");
        return mv;
    }

    /**
     * 待办项目列表条件查询
     * zhujianhu 2017-7-27
     * @param request
     * @param mv
     * @param response
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView indexPost(HttpServletRequest request, ModelAndView mv,HttpServletResponse response,JedaUser currentUser,
                                  String businesscode,String businessname,
                                  @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize,
                                  @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
                                  @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,UserTaskForList obj
                                  ){
        try {
            indexQuery(businesscode, businessname, pageIndex, pageSize, mv, currentUser, request, pi);

        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("flows/todolist");
        return mv;
    }
    /**
     * 待办项目列表条件查询
     * zhujianhu 2017-7-27
     * @param request
     * @param mv
     * @param response
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView indexGet(HttpServletRequest request, ModelAndView mv,HttpServletResponse response,JedaUser currentUser,
                                 String businesscode,String businessname,
                                 @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize,
                                 @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
                                 @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,UserTaskForList obj
    ){
        try {
            businessname = URLDecoder.decode(businessname, "utf-8");
            indexQuery(businesscode, businessname, pageIndex, pageSize, mv, currentUser, request, pi);

        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("flows/todolist");
        return mv;
    }

    @Transactional
    public ModelAndView indexQuery(String businesscode,String businessname,int pageIndex,int pageSize,ModelAndView mv,JedaUser currentUser,HttpServletRequest request,
                                               @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi)throws Exception{
        int pagestart = 0;
        if(pageSize == 0 || pageSize < 0){

            pageSize = this.PAGESIZE;
        }
        if(pi > 0){
            pagestart = (pi - 1) * pageSize ;
        }else {
            pi = 1;
        }
        Long pagecount = this.flowHandler.countTODOTask(currentUser.getUserId(), businesscode, businessname) / pageSize +1;
        List<UserTaskForList> ts = this.flowHandler.gettodoList(businesscode, businessname, currentUser.getUserId(), pagestart, pageSize);
        mv.addObject("pageindex",pi);
        mv.addObject("pageSize",pageSize);
        mv.addObject("pagecount", pagecount);
        mv.addObject("businesscode", businesscode);
        businessname = URLEncoder.encode(URLEncoder.encode(businessname, "utf-8"), "utf-8");
        mv.addObject("businessname", businessname);

        mv.addObject("tasks", ts);
    return mv;
    }

    /**
     * 已办项目列表条件查询
     * zhujianhu 2017-7-27
     * @param request
     * @param mv
     * @param response
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/takenlistindex", method = RequestMethod.POST)
    public ModelAndView takenlistindexPost(HttpServletRequest request, ModelAndView mv,HttpServletResponse response,JedaUser currentUser,
                                  String businesscode,String businessname,
                                  @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize,
                                  @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
                                  @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex
    ){
        try {
            takenlist(businesscode, businessname, pageIndex, pageSize, mv, currentUser, request, pi);
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("flows/takentask");
        return mv;
    }

    /**
     * 已办项目列表条件查询
     * zhujianhu 2017-7-27
     * @param request
     * @param mv
     * @param response
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/takenlistindex", method = RequestMethod.GET)
    public ModelAndView takenlistindexGet(HttpServletRequest request, ModelAndView mv,HttpServletResponse response,JedaUser currentUser,
                                 String businesscode,String businessname,
                                 @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize,
                                 @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
                                 @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex
    ){
        try {
            businessname = URLDecoder.decode(businessname,"utf-8");
            takenlist(businesscode, businessname, pageIndex, pageSize, mv, currentUser, request, pi);
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("flows/takentask");
        return mv;
    }

    @Transactional
    public ModelAndView takenlist(String businesscode,String businessname,int pageIndex,int pageSize,ModelAndView mv,JedaUser currentUser,HttpServletRequest request,
                                   @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi)throws Exception{

        int pagestart = 0;
        if(pageSize == 0 || pageSize < 0){
            pageSize = this.PAGESIZE;
        }
        if(pi > 0){
            pagestart = (pi - 1) * pageSize ;
        }else {
            pi = 1;
        }
        Long pagecount = this.flowHandler.countTask(currentUser.getUserId(), businesscode, businessname) / pageSize +1;
        List<TakenTaskForList> ts = this.flowHandler.gettakenlistList(businesscode, businessname, currentUser.getUserId(), pagestart, pageSize);
        mv.addObject("pageindex",pi);
        mv.addObject("pageSize",pageSize);
        mv.addObject("pagecount", pagecount);
        mv.addObject("businesscode", businesscode);
        businessname = URLEncoder.encode(URLEncoder.encode(businessname,"utf-8"),"utf-8");
        mv.addObject("businessname", businessname);

        mv.addObject("tasks",ts);
        return mv;
    }


    /**
     * 审批中的项目列表条件查询
     * zhujianhu 2017-7-27
     * @param request
     * @param mv
     * @param response
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/indexRun", method = RequestMethod.POST)
    public ModelAndView indexPostRun(HttpServletRequest request, ModelAndView mv,HttpServletResponse response,JedaUser currentUser,
                                     String businesscode,String businessname,String productName,String parent,String taskName,String userID,
                                  @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize,
                                  @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
                                  @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,UserTaskForList obj
    ){
        try {

            indexQueryRun(businesscode, businessname,productName,parent,taskName,userID, pageIndex, pageSize, mv, request, pi);

        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("project/indexRunning");
        return mv;
    }
    /**
     * 审批中的项目列表条件查询
     * zhujianhu 2017-7-27
     * @param request
     * @param mv
     * @param response
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/indexRun", method = RequestMethod.GET)
    public ModelAndView indexGetRun(HttpServletRequest request, ModelAndView mv,HttpServletResponse response,JedaUser currentUser,
                                 String businesscode,String businessname,String productName,String parent,String taskName,String userID,
                                 @RequestParam(value = "pageSize", required = true, defaultValue = "-1") int pageSize,
                                 @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi,
                                 @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,UserTaskForList obj
    ){
        try {
            businessname = URLDecoder.decode(businessname,"utf-8");
            indexQueryRun(businesscode, businessname,productName,parent,taskName,userID,pageIndex, pageSize, mv, request, pi);

        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("project/indexRunning");
        return mv;
    }

    @Transactional
    public ModelAndView indexQueryRun(String businesscode,String businessname,String productName,String parent,String taskName,String userID,int pageIndex,int pageSize,ModelAndView mv,HttpServletRequest request,
                                   @RequestParam(value = "pi", required = true, defaultValue = "-1") int pi)throws Exception{
        int pagestart = 0;
        if(pageSize == 0 || pageSize < 0){

            pageSize = this.PAGESIZE;
        }
        if(pi > 0){
            pagestart = (pi - 1) * pageSize ;
        }else {
            pi = 1;
        }

        Long pagecount = this.flowHandler.countTODOTask(businesscode, businessname,productName,parent,taskName,userID) / pageSize +1;
        List<UserTaskForList> ts = this.flowHandler.gettodoList(businesscode, businessname,productName,parent,taskName,userID, pagestart, pageSize);
        mv.addObject("pageindex",pi);
        mv.addObject("pageSize",pageSize);
        mv.addObject("pagecount", pagecount);
        mv.addObject("businesscode", businesscode);
        businessname = URLEncoder.encode(URLEncoder.encode(businessname,"utf-8"),"utf-8");
        mv.addObject("businessname", businessname);
        mv.addObject("productName", productName);
        mv.addObject("parent", parent);
        mv.addObject("taskName", taskName);
        mv.addObject("userID", userID);

        mv.addObject("startFlow",ts);
        return mv;
    }

    /**
     * 撤回处理逻辑
     * @param flowid       当前流程id
     * @param activitid   要返回的节点ID
     * @param request
     * @param currentUser
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/takeBack/{flowid}/{taskId}/{activitid}/{currentactID}", method = RequestMethod.POST)
    public ModelAndView takeBack(@PathVariable String flowid, @PathVariable String activitid, HttpServletRequest request,
                                 @PathVariable String currentactID,@PathVariable String taskId,
                                 JedaUser currentUser) throws Exception {
        ModelAndView mv = new ModelAndView();
        //判断任务是否存在

        String taskid = flowHandler.getCurrentTaskID(flowid);

        if(taskid ==null || "".equals(taskid)){
            mv.addObject("result","2");//2 表示当前流程已经结束不能返回。
            return mv;
        }

        UserTask uTask = this.flowHandler.getUserTask(taskid);

        if(!currentactID.equals(uTask.getTaskDefinitionKey())){
            mv.addObject("result","3");//  3 表示当前流程已经不在要撤回的节点，不能撤回。
            return mv;
        }

        Map<String, Object> localVar = new HashMap<String, Object>();
        Map<String, Object> processVar = new HashMap<String, Object>();

        localVar.put("commit", "主动撤回");
        //设置本地变量
        this.flowHandler.setLocalVar(taskid, localVar);
        //把办理人设置为空，撤回节点不显示在已办列表
        this.flowHandler.getAnchor().getTaskService().setAssignee(taskid,"");
        ptc.backProcess(taskid, activitid, processVar);
        mv.addObject("result","1"); //1 表示操作成功
        return mv;
    }
}
