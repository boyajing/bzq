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
            .userBtn{
                width:90px
            }
        </style>
    </head>
    <body>
        <%@include file="selectuserinner.jsp" %>        
    </body>
</html>
