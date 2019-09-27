 package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;
@CommandHandler
public class MemberDeleteController{
	IMemberService service = MemberServiceImpl.getInstance();
	
	
	@URIMapping(value="/member/memberDelete.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 	HttpSession session = req.getSession();
	 	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	 	String password = req.getParameter("password");
	 	if(session.isNew() || authMember==null || StringUtils.isBlank(password)) {
	 		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
	 		return null;
	 	}
	 	MemberVO member = new MemberVO(authMember.getMem_id(), password);
	 	ServiceResult result = service.removeMember(member);
	 	String viewName = null;
	 	String message = null;
		switch (result) {
			case INVALIDPASSWORD:
				viewName = "redirect:/mypage";
				message = "비번 오류";
				session.setAttribute("message", message);
				break;
			case FAILED:
				viewName = "redirect:/mypage";
				message = "서버 오류";
				session.setAttribute("message", message);
				break;

			default:
				viewName = "redirect:/";
				session.invalidate();
				break;
		}
		return viewName;
	}
}

//반드시 로그인이 되어있어야함


//비번 인증 하고 탈퇴

//이중인증 비번이 안넘어오면 탈퇴 ㄴㄴ 

//검증했는데 안넘어가면 클라이언트의 문제 400

//통과했으면 탈퇴 탈퇴를 하려면 로직객체를 이용

//removeMember 리턴타입 service 
//		INVALIDPASSWORD
//		FAILD
//		OK 로그아웃으로 redirection