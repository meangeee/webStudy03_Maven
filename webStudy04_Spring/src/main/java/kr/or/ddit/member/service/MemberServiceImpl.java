package kr.or.ddit.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.dao.MemberDAOImpl_JDBC;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
@Service("memberService")
public class MemberServiceImpl implements IMemberService {
	// 결합력 최상. -> HCLC 지향 -> Factory Object pattern, Stategy pattern(DI)
	@Inject
	private IMemberDAO dao;
	@Inject
	private IAuthenticateService service;
	
	
	
	@Override
	public ServiceResult createMember(MemberVO member) {
//		MemberVO saved = dao.selectMember(member);
//		ServiceResult result = null;
//		if(saved != null) {
//			result = ServiceResult.PKDUPLICATED;
//			return result;
//		}else {
//			int cnt = 0;
//			if(cnt > 0) {
//				result = ServiceResult.OK;
//			}else {
//				result = ServiceResult.FAILED;
//			}
//			return result;
//		}
		
		ServiceResult result = null;
		if(dao.selectMember(member)==null) {
			int cnt = dao.insertMember(member);
			if(cnt>0) result = ServiceResult.OK;
			else result = ServiceResult.FAILED;
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
		
	}

	@Override
	public MemberVO retrieveMember(MemberVO member) {
		MemberVO saved = dao.selectMember(member);
		if(saved == null) 
			throw new UserNotFoundException(member.getMem_id()+"가 없음.");
		return saved;
	}

	@Override
	public int retrieveMemberCount(PagingInfoVO<MemberVO> pagingVO) {
		return dao.selectMemberCount(pagingVO);
	}
	
	@Override
	public List<MemberVO> retrieveMemberList(PagingInfoVO pagingVO) {
		return dao.selectMemberList(pagingVO);
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {

		ServiceResult result = null;

		try {
			service.authenticate(member);
			int cnt = dao.updateMember(member);
			if (cnt > 0)
				result = ServiceResult.OK;
			else
				result = ServiceResult.FAILED;
		} catch (NotAuthenticatedException e) {
			// 비번이 틀렸을 경우 실행
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}
	

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = null;

		try {
			service.authenticate(member);
			int cnt = dao.deleteMember(member);
			if (cnt > 0)
				result = ServiceResult.OK;
			else
				result = ServiceResult.FAILED;
		} catch (NotAuthenticatedException e) {
			// 비번이 틀렸을 경우 실행
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}
}
