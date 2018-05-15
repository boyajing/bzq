<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<head>
    <title>上会流程</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
     <%--<script type="text/javascript" src="<%=path%>/js/date/jquery-1.10.1.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
    <script type="text/javascript"  src="<%=path%>/js/bootstrap/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/mypage.css">
    <script type="text/javascript">
        //初始化
        $(function(){
            $('th').attr('nowrap','nowrap');
            $("table").attr("class", "well table resultTable table-hover");
        });
    </script>
</head>
<body>
<div style="height:10px"></div>
<div id="myTabContent" class="tab-content">
    <div id="base">
        <c:choose>
            <c:when test="${selectinput eq 'meetinginfoShow'}">
                <iframe id="iframe_base" frameBorder=0 scrolling=hidden width="100%"  height="800px" src="<%=path%>/meetingflow/meetinginfoShow?meetingid=${meetingid}"></iframe>
            </c:when>
            <c:when test="${selectinput eq 'weiyuanapprove'}">
                <iframe id="iframe_base" frameBorder=0 scrolling=hidden width="100%"  height="800px" src="<%=path%>/meetingflow/weiyuanapprove?meetingid=${meetingid}"></iframe>
            </c:when>
            <c:when test="${selectinput eq 'mishuapprove'}">
                <iframe id="iframe_base" frameBorder=0 scrolling=hidden width="100%"  height="800px" src="<%=path%>/meetingflow/mishuapprove?meetingid=${meetingid}"></iframe>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>

