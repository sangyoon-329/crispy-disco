package kr.or.ddit.mbti;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Properties;

import com.google.gson.Gson;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fet/mbti/form")
public class MbtiFormFetch extends HttpServlet {
	private Properties mbtiProps;
	private ServletContext application;

	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
		String qualifiedName = "/kr/or/ddit/mbti/mbti.properties";

		try (InputStream is = MbtiFormController.class.getResourceAsStream(qualifiedName);
				Reader reader = new InputStreamReader(is, "UTF-8")) {
			mbtiProps = new Properties();
			mbtiProps.load(reader);
			application.setAttribute("mbtiProps", mbtiProps);
		} catch (IOException e) {
			// 예외 전환 정책
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		String result = gson.toJson(mbtiProps);
		
		try (PrintWriter out = resp.getWriter()) {
			out.println(result);
		}
	}
}
