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

        <link href="<%=cpath%>/js/jquery.treeview/jquery.treeview.css" rel="stylesheet" type="text/css" />
        <link href="<%=cpath%>/js/jqueryui/jquery-ui.css" rel="stylesheet" type="text/css">
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.edit.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.async.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.cookie.js" type="text/javascript"></script>
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/jquery-ui.js"></script>
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/datepicker_cn.js"></script>        
        <script type="text/javascript" src="<%=cpath%>/js/validate/jquery.poshytip.js"></script>
        <script type='text/javascript' src='<%=cpath%>/js/validate/jq.validate.js'></script>
<script>
    $(function () {
        initdatepicker_cn();
        $("#userBirthday").datepicker({
            inline: true
        });
    });
   
</script>
 <script language="javascript">
            var treeToggle;
            $(document).ready(function () {
//向后台传送新建的数据，
     $.fn.serializeObject = function() {  
            var o = {};  
            var a = this.serializeArray();  
            $.each(a, function() {  
                if (o[this.name]) {  
                    if (!o[this.name].push) {  
                        o[this.name] = [ o[this.name] ];  
                    }  
                    o[this.name].push(this.value || '');  
                } else {  
                    o[this.name] = this.value || '';  
                }  
            });  
            return o;  
        }; 
         $("#back").click(function() {
             location.href="<%=cpath%>/content";
         });
        $("#edit").click(function() {
            //校验必输项
             if(!beforeSubmit()){
                        return false;
                    }
            var userform = $("#tab");
            //将form数据组装成json对象的juery扩展  
            var user = userform.serializeObject();  
            //将json对象转成String是用的json2（http://www.json.org/js.html）的JSON.stringify  
            var jsonuser = JSON.stringify(user);  
            $.ajax({  
                type : 'POST',  
                contentType : 'application/json',  
                url : '<%=cpath%>/jeda/user/selfedit',  
                data : jsonuser,  
                dataType : 'json',  
                success : function() {  
                    alert("用户修改成功。");
                     location.href="<%=cpath%>/jeda/user/selfedit?userId="+user.userId;//
                } ,
                error:function(user){
                    alert("修改失败");
                    }
            });  
        }); 
        });
        </script>
    </head>
    <body  >
        <div class="container-fluid">
            <div class="row-fluid">
                
                <div class="btn-toolbar">
                    <button name="edit" id="edit"class="btn btn-primary"><i class="icon-save"></i> 保存</button>
                    <div class="btn-group">
                    </div>
                </div>
                <div class="well">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#home" data-toggle="tab">用户信息</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active in" id="home">
                            <form id="tab">
                                <table class="well table resultTable table-hover"> 
                                    <tr>
                                        <td>用户ID</td>
                                        <td>
                                            <input type="text" name="userId" value="${user.userId}" readonly  class="input-xlarge">
                                        </td>
                                        <td>用户姓名</td>
                                        <td>
                                            <input type="text" name="userName" value="${user.userName}" valType="CHINESE"  msg="<font color=red>*</font>请输入"  class="input-xlarge">
                                             
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>性别</td>
                                        <td>
                                            <select id="userGender" name="userGender" >
                                                <c:if test="${user.userGender=='M'}">
                                                    <option value="M" checked>男</option>
                                                    <option value="F">女</option>
                                                </c:if>
                                                <c:if test="${user.userGender=='F'}">
                                                    <option value="F" checked>女</option>
                                                    <option value="M" >男</option>
                                                </c:if>
                                                
                                            </select>
                                        </td>
                                        <td>Email</td>
                                        <td>
                                            <input type="text"  name="userEmail" value="${user.userEmail}"  valType="MAIL"  msg="<font color=red>*</font>请输入" class="input-xlarge">  
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>职务</td>
                                        <td>
                                            <input type="text"  name="positionId" value="${user.positionId}" class="input-xlarge">  
                                        </td>
                                        <td>生日</td>
                                        <td>
                                            <input type="text" name="userBirthday" id="userBirthday" value="<fmt:formatDate value="${user.userBirthday}" pattern="yyyy-MM-dd"/>"  class="input-xlarge">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>电话</td>
                                        <td>
                                            <input type="text" name="userTel" value="${user.userTel}" valType="TEL" msg="<font color=red>*</font>请输入" class="input-xlarge">  
                                        </td>
                                        <td>手机</td>
                                        <td>
                                            <input type="text" name="userMobile" value="${user.userMobile}"  valType="MOBILE" msg="<font color=red>*</font>请输入"  class="input-xlarge"> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>地址</td>
                                        <td>
                                            <input type="text" name="userAddress" value="${user.userAddress}" class="input-xlarge">  
                                        </td>
                                        <td>邮编</td>
                                        <td>
                                            <input type="text" name="userPost" value="${user.userPost}" class="input-xlarge"> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>描述</td>
                                        <td>
                                            <input type="text" name="userDescription" value="${user.userDescription}" class="input-xlarge">
                                        </td>
                                        <td>所属机构</td>
                                        <td>
                                           <input type="text" value="${orgName}"readonly type="text" class="input-xlarge">
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
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
