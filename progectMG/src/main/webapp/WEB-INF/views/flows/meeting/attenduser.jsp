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
        function getSelectedUserIds() {
            var userids ="";

            $("#selectedOptions_1 option").each(function () {
                userids += $(this).val() +",";
            });
            return userids;
        }
        function getSelectedUserNames() {
            var usernames ="";

            $("#selectedOptions_1 option").each(function () {
                usernames += $(this).text() +",";
            });
            return usernames;
        }

        $(function(){
            var cfg = {};
            var url = "<%=cpath%>/jeda/user/selectsend2user.json?"
                    + (cfg.role ? ("role=" + cfg.role + "&") : "")
                    + (cfg.org ? ("org=" + cfg.org) : "");


            $.ajax({
                type: 'GET',
                url: url,

                dataType: 'json',
                success: function (data) {
                    $("#sourceOptions_1 option").remove();
                    $("#selectedOptions_1 option").remove();
                    for (var i = 0; i < data.result.length; i++) {
                        $("#sourceOptions_1").append(
                                "<option value='" + data.result[i].USER_ID + "'>" + data.result[i].USER_NAME + "(" + data.result[i].ORG_NAME + ")</option>"
                        );
                    }

                },
                error: function (user) {
                    alert(user);
                }
            });

            $("#addAllUser2Select").click(function(){
                $("#sourceOptions_1 option").each(function () {
                    document.getElementById("selectedOptions_1").options.add(new Option($(this).html(), $(this).val()));
                });
                $("#sourceOptions_1").empty();
            });

            $("#addUser2Select").click(function(){
                $("#sourceOptions_1 option:selected").each(function () {
                    document.getElementById("selectedOptions_1").options.add(new Option($(this).html(), $(this).val()));
                    $(this).remove();
                });

//                document.getElementById("selectedOptions_1").options.add(new Option($(this).html(), $(this).val()));
//                $(this).remove();
            });
            $("#sourceOptions_1").dblclick(function(){
                $("#sourceOptions_1 option:selected").each(function () {
                    document.getElementById("selectedOptions_1").options.add(new Option($(this).html(), $(this).val()));
                    $(this).remove();
                });
            })


            $("#removeUser2Select").click(function(){
                $("#selectedOptions_1 option:selected").each(function () {
                    document.getElementById("sourceOptions_1").options.add(new Option($(this).html(), $(this).val()));
                    $(this).remove();
                });
//                document.getElementById("sourceOptions_1").options.add(new Option($(this).html(), $(this).val()));
//                $(this).remove();
            });

            $("#selectedOptions_1").dblclick(function(){
                $("#selectedOptions_1 option:selected").each(function () {
                    document.getElementById("sourceOptions_1").options.add(new Option($(this).html(), $(this).val()));
                    $(this).remove();
                });
            });

            $("#removeAllUser2Select").click(function(){
                $("#selectedOptions_1 option").each(function () {
                    document.getElementById("sourceOptions_1").options.add(new Option($(this).html(), $(this).val()));
                });
                $("#selectedOptions_1").empty();
            });


        });



//        function removeAll() {
//            $("#selectedOptions option").each(function () {
//                document.getElementById("sourceOptions").options.add(new Option($(this).html(), $(this).val()));
//            });
//            $("#selectedOptions").empty();
//        }
//        function addAll() {
//            $("#sourceOptions option").each(function () {
//                document.getElementById("selectedOptions").options.add(new Option($(this).html(), $(this).val()));
//            });
//            $("#sourceOptions").empty();
//        }
        function removetosource(data) {
            $("#selectedOptions_1 :selected").each(function () {
                document.getElementById("sourceOptions_1").options.add(new Option($(this).html(), $(this).val()));
                $(this).remove();
            });
        }
        function addtoselected() {
            $("#sourceOptions_1 :selected").each(function () {
                document.getElementById("selectedOptions_1").options.add(new Option($(this).html(), $(this).val()));
                $(this).remove();
            })
        }
    </script>
</HEAD>
<BODY leftmargin="0" topmargin="0" bgcolor="#C6D7EF">

<table id="userselectlisttable1" class="userselectCSS">
    <TR>
        <TD>
            <select multiple="true" id="sourceOptions_1" property="sourceOptions"
                    size="15" >
            </select>
        </TD>
        <TD class="buttonCol">
            <button id="addAllUser2Select" class="btn userBtn" type="button">全部添加</button><br/>
            <button id="addUser2Select" class="btn userBtn" type="button" >添加</button><br/>
            <button id="removeUser2Select" class="btn userBtn" type="button">删除</button><br/>
            <button id="removeAllUser2Select" class="btn userBtn" type="button">全部删除</button>
        </TD>
        <TD>

            <select   id="selectedOptions_1" property="selectedOptions" multiple="multiple" size="15" >

            </select>

        </TD>
    </TR>

</table>

</BODY>
</html>

