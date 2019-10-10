package kr.or.ddit.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.dao.Board2DAOImpl;
import kr.or.ddit.board.dao.IBoard2DAO;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

public class BoardServiceImpl implements IBoardService {
	IBoard2DAO boardDAO = new Board2DAOImpl();
	SqlSessionFactory sqlSession = 
				CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int retrieveCountBoard(PagingInfoVO<Board2VO> pagingVO) {
		
		return 0;
	}

	@Override
	public List<Board2VO> retrieveBoardList(PagingInfoVO<Board2VO> paingVO) {
		return null;
	}

	@Override
	public Board2VO retrieveBoard(Board2VO board) {
		Board2VO savedBoard = boardDAO.selectBoard(board);
		if(savedBoard==null) throws new CommonException(board.getBo_no()+", 해당 글이 없음.");
		boardDAO.
	}

}
