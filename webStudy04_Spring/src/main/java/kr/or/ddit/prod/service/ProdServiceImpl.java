package kr.or.ddit.prod.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;
@Service
public class ProdServiceImpl implements IProdService {
	@Inject
	private IProdDAO dao;	

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
		if (dao.insertProd(prod) == 0) {
			int cnt = dao.insertProd(prod);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		}
		return result;
	}

	@Override
	public int retrievevProdCount(PagingInfoVO pagingVO) {
		return dao.selectProdCount(pagingVO);
	}
	

	@Override
	public List<ProdVO> retrieveProdList(PagingInfoVO pagingVO) {
		return dao.selectProdList(pagingVO);
	}
	
	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = dao.selectProd(prod_id);
		if(prod==null) 
			throw new CommonException(prod_id+" 상품이 없음.");
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProd_id());
		ServiceResult result = null;
		int cnt = dao.updateProd(prod);
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
		
	}

}
