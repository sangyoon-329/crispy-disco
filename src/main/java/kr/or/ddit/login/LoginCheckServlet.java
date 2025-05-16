package kr.or.ddit.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.internal.StringUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * 해당 객체가 보유한 저장소를 가지고 있는 객체들..저장소들은 보유 객체와 생명주기가 동일함.
 * 
 * request (HttpServletRequest) : 요청을 전송한 클라이언트와 해당 요청에 대한 모든 정보를 가진 객체(http request package)
 * response(HttpServletResponse): 서버가 클라이언트에게 전송하는 응답에 대한 모든 정보를 가진 객체로 request와 1:1 구조를 가짐.(http response package)
 * --> request scope (Map) : name/value로 구성된 attribute의 저장소
 * application(ServeltContext) : 서블릿 컨테이너와 하나의 컨텍스트에 대한 모든 정보를 가진 객체로 동일 컨텍스트 내에 싱글턴으로 운영됨.
 * --> application scope (Map) : name/value로 구성된 attribute의 저장소
 * 
 * 한 클라이언트가 독점할 수 있는 저장소가 필요함.
 * session(HttpSession) : 어플리케이션 사용 시작부터 종료까지의 한 세션의 정보를 가진 객체
 * --> session scope
 * 
 * setAttribute(String name, Object value)
 * Object value = getAttribute(String name) --> EL을 사용하는 경우가 일반적, ${attributeName}
 * removeAttribute(String name) : 생존 범위가 명확하지 않는 scope에 있는 데이터에 대해 사용할 때. 
 */

@WebServlet("/login/loginCheck")
public class LoginCheckServlet extends HttpServlet{
	private AuthenticateService service = new AuthenticateServiceImpl();
	/**
	 * SRP(단일 책임의 원칙)
	 * O
	 * L
	 * I
	 * D 
	 * 
	 * 로그인 처리 과정
	 * 1. username / password 입력 - loginForm.jsp
	 * 2. 데이터 베이스로부터 username에 해당하는 회원 정보 조회 - 신원 확인
	 * 3. 저장된 비밀번호와 입력 받은 비밀번호 비교 - 본인 여부 확인
	 * 4. 상황에 따른 흐름 제어 구조 필요 
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 	1. 디코딩 세팅하기
		 	2. 필수 파라미터 2개 누락 검증
		 	3. 둘 중 하나가 누락되면 다시로그인 폼으로 보내기
		 */
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		String dest = null;
		String message = null;
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			dest = "/login/loginForm.jsp";
			message = "아이디나 비밀번호 누락";
		}else {
			MemberVO vo = new MemberVO();
			vo.setMemId(username);
			vo.setMemPassword(password);
			
			if(service.authenticate(vo)) {
				// 1. Principal 구현 객체 생성
				// 2. request.getUserPrincipal 에서 반환될 수 있도록 세팅.
//				--> Web Filter의 활용 
				session.setAttribute("authUser", vo);
				dest = "/";
			}else {
				dest = "/login/loginForm.jsp";
				message = "아이디와 비밀번호가 서로 다른 경우, 로그인 실패";
			}
		}
		if(StringUtils.isNotBlank(message)) {
			
			session.setAttribute("message", message);
		}
		
		String location = req.getContextPath() + dest;
		resp.sendRedirect(location);
	}
}
