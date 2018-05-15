
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
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
<link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
 <%--<script type="text/javascript" src="<%=path%>/js/date/jquery-1.10.1.js"></script>--%>
<script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
<script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
<script type="text/javascript" src="<%=path%>/resources/_ScriptLibrary/WindowTracking.js"></script>
<script src="<%=path%>/js/openr.js"  type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/flow/sendvalidate.js"></script>
<head>
    <title>方案客户列表</title>

</head>
<script type="text/javascript">

    function closeit() {
        window.parent.close();
    }
    $(function () {
        $("table").attr("class", "well table resultTable table-hover");

    });
    //查询
    function query() {
        $("#queryForm").attr("action", "<%=path%>/meeting/queryyiti").submit();
    }
    //查看客户详细信息
    function queryAll(temp) {
        window.open("<%=path%>/fileManager/index_view?businessType=file_5&businessId="+temp, "核保档案管理", "height=800,width=1500,top=100,left=300,toolbar=no,menubar=no,scrollbars=yes, resizable=1,location=no, status=no");
        <%--location.href = "<%=path%>/fileManager/index_view?businessType=file_3&businessId="+temp;--%>
    }
    //返回
    function goback() {
        window.location.href="<%=path%>/archives/underwritingfile/underwritingProject?maxId=${maxId}";
    }

</script>
<body>
<div class="container-fluid">
    <div style="height:10px"></div>
    <%--<form id="queryForm" method="post">--%>
    <%--<div id="queryframe" style="display:none">--%>
    <%--<table class="well table resultTable table-hover">--%>
    <%--<tr>--%>
    <%--<td>会议次数：</td>--%>
    <%--<td><input name="meetingNumber" type="text" value="${meetingNumber}" class="input-medium"/></td>--%>
    <%--<td>开始时间：</td>--%>
    <%--<td><input   type="text"  name="meetingTimestart" id="meetingTimestartdate"  value="<fmt:formatDate value="${meetingTimestart}" pattern="yyyy-MM-dd"/>" readonly/></td>--%>
    <%--<td>结束时间：</td>--%>
    <%--<td><input   type="text"  name="meetingTimeend" id="meetingTimeenddate"  value="<fmt:formatDate value="${meetingTimeend}" pattern="yyyy-MM-dd"/>" readonly/></td>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <%--</div>--%>
    <%--</form>--%>
    <div class="row-fluid">
        <button type="button" onclick="goback();">返回</button>
    </div>
    <div class="row-fluid">
        <table id="" class="well table resultTable table-hover">
            <div style="text-align: center"><h3>方案客户列表</h3></div>
            <thead>
            <tr>
                <th>客户编号</th>
                <th>客户名称</th>
                <th>客户类型</th>
                <th>客户类别</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="item1" varStatus="status1">
                <tr>
                    <td><a onclick="queryAll('${item1.cid}','${item1.khlx}')">${item1.cid}</a></td>
                    <td>${item1.cname}</td>
                    <td><nt:codeValue index="${item1.khlx}" ctype="064"></nt:codeValue></td>
                    <td>${item1.khlb}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="hidden" name="maxId" value="${maxId}"/>
        <div>
            <%@ include file="../../mypage.jsp" %>
            <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
        </div>
    </div>
</div>
</body>
</html>

