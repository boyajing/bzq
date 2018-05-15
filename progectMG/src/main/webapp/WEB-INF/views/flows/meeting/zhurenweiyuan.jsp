<%--
  Created by IntelliJ IDEA.
  User: xiongyiwu
  Date: 2016/6/12
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
    int num = 1;
%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
 <%--<script type="text/javascript" src="<%=path%>/js/date/jquery-1.10.1.js"></script>--%>
<script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
<script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
<script type="text/javascript" src="<%=path%>/resources/_ScriptLibrary/WindowTracking.js"></script>
<script src="<%=path%>/js/openr.js"  type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/flow/sendvalidate.js"></script>
<head>
    <title>会议秘书汇总表决结果</title>

</head>
<script type="text/javascript">
    var isdone2;
    var flowid;
    var taskId;
    function viewinfo() {
        var url = "<%=request.getContextPath()%>/meetingflow/queryvote?meetingId=${meetingId}";
        window.open(url, "frame", "frame");
    }
    function send() {
        var votes = $('#votetable').find('tr');
        var flag = false;
        if (confirm("您确认结束表决？此操作将结束其他未表决人员及列席人员的流程!")) {
            for(var i=1;i<votes.length;i++){
                if($.trim(votes.eq(i).find('td').eq(2).text())==''){
                    flag=true;
                    break;
                }
            }
            if(flag){
                alert("您还有方案未形成决议，请选择方案填写最终决议");
            }else{
                var url = "<%=request.getContextPath()%>/workflow/web/action/selectUser/selectUser?workflowActionName=send&taskId=${taskId}&clyjSel=2&taskTypeId=${taskTypeId}&workflowId=${wfid}";
                launchWin("450", "350", url, "yes", window, "no");
            }
        }
    }
    function closeit() {
        window.parent.close();
    }
    $(function () {
        $("table").attr("class", "well table resultTable table-hover");
    })
    function openVote(meetingId,issueId){
        window.open("<%=path%>/meeting/showmishuVote?meetingId="+meetingId+"&issueId="+issueId+"", "frame", "toolbar=no,menubar=no,scrollbars=yes");
    }
    function setVote(issueId,option,desc){
        <c:forEach var="bj" items="${bjyj}">
        var opts =${bj.codeValue}+'';
        if(opts==option){
            $('#option_'+issueId).html("${bj.codeName}");
        }
        </c:forEach>
        $('#desc_'+issueId).html(desc);
    }
    function openFaxx(issueBusinssKey){
        $.ajax({
            url: "<%=path%>/meeting/queryWorkflowType",
            type: "post",
            async: false,
            data: {issueBusinssKey: issueBusinssKey},
            success: function (e) {
                if(e!='error'){
                    var workflowType = e.substr(0,4);
                    var url ='<%=path%>/workflow/action/framework/frame?workflowType='+workflowType+'&workflowId='+wfid+'&taskId=&actionCode=loadForm&taskTypeId='+e;
                    launchWin(screen.width, screen.height, url, "yes", window, "yes");
                }
            }
        });

    }

    function onloadc(issueId) {
        openWin({
            url: "<%=path%>/fileManager/index_view?businessId=" + issueId + "&businessType=file_7"
            , width: screen.width
            , height: screen.height-100
        });
        <%--if(isdone2==true){--%>
            <%--openWin({--%>
                <%--url:"<%=path%>/fileManager/index_view?businessId="+issueBusinssKey+"&projectCode="+key+"&businessType=file_7"--%>
                <%--, width: screen.width--%>
                <%--, height: screen.height-100--%>
            <%--});--%>
        <%--}else {--%>
            <%--openWin({--%>
                <%--url: "<%=path%>/fileManager/index?businessId=" + issueId + "&businessType=file_7"--%>
                <%--, width: screen.width--%>
                <%--, height: screen.height - 100--%>
            <%--});--%>
        <%--}--%>
    }

    function save() {
        if(checkFlag()=="2"){
            return;
        }
        if(confirm("确定是否保存？")){
            $("#meetingVote").attr("action", "<%=path%>/meetingflow/savezhurenweiyuanapprove").submit();
        }

    }
    //处理打开查看议题详细信息的方法
    function openbusiness(taskid){
        openWin({
            url:"<%=path%>/meetingflow/querytask/"+taskid
            , width: screen.width
            , height: screen.height-100
        });
    }

    function checkFlag() {
        var flag = "1";
        $("#meetingVote textarea").each(function(){
            var len = getLenth($(this).val());
            if(len>999){
                alert("意见不能超过500汉字!");
                flag = "2";
            }
        });
        return flag;
    }

    function getLenth(str) {
        var len = 0;
        for (var i=0;i < str.length ;i++){
            if(str.charCodeAt(i)>127 || str.charCodeAt(i)==94){
                len +=2;
            }else{
                len +=1;
            }
        }
        return len;
    }

    function checkSend(taskDefinitionKey){
        if(checkFlag()=="2"){
            return;
        }

        var data = $("#meetingVote").serialize();
        $.ajax({
            type: 'POST',
            url: '<%=path%>/meetingflow/savezhurenweiyuanapprove.json',
            dataType: 'json',
            async: false,
            data : data,
            success: function (data) {
            },
            error: function (user) {
            }
        });

        var result = 1;
        $.ajax({
            type: 'GET',
            url: '<%=path%>/meetingflow/sendvalite',
            dataType: 'json',
            async: false,
            data:{
                taskDefinitionKey : taskDefinitionKey,
                meetingId : '${meetingId}',
                date:new Date()
            },
            success: function (data) {
                if(data!=""){
                    alert(data);
                    result = 2;
                }
            },
            error: function (user) {

            }
        });
        return result;
    }

    function reback() {
        //ajax调用后台逻辑
        $.ajax({
            type: 'POST',
            url: '<%=path%>/flow/takeBack/'+flowid+'/'+taskId+'/zhurenweiyuan/zongjingli.json',
            dataType: 'json',
            async: false,
            data :{},
            success: function (data) {
                if(data.result=="2"){
                    alert("当前流程已经结束不能撤回！");
                    return;
                }
                if(data.result=="3"){
                    alert("当前流程已经不在要撤回的节点，不能撤回！");
                    return;
                }
                if(data.result=="1"){
                    alert("撤回成功！");
                    window.parent.opener.location.reload();
                    window.parent.close();

                }
            },
            error: function (user) {
            }

        });
    }

    function initBytask(taskDefinitionKey,isdone,processid,taskid){
        //已办
        isdone2=isdone;
        flowid = processid;
        taskId = taskid;
        if(isdone==true){
            $("#save").hide();
            $("input,textarea,select").attr("disabled", "disabled");
            $("#reback").attr("style","display:''");
        }else{//代办

        }
    }
