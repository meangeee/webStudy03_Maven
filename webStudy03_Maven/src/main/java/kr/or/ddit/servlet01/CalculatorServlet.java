package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
	
	public enum OperatorType {
		PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), OTHER("");

		OperatorType(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return this.name;
		}

		public static OperatorType searchOperator(String op) {
			OperatorType result = OTHER;
			for (OperatorType tmp : values()) {
				if (op.contains(tmp.name())) {
					result = tmp;
					break;
				}
			}
			return result;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 검증??? 해야함??
		String left = req.getParameter("leftOp");
		String right = req.getParameter("rightOp");
		String operator = req.getParameter("operator");
		String name = req.getParameter("name");
		
		
		OperatorType op = OperatorType.searchOperator(operator);
		String opr = op.getName();
		
		System.out.println(op.name());
		System.out.println(op.getName());
		
		int leftOp = 0;
		int rightOp = 0;
		
		if (left != null && right != null && left.matches("\\d+") && right.matches("\\d+")) {
			leftOp = Integer.parseInt(left);
			rightOp = Integer.parseInt(right);
		}

		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("<head>");
		html.append("<body>");
		html.append(name);
		html.append("</head>");
		html.append("<br>");
		
		if(opr.equals("+")) {
			html.append(left + opr + right + "=" + (leftOp+rightOp));
		}else if(opr.equals("-")) {
		
			html.append(left + opr + right + "=" + (leftOp-rightOp));
		}else if(opr.equals("*")) {
			
			html.append(left + opr + right + "=" + (leftOp*rightOp));
			
		}else if(opr.equals("/")) {
			html.append(left + opr + right + "=" + (leftOp/rightOp));
			
		}
		
		html.append("</body>");
		html.append("</html>");

		try (PrintWriter out = resp.getWriter();) {
			out.println(html);
		}

	}

}
