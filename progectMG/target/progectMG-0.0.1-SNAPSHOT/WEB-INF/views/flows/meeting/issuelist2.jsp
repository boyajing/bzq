
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String cpath = request.getContextPath();
    int index = 1;
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <link rel="shortcut icon" href="<%=cpath%>/images/icons/ico.ico" type="image/x-icon" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=cpath%>/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=cpath%>/css/theme.css">
    <script src="<%=cpath%>/js/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="<%=cpath%>/js/openr.js"  type="text/javascript"></script>
    <script language="javascript">
        $(document).ready(function () {
//上会
            $("#shanghui_btn").click(function (){
                /*var checkednum = $("input:checked");
                var taskIDs=[];
                var i=0;
                checkednum.each(function () {
                    var taskID = $(this).attr("id");
                    var processDefinitionId = $(this).attr("processDefinitionId");
                    var type = processDefinitionId.split(":");
                    //alert(type[0]);
                    if( type[0]=="planmeetingflow") {
                        taskIDs.push(taskID);
                    }
                });
                if(taskIDs.length!=0){
                    $.ajax({
                        url: '<%=cpath%>/meetingflow/panduanpinggu',
                        traditional:true,
                        data: {taskIDs: taskIDs},
                        dataType: 'json',
                        async:false,
                        type: 'get',
                        success: function (data) {
                            if(data!=""){
                                /!*if(confirm("方案:"+data+"评估没有审批或正在审批中，确定要继续发起方案上会？")){*!/
                                    openWin({
                                        url:"<%=cpath%>" + "/meetingflow/daishanghui"
                                        , width: screen.width
                                        , height: screen.height-100
                                    });
                               /!* }*!/
                            }else{
                                openWin({
                                    url:"<%=cpath%>" + "/meetingflow/daishanghui"
                                    , width: screen.width
                                    , height: screen.height-100
                                });
                            }

                        }
                    });
                }else{*/
                    openWin({
                        url:"<%=cpath%>" + "/meetingflow/daishanghui"
                        , width: screen.width
                        , height: screen.height-100
                    });
               // window.location.reload();
               /* }*/

            });
        });
        //取消选中 给父窗口调用
        function taskcancle(taskid){
            //alert(taskid);
            $('#'+taskid).attr("checked",false);
        }
        function yiti(meeting_issue_tbody) {
            var checkednum = $("input:checked");
            var taskID;
                var i = 0;
                meeting_issue_tbody.empty();
                checkednum.each(function () {
                    //先清空tbody 内元素，再通过jquery 添加
                    taskID = $(this).attr("id");
                    var url = $(this).attr("url");
                    var businessname = $(this).attr("businessname");
                    var processName = $(this).attr("processName");
                    var row = "<tr id='" + taskID + "'><td>" + (i += 1) + "</td><td><input type='hidden' name='issueTaskid' value='" + taskID + "' />"
                            + "<input type=\"hidden\" name=\"issueName_" + taskID + "\" value='" + businessname + "_" + processName + "' />"
                            + "<a href='javascript:void(0);' onclick='openbusiness(\"" + taskID + "\");' >" + businessname + "_" + processName + "</td><td></td><td><button type=\"button\" onclick=\"deleteRow(this," + taskID + ")\">删除</button></td></tr>";
                    meeting_issue_tbody.append(row);

                });
        }
    </script>
</head>
<body  >
<div class="container-fluid">
    <div class="row-fluid">
        <div class="btn-toolbar">
            <%--<a  id="confirm_btn" role="button" data-toggle="modal" class="btn">确定</a>--%>
          <%--  <a  id="cancle_btn" role="button" data-toggle="modal" class="btn">取消</a>--%>
            <a  id="shanghui_btn" role="button" data-toggle="modal" class="btn">上会</a>

        </div>
        <table id="resultTable" class="well table resultTable  table-striped table-hover">
            <thead>
            <tr>
                <th style="width:20px"></th>
                <th style="width:50px"><span>序号</span></th>
                <th inorder="true" insel="true" >
                    <span class="tableheadOrder">流程名</span>
                </th>
                <th inorder="true" insel="true">
                    <span class="tableheadOrder">业务名</span>
                </th>
                <%--<th inorder="true" insel="true" column="org_name">--%>
                <%--<span class="tableheadOrder">机构全称</span>--%>
                <%--</th>--%>
                <%--<th inorder="true" insel="true" column="foundday">--%>
                <%--<span class="tableheadOrder">成立日期</span>--%>
                <%--</th>--%>
                <%--<th inorder="true" insel="true" column="org_Contact">--%>
                <%--<span class="tableheadOrder">联系人</span>--%>
                <%--</th>--%>
                <%--<th inorder="true" insel="true" column="org_Tel">--%>
                <%--<span class="tableheadOrder">联系人电话</span>--%>
                <%--</th>--%>
                <%--<th inorder="true" insel="true" column="org_Enabled">--%>
                <%--<span class="tableheadOrder">状态</span>--%>
                <%--</th>--%>
                <%--<th inorder="true" insel="true" column="province">--%>
                <%--<span class="tableheadOrder">省份</span>--%>
                <%--</th>--%>
                <%--<th inorder="true" insel="true" column="city">--%>
                <%--<span class="tableheadOrder">市县</span>--%>
                <%--</th>--%>
                <%--<th inorder="true" insel="true" column="org_Address">--%>
                <%--<span class="tableheadOrder">详细地址</span>--%>
                <%--</th>--%>
                <%--<th inorder="true" insel="true" column="post">--%>
                <%--<span class="tableheadOrder">邮编</span>--%>
                <%--</th>--%>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${tasks}" varStatus="status">
                <tr style="cursor:pointer" onclick="$('#<c:out value="${task.taskID}"/>').attr('checked', $('#<c:out value="${task.taskID}"/>').attr('checked') ? false : true)">
                    <td >
                        <input id="<c:out value="${task.taskID}"/>" value="<c:out value="${task.taskID}"/>" type="checkbox" url="${task.businessURL}" businessname="${task.businessname}" processName="${task.processName}"
                               processDefinitionId="${task.processDefinitionId}" businessKey="${task.businessKey}">
                    </td>
                    <td style="width:50px">
                            <%--<c:out value="${(pageindex-1)*pagesize+1+status.index}"/>--%>
                        <c:out value="${status.index +1}"/>
                    </td>
                    <td column="orgId"><c:out value="${task.processName}"/></td>
                    <td column="orgName"><c:out value="${task.businessname}"/></td>
                        <%--<td column="orglname"><c:out value="${task.orglname}"/></td>--%>
                        <%--<td column="foundday"><fmt:formatDate value="${org.foundday}" pattern="yyyy-MM-dd"/></td>--%>
                        <%--<td column="orgContact"><c:out value="${org.orgContact}"/></td>--%>
                        <%--<td column="orgTel"><c:out value="${org.orgTel}"/></td>--%>
                        <%--<td column="orgEnabled">--%>
                        <%--<c:if test="${org.orgEnabled==0}">--%>
                        <%--禁用--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${org.orgEnabled==1}">--%>
                        <%--启用--%>
                        <%--</c:if>--%>
                        <%--</td>--%>
                        <%--<td column="province"><c:out value="${org.province}"/></td>--%>
                        <%--<td column="city"><c:out value="${org.city}"/></td>--%>
                        <%--<td column="orgAddress"><c:out value="${org.orgAddress}"/></td>--%>
                        <%--<td column="post"><c:out value="${org.post}"/></td>--%>

                </tr>
                <% index++;%>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
