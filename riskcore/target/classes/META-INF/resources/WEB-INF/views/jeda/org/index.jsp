 
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
        <jsp:include page="../../commonhead.jsp"></jsp:include>
        <link href="<%=cpath%>/js/jquery.treeview/jquery.treeview.css" rel="stylesheet" type="text/css" />
        <link href="<%=cpath%>/js/jqueryui/jquery-ui.css" rel="stylesheet" type="text/css">
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.edit.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.async.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.cookie.js" type="text/javascript"></script>
    <script language="javascript">
        function hiddenLeft(){
            $("#mainframe").attr("cols","0px,*");
        }
        function showLeft(){
            $("#mainframe").attr("cols","200px,*");
        }
        function triggerLeft(){
            if($("#mainframe").attr("cols")!="0px,*"){
                $("#mainframe").attr("cols","0px,*");
            }else{
                $("#mainframe").attr("cols","200px,*");
            }
        }
        function orgtreeClick(id){
            window.open('<%=cpath%>/jeda/org/query?org='+id,"rightframe");
        }
    </script>
    </head>
    <frameset id='mainframe' cols="200px,*">
  <frame name='leftframe' src="<%=cpath%>/jeda/org/orgtree" />
  <frame name='rightframe' src="<%=cpath%>/jeda/org/query" />
</frameset>

</html>
