package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IBoard2DAO {
	
	//전체 출력
	public List<Board2VO> selectBoardList(PagingInfoVO<Board2VO> pagingVO);
	
	//하나만 출력
	public Board2VO selectBoard(Board2VO board);
	
	//페이징
	public int selectBoardCount(PagingInfoVO<Board2VO> pagingVo);
	
	//조회수
	public int updateBoardHit(Board2VO board);
}
