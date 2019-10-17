package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IReply2DAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;
@Service
public class ReplyServiceImpl implements IReplyService {
	@Inject
	IReply2DAO dao;
	@Inject
	SqlSessionTemplate sqlSession;
	
	@Override
	public ServiceResult createReply(Reply2VO reply) {
		int cnt = dao.insertReply(reply);
		ServiceResult result = null;
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public int retrieveReplyCount(PagingInfoVO<Reply2VO> pagingVO) {
		return dao.selectReplyCount(pagingVO);
	}

	@Override
	public List<Reply2VO> retrieveReplyList(PagingInfoVO<Reply2VO> pagingVO) {
		return dao.selectReplyList(pagingVO);
	}

	@Override
	public ServiceResult modifyReply(Reply2VO reply) {
		int cnt = dao.updateReply(reply);
		ServiceResult result = null;
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.INVALIDPASSWORD;
		return result;
	}

	@Override
	public ServiceResult removeReply(Reply2VO reply) {
		int cnt = dao.deleteReply(reply);
		ServiceResult result = null;
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.INVALIDPASSWORD;
		return result;
	}

}
