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
            $(document).ready(function () {
                $(".files_A").click(function(){
                    var path =  '<%=cpath%>/feedback/download?path=' + encodeURI(encodeURI($(this).attr("url")));
                    window.location.href= path;
                });
//                $("[href]").cleck(function(){
//                    window.location.href = $(this).attr("href");
//
//                });
                <%--$(".todolistCSS").click(function () {--%>
                    <%--var taskId = $(this).attr("id");--%>
                    <%--if(taskId != null && taskId !=""){--%>
                        <%--openWin({--%>
                            <%--url: '<%=cpath %>/flow/opinion/'+taskId--%>
                            <%--, width: 800--%>
                            <%--, height: 300--%>
                        <%--});--%>

                    <%--}--%>
                <%--});--%>
            });
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="row-fluid">
                    <span class="span12" style="margin-top:10px">

                        <table  class="well table table-striped table-hover">
                            <thead>
                                <tr style="background-color:#ffffff">


                                    <th inorder="true" insel="true" column="USER_ID">
                                        <span class="tableheadOrder">任务名称</span>
                                    </th>                                     
                                    <th inorder="true" insel="true" column="USER_NAME">
                                        <span class="tableheadOrder">操作人</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">操作时间</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">操作结果</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">意见详细</span>
                                    </th>
                                    <th inorder="true" insel="true" column="USER_ENABLED">
                                        <span class="tableheadOrder">附件</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="item" items="${result}" varStatus="status">
                                    <tr class='todolistCSS' id="${item.taskId}">
                                        <td>
                                            <c:if test="${fn:toUpperCase(item.activityName)=='START'}">
                                                启动
                                            </c:if>
                                            <c:if test="${fn:toUpperCase(item.activityName)=='END'}">
                                                结束
                                            </c:if>
                                            <c:if test="${fn:toUpperCase(item.activityName)!='START'&&fn:toUpperCase(item.activityName)!='END'}">
                                                <c:out value="${item.activityName}"/>
                                            </c:if>
                                        </td>
                                        <%--<td><c:out value="${item.assignee}"/></td>--%>
                                        <td><nt:username userid="${item.assignee}"></nt:username></td>
                                        <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><c:out value="${item.actionBtnName}"/></td>
                                        <td><c:out value="${item.variablesLocal.commit}"/></td>
                                        <td>
                                            <c:if test="${!empty item.variablesLocal.optionpath}">
                                                <a class="files_A" url="<c:out value="${item.variablesLocal.optionpath}"/>" href='#'>查看</a>
                                            </c:if>
                                        </td>

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
