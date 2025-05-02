package kr.or.ddit.calculator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import kr.or.ddit.enumkg.OperatorType;

@WebServlet("/calc/calculateForm.do")
public class CalculateForm extends HttpServlet{
	OperatorType opEnum = null;
}
