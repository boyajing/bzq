
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

            $("#confirm_btn").click(function (){
                var checkednum = $("input:checked");
                if (checkednum.length < 1) {
                    alert("请选择一条数据。");
                } else {
                    var i = 0;

                    $('#meeting_issue_tbody', window.parent.document).empty();
                    checkednum.each(function(){
                        var taskID = $(this).attr("id");
//                        var businessURl = $(this).attr("businessURl");
                        var url = $(this).attr("url");
                        var businessname = $(this).attr("businessname");
                        var processName = $(this).attr("processName");
                        //获取父页面的参会人员name
                        var names = window.parent.getNames();
                        var ids = window.parent.getIds();
                        //id='"+taskID+ids[k]+"' name='"+taskID+ids[k]+"' rec='true'  value='${names[k]}' checked='checked'
                        var cbox ="";
                        for(var k=0;k<names.length-1;k++) {
                           cbox += "<input type='checkbox' name='n_"+taskID+"' rec='true'  value='"+ids[k]+"' checked='checked'/>"+names[k]+")";
                        }

                        var row = "<tr id='"+taskID+"'><td>"+(i+= 1)+"</td><td><input type='hidden' name='issueTaskid' value='"+taskID+"' />"
                                    +"<input type=\"hidden\" name=\"issueName_"+taskID+"\" value='"+businessname+"_"+processName+"' />"
                               + "<a href='javascript:void(0);' onclick='openbusiness(\""+taskID+"\");' >"+businessname+"_"+processName+"</td><td>"+cbox+"</td><td><button type=\"button\" onclick=\"deleteRow(this,"+taskID+")\">删除</button></td></tr>";
                        $('#meeting_issue_tbody', window.parent.document).append(row);
                    });
//                    $('#meeting_issue_modal', window.parent.document).modal('hide');
                    window.parent.issuelist_hide();
                }
            });
            $("#cancle_btn").click(function(){
                window.parent.issuelist_hide();
            });

            //获取父窗口中已经加载的taskid,在这里给默认选中；
            var taskid = window.parent.defalut_taskID();
            $('#'+taskid).attr("checked",true);
        });
        //取消选中 给父窗口调用
        function taskcancle(taskid){
            alert(taskid);
            $('#'+taskid).attr("checked",false);
        }
    </script>
</head>
<body  >
<div class="container-fluid">
    <div class="row-fluid">
        <div class="btn-toolbar">
            <a  id="confirm_btn" role="button" data-toggle="modal" class="btn">确定</a>
            <a  id="cancle_btn" role="button" data-toggle="modal" class="btn">取消</a>

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
            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${tasks}" varStatus="status">
                <tr style="cursor:pointer" onclick="$('#<c:out value="${task.taskID}"/>').attr('checked', $('#<c:out value="${task.taskID}"/>').attr('checked') ? false : true)">
                    <td >
                        <input id="<c:out value="${task.taskID}"/>" value="<c:out value="${task.taskID}"/>" type="checkbox" url="${task.businessURL}" businessname="${task.businessname}" processName="${task.processName}">
                    </td>
                    <td style="width:50px">
                        <c:out value="${status.index +1}"/>
                    </td>
                    <td column="orgId"><c:out value="${task.processName}"/></td>
                    <td column="orgName"><c:out value="${task.businessname}"/></td>
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
