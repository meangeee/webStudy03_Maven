package kr.or.ddit.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
// POJO
public class ProdListController {
	IProdService service = new ProdServiceImpl();
	
	@URIMapping("/prod/prodList.do")
	public String prodList(HttpServletRequest req, HttpServletResponse resp) {
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingInfoVO<ProdVO> pagingVO = new PagingInfoVO<>();
		int totalRecord = service.retrievevProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		req.setAttribute("pagingVO", pagingVO);
		// 처리
		return "prod/prodList";
	}
}
















