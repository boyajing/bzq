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
        #show{display: none; position: absolute; top: 20%; left: 20%; width: 60%; height: 60%; padding: 8px; border: 1px solid #E8E9F7; background-color: white; z-index:1002; overflow: auto;border-radius:30px;}
    </style>
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
        function back() {
            hidediv();
            window.location.href="<%=path%>/expense/indexSalary";
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
        function showdiv() {
            document.getElementById("bg").style.display ="block";
            document.getElementById("show").style.display ="block";
        }
        function hidediv() {
            document.getElementById("bg").style.display ='none';
            document.getElementById("show").style.display ='none';
        }
        function paySalary(){
            deleteD();
            $("#detailCount").val(0);
            var temp = $("input[name='checkboxs']:checked");
            if (temp.length < 1) {
                myAlert("温馨提示","请选中至少一条记录！");
                return;
            }else{
                for(var i=0;i<temp.length;i++){
                    var sal=temp[i].value.split("@@");
                    var userId=sal[0];
                    var strDate=sal[1];
                    var salary=sal[2];
                    salary = fmoneys(salary,2);
                    addSalary(userId,strDate,salary);
                }
                showdiv();
            }
        }
        function addSalary(userId,strDate,salary ) {
            var id=$("#detailCount").val();
            var str = "<tr name='salaryTr'>"+
                "<td width='8%'>"+ userId+
                "<input type='hidden' name='salaryList["+id+"].userId' value='"+userId+"'/>"+
                "</td>"+
                "<td width='8%'>"+
                "<input type='test' readonly name='salaryList["+id+"].strDate' value='"+strDate+"'/>"+
                "</td>"+
                "<td width='8%'>"+
                "<input type='test' readonly money='money' id='salary"+id+"' name='salaryList["+id+"].salary' value='"+salary+"'/>"+
                "</td>"+
                "</tr>";
            $("#hiddenTable").append(str);
            id=parseInt(id);
            $("#detailCount").val(id+1);
        }
        function deleteD() {
            $("tr[name='salaryTr']").each(function (){
                $(this).remove();
            })
        }
        function hiddenSubmit(){
            $("input[money='money']").each(function(){
                var id=this.id;
                $("#"+id).val(this.value.replace(/[^\d\.-]/g, ""));
            });
            $.ajax({
                url:'<%=path%>/expense/paySalary',
                data:$("#hiddenForm").serializeArray(),
                dataType:"json",
                contentType:"application/x-www-form-urlencoded",
                type:'post',
                success:function(data){
                    if(data>0){
                        myAlert("温馨提示：","保存成功！",back);
                    }else{
                        myAlert("温馨提示：","保存失败！");
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
        <button type="button" onclick="paySalary()">发放工资</button>
        <button type="button" onclick="upFile()">管理文件</button>

        <span style="  display: inline-block;float: right"><b>金额单位：元</b></span>
    </div>

    <div style="height:10px"></div>
    <div class="row-fluid">
        <c:forEach items="${users}" var="user">
            <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
                <thead>
                <tr>
                    <th colspan="${fn:length(user.salaryList)+1}">
                        <nt:username userid="${user.userId}"></nt:username>
                    </th>
                </tr>
                <tr>
                    <th rowspan="1"><fmt:formatDate value="${today}" pattern="yyyy" ></fmt:formatDate></th>
                    <c:forEach items="${user.salaryList}" var="salary">
                        <th rowspan="1"><fmt:formatDate value="${salary.salaryDate}" pattern="MM" ></fmt:formatDate></th>
                    </c:forEach>
                </tr>
                <tr>
                    <th rowspan="1">时</th>
                    <c:forEach items="${user.salaryList}" var="salary">
                        <td rowspan="1">
                        <c:if test="${empty salary.manHour}">--</c:if>
                        <c:if test="${not empty salary.manHour}">${salary.manHour}</c:if>
                        </td>
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
                                <fmt:formatNumber value="${salary.salary}" pattern="##,#00.00" ></fmt:formatNumber>
                            </c:if>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <th rowspan="1">工资状态</th>
                    <c:forEach items="${user.salaryList}" var="salary">
                        <td rowspan="1">
                        <c:if test="${empty salary.status}">--</c:if>
                        <c:if test="${salary.status==1 && month==salary.strDate}">
                            未发放
                        </c:if>
                        <c:if test="${salary.status==1 && month!=salary.strDate}">
                            <input rec="true" type="checkbox" name="checkboxs" value="${user.userId}@@${salary.strDate}@@${salary.salary}">未发放
                        </c:if>
                        <c:if test="${salary.status==2}">
                            已发放
                        </c:if>
                        </td>
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
<div id="bg"></div>
<div id="show">
    <form id="hiddenForm" method="post">
        <input type="hidden" id="detailCount" value="0">
        <table class="well table resultTable table-hover" id="hiddenTable">
            <div style="height:20px"></div>
            <span style="font-size:18px">确认发放工资：</span>
            <div style="height:10px"></div>
            <tr>
                <td>姓名</td>
                <td>月</td>
                <td>工资</td>
            </tr>
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
