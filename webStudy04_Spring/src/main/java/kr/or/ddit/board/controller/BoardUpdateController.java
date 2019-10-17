package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.Board2VO;

@Controller
@RequestMapping("/board")
public class BoardUpdateController {
	@Inject
	IBoardService service;

	// get방식으로 들어오면 고대로 내보낸다.
	@RequestMapping(value = "boardUpdate.do", method = RequestMethod.GET)
	public String boardForm(@RequestParam(required = true) String what, Model model) {

		Board2VO board = service.retrieveBoard(new Board2VO(Integer.parseInt(what)));
		model.addAttribute("board", board);
		return "board/boardForm";
	}

	// post방식이면 보내주는 데이터를 받아야함.
	@RequestMapping(value = "boardUpdate.do", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("board") Board2VO board,
						Errors errors, Model model) {
		
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result =
					service.modifyBoard(board);
			switch (result) {
			case OK:
				// - OK : redirect
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

		model.addAttribute("message", message);

		return viewName;
	}

	@RequestMapping(value = "boardLike.do", method = RequestMethod.POST)
	public String updateLike(HttpServletRequest req, HttpServletResponse resp)
			throws JsonGenerationException, JsonMappingException, IOException {

		String bo_no = req.getParameter("bo_no");
		if (!StringUtils.isNumeric(bo_no)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		ServiceResult result = service.incrementLike(Integer.parseInt(bo_no));
		if (ServiceResult.OK.equals(result)) {
			// 이전의 쿠기들을 보낸다
			String cookieValue = new CookieUtil(req).getCookieValue("likeCookie");
			ObjectMapper mapper = new ObjectMapper();
			int[] array = null;
			if (cookieValue != null) {
				// 언마샬링 read
				int[] boNOs = mapper.readValue(cookieValue, int[].class);
				array = new int[boNOs.length + 1];
				System.arraycopy(boNOs, 0, array, 0, boNOs.length);
				array[boNOs.length] = Integer.parseInt(bo_no);
			} else {
				array = new int[] { Integer.parseInt(bo_no) };
			}

			// 마샬링 할떄는 wirte
			cookieValue = mapper.writeValueAsString(array);
			// 모든위치에서 사용 할 수 있도록 name,value,path, maxAge
			Cookie likeCookie = CookieUtil.createCookie("likeCookie", cookieValue, req.getContextPath(), TextType.PATH,
					60 * 60 * 24 * 7);
			resp.addCookie(likeCookie);
		}
		resp.setContentType("text/plain");
		try (PrintWriter out = resp.getWriter();) {
			// result이름을 보내면 view에서 처리 text로 처리해줘서
			out.print(result.name());
		}
		return null;
		// String viewName = null;
		// ServiceResult result = service.incrementLike(bo_no);
		// Board2VO board = new Board2VO();
		// board.setBo_no(bo_no);
		// Board2VO like = service.retrieveBoard(board);
		//

		// ObjectMapper mapper = new ObjectMapper();
		// switch (result) {
		// case OK:
		// try(
		// PrintWriter out = resp.getWriter();
		// ){
		// mapper.writeValue(out, like);
		// }
		// return viewName;
		//
		//
		// case FAILED:
		// return "/board/boardView.do?what="+bo_no;
		// }
		// return viewName;
	}
}
