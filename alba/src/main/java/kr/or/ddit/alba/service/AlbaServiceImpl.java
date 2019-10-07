package kr.or.ddit.alba.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicenseAlbaVO;
import kr.or.ddit.vo.LicenseVO;

public class AlbaServiceImpl implements IAlbaService {

	private IAlbaDAO dao = new AlbaDAOImpl();

	SqlSessionFactory sessionFactory = kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<AlbaVO> retrieveAlbaList() {
		return dao.selectAlbaList();
	}

	@Override
	public AlbaVO retrieveAlba(String alba_id) {
		return dao.selectAlba(alba_id);
	}

	@Override
	public int createAlba(AlbaVO alba) {
		try (SqlSession sqlSession = sessionFactory.openSession();) {
			int rowCnt = dao.insertAlba(alba, sqlSession);
			List<LicenseAlbaVO> licAlbaList = alba.getLicenseAlbaList();
			if (licAlbaList != null && licAlbaList.size() > 0) {
				dao.insertAlba(alba, sqlSession);
			}
			if (rowCnt > 0) {
				sqlSession.commit();
			}
			return rowCnt;
		}
	}

	@Override
	public int modifyAlba(AlbaVO alba) {
		return dao.updateAlba(alba);
	}

	@Override
	public int removeAlba(String alba_id) {
		return dao.deleteAlba(alba_id);
	}

	@Override
	public int insertImg(LicenseAlbaVO lvo) {
		return dao.insertImg(lvo);
	}

	@Override
	public LicenseAlbaVO selecImg(LicenseAlbaVO lvo) {
		return dao.selecImg(lvo);
	}

	@Override
	public int updateImg(LicenseAlbaVO lvo) {
		return dao.updateImg(lvo);
	}

	@Override
	public List<GradeVO> gradeList() {
		return dao.gradeList();
	}

	@Override
	public List<LicenseVO> licenseList() {
		return dao.licenseList();
	}

}
