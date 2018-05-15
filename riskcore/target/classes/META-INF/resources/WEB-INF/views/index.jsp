<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String cpath = request.getContextPath();
    String bpath = request.getContextPath().split("/")[0] + "/Bas";
%>
<!DOCTYPE html>
<html lang="en" style="height:100%">
<head>
    <jsp:include page="commonhead.jsp"></jsp:include>
    <script src="js/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script>
        var navRoot = '<li><a href="<%=cpath%>/content">首页</a><span class="divider">/</span></li>';
        $(document).ready(function () {
            /*//右击弹出菜单
            var oMenu = document.getElementById("ment_www_zzjs_net");
            var aLi = oMenu.getElementsByTagName("li");
            //加载后隐藏自定义右键菜单
            oMenu.style.display = "none";
            //菜单鼠标移入/移出样式
            for (i = 0; i < aLi.length; i++)
            {   //鼠标移入样式
                aLi[i].onmouseover = function ()
                {
                    this.className = "active"
                };
                //鼠标移出样式
                aLi[i].onmouseout = function ()
                {
                    this.className = ""
                }
            }
            //自定义菜单
            document.oncontextmenu = function (event)
            {
                var event = event || window.event;
                var style = oMenu.style;
                style.display = "block";
                style.top = event.clientY-73 + "px";
                style.left = event.clientX-260 + "px";
                return false;
            }
            //页面点击后自定义菜单消失
            document.onclick = function ()
            {
                oMenu.style.display = "none"
            }*/
            updateFrmSize();
            $(window).resize(function () {
                updateFrmSize();
            });
            $('a[href=#menulink]').click(function () {
                if($(this).attr("rel")=='SH002')//处置审核
                    link('<%=cpath%>/workflow/action/framework/frame?workflowType=6002&workflowId=&taskId=&actionCode=begin&taskTypeId=600210');
                else if($(this).attr("rel")=='ZBH001')//立项审核
                    link('<%=cpath%>/workflow/action/framework/frame?workflowType=2002&workflowId=&taskId=&actionCode=begin&taskTypeId=200210');
                else if($(this).attr("rel")=='SH001')//收购方案审核
                    link('<%=cpath%>/workflow/action/framework/frame?workflowType=4002&workflowId=&taskId=&actionCode=begin&taskTypeId=400210');
                else if($(this).attr("rel")=='SH003')//审核上会
                    link('<%=cpath%>/workflow/action/framework/frame?workflowType=1102&workflowId=&taskId=&actionCode=begin&taskTypeId=110210');
                else if($(this).attr("rel")=='ZBH002')//立项上会
                    link('<%=cpath%>/workflow/action/framework/frame?workflowType=1101&workflowId=&taskId=&actionCode=begin&taskTypeId=110110');
                else if($(this).attr("rel")=='ASSET00')//批量建账
                    link('<%=cpath%>/workflow/action/framework/frame?workflowType=3001&workflowId=&taskId=&actionCode=begin&taskTypeId=300110');
                //else if($(this).attr("rel")=='One013_02')//不良资产包导入
                  //  link('<%=cpath%>/workflow/action/framework/frame?workflowType=3001&workflowId=&taskId=&actionCode=begin&taskTypeId=300110');
                else if($(this).attr("rel")=='JD001') {//尽职调查
                    var localobj = window.location;
                    var basepath = localobj.protocol+"//"+localobj.host;
                    window.open(basepath+"/jzdc/creditorAction.do?method=joinSystem&firstEnter=yes&joinUsername=${userid}&userRole=BSC_ZCCZ_XMJL");
                }else
                    addTab($(this));
            });
            $('#tabs a.tab').live('click', function () {
                // Get the tab name
                var contentname = $(this).attr("id") + "_content";

                // hide all other tabs
                $("#content iframe").hide();
                $("#tabs li").removeClass("current");

                // show current tab
                $("#" + contentname).show();
                $(this).parent().parent().addClass("current");
//重新调整页面大小，解决页面内多个iframe时，页签切换回来时，页面布局不正确（页面内容不会显示问题）
                updateFrmSize();
            });

            $('#tabs a.remove').live('click', function () {
                // Get the tab name
                var tabid = $(this).parent().find(".tab").attr("id");

                // remove tab and related content
                var contentname = tabid + "_content";
                $("#" + contentname).remove();
                $(this).parent().parent().remove();

                // if there is no current tab and if there are still tabs left, show the first one
                if ($("#tabs li.current").length == 0 && $("#tabs li").length > 0) {

                    // find the first tab
                    var firsttab = $("#tabs li:first-child");
                    firsttab.addClass("current");

                    // get its link name and show related content
                    var firsttabid = $(firsttab).find("a.tab").attr("id");
                    $("#" + firsttabid + "_content").show();
                    refreshs();
                }
            });

            <%--实现菜单右侧框收缩--%>
            $("#sideFrame").bind("mouseover", function () {
                $("#sideToggle").click();
            });
        });
        //右侧菜单关闭当前、所有页签
        function sy(){
            //删掉除首页意外的页签与页面
            $("#tabs li[name=current]").remove();
            $("#content iframe[name=current]").remove();
            //显示首页页面
            $("#content iframe").show();
            //隐藏菜单
            document.getElementById("ment_www_zzjs_net").style.display = "none";
        }
        function addTab(link) {

            // If tab already exist in the list, return
            if ($("#" + $(link).attr("rel")).length != 0) {
                // Get the tab name
                var contentname = $(link).attr("rel") + "_content";

                // hide all other tabs
                 $("#content iframe").hide();
                 $("#tabs li").removeClass("current");

                 // show current tab
                 $("#" + contentname).show();
                 $("#" + $(link).attr("rel")).parent().parent().addClass("current");
                 //return;
                $("#" + contentname).show();
                /*$("#tabs li").removeClass("current");*/
                var url = $("#" + contentname).attr("src");
                $("#"+contentname).attr("src",url);
            }else{
                var width = 0;
                $("#tabs li").each(function (index, ele) {
                    width += $(this).width();
                });

                // hide other tabs
                $("#tabs li").removeClass("current");
                $("#content iframe").hide();
                // add new tab and related content
                $("#tabs").append("<li name='current' class='current'><image src='images/tab_L.jpg'></image><div><a class='tab' id='" +
                        $(link).attr("rel") + "' href='#'>" + $(link).html().replace(" &nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;", "") +
                        "</a><a href='#' class='remove' onclick='reomveCook(this)'>x</a></div><image src='images/tab_R.jpg'></image></li>");
                $("#content").append(" <iframe name='current'id='" + $(link).attr("rel") + "_content' style='padding: 0 0 0 0;border:0px solid #000000' src='" + $(link).attr("tar") + "' ></iframe>");
                $("#" + $(link).attr("rel") + "_content").show();
                updateFrmSize();
            }
        };
        //清理指定单选框、复选框cook
        function reomveCook(obj){
            var id = obj.previousSibling.id;
            //数据授权
            if(id=='SQ001'){document.cookie = "d9009qxExpland=;expires="+(new Date(0)).toGMTString();}
            //处置
            if(id=='REC001'){document.cookie = "disposeInfo=;expires="+(new Date(0)).toGMTString();}
            //资产交接
            if(id=='ASSET04'){document.cookie = "zcjjcookie=;expires="+(new Date(0)).toGMTString();}
            //审计监控
            if(id=='SJ001'){document.cookie = "sjjkcookie=;expires="+(new Date(0)).toGMTString();}
            //资产交接
            if(id=='ASSET04'){document.cookie = "zcjjcookie=;expires="+(new Date(0)).toGMTString();}
        }

            function updateFrmSize() {
            $("iframe").width(document.body.clientWidth - 248);
            $("iframe").height(document.body.clientHeight - 112);
            $("#sidebarfrm").height(document.body.clientHeight - 80);
            $('iframe[id$=_content]').css({width: "100%"});
        };
        //流程弹出框
        function link(url)
        {
            w=screen.width; h=screen.height;
            var newWin = window.open(url,'','height='+h+',width='+w+',left=0,top=0,status=1,toolbar=0,menubar=0,location=0,resizable=1,border=0',true);
            newWin.moveTo(0,0);
            newWin.resizeTo(screen.availWidth,screen.availHeight);
        }
        //刷新当前页面
        function refreshs(){
            hometab_content.location.reload(true);
        }
    </script>
    <style>
        #tabs {
            margin-top: 4px;
            margin-left: 0px;
            margin-bottom: 0px;
            padding: 0;
            list-style: none;
            overflow: auto;
            height: 20%;
            background-image: url(<%=cpath%>/images/tab_b_b.png);
        }

        #tabs li {
            float: left;
            display: block;
            height: 27px;

            background-image: url(<%=cpath%>/images/tab_bd.jpg);
            border-bottom: 1px #cfcfcf solid
        }

        #tabs li div {
            min-width: 50px;
            padding-top: 4px;
            padding-left: 3px;
            padding-right: 3px;
            float: left;
            text-align: center
        }

        #tabs li img {
            float: left
        }

        #tabs li a.remove {
            color: #555555;
            margin-left: 10px;
        }

        #tabs li a {
            color: #555555;
            text-decoration: none;
        }

        #tabs li.current {
            background-image: url(<%=cpath%>/images/tab_b.jpg);
            border-bottom: 1px #FFFFFF solid
        }

        #tabs li.current a {
            color: #000;
            text-decoration: none;
        }

        #tabs li.current a.remove {
            color: #f00;
            margin-left: 10px;
        }
        #ment_www_zzjs_net li{margin:0;padding:0;font:12px/24px arial;}
        #ment_www_zzjs_net{position:absolute;top:-9999px;left:-9999px;width:90px;border-radius:3px;list-style-type:none;border:1px solid #8f8f8f;padding:2px;background:#fff;}
        #ment_www_zzjs_net li{position:relative;height:24px;padding-left:10px;background:#eaead7;vertical-align:top;}
        #ment_www_zzjs_net li a{display:block;color:#333;background:#fff;padding-left:5px;text-decoration:none;}
        #ment_www_zzjs_net li.active{background:#999;}
        #ment_www_zzjs_net li.active a{color:#fff;background:#8f8f8f;}
        #ment_www_zzjs_net li em{position:absolute;top:0;left:0;width:24px;height:24px;}
        #ment_www_zzjs_net li em.cur{background-position:0 0;}
        #ment_www_zzjs_net li em.copy{background-position:0 -24px;}
        #ment_www_zzjs_net li em.paste{background-position:0 -48px;}
    </style>
    <%--实现菜单右侧框收缩--%>
    <style>
        #sideToggle {
            display: none;
        }
        #wrap {
            transition: 0.25s ease-out;
            -webkit-transition: 0.25s ease-out;
        }
        #sideToggle:checked ~ #wrap {
            left: 1px;
        }
    </style>
