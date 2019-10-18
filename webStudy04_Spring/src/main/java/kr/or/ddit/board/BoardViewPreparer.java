package kr.or.ddit.board;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.board.dao.IBoard2DAO;
import kr.or.ddit.common.annotations.Preparer;
import kr.or.ddit.vo.MenuVO;

@Preparer
public class BoardViewPreparer implements ViewPreparer{
	@Inject
	IBoard2DAO service;

	@Override
	public void execute(Request req, AttributeContext ctx) {
		
		MenuVO menu1 = new MenuVO("게시판목록","/board/boardList.do");
		MenuVO menu2 = new MenuVO("게시판생성","/board/boardInsert.do");
		List<MenuVO> menuList = Arrays.asList(menu1, menu2);
		Map<String, Object> requestScope =
				req.getContext(Request.APPLICATION_SCOPE);
		requestScope.put("menuList", menuList);
	}
	
	
}
