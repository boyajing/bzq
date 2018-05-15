<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="nt" uri="/WEB-INF/nttags/nantian.tld" %>

<%
    String cpath = request.getContextPath();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/js/bootstrap/css/bootstrap.css">
        <script type="text/javascript" src="<%=cpath%>/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/queryframe.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/WindowTracking.js"></script>
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/css/mypage.css">
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/js/date/jquery.ui.css">
        <script type="text/javascript" src="<%=cpath%>/js/date/jquery-1.10.1.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/date/jquery.ui.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/date/datecontroller.js"></script>
        <script src="<%=cpath%>/js/openr.js"  type="text/javascript"></script>
        <title></title>
        <style>
            select, textarea, input[type='text'], input[type='password'], input[type='datetime'], input[type='datetime-local'], input[type='date'], input[type='month'], input[type='time'], input[type='week'], input[type='number'], input[type='email'], input[type='url'], input[type='search'], input[type='tel'], input[type='color'], .uneditable-input {
                display: inline-block;
                height: 30px;
                padding: 4px 6px;
                margin-bottom: 9px;
                font-size: 14px;
                line-height: 20px;
                color: #555555;
                -webkit-border-radius: 3px;
                -moz-border-radius: 3px;
                border-radius: 3px;
            }
            .todolistCSS{
                cursor: pointer;
            }
        </style>
        <script type="text/javascript">
            $(function () {
                $("table").attr("class", "well table resultTable table-hover");
            });
            
            <%--var doTaskURL = "<%=cpath%>";--%>
//            $(document).ready(function () {
//                $(".todolistCSS").click(function () {
//                    openWin({
//                        //url: doTaskURL+$(this).attr("targetURL")+$(this).attr("taskid")
//                        url:doTaskURL+"/flow/approve/"+$(this).attr("taskid")
//                        , width: screen.width
//                        , height: screen.height-100
//                    });
//                });
//            });
            var taskid = "";
            $(document).ready(function () {
                $("table a").click(function () {
                    taskid= $(this).attr("taskid");
                    window.open("<%=cpath%>/userBasic/index?", "选择用户", "height=800,width=1500,top=100,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=1,location=no, status=no");
                });

            });

            function setUser(userid) {
                userid= userid.split(",")[0];
//                alert(taskid+"--"+ userid);
                $.ajax({
                    url: "<%=cpath%>/flowMG/transferAssignee/"+taskid + "/"+ userid +".json",
                    type: "POST",
                    async: true,
                    data: {},
                    success: function (data) {
                        if(data.result =="sucess"){
                            alert("操作成功！");
                            window.location.reload();
                        }else{
                            alert("操作失败！");
                        }
                    },
                    error:function(data){
                        console.info(data);
                        alert("操作失败！");
                    }
                });
            }
            //查询
            function query() {
                $("#queryForm").attr("action", "<%=cpath%>/flow/index").submit();
            }
        </script>
    </head>
    <body>
    <div class="container-fluid">
        <div style="height:10px"></div>
        <form id="queryForm" method="post">
            <div id="queryframe" style="display:none">
                <table>
                            <tr>
                                <td height="40px">项目编号：</td>
                                <td ><input name="businesscode"  id="queryclick" type="text" value="${businesscode}"/></td>
                                <td height="40px">项目名称：</td>
                                <td><input name="businessname" id="queryname" type="text" value="${businessname}"/></td>
                            </tr>
                </table>
            </div>
        </form>
                <div class="row-fluid">
                    <%--<button type="button" id="start_flow" onClick="start_flow();">发起审批</button>--%>
                    <%--<button type="button"  onClick="elastictask('queryframe');">查询条件</button>--%>
                    <%--<button type="button" onclick="query();">查询提交</button>--%>
                </div>
                <div class="row-fluid">
                    <span class="span12" style="margin-top:10px">
                        <h4>待办任务</h4>
                        <table  class="well table table-striped table-hover">
                            <thead>
                                <tr style="background-color:#ffffff">
                                    <th style="width:50px">#</th>
                                    <th inorder="true" insel="true" column="USER_ID">
                                        <span class="tableheadOrder">项目编号</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ID">
                                        <span class="tableheadOrder">项目名称</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ID">
                                        <span class="tableheadOrder">所处阶段</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ID">
                                        <span class="tableheadOrder">当前环节</span>
                                    </th>
                                    <%--<th inorder="true" insel="true" column="LOGIN_NAME">--%>
                                        <%--<span class="tableheadOrder">流程名称</span>--%>
                                    <%--</th>--%>
                                    <th inorder="true" insel="true" column="USER_NAME">
                                        <span class="tableheadOrder">流程发起人</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_NAME">
                                        <span class="tableheadOrder">所属部门</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_NAME">
                                        <span class="tableheadOrder">所属机构</span>
                                    </th>


                                    <%--<th inorder="true" insel="true" column="USER_ENABLED">--%>
                                        <%--<span class="tableheadOrder">发起时间</span>--%>
                                    <%--</th>--%>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">接受时间</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">办理人</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">操作</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="item" items="${tasks}" varStatus="status">
                                    <tr id='t_<c:out value="${item.taskID}"/>' 
                                        taskid='<c:out value="${item.taskID}"/>'
                                        <%--targetURL='<c:out value="${item.targetURL}"/>'--%>
                                        class='todolistCSS'>
                                        <td><c:out value="${status.index+1}"/></td>
                                        <td><c:out value="${item.variables.businesscode}"/></td>
                                        <td><c:out value="${item.businessname}"/></td>
                                        <td><c:out value="${item.processName}"/></td>
                                        <td><c:out value="${item.taskName}"/></td>
                                        <%--<td><c:out value="${item.processName}"/></td>--%>
                                        <%--<td><c:out value="${item.startUser}"/></td>--%>
                                        <td><nt:username userid="${item.startUser}"></nt:username></td>
                                        <td><nt:orgname orgid="${item.variables.orgid}"></nt:orgname></td>
                                        <td><nt:orgname orgid="${item.variables.parent}"></nt:orgname></td>
                                        <%--<td><fmt:formatDate value="${item.startDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                                        <td><fmt:formatDate value="${item.acceptTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><nt:username userid="${item.userID}"></nt:username></td>
                                        <td><a href="#" taskid='${item.taskID}'>修改</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </span>
                </div>

            <%@ include file="../../mypage.jsp" %>
        </div>

        <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
