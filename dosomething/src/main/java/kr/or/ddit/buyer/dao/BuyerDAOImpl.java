package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerDAOImpl implements IBuyerDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();



	@Override
	public int insertBuyer(BuyerVO buyer) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			sqlSession.commit();
			return mapper.insertBuyer(buyer);
		}
	}
	
	@Override
	public int selectBuyerCount(PagingInfoVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerCount(pagingVO);
		}
	}
	
	
	@Override
	public List<BuyerVO> selectBuyerList(PagingInfoVO pagingVO) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerList(pagingVO);
		}
	}

	@Override
	public BuyerVO selectBuyer(BuyerVO buyer) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			return mapper.selectBuyer(buyer);
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
				IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
				sqlSession.commit();
				return mapper.updateBuyer(buyer);
			}
	}

	@Override
	public int deleteBuyer(BuyerVO buyer) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			sqlSession.commit();
			return mapper.deleteBuyer(buyer);
		}
	}

	
}
