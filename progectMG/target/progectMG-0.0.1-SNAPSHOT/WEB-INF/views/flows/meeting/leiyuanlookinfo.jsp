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
    $(function () {
        /* $("table").attr("class", "well table resultTable table-hover");*/
        initTable(null, 0);

    });
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
<head>
    <title>列员会议表决单</title>

</head>

<body>
<div class="container-fluid">
    <div style="height:10px"></div>
    <div class="row-fluid">
    </div>
    <div style="height:10px"></div>
    <div style="text-align: center"><b>列员查看单</b></div>
    <b>列员查看的项目</b>
    <div style="height:10px"></div>
    <div class="row-fluid">
        <form id="meetingVoteForm" action="" method="post">
            <input type="hidden" id="workflowId" name="workflowId" value="${workflowId}"/>
            <input type="hidden" id="meetingId" name="meetingId" value="${meetingId}"/>
            <div id="ONLDIV">
                <table id="votetable"  class="well table resultTable table-hover">
                    <thead>
                    <th style="width:5%;">序号</th>
                    <th style="width:20%;">议题名称</th>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${faxxList}" varStatus="vs">
                        <tr>
                            <td>${vs.count}</td>
                            <td>
                                <input type="hidden" name="issueId" value="${item.issueId}" />
                                <a href='javascript:void(0);' onclick='openbusiness("${item.issueId}");' >${item.issueName}</a>
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
