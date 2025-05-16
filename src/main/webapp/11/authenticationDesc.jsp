<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>웹어플리케이션의 인증 시스템</h4>
<pre>
	보안(auth) 처리 : 인증(Authentication) + 인가(Autorization)
		인증 : 신원 확인 처리(누구인지-username, 본인이 맞는지-credential)
		인가 : 보호 자원에 대해 해당 자원에 대해 접근 권한을 부여 받았는지 확인.
		
	인증 방식(인증된 사용자의 정보를 유지하는 방식)의 종류
	1. 헤더 기반의 인증 : 서버의 부하가 적고, 상대적으로 구현이 단순함. 로그아웃 표현 불가.
		- 미인증 사용자가 보호자원에 접근하면, 
		-> 401(UnAuthorized) + [www-Authenticate : 인증방식]
		-> 브라우저의 기본 인증 UI 사용 (username/password 입력)
		1) Basic : 인증된 사용자의 요청에 [Authorization : Basic base64인코딩(username/password)]
					보안에 굉장히 취약함.
		2) Digest : 인증된 사용자의 요청에 
				[Authorization : Digest base64인코딩(one way - encrypting (username/password))]
	2. 세션 기반의 인증 
		FORM : 로그인 폼이 필요하고, 세션을 통해 인증 정보가 유지되는 방식.
			 헤더 기반의 인증보다 세션을 유지하기 위한 서버 부하가 발생함.
			 요청과 응답에 인증 정보가 전송되지 않기 때문에 상대적으로 안전한 방식이고, 
			 로그 아웃을 표현할 수 있음.
		==> 자바로 구현된 웹어플리케이션에서 클라이언트의 인증 정보는 Principal 구현체로 표현됨.
		
	3. oAuth ==> 
</pre>
</body>
</html>