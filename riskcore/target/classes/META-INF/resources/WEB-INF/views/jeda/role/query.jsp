 
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
        <script src="<%=cpath%>/js/shCore.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/shBrushJScript.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/shBrushCss.js" type="text/javascript"></script>  
        <link href="<%=cpath%>/css/shCore.css" rel="stylesheet" type="text/css" />
        <link href="<%=cpath%>/css/shThemeDefault.css" rel="stylesheet" type="text/css" /> 
        <link href="<%=cpath%>/css/tree.css" rel="stylesheet" type="text/css" />
        <link href="<%=cpath%>/css/page.css" rel="stylesheet" type="text/css" />
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/css/mypage.css">
        <jsp:include page="../../commonhead.jsp"></jsp:include>
        <link href="<%=cpath%>/js/jquery.treeview/jquery.treeview.css" rel="stylesheet" type="text/css" />
        <link href="<%=cpath%>/js/jqueryui/jquery-ui.css" rel="stylesheet" type="text/css">
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.edit.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.async.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.cookie.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/openr.js"  type="text/javascript"></script>
        <script language="javascript">
            var exportExcel=function(){
                var excelurl="<%=cpath%>/jeda/role/excel.xls?o=<c:out value="${ordercase}"/>";                
                window.open(excelurl);
            }
            $(document).ready(function () {
                var findSelectList=function(){
                    return $("[rec='true']input:checked");
                };
                var inselc = $("[insel='true']");
                var columnSelectedTable = $("#columnSelectedTable");
                var csHTML = "";
                for (var i = 0; i < inselc.length; i++) {
                    if (i % 2 == 0) {
                        csHTML += "<tr>"
                    }
                    var col = $(inselc[i]).attr("column");
                    var name = $(inselc[i]).text();
                    csHTML += '<td><label class="checkbox"><input type="checkbox" checked="true" columncheck="true" selcolumn="' + col + '">' + name + '</label></td>';
                    if (i % 2 != 0) {
                        csHTML += "</tr>"
                    }

                }
                if (inselc.length % 2 == 1) {
                    csHTML += "<td></td></tr>";
                }
                columnSelectedTable.html(csHTML);

                $("#columnSelectedOK").click(function () {
                    var cs = $("[columncheck='true']");
                    for (var i = 0; i < cs.length; i++) {
                        var column = $(cs[i]).attr("selcolumn")
                        if ($(cs[i]).attr("checked")) {
                            $("[column='" + column + "']").show();
                        } else {
                            $("[column='" + column + "']").hide();
                        }
                    }
                });




                $("#tree").treeview({
                    url: "<%=cpath%>/jeda/menu/gettree",
                    persist: "cookie"

                });
                $("#delcheck").click(function delcheck()
                {
                    var checkednum = findSelectList();
                    if (checkednum.length < 1) {
                        alert("请选择一条数据。");
                    } else if (checkednum.length > 1) {
                        alert("每次只能删除一条数据。");
                    } else {
                        $('a[id="delcheck"]').attr('href', '#delForm');
                    }
                });
                $("#delbtn").click(function del()
                {
                    window.location.href = "<%=cpath%>/jeda/role/delete?roleID=" + $("input:checked")[0].id.substr(2, 20) + "&pi=${pageindex}&role=${rolebean.roleId}";
                });
                $("#edit").click(function edit()
                {
                    var checkednum = findSelectList();

                    if (checkednum.length < 1) {
                        alert("请选择一条数据。");
                    } else if (checkednum.length > 1) {
                        alert("每次只能修改一条数据。");
                    } else {
                        location.href = "<%=cpath%>/jeda/role/edit?r=${returnview}&roleId=" + checkednum[0].id.substr(2, 20);
                    }
                });
                $("#view").click(function view()
                {
                    var checkednum = findSelectList();
                    if (checkednum.length < 1) {
                        alert("请选择一条数据。");
                    } else if (checkednum.length > 1) {
                        alert("每次只能修改一条数据。");
                    } else {
                        location.href = "<%=cpath%>/jeda/role/view?r=${returnview}&roleId=" + checkednum[0].id.substr(2, 20);
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
                $("[inorder='true'][column='" + ss[0] + "']").append(
                        ss[1] == "ASC" ? '<i class="ui-icon ui-icon-triangle-1-n" style="float:left"></i>' :
                        '<i class="ui-icon ui-icon-triangle-1-s" style="float:left"></i>');
            </c:if>
            });
        </script>
    </head>
    <body  >
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">

                    <a href="<%=cpath%>/jeda/role/new?r=<c:out value="${returnview}"/>&roleId=<c:out value="${rolebean.roleId}"/>" role="button" data-toggle="modal" class="btn"><i class="icon-plus"></i> 新建</a>
                    <a  id="delcheck" role="button" data-toggle="modal" class="btn">删除所选</a>
                    
                    <button id="edit" class="btn">修改</button>
                    <button id="view" class="btn">查看</button>
                    
                    <a href="#queryFormDiag" role="button" data-toggle="modal" class="btn">查询</a>
                    <%--<a class="btn" onclick="javascript:exportExcel()">导出</a>--%>
                    <a href="#columnSelected" role="button" data-toggle="modal" class="btn" style="display: none">选择列</a>
                     <c:if test="${rolebean==null}">全部</c:if>
                    <c:if test="${rolebean!=null}"><c:out value="${rolebean.roleName}"/></c:if>
                    
                </a>-->
                    </div> 
                    <table id="resultTable" class="well table resultTable  table-striped table-hover">
                        <thead>
                            <tr>
                                <th style="width:20px"></th>
                                <th style="width:50px">#</th>
                                <th inorder="true" insel="true" column="ROLE_ID">
                                    <span class="tableheadOrder">角色ID</span>
                                </th>
                                <th inorder="true" insel="true" column="ROLE_NAME">
                                    <span class="tableheadOrder">角色名</span>
                                </th>
                                <th inorder="true" insel="true" column="ROLE_DESCRIPTION">
                                    <span class="tableheadOrder">角色描述</span>
                                </th>
                                <th inorder="true" insel="true" column="ROLE_USER">
                                    <span >角色用户</span>
                                </th>
                                <th inorder="true" insel="true" column="ROLE_MENU">
                                    <span >角色菜单</span>
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="role" items="${result}" varStatus="status">
                            <tr style="cursor:pointer">
                                <td >
                                    <input  rec='true' id="C_<c:out value="${role.roleId}"/>" type="checkbox">  
                                </td>
                                <td style="width:50px"  onclick="$('#C_<c:out value="${role.roleId}"/>').attr('checked', $('#C_<c:out value="${role.roleId}"/>').attr('checked') ? false : true)">
                                    <c:out value="${(pageindex-1)*pagesize+1+status.index}"/> 
                                </td>
                                <td column="ROLE_ID"  onclick="$('#C_<c:out value="${role.roleId}"/>').attr('checked', $('#C_<c:out value="${role.roleId}"/>').attr('checked') ? false : true)"><c:out value="${role.roleId}"/></td>
                                <td column="ROLE_NAME"  onclick="$('#C_<c:out value="${role.roleId}"/>').attr('checked', $('#C_<c:out value="${role.roleId}"/>').attr('checked') ? false : true)"><c:out value="${role.roleName}"/></td>
                                <td column="ROLE_DESCRIPTION"  onclick="$('#C_<c:out value="${role.roleId}"/>').attr('checked', $('#C_<c:out value="${role.roleId}"/>').attr('checked') ? false : true)"><c:out value="${role.roleDescription}"/></td>
                                <td column="ROLE_USER" style='width:100px'>
                                    <a href="#userchoose" id="U_<c:out value="${role.roleId}"/>" role="button" data-toggle="modal" class="btn" onclick='openWin({url:"<%=cpath%>/jeda/user/selectuser?po=<%=cpath%>/jeda/role/updateroleuser2&role=<c:out value="${role.roleId}"/>",width:700,height:620});return true;'>设定用户</a> 
                                </td>
                                <td column="ROLE_MENU" style='width:80px'><input id="M_<c:out value="${role.roleId}"/>" type="button" class="btn" value="编辑菜单" onclick='openWin({url:"<%=cpath%>/jeda/menu/menuselect?po=/jeda/role/updaterolemenu&role=<c:out value="${role.roleId}"/>",width:700,height:500});return true;'></td>
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
                        <h3 id="myModalLabel">查询角色信息</h3>
                    </div>

                    <div class="modal-body">
                        <form id="queryForm" method="post">
                            <label>角色名</label>
                            <input name="roleName" type="text" value="" class="input-xlarge">
                            <label>角色ID</label>
                            <input name="roleId" type="text" value="" class="input-xlarge">
                        </form> 
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                        <button onclick="$('#queryForm').submit()" class="btn " data-dismiss="modal">确认</button>
                    </div>
                </div>
                <div class="modal bigger hide fade" style="height:450px" id="columnSelected" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">勾选在列表中显示的列</h3>
                    </div>

                    <div class="modal-body" style="height:300px;overflow: auto">
                        <table id="columnSelectedTable" class="table resultTable">
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                        <button id="columnSelectedOK" class="btn " data-dismiss="modal">确认</button>
                    </div>
                </div>




            </div>
        </div>

        <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
