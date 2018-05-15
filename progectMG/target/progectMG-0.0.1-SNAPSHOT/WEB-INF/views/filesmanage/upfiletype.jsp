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
  <title>文件管理</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
  <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
  <link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
  <link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
  <script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
  <script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
  <script type="text/javascript">
    $(function () {
      $("table").attr("class","well table resultTable table-hover");
    });
    jQuery(function ($) {
        $("#addfile").click(function () {
                $("#file").append(
                        "<tr><td><input name='file' type='file'/></td>" +
                        "<td><select name='childType'>"+
                        "<option value=''>请选择</option>"+
                        "<nt:code index='${childType}' ctype='${businessType}'></nt:code>"+
                        "</select></td><td><input type='text' name='fileCreater' ></td>"+
                        "<td><button type='button' onclick='deltr(this)'>删除</button></td></tr>")

        });
    });
    //点击上传后返回 提示是否成功
    function result(){
        var result='${result}';
        if(result=="1"){
            alert("上传成功");
            if(!confirm("是否继续上传？")) {
                window.close();
            }
            window.opener.document.getElementById('Button').click();
        }
        if(result=="0") {
            alert("上传失败");
            if(!confirm("是否继续上传？")){
                window.close();
            }
        }
    };
    //删除
    function deltr(delbtn){
        $(delbtn).parents("tr").remove();
    };
    //上传
    function up() {
        var a = 1;
        var fileFlag = false;
        if ($("input[type='file']").length == 0) {
            alert("请增加一个附件！");
            return false;
        }
        $("input[type='file']").each(function () {
            if ($(this).val() == "") {
                 fileFlag = true;
                alert("请选择要上传的文件");
                a = 0;
                return false;
            }
        });

        $("#file input[name=fileCreater]").each(function (i, o) {
            if (o.value == "") {
                alert("请填写责任人！");
                a = 0;
                return false;
            }
        });
        $("#file select[name=childType]").each(function (i, o) {
            if (o.value == "") {
                alert("请选择子业务类型！");
                a = 0;
                return false;
            }
        });

      /*  var fileType = $("input[name=file]");
        for(var i=0;i< fileType.length;i++){
            var type=fileType[i].value;
            type=type.substr(type.length-4);
            //alert(type);
            if(type!="docx"){
                alert("只能上传.docx格式的文件！");
                a = 0;
                return false;
            }
        }*/

        if (a == 1 && fileFlag == false) {
            $("#queryForm").attr("action", "<%=path%>/fileManager/save?businessType=${businessType}&businessId=${businessId}&projectCode=${projectCode}&childType=${childType}").submit();
        }
    };
  </script>
</head>
<body onload="result();">
<div>
    <button type="button" id="addfile">增加文件</button>
    <button style="margin-left: 10px" type="button" onclick="up()">上传</button>
    <form id="queryForm" method="post" enctype="multipart/form-data" onkeydown="if(event.keyCode ==13) return false;">
        <input type="hidden" name="businessId" value="${businessId}">
        <input type="hidden" name="businessType" value="${businessType}">
        <input type="hidden" name="projectCode" value="${projectCode}">
        <table id="file">
            <tr><th>选择上传文件</th><th>附件类型</th><th>项目负责人</th><th></th></tr>
            <tr>
                <td><input name='file' type='file'/></td>
                <td>
                    <select name="childType">
                        <option value="">请选择</option>
                        <nt:code index="${childType}" ctype="${businessType}"></nt:code>
                    </select>
                </td>
                <td><input type="text" name="fileCreater"></td>
                <td><button type="button" onclick="deltr(this)">删除</button></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
