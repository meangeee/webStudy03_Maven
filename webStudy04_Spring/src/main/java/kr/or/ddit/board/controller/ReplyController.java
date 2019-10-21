package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.common.hints.DeleteHint;
import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.common.hints.UpdateHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

@Controller
@RequestMapping(value="/board/{bo_no}/reply", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReplyController {
	@Inject
	IReplyService service;
	
	@GetMapping
	@ResponseBody
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
	
	private String redirectPtrn = "redirect:/board/{bo_no}/reply?bo_no=%s&page=%s";
	
	@PostMapping
	public String insert(
			@RequestParam(required=false, defaultValue="1") int page
			, @ModelAttribute("reply") @Validated(InsertHint.class) Reply2VO reply
			, Errors errors, HttpServletResponse resp
			, Model model
			) throws IOException{
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createReply(reply);
			if(ServiceResult.OK.equals(result)) {
				viewName = String.format(redirectPtrn, reply.getBo_no(), page);
			}else {
				model.addAttribute("failed", true);
				model.addAttribute("message", "저장 실패");
			}	
		}else {
			model.addAttribute("failed", true);
			model.addAttribute("message", "입력 데이터 확인 필요");

		}
		if(model.asMap().size()>2) {
			return "jsonView";
		}else {
			return viewName;
		}
	}
	
	@PutMapping
	@ResponseBody
	public Object update(
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
			return messageMap;
		}else {
			return list(page, reply.getBo_no());
		}
	}
	
	@DeleteMapping
	@ResponseBody
	public Object delete(
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
			return messageMap;
		}else {
			return list(page, reply.getBo_no());
		}
	}
}












