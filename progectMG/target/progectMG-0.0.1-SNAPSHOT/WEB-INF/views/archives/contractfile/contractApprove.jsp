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
    <title>合同信息查询</title>
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
                    <%--<td>项目编号：</td>--%>
                    <%--<td><input name="projectCode" type="text" value="${projectCode}" class="input-medium"/></td>--%>
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
        <%--<button type="button" onclick="goback();">返回</button>--%>
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
                <th rowspan="1">合同编号</th>
                <th rowspan="1">项目编号</th>
                <th rowspan="1">项目名称</th>
                <th rowspan="1">立项日期</th>
                <th rowspan="1">项目类型</th>
                <th rowspan="1">产品名称</th>
                <th rowspan="1">合同状态</th>
                <th rowspan="1">操作人</th>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <%--<td><input type="radio" value="${item.contractId}" name="radio" projectCode="${item.projectCode}" projectName="${item.projectName}"/></td>--%>
                    <td><a onclick="queryAll( '${item.contractId}')">${item.contractId}</a></td>
                    <td>${item.projectCode}</td>
                    <td>${item.projectName}</td>
                    <td><fmt:formatDate value="${item.cteateTime}" pattern="yyyy-MM-dd"/></td>
                    <td><nt:codeValue index="${item.productCode}" ctype="003"></nt:codeValue></td>
                    <td><nt:codeValue index="${item.productName}" ctype="${item.productCode}"></nt:codeValue></td>
                        <%-- <td>${item.contractStatus}</td>--%>
                        <%--<td>${item.creater}</td>--%>
                    <td><c:if test="${item.contractStatus == '9' }">未审查合同</c:if></td>
                    <td><nt:username userid='${item.creater}'></nt:username></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
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
    //查询
    function query() {
        $("#queryForm").attr("action", "<%=path%>/contract/index").submit();
    }
    //返回
    function goback() {
        <%--window.location.href="<%=path%>/archives/index";--%>
        window.parent.location.href='<%=path%>/archives/index';
    }
    function queryAll(temp) {
        window.open("<%=path%>/archives/contractfile/contractArchives?maxId=${maxId}&contractId="+temp, "合同档案管理", "height=800,width=1500,top=100,left=300,toolbar=no,menubar=no,scrollbars=yes, resizable=1,location=no, status=no");
    }

</script>