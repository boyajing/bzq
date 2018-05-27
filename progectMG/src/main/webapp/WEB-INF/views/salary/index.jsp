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
            location.href='<%=path%>/salary/recordSalary';
        }
        function editCustmor(){
            var temp = $("input[name='checkboxs']:checked");

            if (temp.length == 1) {
                var custmorNo=temp.val();
                window.location.href = "<%=path%>/customer/updateCustomer?customerNo="+custmorNo;
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
                url:'<%=path%>/customer/countProjectByCustomer?customerNo='+cusNo,
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
                url:'<%=path%>/customer/delete?customerNo='+cusNo,
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
            window.location.href="<%=path%>/customer/index";
        }
        function query(){
            $("#queryForm").attr("action", "<%=path%>/customer/index").submit();
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
            window.open("<%=path%>/customer/updateCustomer?customerNo="+id+"&edit=2", "frame", "height=1200,width=1900,top=100,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=1,location=no, status=no");
        }
        function chooseC() {
            var temp = $("input[name='checkboxs']:checked");
            var businessId=temp.val();
            opener.chooceCustomer(businessId.split("&&")[0],businessId.split("&&")[1]);
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
        <button type="button" onClick="create()">记工</button>
        <button type="button" onclick="upFile()">管理文件</button>

        <span style="  display: inline-block;float: right"><b>金额单位：元</b></span>
    </div>

    <div style="height:10px"></div>
    <div class="row-fluid">
        <c:forEach items="${users}" var="user">
            <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
                <thead>
                <tr>
                    <th colspan="${fn:length(user.salaryList)+1}"><nt:username userid="${user.userId}"></nt:username></th>
                </tr>
                <tr>
                    <th rowspan="1"><fmt:formatDate value="${today}" pattern="yyyy-MM" ></fmt:formatDate></th>
                    <c:forEach items="${user.salaryList}" var="salary">
                        <th rowspan="1"><fmt:formatDate value="${salary.salaryDate}" pattern="dd" ></fmt:formatDate></th>
                    </c:forEach>
                </tr>
                <tr>
                    <th rowspan="1">时</th>
                    <c:forEach items="${user.salaryList}" var="salary">
                        <td rowspan="1">
                        <c:if test="${empty salary.manHour}">--</c:if>
                        <c:if test="${not empty salary.manHour}">${salary.manHour}</td>
                        </c:if>
                    </c:forEach>
                </tr>
                <tr>
                    <th rowspan="1">元/时</th>
                    <c:forEach items="${user.salaryList}" var="salary">
                        <td rowspan="1">
                            <c:if test="${empty salary.unitSalary}">--</c:if>
                            <c:if test="${not empty salary.unitSalary}">
                                <fmt:formatNumber value="${salary.unitSalary}" pattern="##,#00.00" ></fmt:formatNumber></td>
                            </c:if>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <th rowspan="1">工资</th>
                    <c:forEach items="${user.salaryList}" var="salary">
                        <td rowspan="1">
                        <c:if test="${empty salary.salary}">--</c:if>
                        <c:if test="${not empty salary.salary}">
                            <fmt:formatNumber value="${salary.salary}" pattern="##,#00.00" ></fmt:formatNumber></td>
                        </c:if>
                    </c:forEach>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </c:forEach>
    </div>
    <%--<%@ include file="../mypage.jsp" %>--%>
    <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</div>
</body>
</html>
