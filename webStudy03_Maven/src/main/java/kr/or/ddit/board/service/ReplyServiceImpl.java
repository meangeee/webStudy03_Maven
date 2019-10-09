package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IReply2DAO;
import kr.or.ddit.board.dao.Reply2DAOImpl;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

public class ReplyServiceImpl implements IReplyService {
	IReply2DAO reqDAO = new Reply2DAOImpl();
	
	@Override
	public ServiceResult createReply(Reply2VO reply) {
		
		return null;
	}

	@Override
	public int retrieveReplyCount(PagingInfoVO<Reply2VO> pagingVO) {
			return reqDAO.selectReplyCount(pagingVO);
		}

	@Override
	public List<Reply2VO> retrieveReplyList(PagingInfoVO<Reply2VO> pagingVO) {
		return reqDAO.selectReplyList(pagingVO);
	}

	@Override
	public ServiceResult modifyReply(Reply2VO reply) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeReply(Reply2VO reply) {
		// TODO Auto-generated method stub
		return null;
	}

}
