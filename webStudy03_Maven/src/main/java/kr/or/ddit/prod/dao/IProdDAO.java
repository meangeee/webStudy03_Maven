package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistence Layer
 * 
 */
public interface IProdDAO {
	public int insertProd(ProdVO prod); //int 성공아니면 실패 여부 row count
	public List<ProdVO> selectProdList();
	public ProdVO selectProd(String prod_id);
	public int updateProd(ProdVO prod);
	
}
