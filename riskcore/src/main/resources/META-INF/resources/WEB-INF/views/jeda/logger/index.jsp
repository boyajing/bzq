<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String cpath = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../../commonhead.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="<%=cpath%>/js/jqueryui/jquery-ui.css"/>
    <script type="text/javascript" src="<%=cpath%>/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=cpath%>/js/lhgcore.js"></script>
    <script type="text/javascript" src="<%=cpath%>/js/lhgcalendar.js"></script>
    <script type="text/javascript" src="<%=cpath%>/js/queryframe.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=cpath%>/css/mypage.css">
    <script type="text/javascript" src="<%=cpath%>/js/date/jquery-1.10.1.js"></script>
    <script type="text/javascript" src="<%=cpath%>/js/date/jquery.ui.js"></script>
    <script type="text/javascript" src="<%=cpath%>/js/date/datecontroller.js"></script>
    <script type="text/javascript" src="<%=cpath%>/js/date/datecontroller.js"></script>
    <title>日志管理</title>
</head>
<body>
<div class="container-fluid">
    <div>
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
            <tr>
                <td height="40" align="center" bgcolor="#0099CC"><span class="font_title">日志管理</span></td>
            </tr>
        </table>
    </div>
    <div style="height: 3px"></div>
    <div id="queryframe" style="display: none">
        <form id="queryForm" method="post" action="<%=cpath%>/jeda/slog/query">
            <table>
                <tr>
                    <td>操作起始日期：</td>
                    <td><input name="startdate" type="text" id="startdate" value="${fn:length(startdate)==0 ? null : startdate }"/></td>
                    <td>操作截止日期：</td>
                    <td><input name="enddate" type="text" id="enddate" value="${fn:length(enddate)==0 ? currentdate : enddate }"/></td>
                </tr>
                <tr>
                    <td>操作人：</td>
                    <td><input name="userid" type="text" value="${userid}"/></td>
                    <td>操作对象：</td>
                    <td><input name="oappmenu" type="text" value="${oappmenu}"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div style="height:3px"></div>
    <div>
        <table>
            <tr>
                <td>
                    <button class="btn" onClick="elastic('queryframe')">查询条件</button>
                    <button onclick="$('#queryForm').submit()" class="btn ">查询提交</button>
                    <%--<a class="btn">导出</a>--%>
                </td>
            </tr>
        </table>
    </div>
    <div style="height: 3px"></div>
    <div>

        <table id="resultTable" class="well table resultTable  table-striped table-hover">
            <thead>
            <tr>
                <th style="width:20px"></th>
                <th style="width:50px">序号</th>
                <th inorder="true" insel="true" column="TradeCntrNo">
                    <span class="tableheadOrder">用户ID</span>
                </th>
                <th inorder="true" insel="true" column="Bondcd">
                    <span class="tableheadOrder">用户姓名</span>
                </th>
                <th inorder="true" insel="true" column="BondShortNm">
                    <span class="tableheadOrder">IP地址</span>
                </th>
                <th inorder="true" insel="true" column="TradeDirection">
                    <span class="tableheadOrder">操作对象</span>
                </th>
                <th inorder="true" insel="true" column="TransactionDate">
                    <span class="tableheadOrder">操作类型</span>
                </th>
                <th inorder="true" insel="true" column="AccountDate">
                    <span class="tableheadOrder">操作结果</span>
                </th>
                <th inorder="true" insel="true" column="Counterparty">
                    <span class="tableheadOrder">操作时间</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="slog" items="${lSlog}" varStatus="status">
                <tr id="<c:out value="${slog.logid}"/>" style="cursor:pointer">
                    <td>
                        <input id="<c:out value="${slog.logid}"/>" type="checkbox">
                    </td>
                    <td>
                        <c:out value="${(pageindex-1)*pagesize+1+status.index}"/>
                    </td>
                    <td column="userid"><c:out value="${slog.userid}"/></td>
                    <td column="username"><c:out value="${slog.username}"/></td>
                    <td column="ipadress"><c:out value="${slog.ipadress}"/></td>
                    <td column="oappmenu"><c:out value="${slog.oappmenu}"/></td>
                    <td column="otype"><c:out value="${slog.otype}"/></td>
                    <td column="renum"><c:out value="${slog.renum}"/></td>
                    <td column="otime"><fmt:formatDate value="${slog.otime}"></fmt:formatDate></td>
                </tr>

            </c:forEach>
            </tbody>
        </table>

    </div> <jsp:include page="../pagefoot.jsp"></jsp:include>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</div>
</body>
</html>
