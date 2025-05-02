<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		border-collapse: collapse;
	}
	th, td{
		border : 1px solid black;
	}
</style>
</head>
<body>
<form method="post">
	<select name="minDan">
	<c:forEach begin="2" end="9" var="dan">
		<option value="${dan }">${dan }단</option>
	</c:forEach>
	</select>
	<select name="maxDan">
	<c:forEach begin="2" end="9" var="dan">
		<option value="${dan }">${dan }단</option>
	</c:forEach>
	</select>
	<button type="submit">전송</button>
</form>
<!-- 2단부터 9단까지의 구구단 출력. -->
<!-- 승수 : 9까지 증가. 출력 형식 : 2 * 3= 6 -->
<c:if test="${not empty minDan && not empty maxDan}">
<table>
<c:forEach begin="${minDan }" end="${maxDan }" var="dan">
	<tr>
	<c:forEach begin="1" end="9" var="s">
		<td>${dan } *${s } =${dan * s}&nbsp;&nbsp;</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
</c:if>
<script type="text/javascript">
	document.querySelector('[name="minDan"]').value = `${minDan }`;
	document.querySelector('[name="maxDan"]').value = `${maxDan }`;
</script>
	<c:remove var="minDan" scope="session"/>
	<c:remove var="maxDan" scope="session"/>
</body>
</html>