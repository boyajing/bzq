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
<script src="<%=path%>/js/openr.js"  type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/flow/sendvalidate.js"></script>
<script type="text/javascript">
    function openSeluctuser(nameEn,actionCode){
        var url = "<%=request.getContextPath()%>/workflow/web/action/selectUser/selectUser?workflowActionName=" + nameEn + "&taskId=${taskId}&clyjSel=" + actionCode + "&taskTypeId=${taskTypeId}&workflowId=${wfid}";
        launchWin("450", "350", url, "yes", window, "no");
    }
    function closeit() {
        window.parent.close();
    }
    function save() {
        if (confirm("确认完成表决吗?")) {
            var votes = $('#votetable').find('tr');
            var flag = false;
            for(var i=1;i<votes.length;i++){
                if($.trim(votes.eq(i).find('td').eq(2).text())==''){
                    flag=true;
                    break;
                }
            }
            if(flag){
                alert("您还有方案未表决！请表决完所有的项目后完成表决");
            }else{
                $("#meetingVoteForm").submit();
                window.parent.close();
            }
        }
    }
    function send() {
        <c:if test="${isOnline}">
            if (confirm("确认表决并发送吗?")) {
                var url = "<%=request.getContextPath()%>/workflow/web/action/selectUser/selectUser?workflowActionName=send&taskId=${taskId}&clyjSel=2&taskTypeId=${taskTypeId}&workflowId=${wfid}";
                launchWin("450", "350", url, "yes", window, "no");
            }
        </c:if>
        <c:if test="${!isOnline}">
        if (confirm("确认查阅吗?")) {
            var url = "<%=request.getContextPath()%>/workflow/web/action/selectUser/selectUser?workflowActionName=send&taskId=${taskId}&clyjSel=2&taskTypeId=${taskTypeId}&workflowId=${wfid}";
            launchWin("450", "350", url, "yes", window, "no");
        }
        </c:if>
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
    function openVote(meetingId,issueId){
        window.open("<%=path%>/meetingflow/showVote?meetingId="+meetingId+"&issueId="+issueId+"", "frame", "toolbar=no,menubar=no,scrollbars=yes");
    }
    //返回委员审批的意见
    function setVote(issueId,option,desc){
        <c:forEach var="bj" items="${bjyj}">
                var opts =${bj.codeValue}+"";
                if(opts==option){
                    $("#option_"+issueId).html("${bj.codeName}");
                }
        </c:forEach>
        $("#desc_"+issueId).html(desc);
    }
    function openFaxx(wfid){
        $.ajax({
            url: "<%=path%>/meeting/queryWorkflowType",
            type: "post",
            async: false,
            data: {workflowId: wfid},
            success: function (e) {
                if(e!='error'){
                    var workflowType = e.substr(0,4);
                    var url ='<%=path%>/workflow/action/framework/frame?workflowType='+workflowType+'&workflowId='+wfid+'&taskId=&actionCode=loadForm&taskTypeId='+e;
                    launchWin(screen.width, screen.height, url, "yes", window, "yes");
                }
            }
        });

    }
    //处理打开查看议题详细信息的方法
    function openbusiness(taskid){
        openWin({
            url:"<%=path%>/meetingflow/querytask/"+taskid
            , width: screen.width
            , height: screen.height-100
        });
    }
</script>
<head>
    <title>委员会议表决单</title>

</head>

<body>
<div class="container-fluid">
    <div style="height:10px"></div>
    <div class="row-fluid">
    </div>
    <div style="height:10px"></div>
    <div style="text-align: center"><b>委员表决单</b></div>
    <b>委员表决的项目</b>
    <div style="height:10px"></div>
    <div class="row-fluid">
        <form id="meetingVoteForm" action="<%=path%>/meeting/showVote" method="post">
            <input type="hidden" id="workflowId" name="workflowId" value="${workflowId}"/>
            <input type="hidden" id="meetingId" name="meetingId" value="${meetingId}"/>
            <div id="ONLDIV">
                <table id="votetable"  class="well table resultTable table-hover">
                    <thead>
                        <th style="width:5%;">序号</th>
                        <th style="width:20%;">议题名称</th>
                        <th style="width:10%;">表决意见</th>
                        <th style="width:50%;">意见阐述</th>
                        <th style="width:15%">操作</th>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${faxxList}" varStatus="vs">
                            <tr>
                                <td>${vs.count}</td>
                                <td>
                                    <input type="hidden" name="issueId" value="${item.issueId}" />
                                    <a href='javascript:void(0);' onclick='openbusiness("${item.issueId}");' >${item.issueName}</a>
                                </td>
                                <td id="option_${item.issueId}">
                                    <c:forEach var="vo" items="${vote}">
                                        <c:if test="${vo.issueId==item.issueId}">
                                            <c:forEach var="bj" items="${bjyj}">
                                       <c:if test="${bj.codeValue==vo.voteResult}">${bj.codeName}</c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td id="desc_${item.issueId}">
                                    <c:forEach var="vo" items="${vote}">
                                        <c:if test="${vo.issueId==item.issueId}">
                                            <textarea  maxlength="1300" style="width: 98%" rows="5" cols="100" readonly >${vo.voteOpinion}</textarea>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <button type="button" onclick="openVote('${item.meetingId}','${item.issueId}')">表决</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
    </form>
    </div>
    <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</div>
</body>
</html>
