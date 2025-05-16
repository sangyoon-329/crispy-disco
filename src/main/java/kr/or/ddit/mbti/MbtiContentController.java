package kr.or.ddit.mbti;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mbti")
public class MbtiContentController extends HttpServlet{
	private ServletContext application;
	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties mbtiProps = (Properties) application.getAttribute("mbtiProps");
		
		String mbtiType = req.getParameter("mbtiType");
		String layout = req.getParameter("layout");
		
		if(mbtiType == null || mbtiType.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		if(!mbtiProps.containsKey(mbtiType)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, 
					String.format("%s에 해당하는 mbti는 없음", mbtiType));
			return;
		}
		String prefix = "/WEB-INF/views/mbti/mbti/";
		String suffix = ".html";
		String path = prefix + mbtiType + suffix;
		String contentPage = path;
		
		if(layout!=null && layout.equals("none")) {
			// 비동기 요청으로 fragment를 요청한 경우
			req.getRequestDispatcher(path).forward(req, resp);
		}else {
			// 동기 요청으로 모듈화된 페이지를 요청한 경우
			req.setAttribute("contentPage", contentPage);
			req.getRequestDispatcher("/WEB-INF/views/mbti/mbtiModule/layout.jsp").forward(req, resp);
		}
		
	}
}
