<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>웹 어플리케이션에서 흐름 제어</h4>
<pre>
	흐름 제어 : 하나의 웹 객체에서 또 다른 웹 객체로 이동 방식. 
	HTTP : Connect-less, State-less 
		: 웹이라는 공간의 특성상 서버의 부하를 줄이기 위해, 
		http는 기본적으로 연결을 지향하지 않는 특성을 갖는다.
		(클라이언트와 서버 사이의 요청이 발생하기 전, 3way handshake 방식으로 수립된 connection은 
		하나의 요청에 대한 1:1 의 응답이 전송된 후 종료[close]됨)
		(연결이 종료된 후에는 서버사이드에서 해당 요청에 대한 모든 데이터가 사라짐.)
		c.f) connect-full : 의도적으로 open을 하고 의도적으로 close 하는 걸 표현할 수 있는 방식으로
							주로 데이터베이스에서 활용되는 연결 형태.
		
	request dispatch(요청 분기) : A에서 B로 이동 할 때, 연결이 종료되지 않고, 
								응답이 전송되지 않은 상태에서 이동하는 방식
								기존에 A 쪽으로 발생한 request가 그대로 B로 전달되는 형태.
	<%--
		String path = "/10/dest.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
//		rd.forward(request, response);
		rd.include(request, response);
	--%>
		- forward : 요청에 대한 최종 처리를 B 에게 위임하는 방식(서버사이드 위임). - Model2 구조에 활용.
		- include : 하나의 응답 페이지를 여러개의 view layer를 이용해서 구성하는 페이지 모듈화에 활용.
	redirect location(위치 재지정) : A 방향으로 요청이 발생하고,
								A에서 body가 없는 응답이 전송됨(line-3xx, header-location : B).
								Location 헤더에 설정된 B 쪽으로 새로운 요청을 전송하고, 
								B 에서 최종(body가 포함된) 응답(200)이 전송됨.
	<%
		String location = request.getContextPath() + "/10/dest.jsp";
		response.sendRedirect(location);
	%>
</pre>
</body>
</html>