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
        $("#bsday").datepicker({
            inline: true
        });
    });
</script>
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
            var menuform = $("#tab");  
            //将form数据组装成json对象的juery扩展  
            var menu = menuform.serializeObject();  
            //将json对象转成String是用的json2（http://www.json.org/js.html）的JSON.stringify  
            var jsonmenu = JSON.stringify(menu); 
            $.ajax({  
                type : 'POST',  
                contentType : 'application/json',  
                url : '<%=cpath%>/jeda/menu/new',  
                data : jsonmenu,  
                dataType : 'json',  
                success : function(menu) {  
                    alert("菜单建立成功。");
                    location.href="<%=cpath%>/<c:out value="${returnview}"/>&pmenu=<c:out value="${pm}"/>";//
                    window.open("<%=cpath%>/jeda/menu/menutree","leftframe");
                },
                error:function(){
                    alert("新建失败");
                    }  
            });  
        }); 
         $("#menuId").blur(function (){
                var a=$("input[name='menuId']").val();
                if(a!=""&&a!=null){
                    $.ajax({  
                        type : 'POST',   
                        url : '<%=cpath%>/jeda/menu/checkid',  
                        data : a,   
                        success : function(i) {  
                            if(i==1){
                                alert("已存在相同ID，请输入一个新的ID。");
                            } 
                        } 
                    });  
                }
             });
            });
        </script>
    </head>
    <body  >
        <div class="container-fluid">
            <div class="row-fluid">

                <div class="btn-toolbar">
                    <button name="save" id="save"class="btn btn-primary"><i class="icon-save"></i> 保存</button>
                    <c:if test="${returnview!=''}">
                        <a href="<%=cpath%>/<c:out value="${returnview}"/>&pmenu=<c:out value="${pm}"/>" data-toggle="modal" class="btn">返回</a>    
                    </c:if>
                    <c:if test="${returnview==''}">
                        <a href="#" onclick="javascript:window.close()" data-toggle="modal" class="btn">关闭</a>    
                    </c:if>
                    <div class="btn-group">
                    </div>
                </div>
                <div class="well">
                        <div class="tab-pane active in" id="home">
                            <form id="tab">
                                <label>菜单ID</label>
                                <input type="text" name="menuId" value="" valType="required" msg="<font color=red>*</font>" class="input-xlarge">
                                <label>菜单名称</label>
                                <input type="text" name="menuName" value="" valType="required" msg="<font color=red>*</font>" class="input-xlarge">               
                                <label>菜单链接</label>
                                <input type="text" name="menuUrl" value="" class="input-xlarge">  
                            </form>
                        </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
