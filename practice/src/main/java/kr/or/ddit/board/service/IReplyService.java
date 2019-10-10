package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

public interface IReplyService {

	//전체
	public List<Reply2VO> retrieveReplyList(PagingInfoVO<Reply2VO> pagingVO);
	//댓글번호
	public int retrieveReplyCount(PagingInfoVO<Reply2VO> pagingVO);
	//생성
	public ServiceResult createReply(Reply2VO reply);
	//수정
	public ServiceResult modifyReply(Reply2VO reply);
	//삭제
	public ServiceResult deleteReply(Reply2VO reply);
}
