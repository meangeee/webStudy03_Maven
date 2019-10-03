package kr.or.ddit.buyer.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerInsertController {
	IBuyerService service = new BuyerServiceImpl();
	@URIMapping("/buyer/buyerInsert.do")
	public String insertBuyer(HttpServletRequest req, HttpServletResponse resp) {
//		BuyerVO bv = new BuyerVO();
//
//		try {
//			BeanUtils.populate(bv, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
//		int insertBuyer = service.insertBuyer(bv);
//		req.setAttribute("insertBuyer", insertBuyer);
//		String viewName = "buyer/buyerList";
//
//		return viewName;
		return "buyer/buyerList";
		
	}
	
	@URIMapping(value="/buyer/buyerInsert.do", method=HttpMethod.POST)
	public String insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
		
		try {
			BeanUtils.populate(buyer, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		Part part = req.getPart("buyer_image");
		long size = part.getSize();
		if(size>0) {
			//1. 저장위치
			String saveFolderURL = "/buyerImages";
			String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
			File saveFolder = new File(saveFolderPath);
			if(!saveFolder.exists()) saveFolder.mkdirs();
			//2. 저장명
			String savename = UUID.randomUUID().toString();
			try(
				InputStream is = part.getInputStream();
			){
				FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename));
			}
			buyer.setBuyer_img(savename);
			
		}
		return "buyer/buyerList";
	}
}















