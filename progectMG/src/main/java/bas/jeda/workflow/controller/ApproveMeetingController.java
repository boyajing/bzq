package bas.jeda.workflow.controller;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.workflow.*;
//import bas.jeda.workflow.service.MeetingFlowService;
import com.nantian.service.sysmgn.ProjectJedaOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by NT on 2017/4/29.
 */
@Controller
@RequestMapping("/meetingflow")
public class ApproveMeetingController extends JedaController {

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

//    @Autowired
//    private MeetingFlowService meetingFlowService;

    @Autowired
    private ProjectJedaOrgService projectJedaOrgService;
    /**
     * 打开会议发起审批页面
     *
     * @param pretaskid 需要上会的待办任务id
     * @return
     */
    @RequestMapping(value = "/start/{pretaskid}", method = RequestMethod.GET)
    public ModelAndView start_get(@PathVariable String pretaskid) {
        ModelAndView mv = new ModelAndView();
        UserTask userTask = flowHandler.getUserTask(pretaskid);

        mv.addObject("userTask", userTask);
        mv.setViewName("flows/meeting/meetingstart");
        return mv;
    }

    /**
     * 打开待上会页面
     *
     * @return
     */
    @RequestMapping(value = "/daishanghui", method = RequestMethod.GET)
    public ModelAndView shanghuistart_get() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("flows/meeting/meetingstart2");
        return mv;
    }

    /**
     * 打开重大预警事项会议发起审批页面
     *
     * @param pretaskid 需要上会的待办任务id
     * @return
     */
    @RequestMapping(value = "/startipt/{pretaskid}", method = RequestMethod.GET)
    public ModelAndView start_getipt(@PathVariable String pretaskid) {
        ModelAndView mv = new ModelAndView();
        UserTask userTask = flowHandler.getUserTask(pretaskid);

        mv.addObject("userTask", userTask);
        mv.setViewName("flows/meeting/riskmeetingstart");
        return mv;
    }



    /**
     * iFrame
     * @param meetingid
     * @return
     * @throws Exception
     */
//    @LoginRequired
//    @RequestMapping(value = "/viewmeeting/{meetingid}", method = RequestMethod.GET)
//    public ModelAndView getView(@PathVariable String meetingid,
//                                JedaUser currentUser) {
//        ModelAndView mv = new ModelAndView();
//        //1、根据会议id 查询出会议基本信息，返回给前台。
//        TMeetingBaseInfo tMeetingBaseInfo = meetingFlowService.findTMeetingBaseInfo(meetingid);
//        mv.addObject("meetingBase",tMeetingBaseInfo);
//        //2、根据会议id和用户id ,在相关人员表中查询出当前用户对应的议题id的记录
//        List<TMeetingIssue> tMeetingIssueslist = meetingFlowService.findTMeetingIssueBymeetingid(meetingid);
//        if(tMeetingIssueslist != null){
//            mv.addObject("tMeetingIssueslist",tMeetingIssueslist);
//        }
//
//        // 3、根据会议id查询当前会议下的参会人员
//        List<TMeetingPeople> tMeetingPeoplelist = meetingFlowService.selectByExample(meetingid);
//        if(tMeetingIssueslist != null){
//            mv.addObject("tMeetingPeoplelist",tMeetingPeoplelist);
//        }
//        mv.setViewName("flows/meeting/meetinginfo");
//
//        return mv;
//    }

    /**
     * 启动流程 request中的参数以P_开头的作为流程参数保存在流程实例中
     *
     * @param flowid      流程ID
     * @param request
     * @param currentUser
     * @return json格式ProcessInstanceId属性是启动的流程实例id
     */
//    @LoginRequired
//    @RequestMapping(value = "/start/{flowid}", method = RequestMethod.POST)
//    public ModelAndView start_post(@PathVariable String flowid, HttpServletRequest request,
//                                   TMeetingBaseInfo meetingBaseInfo, String[] issueTaskid,
//                                   JedaUser currentUser) {
//        ModelAndView mv = new ModelAndView();
//        String meetingid = "";
//        try {
//            //1、保存会议基本信息
//
//            //委员
//            String meetingExaminer = meetingBaseInfo.getMeetingCommittee();//request.getParameter("meetingExaminer");
//            //列席人员
//            String meetingAttendee = meetingBaseInfo.getMeetingObserver();//request.getParameter("meetingAttendee");
//            meetingFlowService.insertTMeetingBaseInfo(meetingBaseInfo);
//            meetingid = meetingBaseInfo.getMeetingId();
//            String meeting_issue = "";
//            //2、遍历保存议题信息 和参会人员信息
//            for (int i = 0; i < issueTaskid.length; i++) {
//                meeting_issue += issueTaskid[i] + ",";
//                TMeetingIssue tMeetingIssue = new TMeetingIssue();
//                tMeetingIssue.setIssueId(issueTaskid[i]);
//                tMeetingIssue.setCreater(currentUser.getUserId());
//                tMeetingIssue.setMeetingId(meetingBaseInfo.getMeetingId());
//                String issueName = request.getParameter("issueName_" + issueTaskid[i]);
//                tMeetingIssue.setIssueName(issueName);
//                //获取IssueBusinssKey
//                UserTask userTask = flowHandler.getUserTask(issueTaskid[i]);
//                tMeetingIssue.setIssueBusinssKey(userTask.getBusinessKey());
//                tMeetingIssue.setIssueType(userTask.getProcessDefinitionId());
//                //将上会节点id保存到议题中
//                tMeetingIssue.setWorkflowId(userTask.getTaskDefinitionKey());
//                String[] parameterValues = request.getParameterValues("n_" + issueTaskid[i]);
//                meetingFlowService.saveTMeetingIssue(tMeetingIssue, parameterValues, "");
//            }
//            //返回新的流程实例号码，并且完成会议流程中会议秘书环节。
//            HashMap<String, Object> variables = new HashMap<String, Object>();
//
//            variables.put("orgid", currentUser.getOrgId());
//            JedaOrg jedaOrg = projectJedaOrgService.selectByPrimaryKey(currentUser.getOrgId());
//            String parent =  jedaOrg.getParentOrgId();
//            variables.put("parent", parent);
//
//            variables.put("meetingExaminer", meetingExaminer);//把所有参会人员和列席人员保存到流程引擎中
//            variables.put("meetingAttendee", meetingAttendee);
//            variables.put("meeting_issue", meeting_issue);//把会议的会议id 放入流程中
//            variables.put("businessname", "第"+meetingBaseInfo.getMeetingNumber()+"次会议");//
//            //4、启动流程
//            String businessurl = "/meetingflow/viewmeeting/" + meetingBaseInfo.getMeetingId() + "?a=11"; //拼接议题查看页面
//            String flowID = flowHandler.startFlowByID("meetingflow", currentUser.getUserId(), businessurl, meetingBaseInfo.getMeetingId(), variables);
//            mv.addObject("meetflowid", flowID);
//
//            mv.addObject("taskId",flowHandler.getCurrentTaskID( flowID));
//
//            mv.addObject("sucess", "sucess");
//        }catch (Exception e){
//            //如果有错误，把相关的议题和 和参会人员，表决情况都删除
//            meetingFlowService.deleteIssue(meetingid);
//            mv.addObject("sucess", "error");
//        }
//        return mv;
//    }

    /**
     * @param candidate 候选人员列表类型
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/selectuser/{candidate}", method = RequestMethod.GET)
    public ModelAndView selectuser_Vote(@PathVariable String candidate) throws Exception {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("flows/meeting/" + candidate);
        return mv;
    }

//    @LoginRequired
//    @RequestMapping(value = "/meetingtodolist", method = RequestMethod.GET)
//    public ModelAndView meetingtodolist_get(JedaUser currentUser, HttpServletRequest request) {
//        ModelAndView mv = new ModelAndView();
//        List<UserTaskForList> ts = this.flowHandler.getTODOtaskList(currentUser.getUserId(),0,1000);
//        for (int i = ts.size() - 1; i >= 0; i--) {
//            if (ts.get(i).getDocConfig() == null || (!"true".equals(ts.get(i).getDocConfig().getMeetingtask()))||ts.get(i).getProcessDefinitionId().indexOf("importantflow") != -1) {
//                ts.remove(i);
//            }else{
//                //判断议题信息中有这条数据
//                TMeetingIssue tMeetingIssue = meetingFlowService.findTMeetingIssue(ts.get(i).getTaskID());
//                if(tMeetingIssue != null){
//                    ts.remove(i);
//                }
//            }
//        }
//        mv.addObject("tasks", ts);
//        mv.setViewName("flows/meeting/issuelist");
//        return mv;
//    }

//    @LoginRequired
//    @RequestMapping(value = "/daishanghuimeetingtodolist", method = RequestMethod.GET)
//    public ModelAndView daishanghuimeetingtodolist_get(JedaUser currentUser, HttpServletRequest request) {
//        ModelAndView mv = new ModelAndView();
//        List<UserTaskForList> ts = this.flowHandler.getTODOtaskList(currentUser.getUserId(),0,1000);
//        for (int i = ts.size() - 1; i >= 0; i--) {
//            if (ts.get(i).getDocConfig() == null || (!"true".equals(ts.get(i).getDocConfig().getMeetingtask()))) {
//                ts.remove(i);
//            }else{
//                //判断议题信息中有这条数据
//                TMeetingIssue tMeetingIssue = meetingFlowService.findTMeetingIssue(ts.get(i).getTaskID());
//                if(tMeetingIssue != null){
//                    ts.remove(i);
//                }
//            }
//        }
//        mv.addObject("tasks", ts);
//        mv.setViewName("flows/meeting/issuelist2");
//        return mv;
//    }

    /**
     * 委员审批界面入口
     * iFrame
     * @param request
     * @return
     * @throws Exception
     */
