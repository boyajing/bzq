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
        <script type="text/javascript" src="<%=cpath%>/js/validate/jquery.poshytip.js"></script>
        <script type='text/javascript' src='<%=cpath%>/js/validate/jq.validate.js'></script>
        <script>
            $(function () {
                initdatepicker_cn();
                $("#foundday").datepicker({dateFormat: "yy-mm-dd"}).val();
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
                    var val = document.getElementById("orgDescription").value;
                    if(val==''||val==null){
                        alert("请输入机构编号");
                        return false;
                    }
//                    if(val.length!=6&&val.length!=4){
//                        alert("请输入正确的机构编号");
//                        return false;
//                    }
                    var n = $('input[name="orgName"]').val();
                    var ln = $('input[name="orglname"]').val();
                    if (n === null || n === '') {
                        alert("机构简称不能为空");
                    }
                    else if (ln === null || ln === '') {
                        alert("机构全称不能为空");
                    } else {
                        var orgform = $("#tab");
                        //将form数据组装成json对象的juery扩展  
                        var org = orgform.serializeObject();
                        //将json对象转成String是用的json2（http://www.json.org/js.html）的JSON.stringify  
                        var jsonorg = JSON.stringify(org);
                        $.ajax({
                            type: 'POST',
                            contentType: 'application/json',
                            url: '<%=cpath%>/jeda/org/new',
                            data: jsonorg,
                            dataType: 'json',
                            success: function (org) {
                                alert("建立成功。");
                                location.href = "<%=cpath%>/jeda/org/query?r=${returnview}&o=&org=${orgid}" ;//
                                //window.open("<%=cpath%>/jeda/org/orgtree", "leftframe");
                            },
                            error: function (user) {
//                                alert("修改失败,请输入正确邮编！");
                            }
                        });
                    }
                });
            });
        </script>
    </head>
    <body  >
        <div class="container-fluid">
            <div class="row-fluid">

                <div class="btn-toolbar">
                    <button name="save" id="save"class="btn btn-primary"><i class="icon-save"></i> 保存</button>
                    <c:if test="${returnview!=''}">
                        <a href="<%=cpath%>/<c:out value="${returnview}"/>" data-toggle="modal" class="btn">返回</a>    
                    </c:if>
                    <c:if test="${returnview==''}">
                        <a href="#" onclick="javascript:window.close()" data-toggle="modal" class="btn">关闭</a>    
                    </c:if>
                    <div class="btn-group">
                    </div>
                </div>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab"> 
                            <table class="well table resultTable table-hover"> 
                                <tr>
                                    <td>机构简称</td>
                                    <td><input type="text" name="orgName" value="" valType="required" msg="<font color=red>*</font>" ></td>
                                    <td>机构全称</td>
                                    <td><input type="text"  name="orglname" value="" valType="required" msg="<font color=red>*</font>"></td>
                                </tr>
                                <tr> 
                                    <td>成立日期</td>
                                    <td><input type="text"  name="foundday" id="foundday" value="" ></td>  
                                    <td>联系人</td>
                                    <td><input  name="orgContact" type="text" value=""></td> 
                                </tr>
                                <tr> 
                                    <td>联系人电话</td>
                                    <td><input type="text" name="orgTel" value="" valType="TEL" ></td>  
                                    <td>省份</td>
                                    <td><select id="province" name="province">
                                            <option value="安徽">安徽</option>
                                            <option value="北京">北京</option>
                                            <option value="重庆">重庆</option>
                                            <option value="福建">福建</option>
                                            <option value="甘肃">甘肃</option>
                                            <option value="广东">广东</option>
                                            <option value="广西">广西</option>
                                            <option value="贵州">贵州</option>
                                            <option value="海南">海南</option>
                                            <option value="河北">河北</option>
                                            <option value="黑龙江">黑龙江</option>
                                            <option value="河南">河南</option>
                                            <option value="湖北">湖北</option>
                                            <option value="湖南">湖南</option>
                                            <option value="江苏">江苏</option>
                                            <option value="江西">江西</option>
                                            <option value="吉林">吉林</option>
                                            <option value="辽宁">辽宁</option>
                                            <option value="内蒙古">内蒙古</option>
                                            <option value="宁夏">宁夏</option>
                                            <option value="青海">青海</option>
                                            <option value="陕西">陕西</option>
                                            <option value="山东">山东</option>
                                            <option value="上海">上海</option>
                                            <option value="山西">山西</option>
                                            <option value="四川">四川</option>
                                            <option value="天津">天津</option>
                                            <option value="新疆">新疆</option>
                                            <option value="西藏">西藏</option>
                                            <option value="云南">云南</option>
                                            <option value="浙江">浙江</option>
                                            <option value="香港">香港</option>
                                            <option value="澳门">澳门</option>
                                            <option value="台湾">台湾</option>
                                        </select> 
                                    </td>
                                </tr>
                                <tr> 
                                    <td>市县</td>
                                    <td><input type="text" name="city" value=""></td>  
                                    <td>详细地址</td>
                                    <td><input type="text" name="orgAddress" value=""></td>  
                                </tr>
                                <tr> 
                                    <td>邮编</td>
                                    <td><input type="text" name="post" value="" ></td>
                                    <td>机构ID</td>
                                    <td><input type="text" id="orgDescription" name="orgDescription" value="${orgDescription}"></td>
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
