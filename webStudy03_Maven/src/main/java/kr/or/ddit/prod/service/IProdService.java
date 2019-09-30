package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Business Logic Layer
 */
public interface IProdService {
	
	/**
	 * 
	 * @param prod
	 * @return OK, FAILED
	 */
	public ServiceResult createProd(ProdVO prod);
	
	/**
	 * 
	 * @return
	 */
	public List<ProdVO> retrieveProdList();
	
	public ProdVO retrieveProd(String prod_id);
	
	/**
	 * 
	 * @param prod
	 * @return CommonException, OK, FAILED
	 */
	public ServiceResult modifyProd(ProdVO prod);
	
}
