 
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
        <link href="<%=cpath%>/js/jqueryui/jquery-ui.css" rel="stylesheet" type="text/css"> 
    </head>
    <body  >
        <div class="container-fluid">
            <div class="row-fluid">

                <div class="btn-toolbar">
<!--                   <button class="btn btn-primary"><i class="icon-save"></i> 保存</button>-->
                    <c:if test="${returnview!=''}">
                        <a href="<%=cpath%>/<c:out value="${returnview}"/>" data-toggle="modal" class="btn">返回</a>    
                    </c:if>
                    <c:if test="${returnview==''}">
                        <a href="#" onclick="javascript:window.close()" data-toggle="modal" class="btn">关闭</a>    
                    </c:if>
                    <div class="btn-group">
                    </div>
                </div>
                <div class="well">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#home" data-toggle="tab">角色信息</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active in" id="home">
                            <form id="ttt">
                               <label>角色ID</label>
                                <input type="text" name="roleId" value="${role.roleId}" readonly  class="input-xlarge">
                                <label>角色名</label>
                                <input type="text" name="roleName" value="${role.roleName}" readonly class="input-xlarge">                                
                                <label>角色描述</label>
                                <input type="text"  name="roleDescription" value="${role.roleDescription}" readonly class="input-xlarge"> 
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
