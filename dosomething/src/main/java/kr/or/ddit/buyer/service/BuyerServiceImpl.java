package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.vo.BuyerVO;

public class BuyerServiceImpl implements IBuyerService{

	//결합력이 최상,,그래도 일단 써보자
	private IBuyerDAO dao = BuyerDAOImpl.getInstance();
	//내자신
	private static IBuyerService instance;
	//singleton
	public static IBuyerService getInstance() {
		if(instance==null) instance = new BuyerServiceImpl();
		return instance;
	}
	
	@Override
	public int insertBuyer(BuyerVO buyer) {
		return dao.insertBuyer(buyer);
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		return dao.selectBuyerList();
	}

	@Override
	public BuyerVO selectBuyer(BuyerVO buyer) {
		return dao.selectBuyer(buyer);
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		return dao.updateBuyer(buyer);
	}

	@Override
	public int deleteBuyer(BuyerVO buyer) {
		return dao.deleteBuyer(buyer);
	}

}