</head>

<!--[if lt IE 7 ]>
<body class="ie ie6" style="height:100%;overflow: hidden"> <![endif]-->
<!--[if IE 7 ]>
<body class="ie ie7 " style="height:100%;overflow: hidden"> <![endif]-->
<!--[if IE 8 ]>
<body class="ie ie8 " style="height:100%;overflow: hidden"> <![endif]-->
<!--[if IE 9 ]>
<body class="ie ie9 " style="height:100%;overflow: hidden"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body style="height:100%;overflow: hidden;background-image: url(<%=cpath%>/images/banner/banner_05.jpg); ">
<!--<![endif]-->
<jsp:include page="head.jsp"/>

<input type='checkbox' id='sideToggle'/>

<div id="sideFrame" style="position: absolute;width: 1px; left: 0px;top: 0px;bottom: 0px;"></div>
<div class="sidebar-nav accordion" id="sidebarfrm" style="background-image: url('<%=cpath%>/images/banner/banner_05.jpg');height:100%;overflow:auto;">
    <!--循环添加菜单依次添加1,2,3,4级菜单，1，2,3两个空格，4四个空格-->
    <c:forEach var="item1" items="${MENU}">
        <div class="accordion-group">
            <!--添加1级菜单-->
            <div class="accordion-heading">
                <a href='#<c:out value="${item1.menuitem.menuId}"/>' class="nav-header collapsed" data-parent="#sidebarfrm" data-toggle="collapse">
                    <i class="icon-dashboard"></i>
                    <c:out value="${item1.menuitem.menuName}"/>
                    <i class="icon-chevron-up"></i>
                </a>
            </div>
            <ul id="<c:out value="${item1.menuitem.menuId}"/>" class="nav nav-list collapse nav_list_subitem">
                <!--添加2级菜单,-2级如果有URL显示YesUrl.gif，没有显示NoUrl.gif图标-->
                <c:forEach var="sub2item" items="${item1.subItem}">
                    <li class="nav_list_subitem summenu_<c:out value='${item1.menuitem.menuId}'/>" <c:if test="${fn:length(sub2item.subItem)!=0}"> subvs="true" onclick="if($(this).attr('subvs')=='true'){$('.summenu_<c:out value="${sub2item.menuitem.menuId}"/>').hide();$(this).attr('subvs','false');}else{$('.summenu_<c:out value="${sub2item.menuitem.menuId}"/>').show();$(this).attr('subvs','true');}" </c:if>>
                        <c:choose>
                            <c:when test="${fn:length(sub2item.subItem)!=0}">
                                <a href="#">
                                    <img src="<%=cpath%>/images/icons/HasItems.gif"><c:out value="${sub2item.menuitem.menuName}"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${sub2item.menuitem.menuUrl==null||sub2item.menuitem.menuUrl==''}">
                                    <a>
                                        <img src="<%=cpath%>/images/icons/NoUrl.gif"><c:out value="${sub2item.menuitem.menuName}"/>
                                    </a>
                                </c:if>
                                <c:if test="${sub2item.menuitem.menuUrl!=null||sub2item.menuitem.menuUrl!=''}">
                                    <c:if test="${sub2item.menuitem.menuDescription!='1'}">
                                        <a href="#menulink" rel='${sub2item.menuitem.menuId}' tar='<%=cpath%>/<c:out value="${sub2item.menuitem.menuUrl}"/>'>
                                            <img src="<%=cpath%>/images/icons/YesUrl.gif"><c:out value="${sub2item.menuitem.menuName}"/>
                                        </a>
                                    </c:if>
                                    <c:if test="${sub2item.menuitem.menuDescription=='1'}">
                                        <a href="#menulink" rel='${sub2item.menuitem.menuId}' tar='<%=bpath%>/<c:out value="${sub2item.menuitem.menuUrl}"/>'>
                                            <img src="<%=cpath%>/images/icons/YesUrl.gif"><c:out value="${sub2item.menuitem.menuName}"/>
                                        </a>
                                    </c:if>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <!--3级如果有下级显示文件图标，没有下级显示文件图标-->
                    <c:if test="${fn:length(sub2item.subItem)!=0}">
                        <c:forEach var="sub3item" items="${sub2item.subItem}">
                            <li class="nav_list_subitem summenu_<c:out value='${sub2item.menuitem.menuId}'/>" <c:if test="${fn:length(sub3item.subItem)!=0}"> onclick="$('.summenu_<c:out value="${sub3item.menuitem.menuId}"/>').toggle()"</c:if>>
                                <c:choose>
                                    <c:when test="${fn:length(sub3item.subItem)!=0}">
                                        <a href="#">
                                            &nbsp;&nbsp;<img src="<%=cpath%>/images/icons/HasItems.gif"><c:out value="${sub3item.menuitem.menuName}"/>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${sub3item.menuitem.menuUrl==null||sub3item.menuitem.menuUrl==''}">
                                            <a>
                                                &nbsp;&nbsp;<img src="<%=cpath%>/images/icons/NoUrl.gif"><c:out value="${sub3item.menuitem.menuName}"/>
                                            </a>
                                        </c:if>
                                        <c:if test="${sub3item.menuitem.menuUrl!=null||sub3item.menuitem.menuUrl!=''}">
                                            <c:if test="${sub3item.menuitem.menuDescription!='1'}">
                                                <a href="#menulink" rel='${sub3item.menuitem.menuId}' tar='<%=cpath%>/<c:out value="${sub3item.menuitem.menuUrl}"/>'>
                                                    &nbsp;&nbsp;<img src="<%=cpath%>/images/icons/YesUrl.gif"><c:out value="${sub3item.menuitem.menuName}"/>
                                                </a>
                                            </c:if>
                                            <c:if test="${sub3item.menuitem.menuDescription=='1'}">
                                                <a href="#menulink" rel='${sub3item.menuitem.menuId}' tar='<%=bpath%>/<c:out value="${sub3item.menuitem.menuUrl}"/>'>
                                                    &nbsp;&nbsp;<img src="<%=cpath%>/images/icons/YesUrl.gif"><c:out value="${sub3item.menuitem.menuName}"/>
                                                </a>
                                            </c:if>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <!--4级如果有下级显示文件图标，没有下级显示文件图标-->
                            <c:if test="${fn:length(sub3item.subItem)!=0}">
                                <c:forEach var="sub4item" items="${sub3item.subItem}">
                                    <li class="nav_list_subitem summenu_<c:out value='${sub2item.menuitem.menuId}'/> summenu_<c:out value='${sub3item.menuitem.menuId}'/>" <c:if test="${fn:length(sub4item.subItem)!=0}"> onclick="$('.summenu_<c:out value="${sub4item.menuitem.menuId}"/>').toggle()"</c:if>>
                                        <c:choose>
                                            <c:when test="${fn:length(sub4item.subItem)!=0}">
                                                <a href="#">
                                                    &nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=cpath%>/images/icons/HasItems.gif"><c:out value="${sub4item.menuitem.menuName}"/>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${sub4item.menuitem.menuUrl==null||sub4item.menuitem.menuUrl==''}">
                                                    <a>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=cpath%>/images/icons/NoUrl.gif"><c:out value="${sub4item.menuitem.menuName}"/>
                                                    </a>
                                                </c:if>
                                                <c:if test="${sub4item.menuitem.menuUrl!=null||sub4item.menuitem.menuUrl!=''}">
                                                    <c:if test="${sub4item.menuitem.menuDescription!='1'}">
                                                        <a href="#menulink" rel='${sub4item.menuitem.menuId}' tar='<%=cpath%>/<c:out value="${sub4item.menuitem.menuUrl}"/>'>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=cpath%>/images/icons/YesUrl.gif"><c:out value="${sub4item.menuitem.menuName}"/>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${sub4item.menuitem.menuDescription=='1'}">
                                                        <a href="#menulink" rel='${sub4item.menuitem.menuId}' tar='<%=bpath%>/<c:out value="${sub4item.menuitem.menuUrl}"/>'>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=cpath%>/images/icons/YesUrl.gif"><c:out value="${sub4item.menuitem.menuName}"/>
                                                        </a>
                                                    </c:if>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</div>
<div class="content" id='wrap'>
<%--    <ul id="ment_www_zzjs_net">
        <li><em class="cut"></em><a onclick="sy()" href="javascript:;">关闭所有页签</a></li>
    </ul>--%>
    <table style="width:100%;">
        <tr>
            <td>
                <ul id="tabs">
                    <li class='current'>
                        <image src="images/tab_L.jpg"></image>
                        <div>
                            <a class="tab" id='hometab' onclick="refreshs();" href='#'>首页</a>
                        </div>
                        <image src="images/tab_R.jpg"></image>
                    </li>
                </ul>
                <div id="content">
                    <iframe id='hometab_content' name="hometab_content" style='padding: 0 0 0 0;border:0px solid #000000;' src='content'></iframe>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>