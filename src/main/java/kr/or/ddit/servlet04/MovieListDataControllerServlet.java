package kr.or.ddit.servlet04;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 웹어플리케이션의 아키텍처 
 * Model1 : 요청과 응답에 대한 모든 책임을 하나의 객체(servlet, jsp)로 처리할 때.
 * Model2 : 요청에 대한 처리를 담당하는 객체(controller)와 
 * 			동적 응답을 생성하는 객체(view)가 분리된 구조.
 * 			***HTML 컨텐츠를 생성하는 경우, controller(servlet), view(jsp)
 * 			템플릿 소스와 동적 데이터를 결합할 수 있는 주체가 필요함 - 템플릿 엔진
 * 			서버 사이드 템플릿 엔진(SSR) : jsp, thymleaf, mustache, freemarker, velocity
 * 			클라이언트 사이드 템플릿 엔진(CSR) : React, Vuejs, AngularJS 
 * 			JSON 컨텐츠를 생성하는 경우, controller(servlet), view(servlet)
 *  최종 HTML의 형태가 완성되는 위치에 따른 랜더링 방식
 *  SSR(Server Side Rendering) : 최종 HTML이 서버에서 만들어지고, 한번의 응답으로 전송된 후,
 *  							클라이언트는 랜더링만 수행함.
 *  CSR(Client Side Rendering) : 초기 로딩시에 템플릿 HTML이 응답으로 전송되고, 
 *  							스크립트 기반의 비동기 요청으로 데이터 응답을 수신한 후,
 *  							클라이언트가 템플릿과 데이터를 결합하여 최종 HTML을 생성 후,
 *  							최종적으로 랜더링함.
 *  
 */
@WebServlet("/04/movieListData")
public class MovieListDataControllerServlet extends HttpServlet{
	private ServletContext application;
	private String folderPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		folderPath = application.getInitParameter("movieFolder");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File folder = new File(folderPath);
		String[] children = folder.list((file, name)->{
			String mime = application.getMimeType(name);
			return mime !=null && mime.startsWith("video");
		});
		
		//Mashalling : native data의 표현 구조를 공통 메시지 형식(json)으로 바꾸는 작업
		Gson gson = new Gson();
		String json = gson.toJson(children);
		
		resp.setContentType("application/json;charset=UTF-8");
		try(
			// Closable 객체 생성 및 선언 구문
			PrintWriter out = resp.getWriter();
		){
			out.print(json);
		}
	}
}
