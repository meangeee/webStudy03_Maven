package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IBoard2DAO {
	//바인딩
	public int insertBoard(Board2VO board);
	//등록은 얘로
	public int insertBoard(Board2VO board, SqlSession sqlSession);
	//조회수 증가
	public int updateBoardHit(Board2VO board);
	public Board2VO selectBoard(Board2VO board);
	//paging
	public int selectBoardCount(PagingInfoVO<Board2VO> pagingVO);
	public List<Board2VO>selectBoardList(PagingInfoVO<Board2VO> pagingVO);
	public int updateBoard(Board2VO board);
	public int updateBoard(Board2VO board, SqlSession sqlSession);
	public int deleteBoard(Board2VO board);
	public int deleteBoard(Board2VO board, SqlSession sqlSession); //mybatis가 뭘 허용 안하죠?
	//추천수
	public int updateBoLike(Board2VO board);
}