//    @LoginRequired
//    @RequestMapping(value = "/weiyuanapproveiframe",method = RequestMethod.GET)
//    public ModelAndView weiyuanapprove(JedaUser currentUser, ModelAndView mv, HttpServletRequest request) {
//        try{
//            //常委审批
//            mv.setViewName("flows/meeting/meetingvoteinfo2");
//            String meetingId = request.getParameter("meetingId");
//            TMeetingIssueExample example = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria = example.createCriteria();
//            //根据上会id查询议题
//            if(meetingId != null){
//            criteria.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example);
//            if(list != null && list.size()>0){
//                mv.addObject("faxxList",list);
//            }
//            //查询当前是否已经进行过审批
//            TMeetingVoteExample example1 = new TMeetingVoteExample();
//            TMeetingVoteExample.Criteria criteria1 = example1.createCriteria();
//            if (currentUser.getUserId() != null){
//                criteria1.andUserIdLike(currentUser.getUserId());}
//            if(meetingId != null){
//                criteria1.andMeetingIdLike(meetingId);}
//            List<TMeetingVote> tMeetingVoteslist = meetingFlowService.selecttMeetingVoteByExample(example1);
//            if(tMeetingVoteslist !=null && tMeetingVoteslist.size()>0){
//                /*TMeetingVote tMeetingVote = tMeetingVoteslist.get(0);*/
//                mv.addObject("vote", tMeetingVoteslist);//表决意见
//            }
//            //查找数据字典（表决意见）
//            TSysCodeExample example2 = new TSysCodeExample();
//            TSysCodeExample.Criteria criteria2 = example2.createCriteria();
//            criteria2.andCodeTypeLike("097");
//            List<TSysCode> bjyjlist = meetingFlowService.selectByExample(example2);
//            mv.addObject("bjyj", bjyjlist);
//
//            mv.addObject("meetingId",meetingId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mv;
//    }

    /**
     *进入列员查看页面入口
     * iFrame
     * @param request
     * @return
     * @throws Exception
     */
//    @LoginRequired
//    @RequestMapping(value = "/observeapproveiframe",method = RequestMethod.GET)
//    public ModelAndView leiyuanlook(JedaUser currentUser, ModelAndView mv, HttpServletRequest request) {
//        try{
//            //常委审批
//            mv.setViewName("flows/meeting/leiyuanlookinfo");
//            String meetingId = request.getParameter("meetingId");
//            TMeetingIssueExample example = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria = example.createCriteria();
//            //根据上会id查询议题
//            if(meetingId != null){
//                criteria.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example);
//            if(list != null && list.size()>0){
//                mv.addObject("faxxList",list);
//            }
//            mv.addObject("meetingId",meetingId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mv;
//    }

    /**
     * 打开委员表决页面
     * @param request
     * @param mv
     * @return
     */
//    @LoginRequired
//    @RequestMapping(value = "/showVote")
//    public ModelAndView showVote(JedaUser currentUser,HttpServletRequest request, ModelAndView mv) {
//        //String issueBusinssKey = request.getParameter("issueBusinssKey");
//        String meetingId = request.getParameter("meetingId");
//        String issueId = request.getParameter("issueId");
//        mv.addObject("FON", "NOMAL");
//        //查询已经填写的审批意见
//        TMeetingVoteExample example = new TMeetingVoteExample();
//        TMeetingVoteExample.Criteria criteria = example.createCriteria();
//        if (issueId != null){
//            criteria.andIssueIdLike(issueId);}
//        if(meetingId != null){
//            criteria.andMeetingIdLike(meetingId);}
//        criteria.andUserIdLike(currentUser.getUserId());
//        List<TMeetingVote> tMeetingVoteslist = meetingFlowService.selecttMeetingVoteByExample(example);
//        if(tMeetingVoteslist !=null && tMeetingVoteslist.size()>0){
//            TMeetingVote tMeetingVote = tMeetingVoteslist.get(0);
//            mv.addObject("voteDesc", tMeetingVote.getVoteOpinion());
//            mv.addObject("voteOption", tMeetingVote.getVoteResult());
//        }
//        //查找数据字典（表决意见）
//        TSysCodeExample example1 = new TSysCodeExample();
//        TSysCodeExample.Criteria criteria1 = example1.createCriteria();
//        criteria1.andCodeTypeLike("097");
//        List<TSysCode> bjyjlist = meetingFlowService.selectByExample(example1);
//        mv.addObject("bjyj", bjyjlist);
//        //查询选中的议题
//        TMeetingIssue  tMeetingIssues = meetingFlowService.findTMeetingIssue(issueId);
//        mv.addObject("tMeetingIssues",tMeetingIssues);
//        //议题类型
//        String[] issuetype = tMeetingIssues.getIssueType().split(":");
//        //方案
//        if(issuetype[0].equals("planmeetingflow")){
//            //查询方案项目要素信息 meetingId
//            TMeetingIssue tMeetingIssue = meetingFlowService.findTMeetingIssue(issueId);
//            ScProjectScheme scProjectScheme = meetingFlowService.selectScProjectSchemeByPrimaryKey(tMeetingIssue.getIssueBusinssKey());
//            mv.addObject("scProject",scProjectScheme);
//            mv.addObject("issueType","方案");
//        }else if(issuetype[0].equals("assessflow")||issuetype[0].equals("assess1flow")){//评估
//            mv.addObject("issueType","评估");
//        }else if(issuetype[0].equals("auditflow")){//审计
//            mv.addObject("issueType","审计");
//        }else if(issuetype[0].equals("confapplication")){//其他会议
//            mv.addObject("issueType","其他会议");
//        }else if(issuetype[0].equals("importantflow")){//其他会议
//            mv.addObject("issueType","重大预警事项");
//        }
//        //确定委员是否可以开始表决
//        TMeetingBaseInfo tMeetingBaseInfo = meetingFlowService.findTMeetingBaseInfo(meetingId);
//        mv.addObject("meetingStatus",tMeetingBaseInfo.getMeetingStatus());
//        mv.setViewName("flows/meeting/meetingvoteinfo");
//        return mv;
//    }

    /**
     * 打开秘书表决页面
     * @param request
     * @param mv
     * @return
     */
