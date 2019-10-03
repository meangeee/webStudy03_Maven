package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

@CommandHandler
public class BuyerListController{
	//service가져오기
	IBuyerService service = new BuyerServiceImpl();
	@URIMapping("/buyer/buyerList.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingInfoVO<BuyerVO> pagingVO = new PagingInfoVO<>(5, 2);
		int totalRecord = service.retrieveBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<BuyerVO> buyerList = service.selectBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		req.setAttribute("pagingVO", pagingVO);
		
		if(accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			
			
			String json = new MarshallingUtils().marshalling(buyerList);
			
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
