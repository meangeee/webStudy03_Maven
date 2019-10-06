package kr.or.ddit.alba.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaUpdateController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	@URIMapping("/alba/albaUpdate.do")
	public String openForm(HttpServletRequest req, HttpServletResponse resp) {
		String al_id= req.getParameter("al_id");
		AlbaVO alba = service.retrieveAlba(al_id);
		req.setAttribute("alba", alba);
		return "alba/albaForm";
	}
	
	
	@URIMapping(value="/alba/albaUpdate.do" , method=HttpMethod.POST)
	public String updateAlba(HttpServletRequest req, HttpServletResponse resp) {
		AlbaVO alba = new AlbaVO();
		req.setAttribute("alba", alba);
		try {
			BeanUtils.populate(alba, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		int cnt = service.modifyAlba(alba);
		String viewName = null;
		String message = null;
		if(cnt>0) {
			message = "수정 성공";
			req.getSession().setAttribute("message", message);
			viewName = "redirect:/alba/albaView.do?al_id="+alba.getAl_id();
//			viewName = "redirect:/alba/albaList.do";
		}else {
			message = "수정 실패";
			req.getSession().setAttribute("message", message);
			viewName = "alba/albaView";
		}
		return viewName;
		
	}
	
}
