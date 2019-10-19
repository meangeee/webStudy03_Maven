package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.common.hints.DeleteHint;
import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.common.hints.UpdateHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

@Controller
@RequestMapping("/board")
public class ReplyController {
	IReplyService service = new ReplyServiceImpl();
	
	@RequestMapping(value="replyList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingInfoVO<Reply2VO> list(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(required=true) int bo_no
			){
		PagingInfoVO<Reply2VO> pagingVO = 
					new PagingInfoVO<Reply2VO>(4, 5);
		pagingVO.setSearchVO(new Reply2VO(bo_no));
		int totalRecord = service.retrieveReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<Reply2VO> list = service.retrieveReplyList(pagingVO);
		pagingVO.setDataList(list);
		
		return pagingVO;
	}
	
	private String redirectPtrn = "redirect:/board/replyList.do?bo_no=%s&page=%s";
	
	@RequestMapping(value = "replyInsert.do", method=RequestMethod.POST)
	public String insert(
			@RequestParam(required=false, defaultValue="1") int page
			, @Validated(InsertHint.class) Reply2VO reply
			, Errors errors, HttpServletResponse resp
			) throws IOException{
		Map<String, Object> messageMap = new HashMap<>();
		messageMap.put("reply", reply);
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createReply(reply);
			if(ServiceResult.OK.equals(result)) {
				viewName = String.format(redirectPtrn, reply.getBo_no(), page);
			}else {
				messageMap.put("failed", true);
				messageMap.put("message", "저장 실패");
			}	
		}else {
			messageMap.put("failed", true);
			messageMap.put("message", "입력 데이터 확인 필요");

		}
		if(messageMap.size()>1) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();	
			){
				mapper.writeValue(out, messageMap);
			}
		}
		return viewName;
	}
	
	@RequestMapping(value="replyUpdate.do", method = RequestMethod.POST)
	public String update(
			@RequestParam(required=false, defaultValue="1") int page
			, @Validated(UpdateHint.class) Reply2VO reply
			, Errors errors, HttpServletResponse resp
			) throws IOException {
		Map<String, Object> messageMap = new HashMap<>();
		messageMap.put("reply", reply);
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.modifyReply(reply);
			if(ServiceResult.OK.equals(result)) {
				viewName = String.format(redirectPtrn, reply.getBo_no(), page);
			}else {
				messageMap.put("failed", true);
				messageMap.put("message", "비번 오류");
			}	
		}else {
			messageMap.put("failed", true);
			messageMap.put("message", "입력 데이터 확인 필요");

		}
		if(messageMap.size()>1) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();	
			){
				mapper.writeValue(out, messageMap);
			}
		}
		return viewName;
	}
	
	@RequestMapping(value="replyDelete.do", method = RequestMethod.POST)
	public String delete(
			@RequestParam(required=false, defaultValue="1") int page
			, @Validated(DeleteHint.class) Reply2VO reply
			, Errors errors, HttpServletResponse resp
			) throws IOException {
		Map<String, Object> messageMap = new HashMap<>();
		messageMap.put("reply", reply);
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.removeReply(reply);
			if(ServiceResult.OK.equals(result)) {
				viewName = String.format(redirectPtrn, reply.getBo_no(), page);
			}else {
				messageMap.put("failed", true);
				messageMap.put("message", "비번 오류");
			}	
		}else {
			messageMap.put("failed", true);
			messageMap.put("message", "입력 데이터 확인 필요");

		}
		if(messageMap.size()>1) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();	
			){
				mapper.writeValue(out, messageMap);
			}
		}
		return viewName;
	}
	
	
}






