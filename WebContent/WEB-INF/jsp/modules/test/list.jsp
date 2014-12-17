<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表页</title>
</head>
<body>
	<c:if test="${!empty userList}">
		<c:forEach items="${userList}" var="item" varStatus="status">
			<p>
				${1 + status.index} ;
				<c:out value='${item.userAccount}' />
				；
				<c:out value='${item.userName}' />
				；
				<c:out value='${item.userNo}' />
				；
				<c:out value='${item.title}' />
			</p>
		</c:forEach>
	</c:if>
</body>
</html>