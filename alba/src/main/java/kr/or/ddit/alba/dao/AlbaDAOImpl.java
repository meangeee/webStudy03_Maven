package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;

public class AlbaDAOImpl implements IAlbaDAO {
	private SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<AlbaVO> selectAlbaList() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlbaList();
		}
	}

	@Override
	public AlbaVO selectAlba(String alba_id) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlba(alba_id);
		}
	}

	@Override
	public int insertAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAlba(String alba_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
