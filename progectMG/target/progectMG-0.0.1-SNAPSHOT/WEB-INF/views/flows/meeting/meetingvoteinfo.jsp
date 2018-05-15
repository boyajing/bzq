<%--
  Created by IntelliJ IDEA.
  User: zhaojunhai
  Date: 2017/4/18
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
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
<script type="text/javascript" src="<%=path%>/js/WindowTracking.js"></script>
<script type="text/javascript">
    $(function(){
        if(${issueType=='方案'}){
            $("#fangan").show();
        }
    });
    function closeit() {
        window.parent.close();
    }
    function save() {
        $("#workflowActionCode").val("6");
        $("#workflowActionName").val("finish");//特殊发送，直接完成当前任务
        $("#actionCode").val("send");
        if (confirm("确认表决吗?")) {
            $("#meetingVoteForm").submit();
            window.parent.close();
        }
    }
    function vote(){
        var voteOption = "${voteOption}";
        if(voteOption=="4"){
            alert("会议秘书已经将您设置为缺席，请联系会议秘书！");
        }else if (confirm("确认表决吗?")) {
            var voteOption = $('input[name=voteOption]:checked').val();
            var voteDesc = $('#voteDesc').val();
            var issueId = $('#issueId').val();
            var meetingId = $('#meetingId').val();
            var meetingStatus = $('#meetingStatus').val();

            if(meetingStatus!="1"){
                alert("上会未开始，请等待！");
                window.close();
            }else if(meetingStatus=="2"){
                alert("上会已经结束，请联系秘书！");
                window.close();
            } else {
                $.ajax({
                    url: "<%=path%>/meetingflow/saveVote",
                    type: "post",
                    async: false,
                    dataType:'json',
                    data: {voteOption: voteOption,voteResult:voteDesc,issueId:issueId,meetingId:meetingId,date:new Date()},
                    success: function (e) {

                    }
                });
            window.opener.setVote(issueId,voteOption,voteDesc);
            window.close();
        }
        }
    }
    function finalvote(){
        if (confirm("确认最终决议吗?")) {
            var voteOption = $('input[name=voteOption]:checked').val();
            var voteDesc = $('#voteDesc').val();
            var issueId = $('#issueId').val();
            var meetingId = $('#meetingId').val();
            $.ajax({
                url: "<%=path%>/meetingflow/savemishuVote",
                method: "post",
                async: false,
                data: {voteOption: voteOption,voteDesc:voteDesc,issueId:issueId,meetingId:meetingId},
                success: function (e) {
                }
            });
            window.opener.setVote(issueId,voteOption,voteDesc);
            window.close();
        }
    }
    function send() {
        if (confirm("确认表决并发送吗?")) {
            var url = "<%=request.getContextPath()%>/workflow/web/action/selectUser/selectUser?workflowActionName=send&taskId=${taskId}&clyjSel=2&taskTypeId=${taskTypeId}&workflowId=${wfid}";
            launchWin("450", "350", url, "yes", window, "no");
        }
    }
    function result() {
        var url = "<%=request.getContextPath()%>/businessapproval/project/savehy?workflowId=${wfid}";
        window.open(url, "frame", "frame");
    }
    $(function () {
        initTable(null, 0);
        if (${taskStatus==82}) {
            $('input[type!=hidden],button[id!=close],textarea').attr('disabled', true);
        }
    })
    function show(select) {
        alert("select:"+select.val());
        $("#desc").html(select);
    }
</script>
<head>
    <title>会议表决单</title>

</head>

<body>
<div class="container-fluid">
    <div style="height:10px"></div>
    <div class="row-fluid">
        <c:if test="${FON=='FINAL'}">
            <button type="button" onClick="finalvote();">确定</button>
        </c:if>
        <c:if test="${FON=='NOMAL'}">
            <button type="button" onclick="vote();">委员确定</button>
        </c:if>
    </div>
    <div style="height:10px"></div>
    <div style="text-align: center"><b>汇总表决</b></div>
    <b>会议表决</b>

    <div style="height:10px"></div>
    <div class="row-fluid">
        <form id="meetingVoteForm">
            <input type="hidden" id="meetingStatus" name="meetingStatus" value="${meetingStatus}"/>
            <input type="hidden" id="issueId" name="issueId" value="${tMeetingIssues.issueId}"/>
            <input type="hidden" id="meetingId" name="meetingId" value="${tMeetingIssues.meetingId}"/>
            <div id="fangan" style="display: none">
                <table class="well table resultTable table-hover">
                    <tr>
                        <th rowspan="4" width="15%">项目要素</th>
                    <tr>
                        <td rowspan="1" width="10%">交易结构</td>
                        <td ><textarea cols="400" rows="2" maxlength="1300" style="width: 98%"  style="overflow-y:hidden;" readonly>${scProject.traStructure}</textarea></td>
                    </tr>
                    <tr>
                        <td >出账条件</td>
                        <td ><textarea cols="400" rows="2" maxlength="1300" style="width: 98%"  style="overflow-y:hidden;" readonly>${scProject.condEntry}</textarea></td>
                    </tr>
                    <tr>
                        <td >投后管理要求</td>
                        <td ><textarea cols="400" rows="2" maxlength="1300" style="width: 98%"  style="overflow-y:hidden;" readonly>${scProject.postManageReq}</textarea></td>
                    </tr>
                </table>

                <div>
                    <table class="table table-bordered table-condensed table-striped table-hover">
                        <iframe id="iframe_relate_view" frameBorder=0 scrolling=auto style="width:100%"  height="100%" src="<%=path%>/fileManager/index_view?businessId=${scProject.schemeId}&projectCode=${scProject.projectCode}&businessType=file_12" allowtransparency="true" style="background-color:transparent" ></iframe>
                    </table>
                </div>
            </div>
            <div>
            <table class="well table resultTable table-hover">
                <tr>
                    <td colspan="1">议题名称</td>
                    <td colspan="1"><input type="text" value="${tMeetingIssues.issueName}" readonly/></td>
                    <td colspan="1">议题类型</td>
                    <td colspan="1"><input type="text" value="${issueType}" readonly/></td>
                </tr>
                <tr>
                    <td colspan="1">表决意见</td>
                    <td colspan="1">

                        <c:forEach var="item" items="${bjyj}">
                            <input type="radio" name="voteOption"
                                   <c:if test="${item.codeValue==voteOption}">checked</c:if>
                                   value="${item.codeValue}" onchange="show(this)">${item.codeName}&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr id="Desc">
                    <td colspan="1" align="center">阐述意见</td>
                    <td colspan="3">
                        <textarea id="voteDesc" style="width: 98%;height: 98%" rows="10" cols="100"
                                  name="voteDesc">${voteDesc}</textarea>
                    </td>
                </tr>
            </table>
            </div>
        </form>
    </div>

    <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</div>
</body>
</html>
