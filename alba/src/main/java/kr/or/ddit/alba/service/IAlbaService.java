package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.vo.AlbaVO;

public interface IAlbaService {

	public List<AlbaVO> retrieveAlbaList();
	
	public AlbaVO retrieveAlba(String alba_id);
	
	public int createAlba(AlbaVO alba);
	
	public int modifyAlba(AlbaVO alba);
	
	public int removeAlba(AlbaVO alba);
	
}
