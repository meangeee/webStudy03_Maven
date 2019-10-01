package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
@CommandHandler
public class BuyerUpdateController{
	IBuyerService service = new BuyerServiceImpl();
	
	@URIMapping(value="/buyer/buyerUpdate.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BuyerVO bv = new BuyerVO();
		
		try {
			BeanUtils.populate(bv, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		int cnt = service.updateBuyer(bv);
		req.setAttribute("cnt", cnt);
		String viewName = "buyer/buyerList";
		
		return viewName;
		
	}
	
}
