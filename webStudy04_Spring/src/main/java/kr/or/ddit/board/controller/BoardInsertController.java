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

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.Board2VO;

@Controller
@RequestMapping("/board/{board_type}/boardInsert.do")
public class BoardInsertController {
	@Inject
	IBoardService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String boardForm(
			@PathVariable(required=true) String board_type
			){
		return "board/boardForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String insert(
			@PathVariable(required=true) String board_type,
			@Validated(InsertHint.class) @ModelAttribute("board") Board2VO board
			, Errors errors, Model model) {
		
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createBoard(board);
			switch (result) {
			case OK:
//				- OK   : redirect 
				viewName = "redirect:/board/{board_type}/boardView.do?what="+board.getBo_no();
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










