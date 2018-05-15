<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    var disableUserSelectList = function () {
        $("#sourceOptions option").remove();
        $("#selectedOptions option").remove();
        $("#userselectlisttable").hide();
        $("#sendtolable").hide();
    }
    var getSelectUserList = function () {
        var result = new Array();
        $("#sourceOptions option").each(function () {
            result.push($(this).attr("value"));
        });
        return result;
    }
    var getSelectedUserList = function () {
        var result = new Array();
        $("#selectedOptions option").each(function () {
            result.push($(this).attr("value"));
        });
        return result;
    }
    var reloadUserSelectedList = function (cfg) {
        //在这里判断发送时用户列表是否需要显示，如果不需要直接返回
        if(cfg.role ==null || cfg.role ==""){
            $("#userselectlisttable").hide();
            $("#sendtolable").hide();
            return ;
        }
        $("#sendtolable").show();
        $("#userselectlisttable").show();
        var url = "<%=cpath%>/jeda/user/selectsend2user.json?"
                + (cfg.role ? ("role=" + cfg.role + "&") : "")
                + (cfg.org ? ("org=" + cfg.org) : "");
        if (cfg.assignMutil) {
            $("#sourceOptions").attr("multiple", "true");
        } else {
            $("#sourceOptions").removeAttr("multiple");
        }


        $.ajax({
            type: 'GET',
            url: url,

            dataType: 'json',
            success: function (data) {
                $("#sourceOptions option").remove();
                $("#selectedOptions option").remove();
                for (var i = 0; i < data.result.length; i++) {
                    $("#sourceOptions").append(
//                            "<option value='" + data.result[i].USER_ID + "'>" + data.result[i].USER_NAME + "(" + data.result[i].ORG_NAME + "," + data.result[i].POSITION_ID + ")</option>"
                            "<option value='" + data.result[i].USER_ID + "'>" + data.result[i].USER_NAME + "(" + data.result[i].ORG_NAME +  ")</option>"
                            );
                }

            },
            error: function (user) {
                alert(user);
            }
        });

        //判断selectedOptions中是否已经包含userid
        var findAlreadyinUser = function (userid) {
            var has = false;
            $("#selectedOptions option").each(function () {
                if ($(this).attr("value") == userid) {
                    has = true;
                    return;
                }
            });
            return has;
        };
        var addUser2List = function (vlue, txt) {
            if (!cfg.assignMutil) {
                $("#selectedOptions option").remove();
                $("#selectedOptions").append("<option value='" + vlue + "'>" + txt + "</option>");
                return;
            }
            if (!findAlreadyinUser(vlue)) {
                $("#selectedOptions").append("<option value='" + vlue + "'>" + txt + "</option>");
            }
        };
        //添加所有用户
        $("#addAllUser2Select").click(function () {
            $("#sourceOptions option").each(function () {
                var userid = $(this).attr("value");
                addUser2List(userid, $(this).html());

            });
        });
        //删除所有用
        $("#removeAllUser2Select").click(function () {

            $("#selectedOptions option").remove();
        });
        var addSelectedUser2List = function () {
            $("#sourceOptions option:selected").each(function () {
                var userid = $(this).attr("value");
                addUser2List(userid, $(this).html());

            });
        };
        //添加选定的用户
        $("#addUser2Select").click(addSelectedUser2List);
        $("#sourceOptions").dblclick(addSelectedUser2List);
        //删除选定的用户
        $("#removeUser2Select").click(function () {
            $("#selectedOptions option:selected").each(function () {
                $(this).remove();
            });
        });
    }
</script>

<label id="sendtolable" class="control-label approveformLabelw120px" for='sendto'>发送至：</label>
<table id="userselectlisttable" class="userselectCSS">

    <TR>
        <TD>
            <select multiple="true" id="sourceOptions" property="sourceOptions" 
                    size="5" >               
            </select>
        </TD>
        <TD class="buttonCol">
            <%--<button id="addAllUser2Select" class="btn userBtn" type="button">全部添加</button><br/>--%>
            <button id="addUser2Select" class="btn userBtn" type="button">添加</button><br/>
            <button id="removeUser2Select" class="btn userBtn" type="button">删除</button><br/>
            <%--<button id="removeAllUser2Select" class="btn userBtn" type="button">全部删除</button>                                                 --%>
        </TD>
        <TD>

            <select   id="selectedOptions" property="selectedOptions" multiple="multiple" size="5" >

            </select>

        </TD>
    </TR>
</table>