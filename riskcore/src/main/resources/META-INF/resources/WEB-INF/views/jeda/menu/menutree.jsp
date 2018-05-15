 
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
        <jsp:include page="../../commonhead.jsp"></jsp:include>
        <link href="<%=cpath%>/js/jquery.treeview/jquery.treeview.edit.css" rel="stylesheet" type="text/css" />
        <link href="<%=cpath%>/js/jqueryui/jquery-ui.css" rel="stylesheet" type="text/css">
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.edit.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.async.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.cookie.js" type="text/javascript"></script>
        <script language="javascript">
            var treeToggle;
            $(document).ready(function () {
                $("#tree2").treeview({
                    url: "<%=cpath%>/jeda/menu/gettree",
                    persist: "cookie",
                    toggle: function () {
                        var arrChild = this.id;
                        var objChild = $("li[id^='" + arrChild + "']");
                        objChild.parent().parent().parent().parent().find("ul span").removeAttr("style");
                        objChild.find("span:first").attr("style","color:red");
                         window.parent.menutreeClick(this.id);
                    }
                });
            });
        </script>
    </head>
    <body >
        <ul id="tree2">
        </ul>  
        <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
