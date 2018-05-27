<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/nttags/nantian.tld" prefix="nt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%
    String datetime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //获取系统时间
%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>新增合作商</title>
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
    <script type="text/javascript" src="<%=path%>/js/numberformat.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
    <%--<script type="text/javascript" src="<%=path%>/js/date/jquery-1.10.1.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
    <script type="text/javascript" src="<%=path%>/js/WindowTracking.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/css-loader.css">
    <script type="text/javascript" src="<%=path%>/js/provinceCity.js"></script>
    <script type="text/javascript" src="<%=path%>/js/customerverify/verifycustomer.js"></script>
    <script type="text/javascript" src="<%=path%>/js/info/msgbox.js"></script>
    <script type="text/javascript">
        /***********************************************整体样式控制***********************************************/
        $(function () {
            $('input,select').attr('class', 'input-block-level');
            verify("name,certificateFlag,sin");
            $("input[name=familyMonth]").attr({value:function(){fmoney(this);}});
            $("input[name=personalyear]").attr({value:function(){fmoney(this);}});
            initTable(null, 0);
            $('table').attr("style" ,'table-layout:automatic');
            $('table').attr('class','well table resultTable table-hover');
            $('th').attr('nowrap', 'nowrap');
            $('textarea').attr({style: 'width: 100%;padding: 0px;boder:0px;'});
            if(${message!=null}){
                if(${message=='1'}){
                    alert("保存成功");
                }else if(${message=='2'}){
                    alert("保存失败");
                }
            }
        });
        function calcute(obj){
            var id=obj.id;
            id=id.substring(7);
            if($("#manHour"+id).val()==""){
                return;
            }
            if($("#unitSal"+id).val()==""){
                return;
            }
            var manHour = $("#manHour"+id).val();
            var unitSal = $("#unitSal"+id).val();
            var salary=parseFloat(manHour)*parseFloat(unitSal);
            salary=fmoneys(salary,2);
            $("#salary"+id).val(salary);
            fmoney(obj);
        }
        function save(){
            var flag=true;
            if($("#date").val()==""){
                myAlert("温馨提示","日期不能为空！");
                flag=false;
                return;
            }
//            $("input[id^='manHour']").each(function(){
//                if(this.value==""){
//                    myAlert("温馨提示","工时不能为空！");
//                    flag=false;
//                    return;
//                }
//            });
//            $("input[id^='unitSal']").each(function(){
//                if(this.value==""){
//                    myAlert("温馨提示","元/时不能为空！");
//                    flag=false;
//                    return;
//                }
//            });
//            $("input[id^='salary']").each(function(){
//                if(this.value==""){
//                    myAlert("温馨提示","工资不能为空！");
//                    flag=false;
//                    return;
//                }
//            });
            $("input[money='money']").each(function(){
                var id=this.id;
                $("#"+id).val(this.value.replace(/[^\d\.-]/g, ""));
            });
            if(flag){
                $.ajax({
                    url:'<%=path%>/salary/save',
                    data:$("#expendForm").serializeArray(),
                    dataType:"json",
                    contentType:"application/x-www-form-urlencoded",
                    type:'post',
                    success:function(data){
                        if(data>0){
                            myAlert("温馨提示：","保存成功！",goBack);
                        }else{
                            myAlert("温馨提示：","保存失败！");
                        }
                    },
                    error:function () {
                        alert("生成失败！");
                    }
                });
            }
        }
        function goBack(){
            window.location.href = "<%=path%>/salary/index";
        }
        function changeDate(obj){
           if($(obj).val()>$("#today").val()){
               myAlert("温馨提示：","选择的日期不能大于今天！");
               $(obj).val("")
               return;
           }
           $("input[id^='salaryDate']").each(function (){
               $(this).val($(obj).val());
           })
        }
    </script>

</head>
<body>
<div class="container-fluid"  style="height:100%;width:96%; position:absolute;top:0;overflow-y: scroll;padding-right:20px ">
    <div style="height:10px"></div>
    <div>
        <button type="button" id="save" onclick="save()">保存</button>
        <button type="button" id="goBack" onclick="goBack()">取消</button>
    </div>
    <div style="height:10px"></div>
    <div>
        <form id="expendForm" method="post">
            <table id="atable">
                <tr>
                    <td colspan="4"><h4>记工</h4></td>
                </tr>
                <tr>
                    <input type="hidden" name="date" id="today" value="<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" ></fmt:formatDate>">
                    <td colspan="1">
                        日期：
                    </td>
                    <td colspan="3"><input type="text" id="date" value="<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" ></fmt:formatDate>" onchange="changeDate(this)"> </td>
                </tr>
                <tr><td>姓名</td><td>工时</td><td>元/时</td><td>工资</td></tr>
                <c:forEach items="${salaries}" var="salary" varStatus="statu" >
                    <tr>
                        <td>
                            <nt:username userid="${salary.userId}"></nt:username>
                            <input type="hidden" name="salaryList[${statu.count-1}].userId" value="${salary.userId}">
                        </td>
                        <td><input type="text" money="money" id="manHour${statu.count-1}" name="salaryList[${statu.count-1}].manHour" value="<fmt:formatNumber value="${salary.manHour}" pattern="##,#00.00"></fmt:formatNumber>" onblur="calcute(this)"></td>
                        <td><input type="text" money="money" id="unitSal${statu.count-1}" name="salaryList[${statu.count-1}].unitSalary" value="<fmt:formatNumber value="${salary.unitSalary}" pattern="##,#00.00"></fmt:formatNumber>" onfocus="rmoney(this)" onblur="calcute(this)"></td>
                        <td><input type="text" money="money" id="salary${statu.count-1}" name="salaryList[${statu.count-1}].salary" value="<fmt:formatNumber value="${salary.salary}" pattern="##,#00.00"></fmt:formatNumber>"></td>
                        <input type="hidden" id="salaryDate${statu.count-1}" name="salaryList[${statu.count-1}].salaryDate" value="${today}">
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</div>
<script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>

