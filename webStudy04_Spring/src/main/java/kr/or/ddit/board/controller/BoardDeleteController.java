package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.Board2VO;

@Controller
public class BoardDeleteController {
	@Inject
	IBoardService service;
	
	@RequestMapping(value="/board/{board_type}/boardDelete.do", method=RequestMethod.POST)
	public String delete(
			@RequestParam(required=true) int bo_no
			, @RequestParam(required=true) String bo_pass
			, RedirectAttributes redirectAttributes
			) {
		ServiceResult result = service.removeBoard(new Board2VO(bo_no, bo_pass));
		String viewName = "redirect:/board/{board_type}/boardView.do?what="+bo_no;
		String message = null;
		switch (result) {
			case INVALIDPASSWORD:
				message = "비번 오류";
				break;
			case FAILED:
				message = "서버 오류";
				break;
	
			default:
				viewName = "redirect:/board/{board_type}/boardList.do";
				break;
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}



