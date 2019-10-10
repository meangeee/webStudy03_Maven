package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IReply2DAO;
import kr.or.ddit.board.dao.Reply2DAOImpl;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

public class ReplyServiceImpl implements IReplyService {
	IReply2DAO replyDAO = new Reply2DAOImpl();
	
	
	
	@Override
	public List<Reply2VO> retrieveReplyList(PagingInfoVO<Reply2VO> pagingVO) {
		return replyDAO.selectReplyList(pagingVO);
	}

	@Override
	public int retrieveReplyCount(PagingInfoVO<Reply2VO> pagingVO) {
		return replyDAO.selectReplyCount(pagingVO);
	}

	@Override
	public ServiceResult createReply(Reply2VO reply) {
		ServiceResult result = null;
		int cnt = replyDAO.insertReply(reply);
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public ServiceResult modifyReply(Reply2VO reply) {
		ServiceResult result = null;
		int cnt = replyDAO.updateReply(reply);
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.INVALIDPASSWORD;
		return result;
	}

	@Override
	public ServiceResult deleteReply(Reply2VO reply) {
		ServiceResult result = null;
		int cnt = replyDAO.deleteReply(reply);
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.INVALIDPASSWORD;
		return result;
	}

}
