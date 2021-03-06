<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String cpath = request.getContextPath();
%>
<html>
<HEAD>
    <TITLE>选择人员</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <link rel="shortcut icon" href="<%=cpath%>/images/icons/ico.ico" type="image/x-icon" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="" >
    <link rel="stylesheet" type="text/css" href="<%=cpath%>/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=cpath%>/css/theme.css">
    <script src="<%=cpath%>/js/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="<%=cpath%>/js/openr.js"  type="text/javascript"></script>
    <script type="text/javascript">

        var role =  "<c:out value='${role }'/>";
        var org =  "<c:out value='${org }'/>";

        function getSelectedUserId() {
            var userids ="";

            $("#selectedOptions option").each(function () {
                userids += $(this).val();
            });
            return userids;
        }
      function getSelectedUserNames() {
            var usernames ="";

            $("#selectedOptions option").each(function () {
                usernames += $(this).text() +",";
            });
            return usernames;
        }

        $(function(){
            var url = "<%=cpath%>/jeda/user/selectsend2user.json?"
                    + (role ? ("role=" + role + "&") : "")
                    + (org ? ("org=" + org) : "");


            $.ajax({
                type: 'GET',
                url: url,
                dataType: 'json',
                success: function (data) {
                    $("#sourceOptions option").remove();
                    $("#selectedOptions option").remove();
                    for (var i = 0; i < data.result.length; i++) {
                        $("#sourceOptions").append(
                                "<option value='" + data.result[i].USER_ID + "'>" + data.result[i].USER_NAME + "(" + data.result[i].ORG_NAME +")</option>"
                        );
                    }

                },
                error: function (user) {
                    alert(user);
                }
            });


            $("#addUser2Select").click(function(){
                $("#sourceOptions option:selected").each(function () {
                    $("#selectedOptions").empty();
                    document.getElementById("selectedOptions").options.add(new Option($(this).html(), $(this).val()));
                });
            });
            $("#sourceOptions").dblclick(function(){
                $("#sourceOptions option:selected").each(function () {
                    $("#selectedOptions").empty();
                    document.getElementById("selectedOptions").options.add(new Option($(this).html(), $(this).val()));
                });
            })


            $("#removeUser2Select").click(function(){
                $("#selectedOptions option:selected").each(function () {
                   $(this).remove();
                });

            });

            $("#selectedOptions").dblclick(function(){
                $("#selectedOptions option:selected").each(function () {
                     $(this).remove();
                });
            });
            $(".userBtn").dblclick(function(){
                return false;
            });

        });
    </script>
</HEAD>
<BODY leftmargin="0" topmargin="0" bgcolor="#C6D7EF">

<table id="userselectlisttable" class="userselectCSS">
    <TR>
        <TD>
            <select multiple="true" id="sourceOptions" property="sourceOptions"
                    size="4" >
            </select>
        </TD>
        <TD class="buttonCol">
            <%--<button id="addAllUser2Select" class="btn userBtn" type="button">全部添加</button><br/>--%>
            <button id="addUser2Select" class="btn userBtn" type="button" >添加</button><br/>
            <button id="removeUser2Select" class="btn userBtn" type="button">删除</button><br/>
            <%--<button id="removeAllUser2Select" class="btn userBtn" type="button">全部删除</button>--%>
        </TD>
        <TD>

            <select   id="selectedOptions" property="selectedOptions" multiple="multiple" size="4" >

            </select>

        </TD>
    </TR>

</table>

</BODY>
</html>

