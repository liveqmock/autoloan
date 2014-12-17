<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <%@include file="../../jsfile.jsp"%> --%>
<title>列表页</title>
</head>

<script type="text/javascript">
  
//页面加载
$(document).ready(function(){
			loadGrid();
});

/**
$(function(){
	loadGrid();
});
*/
//加载表格datagrid
function loadGrid()
{
	//加载数据
	$('#cxdm').datagrid({
				width: 'auto',
				height:300,				
				striped: true,
				singleSelect : true,
				url:'${ctx}/testEasyUI/queryData',
				//queryParams:{},
				loadMsg:'数据加载中请稍后……',
				fitColumns: true,
				pagination: true,
				rownumbers: true,	
				columns:[[
				    {field:'applyId',title: '行号',align: 'center',width: 100},
					{field:'clientName',title: '姓名',align: 'center',width: 100
				    	/**,
						//添加超级链，并将来文号作为参数传入
						formatter:function(val,rec){
							//alert(rec.adviceid);
		                	return "<a href='jsp/proposal/psconsultview.jsp?id="+rec.adviceid+"'>"+val+"</a>";
		               }
				        */
					},
					{field:'idNumber',title: '身份证号',align: 'center',width: 200},
					{field:'carBrand',title: '车牌号码',align: 'center',width: 100},
					{field:'cityName',title: '所属城市',align: 'center',width: 100}															
				]]
			});
}



       //为loadGrid()添加参数
		var queryParams = $('#cxdm').datagrid('options').queryParams;
	    queryParams.who = who.value;
	    queryParams.type = type.value;
	    queryParams.searchtype = searchtype.value;
	    queryParams.keyword = keyword.value;
	    //重新加载datagrid的数据
	    $("#cxdm").datagrid('reload');




</script>
<body>
	<table id="cxdm"></table>
</body>
</html>