//    @LoginRequired
//    @RequestMapping(value = "/showmishuVote")
//    public ModelAndView showmishuVote(JedaUser currentUser,HttpServletRequest request, ModelAndView mv) {
//        //String issueBusinssKey = request.getParameter("issueBusinssKey");
//        String meetingId = request.getParameter("meetingId");
//        String issueId = request.getParameter("issueId");
//        mv.addObject("FON", "FINAL");
//        //查找数据字典（表决意见）
//        TSysCodeExample example1 = new TSysCodeExample();
//        TSysCodeExample.Criteria criteria1 = example1.createCriteria();
//        criteria1.andCodeTypeLike("097");
//        List<TSysCode> bjyjlist = meetingFlowService.selectByExample(example1);
//        mv.addObject("bjyj", bjyjlist);
//        //查询选中的议题
//        TMeetingIssue  tMeetingIssues = meetingFlowService.findTMeetingIssue(issueId);
//        mv.addObject("voteDesc", tMeetingIssues.getReply());
//        mv.addObject("voteOption", tMeetingIssues.getIssueResult());
//        mv.addObject("tMeetingIssues",tMeetingIssues);
//        mv.setViewName("flows/meeting/meetingvoteinfo");
//        return mv;
//    }

    /**
     * 保存委员所选议题的意见
     */
//    @LoginRequired
//    @RequestMapping(value = "/saveVote" ,method = RequestMethod.POST)
//    public void saveVote(JedaUser currentUser,HttpServletRequest request) {
//        String meetingId = request.getParameter("meetingId");
//        String issueId = request.getParameter("issueId");
//        String voteOption = request.getParameter("voteOption");
//        String voteResult = request.getParameter("voteResult");
//        //保存或修改已经填写的审批意见
//        try{
//            TMeetingVoteExample example = new TMeetingVoteExample();
//            TMeetingVoteExample.Criteria criteria = example.createCriteria();
//            criteria.andIssueIdLike(issueId).andMeetingIdLike(meetingId).andUserIdLike(currentUser.getUserId());
//            List<TMeetingVote> tMeetingVoteList = meetingFlowService.selecttMeetingVoteByExample(example);
//
//            if(tMeetingVoteList == null || tMeetingVoteList.size()<1) {
//                TMeetingVote tMeetingVote = new TMeetingVote();
//                tMeetingVote.setId(Constants.getUUID());
//                tMeetingVote.setMeetingId(meetingId);
//                tMeetingVote.setIssueId(issueId);
//                tMeetingVote.setUserName(currentUser.getUserName());
//                tMeetingVote.setUserId(currentUser.getUserId());
//                tMeetingVote.setVoteOpinion(voteResult);
//                tMeetingVote.setVoteResult(voteOption);
//                meetingFlowService.tMeetingVoteSave(tMeetingVote, "save");
//            }else{
//                TMeetingVote tMeetingVote = tMeetingVoteList.get(0);
//                tMeetingVote.setVoteOpinion(voteResult);
//                tMeetingVote.setVoteResult(voteOption);
//                meetingFlowService.tMeetingVoteSave(tMeetingVote, "update");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    /**
     * 秘书审批入口
     * iFrame
     * @param request
     * @return
     * @throws Exception
     */
//    @LoginRequired
//    @RequestMapping(value = "/mishuapproveiframe",method = RequestMethod.GET)
//    public ModelAndView miShuApprove(JedaUser currentUser, ModelAndView mv, HttpServletRequest request) {
//        try{
//            //会议秘书进行表决查看并最终审核
//
//            //查询当前会议下的议题信息
//            String meetingId = request.getParameter("meetingId");
//            TMeetingIssueExample example = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria = example.createCriteria();
//            //根据上会id查询
//            if(meetingId != null){
//                criteria.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example);
//            List<HashMap<String,Object>> yitilist = new LinkedList<HashMap<String,Object>>();
//            if(list != null && list.size()>0){
//                mv.addObject("faxxList",list);
//                for (int i = 0; i < list.size(); i++) {
//                    TMeetingIssue tMeetingIssue =  list.get(i);
//                    HashMap<String,Object> map = new HashMap<String, Object>();
//
//                    //查询议题下的所有委员的审批情况
//                    TMeetingVoteExample example1 = new TMeetingVoteExample();
//                    TMeetingVoteExample.Criteria criteria1 = example1.createCriteria();
//                    if(meetingId != null ){
//                        criteria1.andMeetingIdLike(tMeetingIssue.getMeetingId());
//                    }
//                    criteria1.andIssueIdLike(tMeetingIssue.getIssueId());
//                    List<TMeetingVote> tMeetingVoteslist = meetingFlowService.selecttMeetingVoteByExample(example1);
//                    if (tMeetingVoteslist != null && tMeetingVoteslist.size()>0){
//
//                        map.put("count",tMeetingVoteslist.size());
//                        map.put("tMeetingVoteslist",tMeetingVoteslist);
//                    }
//                    map.put("issueName",tMeetingIssue.getIssueName());
//                    map.put("issueId",tMeetingIssue.getIssueId());
//                    yitilist.add(map);
//                }
//            }
//
//            //表决结果
//            List<Map<String,Object>> biaojuelist = new LinkedList<Map<String, Object>>();
//            TMeetingIssueExample example1 = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria1 = example1.createCriteria();
//            if (meetingId != null ) {
//                criteria1.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list1 = meetingFlowService.selectTTMeetingIssue(example1);
//            if (list1 != null && list1.size() > 0) {
//                for (int i = 0; i < list1.size(); i++) {
//                    TMeetingIssue tMeetingIssue =  list1.get(i);
//                    Map<String,Object> map = new HashMap<String, Object>();
//                    TMeetingVoteExample example4 = new TMeetingVoteExample();
//                    TMeetingVoteExample.Criteria criteria4 = example4.createCriteria();
//                    criteria4.andMeetingIdLike(meetingId);
//                    criteria4.andVoteResultIsNotNull();
//                    criteria4.andIssueIdLike(tMeetingIssue.getIssueId());
//                    int tongyinum = 0;
//                    int xvyinum = 0;
//                    int foujuenum = 0;
//                    int weibiaojuenum =0;
//                    List<TMeetingVote> tMeetinglist = meetingFlowService.selecttMeetingVoteByExample(example4);
//                    for (int j = 0; j <tMeetinglist.size() ; j++) {
//                        TMeetingVote tMeetingVote = tMeetinglist.get(j);
//                        if(tMeetingVote.getVoteResult().equals("4")){
//                            weibiaojuenum++;
//                        } else if(tMeetingVote.getVoteResult().equals("2")){
//                            xvyinum++;
//                        }else if(tMeetingVote.getVoteResult().equals("3")){
//                            foujuenum++;
//                        }else if (tMeetingVote.getVoteResult().equals("1")) {
//                            tongyinum++;
//                        }
//                    }
//                    map.put("issueId",tMeetingIssue.getIssueId());
//                    map.put("issueName",tMeetingIssue.getIssueName());
//                    map.put("issueType",tMeetingIssue.getIssueType());
//                    map.put("peopleNum",tMeetinglist.size());
//                    map.put("weibiaojuenum",weibiaojuenum);
//                    map.put("tongyinum",tongyinum);
//                    map.put("xvyinum",xvyinum);
//                    map.put("foujuenum",foujuenum);
//                    if(tongyinum>=5 && (double)tongyinum/(tMeetinglist.size())>=(2.0/3)){
//                        map.put("result","同意");
//                    }else if((double)foujuenum/(tMeetinglist.size())>=(1.0/3)){
//                        map.put("result","否决");
//                    }else{
//                        map.put("result","再议");
//                    }
//                    biaojuelist.add(map);
//                }
//            }
//            mv.addObject("biaojuelist", biaojuelist);
//            mv.addObject("yitilist",yitilist);
//            mv.addObject("meetingId",meetingId);
//            mv.setViewName("flows/meeting/meetingvote2");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mv;
//    }
    //通过ajax返回数据
