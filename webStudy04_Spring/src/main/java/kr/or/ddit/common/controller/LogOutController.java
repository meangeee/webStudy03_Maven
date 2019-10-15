package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogOutController{

	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String doPost(HttpSession session, HttpServletResponse resp) throws ServletException, IOException {
		if(session.isNew()) {
			resp.sendError(400);
			return null;
		}
		session.invalidate();
		return "redirect:/";
		
	}
}
