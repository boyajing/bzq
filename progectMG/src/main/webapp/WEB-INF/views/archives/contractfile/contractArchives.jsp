<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/nttags/nantian.tld" prefix="nt" %>
<%
    String path = request.getContextPath();
    int i=0;
%>
<!DOCTYPE html>
<html>
<head>
    <title>合同档案管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
    <script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
</head>
<body>
<div style="display: none">
    <button id="Button" onclick="window.location.reload()"></button>
</div>
<div class="container-fluid">
    <div style="height:10px"></div>
    <div id="queryframe" style="display:none">
        <form id="queryForm">
            <table class="well table resultTable table-hover">
                <input type="hidden" name="contractId" value="${tContractAchives.contractId}">
                <tr>
                    <td><label>项目经理合同名称：</label></td>
                    <td><input name="managerContractName" type="text" value="${tContractAchives.managerContractName}"/></td>
                    <td><label>合同类型：</label></td>
                    <td><select name="contractType">
                        <option value="">请选择</option>
                        <nt:code index="${tContractAchives.contractType}" ctype="130"></nt:code>
                    </select></td>
                </tr>
            </table>
        </form>
    </div>
    <div>
        <table>
            <tr>
                <td>
                    <button type="button" onClick="elastic('queryframe', '50px');">查询条件</button>
                    <button type="button" onclick="query();">查询提交</button>
                </td>
            </tr>
        </table>
    </div>
    <div style="height:10px"></div>
    <div>
        <table class="well table resultTable table-hover">
            <tr>
                <th rowspan="1">选择</th>
                <th rowspan="1">合同类型</th>
                <th rowspan="1">合同编号</th>
                <th rowspan="1">项目经理合同名称</th>
                <th rowspan="1">签约人</th>
            </tr>
            <tbody>
            <c:forEach var="item" items="${list}" varStatus="loop">
                <tr>
                    <td id="ID" style="display: none" >${item.id}</td>
                    <td><input type="radio" value="${item.id},${item.status}" name="radio"/></td>
                    <td><nt:codeValue index="${item.contractType}" ctype="130"></nt:codeValue></td>
                    <td>${item.uploadContractId}</td>
                    <td><a onclick="managerfileload( '${item.id}')">${item.managerContractName}</a></td>
                    <td>${item.lawCreater}</td>
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
    $(function(){
        if('${projectStatus}'==0){//新建
            $("#del").show();
        }else {
            $("#del").hide();
        }

    });

    //客户：点击查看则不能删除、上传功能
    $(function () {
        if(${edit=="1"}){
            $("#uploading").show();
            $("#del").show();
            $(".box").show();
        }else  if(${edit=="2"}){
            $("#uploading").hide();
            $("#del").hide();
            $(".box").hide();
        }
    });

    $(function(){
        if(${result=='0'}){
            alert("删除失败");
        }else if(${result=='1'}){
            alert("删除成功");
        }
    })
    var iWidth = 1000;                          //弹出窗口的宽度;
    var iHeight = 500;
    var iTop = (window.screen.availHeight - 30 - iHeight) / 2;       //获得窗口的垂直位置;
    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
    //查询
    function query() {
        $("#queryForm").attr({action:"<%=path%>/archives/contractfile/contractArchives",method:"post"}).submit();
    }
    function managerfileload(temp) {
        location.href='<%=path%>/archives/contractfile/mfileload?id='+temp+'';
    }
</script>