//    @LoginRequired
//    @RequestMapping(value = "/ajaxShuApprove",method = RequestMethod.POST)
//    public void ajaxShuApprove(JedaUser currentUser, HttpServletRequest request,
//                              HttpServletResponse response) {
//        try{
//            //会议秘书进行表决查看并最终审核
//
//            //查询当前会议下的议题信息
//            String meetingId = request.getParameter("meetingId");
//            TMeetingIssueExample example = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria = example.createCriteria();
//            //根据上会id查询
//            if(meetingId != null){
//                criteria.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example);
//            List<HashMap<String,Object>> yilist = new LinkedList<HashMap<String,Object>>();
//            if(list != null && list.size()>0){
//                for (int i = 0; i < list.size(); i++) {
//                    TMeetingIssue tMeetingIssue =  list.get(i);
//                    HashMap<String,Object> map = new HashMap<String, Object>();
//
//                    //查询议题下的所有委员的审批情况
//                    TMeetingVoteExample example1 = new TMeetingVoteExample();
//                    TMeetingVoteExample.Criteria criteria1 = example1.createCriteria();
//                    if(meetingId != null ){
//                        criteria1.andMeetingIdLike(tMeetingIssue.getMeetingId());
//                    }
//                    criteria1.andIssueIdLike(tMeetingIssue.getIssueId());
//                    List<TMeetingVote> tMeetingVoteslist = meetingFlowService.selecttMeetingVoteByExample(example1);
//                    if (tMeetingVoteslist != null && tMeetingVoteslist.size()>0){
//
//                        map.put("count",tMeetingVoteslist.size());
//                        map.put("tMeetingVoteslist",tMeetingVoteslist);
//                    }
//                    map.put("issueName",tMeetingIssue.getIssueName());
//                    yilist.add(map);
//                }
//
//            }
//            response.getWriter().print(JSONArray.fromObject(yilist));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 主任委员审批入口
     * iFrame
     * @param request
     * @return
     * @throws Exception
     */
//    @LoginRequired
//    @RequestMapping(value = "/zhurenweiyuanapprove",method = RequestMethod.GET)
//    public ModelAndView zhurenweiyuanApprove(JedaUser currentUser, ModelAndView mv, HttpServletRequest request) {
//        try{
//            //会议秘书进行表决查看并最终审核
//
//            //查询当前会议下的议题信息
//            String meetingId = request.getParameter("meetingId");
//            TMeetingIssueExample example = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria = example.createCriteria();
//            //根据上会id查询
//            if(meetingId != null){
//                criteria.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example);
//            List<HashMap<String,Object>> yitilist = new LinkedList<HashMap<String,Object>>();
//            if(list != null && list.size()>0){
//                mv.addObject("faxxList",list);
//                for (int i = 0; i < list.size(); i++) {
//                    TMeetingIssue tMeetingIssue =  list.get(i);
//                    HashMap<String,Object> map = new HashMap<String, Object>();
//
//                    //查询议题下的所有委员的审批情况
//                    TMeetingVoteExample example1 = new TMeetingVoteExample();
//                    TMeetingVoteExample.Criteria criteria1 = example1.createCriteria();
//                    if(meetingId != null ){
//                        criteria1.andMeetingIdLike(tMeetingIssue.getMeetingId());
//                    }
//                    criteria1.andIssueIdLike(tMeetingIssue.getIssueId());
//                    List<TMeetingVote> tMeetingVoteslist = meetingFlowService.selecttMeetingVoteByExample(example1);
//                    if (tMeetingVoteslist != null && tMeetingVoteslist.size()>0){
//
//                        map.put("count",tMeetingVoteslist.size());
//                        map.put("tMeetingVoteslist",tMeetingVoteslist);
//                    }
//                    map.put("issueName",tMeetingIssue.getIssueName());
//                    yitilist.add(map);
//                }
//            }
//            //表决结果
//            List<Map<String,Object>> biaojuelist = new LinkedList<Map<String, Object>>();
//            TMeetingIssueExample example1 = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria1 = example1.createCriteria();
//            if (meetingId != null ) {
//                criteria1.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list1 = meetingFlowService.selectTTMeetingIssue(example1);
//            if (list1 != null && list1.size() > 0) {
//                for (int i = 0; i < list1.size(); i++) {
//                    TMeetingIssue tMeetingIssue =  list1.get(i);
//                    Map<String,Object> map = new HashMap<String, Object>();
//
//                    TMeetingVoteExample example4 = new TMeetingVoteExample();
//                    TMeetingVoteExample.Criteria criteria4 = example4.createCriteria();
//                    criteria4.andMeetingIdLike(meetingId);
//                    criteria4.andVoteResultIsNotNull();
//                    criteria4.andIssueIdLike(tMeetingIssue.getIssueId());
//                    int tongyinum = 0;
//                    int xvyinum = 0;
//                    int foujuenum = 0;
//                    int weibiaojuenum =0;
//                    List<TMeetingVote> tMeetinglist = meetingFlowService.selecttMeetingVoteByExample(example4);
//                    for (int j = 0; j <tMeetinglist.size() ; j++) {
//                        TMeetingVote tMeetingVote = tMeetinglist.get(j);
//                        if(tMeetingVote.getVoteResult().equals("4")){
//                            weibiaojuenum++;
//                        } else if(tMeetingVote.getVoteResult().equals("2")){
//                            xvyinum++;
//                        }else if(tMeetingVote.getVoteResult().equals("3")){
//                            foujuenum++;
//                        }else if (tMeetingVote.getVoteResult().equals("1")) {
//                            tongyinum++;
//                        }
//                    }
//                    map.put("issueId",tMeetingIssue.getIssueId());
//                    map.put("issueName",tMeetingIssue.getIssueName());
//                    map.put("issueType",tMeetingIssue.getIssueType());
//                    map.put("peopleNum",tMeetinglist.size());
//                    map.put("tongyinum",tongyinum);
//                    map.put("weibiaojuenum",weibiaojuenum);
//                    map.put("xvyinum",xvyinum);
//                    map.put("foujuenum",foujuenum);
//                    map.put("committeeOpinion",tMeetingIssue.getCommitteeOpinion());
//                    map.put("boardOpinion",tMeetingIssue.getBoardOpinion());
//                    map.put("zongjingliOpinion",tMeetingIssue.getZongjingliOpinion());
//                    if(tongyinum>=5 && (double)tongyinum/(tMeetinglist.size())>=(2.0/3)){
//                        map.put("result","同意");
//                    }else if((double)foujuenum/(tMeetinglist.size())>=(1.0/3)){
//                        map.put("result","否决");
//                    }else{
//                        map.put("result","再议");
//                    }
//                    biaojuelist.add(map);
//                }
//            }
//            mv.addObject("biaojuelist", biaojuelist);
//            mv.addObject("yitilist",yitilist);
//            mv.addObject("meetingId",meetingId);
//            //判断走那个页面
//            String currentid = request.getParameter("currentid");
//            mv.addObject("currentid",currentid);
//            if(currentid.equals("2")){
//                mv.setViewName("flows/meeting/zhurenweiyuan");
//            }else if(currentid.equals("3")){
//                mv.setViewName("flows/meeting/zongjingliapprove");
//            }else if(currentid.equals("4")){
//                mv.setViewName("flows/meeting/dongshizhangapprove");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mv;
//    }

    /**
     * 保存主任委员审批结果
     * @param currentUser
     * @param request
     */
