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
    <title>合作商</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
    <script type="text/javascript" src="<%=path%>/js/WindowTracking.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
    <%--<script type="text/javascript" src="<%=path%>/js/date/jquery-1.10.1.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/info/msgbox.js"></script>
    <script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
    <script type="text/javascript">
        $(function () {
            if(${msg!=null}){
                myAlert("温馨提示","${msg}");
            }

        });
        function create(){
            location.href='<%=path%>/tool/createTool';
        }
        function editCustmor(){
            var temp = $("input[name='checkboxs']:checked");

            if (temp.length == 1) {
                var custmorNo=temp.val();
                window.location.href = "<%=path%>/tool/updateTool?toolNo="+custmorNo;
            } else {
                myAlert("温馨提示","请选中一条记录");
            }
        }
        function del(){
            var temp = $("input[name='checkboxs']:checked");
            if(temp.length == 1){
                myConfirm("温馨提示","确认要删除这条记录吗？",deleteCustmor);
            }else{
                myAlert("温馨提示","请选中一条记录！");
                return;
            }
        }
        function deleteCustmor(){
            var cusNo = $("input[name='checkboxs']:checked").val();
            $.ajax({
                url:'<%=path%>/tool/beforeDelete?toolNo='+cusNo,
                type: "GET",
                success:function(data){
                    if(data>0){
                        myAlert("温馨提示","此工具库存不为0，不能删除！");
                    }else{
                        deleteCus();

                    }
                }
            });
        }
        function deleteCus(){
            var cusNo = $("input[name='checkboxs']:checked").val();
            <%--window.location.href = "<%=path%>/tool/delete?pi=${pi}&toolNo="+cusNo;--%>
            $.ajax({
                url:'<%=path%>/tool/delete?toolNo='+cusNo,
                type: "GET",
                success:function(data){
                    if(data>0){
                        myAlert("温馨提示","删除成功！",back);
                    }else{
                        myAlert("温馨提示","删除失败！",back);
                    }
                }
            });
        }

        function back() {
            window.location.href="<%=path%>/tool/index";
        }
        function query(){
            $("#queryForm").attr("action", "<%=path%>/tool/index").submit();
        }
        function xgsc(){
            //var a = window.parent.isShow();
            window.open("<%=path%>/fileManager/index", "frame", "height=1200,width=1900,top=100,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=1,location=no, status=no");
        }
        function upFile(){
            var temp = $("input[name='checkboxs']:checked");

            if (temp.length == 1) {
                var businessId=temp.val();
                //window.location.href = "<%=path%>/fileManager/upFile?businessId="+businessId+"&businessType=file_01";
                window.open("<%=path%>/fileManager/upFile?businessId="+businessId+"&businessType=file_01", "上传文件", "menubar=no,status=no,resizable=no,scrollbars=1,width=1200,height=1000pt,top=100,left=100");
            } else {
                myAlert("温馨提示","请选中一条记录！");
                return ;
            }
        }
        function detailC(id) {
            window.open("<%=path%>/tool/updateTool?toolNo="+id+"&edit=2", "frame", "height=1200,width=1900,top=100,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=1,location=no, status=no");

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
                    <%--<td>交易机构编号：</td>--%>
                    <%--<td><input name="custmorNo" type="text" value="${custmorNo}" class="input-medium"/></td>--%>
                    <td>交易机构名称：</td>
                    <td><input name="custmorName" type="text" value="" class="input-medium"/></td>
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
        <button type="button" onclick="upFile()">管理文件</button>
        <span style="  display: inline-block;float: right"><b>金额单位：元</b></span>
    </div>

    <div style="height:10px"></div>
    <div class="row-fluid">
        <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
            <tr>
                <th rowspan="1"></th>
                <th rowspan="1">工具编号</th>
                <th rowspan="1">工具名称</th>
                <th rowspan="1">单位</th>
                <th rowspan="1">库存</th>
                <th rowspan="1">历史购入个数</th>
                <th rowspan="1">消耗总金额</th>
                <th rowspan="1">录入人</th>
                <th rowspan="1">录入日期</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <td><input rec="true" type="radio" name="checkboxs" value="${item.toolNo}"></td>
                    <td>${item.toolNo}</td>
                    <td><a onclick="detailC('${item.toolNo}')">${item.toolName}</a></td>
                    <td>
                        <c:if test="${empty item.unit}">--</c:if>
                        <c:if test="${not empty item.unit}"><nt:codeValue index="${item.unit}" ctype="003"></nt:codeValue></c:if>
                    </td>
                    <td><c:if test="${empty item.stock}">--</c:if><c:if test="${not empty item.stock}">${item.stock}</c:if></td>
                    <td><c:if test="${empty item.hisCount}">--</c:if><c:if test="${not empty item.hisCount}">${item.hisCount}</c:if></td>
                    <td><c:if test="${empty item.hisAmt}">--</c:if><c:if test="${not empty item.hisAmt}"><fmt:formatNumber value="${item.hisAmt}" pattern='#,##0.00'></fmt:formatNumber></c:if></td>
                    <td>${item.applyOpr}</td>
                    <td><fmt:formatDate value="${item.applyDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
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
