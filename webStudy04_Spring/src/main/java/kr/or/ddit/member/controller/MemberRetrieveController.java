package kr.or.ddit.member.controller;

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

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/member")
public class MemberRetrieveController{ // Plain Old Java Object
	@Inject
	IMemberService service;

	@RequestMapping("memberList.do")
	public String memberList(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			) {
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		
//		String accept = req.getHeader("Accept");
		PagingInfoVO<MemberVO> pagingVO = new PagingInfoVO<MemberVO>(5, 3);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<MemberVO> list = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(list);
		
		model.addAttribute("pagingVO", pagingVO);
		
//		if(accept.toLowerCase().contains("json")) {
//			resp.setContentType("application/json;charset=UTF-8");
//			
//			String json = new MarshallingUtils()
//							.marshalling(pagingVO);
//			
//			try(
//				PrintWriter out = resp.getWriter();	
//			){
//				out.println(json);
//			}
//			return null;
//		}else {
			String viewName = "member/memberList";
			return viewName;
//		}
		
	}
	
	@RequestMapping(value="memberList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingInfoVO<MemberVO> memberListAjax(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			){
		memberList(searchType, searchWord, currentPage, model);
		return (PagingInfoVO<MemberVO>) model.asMap().get("pagingVO");
	}
	@RequestMapping("memberView.do")
	public String memberView(@RequestParam(required=true) String who, Model model)  {
			MemberVO saved = service.retrieveMember(new MemberVO(who, null));
			
			model.addAttribute("member", saved);
			String viewName = "member/memberView";
			return viewName;
		}
}







