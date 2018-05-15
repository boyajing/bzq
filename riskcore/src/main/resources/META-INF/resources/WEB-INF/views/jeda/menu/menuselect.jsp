<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String cpath = request.getContextPath();
    int index = 1;

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <jsp:include page="../../commonhead.jsp"></jsp:include>
        <link href="<%=cpath%>/js/jquery.treeview/jquery.treeview.css" rel="stylesheet" type="text/css" />
        <link href="<%=cpath%>/js/jqueryui/jquery-ui.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="<%=cpath%>/css/component.css" />
        <style>
            .menureg {
                float:left;
                padding: 10px 10px 10px 10px;

            }
        </style>
        <script language="javascript">
            $(document).ready(function () {
                
                
                var StringBuilder = function () {
                    //console.log(this);
                    //StringBuilder     
                    //    _stringArray: Array[2]   
                    //    __proto__: StringBuilder  

                    this._stringArray = new Array();
                };
                StringBuilder.prototype.append = function (str) {
                    this._stringArray.push(str);
                }
                StringBuilder.prototype.toString = function (joinGap) {
                    return this._stringArray.join(joinGap);
                }
                var treeMenuHtml = new StringBuilder();
                var loadTree = function (root, L) {
                    $.ajax({
                        type: 'GET',
                        contentType: 'application/json',
                        url: '<%=cpath%>/jeda/menu/gettree?root=' + root + '&location=source',
                        dataType: 'json',
                        async: false,
                        success: function (data) {
                            for (var i = 0; i < data.length; i++) {
                                var s = '<label style="padding-left:' + (L - 3) * 30 + 'px" class="checkbox">';
                                s+='<input parentid="'+root+'" class="placehode" id="' + data[i].id + '" name="' + data[i].id + '" type="checkbox"><h' + L + ' >' + data[i].text + '</h' + L + '></label>';
                                if (L == 4) {
                                    treeMenuHtml.append("<div id='div_" + data[i].id + "' class='menureg'>" + s);
                                    loadTree(data[i].id, L + 1);
                                    treeMenuHtml.append("</div>");
                                } else {
                                    treeMenuHtml.append(s);
                                    loadTree(data[i].id, L + 1);
                                }


                            }
                        },
                        error: function (user) {

                        }
                    });
                };
                loadTree("source", 4);

                $("#menureg").append(treeMenuHtml.toString(" "));
                var updateSelected = function (root,key) {
                    $.ajax({
                        type: 'GET',
                        contentType: 'application/json',
                        url: '<%=cpath%>/jeda/menu/gettree?root=' + root + '&location=source',
                        dataType: 'json',
                        async: false,
                        success: function (data) {
                            for (var i = 0; i < data.length; i++) {
                                $("#" + data[i].id).attr("checked", key);
                                updateSelected(data[i].id,key);
                            }
                        },
                        error: function (user) {

                        }
                    });
                }
                var updateparentid=function(root){
                    if(root=="source"){return;}
                    $("#"+root).attr("checked",true);
                    updateparentid($("#"+root).attr("parentid"));
                    
                }
                $("input.placehode").click(function () {
                    updateSelected(this.id,this.checked);
                    if(this.checked){
                        updateparentid($(this).attr("parentid"));
                    }
                });
                
                
                
                <c:if test="${menus!=null}">
                <c:forEach var="menu" items="${menus}">
                    $("#<c:out value="${menu.menuId}"/>").attr("checked",true);
                </c:forEach>
                
                </c:if>
                
                
            });

        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">
                    <a class='space_btn'>勾选您选定的菜单项,选定后点击：
                    </a>
                    <button id="edit" class="btn" onclick="postform.submit()">确定</button>
                    <button id="edit" class="btn" onclick="window.close()">关闭</button>
                </div> 
                <form id="postform" action="<%=cpath%><c:out value="${posturl}"/>" method="post">
                    <div id="menureg">

                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
