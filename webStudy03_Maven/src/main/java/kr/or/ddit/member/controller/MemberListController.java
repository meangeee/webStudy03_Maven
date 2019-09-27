package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberListController{ // Plain Old Java Object
	IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value="/member/memberList.do", method=HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//동기 요청과 비동기 요청을 구분하여 응답 데이터를 요청한다
		String accept = req.getHeader("Accept");
		List<MemberVO> list = service.retrieveMemberList();
		if(accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			
			String json = new MarshallingUtils()
							.marshalling(list);
			
			try(
				PrintWriter out = resp.getWriter();	
			){
				out.println(json);
			}
			return null;
		}else {
			String viewName = "member/memberList";
			return viewName;
		}
		
	}
}



