<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ page import="java.io.*,java.util.*"%>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title></title>
        <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="stylesheet" type="text/css" href="<%=cpath%>js/bootstrap/css/bootstrap.css">

        <link rel="stylesheet" type="text/css" href="<%=cpath%>css/theme.css">
        <link rel="stylesheet" href="<%=cpath%>js/lib/font-awesome/css/font-awesome.css">

        <script src="<%=cpath%>/js/jquery-1.7.2.min.js" type="text/javascript"></script>

        <!-- Demo page code -->

        <style type="text/css">
            #line-chart {
                height:300px;
                width:800px;
                margin: 0px auto;
                margin-top: 1em;
            }
            .brand { font-family: georgia, serif; }
            .brand .first {
                color: #ccc;
                font-style: italic;
            }
            .brand .second {
                color: #fff;
                font-weight: bold;
            }
        </style>

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Le fav and touch icons -->
        <link rel="shortcut icon" href="../assets/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
    </head>

    <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
    <!--[if IE 7 ]> <body class="ie ie7 http-error"> <![endif]-->
    <!--[if IE 8 ]> <body class="ie ie8 http-error"> <![endif]-->
    <!--[if IE 9 ]> <body class="ie ie9 http-error"> <![endif]-->
    <!--[if (gt IE 9)|!(IE)]><!--> 
    <body class="http-error"> 
        <!--<![endif]-->  
        
      <%--  <%=exception.getClass()%>:<%=exception.getMessage()%>--%>

        <%
            String msg = exception.getMessage();
            if (msg.indexOf("####UMap Nantian#001####") != -1) {
        %>
        <div class="row-fluid">
            <div class="http-error" align="center">
                <h1>提示：</h1>
                <p class="info">当前用户尚未登录</p>
                <p id="seccount" class="info">3秒后转向登录界面</p>
                <p><i class="icon-home"></i></p>
            </div>
        </div>

        <script language="javascript">
            var c = 2;
            function counts() {
                document.getElementById("seccount").innerHTML = c + "秒后转向登录界面";
                c--;
                if (c == 0) {
                    window.top.location = "<%=cpath%>/";
                } else {
                    window.setTimeout(counts, 1000);
                }
            }
            window.setTimeout(counts, 1000);


        </script>
        <%
        } else if(msg.indexOf("####UMap Nantian#005####") != -1){
        %>
        <div class="row-fluid">
            <div class="http-error">
                <h1>提示</h1>
                <p class="info">当前用户被另一同名用户登录踢出</p>
                <p id="seccount" class="info">5秒后转向登录界面</p>
                <p><i class="icon-home"></i></p>
            </div>
        </div>

        <script language="javascript">
            var c = 5;
            function counts() {
                document.getElementById("seccount").innerHTML = c + "秒后转向登录界面";
                c--;
                if (c == 0) {
                    window.top.location = "<%=cpath%>/";
                } else {
                    window.setTimeout(counts, 1000);
                }
            }
            window.setTimeout(counts, 1000);


        </script>
        <%

        }else {
        %>


        <div class="row-fluid">
            <div class="http-error">
                <h1>提示</h1>
                <p class="info">系统发生内部错误,</p>
                <p><i class="icon-home"></i></p>

                <%
                    Enumeration<String> e = request.getHeaderNames();
                    String key;
                    while (e.hasMoreElements()) {
                        key = e.nextElement();
                    }
                    e = request.getAttributeNames();
                    while (e.hasMoreElements()) {
                        key = e.nextElement();
                    }
                    e = request.getParameterNames();
                    while (e.hasMoreElements()) {
                        key = e.nextElement();
                    }
                %>
                <%=request.getAttribute("javax.servlet.forward.request_uri")%><br>
                <%=request.getAttribute("javax.servlet.forward.servlet_path")%>
                <p>With the following stack trace:</p>
                <pre>
                    <%
                        exception.printStackTrace();
                        ByteArrayOutputStream ostr = new ByteArrayOutputStream();
                        exception.printStackTrace(new PrintStream(ostr));
                        out.print(ostr);
                    %>

        
                    <%
                        }
                    %>
        
    </div>
</div>

 
    
  </body>
</html>

