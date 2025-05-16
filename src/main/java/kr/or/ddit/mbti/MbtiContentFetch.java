package kr.or.ddit.mbti;

import java.io.IOException;
import java.util.Properties;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fet/mbti")
public class MbtiContentFetch extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = (Properties) application.getAttribute("mbtiProps");
		
		String mbtiType = req.getParameter("mbtiType");
		if(mbtiType==null || mbtiType.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "필수 파라미터 누락");
			return;
		}
		
		if(!props.containsKey(mbtiType)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 mbti를 입력했습니다.");
			return;
		}
		String prefix = "/WEB-INF/views/mbti/mbti/";
		String suffix = ".html";
		String path = prefix + mbtiType + suffix;
		req.getRequestDispatcher(path).include(req, resp);
		
	}
}
