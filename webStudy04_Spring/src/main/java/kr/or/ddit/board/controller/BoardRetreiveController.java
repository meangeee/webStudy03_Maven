package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/board/{board_type}")
public class BoardRetreiveController {
	@Inject
	IBoardService service;
	
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping("boardList.do") // UI 동기, data 비동기
	public String list(
			@PathVariable(required=true) String board_type,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			){
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		
		PagingInfoVO<Board2VO> pagingVO = 
					new PagingInfoVO<Board2VO>(7, 5);
		Board2VO searchVO = new Board2VO();
		searchVO.setBoard_type(board_type);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<Board2VO> list = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(list);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardList";
	}
	@RequestMapping(value="boardList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingInfoVO listForAjax(
			@PathVariable(required=true) String board_type,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			){
		list(board_type, searchType, searchWord, currentPage, model);
		return (PagingInfoVO<MemberVO>) model.asMap().get("pagingVO");
	}
	
	@RequestMapping("boardView.do")
	public String boardView(
			@PathVariable(required=true) String board_type
			, @RequestParam(required=true) int what
			, Model model
			, @CookieValue(name="likeCookie", required=false) String cookieValue
			) throws JsonParseException, JsonMappingException, IOException{
		Board2VO board = 
				service.retrieveBoard(new Board2VO(what));
		model.addAttribute("board", board);
		
		boolean likable = false;
		if(cookieValue!=null) {
			ObjectMapper mapper = new ObjectMapper();
			int[] boNos = mapper.readValue(cookieValue, int[].class);
			Arrays.sort(boNos);
			int idx = Arrays.binarySearch(boNos, what);
			if(idx < 0) likable = true;
		}else {
			likable = true;
		}
		model.addAttribute("likable", likable);
		return "board/boardView";
	}
	
	@RequestMapping(value="boardLike.do", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String updateLike(
			@RequestParam(required=true) int what, 
			@CookieValue(required=false, name="likeCookie") String cookieValue
			, HttpServletResponse resp
			) throws IOException {
		ServiceResult result = service.incrementLike(what);
		if(ServiceResult.OK.equals(result)) {
			ObjectMapper mapper = new ObjectMapper();
			int[] array = null;
			if(cookieValue!=null) {
				int[] boNOs = mapper.readValue(cookieValue, int[].class);
				array = new int[boNOs.length+1];
				System.arraycopy(boNOs, 0, array, 0, boNOs.length);
				array[boNOs.length] = what;
			}else {
				array = new int[] {what};
			}
			cookieValue = mapper.writeValueAsString(array);
			Cookie likeCookie = CookieUtil.createCookie("likeCookie", cookieValue, 
							application.getContextPath(), TextType.PATH, 60*60*24*7);
			resp.addCookie(likeCookie);
		}
		return result.name();
	}
}











