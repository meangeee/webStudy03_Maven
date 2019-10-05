package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.vo.AlbaVO;

public class AlbaServiceImpl implements IAlbaService {
	
	private IAlbaDAO dao = new AlbaDAOImpl();
	
	private static IAlbaService instance;
	
	public static IAlbaService getInstance() {
		if(instance==null) instance = new AlbaServiceImpl();
		return instance;
	}
	
	@Override
	public List<AlbaVO> retrieveAlbaList() {
		return dao.selectAlbaList();
	}

	@Override
	public AlbaVO retrieveAlba(String alba_id) {
		return dao.selectAlba(alba_id);
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
