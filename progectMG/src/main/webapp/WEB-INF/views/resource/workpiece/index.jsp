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
    <title>工件</title>
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
            location.href='<%=path%>/workpiece/createWorkpiece';
        }
        function editCustmor(){
            var temp = $("input[name='checkboxs']:checked");

            if (temp.length == 1) {
                var custmorNo=temp.val();
                window.location.href = "<%=path%>/workpiece/updateWorkpiece?workpieceNo="+custmorNo;
            } else {
                myAlert("温馨提示","请选中一条记录");
            }
        }
        function del(){
            var temp = $("input[name='checkboxs']:checked");
            if(temp.length == 1){
                myConfirm("温馨提示","确认要删除这条记录吗？",deleteWorkpiece);
            }else{
                myAlert("温馨提示","请选中一条记录！");
                return;
            }
        }
        function deleteWorkpiece(){
            var cusNo = $("input[name='checkboxs']:checked").val();
            $.ajax({
                url:'<%=path%>/workpiece/beforeDelete?workpieceNo='+cusNo,
                type: "GET",
                success:function(data){
                    if(data>0){
                        myAlert("温馨提示","此合作商下有合同，不能删除！");
                    }else{
                        deleteCus();

                    }
                }
            });
        }
        function deleteCus(){
            var cusNo = $("input[name='checkboxs']:checked").val();
            <%--window.location.href = "<%=path%>/customer/delete?pi=${pi}&customerNo="+cusNo;--%>
            $.ajax({
                url:'<%=path%>/workpiece/delete?workpieceNo='+cusNo,
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
            window.location.href="<%=path%>/workpiece/index";
        }
        function query(){
            $("#queryForm").attr("action", "<%=path%>/workpiece/index").submit();
        }
        function xgsc(){
            //var a = window.parent.isShow();
            window.open("<%=path%>/fileManager/index", "frame", "height=1200,width=1900,top=100,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=1,location=no, status=no");
        }
        function upFile(){
            var temp = $("input[name='checkboxs']:checked");

            if (temp.length == 1) {
                var businessId=temp.val().split("&&")[0];
                //window.location.href = "<%=path%>/fileManager/upFile?businessId="+businessId+"&businessType=file_01";
                window.open("<%=path%>/fileManager/upFile?businessId="+businessId+"&businessType=file_01", "上传文件", "menubar=no,status=no,resizable=no,scrollbars=1,width=1200,height=1000pt,top=100,left=100");
            } else {
                myAlert("温馨提示","请选中一条记录！");
                return ;
            }
        }
        function detailC(id) {
            window.open("<%=path%>/workpiece/updateWorkpiece?workpieceNo="+id+"&edit=2", "frame", "height=1200,width=1900,top=100,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=1,location=no, status=no");
        }
        function chooseWP() {
            var temp = $("input[name='checkboxs']:checked");
            var businessId=temp.val();
            opener.chooceWorkpiece(businessId.split("&&")[0],businessId.split("&&")[1],${detailTr});
            window.close();
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
        <button type="button" onClick="elastic('queryframe')">查询条件</button>
        <button type="button" onclick="query()">查询提交</button>
        <c:if test="${empty select}">
            <button type="button" onClick="create()">新增</button>
            <button type="button" onClick="editCustmor()">修改</button>
            <button type="button" onClick="del()">删除</button>
        </c:if>
        <c:if test="${select==1}">
            <button type="button" onclick="chooseWP()">选择</button>
        </c:if>
        <c:if test="${empty select}">
            <button type="button" onclick="upFile()">管理文件</button>
        </c:if>

        <span style="  display: inline-block;float: right"><b>金额单位：元</b></span>
    </div>

    <div style="height:10px"></div>
    <div class="row-fluid">
        <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
            <tr>
                <th rowspan="1"></th>
                <th rowspan="1">工件编号</th>
                <th rowspan="1">工件名称</th>
                <th rowspan="1">单位</th>
                <th rowspan="1">已完工个数</th>
                <th rowspan="1">未完工个数</th>
                <th rowspan="1">已提货个数</th>
                <th rowspan="1">未提货个数</th>
                <th rowspan="1">总计</th>
                <th rowspan="1">录入人</th>
                <th rowspan="1">录入日期</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <td><input rec="true" type="radio" name="checkboxs" value="${item.workpieceNo}&&${item.workpieceName}"></td>
                    <td>${item.workpieceNo}</td>
                    <td><a onclick="detailC('${item.workpieceNo}')">${item.workpieceName}</a></td>
                    <td>
                        <c:if test="${empty item.unit}">--</c:if>
                        <c:if test="${not empty item.unit}"><nt:codeValue index="${item.unit}" ctype="003"></nt:codeValue></c:if>
                    </td>
                    <td><c:if test="${empty item.finishCount}">--</c:if><c:if test="${not empty item.finishCount}">${item.finishCount}</c:if></td>
                    <td><c:if test="${empty item.totalCount || empty item.finishCount}">--</c:if><c:if test="${not empty item.totalCount && not empty item.finishCount}">${item.totalCount-item.finishCount}</c:if></td>
                    <td><c:if test="${empty item.pickupCount}">--</c:if><c:if test="${not empty item.pickupCount}">${item.pickupCount}</c:if></td>
                    <td><c:if test="${empty item.totalCount || empty item.pickupCount}">--</c:if><c:if test="${not empty item.totalCount && not empty item.pickupCount}">${item.totalCount-item.pickupCount}</c:if></td>
                    <td><c:if test="${empty item.totalCount}">--</c:if><c:if test="${not empty item.totalCount}">${item.totalCount}</c:if></td>
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
