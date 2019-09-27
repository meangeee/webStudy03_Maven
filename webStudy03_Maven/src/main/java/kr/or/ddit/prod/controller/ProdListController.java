package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
// POJO  <- 이게 뭐에ㅛㅇ?
public class ProdListController {
	
	IProdService service = new ProdServiceImpl();
	
	@URIMapping(value="/prod/prodList.do")
	public String prodList(HttpServletRequest req, HttpServletResponse resp) {
		//처리
		String accept = req.getHeader("Accept");
		List<ProdVO> prodList = service.retrievevProdList();
		req.setAttribute("prodList", prodList);
		
		return "prod/prodList";//return type string
	}
}
