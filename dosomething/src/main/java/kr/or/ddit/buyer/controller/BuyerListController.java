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

@Controller 오류
public class BuyerListController{
	//service가져오기
	@Inject
	IBuyerService service;
	@RequestMapping(value="/buyer/buyerList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doGet(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			Model model
			){
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
		
//		if(accept.toLowerCase().contains("json")) {
//			resp.setContentType("application/json;charset=UTF-8");
			
			
//			String json = new MarshallingUtils().marshalling(pagingVO);
			
			//그것을 화면에 출력해준다
//			try(
//				PrintWriter out = resp.getWriter();
//				){
//				out.println(json);
//				}
//				return null;
//			}else {
				String viewName = "buyer/buyerList";
				return viewName;
//		}
		
	}
}
