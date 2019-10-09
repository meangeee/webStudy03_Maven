package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

@CommandHandler
public class ReplyController {
	IReplyService service = new ReplyServiceImpl();

	@URIMapping("/board/replyList.do")
	public String selectReply(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PagingInfoVO<Reply2VO> pagingVO = new PagingInfoVO<Reply2VO>();
		int bo_no = Integer.parseInt(req.getParameter("bo_no"));
		pagingVO.setBo_no(bo_no);
		service.retrieveReplyList(pagingVO);
		
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
			String viewName = "board/boardView";
			return viewName;
		}

	}
}