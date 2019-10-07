package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicenseAlbaVO;
import kr.or.ddit.vo.LicenseVO;

public class AlbaDAOImpl implements IAlbaDAO {
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<AlbaVO> selectAlbaList() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlbaList();
		}
	}

	@Override
	public AlbaVO selectAlba(String alba_id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlba(alba_id);
		}
	}

	@Override
	public int insertAlba(AlbaVO alba, SqlSession sqlSession) {
		return sqlSession.insert("kr.or.ddit.alba.dao.IAlbaDAO.insertAlba", alba);
	}

	@Override
	public int updateAlba(AlbaVO alba) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			int cnt = mapper.updateAlba(alba);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteAlba(String alba_id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			int cnt = mapper.deleteAlba(alba_id);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int insertImg(LicenseAlbaVO lvo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			int cnt = mapper.insertImg(lvo);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public LicenseAlbaVO selecImg(LicenseAlbaVO lvo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selecImg(lvo);
		}
	}

	@Override
	public int updateImg(LicenseAlbaVO lvo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			int cnt = mapper.updateImg(lvo);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public List<GradeVO> gradeList() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.gradeList();
		}
	}

	@Override
	public List<LicenseVO> licenseList() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.licenseList();
		}
	}

}
