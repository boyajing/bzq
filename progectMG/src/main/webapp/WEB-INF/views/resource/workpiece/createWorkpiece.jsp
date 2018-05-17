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
    <script type="text/javascript" src="<%=path%>/js/workpieceverify/verifyworkpiece.js"></script>
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

            if(${edit=='1'}){
                $("#save").show();
                $("#goBack").hide();
            }else if(${edit=='2'}){
                $("#atable").find("input,button,textarea,select").attr("disabled", "disabled");
                $("#save").hide();
                $("#goBack").hide();
            }
        });

        //保存
        function save() {
            if($("#workpieceName").val()==""){
                myAlert("温馨提示：","请填写合作商名称！");
                return;
            }
            $.ajax({
                url:'<%=path%>/workpiece/save',
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
            window.location.href = "<%=path%>/workpiece/index";
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
            <input type="hidden" name="edit" value="${edit}" >
            <table>
                <tr>
                    <td colspan="4"><h4>基本信息</h4></td>
                </tr>
                <tr>
                    <td>工件编号</td>
                    <td>
                        <input readonly type="text" value="${workpiece.workpieceNo}"/>
                        <input type="hidden" name="workpieceNo" id="workpieceNo" value="${workpiece.workpieceNo}"/>
                    </td>
                    <td>工件名称</td>
                    <td>
                        <input type="text" name="workpieceName" id="workpieceName" value="${workpiece.workpieceName}"/>
                    </td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td colspan="3">
                        <select name="unit" id="unit">
                            <option value="">请选择</option>
                            <nt:code index="${workpiece.unit}" ctype="003"></nt:code>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="3"><textarea cols="10" name="remark">${workpiece.remark}</textarea> </td>
                </tr>
                <tr>
                    <td>录入人</td>
                    <td>${applyOpr}</td>
                    <td>录入日期</td>
                    <td>${applyDate}</td>
                </tr>
            </table>
            <%--<div>--%>
                <%--<table id="atable">--%>
                    <%--<tr>--%>
                        <%--<td colspan="4"><h4>基本信息</h4></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>机构编号</td>--%>
                        <%--<td>--%>
                            <%--<input readonly type="text" value="${tCustmorBasic.custmorNo}"/>--%>
                            <%--<input type="hidden" name="custmorNo" id="custmorNo" value="${tCustmorBasic.custmorNo}"/>--%>
                        <%--</td>--%>
                        <%--<td>机构名称</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="custmorName" id="custmorName" value="${tCustmorBasic.custmorName}"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>机构类型</td>--%>
                        <%--<td>--%>
                            <%--<select name="custmorType">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<nt:code index="${tCustmorBasic.custmorType}" ctype="004"></nt:code>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                        <%--<td>所属行业</td>--%>
                        <%--<td>--%>
                            <%--<select name="indNo">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<nt:code index="${tCustmorBasic.indNo}" ctype="005"></nt:code>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td >注册资本（千万）</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="regAmt" id="regAmt" value="${tCustmorBasic.regAmt}"/>--%>
                        <%--</td>--%>
                        <%--<td >企业性质</td>--%>
                        <%--<td>--%>
                            <%--<select name="ownType">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<nt:code index="${tCustmorBasic.ownType}" ctype="006"></nt:code>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>组织机构代码</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="orgCode" id="orgCode" value="${tCustmorBasic.orgCode}"/>--%>
                        <%--</td>--%>
                        <%--<td>地址</td>--%>
                        <%--<td><input type="text" name="addr" id="addr" value="${tCustmorBasic.addr}"/></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>银行基本户名称</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="bankName" id="bankName" value="${tCustmorBasic.bankName}"/>--%>
                        <%--</td>--%>
                        <%--<td>银行基本户账号</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="accNo" id="accNo" value="${tCustmorBasic.accNo}"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>联系人</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="connName" id="connName" value="${tCustmorBasic.connName}"/>--%>
                        <%--</td>--%>
                        <%--<td>联系电话</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="connPhone" id="connPhone" value="${tCustmorBasic.connPhone}"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>信用评级</td>--%>
                        <%--<td>--%>
                            <%--<select name="credit">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<nt:code index="${tCustmorBasic.credit}" ctype="007"></nt:code>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                        <%--<td></td>--%>
                        <%--<td></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
                <%--<table id="creditTable">--%>
                    <%--<input type='hidden' id='txtTRLastIndex' value="2" />--%>
                    <%--<tr>--%>
                        <%--<td><strong>授信信息</strong></td>--%>
                        <%--<td colspan="4" style="text-align:right;">--%>
                            <%--<button type="button" id="add" onclick="addCredit()">添加</button>--%>
                            <%--&lt;%&ndash;<button type="button" id="delete" onclick="deleteCredit()">删除</button>&ndash;%&gt;--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>授信类别</td>--%>
                        <%--<td>授信额度</td>--%>
                        <%--<td>授信开始日</td>--%>
                        <%--<td>授信到期日</td>--%>
                        <%--<td>操作</td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>
        </form>
    </div>
</div>
<script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>