//    @LoginRequired
//    @RequestMapping(value = "/savezhurenweiyuanapprove" ,method = RequestMethod.POST)
//    public ModelAndView savezhurenweiyuanapprove(JedaUser currentUser,HttpServletRequest request, ModelAndView mv,
//                                         String[] issueId) {
//        try{
//            String currentid = request.getParameter("currentid");
//           if(issueId!=null){
//               for (int i = 0; i < issueId.length; i++) {
//                   TMeetingIssue tMeetingIssue = meetingFlowService.findTMeetingIssue(issueId[i]);
//                   if(currentid.equals("2")){
//                       tMeetingIssue.setCommitteeOpinion(request.getParameter("committeeOpinion_"+issueId[i]));
//                   }else if(currentid.equals("3")){
//                       tMeetingIssue.setZongjingliOpinion(request.getParameter("zongjingliOpinion_"+issueId[i]));
//                   }else if(currentid.equals("4")){
//                       tMeetingIssue.setBoardOpinion(request.getParameter("boardOpinion_"+issueId[i]));
//                   }
//
//                   meetingFlowService.updateByPrimaryKeySelective(tMeetingIssue);
//               }
//           }
//            //保存后返回数据
//            String meetingId = request.getParameter("meetingId");
//            TMeetingIssueExample example = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria = example.createCriteria();
//            //根据上会id查询
//            if(meetingId != null){
//                criteria.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example);
//            List<HashMap<String,Object>> yitilist = new LinkedList<HashMap<String,Object>>();
//            if(list != null && list.size()>0){
//                mv.addObject("faxxList",list);
//                for (int i = 0; i < list.size(); i++) {
//                    TMeetingIssue tMeetingIssue =  list.get(i);
//                    HashMap<String,Object> map = new HashMap<String, Object>();
//
//                    //查询议题下的所有委员的审批情况
//                    TMeetingVoteExample example1 = new TMeetingVoteExample();
//                    TMeetingVoteExample.Criteria criteria1 = example1.createCriteria();
//                    if(meetingId != null ){
//                        criteria1.andMeetingIdLike(tMeetingIssue.getMeetingId());
//                    }
//                    criteria1.andIssueIdLike(tMeetingIssue.getIssueId());
//                    List<TMeetingVote> tMeetingVoteslist = meetingFlowService.selecttMeetingVoteByExample(example1);
//                    if (tMeetingVoteslist != null && tMeetingVoteslist.size()>0){
//
//                        map.put("count",tMeetingVoteslist.size());
//                        map.put("tMeetingVoteslist",tMeetingVoteslist);
//                    }
//                    map.put("issueName",tMeetingIssue.getIssueName());
//                    yitilist.add(map);
//                }
//            }
//            //表决结果
//            List<Map<String,Object>> biaojuelist = new LinkedList<Map<String, Object>>();
//            TMeetingIssueExample example1 = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria1 = example1.createCriteria();
//            if (meetingId != null ) {
//                criteria1.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list1 = meetingFlowService.selectTTMeetingIssue(example1);
//            if (list1 != null && list1.size() > 0) {
//                for (int i = 0; i < list1.size(); i++) {
//                    TMeetingIssue tMeetingIssue =  list1.get(i);
//                    Map<String,Object> map = new HashMap<String, Object>();
//
//                    TMeetingVoteExample example4 = new TMeetingVoteExample();
//                    TMeetingVoteExample.Criteria criteria4 = example4.createCriteria();
//                    criteria4.andMeetingIdLike(meetingId);
//                    criteria4.andVoteResultIsNotNull();
//                    criteria4.andIssueIdLike(tMeetingIssue.getIssueId());
//                    int tongyinum = 0;
//                    int xvyinum = 0;
//                    int foujuenum = 0;
//                    int weibiaojuenum =0;
//                    List<TMeetingVote> tMeetinglist = meetingFlowService.selecttMeetingVoteByExample(example4);
//                    for (int j = 0; j <tMeetinglist.size() ; j++) {
//                        TMeetingVote tMeetingVote = tMeetinglist.get(j);
//                        if(tMeetingVote.getVoteResult().equals("4")){
//                            weibiaojuenum++;
//                        } else if(tMeetingVote.getVoteResult().equals("2")){
//                            xvyinum++;
//                        }else if(tMeetingVote.getVoteResult().equals("3")){
//                            foujuenum++;
//                        }else if (tMeetingVote.getVoteResult().equals("1")) {
//                            tongyinum++;
//                        }
//
//                    }
//                    map.put("issueId",tMeetingIssue.getIssueId());
//                    map.put("issueName",tMeetingIssue.getIssueName());
//                    map.put("issueType",tMeetingIssue.getIssueType());
//                    map.put("peopleNum",tMeetinglist.size());
//                    map.put("tongyinum",tongyinum);
//                    map.put("weibiaojuenum",weibiaojuenum);
//                    map.put("xvyinum",xvyinum);
//                    map.put("foujuenum",foujuenum);
//                    map.put("committeeOpinion",tMeetingIssue.getCommitteeOpinion());
//                    map.put("boardOpinion",tMeetingIssue.getBoardOpinion());
//                    map.put("zongjingliOpinion",tMeetingIssue.getZongjingliOpinion());
//                    if(tongyinum>=5 && (double)tongyinum/(tMeetinglist.size())>=(2.0/3)){
//                        map.put("result","同意");
//                    }else if((double)foujuenum/(tMeetinglist.size())>=(1.0/3)){
//                        map.put("result","否决");
//                    }else{
//                        map.put("result","再议");
//                    }
//                    biaojuelist.add(map);
//                }
//            }
//            mv.addObject("biaojuelist", biaojuelist);
//
//            mv.addObject("yitilist",yitilist);
//            mv.addObject("meetingId",meetingId);
//            mv.addObject("currentid",currentid);
//            if(currentid.equals("2")){
//                mv.setViewName("flows/meeting/zhurenweiyuan");
//            }else if(currentid.equals("3")){
//                mv.setViewName("flows/meeting/zongjingliapprove");
//            }else if(currentid.equals("4")){
//                mv.setViewName("flows/meeting/dongshizhangapprove");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return mv;
//    }
    /**
     * 会议秘书出正式批复入口
     * iFrame
     * @param request
     * @return
     * @throws Exception
     */
