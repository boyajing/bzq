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

 <script language="javascript">
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
        $("#save").click(function() {  
           
            var roleform = $("#tab");  
            //将form数据组装成json对象的juery扩展  
            var role = roleform.serializeObject();  
            //将json对象转成String是用的json2（http://www.json.org/js.html）的JSON.stringify  
            var jsonrole = JSON.stringify(role);  
          
            $.ajax({  
                type : 'POST',  
                contentType : 'application/json',  
                url : '<%=cpath%>/jeda/role/new',  
                data : jsonrole,  
                dataType : 'json',  
                success : function(role) {  
                    alert("角色建立成功。");
                    location.href="<%=cpath%>/jeda/role/query?r=${returnview}&roleID="+role.roleId;//
                }  
            });  
        });  
            });
        </script>
    </head>
    <body >
        <div class="container-fluid">
            <div class="row-fluid">

                <div class="btn-toolbar">
                    <button name="save" id="save"class="btn btn-primary"><i class="icon-save"></i> 保存</button>
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
                            <form id="tab">
                                <label>角色ID</label>
                                <input type="text" name="roleId" value="" class="input-xlarge">
                                <label>角色名称</label>
                                <input type="text" name="roleName" value="" class="input-xlarge">               
                                <label>角色描述</label>
                                <input type="text" name="roleDescription" value="" class="input-xlarge">  
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
