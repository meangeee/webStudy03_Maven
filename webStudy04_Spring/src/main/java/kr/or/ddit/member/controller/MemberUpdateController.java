package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController {
	@Inject
	IMemberService service;

	@RequestMapping(value = "/member/memberUpdate.do", method = RequestMethod.POST)
	public String doPost(
			@Valid MemberVO member, Errors errors,
			HttpSession session) {
		
		// 2. 분석 검증
		boolean valid = !errors.hasErrors();
		String viewName = "redirect:/mypage";
		String message = null;
		if (valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				message = "비번오류";
				break;
			case FAILED:
				message = "서버 오류";
				break;

			default:
				message = "수정 성공";
				break;
			}
		} else {

		}
		session.setAttribute("message", message);
		session.setAttribute("errors", errors);
		return viewName;
	}

}