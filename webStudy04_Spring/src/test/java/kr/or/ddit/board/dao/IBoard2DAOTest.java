package kr.or.ddit.board.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
@Transactional
public class IBoard2DAOTest {
	@Inject
	IBoard2DAO boardDAO;
	PagingInfoVO<Board2VO> pagingVO;
	Board2VO board;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setupBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("setup, "+boardDAO);
		pagingVO = new PagingInfoVO<>();
		board = new Board2VO(1000);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("teardown");
	}

	@Test
	public void testInsertBoard() {
		board.setBo_writer("작성자");
		board.setBo_content("내용");
		board.setBo_ip("aaaa");
		board.setBo_title("제목");
		board.setBoard_type("B01");
		board.setBo_pass("1111");
		int rowCnt = boardDAO.insertBoard(board);
		assertEquals(1, rowCnt);
	}

	@Test
	public void testUpdateBoardHit() {
		int rowCnt = boardDAO.updateBoardHit(board);
		assertEquals(0, rowCnt);
		assertThat(rowCnt, equalTo(0));
	}

	@Test
	public void testSelectBoard() {
		Board2VO board = boardDAO.selectBoard(new Board2VO(1000));
		assertThat(board, nullValue());
	}

	@Test
	public void testSelectBoardCount() {
		Board2VO searchVO = new Board2VO();
		searchVO.setBoard_type("B01");
		pagingVO.setSearchVO(searchVO);
		int count = boardDAO.selectBoardCount(pagingVO);
		assertThat(count, greaterThanOrEqualTo(100));
	}

	@Test
	public void testSelectBoardList() {
		Board2VO searchVO = new Board2VO();
		searchVO.setBoard_type("B01");
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(1);
		List<Board2VO> list = boardDAO.selectBoardList(pagingVO);
		assertThat(list, not(hasItem(new Board2VO(606))));
	}

	@Test(expected=RuntimeException.class)
	public void testUpdateBoard() {
		board.setBo_no(606);
		boardDAO.updateBoard(board);
	}

	@Test(timeout=1000)
	public void testDeleteBoard() {
		int rowCnt = boardDAO.deleteBoard(board);
		assertEquals(0, rowCnt);
	}

	@Test
	public void testUpdateBoLike() {
		board.setBo_no(606);
		int rowCnt = boardDAO.updateBoLike(board);
		assertThat(rowCnt, greaterThan(0));
	}

}







