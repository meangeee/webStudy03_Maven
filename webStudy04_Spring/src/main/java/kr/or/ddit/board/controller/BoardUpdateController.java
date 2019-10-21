package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.hints.UpdateHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.Board2VO;

@Controller
@RequestMapping("/board/{board_type}/boardUpdate.do")
public class BoardUpdateController {
	@Inject
	IBoardService service;

	@RequestMapping(method=RequestMethod.GET)
	public String boardForm(
			@PathVariable(required=true) String board_type,
			@RequestParam(required=true) int what
			, Model model
			){
		Board2VO board = 
				service.retrieveBoard(new Board2VO(what));
		model.addAttribute("board", board);
		return "board/boardForm";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String insert(
			@Validated(UpdateHint.class) @ModelAttribute("board") Board2VO board
			, Errors errors, Model model
			){
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.modifyBoard(board);
			switch (result) {
			case OK:
//				- OK   : redirect 
				viewName = "redirect:/board/{board_type}/boardView.do?what="+board.getBo_no();
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
}










