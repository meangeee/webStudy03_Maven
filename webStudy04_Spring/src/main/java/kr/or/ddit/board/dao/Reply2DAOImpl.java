package kr.or.ddit.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

@Repository
public class Reply2DAOImpl implements IReply2DAO {
	@Inject
	SqlSessionTemplate sqlSession;

	@Override
	public int insertReply(Reply2VO reply) {
		IReply2DAO mapper = sqlSession.getMapper(IReply2DAO.class);
		int cnt = mapper.insertReply(reply);
		sqlSession.commit();
		return cnt;
	}

	@Override
	public int selectReplyCount(PagingInfoVO<Reply2VO> pagingVO) {
		IReply2DAO mapper = sqlSession.getMapper(IReply2DAO.class);
		return mapper.selectReplyCount(pagingVO);
	}

	@Override
	public List<Reply2VO> selectReplyList(PagingInfoVO<Reply2VO> pagingVO) {
		IReply2DAO mapper = sqlSession.getMapper(IReply2DAO.class);
		return mapper.selectReplyList(pagingVO);
	}

	@Override
	public int updateReply(Reply2VO reply) {
			IReply2DAO mapper = sqlSession.getMapper(IReply2DAO.class);
			int cnt = mapper.updateReply(reply);
			sqlSession.commit();
			return cnt;
	}

	@Override
	public int deleteReply(Reply2VO reply) {
				IReply2DAO mapper = sqlSession.getMapper(IReply2DAO.class);
			int cnt = mapper.deleteReply(reply);
			sqlSession.commit();
			return cnt;
		}
	}

