<%--<%@ page import="com.nantian.workflow.common.define.process.UserRoleDefine" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: zhaojunhia
  Date: 2017/4/17
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
%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
<script type="text/javascript" src="<%=path%>/js/WindowTracking.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">

<script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
<script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
<%--显示时分--%>
<script type="text/javascript" src="<%=path%>/js/timepicker/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=path%>/js/timepicker/jquery.ui.datepicker-zh-CN.js.js"></script>
<script type="text/javascript" src="<%=path%>/js/timepicker/jquery-ui-timepicker-zh-CN.js"></script>
<script src="<%=path%>/js/openr.js"  type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/flow/sendvalidate.js"></script>
<script type="text/javascript">

</script>
<head>
    <title>会议信息</title>

</head>
<script type="text/javascript">
    $(function(){
     $("table").attr("class", "well table resultTable table-hover");
        /*if (${taskStatus==82}) {//代办查看hide
            $('input[type!=hidden],button[id!=close],textarea').attr('disabled', true);
        }*/
        //显示时分秒
        jQuery('input[id$=TIME]').datetimepicker({
            timeFormat: "HH:mm:ss",
            dateFormat: "yy-mm-dd"
        });
    })

    function send(){

    }

    //处理打开查看议题详细信息的方法
    function openbusiness(taskid){
        //alert("taskid:"+taskid);
        openWin({
            //url: doTaskURL+$(this).attr("targetURL")+$(this).attr("taskid")
            url:"<%=path%>/meetingflow/querytask/"+taskid
            , width: screen.width
            , height: screen.height-100
        });
    }
</script>
<body>
<div class="container-fluid">
    <div style="height:10px"></div>
    <div class="row-fluid">
           <%-- <button type="button" onClick="send();">发送</button>--%>
    </div>
    <div style="height:10px"></div>

    <div class="row-fluid">
        <form id="meetingForm" action="<%=path%>/meeting/start" method="post">
            <input type="hidden"  id="meetingId" name="meetingId" value="${meetingBase.meetingId}"/>
            <b>会议信息</b>
            <table class="well table resultTable table-hover">
                <tr>
                    <td>会议时间</td>
                    <td><input id="meetingTIME" name="meetingTime" value="<fmt:formatDate value='${meetingBase.meetingTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" readonly/></td>
                    <td>会议地点</td>
                    <td><input type="text" name="meetingAdress" value="${meetingBase.meetingAdress}" readonly/></td>
                </tr>
                <tr>
                    <td>会议次数</td>
                        <td><input type="text" name="meetingNumber" onblur="{if(!isinteger($(this).val())){alert('格式不正确');$(this).val('');}}" value="${meetingBase.meetingNumber}" readonly/></td>
                    <td>会议类型</td>
                    <td>
                        <input type="text" id="meetingType" value="<nt:codeValue index="${meetingBase.meetingType}" ctype="044"></nt:codeValue>" readonly/>
                    </td>
                </tr>
                <tr>
                    <td>会议委员</td>
                    <td colspan="3">
                        <textarea maxlength="600" style="width: 89%;height: 95%;resize: none"
                                  id="meetingExaminer_CN" value="<nt:username userid="${meetingBase.meetingCommittee}"></nt:username>" readonly> <nt:username userid="${meetingBase.meetingCommittee}"></nt:username></textarea>
                        <input type="hidden" id="meetingExaminer_EN" name="meetingCommittee" value="<nt:username userid="${meetingBase.meetingCommittee}"></nt:username>"/>
                    </td>

                </tr>
                <tr>
                    <td>列席人员</td>
                    <td colspan="3">
                        <textarea  maxlength="600" style="width: 89%;height: 95%;resize: none"
                                   id="meetingAttendee_CN" value="${meetingBase.meetingObserver}" readonly>
                            <nt:username userid="${meetingBase.meetingObserver}"></nt:username>
                        </textarea>
                        <input type="hidden" id="meetingAttendee_EN" name="meetingObserver" value="${meetingBase.meetingObserver}"/>
                    </td>
                </tr>
            </table>
            <b>议题信息</b>
            <table id="itemTable">
                <thead>
                    <th style="width: 10%">序号</th>
                    <th style="width: 60%">会议议题</th>
                    <th style="width: 50%">参会人员</th>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${tMeetingIssueslist}" varStatus="vs">
                        <tr>
                            <td>${vs.count}</td>
                            <td>
                            <input type="hidden" name="issueId" value="${item.issueId}" />
                            <a href='javascript:void(0);' onclick='openbusiness("${item.issueId}");' >${item.issueName}</a>
                            </td>
                            <td>
                            <c:forEach var="vo" items="${tMeetingPeoplelist}">
                                <c:if test="${vo.issueId==item.issueId}">
                                    <input type="checkbox"  checked="checked"/><nt:username userid="${vo.userId}"></nt:username>
                                </c:if>
                            </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
    <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</div>
</body>
</html>
