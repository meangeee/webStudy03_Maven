package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Dispatch;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MyPageController {
	// 1. singleton
	IMemberService service = new MemberServiceImpl();

	@URIMapping("/mypage")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// session필요
		HttpSession session = req.getSession();
		String viewName = null;
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		MemberVO savedMember = service.retrieveMember(authMember);
		req.setAttribute("savedMember", savedMember);
		viewName = "member/mypage";

		return viewName;
	}
}
