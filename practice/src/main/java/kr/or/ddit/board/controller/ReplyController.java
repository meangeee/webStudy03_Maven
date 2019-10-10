package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

@CommandHandler
public class ReplyController {
	IReplyService service = new ReplyServiceImpl();
	@URIMapping("/board/replyList.do")
	public String replyList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String page = req.getParameter("page");
		String bo_no = req.getParameter("bo_no");
		if(StringUtils.isBlank(bo_no)) {
			resp.sendError(400);
			return null;
		}
		int currentPage = 1;
		if(StringUtils.isNumeric(page)) {
			currentPage = Integer.parseInt(page);
		}
		PagingInfoVO<Reply2VO> pagingVO = 
				new PagingInfoVO<Reply2VO>(4, 5);
		pagingVO.setSearchVO(new Reply2VO(Integer.parseInt(bo_no)));
		int totalRecord = service.retrieveReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<Reply2VO> list = service.retrieveReplyList(pagingVO);
		pagingVO.setDataList(list);
		
		resp.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		try(
			PrintWriter out = resp.getWriter();	
		){
			mapper.writeValue(out, pagingVO);
		}
		return null;
	}
}





















