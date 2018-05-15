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
    <title>出账申请审批查询</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
    <script type="text/javascript" src="<%=path%>/js/WindowTracking.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
    <script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
    <script src="<%=path%>/js/openr.js"  type="text/javascript"></script>
    <script type="text/javascript">

        //出账审批
        function switchRoad(){
            var temp = $("input[name='radio']:checked");
            var projectCode= temp.attr("projectCode");
            if (temp.length == 1) {
                if(temp.val().split("@@")[1]==''||temp.val().split("@@")[1]!='0'){
                    alert("请选择审核状态为“未申请出账”的项目");
                    return false;
                }
            } else {
                alert("请选中一条记录！");
                return;
            }

            var type = projectCode.substring(0,1);

            var projectflow;
            if(type=="T"){
                tzReport();
            }
            if(type=="B"){
                blReport();
            }
        }
        //出账档案查询
        function queryAll(temp) {
            window.open("<%=path%>/fileManager/index_view?businessType=file_6&businessId="+temp, "出账档案管理", "height=800,width=1500,top=100,left=300,toolbar=no,menubar=no,scrollbars=yes, resizable=1,location=no, status=no");
        }
        //查询
        function query() {
            $("#queryForm").attr("action", "<%=path%>/archives/expendfile/expendApprove").submit();
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
                    <%--<td>项目编号：</td>--%>
                    <%--<td><input name="projectCode" type="text" value="${projectCode}"/></td>--%>
                    <%--<td>项目名称：</td>--%>
                    <%--<td><input name="projectName" type="text" value="${projectName}"/></td>--%>
                    <%--<input type="hidden" name="type" value="${type}">--%>
                <%--</tr>--%>
            <%--</table>--%>
        <%--</div>--%>
    <%--</form>--%>
    <%--<div class="row-fluid">--%>
        <%--<button type="button" onClick="elastic('queryframe');">查询条件</button>--%>
        <%--<button type="button" onclick="query();">查询提交</button>--%>
        <%--&lt;%&ndash;<button type="button" onclick="approval();">不良资产包新增</button>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<button type="button" onclick="approvalRegroup();">不良资产重组新增</button>&ndash;%&gt;--%>
        <%--<c:if test="${type==0}">--%>
            <%--<button type="button" onClick="switchRoad();">发起审批</button>--%>
        <%--</c:if>--%>

    <%--</div>--%>
    <div class="row-fluid">
        <button type="button" onclick="goback();">返回项目信息列表</button>
    </div>
    <div style="height:10px"></div>
    <div class="row-fluid">
        <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
            <tr>
                <%--<th rowspan="1"></th>--%>
                <th rowspan="1">出账编号</th>
                <th rowspan="1">项目编号</th>
                <th rowspan="1">项目名称</th>
                <th rowspan="1">资产类型</th>
                <th rowspan="1">产品类型</th>
                <th rowspan="1">交易对手名称</th>
                <th rowspan="1">项目审核状态</th>
                <%--<th rowspan="1">详情</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <%--<td><input type="radio" value="${item.projectCode}@@${item.chargeStatus}@@${item.schemeId}@@${item.underwritingStatus}" name="radio" projectCode="${item.projectCode}" projectName="${item.projectName}" counterpartyName="${item.counterpartyName}" /></td>--%>
                    <td><a onclick="queryAll( '${item.pkCode}')">${item.pkCode}</a></td>
                    <td>${item.projectCode}</td>
                    <td>${item.projectName}</td>
                    <td><nt:codeValue index="${item.productCode}" ctype="003"></nt:codeValue></td>
                    <td><nt:codeValue index="${item.productName}" ctype="${item.productCode}"></nt:codeValue></td>
                    <td>${item.counterpartyName}</td>
                    <td>
                        <c:if test="${item.chargeStatus=='0'}">未申请出账</c:if>
                        <c:if test="${item.chargeStatus=='3'}">出账审批中</c:if>
                        <c:if test="${item.chargeStatus=='1'}">已完成出账</c:if>
                    </td>
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