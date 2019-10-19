package kr.or.ddit.common.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.MemberVO;

@Controller
public class GetUserListController {
	@RequestMapping(value="/getUserList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Set<MemberVO> userList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		Set<MemberVO> userList = 
				(Set<MemberVO>) req.getServletContext().getAttribute("userList");
		return userList;
	}
}











