<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
        <link rel="shortcut icon" href="<%=cpath%>/images/icons/ico.ico" type="image/x-icon" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="" >
        <link rel="stylesheet" type="text/css" href="<%=cpath%>/js/bootstrap/css/bootstrap.css">
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/js/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="<%=cpath%>/css/theme.css">
        <script src="<%=cpath%>/js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="<%=cpath%>/js/queryframe.js"></script>
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/css/mypage.css">
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
            .contentFrm {
                width:100%;
                position: relative;
                overflow: auto;
                height:93%;
            }
            .listhotitem{
                color:red;
                font-weight: bold
            }
            .diagramModal{
                margin: -120px 0 0 -450px;
            }
            .diagramModalBody{
                max-height: 600px;

                overflow-y: auto;
                padding: 15px;
            }
            .todolistCSS{
                cursor: pointer;
            }
        </style>

        <script>

            var doTaskURL = "<%=cpath%>/flow/viewtask/";
            $(document).ready(function () {
                $(".todolistCSS").click(function () {
                    openWin({
                        url: doTaskURL + $(this).attr("taskid")
                        , width: screen.width
                        , height: screen.height-100
                    });
                });
            });

            //查询
            function query() {
                $("#queryForm").attr("action", "<%=cpath%>/flow/takenlistindex").submit();
            }
        </script>


    </head>
    <body>
        <div class="container-fluid">

                <div class="row-fluid">
                    <div style="height:10px"></div>
                    <form id="queryForm" method="post">
                        <div id="queryframe" style="display:none">
                            <table class="well table resultTable table-hover">
                                <tr>
                                    <td height="40px">项目编号：</td>
                                    <td  ><input name="businesscode" id="queryclick"  type="text" value="${businesscode}" class="input-medium"/></td>
                                    <td height="40px">项目名称：</td>
                                    <td><input name="businessname" id="queryname" type="text" value="${businessname}" class="input-medium"/></td>
                                </tr>
                            </table>
                        </div>
                    </form>
                    <div class="row-fluid">
                        <%--<button type="button" id="start_flow" onClick="start_flow();">发起审批</button>--%>
                        <button type="button" onClick="elastictask('queryframe');">查询条件</button>
                        <button type="button" onclick="query();">查询提交</button>
                    </div>
                    <span class="" style="margin-top:10px">
                        <h4>已办任务</h4>
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
                                    <th inorder="true" insel="true" column="LOGIN_NAME">
                                        <span class="tableheadOrder">流程名称</span>
                                    </th>
                                    <th inorder="true" insel="true" column="LOGIN_NAME">
                                        <span class="tableheadOrder">节点审批意见</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_NAME">
                                        <span class="tableheadOrder">所属部门</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_NAME">
                                        <span class="tableheadOrder">所属机构</span>
                                    </th>

                                    <th inorder="true" insel="true" column="USER_NAME">
                                        <span class="tableheadOrder">流程发起人</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">接收时间</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">办理时间</span>
                                    </th>
                                    <%--<th inorder="true" insel="true" column="USER_ENABLED">--%>
                                        <%--<span class="tableheadOrder">操作</span>--%>
                                    <%--</th>--%>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="item" items="${tasks}" varStatus="status">
                                    <tr id='t_<c:out value="${item.taskID}"/>' 
                                        taskid='<c:out value="${item.taskID}"/>'
                                        class='todolistCSS'>
                                        <td><c:out value="${status.index+1}"/></td>
                                        <td><c:out value="${item.variables.businesscode}"/></td>
                                        <td><c:out value="${item.businessname}"/></td>
                                        <td><c:out value="${item.processName}"/></td>
                                        <td><c:out value="${item.taskName}"/></td>
                                        <td><c:out value="${item.actionBtnName}"/></td>
                                        <%--<td><c:out value="${item.startUser}"/></td>--%>
                                        <td><nt:orgname orgid="${item.variables.orgid}"></nt:orgname></td>
                                        <td><nt:orgname orgid="${item.variables.parent}"></nt:orgname></td>

                                        <td><nt:username userid="${item.startUser}"></nt:username></td>
                                        <td><fmt:formatDate value="${item.startDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <%--<td><c:out value="${item.actionBtnName}"/></td>    --%>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </span>

            </div>

            <%@ include file="../mypage.jsp" %>
        </div>
        <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
