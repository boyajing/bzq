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
        <title></title>
        <style>
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
            
            var doTaskURL = "<%=cpath%>/flow/approve/";
            $(document).ready(function () {
                $(".todolistCSS").click(function () {
                    openWin({
                        url: doTaskURL+$(this).attr("taskid")
                        , width: screen.width-50
                        , height: screen.height-150
                    });
                });
            });
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="row-fluid">
                    <span class="span12" style="margin-top:10px">
                        <h4>待办任务</h4>
                        <table  class="well table table-striped table-hover">
                            <thead>
                                <tr style="background-color:#ffffff">
                                    <th style="width:20px"></th>
                                    <th style="width:50px">#</th>

                                    <th inorder="true" insel="true" column="LOGIN_NAME">
                                        <span class="tableheadOrder">流程名称</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_NAME">
                                        <span class="tableheadOrder">流程发起人</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">发起时间</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">结束时间</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="item" items="${result}" varStatus="status">
                                    <tr id='t_<c:out value="${item.processInstanceId}"/>'
                                        taskid='<c:out value="${item.processInstanceId}"/>'
                                        class='todolistCSS'>
                                        <td></td>
                                        <td><c:out value="${status.index+1}"/></td>
                                        <td><c:out value="${item.processDefinitionName}"/></td>
                                        <td><c:out value="${item.startUserId}"/></td>
                                        <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </span>
                </div>
            </div>
        </div>

        <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
