package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Properties;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Lambda로 표현할 수 있는 functional interface 종류
 * () -> {}
 * 1. Consumer : 하나의 인자를 받고, 그에 대한 실행함수를 표현. 반환값이 없음. 
 * 2. Function : 하나의 인자를 받고, 그에 대한 실행이 되면, 새로운 객체가 반환됨.
 * 3. Predicate : 하나의 인자를 받고, 그에 대한 필터링을 수행하고, 최종 boolean이 반환됨.
 * 4. Supplier : 인자가 없고, 새로운 객체가 생성되고 반환됨.
 * 
 * MIME (Multipurpose Internet Mail Extentsion)
 * 동적 자원? 사용자의 요청이 발생했을 때 실행처리의 결과물로 전송되는 응답 컨텐츠
 * 			요청에 따라 다양한 형태의 컨텐츠가 존재할 수 있음.
 * 전송 자원에 대해 해당 자원의 타입이나 형식에 대한 정보를 함께 제공할 수 있어야 함.
 * --> MIME 텍스트로 표현 가능함.
 * MIME 형식 : main_type/sub_type;charset=encoding
 * 		MIME을 통해 컨텐츠를 수신한 이후 사용 방식이 결정될 수 있음.
 * ex) text/html;charset=UTF-8
 * ex) text/plain;charset=UTF-8
 * ex) text/javascript <script type="text/javascript">tag body</script>
 * ex) text/css <style type="text/css">tag body</style>
 * ex) image/jpeg[gif,png,bmp]
 * ex) video/mpeg
 * ex) audio/mp3 
 * ex) application/json[xml];charset=UTF-8
 */
@WebServlet("/systemProps")
public class SystemPropertiesServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.printf("%s 초기화 완료\n", this.getClass().getSimpleName()); // this는 servlet 이름
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doGet(req, resp); 405 에러 유발
		System.out.printf("현재 객체의 hashcode: : %s, 현재 쓰레드 : %s\n", this.hashCode(), Thread.currentThread().getName());
		
//		String javaVersion = System.getProperty("java.version");
//		System.out.println(javaVersion);
		Properties systemProps = System.getProperties();
//		for(Entry<Object, Object> prop: systemProps.entrySet()) {
//			Object key = prop.getKey();
//			Object value = prop.getValue();
//			System.out.printf("%s : %s\n", key, value);
//		}
		StringBuffer html = new StringBuffer();
		
		html.append("<html>                        ");
		html.append("<body>                        ");
		html.append("<table>                       ");
		html.append("<thead>                       ");
		html.append("	<tr>                       ");
		html.append("		<th>프로퍼티 명</th> ");
		html.append("		<th>프로퍼티 값</th>");
		html.append("	</tr>                      ");
		html.append("</thead>                      ");
		html.append("<tbody>                       ");
		
		String pattern = "<tr><td>%s</td><td>%s</td></tr>";
		
		systemProps.forEach((k, v)->{
//			System.out.printf("%s : %s\n", k, v);
			html.append(
				String.format(pattern, k, v)
			);
		});
		
		html.append("</tbody>");
		html.append("</table>");
		html.append("</body> ");
		html.append("</html> ");
		
		resp.setContentType("text/html;charset=UTF-8");
		// try~with resource문법
		try(
			PrintWriter out = resp.getWriter();	//with resource구문 안에 있는건 바로 close됨
		){
			out.print(html);
		}
	}
}
