package kr.or.ddit.board.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.board.dao.IAttatch2DAO;
import kr.or.ddit.board.dao.IBoard2DAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class BoardServiceImplTest {
	@Mock
	IBoard2DAO mockDAO;
	@Mock
	IAttatch2DAO mockAttDAO;
	
	@InjectMocks
	BoardServiceImpl service;

	PagingInfoVO<Board2VO> pagingVO;
	@Mock
	Board2VO mockBoard;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		pagingVO = mock(PagingInfoVO.class);
		when(pagingVO.getStartRow()).thenReturn(1);
		when(pagingVO.getEndRow()).thenReturn(10);
		when(mockBoard.getBo_no()).thenReturn(1000);
		List<Attatch2VO> attachList = mock(ArrayList.class);
		when(attachList.size()).thenReturn(2);
		when(attachList.get(0)).thenReturn(mock(Attatch2VO.class));
		when(attachList.get(1)).thenReturn(mock(Attatch2VO.class));
		when(mockBoard.getAttatchList()).thenReturn(attachList);
		when(mockBoard.getBo_pass()).thenReturn("1234");
//		Integer[] array = mock(Integer[].class);
		Attatch2VO att1 = mock(Attatch2VO.class);
		when(att1.getAtt_savename()).thenReturn("test");
		when(mockAttDAO.selectAttatch(1)).thenReturn(att1);
		when(mockAttDAO.selectAttatch(2)).thenReturn(att1);
		when(mockBoard.getDelAttaches()).thenReturn(new Integer[] {new Integer(1), new Integer(2)});
		Board2VO savedBoard = mock(Board2VO.class);
		when(savedBoard.getBo_pass()).thenReturn("1234");
		when(mockDAO.selectBoard(mockBoard)).thenReturn(savedBoard);
	}

	@Test
	public void testCreateBoard() {
		when(mockDAO.insertBoard(mockBoard)).thenReturn(1);
		ServiceResult result = service.createBoard(mockBoard);
		assertThat(result, equalTo(ServiceResult.OK));
	}

	@Test
	public void testRetrieveBoard() {
		Board2VO savedBoard = service.retrieveBoard(mockBoard);
		assertNotNull(savedBoard);
	}

	@Test
	public void testRetrieveBoardCount() {
		when(mockDAO.selectBoardCount(pagingVO)).thenReturn(100);
		int count = service.retrieveBoardCount(pagingVO);
		assertEquals(100, count);
	}

	@Test
	public void testRetrieveBoardList() {
		List<Board2VO> result = mock(ArrayList.class);
		when(result.size()).thenReturn(10);
		when(mockDAO.selectBoardList(pagingVO)).thenReturn(result);
		List<Board2VO> list = service.retrieveBoardList(pagingVO);
		assertEquals(10, list.size());
	}

	@Test
	public void testModifyBoard() {
		when(mockBoard.getBo_pass()).thenReturn("1111");
		ServiceResult result = service.modifyBoard(mockBoard);
		assertThat(result, equalTo(ServiceResult.INVALIDPASSWORD));
		when(mockBoard.getBo_pass()).thenReturn("1234");
		when(mockDAO.updateBoard(mockBoard)).thenReturn(0);
		when(mockAttDAO.insertAttatches(mockBoard)).thenReturn(0);
		when(mockAttDAO.deleteAttatches(mockBoard)).thenReturn(1);
		result = service.modifyBoard(mockBoard);
		assertThat(result, equalTo(ServiceResult.OK));
	}

	@Test
	public void testRemoveBoard() {
		when(mockDAO.deleteBoard(mockBoard)).thenReturn(0);
		ServiceResult result = service.removeBoard(mockBoard);
		assertThat(result, equalTo(ServiceResult.FAILED));
		when(mockDAO.deleteBoard(mockBoard)).thenReturn(1);
	}

	@Test
	public void testDownloadAttatch() {
		Attatch2VO attVO = service.downloadAttatch(1);
		assertNotNull(attVO);
	}

	@Test
	public void testIncrementLike() {
		when(mockDAO.updateBoLike(new Board2VO(1000))).thenReturn(1);
		ServiceResult result = service.incrementLike(mockBoard.getBo_no());
		assertThat(result, equalTo(ServiceResult.OK));
	}

}












