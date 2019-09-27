package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	
	//dao와의 의존관계
	IProdDAO dao = new ProdDAOImpl();
	
	//자기자신
	private static IProdService instance;
	
	//singleton
	public static IProdService getInstance() {
		if(instance == null) instance = new ProdServiceImpl();
		return instance;
	}
	
	@Override
	public ServiceResult createProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> retrievevProdList() {
		return dao.selectProdList();
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

}
