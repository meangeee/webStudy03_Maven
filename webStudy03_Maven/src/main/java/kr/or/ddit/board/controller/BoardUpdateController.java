package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class BoardUpdateController {
	IBoardService service = new BoardServiceImpl();

	// get방식으로 들어오면 고대로 내보낸다.
	@URIMapping("/board/boardUpdate.do")
	public String boardForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String what = req.getParameter("what");
		if (StringUtils.isBlank(what)) {
			resp.sendError(400);
			return null;
		}
		
		Board2VO board = service.retrieveBoard(new Board2VO(Integer.parseInt(what)));
		req.setAttribute("board", board);
		return "board/boardForm";
	}

	// post방식이면 보내주는 데이터를 받아야함.
	@URIMapping(value = "/board/boardUpdate.do",method = HttpMethod.POST)
	public String insert(HttpServletRequest req, HttpServletResponse resp) {
		Board2VO board = new Board2VO();
		// 미리 공유
		req.setAttribute("board", board);
		try {
			BeanUtils.populate(board, req.getParameterMap());
		} catch (IllegalAccessException |
				InvocationTargetException e) {
				throw new RuntimeException(e);
		}

		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);

		boolean valid = validate(board, errors);
		// 첨부파일을 받아서 board에 넣음
		if (req instanceof MultipartRequestWrapper) {
			PartWrapper[] bo_file =

					((MultipartRequestWrapper) req).getPartWrappers("bo_file");
			// domain layer 이용
			board.setBo_file(bo_file);
		}

		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result =

					service.modifyBoard(board);
			switch (result) {
			case OK:
//				- OK   : redirect 
				viewName =

						"redirect:/board/boardView.do?what=" + board.getBo_no();
				break;
			case INVALIDPASSWORD:
				message = "비번 오류";
				viewName = "board/boardForm";
				break;
			default:
				message = "서버 오류";
				viewName = "board/boardForm";
				break;
			}

		} else {
			viewName = "board/boardForm";
		}

		req.setAttribute("message", message);

		return viewName;
	}

	private boolean validate(Board2VO board, Map<String,

			String> errors) {
		boolean valid = true;

		if (board.getBo_no() == null || board.getBo_no()

		<= 0) {
			valid = false;
			errors.put("bo_no", "글번호 누락");
		}
		if (StringUtils.isBlank(board.getBoard_type())) {
			valid = false;
			errors.put("board_type", "게시판종류 누락");
		}
		if (StringUtils.isBlank(board.getBo_title())) {
			valid = false;
			errors.put("bo_title", "글제목 누락");
		}
		if (StringUtils.isBlank(board.getBo_writer())) {
			valid = false;
			errors.put("bo_writer", "작성자 누락");
		}
		if (StringUtils.isBlank(board.getBo_pass())) {
			valid = false;
			errors.put("bo_pass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(board.getBo_ip())) {
			valid = false;
			errors.put("bo_ip", "아이피 누락");
		}
		return valid;
	}
	@URIMapping(value="/board/boardLike.do", method=HttpMethod.POST)
	public String updateLike(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException {
		
		String bo_no = req.getParameter("bo_no");
		if(!StringUtils.isNumeric(bo_no)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		ServiceResult result = service.incrementLike(Integer.parseInt(bo_no));
		if(ServiceResult.OK.equals(result)) {
			//이전의 쿠기들을 보낸다
			String cookieValue = new CookieUtil(req).getCookieValue("likeCookie");
			ObjectMapper mapper = new ObjectMapper();
			int [] array = null;
			if(cookieValue!=null) {
				//언마샬링 read
				int[] boNOs = mapper.readValue(cookieValue, int[].class);
				array = new int[boNOs.length+1];
				System.arraycopy(boNOs, 0, array, 0, boNOs.length);
				array[boNOs.length] = Integer.parseInt(bo_no);
			}else {
				array = new int[] {Integer.parseInt(bo_no)};
			}
			
			//마샬링 할떄는 wirte
			cookieValue = mapper.writeValueAsString(array);
			//모든위치에서 사용 할 수 있도록 name,value,path, maxAge
			Cookie likeCookie = CookieUtil.createCookie("likeCookie", cookieValue, req.getContextPath(), TextType.PATH, 60*60*24*7);
			resp.addCookie(likeCookie);
		}
		resp.setContentType("text/plain");
		try(
			PrintWriter out = resp.getWriter();
		){
			//result이름을 보내면 view에서 처리 text로 처리해줘서
			out.print(result.name());
		}
		return null;
//		String viewName = null;
//		ServiceResult result = service.incrementLike(bo_no);
//		Board2VO board = new Board2VO();
//		board.setBo_no(bo_no);
//		Board2VO like = service.retrieveBoard(board);
//		
		
//		ObjectMapper mapper = new ObjectMapper();
//		switch (result) {
//			case OK:
//			try(
//				PrintWriter out = resp.getWriter();
//			){
//				mapper.writeValue(out, like);
//			}
//			return viewName;
//			
//			
//			case FAILED:
//			return "/board/boardView.do?what="+bo_no;
//		}
//		return viewName;
	}
}
