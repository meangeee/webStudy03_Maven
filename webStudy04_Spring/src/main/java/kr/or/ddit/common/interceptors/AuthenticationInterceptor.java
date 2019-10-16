package kr.or.ddit.common.interceptors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.member.service.IMemberService;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	@Inject
	IMemberService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		boolean pass = true;
		pass = !session.isNew();
		if(pass) {
			Object authMember = session.getAttribute("authMember");
			pass = authMember!=null;
		}
		if(!pass) {
			response.sendRedirect(request.getContextPath()+"/login");
			
		}
		return pass; //false일때는 핸들러를 호출하지말라는 뜻
	}
}
