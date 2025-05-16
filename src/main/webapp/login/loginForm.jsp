<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/login/loginCheck"
		method="post">
		<input type="text" name="username" placeholder="사용자의 식별자" /> 
		<input type="text" name="password" placeholder="본인임을 증멸할 credential" />
		<button type="submit">로그인</button>
	</form>
	<c:if test="${not empty message }">

	<script>
		alert('${message}')
	</script>
	<c:remove var="message" scope="session"/>
	</c:if>

	<c:if test="${empty message }">


	</c:if>


</body>
</html>