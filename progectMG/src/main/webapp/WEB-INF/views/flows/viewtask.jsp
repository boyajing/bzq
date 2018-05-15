<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String cpath = request.getContextPath();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
        <link rel="shortcut icon" href="<%=cpath%>/images/icons/ico.ico" type="image/x-icon" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="" >
        <link rel="stylesheet" type="text/css" href="<%=cpath%>/js/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="<%=cpath%>/css/theme.css">
        <script src="<%=cpath%>/js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/openr.js"  type="text/javascript"></script>
        <script type="text/javascript" src="<%=cpath%>/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/queryframe.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/numberformat.js"></script>
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/js/date/jquery.ui.css">
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/css/mypage.css">
        <script type="text/javascript" src="<%=cpath%>/js/date/jquery.ui.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/date/datecontroller.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/WindowTracking.js"></script>
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/css/css-loader.css">
        <title></title>
        <style>

            .listhotitem{
                color:red;
                font-weight: bold
            }
            .diagramModal{
                margin: -120px 0 0 -450px;
            }
            .selectUserModal{
                position: absolute;
                z-index: 999999;
            }
            .diagramModalBody{
                max-height: 600px;

                overflow-y: auto;
                padding: 15px;
            }
            .approveformCtrlw120px{
                margin-left: 80px!important;
            }
            .approveformLabelw120px{
                width:100px!important;
            }
            .userselectCSS{


            }
            .userselectCSS .buttonCol{
                width:100px
            }
            .userselectCSS #selectedOptions{
                height: 216px;
                width: 250px
            }
            .userselectCSS #sourceOptions{
                height: 216px;

                width: 250px
            }
            .userselectCSS .userBtn{
                display:block;
                width:90px

            }
        </style>
        <script>
            //全局变量
            var formID = "<c:out value='${result.formID}'/>";
            var businessURL = "<c:out value='${result.businessURL}'/>".replace(/&amp;/g,"&");
            var businessKEY = "<c:out value='${result.businessKey}'/>".replace(/&amp;/g,"&");
            var taskid = "<c:out value='${result.taskID}'/>";
            var taskName = "<c:out value='${result.taskName}'/>";
            var taskDefinitionKey= "<c:out value='${result.taskDefinitionKey}'/>";
            var assignBtn = "<c:out value='${result.docConfig.assginBtn}'/>";
            var assignVarName = "<c:out value='${result.docConfig.assignVarName}'/>";
            var processid = "<c:out value='${result.processid}'/>";

            var processDefinitionId = "<c:out value='${result.processDefinitionId}'/>";
            var assigngroups = "<c:out value='${result.docConfig.assignGroups}'/>".replace("[", "").replace("]", "");
            var assignMutil = ("<c:out value='${result.docConfig.assignMutil}'/>" == "true");

            //函数句柄
            var btnClick;
            var hisBtnClick;
            var selectUserClick;

            //重大预警事项流程选择标志
            var imptKey;

            //获取流程的用户任务节点，当期节点标红
            var getUserActivity = function () {
                //请求当前任务所在的流程实例的全部人工流程，用于显示列表
                if(processDefinitionId.indexOf("importantflow") != -1) {
                    $.ajax({
                        type: 'POST',
                        url: '<%=cpath%>/riskWarning/putInto/findImptKey.json',
                        dataType: 'json',
                        async: false,
                        data: {
                            pkId: businessKEY
                        },
                        success: function (data) {
                            if(data!=null){
                                imptKey = data.imptKey;
                            }
                        },
                        error: function (user) {
                            alert("查询出错");
                        }
                    });
                    //重大预警事项流程左侧菜单显示
                    $.ajax({
                        type: 'GET',
                        url: '<%=cpath%>/flow/activitybyprocessDefinitionid/' + processDefinitionId + '.json',

                        dataType: 'json',
                        success: function (data) {
                            for (var i = 0; i < 5; i++) {
                                if (taskDefinitionKey == data.result[i].id) {
                                    $("#tasklist").append(' <li class="list-group-item listhotitem" >' + data.result[i].name + "</li>" );

                                } else {
                                    $("#tasklist").append(' <li class="list-group-item">' + data.result[i].name + "</li>" );
                                }
                            }
                            if(imptKey=="1"){
                                for (var i = 8; i < 14; i++) {
                                    if (taskDefinitionKey == data.result[i].id) {
                                        $("#tasklist").append(' <li class="list-group-item listhotitem" >' + data.result[i].name + "</li>" );

                                    } else {
                                        $("#tasklist").append(' <li class="list-group-item">' + data.result[i].name + "</li>" );
                                    }
                                }
                            }
                            if(imptKey=="2"){
                                for (var i = 5; i < 8; i++) {
                                    if (taskDefinitionKey == data.result[i].id) {
                                        $("#tasklist").append(' <li class="list-group-item listhotitem" >' + data.result[i].name + "</li>" );

                                    } else {
                                        $("#tasklist").append(' <li class="list-group-item">' + data.result[i].name + "</li>" );
                                    }
                                }
                            }
                            if(imptKey=="3"){
                                for (var i = 14; i < data.result.length; i++) {
                                    if (taskDefinitionKey == data.result[i].id) {
                                        $("#tasklist").append(' <li class="list-group-item listhotitem" >' + data.result[i].name + "</li>" );

                                    } else {
                                        $("#tasklist").append(' <li class="list-group-item">' + data.result[i].name + "</li>" );
                                    }
                                }
                            }
                        },
                        error: function (user) {
//                        alert(user);
                        }
                    });
                }else{
                    $.ajax({
                        type: 'GET',
                        url: '<%=cpath%>/flow/activitybyprocessDefinitionid/' + processDefinitionId + '.json',

                        dataType: 'json',
                        success: function (data) {

                            for (var i = 0; i < data.result.length; i++) {
                                if (taskDefinitionKey == data.result[i].id) {
                                    $("#tasklist").append(' <li class="list-group-item listhotitem" >' + data.result[i].name + "</li>" );

                                } else {
                                    $("#tasklist").append(' <li class="list-group-item">' + data.result[i].name + "</li>" );
                                }
                            }
                        },
                        error: function (user) {
//                        alert(user);
                        }
                    });
                }

            };
            ////////////////////////////////////////////////////////////////////
            //jQuery主程序
            $(document).ready(function () {
                //点击显示历史按钮
                $("#historybtn").click(function () {
                    //显示流程执行历史窗口
                    $('#processHis').modal('show').css({
                        width: '70%',
                        'margin-left': function () {
                            return -($(this).width() / 2);
                        }
                    });
                });
//                //点击显示相关意见窗口
//                $("#opinionlistbtn").click(function () {
//                    //显示流程执行历史窗口
//                    $('#opinionlist').modal('show').css({
//                        width: '70%',
//                        'margin-left': function () {
//                            return -($(this).width() / 2);
//                        }
//                    });
//                });



                //显示流程图
                $("#showdiagram").click(function () {
                    $('#diagramModal').modal('show').css({
                        width: '90%',

                        'margin-left': function () {
                            return -($(this).width() / 2);
                        }
                    });
                });

                //显示流程环节列表
                getUserActivity();

                //主界面中添加业务页面
                <%--if (businessURL != "") {//--%>
                    <%--$("#contentFrm").attr("src","<%=cpath%>" + businessURL);--%>
                   <%----%>
                <%--}--%>

              /*  if (formID != null && formID != "" && !(businessURL.indexOf("roject/queryAll?projectcode") >0 || businessURL.indexOf("rogramme/queryAll?schemeId") >0)) {
                    formID=formID.replace(/&amp;/g,"&");//核保功能需要在formID中给页面传递参数
                    $("#contentFrm").attr("src", "<%=cpath%>" + formID + businessKEY + "&status=1&processid=${result.processid}");
                }*/
                var pr = processDefinitionId.split(":");
                if (formID != null && formID != "" && pr[0] == "meetingflow") {
                    formID=formID.replace(/&amp;/g,"&");//核保功能需要在formID中给页面传递参数
                    $("#contentFrm").attr("src", "<%=cpath%>" + formID + businessKEY + "&status=1&processid=${result.processid}");
                    //下边为处理不良出账已办页面跳转单独页面的功能实现
                } else if(pr[0] == "expend"&&(taskDefinitionKey=="xiangmujinglitiqing1"||taskDefinitionKey=="bumenzongjingli1"||taskDefinitionKey=="fengxianjingban1"||taskDefinitionKey=="jicaibujingban1")){
                    $("#contentFrm").attr("src", "<%=cpath%>/expendmanage/bliFrame?tasktype=2&pkCode="+businessKEY+"&status=2&processid=${result.processid}");
                } else {
                    $("#contentFrm").attr("src", "<%=cpath%>" + businessURL + "&status=1&processid=${result.processid}");
                }

                $("#contentFrm").load(function(){
                    $("#contentFrm").get(0).contentWindow.initBytask(taskDefinitionKey,true,processid,taskid);
                })
                function resizeIframe() {
                    var obj = $("#contentFrm").each(function () {

                        var scrollHeight = this.contentWindow.document.body.scrollHeight == 0 ? this.contentWindow.document.documentElement.scrollHeight : this.contentWindow.document.body.scrollHeight;
                        var scrollWidth = this.contentWindow.document.body.scrollWidth == 0 ? this.contentWindow.document.documentElement.scrollWidth : this.contentWindow.document.body.scrollWidth;
                        if (scrollHeight != 0) {
                            if (scrollHeight > $(this).css("height").replace("px", "") * 1) {
                                $(this).css("height", (scrollHeight) + "px");
                            }
                        }
                        if (scrollWidth != 0) {
                            if (scrollWidth > $(this).css("width").replace("px", "") * 1) {
                                $(this).css("width", (scrollWidth) + "px");
                            }
                        }
                    });
                }
                window.setInterval(resizeIframe, 1000);
            });
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--工具条-->
                <div class="btn-toolbar">                                        
                    <%--<a  id="opinionlistbtn" href="#" role="button" data-toggle="modal" class="btn" btnVarName="<c:out value="${buttons.varName}"/>" btnValue="<c:out value="${buttons.varValue}"/>">--%>
                    <%--<i class="icon-plus"></i>相关部门意见--%>
                    <%--</a>--%>
                    <a  id="historybtn" href="#" onclick="hisBtnClick(this)" role="button" data-toggle="modal" class="btn" btnVarName="<c:out value="${buttons.varName}"/>" btnValue="<c:out value="${buttons.varValue}"/>">
                        <i class="icon-plus"></i>审批历史
                    </a>
                    <a  id="showdiagram" href="#" role="button" data-toggle="modal" class="btn" btnVarName="<c:out value="${buttons.varName}"/>" btnValue="<c:out value="${buttons.varValue}"/>">
                        <i class="icon-plus"></i>审批流程
                    </a>
                </div> 

                <div class="row-fluid">
                    <table id="maintable" style="border:0px;width:100%">
                        <tr>
                            <td style="width:200px;text-align: left;vertical-align: top;background-color:#FFFFFF;border: 0px">

                                <!--左侧流程环节列表-->
                                <h6><c:out value="${result.processName}"/></h6>
                                <div>
                                    <ul  id="tasklist" class="list-group"></ul>
                                </div>
                            </td>
                            <td style="text-align: left;vertical-align: top;background-color:#FFFFFF;border: 0px">
                                <!--内嵌的用于显示业务表单的区域-->
                                <iframe id="contentFrm" style="width:100%" frameBorder=0 src=""></iframe>

                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>



        <!--流程图窗口-->
        <div class="modal diagramModal fade hide" id="diagramModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel1">审批流程</h3>
            </div>
            <div class="diagramModalBody">
                <img src="<%=cpath%>/flow/processImg/<c:out value='${result.processid}'/>"/>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>

            </div>
        </div>

        <!--流程历史窗口-->
        <div class="modal diagramModal fade hide" id="processHis" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel2">历史操作</h3>
            </div>
            <div class="diagramModalBody">
                <iframe src='<%=cpath%>/flow/processHis/<c:out value="${result.processid}"/>' width="99%" height="400px"></iframe>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>

            </div>
        </div>
        <!--相关部门意见窗口-->
        <%--<div class="modal diagramModal fade hide" id="opinionlist" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
        <%--<div class="modal-header">--%>
        <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>--%>
        <%--<h3 id="opinionlist1">相关部门意见</h3>--%>
        <%--</div>--%>
        <%--<div class="diagramModalBody">--%>
        <%--<iframe src='<%=cpath%>/flow/opinionlist/<c:out value="${result.processid}"/>' width="99%" height="400px"></iframe>--%>
        <%--</div>--%>
        <%--<div class="modal-footer">--%>
        <%--<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>--%>

        <%--</div>--%>
        <%--</div>--%>

        <!--点击发送窗口-->
        <div class="modal diagramModal fade hide" id="approveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="approveLabel"></h3>
            </div>
            <div class="diagramModalBody">
                <div class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label approveformLabelw120px" for='sendto'>发送至：</label>
                        <div class="controls approveformCtrlw120px" >
                            <%@include file="selectuserinner.jsp" %>
                            <span id="rejectusername">
                                <span id="norejectnodelabel">没有可以驳回的节点</span>
                                <select id="rejectactitiy">

                                </select>
                            </span>
                        </div>
                    </div>
                    <%//循环产生窗体%>
                    <c:forEach var="formitem" items="${result.docConfig.forms}" varStatus="status">
                        <div class="control-group">
                            <label class="control-label approveformLabelw120px" for='<c:out value="${formitem.varName}"/>'><c:out value="${formitem.name}"/></label>
                            <c:if test="${formitem.type=='text'}">
                                <div class="controls approveformCtrlw120px">
                                    <textarea formelement="true" id='form_<c:out value="${formitem.varName}"/>' style="width:90%;height: 100px" rows="3" name='form_<c:out value="${formitem.varName}"/>'></textarea>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>

            </div>
            <div class="modal-footer">
                <button id='approveActionBtn' class="btn btn-large btn-primary"  data-dismiss="modal" aria-hidden="true"></button>
                <button class="btn btn-large" data-dismiss="modal" aria-hidden="true">关闭</button>

            </div>
        </div>


        <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
