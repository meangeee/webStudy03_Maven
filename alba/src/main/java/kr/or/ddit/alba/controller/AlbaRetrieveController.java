package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaRetrieveController {
	IAlbaService service = new AlbaServiceImpl();

	@URIMapping("/alba/albaList.do")
	public String selcectAlbaList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<AlbaVO> list = service.retrieveAlbaList();
		req.setAttribute("list", list);
		return "alba/albaList";
		
	}
	
	@URIMapping("/alba/albaView.do")
	public String selectAlba(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//받기
		String al_id = req.getParameter("al_id");
		if(StringUtils.isBlank(al_id)) {
			resp.sendError(400);
			return null;
		}
		AlbaVO alba = service.retrieveAlba(al_id);
		req.setAttribute("alba", alba);
		return "alba/albaView";
	}
}

