package kr.or.ddit.servlet01;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sampleProcess")
public class SampleProcessServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//doGet일때는 적용 안됨. 바디 ↓
		req.setCharacterEncoding("UTF-8");
		
		//파라미터데이터가 필요함 그래서 req
		String numParam = req.getParameter("numParam");
		//검증
		if(numParam!=null && numParam.matches("\\d+")) {
			System.out.println(Integer.parseInt(numParam) * 300);
		}
		String textParam = req.getParameter("textParam");
		String radioParam = req.getParameter("radioParam");
		String[] checkParam = req.getParameterValues("checkParam");
		String selectParam1 = req.getParameter("selectParam1");
		String[] selectParam2 = req.getParameterValues("selectParam2");
		Map<String, String[]> pMap =  req.getParameterMap();
		Set<String> nameSet = pMap.keySet();
		//컬렉션 뷰
		Iterator<String> it = nameSet.iterator();
		while (it.hasNext()) {
			String pName = (String) it.next();
			//p를 넘겨서 value를 받음
			String[] values = pMap.get(pName);
			System.out.printf("%s : %s\n", pName, Arrays.toString(values));
		}
	}
	
}
	