</script>
<body>
<div class="container-fluid">

    <div style="height:10px"></div>
    <div class="row-fluid" style="text-align: right">
        <button type="button" id="save" onClick="save()">保存</button>
        <button type="button" id="reback" onClick="reback()" style="display: none">撤回</button>
    </div>
    <div style="height:10px"></div>
    <b>议题汇总意见</b>
    <div style="height:10px"></div>
    <div class="row-fluid">

            <input type="hidden" id="meetingId" name="meetingId" value="${meetingId}"/>
            <table id="" class="well table resultTable table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>议题名称</th>
                    <th>表决人员</th>
                    <th>表决结果</th>
                    <th>意见阐述</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${yitilist}" var="item" varStatus="status">
                    <c:forEach items="${item.tMeetingVoteslist}" var="item1" varStatus="status1">
                    <tr>
                        <c:if test="${status1.first}">
                        <td rowspan="${item.count}"><%=num++%></td>
                        <td rowspan="${item.count}">${item.issueName}</td>
                        </c:if>

                        <td nowrap="nowrap"><nt:username userid="${item1.userId}"></nt:username></td>
                            <td>
                                <c:choose>
                                    <c:when test="${item1.voteResult==1}">
                                        同意
                                    </c:when>
                                    <c:when test="${item1.voteResult==2}">
                                        再议
                                    </c:when>
                                    <c:when test="${item1.voteResult==3}">
                                        否决
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                    ${item1.voteOpinion}
                            </td>


                    </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>

    </div>
    <div style="height:10px"></div>
    <form id="meetingVote"  method="post">
    <b>议题表决意见</b>
    <div style="height:10px"></div>
    <div>
        <table id="votetable">
            <thead>
            <th style="width:10%;">序号</th>
            <th style="width:10%;">议题名称</th>
            <th style="width:20%;">业务审批委员会意见</th>
            <th style="width:50%;">主任委员意见</th>
            <th style="width:10%;">批复草稿</th>

            <input type="hidden"  name="currentid" value="${currentid}"/>
            <input type="hidden"  name="meetingId" value="${meetingId}"/>

            </thead>
            <tbody>
            <c:forEach var="item" items="${biaojuelist}" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>
                        <input type="hidden" name="issueId" value="${item.issueId}" />
                        <a href='javascript:void(0);' onclick='openbusiness("${item.issueId}");' >${item.issueName}</a>
                    </td>
                    <td style="text-align: left">应参会委员${item.peopleNum}人，实参会委员${item.peopleNum-item.weibiaojuenum}人。
                        同意${item.tongyinum}人，再议${item.xvyinum}人，否决${item.foujuenum}人。
                        表决结果为：${item.result}。
                    </td>
                    <td>
                        <textarea  type="text" name="committeeOpinion_${item.issueId}"  cols ="300" rows="10" maxlength="999" onscroll= "this.rows=this.rows+1"
                                 style="word-wrap:break-word;width: 90%;resize: none;font-size: 16px;" >${item.committeeOpinion}</textarea>
                    </td>
                    <td>
                        <a onclick="onloadc('${item.issueId}');">附件</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    </form>
    <div>
        <%--<jsp:include page="../../filesmanage/fujian.jsp"></jsp:include>
        <jsp:include page="../../util/history/history.jsp"></jsp:include>--%>
        <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
    </div>
</div>
</body>
</html>
