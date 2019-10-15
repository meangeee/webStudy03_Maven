package kr.or.ddit.buyer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

@Service
public class BuyerServiceImpl implements IBuyerService{
	@Inject
	IBuyerDAO dao;
	
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
