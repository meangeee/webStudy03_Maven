package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class BoardRetreiveController {
	IBoardService service = new BoardServiceImpl();
	
	@URIMapping("/board/boardList.do") // UI 동기, data 비동기
	public String list(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException{
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingInfoVO<Board2VO> pagingVO = 
					new PagingInfoVO<Board2VO>(7, 5);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<Board2VO> list = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(list);
		
		String accept = req.getHeader("Accept");
		if(accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();	
			){
				mapper.writeValue(out, pagingVO);
			}
			return null;
		}else {
			req.setAttribute("pagingVO", pagingVO);
			String viewName = "board/boardList";
			return viewName;
		}
	}
	
	@URIMapping("/board/boardView.do")
	public String boardView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String what = req.getParameter("what");
		if(StringUtils.isBlank(what)) {
			resp.sendError(400);
			return null;
		}
		Board2VO board = 
				service.retrieveBoard(new Board2VO(Integer.parseInt(what)));
		req.setAttribute("board", board);
		//쿠키 꺼내기
		String cookieValue = new CookieUtil(req).getCookieValue("likeCookie");
		boolean likable = false;
		if(cookieValue!=null) {
			ObjectMapper mapper = new ObjectMapper();
			int[] boNos = mapper.readValue(cookieValue, int[].class);
			Arrays.sort(boNos);
			//aserlization ??? 뭐? 어디서 사용했었데요.
			int idx = Arrays.binarySearch(boNos, Integer.parseInt(what));
			if(idx < 0) likable = true;
		}else {
			likable = true;
		}
		req.setAttribute("likable", likable);
		return "board/boardView";
	}
}











