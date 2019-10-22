package kr.or.ddit.common.events;

import org.springframework.context.ApplicationEvent;

import kr.or.ddit.vo.MemberVO;

public class AuthenticateSuccessEvent extends ApplicationEvent{
	private MemberVO authMember;

	
	public AuthenticateSuccessEvent(Object target, MemberVO authMember) {
		super(target);
		this.authMember = authMember;
	}
	
	public MemberVO getAuthMember() {
		return authMember;
	}
}
