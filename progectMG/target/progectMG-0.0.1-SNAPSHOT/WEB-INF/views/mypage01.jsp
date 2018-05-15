<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.nantian.utils.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xiongyiwu
  Date: 2016/4/20
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title></title>
</head>
<script type="text/javascript">
    $(function () {
        $('#' + '${pageindex01}').addClass("active");
        if ('${pageindex01}' == '1') {
            $('#upperpage').addClass("disabled");
        }
        if ('${pageindex01}' == '${pagecount01}') {
            $('#nextpage').addClass("disabled");
        }
    });

    function getInfo(alabel) {
        var currentPage = $(alabel).html();
        //$(alabel).addClass("active");
        window.location.href = "?pi=" + currentPage+"&f70xmbh=${f70xmbh}"+"&id=${id}&f00khlx=${f00khlx}";
    }
    function upperPage() {
        var currentpage = $('a[class~=active]').html();
        if (currentpage == 1) {
            return;
        } else {
            window.location.href = "?pi=" + (parseInt(currentpage) - 1)+"&f70xmbh=${f70xmbh}"+"&id=${id}&f00khlx=${f00khlx}";
        }

    }
    function nextPage() {
        var currentpage = $('a[class~=active]').html();
        if (currentpage + '' == '${pagecount01}') {

            return;
        } else {
            window.location.href = "?pi=" + (parseInt(currentpage) + 1)+"&f70xmbh=${f70xmbh}"+"&id=${id}&f00khlx=${f00khlx}";
        }
    }
    function firstPage() {
        window.location.href = "?pi=" + 1+"&f70xmbh=${f70xmbh}"+"&id=${id}&f00khlx=${f00khlx}";
    }
    function lastPage() {

        window.location.href = "?pi=" + ${pagecount01}+"&f70xmbh=${f70xmbh}"+"&id=${id}&f00khlx=${f00khlx}";
    }

    function isinteger(num) {
        var re = new RegExp("^-?[\\d]*$");
        if (re.test(num))
            return (!isNaN(parseInt(num)));
        else
            return false;
    }

    function goPage() {
        var currentpage = ($('#gopage').val());
        if(!isinteger(currentpage)){
            alert("请输入正确页码！");
            return;
        }
        if (currentpage >${pagecount01}) {
            alert("页面超出范围");
            return;
        }
        window.location.href = "?pi=" + currentpage+"&f70xmbh=${f70xmbh}"+"&id=${id}&f00khlx=${f00khlx}";
    }
</script>
<body>
<div id="page">
    <c:if test="${pagecount01!=0}">
        <div class="page red" style="margin: auto;">
            <div class="pbtn " onclick="firstPage()">首页</div>
            <div class="pbtn " id="upperpage" onclick="upperPage()">上一页</div>
            <ul>
                <c:if test="${pagecount01<=4}">
                    <c:forEach var="i" begin="1" end="${pagecount01}" step="1">
                        <li><a onclick="getInfo(this)" href="javascript:void(0)" class="red normal" id="${i}"><c:out
                                value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
                <c:if test="${pagecount01>4}">
                    <c:if test="${pageindex01<=2}">
                        <c:forEach var="i" begin="1" end="4" step="1">
                            <li><a onclick="getInfo(this)" href="javascript:void(0)" class="red normal" id="${i}"><c:out
                                    value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pageindex01>=pagecount01-2}">
                        <c:forEach var="i" begin="${pagecount01-3}" end="${pagecount01}" step="1">
                            <li><a onclick="getInfo(this)" href="javascript:void(0)" class="red normal" id="${i}"><c:out
                                    value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pageindex01<pagecount01-2 && pageindex01>2 }">
                        <c:forEach var="i" begin="${pageindex01-1}" end="${pageindex01+2}" step="1">
                            <li><a onclick="getInfo(this)" href="javascript:void(0)" class="red normal" id="${i}"><c:out
                                    value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                </c:if>
            </ul>
            <div class="pbtn" id="nextpage" onclick="nextPage()">下一页</div>
            <div class="pbtn" onclick="lastPage()">尾页</div>
            <div class="pbtn pinfo">
                当前第<c:out value="${pageindex01}"></c:out>页/共<c:out value="${pagecount01}"></c:out>页
            </div>
            <div class="short">
                <input type="text" value="1" id="gopage">
                <input class="pbtn" value="跳转" type="button" onclick="goPage()">
            </div>
        </div>
    </c:if>
    <c:if test="${pagecount01==0}">
        <div style="text-align: center">查询无结果</div>
    </c:if>
    <%--<%
        Page page1 = (Page) request.getAttribute("page");
        out.print(page1.getToolBar());
    %>--%>
</div>
</body>
</html>
