<%--
  Created by IntelliJ IDEA.
  User: xiongyiwu
  Date: 2016/6/12
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/nttags/nantian.tld" prefix="nt" %>
<%
    String path = request.getContextPath();
    int num = 1;
%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
 <%--<script type="text/javascript" src="<%=path%>/js/date/jquery-1.10.1.js"></script>--%>
<script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
<script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
<script type="text/javascript" src="<%=path%>/resources/_ScriptLibrary/WindowTracking.js"></script>
<script src="<%=path%>/js/openr.js"  type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/flow/sendvalidate.js"></script>
<head>
    <title>会议秘书汇总表决结果</title>

</head>
<script type="text/javascript">
    var isdone2;
    var flowid;
    var taskId;
    function viewinfo() {
        var url = "<%=request.getContextPath()%>/meetingflow/queryvote?meetingId=${meetingId}";
        window.open(url, "frame", "frame");
    }
    function send() {
        var votes = $('#votetable').find('tr');
        var flag = false;
        if (confirm("您确认结束表决？此操作将结束其他未表决人员及列席人员的流程!")) {
            for(var i=1;i<votes.length;i++){
                if($.trim(votes.eq(i).find('td').eq(2).text())==''){
                    flag=true;
                    break;
                }
            }
            if(flag){
                alert("您还有方案未形成决议，请选择方案填写最终决议");
            }else{
                var url = "<%=request.getContextPath()%>/workflow/web/action/selectUser/selectUser?workflowActionName=send&taskId=${taskId}&clyjSel=2&taskTypeId=${taskTypeId}&workflowId=${wfid}";
                launchWin("450", "350", url, "yes", window, "no");
            }
        }
    }
    function closeit() {
        window.parent.close();
    }
    $(function () {
        $("table").attr("class", "well table resultTable table-hover");
    })
    function openVote(meetingId,issueId){
        window.open("<%=path%>/meeting/showmishuVote?meetingId="+meetingId+"&issueId="+issueId+"", "frame", "toolbar=no,menubar=no,scrollbars=yes");
    }
    function setVote(issueId,option,desc){
        <c:forEach var="bj" items="${bjyj}">
        var opts =${bj.codeValue}+'';
        if(opts==option){
            $('#option_'+issueId).html("${bj.codeName}");
        }
        </c:forEach>
        $('#desc_'+issueId).html(desc);
    }
    
    //开始表决
    function savemeetingStatus(){
        var meetingId=$("#meetingId").val();
        var biaojue="kaishi";
        //alert(meetingId);
        if(confirm("是否确定委员开始表决？")){
            $.ajax({
                url: "<%=path%>/meetingflow/savemeetingStatus",
                type: "post",
                async: false,
                data: {meetingId:meetingId,biaojue:biaojue},
                success: function (e) {
                }
            });
        }

    }

    //结束表决
    function endmeetingStatus(){
        var meetingId=$("#meetingId").val();
        if(confirm("是否确定结束表决？")){
            $.ajax({
                url: "<%=path%>/meetingflow/ajaxShuApprove",
                type: "post",
                async: false,
                dataType:'json',
                data: {meetingId:meetingId},
                success: function (data) {
                    var i=0;
                    var t=0;
                    for(var k=0;k<data.length;k++) {
                        var tMeetingVotes=data[k].tMeetingVoteslist;
                        for(var m=0;m<tMeetingVotes.length;m++){
                            if(tMeetingVotes[m].voteResult=="1" || tMeetingVotes[m].voteResult=="2" || tMeetingVotes[m].voteResult=="3" ||
                                    tMeetingVotes[m].voteResult=="4"){
                                t++;
                            }
                            if(!tMeetingVotes[m].voteResult){
                                i++;
                            }
                        }
                        if(t<5){
                            alert(data[k].issueName+":表决人数小于五人，请确认！");
                            return;
                        }
                        if(i>0){
                            alert(data[k].issueName+":没有表决完！");
                        }
                    }
                    if(i==0){
                        var biaojue="jieshu";
                        $.ajax({
                            url: "<%=path%>/meetingflow/savemeetingStatus",
                            type: "post",
                            async: false,
                            data: {meetingId:meetingId,biaojue:biaojue},
                            success: function (e) {
                            }
                        });
                    }
                }
            });
        }
    }

    function onloadc(obj) {
        if(isdone2==true){
            openWin({
                url: "<%=path%>/fileManager/index_view?businessId=" + obj + "&businessType=file_7"
                , width: screen.width
                , height: screen.height-100
            });
        }else {
            openWin({
                url: "<%=path%>/fileManager/index?businessId=" + obj + "&businessType=file_7"
                , width: screen.width
                , height: screen.height - 100
            });
        }
    }

    //处理打开查看议题详细信息的方法
    function openbusiness(taskid){
        openWin({
            url:"<%=path%>/meetingflow/querytask/"+taskid
            , width: screen.width
            , height: screen.height-100
        });
    }

    function absence(userId,issueId) {
        if(isdone2!=true){
            var meetingId=$("#meetingId").val();
            if(confirm("是否将该参会人员设置为缺席？")){
                $.ajax({
                    url: "<%=path%>/meetingflow/saveAbsence",
                    type: "post",
                    async: false,
                    dataType: 'json',
                    data: {meetingId: meetingId,userId:userId,issueId:issueId},
                    success: function (data) {
                        //alert(data);
                        if(data=='4'){
                            alert("该参会委员已经表决，请确认！");
                        }
                    }
                });
            }
        }
    }

    function checkSend(taskDefinitionKey){
        if(!confirm("请确保在点击发送按钮前选择了审批权限！")){
            return;
        }
        else{
            //将审批权限信息存入会议信息库中
            var temp = $("input[name='authority']:checked").val();

            $.ajax({
                type: 'POST',
                url: '<%=path%>/meetingflow/saveAuthority.json',
                dataType: 'json',
                async: false,
                data : {
                    meetingId : '${meetingId}',
                    authority : temp
                },
                success: function (data) {
                },
                error: function (user) {
                    alert("保存审批权限失败！");
                }
            });


            //会议发送校验
            var result = 1;
            $.ajax({
                type: 'GET',
                url: '<%=path%>/meetingflow/sendvalite',
                dataType: 'json',
                async: false,
                data:{
                    taskDefinitionKey : taskDefinitionKey,
                    meetingId : '${meetingId}',
                    date:new Date()

                },
                success: function (data) {
                    if(data=='jieshu'){
                        alert("表决未结束，请确认！");
                        result = 2;
                    }else if(data=='kaishi'){
                        alert("表决未开始，请确认！");
                        result = 2;
                    }
                },
                error: function (user) {

                }
            });
            return result;
        }
    }

    function reLoad() {
        window.location.reload();
    }

    function reback() {
        //ajax调用后台逻辑
        $.ajax({
            type: 'POST',
            url: '<%=path%>/flow/takeBack/'+flowid+'/'+taskId+'/sum_vote/zhurenweiyuan.json',
            dataType: 'json',
            async: false,
            data :{},
            success: function (data) {
                if(data.result=="2"){
                    alert("当前流程已经结束不能撤回！");
                    return;
                }
                if(data.result=="3"){
                    alert("当前流程已经不在要撤回的节点，不能撤回！");
                    return;
                }
                if(data.result=="1"){
                    alert("撤回成功！");
                    window.parent.opener.location.reload();
                    window.parent.close();

                }
            },
            error: function (user) {
            }

        });
    }

    function initBytask(taskDefinitionKey,isdone,processid,taskid){
        //已办
        isdone2=isdone;
        flowid = processid;
        taskId = taskid;
        if(isdone==true){
            $("#savemeetingStatus").hide();
            $("#endmeetingStatus").hide();
            $("#reLoad").hide();
            $("input,textarea,select").attr("disabled", "disabled");
            $("#reback").attr("style","display:''")
        }
    }
