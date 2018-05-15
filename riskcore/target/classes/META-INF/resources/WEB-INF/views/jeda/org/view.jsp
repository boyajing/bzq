 
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
        <link href="<%=cpath%>/js/jqueryui/jquery-ui.css" rel="stylesheet" type="text/css">
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/datepicker_cn.js"></script>
       
    </head>
    <body  >
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">
                    <!--                   <button class="btn btn-primary"><i class="icon-save"></i> 保存</button>-->
                    <c:if test="${returnview!=''}">
                        <a href="<%=cpath%>/<c:out value="${returnview}"/>&org=${porg}" data-toggle="modal" class="btn">返回</a>    
                    </c:if>
                    <c:if test="${returnview==''}">
                        <a href="#" onclick="javascript:window.close()" data-toggle="modal" class="btn">关闭</a>    
                    </c:if>
                    <div class="btn-group">
                    </div>
                </div>
                <div class="well">
                        <div class="tab-pane active in" id="home">
                            <table class="well table resultTable  table-hover"> 
                                <tr>
                                    <td>机构简称</td>
                                    <td>${org.orgName}</td>
                                    <td>机构ID</td>
                                    <td>${org.orgId}</td>
                                </tr>
                                <tr>
                                    <td>机构全称</td>
                                    <td>${org.orglname}</td>
                                    <td>成立日期</td>
                                    <td><fmt:formatDate value="${org.foundday}" pattern="yyyy-MM-dd"/></td>
                                </tr>
                                <tr>
                                    <td>联系人</td>
                                    <td>${org.orgContact}</td>
                                    <td>联系人电话</td>
                                    <td>${org.orgTel}</td>
                                </tr>
                                <tr>
                                    <td>省份</td>
                                    <td>${org.province}</td>
                                    <td>市县</td>
                                    <td>${org.city}</td>
                                </tr>
                                <tr>
                                    <td>详细地址</td>
                                    <td>${org.orgAddress}</td>
                                    <td>邮编</td>
                                    <td>${org.post}</td>
                                </tr>
                            </table> 
                        </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
