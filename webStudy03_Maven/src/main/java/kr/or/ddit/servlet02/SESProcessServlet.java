package kr.or.ddit.servlet02;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/sesProcess")
public class SESProcessServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ses라는 파라미터를 받아야 함
		String sesMember = request.getParameter("sesMember");
		//파라미터 검증
		
		Map<String, String[]> memberMap = new LinkedHashMap<>();
		memberMap.put("a001", new String[]{"바다", "/ses/bada.jsp"});
		memberMap.put("a002", new String[]{"유진", "/ses/yujin.jsp"});
		memberMap.put("a003", new String[]{"슈", "/ses/shu.jsp"});
		
		if(StringUtils.isNotBlank(sesMember)){
			//어떤ㅇ ㅔㄴ트리에 포함되는지 or 어떤엔트리에도 없는지
			if(!memberMap.containsKey(sesMember)){//트루면ㅇ ㅣㅆ다 false면 ㅇ벗음 그러므로 클라이언트의잘못
				//잘못된 인식을 주고 싶으면 400 실수를 인식시키고 싶으면 404
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 멤버는 존재하지 않음");
				return;
			}
			String[] record = memberMap.get(sesMember); //한명이 갖고있ㄴ는 어쩌고저쩌고
//	 		String path = "/05"+ record[1]; //도착지
			String path = "/WEB-INF"+ record[1]; //도착지
			
			RequestDispatcher rd = request.getRequestDispatcher(path);//서버사이드 절대 경로
			rd.forward(request, response);
//	 		rd.include(request, response);
			
//	 		response.sendRedirect(request.getContextPath() + path); //이게 무슨 방식이었죠?
			return;
		}else {
			response.sendError(400, "어떤 멤버가 필요한가??");
		}
//	 	Iterator<String> it = memberMap.keySet().iterator();
			
//	 		while(it.hasNext()){
	// // 			멤버들 key
//	 			String pk = it.next();
	// // 			값을 가져오고 싶다!
//	 			String name = memberMap.get(pk)[0];
	}
}