//package kr.or.ddit.board.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.lang.reflect.InvocationTargetException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.core.tools.picocli.CommandLine.Command;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import kr.or.ddit.board.service.IReplyService;
//import kr.or.ddit.board.service.ReplyServiceImpl;
//import kr.or.ddit.enums.ServiceResult;
//import kr.or.ddit.vo.PagingInfoVO;
//import kr.or.ddit.vo.Reply2VO;
//
//@Command
//public class ReplyController {
//	IReplyService service = new ReplyServiceImpl();
//	
//	@RequestMapping("/board/replyList.do")
//	public String list(
//			@RequestParam(name="page", required=true)String pageParam,
//						@RequestParam(name="bo_no") String bo_noParam,
//						@RequestParam(required=true, defaultValue="1")int currentPage
//			){
//		PagingInfoVO<Reply2VO> pagingVO = 
//					new PagingInfoVO<Reply2VO>(4, 5);
//		pagingVO.setSearchVO(new Reply2VO(Integer.parseInt(bo_noParam)));
//		int totalRecord = service.retrieveReplyCount(pagingVO);
//		pagingVO.setTotalRecord(totalRecord);
//		pagingVO.setCurrentPage(currentPage);
//		List<Reply2VO> list = service.retrieveReplyList(pagingVO);
//		pagingVO.setDataList(list);
//		
//		resp.setContentType("application/json;charset=UTF-8");
//		ObjectMapper mapper = new ObjectMapper();
//		try(
//			PrintWriter out = resp.getWriter();	
//		){
//			mapper.writeValue(out, pagingVO);
//		}
//		return null;
//	}
//	
//	public static interface DefaultHint{}
//	public static interface InsertHint extends DefaultHint{}
//	public static interface UpdateHint extends DefaultHint{}
//	public static interface DeleteHint extends DefaultHint{}
//	
//	private boolean validate(Reply2VO reply, Map<String, Object> errors, Class<?> hint) {
//		boolean valid = true;
//		// 신규, 수정, 삭제시 공통 검증
//		if(DefaultHint.class.isAssignableFrom(hint)) {
//			if(reply.getBo_no()==null || reply.getBo_no()<=0) {
//				errors.put("bo_no", "글번호 누락");
//				valid = false;
//			}
//			if(StringUtils.isBlank(reply.getRep_pass())) {
//				errors.put("rep_pass", "비밀번호 누락");
//				valid = false;
//			}
//		}
//		// 신규 검증
//		if(InsertHint.class.equals(hint)) {
//			if(StringUtils.isBlank(reply.getRep_writer())) {
//				errors.put("rep_writer", "작성자 누락");
//				valid = false;
//			}
//			if(StringUtils.isBlank(reply.getRep_ip())) {
//				errors.put("rep_ip", "아이피 누락");
//				valid = false;
//			}
//			if(StringUtils.length(reply.getRep_content()) > 200) {
//				errors.put("rep_content", "댓글 길이 200자 제한");
//				valid = false;
//			}
//		}
//		// 수정 검증
//		if(UpdateHint.class.equals(hint)) {
//			if(reply.getRep_no()==null || reply.getRep_no()<=0) {
//				errors.put("rep_no", "댓글번호 누락");
//				valid = false;
//			}
//			if(StringUtils.length(reply.getRep_content()) > 200) {
//				errors.put("rep_content", "댓글 길이 200자 제한");
//				valid = false;
//			}
//		}
//		// 삭제 검증
//		if(DeleteHint.class.equals(hint)) {
//			if(reply.getRep_no()==null || reply.getRep_no()<=0) {
//				errors.put("rep_no", "댓글번호 누락");
//				valid = false;
//			}
//		}
//		return valid;
//	}
//	
//	private String redirectPtrn = "redirect:/board/replyList.do?bo_no=%s&page=%s";	
//	@URIMapping(value = "/board/replyInsert.do", method = HttpMethod.POST)
//	public String insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		String pageParam = req.getParameter("page");
//		Reply2VO reply = new Reply2VO();
//		try {
//			BeanUtils.populate(reply, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
//		Map<String, Object> messageMap = new HashMap<>();
//		messageMap.put("reply", reply);
//		boolean valid = validate(reply, messageMap, InsertHint.class);
//		String viewName = null;
//		if(valid) {
//			ServiceResult result = service.createReply(reply);
//			if(ServiceResult.OK.equals(result)) {
//				viewName = String.format(redirectPtrn, reply.getBo_no(), pageParam);
//			}else {
//				messageMap.put("failed", true);
//				messageMap.put("message", "저장 실패");
//			}	
//		}else {
//			messageMap.put("failed", true);
//			messageMap.put("message", "입력 데이터 확인 필요");
//
//		}
//		if(messageMap.size()>1) {
//			resp.setContentType("application/json;charset=UTF-8");
//			ObjectMapper mapper = new ObjectMapper();
//			try(
//				PrintWriter out = resp.getWriter();	
//			){
//				mapper.writeValue(out, messageMap);
//			}
//		}
//		return viewName;
//	}
//	
//	@URIMapping(value="/board/replyUpdate.do", method = HttpMethod.POST)
//	public String update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		String pageParam = req.getParameter("page");
//		Reply2VO reply = new Reply2VO();
//		try {
//			BeanUtils.populate(reply, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
//		Map<String, Object> messageMap = new HashMap<>();
//		messageMap.put("reply", reply);
//		boolean valid = validate(reply, messageMap, UpdateHint.class);
//		String viewName = null;
//		if(valid) {
//			ServiceResult result = service.modifyReply(reply);
//			if(ServiceResult.OK.equals(result)) {
//				viewName = String.format(redirectPtrn, reply.getBo_no(), pageParam);
//			}else {
//				messageMap.put("failed", true);
//				messageMap.put("message", "비번 오류");
//			}	
//		}else {
//			messageMap.put("failed", true);
//			messageMap.put("message", "입력 데이터 확인 필요");
//
//		}
//		if(messageMap.size()>1) {
//			resp.setContentType("application/json;charset=UTF-8");
//			ObjectMapper mapper = new ObjectMapper();
//			try(
//				PrintWriter out = resp.getWriter();	
//			){
//				mapper.writeValue(out, messageMap);
//			}
//		}
//		return viewName;
//	}
//	
//	@URIMapping(value="/board/replyDelete.do", method = HttpMethod.POST)
//	public String delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		String pageParam = req.getParameter("page");
//		Reply2VO reply = new Reply2VO();
//		try {
//			BeanUtils.populate(reply, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
//		Map<String, Object> messageMap = new HashMap<>();
//		messageMap.put("reply", reply);
//		boolean valid = validate(reply, messageMap, DeleteHint.class);
//		String viewName = null;
//		if(valid) {
//			ServiceResult result = service.removeReply(reply);
//			if(ServiceResult.OK.equals(result)) {
//				viewName = String.format(redirectPtrn, reply.getBo_no(), pageParam);
//			}else {
//				messageMap.put("failed", true);
//				messageMap.put("message", "비번 오류");
//			}	
//		}else {
//			messageMap.put("failed", true);
//			messageMap.put("message", "입력 데이터 확인 필요");
//
//		}
//		if(messageMap.size()>1) {
//			resp.setContentType("application/json;charset=UTF-8");
//			ObjectMapper mapper = new ObjectMapper();
//			try(
//				PrintWriter out = resp.getWriter();	
//			){
//				mapper.writeValue(out, messageMap);
//			}
//		}
//		return viewName;
//	}
//	
//	
//}
