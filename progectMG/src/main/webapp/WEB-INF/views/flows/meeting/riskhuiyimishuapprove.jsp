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

    function viewinfo() {
        var url = "<%=request.getContextPath()%>/meetingflow/queryvote?meetingId=${meetingId}";
        window.open(url, "frame", "frame");
    }
    function closeit() {
        window.parent.close();
    }
    $(function () {
        $("table").attr("class", "well table resultTable table-hover");
        $("textarea,select").attr('class', 'input-block-level');
        if('${key}'=="2"){
            $("tr[id='dongshizhang']").attr("style","display:none");
        }
    });

    function onloadc(issueBusinssKey,key) {
        if(isdone2==true){
            openWin({
                url:"<%=path%>/fileManager/index_view?businessId="+issueBusinssKey+"&projectCode="+key+"&businessType=file_7"
                , width: screen.width
                , height: screen.height-100
            });
        }else{
            openWin({
                url:"<%=path%>/fileManager/index?businessId="+issueBusinssKey+"&projectCode="+key+"&businessType=file_7"
                , width: screen.width
                , height: screen.height-100
            });
        }
    }

    //正式批复
    function zhengshionloadc(issueBusinssKey,key) {
        if(isdone2==true){
            openWin({
                url:"<%=path%>/fileManager/index_view?businessId="+issueBusinssKey+"&projectCode="+key+"&businessType=file_7"
                , width: screen.width
                , height: screen.height-100
            });
        }else{
            openWin({
                url:"<%=path%>/fileManager/index?businessId="+issueBusinssKey+"&projectCode="+key+"&businessType=file_7"
                <%--url:"<%=path%>/fileManager/index?issueId="+issueId+"&type=4&meetingId=${meetingId}&customerId="+workflowId+""--%>
                , width: screen.width
                , height: screen.height-100
            });
        }
    }
    function save() {
        if(confirm("确定保存？")){
            $("#queryForm").attr("action", "<%=path%>/meetingflow/savehuiyiweiyuanApprove?meetingId=${meetingId}").submit();
        }
    }
    //处理打开查看议题详细信息的方法
    function openbusiness(issueBusinssKey,issueId){
        if(isdone2==true){
            openWin({
                url:"<%=path%>/meetingflow/zhenshipifu?issueId="+issueId+"&issueBusinssKey="+issueBusinssKey+"&isdone2=1"+""
                , width: screen.width
                , height: screen.height-100
            });
        }else{
            openWin({
                url:"<%=path%>/meetingflow/zhenshipifu?issueId="+issueId+"&issueBusinssKey="+issueBusinssKey+"&isdone2=2"+""
                , width: screen.width
                , height: screen.height-100
            });
        }
    }

    function checkSend(taskDefinitionKey){
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
    function initBytask(taskDefinitionKey,isdone){
        //已办
        isdone2=isdone;
        if(isdone==true){
            $("#save").hide();
            $("input,textarea,select").attr("disabled", "disabled");
        }else{//代办

        }
    }
</script>
<body>
<div class="container-fluid">

    <div style="height:10px"></div>
    <div class="row-fluid" style="text-align: right">
        <button type="button" id="save" onClick="save();">保存</button>
    </div>
    <form id="queryForm" method="post">
        <input type="hidden" id="meetingId" name="meetingId" value="${meetingId}"/>
        <div style="text-align: center"><th>会议秘书出正式批复</th></div>
        <div style="width: 100%">
            <c:forEach var="item" items="${yitilist}" varStatus="status">
                <div><th>议题${status.index+1}</th>  </div>
                <table id="votetable">
                    <thead>
                    <th style="width: 20%">议题名称</th>
                    <th>最终结果</th>
                    <th>批复文号</th>
                    <th>批复时间</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <input type="hidden" name="issueId" value="${item.issueId}" />
                            <a id="openbusiness" href='javascript:void(0);' onclick='openbusiness("${item.issueBusinssKey}","${item.issueId}");' >${item.issueName}</a>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${item.issueResult==2}">
                                    <input type="radio" name="issueResult_${item.issueId}" value="1"/>同意
                                    <input type="radio" name="issueResult_${item.issueId}" value="2" checked/>否决
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="issueResult_${item.issueId}" value="1" checked/>同意
                                    <input type="radio" name="issueResult_${item.issueId}" value="2"/>否决
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <input type="text"  name="documentNumber_${item.issueId}"   value="${item.documentNumber}"/>
                        </td>
                        <td>
                            <input type="text"  name="replyTime_${item.issueId}" id="${item.issueId}_replyTimedate"  value="<fmt:formatDate value="${item.replyTime}" pattern="yyyy-MM-dd"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>风险管理与内部控制委员会:</th>
                        <td colspan="2">
                        <textarea  type="text" name="partyCommitteeOpinion_${item.issueId}"  cols ="300" <%--rows = "3"--%>
                                   style="word-wrap:break-word;resize: none;font-size: 16px;" >${item.partyCommitteeOpinion}</textarea>
                        </td>
                        <td>
                            <a id="onloadc2" onclick="onloadc('${item.issueBusinssKey}','2');">附件</a>
                        </td>
                    </tr>
                    <tr>
                        <th>董事会意见:</th>
                        <td colspan="2">
                        <textarea  type="text" name="boardMeetingOpinion_${item.issueId}"  cols ="300" <%--rows = "3"--%>
                                   style="word-wrap:break-word;resize: none;font-size: 16px;" >${item.boardMeetingOpinion}</textarea>
                        </td>
                        <td>
                            <a id="onloadc" onclick="onloadc('${item.issueBusinssKey}','3');">附件</a>
                        </td>
                    </tr>
                    <tr>
                        <th>正式批复:</th>
                        <td colspan="3">
                            <a id="onloadc4" onclick="zhengshionloadc('${item.issueBusinssKey}','4');">正式批复</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:forEach>
        </div>
    </form>
    <div>
        <table>
            <div class="container-fluid">
                <c:forEach var="item" items="${biaojuelist}" varStatus="status">
                    <th style="width: 20%">议题名称：</th>
                    <th colspan="2">${item.issueName}</th>
                    <tr>
                        <td width="20%">主任委员意见</td>
                        <td>
                            <textarea  type="text" cols ="300" rows="10" onscroll= "this.rows=this.rows+1" readonly
                                       style="word-wrap:break-word;width: 100%;resize: none;font-size: 16px;">${item.committeeOpinion}</textarea>
                            </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%">总经理意见</td>
                        <td>
                            <textarea  type="text" cols ="300" rows="10" onscroll= "this.rows=this.rows+1" readonly
                                       style="word-wrap:break-word;width: 100%;resize: none;font-size: 16px;">${item.zongjingliOpinion}</textarea>
                            </textarea>
                        </td>
                    </tr>
                    <tr id="dongshizhang">
                        <td width="20%">董事长意见</td>
                        <td>
                            <textarea  type="text" cols ="300" rows="10" onscroll= "this.rows=this.rows+1" readonly
                                       style="word-wrap:break-word;width: 100%;resize: none;font-size: 16px;">${item.boardOpinion}</textarea>
                            </textarea>
                        </td>
                    </tr>
                </c:forEach>
            </div>
        </table>

        <div>
            <table class="table table-bordered table-condensed table-striped table-hover">
                <iframe id="iframe_relate" frameBorder=0 scrolling=auto style="width:100%"  height="100%" src="<%=path%>/fileManager/index?businessId=${meetingId}&projectCode=1&businessType=file_8" allowtransparency="true" style="background-color:transparent" ></iframe>
            </table>

        </div>
        <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
    </div>
</div>
</body>
</html>
