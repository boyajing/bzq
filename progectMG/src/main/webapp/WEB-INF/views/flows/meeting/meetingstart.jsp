<%--<%@ page import="com.nantian.workflow.common.define.process.UserRoleDefine" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: zhaojunhia
  Date: 2017/4/17
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
<script type="text/javascript" src="<%=path%>/js/WindowTracking.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">

<script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
<script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
<%--显示时分--%>
<script type="text/javascript" src="<%=path%>/js/timepicker/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=path%>/js/timepicker/jquery.ui.datepicker-zh-CN.js.js"></script>
<script type="text/javascript" src="<%=path%>/js/timepicker/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/flow/sendvalidate.js"></script>
<script src="<%=path%>/js/openr.js"  type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/css-loader.css">
<script type="text/javascript">



    function defalut_taskID(){
        return '${userTask.taskID}';
    }
    function getNames(){
        var meetingExaminer_usernames= document.getElementById("voteuseriframe").contentWindow.getSelectedUserNames();
        var names =  meetingExaminer_usernames.split("),");
        return names;
    }
    function getIds() {
        var meetingExaminer_ids = document.getElementById("voteuseriframe").contentWindow.getSelectedUserIds();
        var ids = meetingExaminer_ids.split(",");
        return ids;
    }

    $(function(){
        $("table").attr("class", "well table resultTable table-hover");
        //显示时分秒
        jQuery('input[id$=TIME]').datetimepicker({
            timeFormat: "HH:mm:ss",
            dateFormat: "yy-mm-dd"
        });


        $("#meetingExaminer_select").click(function(){
            $('#selectuser_modal').modal('show').css({
                width: '40%',
                'margin-left': function () {
                    return -($(this).width() / 2);
                }
            });

        });
        $("#meetingAttendee_select").click(function(){
            $('#selectuser_modal_attend').modal('show').css({
                width: '40%',
                hight:'60%',
                'margin-left': function () {
                    return -($(this).width() / 2);
                }
            });

        });


        $("#approveActionBtn").click(function(){

            var meetingExaminer_ids = document.getElementById("voteuseriframe").contentWindow.getSelectedUserIds();
            var meetingExaminer_usernames= document.getElementById("voteuseriframe").contentWindow.getSelectedUserNames();;
            $("#meetingExaminer_CN").val(meetingExaminer_usernames);
            $("#meetingExaminer_EN").val(meetingExaminer_ids);
            var names =  meetingExaminer_usernames.split("),");
            var ids = meetingExaminer_ids.split(",");

            //议题后面添加参与人员
            var tab=document.getElementById("itemTable");
            var tabRows = tab.rows.length;
            for(var i = 1;i<tabRows;i++){
                var taskID = $(tab.rows[i]).attr("id");
                var cbox ="";
                for(var k=0;k<names.length-1;k++) {
                    cbox += "<input type='checkbox' name='n_"+taskID+"' rec='true'  value='"+ids[k]+"' checked='checked'/>"+names[k]+")";
                }
                tab.rows[i].cells[2].innerHTML = cbox;
            }


        });
        $("#approveActionBtn1").click(function(){
            var meetingAttendee_ids = document.getElementById("attenduseriframe").contentWindow.getSelectedUserIds();
            var meetingAttendee_usernames= document.getElementById("attenduseriframe").contentWindow.getSelectedUserNames();;

            $("#meetingAttendee_CN").val(meetingAttendee_usernames);
            $("#meetingAttendee_EN").val(meetingAttendee_ids);

        });

        //打开增加议题列表
        $("#addItem").click(function(){
            $('#meeting_issue_modal').modal('show').css({
                width: '80%',
                hight:'60%',
                'margin-left': function () {
                    return -($(this).width() / 2);
                }
            });

        });
        //  发送按钮
        $("#send_btn").click(function(){
            $("#loading").attr("style","display:block");
            //控制所选时间不小于当前时间
            var date = new Date();
            var selectDate = $("#meetingTIME").val();
            selectDate = selectDate.replace(/-/g,"/");
            var date1 = new Date(selectDate );
            if(date>date1){
                alert("会议开始时间不能小于当前时间！")
                $("#loading").attr("style","display:none");
                return;
            }

            var meetingExaminer_ids = document.getElementById("voteuseriframe").contentWindow.getSelectedUserIds();
            var ids = meetingExaminer_ids.split(",");
            var data = $("#meetingForm").serialize();

            //判断所选议题的参会人员是否大于6人
            var tab=document.getElementById("itemTable");
            var tabRows = tab.rows.length;
            if(5>(ids.length-1)){
                alert("所选委员人数必须大于或等于五人");
                $("#loading").attr("style","display:none");
            }else{
                for(var i = 1;i<tabRows;i++) {
                    var taskID = $(tab.rows[i]).attr("id");
                    //var issueName_="issueName_"+taskID;
                    var issueName=$("input[name=issueName_"+taskID+"]").val();
                    //alert("taskID:"+taskID+"issueName:"+issueName);
                    var arr=[];
                    $("input[name='n_"+taskID+"']:checked").each(function () {
                        var id = $(this).val();
                        arr.push(id);
                    });
                    //alert("arr:"+arr);
                    if(arr.length %2==1) {
                        if (arr.length > 7) {
                            if (!confirm("议题：" + issueName + "的参会人数大于七人，是否继续发送！")) {
                                $("#loading").attr("style","display:none");
                                return;
                            }

                        } else if (arr.length < 5) {
                            alert("议题：" + issueName + "的参会人数不能小于五，请确认！");
                            $("#loading").attr("style","display:none");
                            return;
                        }
                    }
                }
                    $.ajax({
                        url:'<%=path%>/meetingflow/start/abcde.json',
                        data:data,
                        async:true,
                        type:'post',
                        dataType:'json',
                        success:function(data){
                            if(data.sucess == "sucess"){
                                window.location.href ="<%=path%>/flow/approve/"+data.taskId;
                            }else {
                                alert("启动失败");
                                $("#loading").attr("style","display:none");
                            }

                        },
                        error:function(data){
                            alert("启动失败" + data);
                            $("#loading").attr("style","display:none");
                        }
                    })
            }
        });


    })
    //处理打开查看议题详细信息的方法
    function openbusiness(taskid){
        openWin({
            url:"<%=path%>/meetingflow/querytask/"+taskid
            , width: screen.width
            , height: screen.height-100
        });
    }
    //初始化行,设置序列号;
    function initRows(tab){
        var tabRows = tab.rows.length;
        for(var i = 1;i<tabRows;i++){
            tab.rows[i].cells[0].innerHTML = i;
        }
    }

    //删除行;(obj代表连接对象)
    function deleteRow(obj,taskid){
        var tab = document.getElementById("itemTable");
        //获取tr对象;
        var tr = obj.parentNode.parentNode;

        if(tab.rows.length>2){
            //tr.parentNode，指的是，table对象;移除子节点;
            tr.parentNode.removeChild(tr);
            //列表中该数据取消选中
            document.getElementById("meeting_issue_iframe").contentWindow.taskcancle(taskid);
            //重新生成行号;
            initRows(document.getElementById("itemTable"));
        }else {
            alert("至少需要保留一个议题");
        }
    }
    function issuelist_hide(){
        $('#meeting_issue_modal').modal('hide');
    }

