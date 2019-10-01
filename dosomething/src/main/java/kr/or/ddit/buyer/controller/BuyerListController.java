package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;

@CommandHandler
public class BuyerListController{
	//service가져오기
	IBuyerService service = BuyerServiceImpl.getInstance();
	@URIMapping("/buyer/buyerList.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//접속경로가져오기
		String accept = req.getHeader("Accept");
		//selectbuyerlist를 담은 list만들기
		List<BuyerVO> list = service.selectBuyerList();
		//header에 json이 있으면  ㅋ utf설정해주고 marshalling을 쓴다
		if(accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			
			
			String json = new MarshallingUtils().marshalling(list);
			
			//그것을 화면에 출력해준다
			try(
				PrintWriter out = resp.getWriter();
				){
				out.println(json);
				}
				return null;
			}else {
				String viewName = "buyer/buyerList";
				return viewName;
		}
	}
}
