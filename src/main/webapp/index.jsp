<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="jakarta.tags.core" prefix="c" %>
 <%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to the King of IronFist Tournament Challenge</h1>
<h1>로그인 되어있는 사용자 : ${pageContext.request.userPrincipal.realUser.memName }</h1>
<pre>
	차후에 web filter 구조를 통해 Principal 구현체로+ 인증된 사용자를 표현할 예정.
</pre>
<c:set value="${pageContext.request.userPrincipal}" var="principal" />
<c:set value="${principal.realUser.memBir}" var="memBir" />
<c:set value="${fn:substring(memBir, 5, 10)}" var="subMemBir" />

<c:if test="${not empty principal}">
	${principal.realUser.memName }(${principal.realUser.memMail })(${subMemBir}) 님 <a href="<c:url value="/login/logout"/>">로그아웃</a>
</c:if>

<c:if test="${empty principal}">

	<a href="<c:url value="/login/loginForm.jsp"/>">로그인 하러가기</a>
</c:if>

</body>
</html>