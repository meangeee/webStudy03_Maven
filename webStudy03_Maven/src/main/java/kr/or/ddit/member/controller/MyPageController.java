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
public class MyPageController{
	//1. singleton
	IMemberService service = new MemberServiceImpl();
	
	@URIMapping("/mypage")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//session필요
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		String viewName = null;
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		boolean redirect = false;
		if(authMember==null) {
			String message = "마이페이지는 로그인이 필요함.";
			session.setAttribute("message", message);
			viewName = "redirect:/login";
			redirect = true;
		}else {
			MemberVO savedMember = service.retrieveMember(authMember);
			req.setAttribute("savedMember", savedMember);
			viewName = "member/mypage";
		}
		
		return viewName;
	}
}
