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
    <!-- 配置文件 -->
    <script type="text/javascript" src="plugins/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="plugins/ueditor/ueditor.all.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
//      var ue = UE.getEditor('container');
        var ue = UE.getEditor('container', {
            //初始化一些配置，覆盖ueditor.config.js
            autoHeightEnabled: true
        });

        //对编辑器的操作最好在编辑器ready之后再做
        ue.ready(function() {
            //设置编辑器的内容
//          ue.setContent('hello');
            //获取html内容，返回: <p>hello</p>
            var html = ue.getContent();
            //获取纯文本内容，返回: hello
            var txt = ue.getContentTxt();

        });
    </script>
<div style="width:1000px;margin:0 auto" >
<h1>万恶的Demo</h1>
    <form id="actform" action="save_form" method="post">
        <script id="container" name="content" type="text/plain">${content}</script>
        
        
    </form>
</div>
</body>
</html>
