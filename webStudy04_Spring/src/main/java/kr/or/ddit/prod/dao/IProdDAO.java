package kr.or.ddit.prod.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistence Layer
 * 
 */
@Repository
public interface IProdDAO {
	public int insertProd(ProdVO prod); //int 성공아니면 실패 여부 row count
	public int selectProdCount(PagingInfoVO pagingVO);
	public List<ProdVO> selectProdList(PagingInfoVO pagingVO);
	public ProdVO selectProd(String prod_id);
	public int updateProd(ProdVO prod);
	
}
