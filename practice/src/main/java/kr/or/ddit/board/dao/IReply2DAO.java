package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

public interface IReply2DAO {
	
	//전체
	public List<Reply2VO> selectReplyList(PagingInfoVO<Reply2VO> pagingVO);
	//글번호
	public int selectReplyCount(PagingInfoVO<Reply2VO> pagingVO);
	//생성
	public int insertReply(Reply2VO reply);
	//수정
	public int updateReply(Reply2VO reply);
	//삭제
	public int deleteReply(Reply2VO reply);
}
