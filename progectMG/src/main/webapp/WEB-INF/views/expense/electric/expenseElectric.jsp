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

            //证件类型，新建默认为身份证
            if(${TNaturalPerson.certificateFlag==null}){
                $("#certificateFlag  option[value='3'] ").attr("selected",true);
            }

            if(${addtype==2}){
                $("#goBack").hide();
            }
            if(${edit=='2'}){
                $("#atable").find("input,button,textarea,select").attr("disabled", "disabled");
                $("#btable").find("input,button,textarea,select").attr("disabled", "disabled");
                $("#save").hide();
                $("#goBack").hide();
                $("button[InputHide='1']").each(function(){
                    var id=this.id;
                    $("#"+id).hide();
                });
            }else {
                $("input[InputSize='1']").attr('class', 'input-large');
            }
            var days=getDays($("#pBgdate").val(),$("#pEddate").val());
            if(days>=0){
                $("#days").val(days);
            }
        });

        //保存
        function save() {
            if($("#expensedate").val()==""){
                myAlert("温馨提示：","请先填写缴费日期！");
                return;
            }

            if($("#unitPrice").val()==""){
                myAlert("温馨提示：","请填写电费单价！");
                return;
            }
            if($("#ammeterTotal").val()==""){
                myAlert("温馨提示：","请填写总电表数！");
                return;
            }
            if($("#ammeterHb").val()==""){
                myAlert("温馨提示：","请填写总电表数！");
                return;
            }
            if($("#HBquantity").val()==""){
                myAlert("温馨提示：","请填写宏博用电量！");
                return;
            }
            if($("#HBtotalPrice").val()==""){
                myAlert("温馨提示：","请填写宏博电费！");
                return;
            }
            if($("#YCquantity").val()==""){
                myAlert("温馨提示：","请填写永辰用电量！");
                return;
            }
            if($("#YCtotalPrice").val()==""){
                myAlert("温馨提示：","请填写永辰电费！");
                return;
            }

            $("input[money='money']").each(function(){
                var id=this.id;
                $("#"+id).val(this.value.replace(/[^\d\.-]/g, ""));
            });

            $.ajax({
                url:'<%=path%>/expense/saveElec',
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
        //返回取消
        function goBack() {
            window.location.href = "<%=path%>/expense/indexElec";
        }
        $(function(){
            var start = 1960; // 指定开始年份
            var end = new Date().getFullYear();// 获取当前年份

            for(i=start;i<=end;i++) {
                if('${TNaturalPerson.graduationYear}'==i){
                    $("#myYear").append("<option selected='selected' value="+i+">"+i+"</option>");
                }else{
                    $("#myYear").append("<option value=" + i + ">" + i + "</option>")
                }
            }
        })
        function calcute(){
            if($("#ammeterTotal").val()==""){
                return;
            }
            if($("#ammeterHb").val()==""){
                return;
            }
            if($("#unitPrice").val()==""){
                return;
            }
            var ammeterTotal=$("#ammeterTotal").val().replace(/[^\d\.-]/g, "");
            var ammeterHb=$("#ammeterHb").val().replace(/[^\d\.-]/g, "");
            var unitPrice=$("#unitPrice").val().replace(/[^\d\.-]/g, "");
            $.ajax({
                url:'<%=path%>/expense/calcuteElec?ammeterTotal='+ammeterTotal+'&ammeterHb='+ammeterHb+'&unitPrice='+unitPrice+'&edit=${edit}'+'&id=${id}',
                contentType:"application/x-www-form-urlencoded",
                type:'get',
                success:function(data){
                    data = eval('(' + data + ')');
                    $("#HBquantity").val(fmoneys(data[0].quantity,2));
                    $("#YCquantity").val(fmoneys(data[1].quantity,2));
                    $("#HBtotalPrice").val(fmoneys(data[0].totalPrice,2));
                    $("#YCtotalPrice").val(fmoneys(data[1].totalPrice,2));
                },
                error:function () {
                    alert("生成失败！");
                }
            });
            $("#ammeterTotal").val(fmoneys($("#ammeterTotal").val().totalPrice,2));
            $("#ammeterHb").val(fmoneys($("#ammeterHb").val().totalPrice,2));
        }
        function compareExpenseDate(){
            if($("#expensedate").val()<$("#lastDate").val()){
                myAlert("温馨提示：","本次缴费日期不能小于上次缴费日期！");
                $("#expensedate").val("");
                return;
            }
        }
        function calcuteF(obj){
            if($("#expensedate").val()==""){
                myAlert("温馨提示：","请先填写缴费日期！");
                return;
            }
            if($("#unitPrice").val()==""){
                myAlert("温馨提示：","请先填写电费单价！");
                return;
            }
            $(obj).val($(obj).val().replace(/[^\d\.-]/g, ""));
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
                <input type="hidden" id="detailCount" value="0">
                <input type="hidden" name="edit" value="${edit}">
                <input type="hidden" name="type" value="1">
                <tr>
                    <td colspan="8"><h4>电费支出</h4></td>
                </tr>
                <tr>
                    <td>缴费日期</td>
                    <input type="hidden" id="lastDate" value="<fmt:formatDate value='${lastDate}' pattern='yyyy-MM-dd'/>">
                    <td><input type="text" name="expenseDate"  id="expensedate"  value="<fmt:formatDate value='${detail.expenseDate}' pattern='yyyy-MM-dd'/>" onchange="compareExpenseDate()"/></td>
                    <td>元/度</td>
                    <td><input type="text" money="money" name="unitPrice" id="unitPrice" value="<fmt:formatNumber type='number' value='${detail.unitPrice}' pattern='#,##0.0000'/>"/></td>
                </tr>
                <tr>
                    <td>总表读数(度)</td>
                    <td><input type="text" money="money" name="ammeterTotal" id="ammeterTotal" value="<fmt:formatNumber value='${detail.ammeterTotal}' pattern='#,##0.00'/>" onfocus="calcuteF(this)" onblur="calcute()"/></td>
                    <td>宏博电表数(度)</td>
                    <td><input type="text" money="money" name="ammeterHb"  id="ammeterHb" value="<fmt:formatNumber type='number' value='${detail.ammeterHb}' pattern='#,##0.00' />" onfocus="calcuteF(this)" onblur="calcute()"/></td>
                </tr>
                <tr>
                    <td>宏博用电量(度)</td>
                    <td><input type="text" money="money" id="HBquantity" name="HBquantity" value="<fmt:formatNumber value='${HBdetail.quantity}' pattern='#,##0.00'/>"/></td>
                    <td>宏博电费(元)</td>
                    <td><input type="text" money="money" id="HBtotalPrice" name="HBtotalPrice" value="<fmt:formatNumber type='number' value='${HBdetail.totalPrice}' pattern='#,##0.00'/>"/></td>
                </tr>
                <tr>
                    <td>永辰用电量(度)</td>
                    <td><input type="text" money="money" id="YCquantity" name="YCquantity" value="<fmt:formatNumber value='${YCdetail.quantity}' pattern='#,##0.00'/>"/></td>
                    <td>永辰电费(元)</td>
                    <td><input type="text" money="money" id="YCtotalPrice" name="YCtotalPrice" value="<fmt:formatNumber type='number' value='${YCdetail.totalPrice}' pattern='#,##0.00'/>"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>

