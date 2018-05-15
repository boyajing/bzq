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
            .modal.fade.in{
                top:150px;
            }
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
            var taskDefinitionKey = "<c:out value='${result.taskDefinitionKey}'/>";
            var taskName = "<c:out value='${result.taskName}'/>";
            var assignBtn = "<c:out value='${result.docConfig.assginBtn}'/>";
            var assignVarName = "<c:out value='${result.docConfig.assignVarName}'/>";
            var processid = "<c:out value='${result.processid}'/>";
            var processDefinitionId = "<c:out value='${result.processDefinitionId}'/>";
            var assigngroups = "<c:out value='${result.docConfig.assignGroups}'/>".replace("[", "").replace("]", "");
            var assignMutil = ("<c:out value='${result.docConfig.assignMutil}'/>" == "true");
            var forms = "<c:out value='${result.docConfig.forms}'/>"
            var recipients = '${recipients}';

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
                //点击显示相关意见窗口
//                $("#opinionlistbtn").click(function () {
//                    //显示流程执行历史窗口
//                    $('#opinionlist').modal('show').css({
//                        width: '70%',
//                        'margin-left': function () {
//                            return -($(this).width() / 2);
//                        }
//                    });
//                });

                //点击发送或驳回按钮处理程序
                $("#approveActionBtn").click(function () {
                    if ($(this).attr("btndirect") == "go") {

                        var dodata = new FormData();
                        //发送 判断是否配置候选人角色，如果配置判断候选人是否选择，没有不判断候选人员
                        if (assigngroups == null || assigngroups == "") {

                        } else {
                            var asuser = getSelectedUserList()[0];
                            //判断，如果没有选择人 不能发送
                            if (asuser == null || asuser == "") {
                                alert("请选择办理人员");
                                return false;
                            }
                            dodata.append("assignUser", asuser);
                        }
                        //判断候选组如果有多个，这里就把候选人放入流程变量中
                        if (recipients) {
                            //在这里放入选中的人员信息
                            $.each(eval(recipients),function(i,item){
                                var userid = $("#iframe_"+ item.id).get(0).contentWindow.getSelectedUserId();
                                if (userid == null || userid == "") {
                                    alert("请确保每个分支都选择了办理人员");
                                    return false;
                                }
                                dodata.append("P_"+item.reVarName ,userid);
                            });
                        }
                        var a =1;
                        $("[formelement]").each(function () {//审批意见
                            if($(this).val() == null || $(this).val() == ""){
                                alert("请填写审查意见！");
                                a=0;
                            }else {
                                dodata.append($(this).attr('id').replace("form_", ""), $(this).val());
                            }
                        });
                        if(a==0){
                            return false;
                        }
                        if ($('#fileupload').get(0) && $('#fileupload').get(0).files[0]) {
                            dodata.append('file', $('#fileupload').get(0).files[0]);
                        }
                        dodata.append($(this).attr("varname"), $(this).attr("varvalue"));
                        dodata.append('btnName', $(this).text());
                        dotask(dodata);
                    }

                    if ($(this).attr("btndirect") == "back") {
                        //驳回 处理逻辑
                       // var dodata = new Object;
                        var dodata = new FormData();//zhujianhu
                        //dodata["assignUser"] = asuser;
                        var a =1;
                        $("[formelement]").each(function () {//审批意见
                            if($(this).val() == null || $(this).val() == ""){
                                alert("请填写审查意见！");
                                a=0;
                            }else {
                                dodata.append($(this).attr('id').replace("form_", ""), $(this).val());
                            }
                        });
                        if(a==0){
                            return false;
                        }
                        if ($('#fileupload').get(0) && $('#fileupload').get(0).files[0]) {
                            dodata.append('file', $('#fileupload').get(0).files[0]);
                        }
                       // dodata[$(this).attr("varname")] = $(this).attr("varvalue");
                        dodata.append($(this).attr("varname"), $(this).attr("varvalue"));
                        $.ajax({
                            type: 'POST',
                            url: '<%=cpath%>/flow/backtask/' + taskid + '/' + $("#rejectactitiy").val() + '.json',
                            async: false,
                            cache: false,
                            data: dodata,
                            processData: false,
                            contentType: false,
                            success: function (data) {
                                if(data.result=="sucess"){
                                    alert("处理成功！");
                                    //刷新父窗口，关闭页面
                                    window.opener.location.reload();
                                    window.close();
                                }else{
                                    alert("处理失败！");
                                    window.opener.location.reload();
                                }
                            },
                            error: function (user) {
                                alert("处理失败！");
                                window.opener.location.reload();
                            }
                        });
                    }
                    if ($(this).attr("btndirect") == "votedown") {
                        //var dodata = new Object;
                        var dodata = new FormData();//zhujianhu
                        var a =1;
                        $("[formelement]").each(function () {//审批意见
                            if($(this).val() == null || $(this).val() == ""){
                                alert("请填写审查意见！");
                                a=0;
                            }else {
                                dodata.append($(this).attr('id').replace("form_", ""), $(this).val());
                            }
                        });
                        if(a==0){
                            return false;
                        }
                        if ($('#fileupload').get(0) && $('#fileupload').get(0).files[0]) {
                            dodata.append('file', $('#fileupload').get(0).files[0]);
                        }

                        dodata.append($(this).attr("varname"), $(this).attr("varvalue"));
                        $.ajax({
                            type: 'POST',
                            url: '<%=cpath%>/flow/votedprocess/' + taskid + '.json',
                            async: false,
                            cache: false,
                            data: dodata,
                            processData: false,
                            contentType: false,
                            success: function (data) {
                                if(data.result=="sucess"){
                                    alert("处理成功！");
                                    //刷新父窗口，关闭页面
                                    window.opener.location.reload();
                                    window.close();
                                }else{
                                    alert("处理失败！");
                                    window.opener.location.reload();
                                }
                            },
                            error: function (user) {
                                alert("处理失败！");
                                window.opener.location.reload();
                            }
                        });
                    }
                });

                //点击通过按钮
                $("#approvebtn").click(function () {
                    //这里调用业务验证是否可以发送的方法
                    var checkSecnd = $("#contentFrm").get(0).contentWindow.checkSend( taskDefinitionKey );
                    if(checkSecnd != 1){
                        return false;
                    }
                    var obj = this;
                    //在这里判断如果候选人员组合候选意见框为空，提示用户确认要发送吗，用户点击确认 后直接发送   recipients为空表示候选的多人组也为空
                    if((assigngroups == null || assigngroups =="")&&(forms =="[]") && !recipients ){
                        if(confirm("确认要进行发送吗？")){
                            //在这里加入发送任务代码
                            var dodata = new FormData();
                            dotask(dodata);
                        }
                        return false;
                    }

                    //初始化审批意见
                    var initoption = $(this).text().trim();
                   /* if($("#form_commit")){
                        $("#form_commit").val(initoption);
                    }*/

                    $("#approveLabel").text(initoption);//设定小窗口标题
                    $("#approveActionBtn").attr("varname", $(obj).attr("id"));
                    $("#approveActionBtn").attr("varvalue", $(obj).attr("varvalue"));
                    $("#approveActionBtn").attr("btndirect", "go");
                    $("#approveActionBtn").text(initoption);//设定小窗口按钮名称
                    $("#recipients").show();//显示分发框
                    $("#rejectusername").hide();//隐藏驳回到的用户名
                    //在这里判断是否是最后一步，是最后一步不显示选人列表
                    if(!recipients){
                        reloadUserSelectedList({"role": assigngroups, "assignMutil": assignMutil});
                    }else{
                        disableUserSelectList();
                    }

                    $('#approveModal').modal('show').css({//显示模式窗口
                        width: '60%',
                        'margin-left': function () {
                            return -($(this).width() / 2);
                        }
                    });
                });

                //发送通用方法，同点击发送按钮作用，用于跳转流程调用
                function approveCommon(obj){                   //这里调用业务验证是否可以发送的方法

                    var checkSecnd = $("#contentFrm").get(0).contentWindow.checkSend( taskDefinitionKey );
                    if(checkSecnd != 1){
                        return false;
                    }

                    //在这里判断如果候选人员组合候选意见框为空，提示用户确认要发送吗，用户点击确认 后直接发送   recipients为空表示候选的多人组也为空
                    if((assigngroups == null || assigngroups =="")&&(forms =="[]") && !recipients ){
                        if(confirm("确认要进行发送吗？")){
                            //在这里加入发送任务代码
                            var dodata = new FormData();
                            dotask(dodata);
                        }
                        return false;
                    }

                    //初始化审批意见
                    var initoption = obj.text().trim();
                    /* if($("#form_commit")){
                     $("#form_commit").val(initoption);
                     }*/

                    $("#approveLabel").text(initoption);//设定小窗口标题
                    $("#approveActionBtn").attr("varname", obj.attr("id"));
                    $("#approveActionBtn").attr("varvalue", obj.attr("varvalue"));
                    $("#approveActionBtn").attr("btndirect", "go");
                    $("#approveActionBtn").text(initoption);//设定小窗口按钮名称
                    $("#recipients").show();//显示分发框
                    $("#rejectusername").hide();//隐藏驳回到的用户名
                    //在这里判断是否是最后一步，是最后一步不显示选人列表
                    if(!recipients){
                        reloadUserSelectedList({"role": assigngroups, "assignMutil": assignMutil});
                    }else{
                        disableUserSelectList();
                    }

                    $('#approveModal').modal('show').css({//显示模式窗口
                        width: '60%',
                        'margin-left': function () {
                            return -($(this).width() / 2);
                        }
                    });
                }

                //点击驳回按钮
                $("#rejectbtn").click(function () {
                    var obj = this;
                    //初始化审批意见
                    var initoption = $(this).text().trim();
                  /*  if($("#form_commit")){
                        $("#form_commit").val(initoption);
                    }*/
                    $("#approveLabel").text(initoption);
                    $("#approveActionBtn").attr("btndirect", "back");
                    $("#approveActionBtn").text(initoption);
                    $("#approveActionBtn").attr("varname", $(obj).attr("id"));
                    $("#approveActionBtn").attr("varvalue", $(obj).attr("varvalue"));

                    disableUserSelectList();//隐藏选人的列表
                    $("#recipients").hide();//隐藏分发框
                    $("#rejectusername").show();//显示驳回到的节点
                    $("#rejectactitiy option").remove();
                    //加载要驳回的节点列表
                    $.ajax({
                        type: 'GET',
                        url: '<%=cpath%>/flow/rejectactivitylist/' + processid + '/' + taskid + ".json",
                        dataType: 'json',
                        async: false,
                        success: function (data) {
                            //刷新父窗口，关闭页面
                            if (data.result) {
                                $("#norejectnodelabel").hide();
                                $("#rejectactitiy").show();

                                for (i = 0; i < data.result.length; i++) {
                                    $("#rejectactitiy").append("<option value='" + data.result[i].id + "'>" + data.result[i].name + "</option>")
                                }
                            } else {
                                $("#norejectnodelabel").show();
                                $("#rejectactitiy").hide();
                            }
                        },
                        error: function (user) {
                            alert("处理失败！");
                            window.opener.location.reload();
                        }
                    });

                    $('#approveModal').modal('show').css({
                        width: '60%',
                        'margin-left': function () {
                            return -($(this).width() / 2);
                        }
                    });
                });

                //驳回通用方法，同点击驳回按钮作用，用于跳转流程调用
                function rejectCommon() {
                    if(!confirm("确认发送？")){
                        return;
                    }else{
                        var dodata = new FormData();//zhujianhu
                        dodata.append("approveCommonbtn", "1");
                        $.ajax({
                            type: 'POST',
                            url: '<%=cpath%>/flow/backtask/' + taskid + '/' + "huiyimishuappprove" + '.json',
                            async: false,
                            cache: false,
                            data: dodata,
                            processData: false,
                            contentType: false,
                            success: function (data) {
                                if(data.result=="sucess"){
                                    alert("处理成功！");
                                    //刷新父窗口，关闭页面
                                    window.opener.location.reload();
                                    window.close();
                                }else{
                                    alert("处理失败！");
                                    window.opener.location.reload();
                                }
                            },
                            error: function (user) {
                                alert("处理失败！");
                                window.opener.location.reload();
                            }
                        });
                    }
                }

                //点击通用发送按钮，用于跳转流程
                $("#approveCommonbtn").click(function () {
                    var obj = this;
                    $.ajax({
                        type: 'POST',
                        url: '<%=cpath%>/meetingflow/findAuthority.json',
                        dataType: 'json',
                        async: false,
                        data : {
                            meetingId : businessKEY
                        },
                        success: function (data) {
                            if(data.authority=="1"){
                                approveCommon($("#approveCommonbtn"));
                            }
                            else if(data.authority=="2"){
                                rejectCommon();
                            }
                            else{
                                alert(data.authority+"end");
                            }
                        },
                        error: function (user) {
                            alert("保存审批权限失败！");
                        }
                    });
                });

                //点击否决按钮
                $("#votedBtn").click(function () {
                    var obj = this;
                    //初始化审批意见
                    var initoption = $(this).text().trim();
                    /*if($("#form_commit")){
                        $("#form_commit").val(initoption);
                    }*/
                    $("#approveLabel").text(initoption);
                    $("#approveActionBtn").attr("btndirect", "votedown");
                    $("#approveActionBtn").text(initoption);
                    $("#approveActionBtn").attr("varname", $(obj).attr("id"));
                    $("#approveActionBtn").attr("varvalue", $(obj).attr("varvalue"));
                    disableUserSelectList();//隐藏选人的列表
                    $("#recipients").hide();
                    $("#rejectusername").hide();//隐藏驳回到的用户名
                    $("#rejectactitiy option").remove();
                    $('#approveModal').modal('show').css({
                        width: '60%',
                        'margin-left': function () {
                            return -($(this).width() / 2);
                        }
                    });
                });

                //点击补充材料按钮
                $("#annexBtn").click(function () {
                    var obj = this;
                    //初始化审批意见
                    var initoption = $(this).text().trim();
                   /* if($("#form_commit")){
                        $("#form_commit").val(initoption);
                    }*/
                    $("#approveLabel").text(initoption);
                    $("#approveActionBtn").attr("btndirect", "go");
                    $("#approveActionBtn").text(initoption);
                    $("#approveActionBtn").attr("varname", $(obj).attr("id"));
                    $("#approveActionBtn").attr("varvalue", $(obj).attr("varvalue"));
                    disableUserSelectList();//隐藏选人的列表
                    $("#rejectusername").hide();//隐藏驳回到的用户名
                    $("#rejectactitiy option").remove();
                    $('#approveModal').modal('show').css({
                        width: '60%',
                        'margin-left': function () {
                            return -($(this).width() / 2);
                        }
                    });
                });

                //点击发起会议按钮处理逻辑
                $("#approvemeeting").click(function () {
                    window.location.href = "<%=cpath%>" + "/meetingflow/start/" + taskid;
                });
                //点击发起会议按钮，用于重大预警事项上会逻辑
                $("#approvemeetingipt").click(function () {
                    window.location.href = "<%=cpath%>" + "/meetingflow/startipt/" + taskid;
                });

                //方案审批经办点击补充材料
                $("#supplementBtn").click(function () {
                    var businessurl=businessURL;
                    var projectName= "";
                    var projectCode= "";
                    //根据方案编码查询方案信息
                    $.ajax({
                        type: 'POST',
                        url: '<%=cpath%>/programme/findScheme.json',
                        dataType: 'json',
                        async: false,
                        data:{
                            businessKEY :businessKEY,
                            processDefinitionId :processDefinitionId
                        },
                        success: function(data) {
                            if(data.projectName!=null&&data.projectName!=""){
                                projectName = data.projectName;
                                projectCode = data.projectCode;
                            }
                        }
                    });

                    var checkey ="";
                    $.ajax({
                        type: 'POST',
                        url: '<%=cpath%>/programme/checkResult.json',
                        dataType: 'json',
                        async: false,
                        data: {
                            projectCode: projectCode
                        },
                        success: function (data) {
                            checkey = data.checkKey;
                        }
                    });

                    if(checkey=="3"){
                        alert("有在途补充材料流程，请勿重复发起！");
                        return;
                    }

                    if(!confirm("该操作发起补充材料流程，用于项目经理对相关材料进行完善，是否确认发起该流程？")){
                        return;
                    }else{
                        //发起补充材料流程
                        $.ajax({
                            url:'<%=cpath%>/flow/start/supplement/'+businessKEY +'.json',
                            data:{
                                businessurl:businessurl,
                                P_businessname: projectName,
                                P_businesscode: projectCode,
                                P_name: projectName
                            },
                            dataType:'json',
                            type:'post',
                            success:function(data){
                                openWin({
                                    url:"<%=cpath%>/flow/approve/"+data.taskId
                                    , width: screen.width
                                    , height: screen.height-100
                                });
                                window.location.reload();
                            },
                            error:function () {
                                alert("启动流程失败！");
                                window.location.reload();
                            }
                        });
                    }
                });

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
                if (formID != null && formID != "") {
                    formID=formID.replace(/&amp;/g,"&");//核保功能需要在formID中给页面传递参数
                    $("#contentFrm").attr("src", "<%=cpath%>" + formID + businessKEY + "&status=1&processid=${result.processid}");
                } else {
                    $("#contentFrm").attr("src", "<%=cpath%>" + businessURL + "&status=1&processid=${result.processid}");
                }
                $("#contentFrm").load(function(){
                    $("#contentFrm").get(0).contentWindow.initBytask(taskDefinitionKey);
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

            function dotask(dodata){
                $.ajax({
                    type: 'POST',
                    url: '<%=cpath%>/flow/dotask/' + taskid + ".json",
                    //dataType: 'json',
                    async: false,
                    cache: false,
                    data: dodata,
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        if(data.result=="success"){
                            alert("发送成功！");
                            window.close();
                            //刷新父窗口，关闭页面
                            window.opener.location.reload();
                            window.close();

                        }else{
                            alert("操作失败，请稍后重试！");
                            //刷新父窗口，关闭页面
                            window.opener.location.reload();
                        }
                    },
                    error: function (user) {
                        alert("发送失败！");
                    }
                });

            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--工具条-->
                <div class="btn-toolbar">
                    <c:forEach var="buttons" items="${result.docConfig.buttonValues}" varStatus="status">
                        <a href="#" role="button" data-toggle="modal" class="btn" id='<c:out value="${buttons.varName}"/>' varvalue='<c:out value="${buttons.varValue}"/>'>
                            <i class="icon-plus"></i><c:out value="${buttons.btnName}"/>
                        </a>
                    </c:forEach>
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
                                <iframe id="contentFrm" style="width:100%" frameBorder=0  src=""></iframe>

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
                <map name="planetmap" id="planetmap"></map>
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

        <div class="modal diagramModal fade hide" id="approveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="approveLabel"></h3>
            </div>
            <div class="diagramModalBody">
                <div class="form-horizontal">
                    <div class="control-group">
                        <%--<label class="control-label approveformLabelw120px" for='sendto'>发送至：</label>--%>
                        <div class="controls approveformCtrlw120px" >
                             <div id="recipients">
                                 <c:if test="${!empty result.docConfig.recipients}">
                                     <c:forEach var="item" items="${result.docConfig.recipients}" varStatus="status">
                                         ${item.name}
                                          <iframe id="iframe_${item.id}" src='<%=cpath%>/flow/selectuser?role=${item.recipientGrop}'
                                                frameborder="no" scrolling="no" width="99%" height="90px"></iframe>
                                     </c:forEach>
                                 </c:if>
                             </div>

                            <%@include file="selectuserinner.jsp" %>

                            <span id="rejectusername">
                                <label class="control-label approveformLabelw120px" for='sendto'>驳回至：</label>
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
                                    <textarea formelement="true" id='form_<c:out value="${formitem.varName}"/>' style="width:90%;height: 100px" rows="3"    name='form_<c:out value="${formitem.varName}"/>'></textarea>
                                    <br/>
                                    意见附件（可以为空）： <input id="fileupload" type="file" name="file">
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
