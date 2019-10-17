package kr.or.ddit.board.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.vo.Board2VO;

@Repository
public class Attatch2DAOImpl implements IAttatch2DAO {
	@Inject
	SqlSessionTemplate sqlSession;

	@Override
	public int insertAttatches(Board2VO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAttatches(Board2VO board, SqlSession sqlSession) {
		IAttatch2DAO mapper = sqlSession.getMapper(IAttatch2DAO.class);
		return mapper.insertAttatches(board);
	}

	@Override
	public Attatch2VO selectAttatch(int att_no) {
		IAttatch2DAO mapper = sqlSession.getMapper(IAttatch2DAO.class);
		return mapper.selectAttatch(att_no);
	}

	@Override
	public int deleteAttatches(Board2VO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAttatches(Board2VO board, SqlSession sqlSession) {
		IAttatch2DAO mapper = sqlSession.getMapper(IAttatch2DAO.class);
		return mapper.deleteAttatches(board);
	}

	@Override
	public int updateDowncount(int att_no) {
		IAttatch2DAO mapper = sqlSession.getMapper(IAttatch2DAO.class);
		int cnt = mapper.updateDowncount(att_no);
		sqlSession.commit();
		return cnt;
	}

}
