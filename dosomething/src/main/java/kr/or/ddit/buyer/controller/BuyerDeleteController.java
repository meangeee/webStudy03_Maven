package kr.or.ddit.buyer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerDeleteController {
	IBuyerService service = BuyerServiceImpl.getInstance();
	@URIMapping("/buyer/buyerDelete.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) {
		String bId = req.getParameter("id");
		BuyerVO bv = new BuyerVO();
		bv.setBuyer_id(bId);
		
		int cnt = service.deleteBuyer(bv);
		
		String viewName = "buyer/buyerList";
		return viewName;
	}
}
