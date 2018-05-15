
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bas.jeda.controller.Constants"%>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="../commonhead.jsp"></jsp:include>
         <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
         
         <script >
                //!window.jQuery&&document.write(""+"");
                function findtopFrame(win){
                    if(win.frameElement!=null){
                        return findtopFrame(win.parent);
                    }
                    return win;
                }
                var twin=findtopFrame(this);
                if(twin!=this){
                    window.parent.location="<%=cpath%>/login";
                }

                $(document).ready(function () {
                    if (window.screen.width <= 1280) {
                        $("#__01").hide();
                        $("#__02").show();
                    } else {
                        $("#__02").hide();
                        $("#__01").show();
                    }
                    /*登录*/
                     $("#btn_login").click(function(){
                        var userName = document.getElementById("u");
                    var pwd      = document.getElementById("p");
                    var ems_u    = document.getElementById("ems_u");
                     
                     if(userName===null||userName.value===""){
                       //  ems_u.innerHTML = "用户名不能为空";
                       alert("用户账户不能为空!");
                       userName.focus();
                         return false;
                     }
                     if(pwd===null||pwd.value===""){
                      
                       alert("密码不能为空!");
                       pwd.focus();
                         return false;
                     }
                     $('#form1').submit();   
                });
                 /*登录第二个table*/
                     $("#btn_login2").click(function(){
                        var userName = document.getElementById("u2");
                        var pwd      = document.getElementById("p2");
                     
                     if(userName===null||userName.value===""){
                       //  ems_u.innerHTML = "用户名不能为空";
                       alert("用户账户不能为空!");
                       userName.focus();
                         return false;
                     }
                     if(pwd===null||pwd.value===""){
                      
                       alert("密码不能为空!");
                       pwd.focus();
                         return false;
                     }
                     $('#form2').submit();   
                });
                
                });
                
     
               
               
            </script>
        </head>  

        <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
        <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
        <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
        <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
        <!--[if (gt IE 9)|!(IE)]><!--> 
        <body style="background-color: #285598"> 
            <!--<![endif]-->
            <table id="__01" style="margin-left: auto;margin-right: auto" width="1300" height="800" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="3">
                        <img src="images/login/login_01.jpg" width="1300" height="353" alt=""></td>
                </tr>
                <tr>
                    <td rowspan="2">
                        <img src="images/login/login_02.jpg" width="934" height="447" alt=""></td>
                    <td style="width:280px;height:219px;background-image: url(images/login/login_bk.jpg); ">
                        <div>
                            <form id="form1" method="post" action="<%=cpath%>/login">
                            <label id="l_userName">用户账户
                               <c:if test="${EMSG!=null}"><span style="color: red">(${EMSG})</span></c:if>
                            </label>
                            <input id="u" type="text" name="u" value="${userName}">
                            <label>用户密码</label>
                            <input id="p" type="password" name="p"><br>
                            <input id="btn_login" type="submit"  class="btn btn-primary"  value="登录">

                        </form>

                    </div>
                </td>
                <td rowspan="2">
                    <img src="images/login/login_04.jpg" width="86" height="447" alt=""></td>
            </tr>
            <tr>
                <td>
                    <img src="images/login/login_06.jpg" width="280" height="228" alt=""></td>
            </tr>
        </table>

        <table id="__02" width="1000" style="margin-left: auto;margin-right: auto"  height="649" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="3">
                    <img src="images/login/login1024_01.jpg" width="1000" height="286" alt=""></td>
            </tr>
            <tr>
                <td rowspan="2">
                    <img src="images/login/login1024_02.jpg" width="704" height="363" alt=""></td>
                <td style="width:228px;height:179px;background-image: url(images/login/login_bk.jpg); ">
                    <div>
                            <form id="form2" method="post" action="<%=cpath%>/login">
                            <label id="l_userName2">用户账户
                               <c:if test="${EMSG!=null}"><span style="color: red">(${EMSG})</span></c:if>
                            </label>
                            <input id="u2" type="text" name="u" value="${userName}">
                            <label>用户密码</label>
                            <input id="p2" type="password" name="p"><br>
                            <input id="btn_login2" type="submit"  class="btn btn-primary"  value="登录">
                        </form>

                    </div>
                
                </td>
                <td rowspan="2">
                    <img src="images/login/login1024_04.jpg" width="68" height="363" alt=""></td>
            </tr>
            <tr>
                <td>
                    <img src="images/login/login1024_06.jpg" width="228" height="184" alt=""></td>
            </tr>
        </table>
       


    </body>
</html>

