package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

@RestController //복합어노테이션 controller, responsebody포함
public class IdCheckController{
	@Inject
	IMemberService service;

	@RequestMapping(value = "/member/idCheck.do", method=RequestMethod.POST, 
			produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> doPost(
			@RequestParam(required=true)String mem_id) throws IOException  {
//		String mem_id = req.getParameter("mem_id");
	
		//검증
		//있나없나
		Pattern regex = Pattern.compile("([a-z][a-zA-Z0-9]{3,11})");
		Matcher matcher = regex.matcher(mem_id);
		while(matcher.find()) {
			System.out.println(matcher.group(1));
		}
		
		Map<String, Object> result = new HashMap<>();
		try {
			service.retrieveMember(new MemberVO(mem_id, null));
			result.put("valid",new Boolean(false));
		}catch(UserNotFoundException e) {
			result.put("valid",new Boolean(true));
			result.put("useId", mem_id);
		}
//		resp.setContentType("application/json;charset=UTF-8");
//		String json = new MarshallingUtils().marshalling(result);
//		try(
//				PrintWriter out = resp.getWriter();
//		){
//			out.println(json);
//		}
		return result;
	}
}
