package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicenseAlbaVO;
import kr.or.ddit.vo.LicenseVO;

public interface IAlbaService {

	public List<AlbaVO> retrieveAlbaList();

	public AlbaVO retrieveAlba(String alba_id);

	public int createAlba(AlbaVO alba);

	public int modifyAlba(AlbaVO alba);

	public int removeAlba(String alba_id);

	/**
	 * 자격증 이미지 추가
	 * 
	 * @param alba
	 * @return
	 */
	public int insertImg(LicenseAlbaVO lvo);

	/**
	 * 자격증 이미지 조회
	 * 
	 * @param lvo
	 * @return
	 */
	public LicenseAlbaVO selecImg(LicenseAlbaVO lvo);

	/**
	 * 자격증 업데이트
	 * 
	 * @param lvo
	 * @return
	 */
	public int updateImg(LicenseAlbaVO lvo);

	// 학력이름리스트
	public List<GradeVO> gradeList();

	// 자격증명리스트
	public List<LicenseVO> licenseList();
}
