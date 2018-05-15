 
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
        <link rel="stylesheet" type="text/css" href="<%=cpath%>/css/component.css" />
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.edit.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.async.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.cookie.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.ba-throttle-debounce.min.js"></script>
        <script src="<%=cpath%>/js/jquery.stickyheader.js"></script>
        <link type="text/css" rel="stylesheet" href="<%=cpath%>/css/mypage.css">
        <script language="javascript">
            //设定用户组织机构时用户选择的机构ID
            var orgSelectID = "";
            var orgtreeClick = function (id) {

                $.ajax({
                    type: 'GET',
                    contentType: 'application/json',
                    url: '<%=cpath%>/jeda/org/view.json?orgID=' + id,
                    dataType: 'json',
                    success: function (data) {
                        orgSelectID = id;
                        $("#curOrcSelected").html("当前选定的机构为：<b>" + data.org.orgName + "</b>");
                    },
                    error: function (user) {

                    }
                });
            };

            var hiddenOrglist = function () {

                parent.triggerLeft();

            };
            var exportExcel = function () {
                var excelurl = "<%=cpath%>/jeda/user/excel.xls?o=<c:out value="${ordercase}"/>";
            <c:if test="${orgbean!=null}">
                        excelurl += "&org=<c:out value="${orgbean.orgId}"/>";
            </c:if>

                        window.open(excelurl);
                    };
                    $(document).ready(function () {
                        //获取角色列表
                        $.ajax({
                            type: 'GET',
                            contentType: 'application/json',
                            url: '<%=cpath%>/jeda/role/list.json',
                            dataType: 'json',
                            success: function (data) {
                                for (var i = 0; i < data.result.length; i++) {
                                    var d = data.result[i];

                                    $("#rolelisttable").append("<tr style=\"cursor:pointer\" onclick=\"$('#rc_" + d.roleId + "').attr('checked', $('#rc_" + d.roleId + "').attr('checked') ? false : true)\"><td><input type='checkbox' id='rc_" + d.roleId + "' rolechk='true' roleid='" + d.roleId + "'></td><td>" + d.roleId + "</td><td>" + d.roleName + "</td></tr>");
                                }
                                $("[href=#setroleDiag]").click(function () {
                                    $("[rolechk=true]").attr("checked", false);
                                    var checkednum = findSelectList();
                                    if (checkednum.length == 1) {
                                        $.ajax({
                                            type: 'GET',
                                            url: '<%=cpath%>/jeda/role/queryUsersRole.json?userid=' + $(checkednum[0]).attr("id").substr(2, 20),
                                            dataType: 'json',
                                            success: function (data) {

                                                for (var i = 0; i < data.result.length; i++) {
                                                    var roleid = "#rc_" + data.result[i].roleId;
                                                    $(roleid).attr("checked", true);
                                                }

                                            },
                                            error: function (data) {

                                            }
                                        });
                                    }
                                });
                            },
                            error: function (data) {

                            }
                        });
                        $("#orgselectbtn").click(function () {
                            if (orgSelectID === "") {
                                alert("请选择组织机构");
                                return;
                            }


                            var us = "";


                            var checkednum = findSelectList();
                            checkednum.each(function (index, ele) {

                                us += $(this).attr("id").substr(2, 20) + ",";
                            });
                            if (us !== "") {
                                $.ajax({
                                    type: 'GET',
                                    url: '<%=cpath%>/jeda/org/updateorguser.json',
                                    data: {
                                        orgID: orgSelectID,
                                        us: us
                                    },
                                    dataType: 'json',
                                    success: function (data) {

                                        if (data.result === 'succeed') {
                                            alert("组织机构设定成功");
                                        }
                                        window.location.reload();
                                    },
                                    error: function (user) {
                                        alert(user);
                                    }
                                });
                            }

                        });
                        $("#roleselectbtn").click(function () {
                            var rs = "";
                            var us = "";
                            $("[rolechk=true]").each(function (index, ele) {
                                if ($(this).attr("checked")) {
                                    rs += $(this).attr("roleid") + ",";
                                }
                            });

                            var checkednum = findSelectList();
                            checkednum.each(function (index, ele) {

                                us += $(this).attr("id").substr(2, 20) + ",";
                            });
                            if ( us !== "") {
                                $.ajax({
                                    type: 'POST',
                                    url: '<%=cpath%>/jeda/role/updateroleuser.json',
                                    data: {
                                        rs: rs,
                                        us: us
                                    },
                                    dataType: 'json',
                                    success: function (data) {

                                        if (data.result === 'succeed') {
                                            alert("角色设定成功");
                                        }
                                    },
                                    error: function (user) {

                                    }
                                });
                            }
                        });

                        var findSelectList = function () {
                            return $("[rec='true']input:checked");
                        };
                        var inselc = $("[insel='true']");
                        var columnSelectedTable = $("#columnSelectedTable");
                        var csHTML = "";
                        for (var i = 0; i < inselc.length; i++) {
                            if (i % 2 === 0) {
                                csHTML += "<tr>";
                            }
                            var col = $(inselc[i]).attr("column");
                            var name = $(inselc[i]).text();
                            csHTML += '<td><label class="checkbox"><input type="checkbox" checked="true" columncheck="true" selcolumn="' + col + '">' + name + '</label></td>';
                            if (i % 2 !== 0) {
                                csHTML += "</tr>";
                            }

                        }
                        if (inselc.length % 2 === 1) {
                            csHTML += "<td></td></tr>";
                        }
                        columnSelectedTable.html(csHTML);

                        $("#columnSelectedOK").click(function () {
                            var cs = $("[columncheck='true']");
                            for (var i = 0; i < cs.length; i++) {
                                var column = $(cs[i]).attr("selcolumn");
                                if ($(cs[i]).attr("checked")) {
                                    $("[column='" + column + "']").show();
                                } else {
                                    $("[column='" + column + "']").hide();
                                }
                            }
                        });
                        $("#delcheck").click(function delcheck()
                        {
                            var checkednum = findSelectList();

                            if (checkednum.length < 1) {
                                alert("请选择一条数据。");
                            }  else {
                                $('a[id="delcheck"]').attr('href', '#delForm');
                            }
                        });
                        $("#delbtn").click(function del()
                        {
                            var checkedradio=$("input:checked[rec='true']");
                            var arr=[];
                           for(var i=0;i<checkedradio.length;i++){
                               arr.push(checkedradio[i].id.substr(2,20));
                           }
                           <%--alert("<%=cpath%>/jeda/user/delete?userID=" + arr + "&pi=${pageindex}&org=${orgbean.orgId}");--%>
                            window.location.href = "<%=cpath%>/jeda/user/delete?userID=" + arr + "&pi=${pageindex}&org=${orgbean.orgId}";
                        });
                        $("#edit").click(function edit()
                        {
                            var checkednum = findSelectList();

                            if (checkednum.length < 1) {
                                alert("请选择一条数据。");
                            } else if (checkednum.length > 1) {
                                alert("每次只能修改一条数据。");
                            } else {
                                location.href = "<%=cpath%>/jeda/user/edit?r=<c:out value="${returnview}"/>&userID=" + checkednum[0].id.substr(2, 20);
                            }
                        });
                        //重置密码
                        $("#reset").click(function() {
                            var a="123";
                            var checkednum = findSelectList();
                            var b=checkednum[0].id.substr(2, 20);

                            if(b==null||b==undefined){
                                return "请选择需要重置密码的用户";
                            }
                            if(confirm('确实要为用户：'+b+"重置密码吗？"))
                                if(b!=""&& b!=null){
                                    $.ajax({
                                        type : 'POST',
                                        url : '<%=cpath%>/jeda/user/resetpwd',
                                        data : {
                                            pwd:a,
                                            userid:b
                                        },
                                        success : function(i) {
                                            alert("为用户："+b+" 重置密码成功。");

                                        }
                                    });
                                }
                        });
                        $("#view").click(function view()
                        {
                            var checkednum = findSelectList();

                            if (checkednum.length < 1) {
                                alert("请选择一条数据。");
                            } else if (checkednum.length > 1) {
                                alert("每次只能查看一条数据。");
                            } else {
                                location.href = "<%=cpath%>/jeda/user/view?r=<c:out value="${returnview}"/>&userId=" + checkednum[0].id.substr(2, 20);
                            }
                        });
                        $("#userable").click(function able()
                        {
                            var checkednum = findSelectList();
                            var userenabled = checkednum[0].id.substr(0, 1);
                            if (checkednum.length < 1) {
                                alert("请选择一条数据。");
                            } else if (checkednum.length > 1) {
                                alert("每次只能激活一条数据。");
                            } else {
                                if (userenabled === 1) {
                                    alert("此用户已经处在启用状态，不需再次启用。");
                                } else {
                                    location.href = "<%=cpath%>/jeda/user/able?e=1&r=<c:out value="${returnview}"/>&userId=" + checkednum[0].id.substr(2, 20) + "&pi=${pageindex}&org=${orgbean.orgId}";
                                    alert("启用成功");
                                }
                            }
                        });
                        $("#userunable").click(function unable()
                        {
                            var checkednum = findSelectList();

                            var userenabled = checkednum[0].id.substr(0, 1);
                            if (checkednum.length < 1) {
                                alert("请选择一条数据。");
                            } else if (checkednum.length > 1) {
                                alert("每次只能停用一条数据。");
                            } else {

                                if (userenabled === 0) {
                                    alert("此用户已经处在禁用状态，不需再次禁用。");
                                } else {
                                    location.href = "<%=cpath%>/jeda/user/able?e=0&r=<c:out value="${returnview}"/>&userId=" + checkednum[0].id.substr(2, 20) + "&pi=${pageindex}&org=${orgbean.orgId}";
                                    alert("停用成功");

                                }
                            }
                        });
                        $("#new").click(function edit()
                        {
                            if (${orgbean==null}) {
                                alert("请为要建立的用户选择所在机构。");
                            } else {
                                location.href = "<%=cpath%>/jeda/user/new?r=<c:out value="${returnview}"/>&orgId=<c:out value="${orgbean.orgId}"/>";
                                            }
                                        });
                                        $.fn.bondTableHead();
                                        $("span.tableheadOrder").click(function (evn) {

                                            var column = $(evn.target).parent().attr("column");
            <c:if test="${ordercase==''}">
                                            column += " ASC";
            </c:if>
            <c:if test="${ordercase!=''}">
                                            if (column + " ASC" === '<c:out value="${ordercase}"/>') {
                                                column += " DESC";
                                            } else if (column + " DESC" === '<c:out value="${ordercase}"/>') {
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
                                                ss[1] === "ASC" ? '<i class="ui-icon ui-icon-triangle-1-n" style="float:left"></i>' :
                                                '<i class="ui-icon ui-icon-triangle-1-s" style="float:left"></i>');
            </c:if>


                                        $("#tree1").treeview({
                                            url: "<%=cpath%>/jeda/org/gettree",
                                            persist: "cookie"
                                        });

                                    });
            function selectradio(obj){
                var temp=$(obj).parents('tr').find('input');
                var bool = temp.prop("checked");
                if(bool){
                    $(obj).parents('tr').find('input').attr('checked',false);
                }else{
                    $(obj).parents('tr').find('input').attr('checked',true);
                }
            }

        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">

                    <a  id="new" role="button" data-toggle="modal" class="btn"><i class="icon-plus"></i> 创建用户</a>
                    <a  id="delcheck" role="button" data-toggle="modal" class="btn">删除所选</a>
                    <button id="edit" class="btn">修改</button>
                    <button id="view" class="btn">查看</button>
                    <button id="userable" class="btn">启用</button>
                    <button id="userunable" class="btn">停用</button>
                    <a href="#setroleDiag" role="button" data-toggle="modal" class="btn">设置角色</a>
                    <a href="#setorgDiag" role="button" data-toggle="modal" class="btn">设置机构</a>
                    <a href="#queryFormDiag" role="button" data-toggle="modal" class="btn">查询</a>
                    <button name="reset" id="reset"class="btn btn-primary"> 初始化密码</button>
                    <%--<a class="btn" onclick="javascript:exportExcel()">导出</a>--%>


                    <!--                    <a href="#hideorglist" onclick="hiddenOrglist()" role="button" data-toggle="modal" class="btn">隐藏机构树</a>-->
            <%--        <a class='space_btn'>当前机构：
                        <c:if test="${orgbean==null}">全部</c:if>
                        <c:if test="${orgbean!=null}"><c:out value="${orgbean.orgName}"/></c:if>

                        </a>--%>
                    </div> 
                    <div style="margin-bottom:10px">
                    <table id="resultTable" class="well table resultTable  table-striped table-hover">
                        <thead>
                            <tr style="background-color:#ffffff">
                                <th style="width:20px"></th>
                                <th style="width:50px">#</th>
                                <th inorder="true" insel="true" column="USER_ID">
                                    <span class="tableheadOrder">用户ID</span>
                                </th>
                                <th inorder="true" insel="true" column="LOGIN_NAME">
                                    <span class="tableheadOrder">登录名</span>
                                </th>
                                <th inorder="true" insel="true" column="USER_NAME">
                                    <span class="tableheadOrder">用户名</span>
                                </th>
                         <%--       <th inorder="true" insel="true" column="USER_ENABLED">
                                    <span class="tableheadOrder">状态</span>
                                </th>--%>

                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${result!=null}">
                            <c:forEach var="user" items="${result}" varStatus="status">
                                <tr  >
                                    <td style="cursor:pointer;height:30px">
                                        <input rec="true" id="<c:out value="${user.userEnabled}"/>_<c:out value="${user.userId}"/>" type="checkbox">  
                                    </td>
                                    <td style="width:50px" onclick="selectradio(this)">
                                        <c:out value="${(pageindex-1)*pagesize+1+status.index}"/> 
                                    </td>
                                    <td column="USER_ID" onclick="selectradio(this)"><c:out value="${user.userId}"/></td>
                                    <td column="LOGIN_NAME" onclick="selectradio(this)"><c:out value="${user.loginName}"/></td>
                                    <td column="USER_NAME" onclick="selectradio(this)"><c:out value="${user.userName}"/></td>
                                    <%--<td column="USER_ENABLED" onclick="selectradio(this)">
                                        <c:if test="${user.userEnabled==0}">
                                            禁用
                                        </c:if>
                                        <c:if test="${user.userEnabled==1}">
                                            启用
                                        </c:if>
                                    </td>--%>
                                </tr>
                                <% index++;%>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
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

                <div class="modal small hide fade" id="setroleDiag" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">选择用户角色</h3>
                    </div>
                    <div class="modal-body">
                        <table  class="table" id='rolelisttable'></table>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                        <button id='roleselectbtn' class="btn " data-dismiss="modal">确认</button>
                    </div>
                </div>

                <div class="modal small hide fade" id="setorgDiag" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">选择组织机构</h3>
                    </div>
                    <div class="modal-body">
                        <span id="curOrcSelected"></span>
                        <iframe width="100%" height="300px" src="<%=cpath%>/jeda/org/orgtree">
                        </iframe>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                        <button id='orgselectbtn' class="btn " data-dismiss="modal">确认</button>
                    </div>
                </div>

                <div class="modal small hide fade" id="queryFormDiag" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">查询用户信息</h3>
                    </div>

                    <div class="modal-body">
                        <form id="queryForm" method="post">
                            <label>用户名</label>
                            <input name="userName" type="text" value="" class="input-xlarge">
                            <label>登录名</label>
                            <input name="loginName" type="text" value="" class="input-xlarge">
                            <label>用户ID</label>
                            <input name="userID" type="text" value="" class="input-xlarge">
                        </form> 
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                        <button onclick="$('#queryForm').submit()" class="btn " data-dismiss="modal">确认</button>
                    </div>
                </div>
                <div class="modal small hide fade" style="height:450px" id="columnSelected" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
