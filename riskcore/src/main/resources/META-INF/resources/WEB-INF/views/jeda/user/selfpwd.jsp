<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../../commonhead.jsp"></jsp:include>
        <link href="<%=cpath%>/js/jqueryui/jquery-ui.css" rel="stylesheet" type="text/css">
    
        <script src="<%=cpath%>/js/jquery.cookie.js" type="text/javascript"></script>
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/jquery-ui.js"></script>
        <script type ="text/javascript" src="<%=cpath%>/js/jqueryui/datepicker_cn.js"></script>
        <script type="text/javascript" src="<%=cpath%>/js/validate/jquery.poshytip.js"></script>
        <script type='text/javascript' src='<%=cpath%>/js/validate/jq.validate.js'></script>

 <script language="javascript">
            $(document).ready(function () {
//向后台传送新建的数据，
  
        $("#save").click(function() {  
            var a=$("input[name='npwd']").val();
                if(a!=""&&a!=null){
                    $.ajax({
                        type : 'POST',
                        contentType:"application/json",
                        url : '<%=cpath%>/jeda/user/selfpwd',
                        data : a,
                        success : function(i) { 
                                alert("密码修改成功。");
                                /*location.href="<%=cpath%>/content";*/
                        } 
                    });  
                }
        });  
        $("#opwd").blur(function (){
                var a=$("input[name='opwd']").val();
                if(a!=""&&a!=null){
                    $.ajax({  
                        type : 'POST',
                        contentType:"application/json",
                        url : '<%=cpath%>/jeda/user/checkpwd',
                        data : a,   
                        success : function(i) {  
                            if(i!=1){
                                alert("密码错误请重新输入。");
                            } 
                        } 
                    });  
                }
             });
             $("#nnpwd").blur(function (){
                if($("input[name='npwd']").val()!=$("input[name='nnpwd']").val()){
                    alert("密码不一致，请重新输入。");
                    $("input[name='npwd']").val(null);
                    $("input[name='nnpwd']").val(null);
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
                <%--    <c:if test="${returnview!=''}">
                        <a href="<%=cpath%>/content" data-toggle="modal" class="btn">返回</a>    
                    </c:if>--%>
                    <c:if test="${returnview==''}">
                        <a href="#" onclick="javascript:window.close()" data-toggle="modal" class="btn">关闭</a>    
                    </c:if>
                    <div class="btn-group">
                    </div>
                </div>
                <div class="well">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#home" data-toggle="tab">密码</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active in" id="home">
                            <form id="tab">
                                <label>原始密码</label>
                                <input type="password" name="opwd" id="opwd" class="input-xlarge">
                                <label>新密码</label>
                                <input type="password" name="npwd" id="npwd" valType="required" msg="<font color=red>*</font>不能设置空的密码" class="input-xlarge" value="">
                                <label>再次确认新密码</label>
                                <input type="password" name="nnpwd" id="nnpwd" valType="required" class="input-xlarge" value="">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=cpath%>/js/bootstrap/js/bootstrap.js"></script>
</body>
</html>
