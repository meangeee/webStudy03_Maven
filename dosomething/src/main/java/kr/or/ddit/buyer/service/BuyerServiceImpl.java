package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerServiceImpl implements IBuyerService{
	
	IBuyerDAO dao = new BuyerDAOImpl();
	
	@Override
	public int insertBuyer(BuyerVO buyer) {
		return dao.insertBuyer(buyer);
	}

	@Override
	public int retrieveBuyerCount(PagingInfoVO pagingVO) {
		return dao.selectBuyerCount(pagingVO);
	}
	
	@Override
	public List<BuyerVO> selectBuyerList(PagingInfoVO pagingVO) {
		return dao.selectBuyerList(pagingVO);
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
