package kr.or.ddit.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	//spring bean 등록 + Command handler 지정
public class HelloController {
	
	@RequestMapping("/hello.do")
	public String hello(Model model) {//POJO
		Date today = new Date();
		//request에 담은거랑 똑같음
		model.addAttribute("today", today);
		return "hello";
	}
}
