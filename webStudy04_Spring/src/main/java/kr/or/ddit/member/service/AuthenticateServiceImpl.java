package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;
@Service
public class AuthenticateServiceImpl implements IAuthenticateService{
	@Inject
	IMemberDAO dao;
	@Override
	public MemberVO authenticate(MemberVO member) {
		//회원 유무 확인
		MemberVO savedMember = dao.selectMember(member);
		if(savedMember == null) {
			throw new UserNotFoundException("아이디가 잘못됐음");
		}
		if(!savedMember.getMem_pass().equals(member.getMem_pass())) {
			throw new NotAuthenticatedException("비번 오류");
		}
		return savedMember;
	}

}