//    @LoginRequired
//    @RequestMapping(value = "/huiyimishuapprove",method = RequestMethod.GET)
//    public ModelAndView huiyiweiyuanApprove(JedaUser currentUser, ModelAndView mv, HttpServletRequest request) {
//        try{
//
//            String meetingId = request.getParameter("meetingId");
//            String[] issuetype = new String[3];
//            TMeetingIssueExample example1 = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria1 = example1.createCriteria();
//            if (meetingId != null ) {
//                criteria1.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example1);
//            SimpleDateFormat sfs = new SimpleDateFormat("yyyy");
//            if(list!=null && list.size()>0){
//                for(int i=0;i<list.size();i++){
//                    TMeetingIssue tMeetingIssue = list.get(i);
//                    String documentNumber_ = request.getParameter("documentNumber_"+tMeetingIssue.getIssueId());
//                    if(tMeetingIssue.getDocumentNumber()==null && documentNumber_ ==null ){
//                        TSysCodeExample example = new TSysCodeExample();
//                        TSysCodeExample.Criteria criteria = example.createCriteria();
//                        criteria.andCodeTypeEqualTo("512");
//                        List<TSysCode> tSysCodes = meetingFlowService.selectTSysCodeByExample(example);
//                        if(tSysCodes!=null){
//                            TSysCode sysCode = tSysCodes.get(0);
//                            tMeetingIssue.setDocumentNumber(sfs.format(new Date())+"年度第"+sysCode.getCodeValue()+"号文");
//                            meetingFlowService.updateByPrimaryKeySelective(tMeetingIssue);
//                            //保存批复文号计数
//                            sysCode.setCodeValue((Integer.parseInt(sysCode.getCodeValue())+1)+"");
//                            meetingFlowService.updateByPrimaryKeySelective(sysCode);
//                        }
//                    }
//                }
//            }
//            //表决结果
//            List<Map<String,Object>> biaojuelist = new LinkedList<Map<String, Object>>();
//            TMeetingIssueExample example2 = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria2 = example1.createCriteria();
//            if (meetingId != null ) {
//                criteria2.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list1 = meetingFlowService.selectTTMeetingIssue(example1);
//            if (list1 != null && list1.size() > 0) {
//                for (int i = 0; i < list1.size(); i++) {
//                    TMeetingIssue tMeetingIssue =  list1.get(i);
//                    //议题类型
//                    issuetype = tMeetingIssue.getIssueType().split(":");
//
//                    Map<String,Object> map = new HashMap<String, Object>();
//
//                    TMeetingVoteExample example4 = new TMeetingVoteExample();
//                    TMeetingVoteExample.Criteria criteria4 = example4.createCriteria();
//                    criteria4.andMeetingIdLike(meetingId);
//                    criteria4.andVoteResultIsNotNull();
//                    criteria4.andIssueIdLike(tMeetingIssue.getIssueId());
//                    int tongyinum = 0;
//                    int xvyinum = 0;
//                    int foujuenum = 0;
//                    int weibiaojuenum =0;
//                    List<TMeetingVote> tMeetinglist = meetingFlowService.selecttMeetingVoteByExample(example4);
//                    for (int j = 0; j <tMeetinglist.size() ; j++) {
//                        TMeetingVote tMeetingVote = tMeetinglist.get(j);
//                        if(tMeetingVote.getVoteResult().equals("4")){
//                            weibiaojuenum++;
//                        } else if(tMeetingVote.getVoteResult().equals("2")){
//                            xvyinum++;
//                        }else if(tMeetingVote.getVoteResult().equals("3")){
//                            foujuenum++;
//                        }else if (tMeetingVote.getVoteResult().equals("1")) {
//                            tongyinum++;
//                        }
//
//                    }
//                    map.put("issueId",tMeetingIssue.getIssueId());
//                    map.put("issueName",tMeetingIssue.getIssueName());
//                    map.put("issueType",tMeetingIssue.getIssueType());
//                    map.put("peopleNum",tMeetinglist.size());
//                    map.put("tongyinum",tongyinum);
//                    map.put("weibiaojuenum",weibiaojuenum);
//                    map.put("xvyinum",xvyinum);
//                    map.put("foujuenum",foujuenum);
//                    map.put("committeeOpinion",tMeetingIssue.getCommitteeOpinion());
//                    map.put("boardOpinion",tMeetingIssue.getBoardOpinion());
//                    map.put("zongjingliOpinion",tMeetingIssue.getZongjingliOpinion());
//                    if(tongyinum>=5 && (double)tongyinum/(tMeetinglist.size())>=(2.0/3)){
//                        map.put("result","同意");
//                    }else if((double)foujuenum/(tMeetinglist.size())>=(1.0/3)){
//                        map.put("result","否决");
//                    }else{
//                        map.put("result","再议");
//                    }
//                    biaojuelist.add(map);
//                }
//            }
//            TMeetingBaseInfo tMeetingBaseInfo = meetingFlowService.findTMeetingBaseInfo(meetingId);
//            if(tMeetingBaseInfo!=null&&!"".equals(tMeetingBaseInfo)){
//                mv.addObject("key",tMeetingBaseInfo.getAuthority());
//            }
//
//            mv.addObject("biaojuelist", biaojuelist);
//            mv.addObject("yitilist",list);
//            mv.addObject("meetingId",meetingId);
//            if("importantflow".equals(issuetype[0])){
//                mv.setViewName("flows/meeting/riskhuiyimishuapprove");
//            }else{
//                mv.setViewName("flows/meeting/huiyimishuapprove");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mv;
//    }

    /**
     * 保存会议秘书填写的审批结果
     * @param currentUser
     * @param request
     */
//    @LoginRequired
//    @RequestMapping(value = "/savehuiyiweiyuanApprove" ,method = RequestMethod.POST)
//    public ModelAndView savehuiyiweiyuanApprove(JedaUser currentUser,HttpServletRequest request,ModelAndView mv,
//                                        String[] issueId) {
//
//        try{
//            if(issueId != null){
//                for (int i = 0; i < issueId.length; i++) {
//                    TMeetingIssue tMeetingIssue = meetingFlowService.findTMeetingIssue(issueId[i]);
//                    tMeetingIssue.setIssueResult(request.getParameter("issueResult_"+tMeetingIssue.getIssueId()));
//                    //tMeetingIssue.setBoardOpinion(request.getParameter("boardOpinion_"+tMeetingIssue.getIssueId()));
//                    tMeetingIssue.setPartyCommitteeOpinion(request.getParameter("partyCommitteeOpinion_"+tMeetingIssue.getIssueId()));
//                    tMeetingIssue.setBoardMeetingOpinion(request.getParameter("boardMeetingOpinion_"+tMeetingIssue.getIssueId()));
//                    tMeetingIssue.setDocumentNumber(request.getParameter("documentNumber_"+tMeetingIssue.getIssueId()));
//                    String replyTime_= request.getParameter("replyTime_"+tMeetingIssue.getIssueId());
//                    DateFormat fa = new SimpleDateFormat("yyyy-MM-dd");
//                    if(replyTime_!=null && !"".equals(replyTime_)){
//                        tMeetingIssue.setReplyTime( fa.parse(replyTime_));
//                    }
//                    meetingFlowService.updateByPrimaryKeySelective(tMeetingIssue);
//                }
//            }
//
//            //返回数据
//            String meetingId = request.getParameter("meetingId");
//            TMeetingIssueExample example1 = new TMeetingIssueExample();
//            TMeetingIssueExample.Criteria criteria1 = example1.createCriteria();
//            if (meetingId != null ) {
//                criteria1.andMeetingIdLike(meetingId);
//            }
//            List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example1);
//
//            //返回领导意见
//            List<Map<String,Object>> biaojuelist = new LinkedList<Map<String, Object>>();
//            if (list != null && list.size() > 0) {
//                for (int i = 0; i < list.size(); i++) {
//                    TMeetingIssue tMeetingIssue =  list.get(i);
//                    Map<String,Object> map = new HashMap<String, Object>();
//
//                    TMeetingVoteExample example4 = new TMeetingVoteExample();
//                    TMeetingVoteExample.Criteria criteria4 = example4.createCriteria();
//                    criteria4.andMeetingIdLike(meetingId);
//                    criteria4.andVoteResultIsNotNull();
//                    criteria4.andIssueIdLike(tMeetingIssue.getIssueId());
//                    int tongyinum = 0;
//                    int xvyinum = 0;
//                    int foujuenum = 0;
//                    int weibiaojuenum =0;
//                    List<TMeetingVote> tMeetinglist = meetingFlowService.selecttMeetingVoteByExample(example4);
//                    for (int j = 0; j <tMeetinglist.size() ; j++) {
//                        TMeetingVote tMeetingVote = tMeetinglist.get(j);
//                        if(tMeetingVote.getVoteResult().equals("4")){
//                            weibiaojuenum++;
//                        } else if(tMeetingVote.getVoteResult().equals("2")){
//                            xvyinum++;
//                        }else if(tMeetingVote.getVoteResult().equals("3")){
//                            foujuenum++;
//                        }else if (tMeetingVote.getVoteResult().equals("1")) {
//                            tongyinum++;
//                        }
//
//                    }
//                    map.put("issueId",tMeetingIssue.getIssueId());
//                    map.put("issueName",tMeetingIssue.getIssueName());
//                    map.put("issueType",tMeetingIssue.getIssueType());
//                    map.put("peopleNum",tMeetinglist.size());
//                    map.put("tongyinum",tongyinum);
//                    map.put("weibiaojuenum",weibiaojuenum);
//                    map.put("xvyinum",xvyinum);
//                    map.put("foujuenum",foujuenum);
//                    map.put("committeeOpinion",tMeetingIssue.getCommitteeOpinion());
//                    map.put("boardOpinion",tMeetingIssue.getBoardOpinion());
//                    map.put("zongjingliOpinion",tMeetingIssue.getZongjingliOpinion());
//                    if(tongyinum>=5 && (double)tongyinum/(tMeetinglist.size())>=(2.0/3)){
//                        map.put("result","同意");
//                    }else if((double)foujuenum/(tMeetinglist.size())>=(1.0/3)){
//                        map.put("result","否决");
//                    }else{
//                        map.put("result","再议");
//                    }
//                    biaojuelist.add(map);
//                }
//            }
//            //用来控制是否显示董事长意见框
//            TMeetingBaseInfo tMeetingBaseInfo = meetingFlowService.findTMeetingBaseInfo(meetingId);
//            if(tMeetingBaseInfo!=null&&!"".equals(tMeetingBaseInfo)){
//                mv.addObject("key",tMeetingBaseInfo.getAuthority());
//            }
//
//            mv.addObject("biaojuelist", biaojuelist);
//            mv.addObject("yitilist",list);
//            mv.addObject("meetingId",meetingId);
//            mv.setViewName("flows/meeting/huiyimishuapprove");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return mv;
//    }

