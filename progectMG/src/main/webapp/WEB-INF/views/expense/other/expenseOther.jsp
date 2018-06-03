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
            if($("tr[name='detailtr']").length<1){
                myAlert("温馨提示：","请添加至少一条支出！");
                return;
            }
            if($("input[id$='expenseDate']").val()==""){
                myAlert("温馨提示：","请填写支出日期！");
                return;
            }
            if($("input[id$='totalPrice']").val()==""){
                myAlert("温馨提示：","请填写支出金额！");
                return;
            }
            if($("input[id$='remark']").val()==""){
                myAlert("温馨提示：","请填写支出内容！");
                return;
            }
            $("input[money='money']").each(function(){
                var id=this.id;
                $("#"+id).val(this.value.replace(/[^\d\.-]/g, ""));
            });

            $.ajax({
                url:'<%=path%>/expense/saveExpense',
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
            window.location.href = "<%=path%>/expense/indexOther";
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
        function addCredit(){
            var txtTRLastIndex = findObj("txtTRLastIndex",document);
            var rowID = parseInt(txtTRLastIndex.value);
            var creditTable = findObj("creditTable",document);
            var creditLength=$("#credit tr").length-2;

            var newTR = creditTable.insertRow(creditTable.rows.length);
            newTR.id = "creditTr" + rowID;

            var newTdPerAmt=newTR.insertCell(0);
            newTdPerAmt.innerHTML = "<select name='tCustmorCreditList["+(rowID)+"].perAmt' id='perAmt" + rowID + "' value=''>"
                +"<option value=''>请选择</option>"
                +"<nt:code ctype='004'></nt:code>"
                +"</select>";
            var newTdActAmt=newTR.insertCell(1);
            newTdActAmt.innerHTML = "<input name='tCustmorCreditList["+(rowID)+"].actAmt' id='actAmt" + rowID + "' type='text' value=''/>";
            var newTdBgDate=newTR.insertCell(2);
            newTdBgDate.innerHTML = "<input name='tCustmorCreditList["+(rowID)+"].bgDate' id='bgDate" + rowID + "' type='date'/>";
            var newTdEndDate=newTR.insertCell(3);
            newTdEndDate.innerHTML = "<input name='tCustmorCreditList["+(rowID)+"].endDate' id='endDate" + rowID + "' type='date' />";
            var newTdDelete=newTR.insertCell(4);
            newTdDelete.innerHTML = "<a href='javascript:;' class='btn' onclick=\"deleteCredit('tr" + rowID + "')\">删除</a>";

            txtTRLastIndex.value = (rowID + 1).toString();

            var oHead = document.getElementsByTagName('HEAD').item(0);
            var oScript= document.createElement("script");
            oScript.type = "text/javascript";
            oScript.src="<%=path%>/js/date/datecontroller.js";
            oHead.appendChild( oScript);

        }
        function findObj(theObj, theDoc) {
            var p, i, foundObj;
            if(!theDoc) theDoc = document;
            if( (p = theObj.indexOf("?")) > 0 && parent.frames.length) {
                theDoc = parent.frames[theObj.substring(p+1)].document;
                theObj = theObj.substring(0,p);
            }
            if(!(foundObj = theDoc[theObj]) && theDoc.all)
                foundObj = theDoc.all[theObj];
            for (i=0; !foundObj && i < theDoc.forms.length; i++)
                foundObj = theDoc.forms[i][theObj];
            for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
                foundObj = findObj(theObj,theDoc.layers[i].document);
            if(!foundObj && document.getElementById)
                foundObj = document.getElementById(theObj);
            return foundObj;
        }
        function deleteCredit(trid){
            var tab=document.getElementById("credit");
            var row=document.getElementById(trid);
            var index=row.rowIndex;//rowIndex属性为tr的索引值，从0开始
            tab.deleteRow(index);
            //重新排序序号
            var length=$("#credit tr").length;
            for(i=index;i<tab.rows.length;i++){
                tab.rows[i].cells[0].innerHTML = i.toString();
            }
        }
        function addRowId(id){
            id=parseInt(id);
            id++;
            $("#rowID").val(id);
        }
        function getDays(bgDate,endDate){
            var date11=new Date(bgDate);  //开始时间
            var date22=new Date(endDate);    //结束时间
            var date3=date22.getTime()-date11.getTime()  //时间差的毫秒数
            var days=Math.floor(date3/(24*3600*1000))
            return days;
        }
        function selectC(){
            window.open("<%=path%>/customer/index?select=1", "选择合作商", "menubar=no,status=no,resizable=no,scrollbars=1,width=1000,height=800pt,top=100,left=100");
        }
        function chooceCustomer(cNo,cName){
            $("#customerName").val(cName);
            $("#customerNo").val(cNo);
        }
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
        function addDetail() {
            var id=$("#detailCount").val();
            var str = "<tr name='detailtr' id='detailtr"+id+"'>"+
                "<td width='8%'>"+
                "<input type='text' name='details["+id+"].expenseDate'  id='"+id+"expensedate'  value=''/>"+
                "</td>"+
                "<td width='8%'><input class='input-block-level' type='text' money='money' name='details["+id+"].totalPrice'  id='"+id+"totalPrice' value='' onfocus='rmoney(this)' onblur='fmoney(this)'/></td>"+
                "<td width='8%'><input class='input-block-level' type='text' name='details["+id+"].remark'  id='"+id+"remark' value=''/></td>"+
                "<td width='5%'><button class='btn' type='button' onclick='deleteD("+id+")'>删除</button></td>"+
                "</tr>";
            $("#atable").append(str);
            id=parseInt(id);
            $("#detailCount").val(id+1);
            $(function () {
                $('button').button();
                $('input[id$=date]').attr({onfocus:"this.blur()"});
                //年月日
                //$('body').on('datepicker','input[id$=date]',function);
                $('input[id$=date]').datepicker({
                    dateFormat: 'yy-mm-dd',
                    //dayNames : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
                    //dayNamesShort : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    monthNamesShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                    //appendText : '日历',
                    showWeek: false,
                    weekHeader: '周',
                    firstDay: 0,
                    changeMonth: true,
                    changeYear: true,
                    //yearSuffix : '年',
                    showMonthAfterYear: true,
                    showButtonPanel: true,
                    closeText: '关闭',
                    currentText: '今天',
                    nextText: '下个月',
                    prevText: '上个月',
                    yearRange: '1949:2200'
                });
            });
        }
        function deleteD(id) {
            var trid = "detailtr"+id;
            $("#"+trid).remove();
            calcuteToTal();
        }
        function selectT(detailTr) {
            window.open("<%=path%>/tool/index?select=1&detailTr="+detailTr, "选择工具", "menubar=no,status=no,resizable=no,scrollbars=1,width=1000,height=800pt,top=100,left=100");
        }
        function chooceTool(Tno,Tname,detailTr) {
            $("#"+detailTr+"toolname").val(Tname);
            $("#"+detailTr+"toolno").val(Tno);
        }
        function calcuteToTal(){
            var totalQuantity=0;
            var totalPriceP=0;
            $("tr[name='detailtr']").each(function(){
                var id=this.id.substring(8);
                var unitPrice=0;
                var quantity=0;
                if($("#"+id+"unitPrice").val()!=""){
                    unitPrice=$("#"+id+"unitPrice").val().replace(/[^\d\.-]/g, "");
                }
                if($("#"+id+"quantity").val()!=""){
                    quantity=$("#"+id+"quantity").val();
                }
                var totalPrice=parseFloat(unitPrice)*parseFloat(quantity);
                $("#"+id+"totalPrice").val(fmoneys(totalPrice,2));
                $("#"+id+"unitPrice").val(fmoneys(unitPrice,2));
                totalQuantity+=parseFloat($("#"+id+"quantity").val());
                totalPriceP+=totalPrice;
            });
            if(totalQuantity>=0){
                $("#quantity").val(totalQuantity);
            }
            if(totalPriceP>=0){
                $("#totalPrice").val(fmoneys(totalPriceP,2));
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
        <form id="expendForm" method="post">
            <table id="atable">
                <input type="hidden" id="detailCount" value="0">
                <input type="hidden" name="edit" value="${edit}">
                <input type="hidden" name="type" value="4">
                <tr>
                    <td colspan="8"><h5>材料费支出</h5></td>
                </tr>
                <tr>
                    <td width="8%">支出时间</td>
                    <td width="8%">支出金额</td>
                    <td width="8%">支出内容</td>
                    <td width="5%"><button type="button" onclick="addDetail();">添加</button></td>
                </tr>
                <c:forEach var="detail" items="${details}" varStatus="status">
                    <tr name="detailtr" id="detailtr${status.count-1}">
                        <td><input type="text" name="details[${status.count-1}].expenseDate"  id="${status.count-1}expensedate"  value="<fmt:formatDate value='${detail.expenseDate}' pattern='yyyy-MM-dd'/>"/></td>
                        <input type="hidden" name="details[${status.count-1}].id" value="${detail.id}">
                        <td><input type="text" money="money" name="details[${status.count-1}].totalPrice"  id="${status.count-1}totalPrice" value="<fmt:formatNumber type='number' value='${detail.totalPrice}' pattern='#,##0.00'/>" onfocus="rmoney(this)" onblur="fmoney(this)"/></td>
                        <td><input type="text" name="details[${status.count-1}].remark"  id="${status.count-1}remark" value="${detail.remark}"/></td>
                        <td><button type="button" onclick="deleteD('${status.count-1}')">删除</button></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</div>
<script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>

