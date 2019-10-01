package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;

/**
 * buyer관리를 위한 Business Logic Layer
 * 컨트롤러와 뷰를 연결하는 역할, 다른 계층과 통신하기 위한 인터페이스 제공,
 * 트랜잭션 처리 등.
 */
public interface IBuyerService {
	
	/**
	 * 신규 등록
	 * @param buyer
	 * @return 등록 성공(>0), 실패(<=0)
 	 */
	public int insertBuyer(BuyerVO buyer);
	
	/**
	 * buyer 목록 조회
	 * @return 조건에 맞는 회원이 없는 경우, size()==0
	 */
	public List<BuyerVO> selectBuyerList();
	
	/**
	 * buyer 상세 목록 조회
	 * @param buyer 조회할 buyer에 대한 조건을 가진 VO
	 * @return 조건에 맞는 buyer가 없을 경우, null 반환
	 */
	public BuyerVO selectBuyer(BuyerVO buyer);
	
	/**
	 * buyer 정보 수정
	 * @param buyer 수정할 정보를 가진 VO
	 * @return 수정 성공 실패 여부를 확인할 row count
	 */
	public int updateBuyer(BuyerVO buyer);
	
	/**
	 * buyer 정보 삭제
	 * @param buyer
	 * @return 삭제 성공 실패 여부를 확인할 row count
	 */
	public int deleteBuyer(BuyerVO buyer);
}
