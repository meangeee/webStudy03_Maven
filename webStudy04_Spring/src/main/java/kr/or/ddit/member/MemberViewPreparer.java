package kr.or.ddit.member;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotations.Preparer;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MenuVO;
@Preparer
public class MemberViewPreparer implements ViewPreparer {
	@Inject
	IMemberService service;
	
	@Override
	public void execute(Request req, AttributeContext ctx) {
		//메뉴구조???도??
		MenuVO menu1 = new MenuVO("회원목록","/member/memberList.do");
		MenuVO menu2 = new MenuVO("신규가입","/member/memberInsert.do");
		List<MenuVO> menuList = Arrays.asList(menu1, menu2);
		Map<String, Object> requestScope = 
				req.getContext(Request.APPLICATION_SCOPE);
		requestScope.put("menuList", menuList);
	}

}
