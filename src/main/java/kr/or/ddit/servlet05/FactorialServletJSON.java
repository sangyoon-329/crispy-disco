package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ResponseCache;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/factorial")
public class FactorialServletJSON extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1. 의사코드(내가 해야하는걸 한글로 쓰기) 먼저 만들기 
		 * 2. op 파라미터 확보 
		 * 3. 파라미터 누락 여부 확인 : 누락됐다면, 400 상태 코드 전송
		 * 4. 문자열 파라미터를 숫자로 파싱 : 파싱이 안되는 파라미터 400 전송
		 * 5. {"result":55} 형태의 json 파싱
		 * 6. MIME 결정 후 응답 기록.
		 */
		String opParam = req.getParameter("op");
		
		if(opParam==null||opParam.trim().isEmpty()) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		
		try {
			int op = Integer.parseInt(opParam);
			long res = factorial(op);
			Map<String, Long> map = Map.of("result", res);
			
			Gson gson = new Gson();
			String result = gson.toJson(map);
		
			resp.setContentType("application/json;charset=UTF-8");
			
			try(
				PrintWriter out = resp.getWriter();
			){
				out.println(result);
			}
			
		} catch (NumberFormatException e) {
			resp.sendError(400, e.getMessage());
		}
	}
	
	private long factorial(int op) {
		if(op<=0) {
			throw new IllegalArgumentException();
		}
		if(op==1) return 1;
		return op * factorial(op-1);
	}
}
