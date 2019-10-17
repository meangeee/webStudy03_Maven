package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			RedirectAttributes redirectAttributes
			) {
		
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
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("errors", errors);
		return viewName;
	}

}