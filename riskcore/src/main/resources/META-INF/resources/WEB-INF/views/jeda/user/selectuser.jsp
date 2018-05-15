 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String cpath = request.getContextPath();
    int index = 1;
    String taskid=request.getParameter("taskid");
    String flowid=request.getParameter("flowid");
    String spyj=request.getParameter("spyj");
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
            }
            var addUser, removeuser, removealluser, updateuserrole;
            var usersCookie;
            $(document).ready(function () {
            <c:if test="${usersList!=null}">
                $.cookie('userCookie', '');
            </c:if>
                userCookie = $.cookie('userCookie');
                if (userCookie) {
                    var ss = userCookie.split(";");
                    for (var i = 0; i < ss.length; i++) {
                        if (ss[i] != "") {
                            var u = ss[i].split(",");
                            var userid = u[0];
                            var username = u[1];
                            $(".usrList").append('<span   onclick="removeuser(\'' + userid + '\')" class="usrItem" uname="' + username + '" id="' + userid + '">' + username + '<a style="padding-left:3px" href="#" onclick="removeuser(\'' + userid + '\')"><sup>X</sup></a></span>');
                        }
                    }
                }
                //重载goPage方法，在转页前保存选定的用户。
                goPage = function (pageIndex) {

                    window.location = "?pi=" + pageIndex;
                };
                $("#searchBtn").click(function () {
                    $('#queryForm').submit();
                });
                removealluser = function () {
                    $(".usrList").empty();
                    updateCookie();
                };
                removeuser = function (userid) {
                    $("span[id='" + userid + "']").remove();
                    updateCookie();
                };
                var updateCookie = function () {
                    var s = $(".usrItem");
                    var us = "";
                    for (var i = 0; i < s.length; i++) {
                        us += $(s[i]).attr("id") + "," + $(s[i]).attr("uname") + ";";
                    }

                    $.cookie('userCookie', us);

                };
                addUser = function (userid, username) {
                    if ($("span[id='" + userid + "']").length == 0) {
                        $(".usrList").append('<span   onclick="removeuser(\'' + userid + '\')" class="usrItem" uname="' + username + '" id="' + userid + '">' + username + '<a style="padding-left:3px" href="#" onclick="removeuser(\'' + userid + '\')"><sup>X</sup></a></span>');
                    }
                    updateCookie();
                };
                updateuserrole = function () {
                    var s = $(".usrItem");
                    var us = "";
                    for (var i = 0; i < s.length; i++) {
                        us += $(s[i]).attr("id") + ",";
                    }
                    $("#users").attr("value", us);
                    updateF.submit();
                };
            <c:if test="${usersList!=null}">
                <c:forEach var="uitem" items="${usersList}">
                if ($("span[id='<c:out value="${uitem.userId}"/>']").length == 0) {
                    $(".usrList").append('<span   onclick="removeuser(\'<c:out value="${uitem.userId}"/>\')" class="usrItem" uname="<c:out value="${uitem.userName}"/>" id="<c:out value="${uitem.userId}"/>"><c:out value="${uitem.userName}"/><a style="padding-left:3px" href="#" onclick="removeuser(\'<c:out value="${uitem.userId}"/>\')"><sup>X</sup></a></span>');
                }
                </c:forEach>

                updateCookie();
            </c:if>
                //<c:out value="${role}"/>
                //<c:out value="${posturl}"/>
            <c:if test="${not empty posturl}">
                $.cookie('postURL', '<c:out value="${posturl}"/>');
            </c:if>
            <c:if test="${not empty role}">
                $.cookie('role', '<c:out value="${role}"/>');
            </c:if>
                $("#roles").attr("value", $.cookie('role'));
                $("#updateF").attr("action", $.cookie('postURL'));

            });

        </script>
        <style>
            .usrList{
                padding: 15px 15px 15px 15px;


            }
            .usrItem{
                padding: 3px 3px 3px;border:1px solid #666666;background-color: #eeeeee;cursor:pointer;
                line-height: 40px
            }
        </style>
    </head>
    <body>
        <form id="updateF" method="post" action="">
            <input type="hidden" name="users" id="users" value=""/>
            <input type="hidden" name="roles" id="roles" value=""/>
            <input type="hidden" name="taskid" id="taskid" value="<%=taskid%>"/>
            <input type="hidden" name="flowid" id="flowid" value="<%=flowid%>"/>
            <input type="hidden" name="spyj" id="spyj" value="<%=spyj%>"/>
        </form>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">
                    <form id="queryForm" method="post">
                        <label style="float: left">用户名/登录名或用户ID：</label>
                        <input style="float: left;width:150px" name="userName" type="text"  value="" class="input-xlarge">                        
                    </form> 
                    <a id='searchBtn' role="button" data-toggle="modal" class="btn">查询</a>
                    <a onclick="removealluser()" role="button" data-toggle="modal" class="btn">清除选定的用户</a>
                    <a onclick="updateuserrole()" role="button" data-toggle="modal" class="btn">确定</a>
                    <a onclick="window.close()" role="button" data-toggle="modal" class="btn" >取消</a>
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;双击列表选定用户：
                <div class="usrList">
                </div>
                <table id="resultTable" style="width:620px;margin-left: 20px;margin-right:auto" class="well resultTable  table-striped table-hover">
                    <thead>
                        <tr>
                            <th style="width:50px">#</th>
                            <th inorder="true" insel="true" column="USER_ID">
                                <span class="tableheadOrder">用户ID</span>
                            </th>
                            <th inorder="true" insel="true" column="USER_NAME">
                                <span class="tableheadOrder">用户名</span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${result}" varStatus="status">
                            <tr style="cursor:pointer" ondblclick="addUser('${user.userId}', '${user.userName}')">

                                <td style="width:50px">
                                    <c:out value="${(pageindex-1)*pagesize+1+status.index}"/> 
                                </td>
                                <td column="USER_ID"><c:out value="${user.userId}"/></td>

                                <td column="USER_NAME"><c:out value="${user.userName}"/></td>

                            </tr>
                            <% index++;%>
                        </c:forEach>
                    </tbody>
                </table>

                <jsp:include page="../pagefoot.jsp"></jsp:include>


            </div>
        </div>

        <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
