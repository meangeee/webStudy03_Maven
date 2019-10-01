package kr.or.ddit.buyer.controller;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class BuyerInsertController {
	IBuyerService service = BuyerServiceImpl.getInstance();
	@URIMapping("/buyer/buyerInsert.do")
	public String insertBuyer(HttpServletRequest req, HttpServletResponse resp) {
		BuyerVO bv = new BuyerVO();

		try {
			BeanUtils.populate(bv, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		int insertBuyer = service.insertBuyer(bv);
		req.setAttribute("insertBuyer", insertBuyer);
		String viewName = "buyer/buyerList";

		return viewName;
		
	}
	
}
