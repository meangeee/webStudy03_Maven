package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class BoardUpdateController {
	IBoardService service = new BoardServiceImpl();
	//get방식으로 들어오면 고대로 내보낸다.
	@URIMapping("/board/boardUpdate.do")
	public String boardForm(HttpServletRequest req, 

HttpServletResponse resp) throws IOException {
		String what = req.getParameter("what");
		if(StringUtils.isBlank(what)) {
			resp.sendError(400);
			return null;
		}
		Board2VO board = 
				service.retrieveBoard(new 

Board2VO(Integer.parseInt(what)));
		req.setAttribute("board", board);
		return "board/boardForm";
	}
	//post방식이면 보내주는 데이터를 받아야함.
	@URIMapping(value="/board/boardUpdate.do", 

method=HttpMethod.POST)
	public String insert(HttpServletRequest req, 

HttpServletResponse resp) {
		Board2VO board = new Board2VO();
		//미리 공유
		req.setAttribute("board", board);
		try {
			BeanUtils.populate(board, 

req.getParameterMap());
		} catch (IllegalAccessException | 

InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		
		Map<String, String> errors = new HashMap<String, 

String>();
		req.setAttribute("errors", errors);
		
		boolean valid = validate(board, errors);
		//첨부파일을 받아서 board에 넣음
		if(req instanceof MultipartRequestWrapper) {
			PartWrapper[] bo_file = 
					

((MultipartRequestWrapper) req).getPartWrappers("bo_file");
			//domain layer 이용
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

"redirect:/board/boardView.do?what="+board.getBo_no();
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
		
		if (board.getBo_no()==null || board.getBo_no()

<=0) {
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
}










