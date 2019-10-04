package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.vo.AlbaVO;

public class AlbaServiceImpl implements IAlbaService {
	
	private IAlbaService dao = new AlbaServiceImpl();
	//로직 재활용
	private static IAlbaService instance;
	
	public static IAlbaService getInstance() {
		if(instance==null) instance = new AlbaServiceImpl();
		return instance;
	}
	
	@Override
	public List<AlbaVO> retrieveAlbaList() {
		
		return dao.retrieveAlbaList();
	}

	@Override
	public AlbaVO retrieveAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

}
