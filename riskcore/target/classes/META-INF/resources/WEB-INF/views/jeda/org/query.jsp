 
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
            var hiddenOrglist = function () {
                parent.triggerLeft();
            };
            $(document).ready(function () {

                $("#delcheck").click(function delcheck()
                {
                    var checkednum = $("input:checked");
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
                    window.location.href = "<%=cpath%>/jeda/org/delete?orgID=" + $("input:checked")[0].id + "&pi=${pageindex}&org=${orgbean.orgId}";
                    //window.open("<%=cpath%>/jeda/org/orgtree", "leftframe");
                });
                $("#edit").click(function edit()
                {
                    var checkednum = $("input:checked");
                    if (checkednum.length < 1) {
                        alert("请选择一条数据。");
                    } else if (checkednum.length > 1) {
                        alert("每次只能修改一条数据。");
                    } else {
                        location.href = "<%=cpath%>/jeda/org/edit?r=<c:out value="${returnview}"/>&org=<c:out value="${orgbean.orgId}"/>&orgID=" + checkednum[0].id;
                    }
                });
                $("#view").click(function view()
                {
                    var checkednum = $("input:checked");
                    if (checkednum.length < 1) {
                        alert("请选择一条数据。");
                    } else if (checkednum.length > 1) {
                        alert("每次只能查看一条数据。");
                    } else {
                        location.href = "<%=cpath%>/jeda/org/view?org=<c:out value="${orgbean.orgId}"/>&r=<c:out value="${returnview}"/>&orgID=" + checkednum[0].id;
                    }
                });

                $("#able").click(function able()
                {
                    var checkednum = $("input:checked");
                    if (checkednum.length < 1) {
                        alert("请选择一条数据。");
                    } else if (checkednum.length > 1) {
                        alert("每次只能激活一条数据。");
                    } else {
                        var enabled = checkednum[0].value;
                        if (enabled === 1) {
                            alert("此机构已经处在启用状态，不需再次启用。");
                        } else {
                            location.href = "<%=cpath%>/jeda/org/able?e=1&orgId=" + checkednum[0].id + "&org=${orgbean.orgId}";
                            alert("启用成功");
                        }
                    }
                });
                $("#unable").click(function enable()
                {
                    var checkednum = $("input:checked");
                    if (checkednum.length < 1) {
                        alert("请选择一条数据。");
                    } else if (checkednum.length > 1) {
                        alert("每次只能停用一条数据。");
                    } else {
                        var enabled = checkednum[0].value;
                        if (enabled === 0) {
                            alert("此机构已经处在禁用状态，不需再次禁用。");
                        } else {
                            location.href = "<%=cpath%>/jeda/org/able?e=0&r=<c:out value="${returnview}"/>&orgId=" + checkednum[0].id + "&pi=${pageindex}&org=${orgbean.orgId}";
                            alert("停用成功");
                        }
                    }
                });
                $("span.tableheadOrder").click(function (evn) {
                    var column = $(evn.target).parent().attr("column");
                    //alert(column);
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


                $("#tree1").treeview({
                    url: "<%=cpath%>/jeda/org/gettree",
                    persist: "cookie"
                });
                //创建机构
                $("#cjjg").click(function cjjg(){
                    location.href="<%=cpath%>/jeda/org/new?r=${returnview}&orgId="+encodeURIComponent('${orgbean.orgId}');
                });

            });
        </script>
    </head>
    <body  >
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">
                    <a id="cjjg" role="button" data-toggle="modal" class="btn"><i class="icon-plus"></i> 创建机构</a>
                    <a  id="delcheck" role="button" data-toggle="modal" class="btn">删除所选</a>
                    <button id="edit" class="btn">修改</button>
                    <button id="view" class="btn">查看</button>
                    <a href="#queryFormDiag" role="button" data-toggle="modal" class="btn">查询</a>
                    <%--<button id="able" class="btn">启用</button>
                    <button id="unable" class="btn">停用</button>--%>
                    <!--  <button class="btn">导出</button>
                  <a href="#columnSelected" role="button" data-toggle="modal" class="btn">选择列</a>
                   <a href="#hideorglist" onclick="hiddenOrglist()" role="button" data-toggle="modal" class="btn">隐藏机构树</a>-->
                    <a class='space_btn'>当前机构：
                        <c:if test="${orgbean==null}">全部</c:if>
                        <c:if test="${orgbean!=null}"><c:out value="${orgbean.orgName}"/></c:if>

                        </a>
                    </div> 
                    <table id="resultTable" class="well table resultTable  table-striped table-hover">
                        <thead>
                            <tr>
                                <th style="width:20px"></th>
                                <th style="width:50px"><span>序号</span></th>
                                <th inorder="true" insel="true" column="org_Id">
                                    <span class="tableheadOrder">机构ID</span>
                                </th>
                                <th inorder="true" insel="true" column="org_Name">
                                    <span class="tableheadOrder">机构简称</span>
                                </th>
                                <th inorder="true" insel="true" column="org_name">
                                    <span class="tableheadOrder">机构全称</span>
                                </th>
                                <th inorder="true" insel="true" column="foundday">
                                    <span class="tableheadOrder">成立日期</span>
                                </th>
                                <th inorder="true" insel="true" column="org_Contact">
                                    <span class="tableheadOrder">联系人</span>
                                </th>
                                <th inorder="true" insel="true" column="org_Tel">
                                    <span class="tableheadOrder">联系人电话</span>
                                </th>
                               <%-- <th inorder="true" insel="true" column="org_Enabled">
                                    <span class="tableheadOrder">状态</span>
                                </th>--%>
                                <th inorder="true" insel="true" column="province">
                                    <span class="tableheadOrder">省份</span>
                                </th>
                                <th inorder="true" insel="true" column="city">
                                    <span class="tableheadOrder">市县</span>
                                </th>
                                <th inorder="true" insel="true" column="org_Address">
                                    <span class="tableheadOrder">详细地址</span>
                                </th>
                                <th inorder="true" insel="true" column="post">
                                    <span class="tableheadOrder">邮编</span>
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="org" items="${result}" varStatus="status">
                            <tr style="cursor:pointer" onclick="$('#<c:out value="${org.orgId}"/>').attr('checked', $('#<c:out value="${org.orgId}"/>').attr('checked') ? false : true)">
                                <td >
                                    <input id="<c:out value="${org.orgId}"/>" value="<c:out value="${org.orgEnabled}"/>" type="checkbox">  
                                </td>
                                <td style="width:50px">
                                    <c:out value="${(pageindex-1)*pagesize+1+status.index}"/> 
                                </td>
                                <td column="orgId"><c:out value="${org.orgId}"/></td>
                                <td column="orgName"><c:out value="${org.orgName}"/></td>
                                <td column="orglname"><c:out value="${org.orglname}"/></td>
                                <td column="foundday"><fmt:formatDate value="${org.foundday}" pattern="yyyy-MM-dd"/></td>
                                <td column="orgContact"><c:out value="${org.orgContact}"/></td>
                                <td column="orgTel"><c:out value="${org.orgTel}"/></td>
                                <%--<td column="orgEnabled">
                                    <c:if test="${org.orgEnabled==0}">
                                        禁用
                                    </c:if>
                                    <c:if test="${org.orgEnabled==1}">
                                        启用
                                    </c:if>
                                </td>--%>
                                <td column="province"><c:out value="${org.province}"/></td>
                                <td column="city"><c:out value="${org.city}"/></td>
                                <td column="orgAddress"><c:out value="${org.orgAddress}"/></td>
                                <td column="post"><c:out value="${org.post}"/></td>

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
                        <h3 id="myModalLabel">查询机构信息</h3>
                    </div>

                    <div class="modal-body">
                        <form id="queryForm" method="post">
                            <label>机构名</label>
                            <input name="orgName" type="text" value="" class="input-xlarge">
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
