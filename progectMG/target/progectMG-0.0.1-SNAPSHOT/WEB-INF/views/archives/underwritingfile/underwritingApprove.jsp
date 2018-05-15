<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/nttags/nantian.tld" prefix="nt" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>核保查询</title>
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
    <script type="text/javascript">
        //查询
        function query() {
            $("#queryForm").attr({action: "<%=path%>/underwriting/index",method: "post"}).submit();
        }
        //返回
        function goback() {
            <%--window.location.href="<%=path%>/archives/index";--%>
            window.parent.location.href='<%=path%>/archives/index';
        }

    </script>
</head>
<body>
<div class="container-fluid">
    <div style="height:10px"></div>
    <%--<form id="queryForm" method="post">--%>
    <%--<div id="queryframe" style="display:none">--%>
    <%--<table class="well table resultTable table-hover">--%>
    <%--<tr>--%>
    <%--<td>批复号</td>--%>
    <%--<td><input type="text" name="replyId" class="input-medium" ></td>--%>
    <%--<td>客户名称</td>--%>
    <%--<td><input name="customerName" type="text"  class="input-medium"/></td>--%>
    <%--<td>交易种类</td>--%>
    <%--<td>--%>
    <%--<select name="transactionType">--%>
    <%--<option value="">请选择</option>--%>
    <%--<nt:code index="${transactionType}" ctype="003"></nt:code>--%>
    <%--</select>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <%--</div>--%>
    <%--</form>--%>
    <%--<div class="row-fluid">--%>
    <%--<button type="button" onclick="report();">发起审批</button>--%>
    <%--<button type="button" onClick="elastic('queryframe');">查询条件</button>--%>
    <%--<button type="button" onclick="query();">查询提交</button>--%>
    <%--<button type="button" onclick="underwriting();">核保申请</button>--%>
    <%--<button type="button" onclick="edit();">修改</button>--%>
    <%--<button type="button" onclick="del();">删除</button>--%>
    <%--</div>--%>
    <div class="row-fluid">
        <button type="button" onclick="goback();">返回项目信息列表</button>
    </div>
    <div style="height:10px"></div>
    <div class="row-fluid">
        <table id="resultTab"   class="table table-bordered table-condensed table-striped table-hover">
            <thead >
            <tr>
                <%--<th rowspan="1">选择</th>--%>
                <th rowspan="1">方案编号</th>
                <th rowspan="1">批复号</th>
                <th rowspan="1">客户名称</th>
                <th rowspan="1">交易种类</th>
                <th rowspan="1">业务金额（万元）</th>
                <th rowspan="1">操作人</th>
                <th rowspan="1">所属机构</th>
                <%--<th rowspan="1">所属部门</th>--%>
                <th rowspan="1">核保状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <%--<td><input type="radio" name="radio" value="${item.applyId}" projectname="${item.projectName}" projectCode="${item.projectCode}"/></td>--%>
                    <td><a onclick="queryAll( '${item.schemeId}')">${item.schemeId}</a></td>
                    <td>${item.replyId}</td>
                    <td>${item.customerName}</td>
                    <td><nt:codeValue index="${item.transactionType}" ctype="003"></nt:codeValue></td>
                    <td><fmt:formatNumber type="number" value="${item.amount/10000}" pattern="#,##0.00"/></td>
                    <td><nt:username userid='${item.creater}'></nt:username></td>
                    <td>${item.orgName}</td>
                        <%--<td>${item.replyId}</td>--%>
                    <td><nt:codeValue index="${item.psus}" ctype="111"></nt:codeValue></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="hidden" name="applyId" value="${item.applyId}"/>
        <input type="hidden" name="maxId" value="${maxId}"/>
    </div>
    <%@ include file="../../mypage.jsp" %>
    <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</div>
</body>
</html>
