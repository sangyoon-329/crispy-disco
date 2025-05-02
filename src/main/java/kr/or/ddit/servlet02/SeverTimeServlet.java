package kr.or.ddit.servlet02;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup = 1, value="/server-time")
public class SeverTimeServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.printf("%s 객체 초기화", this.getClass().getSimpleName());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Date : epoch time 방식< 1970.1.1.00:00:00 부터 밀리세컨드로 환산된 시간.
		// java.time Time Api는 UTC(세계 표준시)방식으로 시간 환산. 시간의 단위별로 처리 객체가 분리됨.
		// immutable(불변) 객체로 운영됨.
		// new로 생성 안하고 객체를 생성하는 factory 메소드를 활용함.
		//Year, YearMonth, LocalDate, LocalDateTime, ZonedDateTime
		LocalDateTime now =  LocalDateTime.now();
		resp.getWriter().println(now);
	}
	
}
