package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try (InputStream is = req.getInputStream();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
//
//			String tmp = null;
//			while ((tmp = reader.readLine()) != null) {
//				System.out.printf("==============>%s\n", tmp);
//
//
//			}
//		}

		// req를 이용해서 무언가를 가져와야함
		String minParam = req.getParameter("minDan");
		String maxParam = req.getParameter("maxDan");

		// 검증
		int minDan = 2;
		int maxDan = 9;
		if (minParam != null && minParam.matches("[0-9]{1,2}") && maxParam != null && maxParam.matches("[0-9]{1,2}")) {
			minDan = Integer.parseInt(minParam);
			maxDan = Integer.parseInt(maxParam);
		}

		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer template = readTemplate("gugudan.tmpl");

		StringBuffer gugudan = new StringBuffer();

		String ptrn = "<td>%d*%d=%d</td>";
		for (int dan = minDan; dan <= maxDan; dan++) {
			gugudan.append("<tr>");
			for (int mul = 1; mul <= 9; mul++) {
				gugudan.append(String.format(ptrn, dan, mul, dan * mul));
			}
			gugudan.append("</tr>");
		}

		int idx = template.indexOf("@gugudan");
		StringBuffer html = template.replace(idx, idx + "@gugudan".length(), gugudan.toString());
		idx = html.indexOf("@cnt");
		html = html.replace(idx, idx + "@cnt".length(), (9 - 1 + 1) + "");

		try (PrintWriter out = resp.getWriter();) {
			out.println(html);
			out.println(getServletContext().hashCode());
		}

	}

	private StringBuffer readTemplate(String name) throws IOException {
		StringBuffer template = new StringBuffer();
		try (
				// 자료가 오갈 때 통로 느낌?
				InputStream is = GugudanServlet.class.getResourceAsStream(name);

				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));) {
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				template.append(tmp + "\n");
			}
		}
		return template;
	}
}
