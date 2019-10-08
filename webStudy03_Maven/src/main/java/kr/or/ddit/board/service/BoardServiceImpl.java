package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.Board2DAOImpl;
import kr.or.ddit.board.dao.IBoard2DAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

public class BoardServiceImpl implements IBoardService {
	IBoard2DAO boardDAO = new Board2DAOImpl();

	@Override
	public ServiceResult createBoard(Board2VO board)

	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board2VO retrieveBoard(Board2VO board) {
		Board2VO saved = boardDAO.selectBoard(board);
		if(saved == null) throw new CommonException("해당 게시물 없음");
		boardDAO.updateBoardHit(board);
		return saved;
	}

	@Override
	public int retrieveBoardCount

	(PagingInfoVO<Board2VO> pagingVO) {
		return boardDAO.selectBoardCount(pagingVO);
	}

	@Override
	public List<Board2VO> retrieveBoardList

	(PagingInfoVO<Board2VO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public ServiceResult modifyBoard(Board2VO board)

	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(Board2VO board)

	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attatch2VO downloadAttatch(int att_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult incrementLike(int bo_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
