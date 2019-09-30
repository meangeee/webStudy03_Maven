package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class OthersController {
	IOthersDAO othersDAO = new OthersDAOImpl();	
	
	@URIMapping("/prod/getLprodList.do")
	public String getLprodListForAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		resp.setContentType("application/json;charset=UTF-8");
		String json = new MarshallingUtils().marshalling(lprodList);
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(json);
		}
		return null;
	}
	
	@URIMapping("/prod/getBuyerList.do")
	public String getBuyerListForAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(null);
		resp.setContentType("application/json;charset=UTF-8");
		String json = new MarshallingUtils().marshalling(buyerList);
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(json);
		}
		return null;
	}
}













