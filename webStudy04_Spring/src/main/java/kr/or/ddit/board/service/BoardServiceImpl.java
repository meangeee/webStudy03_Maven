package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IAttatch2DAO;
import kr.or.ddit.board.dao.IBoard2DAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

@Service
public class BoardServiceImpl implements IBoardService {
	@Inject
	IBoard2DAO boardDAO;
	@Inject
	IAttatch2DAO attatchDAO;

	public static String encryptSha512(String plain) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] encrypted = md.digest(plain.getBytes());
		System.out.println(encrypted.length);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
		return encoded;
	}

	File saveFolder = new File("d:/saveFiles");

	private int processAttatch(Board2VO board) {
		List<Attatch2VO> attatchList = board.getAttatchList();
		int cnt = 0;
		if (attatchList != null && attatchList.size() > 0) {
			cnt = attatchDAO.insertAttatches(board);
			for (Attatch2VO attatch : attatchList) {
				// if(1==1) throw new RuntimeException("강제 발생 예외");
				try {
					attatch.saveFile(saveFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	private int deleteAttatch(Board2VO board) {
		Integer[] delAtts = board.getDelAttaches();
		int cnt = 0;
		if (delAtts != null && delAtts.length > 0) {
			for (int delNo : delAtts) {
				Attatch2VO attatch = attatchDAO.selectAttatch(delNo);
				FileUtils.deleteQuietly(new File(saveFolder, attatch.getAtt_savename()));
			}
			cnt += attatchDAO.deleteAttatches(board);
		}
		return cnt;
	}

	@Override
	public ServiceResult createBoard(Board2VO board) {
		String bo_pass = board.getBo_pass();
		try {
			bo_pass = encryptSha512(board.getBo_pass());
			board.setBo_pass(bo_pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		int cnt = boardDAO.insertBoard(board);
		cnt += processAttatch(board);
		ServiceResult result = null;
		if (cnt > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public Board2VO retrieveBoard(Board2VO board) {
		Board2VO savedBoard = boardDAO.selectBoard(board);
		if (savedBoard == null)
			throw new CommonException(board.getBo_no() + ", 해당 글이 없음.");
		boardDAO.updateBoardHit(board);
		return savedBoard;
	}

	@Override
	public int retrieveBoardCount(PagingInfoVO<Board2VO> pagingVO) {
		return boardDAO.selectBoardCount(pagingVO);
	}

	@Override
	public List<Board2VO> retrieveBoardList(PagingInfoVO<Board2VO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public ServiceResult modifyBoard(Board2VO board) {
		Board2VO savedBoard = retrieveBoard(board);
		ServiceResult result = null;
		String bo_pass = null;
		try {
			bo_pass = encryptSha512(board.getBo_pass());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (savedBoard.getBo_pass().equals(bo_pass)) {
			int cnt = boardDAO.updateBoard(board);
			cnt += processAttatch(board);
			cnt += deleteAttatch(board);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override // AOP
	public ServiceResult removeBoard(Board2VO board) {
		Board2VO savedBoard = retrieveBoard(board);
		ServiceResult result = null;
		String bo_pass = null;
		try {
			bo_pass = encryptSha512(board.getBo_pass());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (savedBoard.getBo_pass().equals(bo_pass)) {
			List<Attatch2VO> attatchList = savedBoard.getAttatchList();
			int cnt = 0;
			if (attatchList != null && attatchList.size() > 0) {
				// meta delete
				cnt += attatchDAO.deleteAttatches(board);
			}
			// board delete
			cnt += boardDAO.deleteBoard(board);
			if (cnt > 0) {
				if (attatchList != null) {
					// binary delete
					for (Attatch2VO attatch : attatchList) {
						FileUtils.deleteQuietly(new File(saveFolder, attatch.getAtt_savename()));
					}
				}
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public Attatch2VO downloadAttatch(int att_no) {
		Attatch2VO attatch = attatchDAO.selectAttatch(att_no);
		if (attatch == null)
			throw new CommonException(att_no + "파일 없음.");
		attatchDAO.updateDowncount(att_no);
		return attatch;
	}

	@Override
	public ServiceResult incrementLike(int bo_no) {
		int cnt = boardDAO.updateBoLike(new Board2VO(bo_no));
		ServiceResult result = null;
		if (cnt > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		return result;
	}

}
