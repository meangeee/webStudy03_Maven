package kr.or.ddit.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController {
	@Inject
	IMemberService service;

	@RequestMapping(value = "/member/memberUpdate.do", method = RequestMethod.POST)
	public String doPost(MemberVO member, HttpSession session) {
		/*
		 * 1. 요청(파라미터,헤더,바디...)받고, 2.분석(검증) 3.통과 1)의존성
		 * 2)로직선택(ServiceResultmodifyMember(member)) session에 메세지 넣기 - INVALIDPASSWORD :
		 * redirect -> mypage - OK : redirect -> mypage - FAILED : redirect -> mypage
		 * 4.불통과 : redirect ->mypage
		 * 
		 */
		
		// 2. 분석 검증
		Map<String, String> errors = new HashMap<String, String>();
		boolean valid = validate(member, errors);
		String viewName = "redirect:/mypage";
		String message = null;
		if (valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				message = "비번오류";
				break;
			case FAILED:
				message = "서버 오류";
				break;

			default:
				message = "수정 성공";
				break;
			}
		} else {

		}
		session.setAttribute("message", message);
		session.setAttribute("errors", errors);
		return viewName;
	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
//		if (StringUtils.isBlank(member.getMem_id())) {
//			valid = false;
//			errors.put("mem_id", "회원아이디 누락");
//		}
		if (StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			valid = false;
			errors.put("mem_name", "이름 누락");
		}
		if (StringUtils.isBlank(member.getMem_zip())) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_add1())) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMem_add2())) {
			valid = false;
			errors.put("mem_add2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMem_mail())) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}
		return valid;
	}
}