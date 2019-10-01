package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {
	private SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertProd(ProdVO prod) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			int cnt = mapper.insertProd(prod);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int selectProdCount(PagingInfoVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProdCount(pagingVO);
		}
	}
	
	@Override
	public List<ProdVO> selectProdList(PagingInfoVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
			){
		IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
		return mapper.selectProdList(pagingVO);
	}
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
			){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProd(prod_id);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();	
			){
				IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
				int cnt = mapper.updateProd(prod);
				sqlSession.commit();
				return cnt;
			}
	}

}
