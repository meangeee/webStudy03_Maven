package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaRetrieveController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	@URIMapping("/alba/albaList.do")
	public String selcectAlbaList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		String accept = req.getHeader("Accpet");
		List<AlbaVO> list = service.retrieveAlbaList();
		
		if(accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			String json = new MarshallingUtils()
								.marshalling(list);
			
			try(
				PrintWriter out = resp.getWriter();
			){
				out.println(json);
			}
			return null;
		}else {
			String viewName = "alba/albaList";
			return viewName;
		}
	}
}