//    /**
//     * 保存开会开始或结束状态
//     * @param currentUser
//     * @param request
//     */
//    @LoginRequired
//    @RequestMapping(value = "/savemeetingStatus" ,method = RequestMethod.POST)
//    public void savemeetingStatus(JedaUser currentUser,HttpServletRequest request) {
//        try{
//            String meetingId = request.getParameter("meetingId");
//            String biaojue = request.getParameter("biaojue");
//            TMeetingBaseInfo tMeetingBaseInfo = meetingFlowService.findTMeetingBaseInfo(meetingId);
//            if(biaojue.equals("kaishi")){
//                tMeetingBaseInfo.setMeetingStatus("1");
//            }else if(biaojue.equals("jieshu")){
//                tMeetingBaseInfo.setMeetingStatus("2");
//            }
//            meetingFlowService.updatetMeetingBaseInfo(tMeetingBaseInfo);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    /**
     *  查看议题详细信息
     * @return
     */
//    @LoginRequired
//    @RequestMapping(value = "/querytask/{taskid}")
//    public ModelAndView queryvote(@PathVariable String taskid) {
//        ModelAndView mv = new ModelAndView();
//        String schemid = null;
//        TakenTask hisUserTask = this.flowHandler.getHisUserTask(taskid);
//        schemid = hisUserTask.getBusinessKey();
//        if(hisUserTask !=null){
//
//        }else {
//            UserTask userTask = this.flowHandler.getUserTask(taskid);
//            schemid = userTask.getBusinessKey();
//        }
//        mv.setViewName("redirect:/programme/queryAll?flowName=planparallelflow&schemeId="+schemid);
//        TConferenceApplication tConferenceApplication=meetingFlowService.selectTConferenceApplicationByPrimaryKey(schemid);
//        ScProjectScheme scProjectScheme = meetingFlowService.selectScProjectSchemeByPrimaryKey(schemid);
//        TRiskEarlyWarningSignal tRiskEarlyWarningSignal = meetingFlowService.selectTRByPrimaryKey(schemid);
//       if(tConferenceApplication!=null){
//           mv.addObject("conference", tConferenceApplication);
//           mv.setViewName("redirect:/conference/edit?id="+schemid);
//       }else if(scProjectScheme != null){
//           mv.setViewName("redirect:/programme/queryAll?flowName=planparallelflow&schemeId="+schemid);
//       }else if(tRiskEarlyWarningSignal!=null&&!"".equals(tRiskEarlyWarningSignal)){
//           mv.addObject("tRiskEarlyWarningSignal",tRiskEarlyWarningSignal);
//           mv.setViewName("redirect:/riskWarning/putInto/update?workflowKey=2&pkId="+tRiskEarlyWarningSignal.getPkId());
//       }
//
//       return mv;
//    }

    /**
     *  正式批复查看批复金额
     * @return
     */
//    @LoginRequired
//    @RequestMapping(value = "/zhenshipifu")
//    public ModelAndView zhenshipifu(HttpServletRequest request) {
//        ModelAndView mv = new ModelAndView();
//        String issueId = request.getParameter("issueId");
//        String issueBusinssKey = request.getParameter("issueBusinssKey");
//        String isdone2 = request.getParameter("isdone2");
//        TMeetingIssue  tMeetingIssues = meetingFlowService.findTMeetingIssue(issueId);
//        String[] issuetype = tMeetingIssues.getIssueType().split(":");
//        //方案
//        if(issuetype[0].equals("planmeetingflow")){
//            if(isdone2.equals("2")){
//                mv.setViewName("redirect:/programme/edit?schemeId="+issueBusinssKey+"&status=1");
//            }else{
//                mv.setViewName("redirect:/programme/queryAll?schemeId="+issueBusinssKey);
//            }
//        }else if(issuetype[0].equals("assessflow")){//评估
//            mv.setViewName("redirect:/assessmentmgr/employmentagency/employmentagency?applyCode="+issueBusinssKey);
//        }else if(issuetype[0].equals("assess1flow")){//评估二次上会
//            mv.setViewName("redirect:/assessmentmgr/assessmentresult/assessmentresult?applyCode="+issueBusinssKey);
//        }else if(issuetype[0].equals("auditflow")){//审计
//            mv.setViewName("redirect:/assessmentmgr/employmentaudit/employmentaudit?applyCode="+issueBusinssKey);
//        }else if(issuetype[0].equals("confapplication")){
//            mv.setViewName("redirect:/conference/edit?id="+issueBusinssKey);
//        }
//        return mv;
//    }

    /**
     * 保存缺席人员状态
     * @param currentUser
     * @param request
     */
