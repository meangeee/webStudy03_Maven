package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;





/**
 * Buyer 관리 Persistence Layer
 * 영구 데이터를 빼내어 객체화 시키며, 영구 저장소에 데이터를 저장, 수정, 삭제하는 계층이다.
 * 즉, 데이터베이스나 파일에 접근하여 데이터를 CRUD 하는 계층이다.
 * 
 *
 */
public interface IBuyerDAO {

	/**
	 * 신규 등록
	 * @param buyer
	 * @return 등록 성공(>0), 실패(<=0)
 	 */
	public int insertBuyer(BuyerVO buyer);
	
	/**
	 * buyer 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없는 경우, size()==0
	 */
	public List<BuyerVO> selectBuyerList(PagingInfoVO pagingVO);
	
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
	
	
	public int selectBuyerCount(PagingInfoVO pagingVO);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
