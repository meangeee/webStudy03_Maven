package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/module/layout.do")
public class PageModulization extends HttpServlet{
	
	
	Properties service;
	//servlet은 기본적으로 singleton
	@Override
	public void init(ServletConfig config) throws ServletException {		
		super.init(config);
		//하드코딩안하려고
//		String serviceFile = getServletContext().getInitParameter("service");
		String serviceFile = "/kr/or/ddit/servlet05/service.xml";
		service = new Properties();
		//xml읽어오려면 입력 stream이 필요함
		//getClass 이servlet을 가져옴
		InputStream in = getClass().getResourceAsStream(serviceFile);
		try {
			service.loadFromXML(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		1. command 파라미터
		String command = req.getParameter("command");
		int status = 200;
		
		if(StringUtils.isNotBlank(command)) {
			
//		2. 있으면?? 명령의 식별자와 해당 명령의 URI를 객체화
//			String value = service.get(command);
			String uri = service.getProperty(command);
//		3. 정상 서비스 요청
			if(uri!=null) {//if(value.containsKey(command))
			//jsp와 servlet이 데이터를 공유해야함 -> scope
			req.setAttribute("includePage", uri);
			
			}else {
//				status = 404;
				status = HttpServletResponse.SC_NOT_FOUND;
			}
				
			
		}
		if(status==200) {
			String viewName = "/WEB-INF/views/layout.jsp";
			req.getRequestDispatcher(viewName).forward(req,resp);
			
		}else {
//		404,400
			resp.sendError(status);
		}
		
		
	}
}
