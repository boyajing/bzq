 
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
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/jquery-ui.js"></script>
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/datepicker_cn.js"></script>
        <script>
            parent.updateNavByMenuid("A003", [{
                    "menuName": "查看用户", "menuUrl": ""
                }]);
            
            $(function () {
                initdatepicker_cn();
                    $("#userBirthday").datepicker({
                        inline: true
                    });
                });
        </script>
        
    </head>
    <body >
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
                                <table class="well table resultTable table-hover"> 
                                    <tr>
                                        <td>用户ID</td>
                                        <td>
                                            <input type="text" name="userId" value="${user.userId}" readonly  class="input-xlarge">
                                        </td>
                                        <td>用户姓名</td>
                                        <td>
                                            <input type="text" name="userName" value="${user.userName}" readonly valType="CHINESE"  msg="<font color=red>*</font>请输入您的中文名"  class="input-xlarge">
                                             
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>性别</td>
                                        <td>
                                            <select id="userGender" name="userGender" >
                                                <c:if test="${user.userGender=='M'}"><option value="M">男</option></c:if>
                                                <c:if test="${user.userGender=='F'}"><option value="F">女</option></c:if>
                                                
                                            </select>
                                        </td>
                                        <td>Email</td>
                                        <td>
                                            <input type="text"  name="userEmail" value="${user.userEmail}"   readonly valType="MAIL"  msg="<font color=red>*</font>请输入正确的邮箱地址" class="input-xlarge">  
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>职务</td>
                                        <td>
                                            <input type="text"  name="positionId" value="${user.positionId}" readonly  class="input-xlarge">  
                                        </td>
                                        <td>生日</td>
                                        <td>
                                            <input type="text" name="userBirthday" id="userBirthday" value="<fmt:formatDate value="${user.userBirthday}" pattern="yyyy-MM-dd"/>" readonly  pattern="yyyy-MM-dd"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>电话</td>
                                        <td>
                                            <input type="text" name="userTel" value="${user.userTel}" readonly valType="TEL" msg="<font color=red>*</font>请输入电话" class="input-xlarge">  
                                        </td>
                                        <td>手机</td>
                                        <td>
                                            <input type="text" name="userMobile" value="${user.userMobile}"  readonly valType="MOBILE" msg="<font color=red>*</font>请输入手机号码"  class="input-xlarge"> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>地址</td>
                                        <td>
                                            <input type="text" name="userAddress" value="${user.userAddress}" readonly class="input-xlarge">  
                                        </td>
                                        <td>邮编</td>
                                        <td>
                                            <input type="text" name="userPost" value="${user.userPost}" readonly class="input-xlarge"> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>描述</td>
                                        <td>
                                            <input type="text" name="userDescription" value="${user.userDescription}" readonly class="input-xlarge">
                                        </td>
                                        <td>所属机构</td>
                                        <td>
                                            <input type="text" value="${orgName}"readonly type="text" readonly class="input-xlarge">
                                           
                                             <input  name="orgId" id="orgId" value="${user.orgId}" type="hidden" class="input-xlarge"> 
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
            </div>
        </div>
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
