<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language='javascript'>
    var goPage = function (pageIndex) {
        var id = "${id}";
        window.location = "?pi=" + pageIndex + "&id=" + (id == "" ? "" : id);
    }
</script>
<div class="pagination" style="left: 0px;float: left;margin: 0px;">
    <ul>
        <c:if test="${pagecount<=15}">
            <c:forEach var="i" begin="1" end="${pagecount}" step="1">
                <li><a href="#" onclick="goPage('<c:out value="${i}"/>')">
                    <c:if test="${pageindex==i}">
                        <span style="color:red"><c:out value="${i}"/></span>
                    </c:if>
                    <c:if test="${pageindex!=i}">
                        <c:out value="${i}"/>
                    </c:if>
                </a></li>

            </c:forEach>
        </c:if>

        <c:if test="${pagecount>15}">
            <c:if test="${pageindex>1}">
                <li>
                    <a href="#" onclick="goPage('<c:out value="${pageindex-1}"/>')">前页</a>
                </li>
            </c:if>

            <c:if test="${pageindex<=5||pageindex>=pagecount-5}">
                <c:forEach var="i" begin="1" end="5" step="1">
                    <li>
                        <a href="#" onclick="goPage('<c:out value="${i}"/>')">

                            <c:if test="${pageindex==i}">
                                <span style="color:red"><c:out value="${i}"/></span>
                            </c:if>
                            <c:if test="${pageindex!=i}">
                                <c:out value="${i}"/>
                            </c:if>
                        </a>
                    </li>
                </c:forEach>

                <li>
                    <a href="#">......</a>
                </li>
                <c:forEach var="i" begin="${pagecount-5}" end="${pagecount}" step="1">
                    <li>
                        <a href="#" onclick="goPage('<c:out value="${i}"/>')">
                            <c:if test="${pageindex==i}">
                                <span style="color:red"><c:out value="${i}"/></span>
                            </c:if>
                            <c:if test="${pageindex!=i}">
                                <c:out value="${i}"/>
                            </c:if>
                        </a>
                    </li>
                </c:forEach>

            </c:if>
            <c:if test="${!(pageindex<=5||pageindex>=pagecount-5)}">
                <li>
                    <a href="#" onclick="goPage('1')">1</a>
                </li>
                <li>
                    <a href="#">......</a>
                </li>
                <c:forEach var="i" begin="${(pageindex-3)>0?(pageindex-3):1}"
                           end="${(pageindex+3)<=pagecount?(pageindex+3):pagecount}" step="1">
                    <li>
                        <a href="#" onclick="goPage('<c:out value="${i}"/>')">
                            <c:if test="${pageindex==i}">
                                <span style="color:red"><c:out value="${i}"/></span>
                            </c:if>
                            <c:if test="${pageindex!=i}">
                                <c:out value="${i}"/>
                            </c:if>
                        </a>
                    </li>
                </c:forEach>
                <c:if test="${(pageindex+3)<pagecount}">
                    <li>
                        <a href="#">......</a>
                    </li>
                    <li>
                        <a href="#" onclick="goPage('<c:out value="${pagecount}"/>')">
                            <c:if test="${pageindex==pagecount}">
                                <span style="color:red"><c:out value="${pagecount}"/></span>
                            </c:if>
                            <c:if test="${pageindex!=pagecount}">
                                <c:out value="${pagecount}"/>
                            </c:if>
                        </a>
                    </li>
                </c:if>

            </c:if>
            <c:if test="${pageindex<pagecount}">
                <li>
                    <a href="#" onclick="goPage('<c:out value="${pageindex+1}"/>')">后页</a>
                </li>
            </c:if>
        </c:if>
    </ul>
</div>
<div class="pagination" style="right: 0px;float: right;margin: 0px;">
    <ul>
        <li><span>第${pageindex==null?1:pageindex}页</span></li>
        <li><span>共${pagecount==null||pagecount==0?1:pagecount}页。</span></li>
    </ul>
</div>
<!--选择框 查询结果中使用-->
<script type="text/javascript">
    $(document).ready(function () {
        $("#resultTable tr").click(function (e) {
            //console.info(e.target);
            if (e.target.type != undefined && e.target.type == "checkbox") return;
            $(this).toggleClass('selected');
            var checkBoxe = $(this).find("input");
            checkBoxe.prop("checked", !checkBoxe.prop("checked"));
        });

        //点击列表排序
        $("span.tableheadOrder").click(function (evn) {
            var column = $(evn.target).parent().attr("column");
            <c:if test="${ordercase==''}">
            column += " ASC";
            </c:if>
            <c:if test="${ordercase!=''}">
            if (column + " ASC" == '<c:out value="${ordercase}"/>') {
                column += " DESC";
            } else if (column + " DESC" == '<c:out value="${ordercase}"/>') {
                column += " ASC";
            } else {
                column += " ASC";
            }
            </c:if>
            window.location = "?pi=<c:out value="${pageindex}"/>&o=" + column;
        });
        <c:if test="${ordercase!=''}">
        var orderCase = '<c:out value="${ordercase}"/>';
        var ss = orderCase.split(' ');
        //

        $("[inorder='true'][column='" + ss[0] + "']").append(
                ss[1] == "ASC" ? '<i class="ui-icon ui-icon-triangle-1-n" style="float:left"></i>' :
                        '<i class="ui-icon ui-icon-triangle-1-s" style="float:left"></i>');
        </c:if>
    });
</script>