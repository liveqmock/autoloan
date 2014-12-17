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
    <script type="text/javascript">
    var basePath='<%=basePath%>';
    window.UEDITOR_HOME_URL= '<%=path%>'+'/plugins/ueditor/';
    </script>
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
    <form id="actform" action="edit" method="post" target="_blank">
        <script id="container" name="content" type="text/plain"></script>
        <input type="submit" text="提交" />
        <button> just a button </button>
         <button onclick="var form = document.getElementById('actform');form.action='edit';form.submit();">展示并编辑</button>
         <button onclick="var form = document.getElementById('actform');form.action='show';form.submit();">只是展示</button>
        <input type="button" value="获取输入长度" onclick="alert(ue.getContentLength(true));"/>
        <input type="button" value="获取纯文本" onclick="alert(ue.getContentTxt());"/>
        <input type="button" value="获取html格式文本" onclick="alert(ue.getContent());"/>
        <input type="button" value="判断是否有内容" onclick="alert(ue.hasContents());"/>
        
    </form>
    
    <h1>传图传图传图传图传图</h1>
    <form id="picform" action="upload_file?action=lw_uploadimage" method="post" enctype="multipart/form-data" target="_blank">
        <input type="text" name="id" value="" />
        <input type="file" name="pic" />
        <input type="submit" text="提交" />
    </form>
</div>  
</body>
</html>
