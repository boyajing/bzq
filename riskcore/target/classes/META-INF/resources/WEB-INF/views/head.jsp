<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String cpath = request.getContextPath();
%>
<head>
    <script type="text/javascript" src="<%=cpath%>/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
        $("#s_zxrs").click(function(){
            location.reload();
        });

        var int=self.setInterval("clock()",300000);
        function clock() {
            $.ajax({
                url: '<%=cpath%>/pageController/updateUser',
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    $("#countuser").find("span").remove();
                    $("#countuser").append("<span>"+data+"</span>");

                }
            })
        }
    </script>
</head>

<div class="navbar" style="background: url(<%=cpath%>/images/banner/banner_02.jpg);">
    <div  style="height:73px;background: url(<%=cpath%>/images/banner/banner.jpg);background-repeat:no-repeat">
        <div style="height: 40px"></div>
        <div style="float: right;  text-align: right;  color:white;padding-right: 20px">
            <img src="<%=cpath%>/images/icons/user.gif" id="testImg" onclick="takeStatus()">
            &nbsp;&nbsp;欢迎
            <span style="color: yellow">${CUSER.userName }</span><input type="hidden" id="userId" value="${CUSER.userId}">
            &nbsp;&nbsp;|&nbsp;&nbsp; 所属机构:
            <span id="s_orgName">${orgName}</span>
            &nbsp;&nbsp;|&nbsp;&nbsp;系统日期:
            <span id="s_sysDate">${systemDate}</span>
            &nbsp;&nbsp;|&nbsp;&nbsp;在线人数:
            <span id="s_zxrs" style="cursor:pointer" onclick="return window.open ('<%=cpath%>/pageController/viewUser','newwindow','height=400,width=800,top=200,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');"><span id="countuser"><span>${num}</span></span>&nbsp;人</span>
           <%-- &nbsp;&nbsp;|&nbsp;&nbsp;客户地址:--%>
           <%-- <%= request.getRemoteAddr()  %>--%>
            &nbsp;&nbsp;
            <a tabindex="-1" href="<%=cpath%>/logout" onclick="return confirm('您将退出系统!')" style="color:white"><img src="<%=cpath%>/images/icons/quit.gif">退出</a>
        </div>
    </div>
</div>