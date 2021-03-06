package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

@Controller //2가지의 의미 등록이되면서 동시에 이정보가 수집이 됨 by handler mapping
// POJO
@RequestMapping("/prod")
public class ProdListController {
	private static Logger logger = LoggerFactory.getLogger(ProdListController.class);
	@Inject
	IProdService service;

	@RequestMapping
	public String prodList(
			@ModelAttribute("searchVO") ProdVO searchVO,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			Model model
			) {
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
	
	@RequestMapping("{what}")
    public ModelAndView prodView(
    		@PathVariable(name="what", required=true) String prod_id){
       
       ProdVO prod = service.retrieveProd(prod_id);
       ModelAndView mav = new ModelAndView();
       mav.addObject("prod", prod);
       mav.setViewName("prod/prodView");
       return mav;
    }
}