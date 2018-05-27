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
        //返回取消
        function goBack() {
            window.location.href = "<%=path%>/project/index?time=2";
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
        function stend(obj){
            if($("#pBgdate").val()==""){
                return;
            }
            if($("#pEddate").val()==""){
                return;
            }
            if($("#pBgdate").val()>$("#pEddate").val()){
                myAlert("温馨提示：","开始日不能大于结束日！");
                $(obj).val("");
                $("#days").val("");
                return;
            }
            var days=getDays($("#pBgdate").val(),$("#pEddate").val());
            if(days){
                $("#days").val(days);
            }
        }
        function save() {
            var flag=false;
            $("input[before='before']").each(function () {
                if(this.value!=""&&parseInt(this.value)>0){
                    flag=true;
                }
            });
            if(flag){
                $.ajax({
                    url:'<%=path%>/project/recordEnd',
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
            }else{
                myAlert("温馨提示：","请填写完工个数！");
            }
        }
        function calcute1(obj){
            var value=$(obj).val();
            if(value==""){
                return;
            }
            var id =obj.id;
            id=id.substring(3);
            var unEnd=$("#unEndQuantity"+id).val();
            if(parseInt(value) > parseInt(unEnd)){
                myAlert("温馨提示","本次完工数不能大于未完工总数！");
                $(obj).val("");
            }
        }
        function calcute2(obj){
            var value=$(obj).val();
            if(value==""){
                return;
            }
            var id =obj.id;
            id=id.substring(6);
            var unPickup=$("#unPickupQuantity"+id).val();
            if(parseInt(value) > parseInt(unPickup)){
                myAlert("温馨提示","本次提货数不能大于未提货总数！");
                $(obj).val("");
                return;
            }
            var total=parseInt($("#endQuantity"+id).val());
            if(parseInt($("#end"+id).val())>0){
                total=total+parseInt($("#end"+id).val());
            }
            if(parseInt(value)> total){
                myAlert("温馨提示","本次提货数不能大于完工总数！");
                $(obj).val("");
                return;
            }
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
        <input type="hidden" name="edit" value="${edit}" >
        <table id="atable">
            <tr>
                <td colspan="4"><h4>合同基本信息</h4></td>
            </tr>
            <tr>
                <td>合同编号</td>
                <td>
                    <input readonly type="text" value="${project.projectNo}"/>
                    <input type="hidden" name="project.projectNo" id="projectNo" value="${project.projectNo}"/>
                </td>
                <td>合作商</td>
                <td>
                    <input InputSize="1" type="text" readonly id="customerName" value="<nt:cusname cuscomerid="${project.customerNo}"></nt:cusname>"/>
                    <input type="hidden" id="customerNo" name="project.customerNo" value="${project.customerNo}"/>
                    <button InputHide="1" type="button" onclick="selectC()" id="selectCustomer" >选择</button>
                </td>
            </tr>
            <tr>
                <td>总数量</td>
                <td><input readonly type="text" id="quantity" name="project.quantity" value="${project.quantity}" ></td>
                <td>总金额</td>
                <td><input readonly type="text" money="money" id="totalPrice" name="project.totalPrice" value="<fmt:formatNumber type='number' value='${project.totalPrice}' pattern='#,##0.00'/>" ></td>
            </tr>
            <tr>
                <td>开始日期</td>
                <td><input type="text" id="pBgdate" name="project.beginDate" value="<fmt:formatDate value='${project.beginDate}' pattern='yyyy-MM-dd'/>" onchange="stend(this);"/></td>
                <td>结束日期</td>
                <td><input type="text" id="pEddate" name="project.endDate" value="<fmt:formatDate value='${project.endDate}' pattern='yyyy-MM-dd'/>" onchange="stend(this);"/></td>
            </tr>
            <tr>
                <td>预期天数</td>
                <td><input readonly type="text" id="days" value="" ></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>备注</td>
                <td colspan="3"><textarea cols="10" name="project.remark">${project.remark}</textarea> </td>
            </tr>
            <tr>
                <td>录入人</td>
                <td>${project.applyOpr}</td>
                <td>录入日期</td>
                <td><fmt:formatDate value="${project.applyDate}" pattern="yyyy-MM-dd" ></fmt:formatDate></td>
            </tr>
        </table>
        <table id="btable">
            <input type="hidden" id="detailCount" value="${fn:length(details)}">
            <tr>
                <td colspan="7"><h5>合同明细</h5></td>
            </tr>
            <tr>
                <td width="16%">工件</td>
                <td width="8%">单位</td>
                <td width="8%">单价</td>
                <td width="8%">数量</td>
                <td width="8%">总价</td>
                <td width="8%">加工类型</td>
                <%--<td>开始日期</td>--%>
                <%--gggg<td>结束日期</td>--%>
                <%--<td>天数</td>--%>
                <td width="8%">备注</td>
            </tr>
            <c:forEach var="detail" items="${details}" varStatus="status">
                <tr name="detailtr" id="detailtr${status.count-1}">
                    <td>
                        <input type="hidden" name="details[${status.count-1}].id" value="${detail.id}">
                        <input InputSize="1" readonly type="text" id="${status.count-1}workpiecename" value="<nt:workpiecename workpieceno="${detail.workpieceNo}"></nt:workpiecename>" />
                        <input type="hidden"  id="${status.count-1}workpieceno" name="details[${status.count-1}].workpieceNo" value="${detail.workpieceNo}"/>
                        <button InputHide="1" id="${status.count-1}select" type="button" onclick="selectWP('${status.count-1}')">选择</button>
                    </td>
                    <td>
                        <select name="details[${status.count-1}].unit" id="${status.count-1}unit">
                            <option value="">请选择</option>
                            <nt:code index="${detail.unit}" ctype="003"></nt:code>
                        </select>
                    </td>
                    <td><input type="text" money="money" name="details[${status.count-1}].unitPrice"  id="${status.count-1}unitPrice" onblur="calcuteToTal()" onfocus="rmoney(this)" value="<fmt:formatNumber type='number' value='${detail.unitPrice}' pattern='#,##0.00'/>"/></td>
                    <td><input type="text" name="details[${status.count-1}].quantity"  id="${status.count-1}quantity" value="${detail.quantity}" onblur="calcuteToTal()"/></td>
                    <td><input type="text" money="money" name="details[${status.count-1}].totalPrice"  id="${status.count-1}totalPrice" value="<fmt:formatNumber type='number' value='${detail.totalPrice}' pattern='#,##0.00'/>"/></td>
                    <td>
                        <select name="details[${status.count-1}].processType" id="${status.count-1}processType">
                            <option value="">请选择</option>
                            <nt:code index="${detail.processType}" ctype="006"></nt:code>
                        </select>
                    </td>
                    <td><input type="text" name="details[${status.count-1}].remark"  id="${status.count-1}remark" value="${detail.remark}"/></td>
                </tr>
            </c:forEach>
        </table>
        <form id="expendForm" method="post">
            <table id="ctable">
                <tr>
                    <td colspan="8"><h4>合同进度记录</h4></td>
                </tr>
                <tr>
                    <td width="16%">工件</td>
                    <td width="8%">总数量</td>
                    <td width="8%">已完工数量</td>
                    <td width="8%">未完工个数</td>
                    <td width="8%">已提货数量</td>
                    <td width="8%">未提货个数</td>
                    <td width="8%">完工记录</td>
                    <td width="8%">提货记录</td>
                </tr>
                <c:forEach var="detail" items="${details}" varStatus="status">
                    <tr>
                        <input type="hidden" name="details[${status.count-1}].id" value="${detail.id}">
                        <td><input type="text" readonly value="<nt:workpiecename workpieceno="${detail.workpieceNo}"></nt:workpiecename>"/></td>
                        <td><input type="text" readonly id="quantity${status.count-1}" value="${detail.quantity}"></td>
                        <td><input type="text" readonly id="endQuantity${status.count-1}" value="${detail.endQuantity}"></td>
                        <td><input type="text" readonly id="unEndQuantity${status.count-1}" value="${detail.quantity-detail.endQuantity}"></td>
                        <td><input type="text" readonly id="pickupQuantity${status.count-1}" value="${detail.pickupQuantity}"></td>
                        <td><input type="text" readonly id="unPickupQuantity${status.count-1}" value="${detail.quantity-detail.pickupQuantity}"></td>
                        <td>
                        <c:if test="${detail.status!=5 && detail.status!=6}">
                            <input type="text" id="end${status.count-1}" name="details[${status.count-1}].endQuantity" before="before" onblur="calcute1(this)" value="">
                        </c:if>
                        <c:if test="${detail.status==5 || detail.status==6}">
                            全部完工
                        </c:if>
                        </td>
                        <td><input type="text" id="pickup${status.count-1}" name="details[${status.count-1}].pickupQuantity" before="before" onblur="calcute2(this)" value=""></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</div>
<script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>