//    @LoginRequired
//    @RequestMapping(value = "/saveAbsence" ,method = RequestMethod.POST)
//    public void saveAbsence(JedaUser currentUser,HttpServletRequest request,HttpServletResponse response) {
//        try{
//            String meetingId = request.getParameter("meetingId");
//            String userId = request.getParameter("userId");
//            String issueId = request.getParameter("issueId");
//            TMeetingVoteExample example = new TMeetingVoteExample();
//            TMeetingVoteExample.Criteria criteria = example.createCriteria();
//            criteria.andMeetingIdEqualTo(meetingId).andUserIdLike(userId).andIssueIdEqualTo(issueId);
//            List<TMeetingVote> meetingVoteList = meetingFlowService.selecttMeetingVoteByExample(example);
//            if(meetingVoteList != null && meetingVoteList.size()>0){
//                TMeetingVote tMeetingVote =  meetingVoteList.get(0);
//                if(tMeetingVote.getVoteResult() == null || "4".equals(tMeetingVote.getVoteResult())){
//                    tMeetingVote.setVoteResult("4");
//                    meetingFlowService.update(tMeetingVote);
//                }else{
//                    String[] oteResult={"4"};
//                    response.getWriter().print(JSONArray.fromObject(oteResult));
//                }
//
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @LoginRequired
//    @ResponseBody
//    @RequestMapping(value = "/sendvalite" ,method = RequestMethod.GET)
//    public String sendvalite(HttpServletRequest request,HttpServletResponse response,
//                           @RequestParam(value = "taskDefinitionKey") String taskDefinitionKey,
//                           @RequestParam(value = "meetingId") String meetingId){
//        String result="";
//        try{
//            if (taskDefinitionKey.equals("huiyimishuappprove")) {
//                TMeetingIssueExample example1 = new TMeetingIssueExample();
//                TMeetingIssueExample.Criteria criteria1 = example1.createCriteria();
//                if (meetingId != null ) {
//                    criteria1.andMeetingIdLike(meetingId);
//                }
//                List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example1);
//                if(list!=null && list.size()>0){
//                    for(int k=0;k<list.size();k++){
//                        TMeetingIssue tMeetingIssue =list.get(k) ;
//                        HashMap<String,String> map = new HashMap();
//                        if(tMeetingIssue.getDocumentNumber()==null){
//                            result = tMeetingIssue.getIssueName()+":批复文号未填写或未保存，请确认！";
//                            return result;
//                        }
//                        if(tMeetingIssue.getReplyTime()==null){
//                            result = tMeetingIssue.getIssueName()+":批复时间未填写或未保存，请确认！";
//                            return result;
//                        }
//                        //判断附件是否已经上传
//                        TArchivesExample example2 = new TArchivesExample();
//                        TArchivesExample.Criteria criteria2 = example2.createCriteria();
//                        if (tMeetingIssue.getIssueBusinssKey() != null && !"".equals(tMeetingIssue.getIssueBusinssKey())) {
//                            criteria2.andBusinessIdEqualTo(tMeetingIssue.getIssueBusinssKey());
//                        }
//                        criteria2.andProjectCodeEqualTo("4") ;
//                        List<TArchives> tArchiveslist = meetingFlowService.selectTArchivesByExample(example2);
//                        if(tArchiveslist == null || tArchiveslist.size()==0) {
//                            result = tMeetingIssue.getIssueName()+":正式批复文件未上传，请确认！";
//                            return result;
//                        }
//                    }
//                }
//            } else if (taskDefinitionKey.equals("sum_vote")) {
//                TMeetingBaseInfo tMeetingBaseInfo = meetingFlowService.findTMeetingBaseInfo(meetingId);
//                if(tMeetingBaseInfo.getMeetingStatus() == null || tMeetingBaseInfo.getMeetingStatus().equals("")) {
//                    result="kaishi";
//                    return result;
//                }else if (tMeetingBaseInfo.getMeetingStatus().equals("1")) {
//                    result="jieshu";
//                    return result;
//                }
//            } else if (taskDefinitionKey.equals("zhurenweiyuan")) {
//                TMeetingIssueExample example1 = new TMeetingIssueExample();
//                TMeetingIssueExample.Criteria criteria1 = example1.createCriteria();
//                if (meetingId != null ) {
//                    criteria1.andMeetingIdLike(meetingId);
//                }
//                List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example1);
//                if(list!=null && list.size()>0){
//                    for(int k=0;k<list.size();k++){
//                        TMeetingIssue tMeetingIssue =list.get(k) ;
//                        if(tMeetingIssue.getCommitteeOpinion()==null){
//                            result = tMeetingIssue.getIssueName()+":主任委员意见未填写或未保存，请确认！";
//                            return result;
//                        }
//                    }
//                }
//            }else if (taskDefinitionKey.equals("zongjingli")) {
//                TMeetingIssueExample example1 = new TMeetingIssueExample();
//                TMeetingIssueExample.Criteria criteria1 = example1.createCriteria();
//                if (meetingId != null ) {
//                    criteria1.andMeetingIdLike(meetingId);
//                }
//                List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example1);
//                if(list!=null && list.size()>0){
//                    for(int k=0;k<list.size();k++){
//                        TMeetingIssue tMeetingIssue =list.get(k) ;
//                        if(tMeetingIssue.getZongjingliOpinion()==null){
//                            result = tMeetingIssue.getIssueName()+":总经理意见未填写或未保存，请确认！";
//                            return result;
//                        }
//                    }
//                }
//            }else if (taskDefinitionKey.equals("dongshizhang")) {
//                TMeetingIssueExample example1 = new TMeetingIssueExample();
//                TMeetingIssueExample.Criteria criteria1 = example1.createCriteria();
//                if (meetingId != null ) {
//                    criteria1.andMeetingIdLike(meetingId);
//                }
//                List<TMeetingIssue> list = meetingFlowService.selectTTMeetingIssue(example1);
//                if(list!=null && list.size()>0){
//                    for(int k=0;k<list.size();k++){
//                        TMeetingIssue tMeetingIssue =list.get(k) ;
//                        if(tMeetingIssue.getBoardOpinion()==null){
//                            result = tMeetingIssue.getIssueName()+":董事长意见未填写或未保存，请确认！";
//                            return result;
//                        }
//                    }
//                }
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return result;
//    }

    /**
     * 保存审批权限
     * @param currentUser
     * @param request
     */
//    @LoginRequired
//    @RequestMapping(value = "/saveAuthority" ,method = RequestMethod.POST)
//    public ModelAndView saveAuthority(JedaUser currentUser,HttpServletRequest request, ModelAndView mv) {
//        try{
//            //保存后返回数据
//            String meetingId = request.getParameter("meetingId");
//            String authority = request.getParameter("authority");
//            TMeetingBaseInfo tMeetingBaseInfo = meetingFlowService.findTMeetingBaseInfo(meetingId);
//            if(tMeetingBaseInfo!=null&&!"".equals(tMeetingBaseInfo)){
//                tMeetingBaseInfo.setAuthority(authority);
//            }
//            meetingFlowService.updatetMeetingBaseInfo(tMeetingBaseInfo);
//            mv.addObject("message","保存审批权限成功！");
//        }catch (Exception e){
//            e.printStackTrace();
//            mv.addObject("message","保存审批权限失败！");
//        }
//        return mv;
//    }

    /**
     * 获取审批权限
     * @param currentUser
     * @param request
     */
//    @LoginRequired
//    @RequestMapping(value = "/findAuthority" ,method = RequestMethod.POST)
//    public ModelAndView findAuthority(JedaUser currentUser,HttpServletRequest request, ModelAndView mv) {
//        try{
//            //保存后返回数据
//            String meetingId = request.getParameter("meetingId");
//            TMeetingBaseInfo tMeetingBaseInfo = meetingFlowService.findTMeetingBaseInfo(meetingId);
//            if(tMeetingBaseInfo!=null&&!"".equals(tMeetingBaseInfo)){
//                String authority = tMeetingBaseInfo.getAuthority();
//                mv.addObject("authority",authority);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            mv.addObject("message","获取审批权限失败！");
//        }
//        return mv;
//    }
}