</script>
<head>
    <title>会议信息</title>
</head>
<body>
<!-- Loader 防止多次点击发起多个代办-->
<div id="loading" class="loader loader-default is-active" data-text style="display:none"></div>
<div class="container-fluid">
    <div style="height:10px"></div>
    <div class="row-fluid">
            <button type="button" id="send_btn">发送</button>
    </div>
    <div style="height:10px"></div>

    <div class="row-fluid">
        <form id="meetingForm" action="<%=path%>/meeting/start" method="post">
            <input type="hidden"  id="meetingId" name="meetingId" value="${meetingBase.meetingId}"/>
            <b>会议信息</b>
            <table class="well table resultTable table-hover">
                <tr>
                    <td>会议时间</td>
                    <td><input type="text" id="meetingTIME" name="meetingTime" value="<fmt:formatDate value='${meetingBase.meetingTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/></td>
                    <td>会议地点</td>
                    <td><input type="text" id="meetingAdress" name="meetingAdress" value="${meetingBase.meetingAdress}"/></td>
                </tr>
                <tr>

                    <td>会议类型</td>
                    <td> <select id="meetingType" name="meetingType" >
                            <option value="">请选择</option>
                            <nt:code index="${meetingBase.meetingType}" ctype="044"></nt:code>
                        </select>
                    </td>

                    <td>会议次数</td>
                    <td><input type="text" name="meetingNumber" onblur="{if(!isinteger($(this).val())){alert('格式不正确');$(this).val('');}}" value="${meetingBase.meetingNumber}"/></td>

                </tr>
                <tr>
                    <td>会议委员</td>
                    <td colspan="3">
                        <textarea maxlength="600" style="width: 89%;height: 95%;resize: none"
                                  id="meetingExaminer_CN" value="${meetingExaminer_CN}" readonly> </textarea>
                        <input type="hidden" id="meetingExaminer_EN" name="meetingCommittee" value="${meetingBase.meetingCommittee}"/>
                        <button type="button" id="meetingExaminer_select">选人</button>
                    </td>

                </tr>
                <tr>
                    <td>列席人员</td>
                    <td colspan="3">
                        <textarea  maxlength="600" style="width: 89%;height: 95%;resize: none"
                                   id="meetingAttendee_CN" value="${meetingAttendee_CN}" readonly> </textarea>
                        <input type="hidden" id="meetingAttendee_EN" name="meetingObserver" value="${meetingBase.meetingObserver}"/>
                        <button type="button" id="meetingAttendee_select">选人</button>
                    </td>
                </tr>

            </table>
            <b>议题信息</b>
            <div style="text-align: right"><button type="button" id="addItem">增加议题</button></div>
            <table id="itemTable">
                <thead>
                    <th style="width: 10%">序号</th>
                    <th style="width: 60%">会议议题</th>
                    <th style="width: 50%">参会人员</th>
                    <th style="width: 10%">操作</th>
                </thead>
                <tbody id="meeting_issue_tbody">
                    <tr id="${userTask.taskID}">
                       <td>1</td>
                       <td>
                           <input type="hidden" name="issueTaskid" value="${userTask.taskID}" />
                           <input type="hidden" name="issueName_${userTask.taskID}" value='<c:out value="${userTask.businessname}"></c:out>_<c:out value="${userTask.processName}"></c:out>' />
                            <a href='javascript:void(0);' onclick='openbusiness("${userTask.taskID}");' > <c:out value="${userTask.businessname}"></c:out>_<c:out value="${userTask.processName}"></c:out> </a>

                       </td>

                        <td id="canyurenyuan_${userTask.taskID}">

                        </td>
                        <td>
                        <button type="button" onclick="deleteRow(this,${userTask.taskID})">删除</button></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
    <!--参会人员选人列表窗口-->
    <div class="modal diagramModal fade hide" id="selectuser_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 >参会人员选人列表</h3>
        </div>
        <div class="diagramModalBody">
            <iframe id="voteuseriframe" src='<%=path%>/meetingflow/selectuser/voteuser' width="99%" height="400px"></iframe>
        </div>
        <div class="modal-footer">
            <button id='approveActionBtn' class="btn btn-large btn-primary"  data-dismiss="modal" aria-hidden="true">确认</button>
            <button class="btn btn-large" data-dismiss="modal" aria-hidden="true">关闭</button>
        </div>
    </div>
    <!--参会人员选人列表窗口-->
    <div class="modal diagramModal fade hide" id="selectuser_modal_attend" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 >列席选人列表</h3>
        </div>
        <div class="diagramModalBody">
            <iframe id="attenduseriframe" src='<%=path%>/meetingflow/selectuser/attenduser' width="99%" height="400px"></iframe>
        </div>
        <div class="modal-footer">
            <button id='approveActionBtn1' class="btn btn-large btn-primary"  data-dismiss="modal" aria-hidden="true">确认</button>
            <button class="btn btn-large" data-dismiss="modal" aria-hidden="true">关闭</button>
        </div>
    </div>
    <!--议题列表-->
    <div class="modal diagramModal fade hide" id="meeting_issue_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 >待上会议题列表</h3>
        </div>
        <div class="diagramModalBody">
            <iframe id="meeting_issue_iframe" src='<%=path%>/meetingflow/meetingtodolist' width="99%" height="400px"></iframe>
        </div>
    </div>
    <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</div>
</body>
</html>
