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
    <style type="text/css">
        #bg{display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:1001; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=70);}
        #show{display: none; position: absolute; top: 25%; left: 25%; width: 50%; height: 55%; padding: 8px; border: 1px solid #E8E9F7; background-color: white; z-index:1002; overflow: auto;border-radius:25px;}
    </style>
    <script type="text/javascript">
        $(function () {
            if(${msg!=null}){
                myAlert("温馨提示","${msg}");
            }

        });
        function create(){
            location.href='<%=path%>/project/createProject';
        }
        function editProject(){
            var temp = $("input[name='checkboxs']:checked");

            if (temp.length == 1) {
                var custmorNo=temp.val().split("&&")[0];
                window.location.href = "<%=path%>/project/updateProject?projectNo="+custmorNo;
            } else {
                myAlert("温馨提示","请选中一条记录");
            }
        }
        function del(){
            var temp = $("input[name='checkboxs']:checked");
            if(temp.length == 1){
                myConfirm("温馨提示","确认要删除这条记录吗？",deleteProject);
            }else{
                myAlert("温馨提示","请选中一条记录！");
                return;
            }
        }
        function deleteProject(){
            var cusNo = $("input[name='checkboxs']:checked").val().split("&&")[0];
            $.ajax({
                url:'<%=path%>/project/countProjectByproject?projectNo='+cusNo,
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
            var cusNo = $("input[name='checkboxs']:checked").val().split("&&")[0];
            <%--window.location.href = "<%=path%>/project/delete?pi=${pi}&projectNo="+cusNo;--%>
            $.ajax({
                url:'<%=path%>/project/delete?projectNo='+cusNo,
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
            window.location.href="<%=path%>/project/index?time=2";
        }
        function query(){
            $("#queryForm").attr("action", "<%=path%>/project/index?time=2").submit();
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
            window.open("<%=path%>/project/datails?projectNo="+id, "frame", "height=1200,width=1900,top=100,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=1,location=no, status=no");

        }
        function recordEnd(){
            var temp = $("input[name='checkboxs']:checked");
            if (temp.length == 1) {
                var value=temp.val();
                var no=value.split("&&")[0];
                var status=value.split("&&")[1];
                if(status=="2" || status=="3" || status=="4"|| status=="5"|| status=="6"){
                    window.location.href = "<%=path%>/project/recordEnd?projectNo="+no;
                }else{
                    myAlert("温馨提示","请选择未全部完工的合同！");
                    return ;
                }
            } else {
                myAlert("温馨提示","请选中一条记录！");
                return ;
            }
        }
        function showdiv() {
            document.getElementById("bg").style.display ="block";
            document.getElementById("show").style.display ="block";
        }
        function hidediv() {
            document.getElementById("bg").style.display ='none';
            document.getElementById("show").style.display ='none';
        }
        function proceed(){
            var temp = $("input[name='checkboxs']:checked");
            if (temp.length == 1) {
                var value=temp.val();
                var no=value.split("&&")[0];
                var status=value.split("&&")[1];
                var totalPrice=value.split("&&")[2];
                if(status=="7"){
                    //window.location.href = "<%=path%>/project/proceed?projectNo="+no;
                    totalPrice=fmoneys(totalPrice,2);
                    $("#actAmt").val(totalPrice);
                    $("#projectNo").val(no);
                    showdiv();
                }else{
                    myAlert("温馨提示","请选择全部提货的合同！");

                }
            } else {
                myAlert("温馨提示","请选中一条记录！");
            }
        }
        function hiddenSubmit(){
            var temp = $("input[name='checkboxs']:checked").val().split("&&");
            $("input[money='money']").each(function(){
                var id=this.id;
                $("#"+id).val(this.value.replace(/[^\d\.-]/g, ""));
            });
            $.ajax({
                url:'<%=path%>/project/proceed',
                data:$("#hiddenForm").serializeArray(),
                dataType:"json",
                contentType:"application/x-www-form-urlencoded",
                type:'post',
                success:function(data){
                    if(data>0){
                        myAlert("温馨提示：","收款成功，此合同已结束！",back);
                    }else{
                        myAlert("温馨提示：","收款失败！",back);
                    }
                },
                error:function () {
                    alert("生成失败！");
                }
            });
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
        <button type="button" onClick="recordEnd()">进度记录</button>
        <%--<button type="button" onClick="pickup()">提货</button>--%>
        <button type="button" onClick="proceed()">收款</button>
        <button type="button" onclick="upFile()">管理文件</button>
        <span style="  display: inline-block;float: right"><b>金额单位：元</b></span>
    </div>

    <div style="height:10px"></div>
    <div class="row-fluid">
        <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
            <tr>
                <th rowspan="1"></th>
                <th rowspan="1">合同编号</th>
                <th rowspan="1">合同名称</th>
                <th rowspan="1">合作商</th>
                <th rowspan="1">开始日期</th>
                <th rowspan="1">合同金额</th>
                <th rowspan="1">录入人</th>
                <th rowspan="1">录入日期</th>
                <th rowspan="1">合同状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <td><input rec="true" type="radio" name="checkboxs" value="${item.projectNo}&&${item.status}&&${item.totalPrice}"></td>
                    <td>${item.projectNo}</td>
                    <td><a onclick="detailC('${item.projectNo}')">${item.projectName}</a></td>
                    <td><nt:cusname cuscomerid="${item.customerNo}"></nt:cusname></td>
                    <td><fmt:formatDate value="${item.beginDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
                    <td><fmt:formatNumber value="${item.totalPrice}" pattern="#,#00.00"></fmt:formatNumber> </td>
                    <td>${item.applyOpr}</td>
                    <td><fmt:formatDate value="${item.applyDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
                    <td><nt:codeValue index="${item.status}" ctype="007"></nt:codeValue></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@ include file="../../mypage.jsp" %>
    <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</div>
<div id="bg"></div>
<div id="show">
    <form id="hiddenForm" method="post">
        <table class="well table resultTable table-hover">
            <input name="projectNo" id="projectNo" type="text" value=""/>
            <div style="height:50px"></div>
            <span style="font-size:18px">请填写实收金额和收款日期：</span>
            <div style="height:20px"></div>
            <tr><td>实收金额：</td><td><input money="money" name="actAmt" id="actAmt" type="text" value="" onblur="fmoney(this)" onfocus="rmoney(this)"/></td></tr>
            <tr><td>收款日期：</td><td><input name="endDate" id="enddate" type="text" value="<fmt:formatDate value='${today}' pattern='yyyy-MM-dd'/>"/></td></tr>
        </table>
    </form>
    <div style="height:20px"></div>
    <div style="text-align:center">
        <button type="button" onclick="hiddenSubmit()">确认</button>
        <button type="button" id="btnclose" onclick="hidediv()">取消</button>
    </div>
</div>
</body>
</html>
