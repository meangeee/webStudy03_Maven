package kr.or.ddit.member.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MyPageController {
	// 1. singleton
	@Inject
	IMemberService service;

	@RequestMapping("/mypage")
	public String doGet(HttpSession session, Model model) {
		String viewName = null;
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		MemberVO savedMember = service.retrieveMember(authMember);
		model.addAttribute("savedMember", savedMember);
		viewName = "member/mypage";

		return viewName;
	}
}
