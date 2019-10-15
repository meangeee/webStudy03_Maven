 package kr.or.ddit.member.controller;


import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	@Resource(type=IMemberService.class, name="memberService")//injection 뭐요?
	IMemberService service;
	
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String doPost(
			@RequestParam(required=true) String password, 
			HttpSession session,
			HttpServletResponse resp) throws IOException{
	 	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
//	 	String password = req.getParameter("password");
	 	MemberVO member = new MemberVO(authMember.getMem_id(), password);
	 	
	 	if(session.isNew()||authMember==null) {
	 		resp.sendError(400);
	 		return null;
	 	}
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