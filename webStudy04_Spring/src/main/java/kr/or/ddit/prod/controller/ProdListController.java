package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

// POJO
@Controller
public class ProdListController {
	private static Logger logger = LoggerFactory.getLogger(ProdListController.class);
	@Inject
	IProdService service;

	@RequestMapping("/prod/prodList.do")
	public String prodList(
			ProdVO searchVO,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			Model model
			) {
//		ProdVO searchVO = new ProdVO();
//		try {
//			BeanUtils.populate(searchVO, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			logger.error("검색 중 예외 발생", e);
//		}
		
		
//		String pageParam = req.getParameter("page");
//		int currentPage = 1;
//		if (StringUtils.isNumeric(pageParam)) {
//			currentPage = Integer.parseInt(pageParam);
//		}
		PagingInfoVO<ProdVO> pagingVO = new PagingInfoVO<>(5, 3);
		pagingVO.setSearchVO(searchVO);
		int totalRecord = service.retrievevProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		model.addAttribute("pagingVO", pagingVO);
		// 처리
		return "prod/prodList";
	}
}