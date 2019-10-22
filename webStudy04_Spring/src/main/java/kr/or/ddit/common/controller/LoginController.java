package kr.or.ddit.common.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.events.AuthenticateSuccessEvent;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginController implements ApplicationEventPublisherAware{

	@Inject
	ApplicationEventPublisher publisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@RequestMapping(value = "/login")
	public String loginForm() {
//		String saveId = new CookieUtil(req).getCookieValue("idCookie");
//		req.setAttribute("saveId", saveId);
		String viewName = "login/loginForm";

		return viewName;

	}

	@Inject
	IAuthenticateService service;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String dopost(
			@RequestParam(required=true)String mem_id, 
			@RequestParam(required=true)String mem_pass,
			@RequestParam(required=false, name="idSave") String checkbox,
			HttpSession session, HttpServletResponse resp
			) throws IOException {

		// 아이디와 비번이 동일하면 성공
		// request scope에는 로그인이 성공했어도 다시 로그인하라고 뜬다
		// 한명의 유저가 하나의 브라우저를 이용해서 로그인한거니까 session scope를 사용
//      HttpSession session = req.getSession(); //session을 돌려주기는 하지만 만약에 이 
		// request에 session이 없으면 강제로 만들어서 돌려준다.
		// session이 있으면 돌려주고 없으면 null이 나옴
		if (session == null || session.isNew()) { // isNew() -> 방금만들어진아이인지 확인하는
			// 로그인페이지를 가져갔는데 아무것도 안한거(30분이지난후에 )
			// 강제로 내 세션을 복제하려는 경우 (위주할목적으로) 문제는 클라이언트에게 있음
			resp.sendError(400, "로그인 절차가 이상합니다!!");
			return null;
		}

		

		String viewName = null;
		try {
			MemberVO savedMember = service.authenticate(new MemberVO(mem_id, mem_pass));
			Cookie idCookie = CookieUtil.createCookie("idCookie", mem_id);
			int maxAge = 0;
			if ("idSave".equals(checkbox)) {
				maxAge = 60 * 60 * 24 * 2;
			}
			idCookie.setMaxAge(maxAge);
			resp.addCookie(idCookie);
			publisher.publishEvent(new AuthenticateSuccessEvent(this, savedMember));
			session.setAttribute("authMember", savedMember);// 로그인에 성공했다
			// 이동방식
			viewName = "redirect:/";
		} catch (UserNotFoundException | NotAuthenticatedException e) {
			// 현재 request를 남겨놓을 필요가 있다?없다?
			// 무조건 인증이나 인가를 실패했다면 정상적인 유저가 아니라고 생각한다
			session.setAttribute("message", e.getMessage());
			viewName = "redirect:/login";
		}
		return viewName;
	}


}