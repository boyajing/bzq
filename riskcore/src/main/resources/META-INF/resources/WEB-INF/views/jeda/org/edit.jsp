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
        <jsp:include page="../../commonhead.jsp"></jsp:include>
        <link href="<%=cpath%>/js/jquery.treeview/jquery.treeview.css" rel="stylesheet" type="text/css" />
        <link href="<%=cpath%>/js/jqueryui/jquery-ui.css" rel="stylesheet" type="text/css">
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.edit.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.treeview/jquery.treeview.async.js" type="text/javascript"></script>
        <script src="<%=cpath%>/js/jquery.cookie.js" type="text/javascript"></script>
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/jquery-ui.js"></script>
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/datepicker_cn.js"></script>
        <script>
            $(function () {
                initdatepicker_cn();
                $("#foundday").val("<fmt:formatDate value="${org.foundday}" pattern="yyyy-MM-dd"/>");
                $("#foundday").datepicker({dateFormat: "yy-mm-dd"}).val();
            });
        </script>
        <script language="javascript">
            var treeToggle;
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
                $("#edit").click(function () {
                    var orgform = $("#tab");
                    //将form数据组装成json对象的juery扩展  
                    var org = orgform.serializeObject();
                    //将json对象转成String是用的json2（http://www.json.org/js.html）的JSON.stringify  
                    var jsonorg = JSON.stringify(org);

                    $.ajax({
                        type: 'POST',
                        contentType: 'application/json',
                        url: '<%=cpath%>/jeda/org/edit',
                        data: jsonorg,
                        dataType: 'json',
                        success: function () {
                            alert("修改成功。");
                            location.href = "<%=cpath%>/<c:out value="${returnview}"/>&orgName=&o=&org=${orgid}";//
                        },
                        error: function (user) {
//                            alert("修改失败,请输入正确邮编！");
                        }
                    });
                });

                $("#tree2").treeview({
                    url: "<%=cpath%>/jeda/org/gettree",
                    persist: "cookie",
                    toggle: function () {
                        selecttreeClick(this.id);
                    }
                });
            });

            function goBake(){
                location.href="<%=cpath%>/${returnview}&org=${orgid}";
            }
        </script>
    </head>

    <body  >
        <div class="container-fluid">
            <div class="row-fluid">

                <div class="btn-toolbar">
                    <button name="edit" id="edit"class="btn btn-primary"><i class="icon-save"></i> 保存</button>
                    <c:if test="${returnview!=''}">
                        <%--<a href="<%=cpath%>/<c:out value="${returnview}"/>&org=<c:out value="${orgid}"/>" data-toggle="modal" class="btn">返回</a>--%>
                        <a onclick="goBake()" data-toggle="modal" class="btn">返回</a>
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
                                    <td>机构简称</td>
                                    <td><input type="text" name="orgName" value="${org.orgName}" ></td>

                                    <td>机构ID</td>
                                    <td><input type="text" name="orgId" value="${org.orgId}" readonly></td>
                                </tr>

                                <tr>
                                    <td>机构全称</td>
                                    <td><input type="text"  name="orglname" value="${org.orglname}"  ></td>

                                    <td>成立日期</td>
                                    <td><input type="text"  name="foundday" id="foundday" value=""></td>
                                </tr>

                                <tr>
                                    <td>联系人</td>
                                    <td><input  name="orgContact" type="text" value="${org.orgContact}"></td>

                                    <td>联系人电话</td>
                                    <td><input type="text" name="orgTel" value="${org.orgTel}"></td>
                                </tr>

                                <tr>
                                    <td>省份</td>
                                    <td>
                                        <select id="province" name="province">
                                            <option <c:if test="${org.province=='安徽'}">selected</c:if> value="安徽">安徽</option>
                                            <option <c:if test="${org.province=='北京'}">selected</c:if> value="北京">北京</option>
                                            <option <c:if test="${org.province=='重庆'}">selected</c:if> value="重庆">重庆</option>
                                            <option <c:if test="${org.province=='福建'}">selected</c:if> value="福建">福建</option>
                                            <option <c:if test="${org.province=='甘肃'}">selected</c:if> value="甘肃">甘肃</option>
                                            <option <c:if test="${org.province=='广东'}">selected</c:if> value="广东">广东</option>
                                            <option <c:if test="${org.province=='广西'}">selected</c:if> value="广西">广西</option>
                                            <option <c:if test="${org.province=='贵州'}">selected</c:if> value="贵州">贵州</option>
                                            <option <c:if test="${org.province=='海南'}">selected</c:if> value="海南">海南</option>
                                            <option <c:if test="${org.province=='河北'}">selected</c:if> value="河北">河北</option>
                                            <option <c:if test="${org.province=='黑龙江'}">selected</c:if> value="黑龙江">黑龙江</option>
                                            <option <c:if test="${org.province=='河南'}">selected</c:if> value="河南">河南</option>
                                            <option <c:if test="${org.province=='湖北'}">selected</c:if> value="湖北">湖北</option>
                                            <option <c:if test="${org.province=='湖南'}">selected</c:if> value="湖南">湖南</option>
                                            <option <c:if test="${org.province=='江苏'}">selected</c:if> value="江苏">江苏</option>
                                            <option <c:if test="${org.province=='江西'}">selected</c:if> value="江西">江西</option>
                                            <option <c:if test="${org.province=='吉林'}">selected</c:if> value="吉林">吉林</option>
                                            <option <c:if test="${org.province=='辽宁'}">selected</c:if> value="辽宁">辽宁</option>
                                            <option <c:if test="${org.province=='内蒙古'}">selected</c:if> value="内蒙古">内蒙古</option>
                                            <option <c:if test="${org.province=='宁夏'}">selected</c:if> value="宁夏">宁夏</option>
                                            <option <c:if test="${org.province=='青海'}">selected</c:if> value="青海">青海</option>
                                            <option <c:if test="${org.province=='陕西'}">selected</c:if> value="陕西">陕西</option>
                                            <option <c:if test="${org.province=='山东'}">selected</c:if> value="山东">山东</option>
                                            <option <c:if test="${org.province=='上海'}">selected</c:if> value="上海">上海</option>
                                            <option <c:if test="${org.province=='山西'}">selected</c:if> value="山西">山西</option>
                                            <option <c:if test="${org.province=='四川'}">selected</c:if> value="四川">四川</option>
                                            <option <c:if test="${org.province=='天津'}">selected</c:if> value="天津">天津</option>
                                            <option <c:if test="${org.province=='新疆'}">selected</c:if> value="新疆">新疆</option>
                                            <option <c:if test="${org.province=='西藏'}">selected</c:if> value="西藏">西藏</option>
                                            <option <c:if test="${org.province=='云南'}">selected</c:if> value="云南">云南</option>
                                            <option <c:if test="${org.province=='浙江'}">selected</c:if> value="浙江">浙江</option>
                                            <option <c:if test="${org.province=='香港'}">selected</c:if> value="香港">香港</option>
                                            <option <c:if test="${org.province=='澳门'}">selected</c:if> value="澳门">澳门</option>
                                            <option <c:if test="${org.province=='台湾'}">selected</c:if> value="台湾">台湾</option>
                                        </select> </td>

                                    <td>市县</td>
                                    <td><input type="text" name="city" value="${org.city}"></td>  

                                </tr>
                                <tr>
                                    <td>详细地址</td>
                                    <td><input type="text" name="orgAddress" value="${org.orgAddress}"></td>

                                    <td>邮编</td>
                                    <td><input type="text" name="post" value="${org.post}"></td>

                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>

<div id="selectItem2" class="selectItemhidden" hidden="true"> 
    <div id="selectItemAd" class="selectItemtit bgc_ccc"> 
        <div id="selectItemClose" class="selectItemright">关闭</div>
    </div> 
    <ul id="tree2">

    </ul> 

</div> 