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
    <title>交易机构池</title>
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
    <script type="text/javascript">
        function create(){
            location.href='<%=path%>/tradInstitutionPool/createCustmor';
        }
        function editCustmor(){
            var temp = $("input[name='checkboxs']:checked");

            if (temp.length < 1) {
                alert("请选中一条记录！");
            } else if (temp.length > 1) {
                alert("每次只能修改一条记录！");
            } else {
                var custmorNo=temp.val();
                window.location.href = "<%=path%>/tradInstitutionPool/updateCustmor?custmorNo="+custmorNo;
            }
        }
        function del(){
            //TODO 删除前判断关联表
            var temp = $("input[name='checkboxs']:checked");
            if (temp.length < 1) {
                alert("请选中一条记录！");
            } else if (temp.length > 1) {
                alert("每次只能删除一条记录！");
            } else {
                $.ajax({
                    url:'<%=path%>/tradInstitutionPool/deleteCustmor',
                    data:{custmorNo:temp.val()},
                    dataType:"json",
                    type: "POST",
                    success:function(data){
                        if(data>0){
                            alert("删除成功！");
                            //window.location.href = "<%=path%>/tradInstitutionPool/index";
                        }else{
                            alert("删除失败！");
                        }
                    },
                    error:function () {
                        alert("生成失败！");
                    }
                });
            }
        }
        function query(){
            $("#queryForm").attr("action", "<%=path%>/tradInstitutionPool/index").submit();
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div style="height:10px"></div>
    <form id="queryForm" method="post">
        <div id="queryframe" style="display:none">
            <table class="well table resultTable table-hover">
                <tr>
                    <td>交易机构编号：</td>
                    <td><input name="custmorNo" type="text" value="${custmorNo}" class="input-medium"/></td>
                    <td>交易机构名称：</td>
                    <td><input name="custmorName" type="text" value="${custmorName}" class="input-medium"/></td>
                    <td>交易机构类型：</td>
                    <td>
                        <select id="custmorType" name="custmorType">
                            <option value="">请选择</option>
                            <nt:code ctype="004"></nt:code>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <div class="row-fluid">
        <button type="button" onClick="create()">新增</button>
        <button type="button" onClick="editCustmor()">修改</button>
        <button type="button" onClick="del()">删除</button>
        <button type="button" onClick="elastic('queryframe')">查询条件</button>
        <button type="button" onclick="query()">查询提交</button>
    </div>

    <div style="height:10px"></div>
    <div class="row-fluid">
        <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
            <tr>
                <th rowspan="1"></th>
                <th rowspan="1">编号</th>
                <th rowspan="1">交易机构名称</th>
                <th rowspan="1">交易机构类型</th>
                <th rowspan="1">所属行业</th>
                <th rowspan="1">企业性质</th>
                <th rowspan="1">组织机构代码</th>
                <th rowspan="1">联系人</th>
                <th rowspan="1">联系电话</th>
                <th rowspan="1">信用评级</th>
                <th rowspan="1">授信额度</th>
                <th rowspan="1">已占用额度</th>
                <th rowspan="1">剩余额度</th>
                <th rowspan="1">历史项目</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <td><input rec="true" type="checkbox" name="checkboxs" value="${item.custmorNo}"></td>
                    <td>${item.custmorNo}</td>
                    <td>${item.custmorName}</td>
                    <td><nt:codeValue index="${item.custmorType}" ctype="004"></nt:codeValue></td>
                    <td><nt:codeValue index="${item.indNo}" ctype="005"></nt:codeValue></td>
                    <td><nt:codeValue index="${item.ownType}" ctype="006"></nt:codeValue></td>
                    <td>${item.orgCode}</td>
                    <td>${item.connName}</td>
                    <td>${item.connPhone}</td>
                    <td><nt:codeValue index="${item.credit}" ctype="007"></nt:codeValue></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
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
