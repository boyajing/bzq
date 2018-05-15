
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
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link href="<%=cpath%>/js/zTree-3.5.29/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet">

    <%--<link href="<%=cpath%>/js/zTree-3.5.29/css/demo.css" type="text/css" rel="stylesheet">--%>

    <script type="text/javascript" src="<%=cpath%>/js/jquery-1.7.2.min.js"></script>
    <script type ="text/javascript" src="<%=cpath%>/js/zTree-3.5.29/js/jquery.ztree.all.min.js"></script>
    <style type="text/css">
        /*图标大小*/
        .ztree li span.button.switch {
            height: 20px;
            width: 20px;
        }
        .ztree * {
            font-size: 16px;/*字体大小*/
        }
        .ztree li a.curSelectedNode {
            height: 22px; /*修改选中背景色大小*/
        }
    </style>
    <script type="text/javascript">
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            treeNodeKey: "id",               //在isSimpleData格式下，当前节点id属性
            treeNodeParentKey: "pId",        //在isSimpleData格式下，当前节点的父节点id属性
            showLine: true,                  //是否显示节点间的连线
            checkable: true,                //每个节点上是否显示 CheckBox
            callback: {
                beforeClick: beforeClick1,
                onClick: onClick1
            }
        };
        var zNodes =[];

        $(document).ready(function(){
            $.ajax({
                url:"<%=cpath%>/jeda/org/org_zTree.json",
                type:"GET",
                data:{},
                success:function(data){
                    zNodes= data.result;
                    $.fn.zTree.init($("#tree1"), setting, zNodes);
                },
                error:function(erro){
                }
            });


        });
        var log, className = "dark";
        function beforeClick1(treeId, treeNode, clickFlag) {
            className = (className === "dark" ? "":"dark");
//            alert("[ "+getTime()+" beforeClick ]&nbsp;&nbsp;" + treeNode.name );
            return (treeNode.click != false);
        }
        function onClick1(event, treeId, treeNode, clickFlag) {
//            alert("[ "+getTime()+" onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag===1 ? "普通选中": (clickFlag===0 ? "<b>取消选中</b>" : "<b>追加选中</b>")) + ")");
//            alert(treeId + treeNode.id + treeNode.name);
            window.parent.orgtreeClick(treeNode.id);
        }
        function getTime() {
            var now= new Date(),
                h=now.getHours(),
                m=now.getMinutes(),
                s=now.getSeconds();
            return (h+":"+m+":"+s);
        }
    </script>
</head>
<body>
            <ul id="tree1" class="ztree" >

            </ul>
</body>
</html>
