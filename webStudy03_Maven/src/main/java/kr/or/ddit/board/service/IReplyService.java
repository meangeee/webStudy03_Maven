package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

public interface IReplyService {
	/**등록 성공, 실패
	 * @param reply
	 * @return
	 */
	public ServiceResult createReply(Reply2VO reply);
	public int retrieveReplyCount(PagingInfoVO<Reply2VO> pagingVO);
	public List<Reply2VO> retrieveReplyList(PagingInfoVO<Reply2VO> paiInfoVO);
	
	/** 경우의 수 3가지
	 * DB에서 확인 함
	 * 경우의수가 존재하지 않거나 , 실패, 성공
	 * 실패일때는 인증 실패임. 
	 * @param reply
	 * @return
	 */
	public ServiceResult modifyReply(Reply2VO reply);
	/** 있거나 없거나 인증실패, 삭제 실패 = 인증실패
	 * @param reply
	 * @return
	 */
	public ServiceResult removeReply(Reply2VO reply);
	
}
