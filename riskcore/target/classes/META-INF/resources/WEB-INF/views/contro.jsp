
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en" style="height:100%">
    <head>
        <jsp:include page="commonhead.jsp"></jsp:include>
        <script src="js/bootstrap/js/bootstrap.js"></script>
        <link type="text/css" rel="stylesheet" href="css/css-loader.css">
        <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
        <script>
        </script>
    </head>
    <script type="text/javascript">
        $(function(){
            window.location.href="<%=cpath%>/flow/todolist";
        })
    </script>
    <body>
    <tr></tr>
    111
    <div id="loading" class="loader loader-default is-active" data-text></div>
    </body>
</html>
