package kr.or.ddit.buyer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping(value = "/buyer/buyerList.do")
public class BuyerListController {
	// service가져오기
	@Inject
	IBuyerService service;

	@RequestMapping
	public String doGet(@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false) String searchType, @RequestParam(required = false) String searchWord,
			Model model) {
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);

		PagingInfoVO<BuyerVO> pagingVO = new PagingInfoVO<>(5, 2);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<BuyerVO> buyerList = service.selectBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		model.addAttribute("pagingVO", pagingVO);

		return "buyer/buyerList";

	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody // 마샬링
	public PagingInfoVO<BuyerVO> buyerListAjax(@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, Model model) {
		doGet(currentPage, searchWord, searchType, model);

		return (PagingInfoVO<BuyerVO>) model.asMap().get("pagingVO");
	}
}
