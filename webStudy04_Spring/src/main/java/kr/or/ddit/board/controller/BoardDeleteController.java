package kr.or.ddit.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.Board2VO;

@Controller
public class BoardDeleteController {
	@Inject
	IBoardService service;
	@RequestMapping(value="/board/boardDelete.do", method=RequestMethod.POST)
	public String boardView(
			@RequestParam(name="bo_no", required=true) int bo_noParam,
			@RequestParam(required=true) String bo_pass,
			HttpSession session)  {
		
		ServiceResult result = service.removeBoard(new Board2VO(bo_noParam, bo_pass));
		String viewName = "redirect:/board/boardView.do?what="+bo_noParam;
		String message = null;
		switch (result) {
			case INVALIDPASSWORD: 
				message = "비번 오류";
				break;
			case FAILED:
				message = "서버 오류";
				break;
	
			default:
				viewName = "redirect:/board/boardList.do";
				break;
		}
		session.setAttribute("message", message);
		return viewName;
	}
}



