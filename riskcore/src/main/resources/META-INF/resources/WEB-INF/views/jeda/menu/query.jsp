 
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
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/css/mypage.css">
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.edit.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.async.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.cookie.js" type="text/javascript"></script>
        <script language="javascript">
            var hiddenMenulist=function(){
                parent.triggerLeft();
            };
            $(document).ready(function () {
                $("#delcheck").click(function delcheck()
                {
                    var checkednum = $("input:checked");
                    if(checkednum.length<1){
                        alert("请选择一条数据。");
                    }else if(checkednum.length>1){
                        alert("每次只能删除一条数据。");
                    }else{
                        $('a[id="delcheck"]').attr('href', '#delForm');
                    }});
               $("#delbtn").click(function del()
                {window.location.href = "<%=cpath%>/jeda/menu/delete?menuId=" + $("input:checked")[0].id.substr(2, 20)+"&pi=${pageindex}&menu=${menubean.menuId}";
                    window.open("<%=cpath%>/jeda/menu/menutree","leftframe");
           } );
                $("#edit").click(function edit()
                {
                    
                    var checkednum = $("input:checked");
                    
                    if(checkednum.length<1){
                        alert("请选择一条数据。");
                    }else if(checkednum.length>1){
                        alert("每次只能修改一条数据。");
                    }else{
                        location.href = "<%=cpath%>/jeda/menu/edit?r=<c:out value="${returnview}"/>&menuId=" + checkednum[0].id.substr(2, 20);
                        window.open("<%=cpath%>/jeda/menu/menutree","leftframe");
                    }      
                });
                $("#view").click(function view()
                {
                    var checkednum = $("input:checked");
                    if(checkednum.length<1){
                        alert("请选择一条数据。");
                    }else if(checkednum.length>1){
                        alert("每次只能查看一条数据。");
                    }else{
                        //alert("${returnview}");
                        location.href = "<%=cpath%>/jeda/menu/view?r=<c:out value="${returnview}"/>&menuId=" + checkednum[0].id.substr(2, 20);
                       
                    }      
                });
                $("span.tableheadOrder").click(function (evn) {
                    var column = $(evn.target).parent().attr("column");
            <c:if test="${ordercase==''}">
                    column += " ASC";
            </c:if>
            <c:if test="${ordercase!=''}">
                    if (column + " ASC" == '<c:out value="${ordercase}"/>') {
                        column += " DESC";
                    } else if (column + " DESC" == '<c:out value="${ordercase}"/>') {
                        column += " ASC";
                    } else {
                        column += " ASC";
                    }
            </c:if>
                    window.location = "?pi=<c:out value="${pageindex}"/>&o=" + column;
                });
            <c:if test="${ordercase!=''}">
                var orderCase = '<c:out value="${ordercase}"/>';
                var ss = orderCase.split(' ');
                //

                $("[inorder='true'][column='" + ss[0] + "']").append(
                        ss[1] == "ASC" ? '<i class="ui-icon ui-icon-triangle-1-n" style="float:left"></i>' :
                        '<i class="ui-icon ui-icon-triangle-1-s" style="float:left"></i>');
            </c:if>
            });

        </script>
    </head>
    <body>
                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="btn-toolbar">
                                <a href="<%=cpath%>/jeda/menu/new?r=<c:out value="${returnview}"/>&pm=<c:out value="${menubean.menuId}"/>" role="button" data-toggle="modal" class="btn"><i class="icon-plus"></i> 创建菜单</a>
                                <a  id="delcheck" role="button" data-toggle="modal" class="btn">删除所选</a>
                                <button id="edit" class="btn">修改</button>
                                <button id="view" class="btn">查看</button>
                                <a href="#queryFormDiag" role="button" data-toggle="modal" class="btn">查询</a>
<!--                                <button class="btn">导出</button>-->
<!--                                <a href="#columnSelected" role="button" data-toggle="modal" class="btn">选择列</a>-->
                                
<!--                                <a href="#hidemenulist" onclick="hiddenMenulist()" role="button" data-toggle="modal" class="btn">隐藏菜单树</a>-->
                                <a class='space_btn'>当前菜单：
                                    <c:if test="${menubean==null}">全部</c:if>
                                    <c:if test="${menubean!=null}"><c:out value="${menubean.menuName}"/></c:if>
                                </a>
                            </div> 
                            <table id="resultTable" class="well table resultTable  table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th style="width:20px"></th>
                                        <th style="width:50px">#</th>
                                        <th inorder="true" insel="true" column="MENU_ID">
                                            <span class="tableheadOrder">菜单ID</span>
                                        </th>
                                        <th inorder="true" insel="true" column="MENU_NAME">
                                            <span class="tableheadOrder">菜单名</span>
                                        </th>
                                        <th inorder="true" insel="true" column="MENU_URL">
                                            <span class="tableheadOrder">菜单链接</span>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="menu" items="${result}" varStatus="status">
                                        <tr style="cursor:pointer" onclick="$('#C_<c:out value="${menu.menuId}"/>').attr('checked', $('#C_<c:out value="${menu.menuId}"/>').attr('checked') ? false : true)">
                                            <td >
                                                <input id="C_<c:out value="${menu.menuId}"/>" type="checkbox">  
                                            </td>
                                            <td style="width:50px">
                                                <c:out value="${(pageindex-1)*pagesize+1+status.index}"/> 
                                            </td>
                                            <td column="MENU_ID"><c:out value="${menu.menuId}"/></td>
                                            <td column="MENU_NAME"><c:out value="${menu.menuName}"/></td>
                                            <td column="MENU_URL"><c:out value="${menu.menuUrl}"/></td>
                                        </tr>
                                        <% index++;%>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <jsp:include page="../pagefoot.jsp"></jsp:include>
                            <div class="modal small hide fade" id="delForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h3 id="myModalLabel">删除操作确认</h3>
                                </div>
                                <div class="modal-body">
                                    <p class="error-text"><i class="icon-warning-sign modal-icon"></i>真的要删除这条记录吗?</p>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消操作</button>
                                    <button id="delbtn" class="btn btn-danger" data-dismiss="modal">确认删除</button>
                                </div>
                            </div>
                            <div class="modal bigger hide fade" id="queryFormDiag" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h3 id="myModalLabel">查询菜单信息</h3>
                                </div>
                                <div class="modal-body">
                                    <form id="queryForm" method="post">
                                        <label>菜单id</label>
                                        <input name="menuId" type="text" value="" class="input-xlarge">
                                        <label>菜单名</label>
                                        <input name="menuName" type="text" value="" class="input-xlarge">
                                        
                                    </form> 
                                </div>
                                <div class="modal-footer">
                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                                    <button onclick="$('#queryForm').submit()" class="btn " data-dismiss="modal">确认</button>
                                </div>
                            </div>

                        </div>
                    </div>
        <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
