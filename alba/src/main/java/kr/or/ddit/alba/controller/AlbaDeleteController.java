package kr.or.ddit.alba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class AlbaDeleteController {
	IAlbaService service = new AlbaServiceImpl();
	@URIMapping("/alba/albaDelete.do")
	public String deleteAlba(HttpServletRequest req, HttpServletResponse resp) {
		String al_id = req.getParameter("al_id");
		int cnt = service.removeAlba(al_id);
		String viewName = null;
		if(cnt > 0) {
			viewName = "redirect:/alba/albaList.do";
		}
		
		return viewName;
	}
	
}
