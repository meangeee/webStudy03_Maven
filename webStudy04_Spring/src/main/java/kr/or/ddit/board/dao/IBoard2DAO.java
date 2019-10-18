package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

@Repository
public interface IBoard2DAO {
	public int insertBoard(Board2VO board);
	
	public int updateBoardHit(Board2VO board);
	
	public Board2VO selectBoard(Board2VO board);
	
	public int selectBoardCount(PagingInfoVO<Board2VO> pagingVO);
	
	public List<Board2VO> selectBoardList(PagingInfoVO<Board2VO> pagingVO);
	
	public int updateBoard(Board2VO board);
	
	public int deleteBoard(Board2VO board);
	
	public int updateBoLike(Board2VO board);
}












