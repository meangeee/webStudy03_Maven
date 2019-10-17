package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/board")
public class BoardRetreiveController {
	@Inject
	IBoardService service;

	@RequestMapping(value = "boardList.do", produces = MediaType.APPLICATION_JSON_VALUE) // UI 동기, data 비동기
	public ModelAndView list(String searchType, String searchWord,
			@RequestParam(name = "page", defaultValue = "1") int currentPage, Model model) {
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);

		PagingInfoVO<Board2VO> pagingVO = new PagingInfoVO<Board2VO>(7, 5);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<Board2VO> list = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(list);

//		model.addAttribute("pagingVO", pagingVO);
		ModelAndView mav = new ModelAndView();
		String viewName = "board/boardList";
		mav.addObject("pagingVO", pagingVO);
		mav.setViewName(viewName);
		return mav;
	}
	

	@RequestMapping("boardView.do")
	public String boardView(
			@RequestParam(required=true)String what,
			HttpServletRequest req,
			Model model
			) throws JsonParseException, JsonMappingException, IOException  {
		Board2VO board = service.retrieveBoard(new Board2VO(Integer.parseInt(what)));
		model.addAttribute("board", board);
		// 쿠키 꺼내기
		String cookieValue = new CookieUtil(req).getCookieValue("likeCookie");
		boolean likable = false;
		if (cookieValue != null) {
			ObjectMapper mapper = new ObjectMapper();
			int[] boNos = mapper.readValue(cookieValue, int[].class);
			Arrays.sort(boNos);
			// aserlization ??? 뭐? 어디서 사용했었데요.
			int idx = Arrays.binarySearch(boNos, Integer.parseInt(what));
			if (idx < 0)
				likable = true;
		} else {
			likable = true;
		}
		req.setAttribute("likable", likable);
		return "board/boardView";
	}
}