</script>
<body>
<div class="container-fluid">

    <div style="height:10px"></div>
    <div class="row-fluid">
           <button id="savemeetingStatus" type="button" onClick="savemeetingStatus();">开始表决</button>
           <button id="endmeetingStatus" type="button" onClick="endmeetingStatus();">结束表决</button>
           <button id="reLoad" type="button" onClick="reLoad();">刷新表决结果</button>
    </div>
    <div style="text-align: right">
        <button type="button" id="reback" onClick="reback()" style="display: none">撤回</button>
    </div>
    <div style="text-align: right">
        <td>审批权限:</td>
        <td id="authority">
            <input type="radio" name="authority"  value="1" checked/>董事长
            <input type="radio" name="authority"  value="2"/>总经理
        </td>
    </div>
    <div style="height:10px"></div>
    <b>议题汇总意见</b>
    <div style="height:10px"></div>
    <div class="row-fluid">
        <form id="meetingVote" action="" method="post">
            <input type="hidden" id="meetingId" name="meetingId" value="${meetingId}"/>
            <table id="" class="well table resultTable table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>议题名称</th>
                    <th>表决人员</th>
                    <th>表决结果</th>
                    <th>意见阐述</th>
                    <th>是否请假</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${yitilist}" var="item" varStatus="status">
                    <c:forEach items="${item.tMeetingVoteslist}" var="item1" varStatus="status1">
                    <tr >
                        <c:if test="${status1.first}">
                        <td rowspan="${item.count}"><%=num++%></td>
                        <td rowspan="${item.count}">${item.issueName}</td>
                        </c:if>

                            <td nowrap="nowrap"><nt:username userid="${item1.userId}"></nt:username></td>
                            <td>
                                <c:choose>
                                    <c:when test="${item1.voteResult==1}">
                                        同意
                                    </c:when>
                                    <c:when test="${item1.voteResult==2}">
                                        再议
                                    </c:when>
                                    <c:when test="${item1.voteResult==3}">
                                        否决
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                    ${item1.voteOpinion}
                            </td>
                        <td>
                            <button type="button" onclick="absence('${item1.userId}','${item.issueId}')">请假</button>
                        </td>
                    </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
    <div style="height:10px"></div>
    <b>议题表决意见</b>
    <div style="height:10px"></div>
    <div>
        <table id="votetable">
            <thead>
            <th style="width:5%;">序号</th>
            <th style="width:20%;">议题名称</th>
            <th style="width:40%;">业务审批委员会意见</th>
            <th style="width:10%;">批复草稿</th>

            </thead>
            <tbody>
            <c:forEach var="item" items="${biaojuelist}" varStatus="status">
                <tr>
                    <td style="width:10%;">${status.index+1}</td>
                    <td>
                        <input type="hidden" name="issueId" value="${item.issueId}" />
                        <a href='javascript:void(0);' onclick='openbusiness("${item.issueId}");' >${item.issueName}</a>
                    </td>
                    <td>应参会委员${item.peopleNum}人，实参会委员${item.peopleNum-item.weibiaojuenum}人。
                        同意${item.tongyinum}人，再议${item.xvyinum}人，否决${item.foujuenum}人。
                        表决结果为：${item.result}。
                    </td>
                    <td>
                        <a name="${item.issueId}" onclick="onloadc(this.name);">附件</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div>
        <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
    </div>
</div>
</body>
</html>
