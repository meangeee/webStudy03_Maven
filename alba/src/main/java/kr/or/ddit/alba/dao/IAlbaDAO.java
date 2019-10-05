package kr.or.ddit.alba.dao;

import java.util.List;

import kr.or.ddit.vo.AlbaVO;

public interface IAlbaDAO {
	
	
	/** 알바 목록 조회
	 * @return 조건에 맞는 알바 없는 경우, size==0
	 */
	public List<AlbaVO> selectAlbaList();
	
	/** 알바 상세 조회
	 * @param alba 조회활 알바에 대한 조건을 가진 VO
	 * @return 조건에 맞는 알바가 없는 경우 , null 반환
	 */
	public AlbaVO selectAlba(String alba_id);
	
	/** 신규 등록
	 * @param alba
	 * @return 성공 (>0) 실패 (<=0)
	 */
	public int insertAlba(AlbaVO alba);
	
	
	/** 정보 수정
	 * @param alba
	 * @return
	 */
	public int updateAlba(AlbaVO alba);
	
	public int deleteAlba(String alba_id);
}
