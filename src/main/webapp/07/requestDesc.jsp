<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> HTTP request packaging : HttpServletRequest</h4>
<%



%>
<pre>
	 Request Line : 수신자(서버의 대상 자원에 대한 표현, URL)
	 				서버의 자원을 대상으로 한 액션의 표현(Request method)
			<%=request.getRequestURI()%>, <%=request.getMethod() %>
			${pageContext.request.requestURI }, ${pageContext.request.method}
	 				Create(등록) : POST
	 							ex) form action 속성(url) method 속성
	 				Read(조회) : GET (default method)
	 						   ex) a href 속성, img src 속성, script src 속성
	 				Update(수정) : PUT / PATCH
	 				Delete(삭제) : DELETE
	 							ex) member/ memberInsert/.do,
					Preflight 요청 : OPTION
					HEAD		: 응답을 수신 할 때, 응답의 Body 없이 수신할 요청의 표현
					TRACE		: 서버를 대상으로 디버깅할때 사용되는 메소드
	 Request Header : 요청에 대한 부가정보를 표현하는 메타데이터 영역 (name : value)
	 <%=request.getHeader("accept") %>
	<%--  ${pageContext.request.getHeader("Accept") } --%>
	--> ${header['accept'] }, ${header.accept} }
	 ${header['accept-language'] }
	 \${header.accept-language}
	 	* Accept : 응답에 대한 조건의 표현
	 		ex) Accept : application/json (ajax - dataType : json)
	 		ex) Accept : application/xml (ajax - dataType : xml)
	 		ex) Accept : text/html (ajax - dataType : html)
	 		ex) Accept : text/plain (ajax - dataType : text)
 		  Accept-Language : 언어, 국가 표현(locale)
 		  	ex) Accept-Language : ko-KR (한글-한국), en-US (영어-미국), en-UK (영어-영국)
 		  Accpet-Encoding : 응답의 압축 포맷 표현 (gzip)	
 		  
 		* Content -* : 요청 컨텐츠(content body)에 대한 표현
 			Content-Length : content body 의 길이
 			Content-Type : Content Body 의 MIME
 			ex) Content-Type : application/json
 			ex) Content-Type : x-www-form-urlencoded 요청 파라미터 전송
 			ex) Content-Type : multipart/form-data
		* user-Agemt : 사용자의 OS, 렌더링 엔진, 브라우저 정보 표현
		 
	 Reqest Body(Message Body, Content Body)
	 	* method = "post", content-* 헤더가 존재하는 경우 형성됨.
	 	1) 요청 파라미터
	 	2) 멀티파트
	 	3) 요청페이로드
	 
</pre>
<form method="post" enctype="application/json">
<input type="text" name="dummy">
<input type="text" name="dummy2">
<button type="submit">전송</button>
</form>
<table>
	<thead>
		<tr>
			<th>헤더이름</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${header }" var="entry">
			<tr>
				<td>${entry.key }</td>
				<td>${entry.value }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>