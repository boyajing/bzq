 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="<%=cpath%>/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/queryframe.js"></script>
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/css/mypage.css">
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/js/bootstrap/css/bootstrap.css">
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/js/date/jquery.ui.css">
    </head>
    <body>
    <div class="row-fluid">
        <table id="resultTab" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
            <tr>
                <th rowspan="1">用户姓名</th>
                <th rowspan="1">所在部门</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.orgName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@ include file="../pagefoot.jsp" %>
        <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
