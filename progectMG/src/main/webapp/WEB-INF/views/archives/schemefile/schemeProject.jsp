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
<head>
    <title>根据项目编号管理方案</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
    <script type="text/javascript" src="<%=path%>/js/WindowTracking.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
     <%--<script type="text/javascript" src="<%=path%>/js/date/jquery-1.10.1.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
    <script src="<%=path%>/js/openr.js"  type="text/javascript"></script>
</head>
<body>
<div class="container-fluid">
    <div style="height:10px"></div>
    <%--<form id="queryForm" method="post">--%>
        <%--<div id="queryframe" style="display:none">--%>
            <%--<table class="well table resultTable table-hover">--%>
                <%--<tr>--%>
                    <%--<td>方案编号：</td>--%>
                    <%--<td><input name="schemeId" type="text" value="${schemeId}" class="input-medium"/></td>--%>
                    <%--<td>项目名称：</td>--%>
                    <%--<td><input name="projectName" type="text" value="${projectName}" class="input-medium"/></td>--%>
                    <%--<td>项目类型：</td>--%>
                    <%--<td>--%>
                        <%--<select id="productCode" name="productCode">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<nt:code ctype="003"></nt:code>--%>
                        <%--</select>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        <%--</div>--%>
    <%--</form>--%>
    <%--<div class="row-fluid">--%>
        <%--<button type="button" onClick="elastic('queryframe');">查询条件</button>--%>
        <%--<button type="button" onclick="query();">查询提交</button>--%>
    <%--</div>--%>
    <div class="row-fluid">
        <button type="button" onclick="goback();">返回项目信息列表</button>
    </div>

    <div style="height:10px"></div>
    <div class="row-fluid">
        <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
            <tr>
                <%--<th rowspan="1">选择</th>--%>
                <th rowspan="1">方案编号</th>
                <th rowspan="1">项目编号</th>
                <th rowspan="1">项目名称</th>
                <th rowspan="1">立项日期</th>
                <th rowspan="1">项目类型</th>
                <th rowspan="1">产品名称</th>
                <th rowspan="1">方案审查日期</th>
                <th rowspan="1">操作人</th>
                <th rowspan="1">审查状态</th>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <%--<td><input type="radio" value="${item.schemeId},${item.status}" name="radio" projectName="${item.projectName}" projectCode="${item.projectCode}"/></td>--%>
                    <td><a onclick="queryAll( '${item.schemeId}')">${item.schemeId}</a></td>
                    <td>${item.projectCode}</td>
                    <td>${item.projectName}</td>
                    <td><fmt:formatDate value="${item.projectDate}" pattern="yyyy-MM-dd"/></td>
                    <td><nt:codeValue index="${item.productCode}" ctype="003"></nt:codeValue></td>
                        <%--<td><c:if test="${'bl1'==item.productCode}"><nt:codeValue index="${item.productName}" ctype="bl1"></nt:codeValue></c:if>
                            <c:if test="${'tz1'==item.productCode}"><nt:codeValue index="${item.productName}" ctype="tz1"></nt:codeValue></c:if>
                        </td>--%>
                    <td><nt:codeValue index="${item.productName}" ctype="${item.productCode}"></nt:codeValue></td>
                    <td><fmt:formatDate value="${item.cteateTime}" pattern="yyyy-MM-dd"/></td>
                        <%--<td>${item.creater}</td>--%>
                    <td><nt:username userid='${item.creater}'></nt:username></td>
                        <%--<td>${item.status}</td>--%>
                    <td><nt:codeValue index="${item.status}" ctype="111"></nt:codeValue></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%--<input type="hidden" name="maxId" value="${maxId}"/>--%>
    <%@ include file="../../mypage.jsp" %>
    <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</div>
</body>
</html>
<script type="text/javascript">
    //初始化

    $(function () {
        $("table").attr("class", "well table resultTable table-hover");
    });
    $(function () {
        var mes = '${message}';
        if (mes != 'no') {
        }
    });
    //返回
    function goback() {
        window.parent.location.href='<%=path%>/archives/index';
    }
    //查询
    function query() {
        $("#queryForm").attr("action", "<%=path%>/programme/indexFinish").submit();
    }

    function queryAll(temp) {
        //location.href='<%=path%>/projectInvest/queryAll?projectcode='+temp+'';
        location.href="<%=path%>/archives/schemefile/schemeCustom?schemeId="+temp+"&maxId=${maxId}";
    }
</script>