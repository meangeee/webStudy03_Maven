package kr.or.ddit.common.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import kr.or.ddit.common.events.LogoutSuccessEvent;
import kr.or.ddit.vo.MemberVO;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{
	@Inject
	ApplicationEventPublisher publisher;
	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		MemberVO authMember = (MemberVO) authentication.getPrincipal();
		publisher.publishEvent(new LogoutSuccessEvent(this, authMember));
		response.sendRedirect(request.getContextPath() + "/");
	}
}
