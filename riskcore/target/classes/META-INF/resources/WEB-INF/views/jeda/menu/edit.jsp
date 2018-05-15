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
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/jquery-ui.js"></script>
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/datepicker_cn.js"></script>
        <script>
            $(function () {
                initdatepicker_cn();
                $("#userBirthday").datepicker({dateFormat: "dd-mm-yy"}).val();
            });

        </script>
        <script language="javascript">
            $(document).ready(function () {
       //向后台传送新建的数据，
                $.fn.serializeObject = function () {
                    var o = {};
                    var a = this.serializeArray();
                    $.each(a, function () {
                        if (o[this.name]) {
                            if (!o[this.name].push) {
                                o[this.name] = [o[this.name]];
                            }
                            o[this.name].push(this.value || '');
                        } else {
                            o[this.name] = this.value || '';
                        }
                    });
                    return o;
                };
                $("#edit").click(function () {
                    var menuform = $("#tab");
                    //将form数据组装成json对象的juery扩展  
                    var menu = menuform.serializeObject();
                    //将json对象转成String是用的json2（http://www.json.org/js.html）的JSON.stringify  
                    var jsonmenu = JSON.stringify(menu);
                    $.ajax({
                        type: 'POST',
                        contentType: 'application/json',
                        url: '<%=cpath%>/jeda/menu/edit',
                        data: jsonmenu,
                        dataType: 'json',
                        success: function () {
                            alert("修改成功。");
                            location.href="<%=cpath%>/<c:out value="${returnview}"/>&pmenu=<c:out value="${menu.parentMenuId}"/>";//
                            window.open("<%=cpath%>/jeda/menu/menutree","leftframe");
                        },
                        error: function () {
                            alert("修改失败");
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
                    <button name="edit" id="edit"class="btn btn-primary"><i class="icon-save"></i> 保存</button>
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
                                <input type="text" name="menuId" value="${menu.menuId}" readonly  class="input-xlarge">
                                <label>菜单名</label>
                                <input type="text" name="menuName" value="${menu.menuName}" class="input-xlarge">                                
                                <label>菜单链接</label>
                                <input type="text"  name="menuUrl" value="${menu.menuUrl}" class="input-xlarge">  
                            </form>
                        </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
