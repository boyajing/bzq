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
    <title>相关文档</title>
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
                <input type="hidden" name="businessId" value="${tArchives.businessId}">
                <input type="hidden" name="projectCode" value="${tArchives.projectCode}">
                <input type="hidden" name="businessType" value="${tArchives.businessType}">
                <tr>
                    <td><label>文件名称：</label></td>
                    <td><input name="fileName" type="text" value="${tArchives.fileName}"/></td>
                    <td><label>文件类型：</label></td>
                    <td><select name="childType">
                            <option value="">请选择</option>
                            <nt:code index="${tArchives.childType}" ctype="${tArchives.businessType}"></nt:code>
                        </select></td>
                </tr>
                <tr>
                    <td><label>上传开始日期：</label></td>
                    <td><input name="beinDate" id="beindate" type="text" /></td>
                    <td><label>上传结束日期：</label></td>
                    <td><input name="endDate" id="enddate" type="text"/></td>
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
                    <button type="button" id="uploading"  class="btn" onclick="uploading();">上传</button>
                    <button type="button" id="del" class="btn" onclick="del();">删除</button>
                </td>
            </tr>
        </table>
    </div>
    <div style="height:10px"></div>
    <div>
        <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
            <tr>
                <th rowspan="1" class="box"></th>
                <th rowspan="1">序号</th>
                <th rowspan="1">文件名</th>
                <th rowspan="1">文件日期</th>
                <th rowspan="1">文件类型</th>
                <th rowspan="1">提交人</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <% i++;%>
                <tr>
                    <td class="box"><input type="checkbox" value="${item.id}@@<%=i%>@@${item.businessType}" name="radio"/></td>
                    <td><%=i%></td>
                    <td><a href="<%=path%>/fileManager/fileload?id=${item.id}">${item.fileName}</a></td>
                    <td><fmt:formatDate value="${item.cteateTime}" pattern="yyyy-MM-dd"/></td>
                    <td><nt:codeValue index="${item.childType}" ctype="${item.businessType}"></nt:codeValue></td>
                    <td>${item.createrId}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@ include file="../mypage.jsp" %>
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
        $("#queryForm").attr({action:"<%=path%>/fileManager/index",method:"post"}).submit();
    }
    //上传
    function uploading() {
        if(!'${tArchives.businessId}'){
            alert("请先做保存");
        }else {
        window.open("<%=path%>/fileManager/upfile?businessType=${tArchives.businessType}&businessId=${tArchives.businessId}&projectCode=${tArchives.projectCode}&childType=${tArchives.childType}",
                null, "height=" + iHeight + ",width=" + iWidth + ",top=" + iTop + ",left=" + iLeft + ",status=yes,toolbar=no,scrollbars=yes,directories=yes,menubar=no,location=no");
        }
    }
    //删除
    function del(){
        var checkbox = $("input[name='radio']:checked");
        //校验
        if (checkbox.length >= 1) {
            if (window.confirm("确认删除吗?")) {
                var id = '';//要删除的id
                var message =""//错误信息
                for (var i = 0; i < checkbox.length; i++) {
                    var obj = checkbox[i].value.split("@@");
                    //方案不能删除项目文件
                    if(${businessType=='file_2'}){
                        if(obj[2]=='file_1'){
                            message +="("+obj[1]+")";
                        }
                    }
                    if (i == 0) //使文件编号用@@分割加起来传入后台
                        id += obj[0];
                    else
                        id += "@@" + obj[0];
                }
                if(message!='') {
                    alert("不能删除序号为" + message + "的文件，此文件为项目阶段上传的文件");
                    return false;
                }
               location.href='<%=path%>/fileManager/del?id='+id+'&projectCode=${tArchives.projectCode}&businessType=${tArchives.businessType}&businessId=${tArchives.businessId}&childType=${tArchives.childType}';
            }
        }else {
            alert("请选中一条记录！");
        }
    }
</script>