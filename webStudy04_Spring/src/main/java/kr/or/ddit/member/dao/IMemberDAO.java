package kr.or.ddit.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
/**
 * 회원관리를 위한 Persistence Layer
 * CRUD
 *
 */
@Repository //이거 왜 한다고요?
public interface IMemberDAO {
	
	/**
	 * 신규 등록
	 * @param member
	 * @return 등록 성공(>0), 실패(<=0)
	 */
	public int insertMember(MemberVO member);
	
	public int selectMemberCount(PagingInfoVO<MemberVO> pagingVO);
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없는 경우, size()==0
	 */
	public List<MemberVO> selectMemberList(PagingInfoVO pagingVO);
	
	/**
	 * 회원 상세 조회
	 * @param member 조회할 회원에 대한 조건을 가진 VO
	 * @return 조건에 맞는 회원이 없는 경우, null 반환
	 */
	public MemberVO selectMember(MemberVO member);
	
	/**
	 * 회원 정보 수정
	 * @param member 수정 할 정보를 가진 VO
	 * @return 수정 성공 실패 여부를 확인할 row count
	 */
	public int updateMember(MemberVO member);

	/**
	 * 회원 정보 삭제
	 * @param member
	 * @return 수정 성공 실패 여부를 확인할 row count
	 */
	public int deleteMember(MemberVO member);
}
