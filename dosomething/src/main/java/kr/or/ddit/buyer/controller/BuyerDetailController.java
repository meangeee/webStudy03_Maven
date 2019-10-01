package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerDetailController {

	IBuyerService service = BuyerServiceImpl.getInstance();

	@URIMapping("/buyer/detail")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 일단 경로
		String accept = req.getHeader("Accept");
		// buyer id를 가져오고
		String buyer_id = req.getParameter("id");
		// buyer에 id를 세팅해준다
		BuyerVO vo = new BuyerVO();
		vo.setBuyer_id(buyer_id);

		BuyerVO bId = service.selectBuyer(vo);

		if (accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");

			String json = new MarshallingUtils().marshalling(bId);

			try (PrintWriter out = resp.getWriter();) {
				out.println(json);
			}
			return null;

		} else {
			String viewName = "buyer/buyerList.jsp";
			return viewName;
		}
	}
}
