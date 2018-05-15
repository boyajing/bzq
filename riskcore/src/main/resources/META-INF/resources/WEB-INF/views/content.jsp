
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en" style="height:100%">
    <head>
        <jsp:include page="commonhead.jsp"></jsp:include>
        <script src="js/bootstrap/js/bootstrap.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=cpath%>/css/hover-min.css">
        <script>
        </script>
    </head>
    <script type="text/javascript">
        function dblink(link){
            if ($("#" + $(link).attr("rel"),parent.document).length != 0) {
                //隐藏所有iframe
                window.parent.document.getElementById("content").getElementsByTagName("iframe")[0].style.display="none";
                //显示点中的iframe
                parent.document.getElementById($(link).attr("rel") + "_content").style.display="inline";
                //删除class=current
                $("#tabs li",parent.document).removeClass("current");
                //给页签赋值class
                parent.document.getElementById($(link).attr("rel") + "_li").className="current";
                //刷新页面
                var url = parent.document.getElementById($(link).attr("rel") + "_content").src;
                parent.document.getElementById($(link).attr("rel") + "_content").src=url;

            }else {
                var width = 0;
                $(window.parent.document.getElementById("tabs").getElementsByTagName("li")).each(function (index, ele) {
                    width += $(window.parent.document.getElementById("tabs").getElementsByTagName("li")).width();
                });
                window.parent.document.getElementById("tabs").getElementsByTagName("li")[0].className="";
                window.parent.document.getElementById("content").getElementsByTagName("iframe")[0].style.display="none";
                $(window.parent.document.getElementById("tabs")).append("<li name='current' class='current' id='"+$(link).attr("rel")+'_li'+"'><image src='images/tab_L.jpg'></image><div><a class='tab' id='" +
                        $(link).attr("rel") + "' href='#'>" + $(link).attr("name") +
                        "</a><a href='#' class='remove' onclick='reomveCook(this)'>x</a></div><image src='images/tab_R.jpg'></image></li>");
                $(window.parent.document.getElementById("content")).append(" <iframe name='current' id='" + $(link).attr("rel") + "_content' style='padding: 0 0 0 0;border:0px solid #000000' src='" + $(link).attr("tar") + "' ></iframe>");
                window.parent.document.getElementById($(link).attr("rel") + "_content").display="inline";
                updateFrmSize(link);
            }
        }
        function updateFrmSize(link) {
            $(window.parent.document.getElementById("content").getElementsByTagName("iframe")).width("100%");
            $(window.parent.document.getElementById("content").getElementsByTagName("iframe")).height(parent.document.body.clientHeight - 112);


        }
        $('#tabs a.tab').live('click', function () {
            // Get the tab name
            var contentname = $(this).attr("id") + "_content";

            // hide all other tabs
            $("#content iframe").hide();
            $("#tabs li").removeClass("current");

            // show current tab
            $("#" + contentname).show();
            $(this).parent().parent().addClass("current");
        });
        //刷新首页
        function refreshs(){
            location.replace(location.href);
        }
    </script>
    <frameset id='mainframe' class="current"  scrolling="no"  name="current" cols="*" rows="60%,*" frameborder="yes" border="1" framespacing="1">
        <frameset id='mainframe2' class="current" scrolling="no"  name="current2" cols="50%,50%"  frameborder="yes"  framespacing="1">
            <frame id="rightframe" name='rightframe'   noresize src="<%=cpath%>/workflow/action/claimTask/dbyb"/>
            <frame id="leftframe" name='leftframe' noresize src="<%=cpath%>/workflow/action/claimTask/yj"/>
        </frameset>
        <frame id="rightframe2" name='rightframe2'  noresize  src="<%=cpath%>/workflow/action/claimTask/tb"/>
    </frameset>
</html>
