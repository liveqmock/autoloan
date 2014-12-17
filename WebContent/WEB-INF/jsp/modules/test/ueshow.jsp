<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en-US">

<head>
    <meta charset="UTF-8">
    <title>ueditor demo</title>
    <base href="<%=basePath%>">
    <script type="text/javascript">var basePath='<%=basePath%>';</script>
</head>

<body>
    <script type="text/javascript" src="plugins/ueditor/ueditor.parse.js"></script>
        <script>
        uParse('#preview',{
            chartContainerHeight:500
        })
    </script>
<div style="width:1000px;margin:0 auto" >
<h1>万恶的Demo</h1>
<h6>=========万恶的分隔线，以下开始是正文=================</h6>
<div id="preview">
${content}
</div>
</div>
</body>
</html>
