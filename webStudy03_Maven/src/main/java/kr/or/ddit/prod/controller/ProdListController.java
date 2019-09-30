package kr.or.ddit.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
// POJO
public class ProdListController {
	IProdService service = new ProdServiceImpl();
	
	@URIMapping("/prod/prodList.do")
	public String prodList(HttpServletRequest req, HttpServletResponse resp) {
		List<ProdVO> prodList = service.retrieveProdList();
		req.setAttribute("prodList", prodList);
		// 처리
		return "prod/prodList";
	}
}
















