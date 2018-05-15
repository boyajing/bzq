 
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
                        <a href="<%=cpath%>/r?r=<c:out value="${returnview}"/>" data-toggle="modal" class="btn">返回</a>    
                    </c:if>
                    <c:if test="${returnview==''}">
                        <a href="#" onclick="javascript:window.close()" data-toggle="modal" class="btn">关闭</a>    
                    </c:if>
                    <div class="btn-group">
                    </div>
                </div>
                <div class="well">
                        <div class="tab-pane active in" id="home">
                            <form id="ttt">
                               <label>菜单ID</label>
                                <input type="text" name="menuId" value="${menu.menuId}" readonly  class="input-xlarge">
                                <label>菜单名</label>
                                <input type="text" name="menuName" value="${menu.menuName}" readonly class="input-xlarge">                                
                                <label>菜单链接</label>
                                <input type="text"  name="menuUrl" value="${menu.menuUrl}" readonly class="input-xlarge"> 
                            </form>
                        </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
