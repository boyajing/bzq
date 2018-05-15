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
        $('#' + '${pageindex}').addClass("active");
        if ('${pageindex}' == '1') {
            $('#upperpage').addClass("disabled");
        }
        if ('${pageindex}' == '${pagecount}') {
            $('#nextpage').addClass("disabled");
        }
    });

    function getInfo(alabel) {
        var currentPage = $(alabel).html();
        //$(alabel).addClass("active");
        window.location.href = "?pi=" + currentPage+"&khbh=${khbh}"+"&id=${id}&pc=${pc}&actionCode=${actionCode}&workflowId=${workflowId}&taskId=${taskId}&maxId=${maxId}&inputid=${inputid}&projectCode=${projectCode}&naturalPersonId=${naturalPersonId}&customerId=${customerId}&type=${type}&edit=${edit}&key=${key}&businesscode=${businesscode}&businessname=${businessname}&businessId=${businessId}&businessType=${businessType}";
    }
    function upperPage() {
        var currentpage = $('a[class~=active]').html();
        if (currentpage == 1) {
            return;
        } else {
            window.location.href = "?pi=" + (parseInt(currentpage) - 1)+"&khbh=${khbh}"+"&id=${id}&pc=${pc}&actionCode=${actionCode}&workflowId=${workflowId}&taskId=${taskId}&maxId=${maxId}&inputid=${inputid}&projectCode=${projectCode}&naturalPersonId=${naturalPersonId}&customerId=${customerId}&type=${type}&edit=${edit}&key=${key}&businesscode=${businesscode}&businessname=${businessname}&businessId=${businessId}&businessType=${businessType}";
        }

    }
    function nextPage() {
        var currentpage = $('a[class~=active]').html();
        if (currentpage + '' == '${pagecount}') {
            return;
        } else {
            window.location.href = "?pi=" + (parseInt(currentpage) + 1)+"&khbh=${khbh}"+"&id=${id}&pc=${pc}&actionCode=${actionCode}&workflowId=${workflowId}&taskId=${taskId}&maxId=${maxId}&inputid=${inputid}&projectCode=${projectCode}&naturalPersonId=${naturalPersonId}&customerId=${customerId}&type=${type}&edit=${edit}&key=${key}&businesscode=${businesscode}&businessname=${businessname}&businessId=${businessId}&businessType=${businessType}";
        }
    }
    function firstPage() {
        window.location.href = "?pi=" + 1+"&khbh=${khbh}"+"&id=${id}&pc=${pc}&actionCode=${actionCode}&workflowId=${workflowId}&taskId=${taskId}&maxId=${maxId}&inputid=${inputid}&projectCode=${projectCode}&naturalPersonId=${naturalPersonId}&customerId=${customerId}&type=${type}&edit=${edit}&key=${key}&businesscode=${businesscode}&businessname=${businessname}&businessId=${businessId}&businessType=${businessType}";
    }
    function lastPage() {
        window.location.href = "?pi=" + ${pagecount}+"&khbh=${khbh}"+"&id=${id}&pc=${pc}&actionCode=${actionCode}&workflowId=${workflowId}&taskId=${taskId}&maxId=${maxId}&inputid=${inputid}&projectCode=${projectCode}&naturalPersonId=${naturalPersonId}&customerId=${customerId}&type=${type}&edit=${edit}&key=${key}&businesscode=${businesscode}&businessname=${businessname}&businessId=${businessId}&businessType=${businessType}";
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
        if (currentpage >${pagecount}) {
            alert("页面超出范围");
            return;
        }
        window.location.href = "?pi=" + currentpage+"&khbh=${khbh}"+"&id=${id}&pc=${pc}&actionCode=${actionCode}&workflowId=${workflowId}&taskId=${taskId}&maxId=${maxId}&inputid=${inputid}&projectCode=${projectCode}&customerId=${customerId}&type=${type}&naturalPersonId=${naturalPersonId}&edit=${edit}&key=${key}&businesscode=${businesscode}&businessname=${businessname}&businessId=${businessId}&businessType=${businessType}";
    }
</script>
<body>
<div id="page">
    <c:if test="${pagecount!=0&&pagecount!=null}">
        <div class="page red" style="margin: auto;">
            <div class="pbtn " onclick="firstPage()">首页</div>
            <div class="pbtn " id="upperpage" onclick="upperPage()">上一页</div>
            <ul >
                <c:if test="${pagecount<=4}">
                    <c:forEach var="i" begin="1" end="${pagecount}" step="1">
                        <li  style="float: left" ><a onclick="getInfo(this)" href="javascript:void(0)" class="red normal" id="${i}"><c:out
                                value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
                <c:if test="${pagecount>4}">
                    <c:if test="${pageindex<=2}">
                        <c:forEach var="i" begin="1" end="4" step="1">
                            <li ><a onclick="getInfo(this)" href="javascript:void(0)" class="red normal" id="${i}"><c:out
                                    value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pageindex>=pagecount-2}">
                        <c:forEach var="i" begin="${pagecount-3}" end="${pagecount}" step="1">
                            <li ><a onclick="getInfo(this)" href="javascript:void(0)" class="red normal" id="${i}"><c:out
                                    value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pageindex<pagecount-2 && pageindex>2 }">
                        <c:forEach var="i" begin="${pageindex-1}" end="${pageindex+2}" step="1">
                            <li ><a onclick="getInfo(this)" href="javascript:void(0)" class="red normal" id="${i}"><c:out
                                    value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                </c:if>
            </ul>
            <div class="pbtn" id="nextpage" onclick="nextPage()">下一页</div>
            <div class="pbtn" onclick="lastPage()">尾页</div>
            <div class="pbtn pinfo">
                当前第<c:out value="${pageindex}"></c:out>页/共<c:out value="${pagecount}"></c:out>页
            </div>
            <div class="short">
                <input type="text" value="1" id="gopage">
                <input class="pbtn" value="跳转" type="button" onclick="goPage()">
            </div>
        </div>
    </c:if>
    <c:if test="${pagecount==0||pagecount==null}">
        <div style="text-align: center">查询无结果</div>
    </c:if>
    <%--<%
        Page page1 = (Page) request.getAttribute("page");
        out.print(page1.getToolBar());
    %>--%>
</div>
</body>
</html>
