<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="bas.jeda.controller.Constants"%>
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
                $("#userBirthday").datepicker({
                    inline: true
                });
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
                $("#save").click(function () {
                    //校验必输项
//                  if(!beforeSubmit()){
//                        return false;
//                    }
                    var userform = $("#tab");
                    //将form数据组装成json对象的juery扩展  
                    var user = userform.serializeObject();
                    //将json对象转成String是用的json2（http://www.json.org/js.html）的JSON.stringify  
                    var jsonuser = JSON.stringify(user);
                    $.ajax({
                        type: 'POST',
                        contentType: 'application/json',
                        url: '<%=cpath%>/jeda/user/new',
                        data: jsonuser,
                        dataType: 'json',
                        success: function (user) {
                            alert("用户建立成功。");
                            location.href = "<%=cpath%>/jeda/user/query?pi=1&r=${returnview}&o=&userId=" + user.userId+"&org=${orgId}";//
                        }
                    });
                });
                $("#userId").blur(function () {
                    var a = $("input[name='userId']").val();
                    if (a != "" && a != null) {
                        $.ajax({
                            type: 'POST',
                            url: '<%=cpath%>/jeda/user/checkid',
                            data: {
                                a:a,
                            },
                            success: function (i) {
                                if (i == 1) {
                                    alert("已存在相同ID，请输入一个新的ID。");
                                }
                            }
                        });
                    }
                });
            });
        </script>
    </head>
    <body >
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">
                    <button name="save" id="save"class="btn btn-primary"><i class="icon-save"></i> 保存</button>
                    <c:if test="${returnview!=''}">
                        <a href="<%=cpath%>/r?r=<c:out value="${returnview}"/>" data-toggle="modal" class="btn">返回</a>    
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
                                <table class="well table resultTable table-hover"> 
                                    <tr>
                                        <td>用户ID</td>
                                        <td><input type="text" name="userId" id="userId" value="" valType="required" msg="<font color=red>*</font>用户ID不能为空"  class="input-xlarge"> </td>
                                        <td>用户姓名</td>
                                        <td><input type="text" name="userName" value="" valType="CHINESE"  msg="<font color=red>*</font>请输入" class="input-xlarge"></td>
                                    </tr>
                                    <tr>
                                        <td>性别</td>
                                        <td>
                                            <select id="userGender" name="userGender">
                                                <option value="M">男</option>
                                                <option value="F">女</option>
                                            </select>
                                        </td>
                                        <td>Email</td>
                                        <td>
                                            <input type="text"  name="userEmail" value=""  valType="MAIL"  msg="<font color=red>*</font>请输入正确格式" class="input-xlarge">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>职务</td>
                                        <td>
                                            <input type="text"  name="positionId" value="" class="input-xlarge">
                                        </td>
                                        <td>生日</td>
                                        <td>
                                            <input type="text" id="userBirthday" name="userBirthday" id="userBirthday" value=""  class="input-xlarge">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>电话</td>
                                        <td>
                                            <input type="text" name="userTel" value="" valType="TEL" msg="<font color=red>*</font>请输入正确格式" class="input-xlarge">
                                        </td>
                                        <td>手机</td>
                                        <td>
                                            <input type="text" name="userMobile" value="" valType="MOBILE" msg="<font color=red>*</font>请输入正确格式" class="input-xlarge">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>地址</td>
                                        <td>
                                            <input type="text" name="userAddress" value="" class="input-xlarge"> 
                                        </td>
                                        <td>邮编</td>
                                        <td>
                                            <input type="text" name="userPost" value="" class="input-xlarge">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>描述</td>
                                        <td>
                                            <input type="text" name="userDescription" value="" class="input-xlarge">
                                        </td>
                                        <td>所属机构</td>
                                        <td>
                                            <input type="text" value="${orgName}" class="input-xlarge">
                                        </td>
                                    </tr>
                                </table>
                                
                                <input  name="orgId" id="orgId" value="${orgId}" type="hidden" class="input-xlarge"> 
                            </form>
                        </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
