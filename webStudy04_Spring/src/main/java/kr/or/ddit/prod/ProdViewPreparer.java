package kr.or.ddit.prod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotations.Preparer;
import kr.or.ddit.vo.MenuVO;

@Preparer
public class ProdViewPreparer implements ViewPreparer{
	
	@Override
	public void execute(Request req, AttributeContext arg1) {
		MenuVO menu1 = new MenuVO("상품목록","/prod");
		MenuVO menu2 = new MenuVO("신규상품등록","/prod/prodInsert.do");
		List<MenuVO> menuList = Arrays.asList(menu1, menu2);
		Map<String, Object> requestScope =
				req.getContext(Request.APPLICATION_SCOPE);
		requestScope.put("menuList",menuList);
	}
}
