<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>자원의 식별 체계</h4>
<pre>
	자원의 위치에 따른 자원의 종류
	1. 파일 시스템 자원 : 파일 시스템 상의 절대 경로(물리 경로)를 통해 접근하는 자원
		ex) D:\00.medias\images\cat1.jpg
	2. 클래스패스 자원 : 빌드 및 실행 환경에 존재하는 자원으로
					클래스패스 이후의 경로를 qulified name의 형태로 접근하는 자원
		ex) kr.or.ddit.servlet01.HelloServlet .과 \는 같은 의미
		ex) /kr/or/ddit/dummy.xml
	3. 네트워크 자원(웹 자원) : 네트워크를 통해 서비스 되는 자원으로
					URL 이라는 식별 체계를 통해 접근하는 자원.
		ex) http://www.google.com:80/logos/2024/moon/moon_april-rc2/cta.png			
		
		URL : 서버식별"/"컨텐스트패스"/"컨텍스트내에서의 자원의패스
		URI (Uniform Resource Identifier)
			: 네트워크를 통해 서비스되는 자원을 표현하는 식별 체계에 대한 포괄적인 명칭
			URN(Naming) : 자원의 이름을 통해서 자원을 식별
			URC(Content): 자원의 속성으로 식별
			URL(Locator) : 서버의 설정으로 자원에 대한 위치가 논리적으로 결정된 식별체계
		
		schema(protocol) : //IP[Domain]:port - 서버에 대한 식별(자원의 출처-origin)
		origin 이후의 모든 경로 - path
		path : context path + resource path
		
		절대경로 : url을 full path로 표현
				origin이 생략된 path로 표현
				origin이 생략 가능한 경우 : location의 origin과 자원의 origin이 동일할 때.
		상대경로 : 현재 도큐먼트의 path를 기준으로 경로를 표기하는 방식.
</pre>
<img src="../resources/images/cat1.jpg">
<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg">
<script type="text/javascript" src=""></script>
<!-- css 파일은 invade 형태가 아니라서 기준이 자신이 됨 -->
</body>
</html>