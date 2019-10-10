package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IBoardService {
	
	public int retrieveCountBoard(PagingInfoVO<Board2VO> pagingVO);

	public List<Board2VO> retrieveBoardList(PagingInfoVO<Board2VO> paingVO);
	
	public Board2VO retrieveBoard(Board2VO board);
}
