package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberInsertController{
	IMemberService service = MemberServiceImpl.getInstance();
	
	
	@URIMapping(value="/member/memberInsert.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String viewName = "member/memberForm";
		
		return viewName;
	}
	
	//1 보내주는 데이터를 받겠죠 근데 한두개가 아니여 그걸 뭐로 받어?
	//vo로 받어
	//membervo 생성 그 안에 데이터 채워넣음
	//membervo를 계속 갖고 다님
	//그렇다면 어딘가에 공유를 해야겠지
	//데이터를 다 받았다라면 검증을 해야겠죠 검증 기준은 member table에 schema에 있음
	//1. 뭔 데이트가 2 입력된 데이터가 뭔지 3 길이가 얼마난지 - 검증룰에따라 검증함
	//통과 등록을 해야함
	//불통과 다시 제대로된 데이터를 입력 할 수 있는 곳으로 보낸다 memberForm 갖고가야하는 데이터 2개
	//1.기존데이터 2.ㅁ ㅜ엇이 잘못되었는지 정보. 근데 그게 한두개가 아님 map활용 updateServlet 보세요
	//가입해야져 뒤에있는 service를 사용 = 의존관ㄱㅖ 활용]
	//로직선택 -> 돌아오는 결과 createMember
	//리턴결과에따라 판단해야하는 3가지 pk중복(아이디중복)->memberform 에러 메시지도,성공,실패
	//failed는 가입버튼을 다시 누를 수 있는 곳 == memberForm 가져갈꺼1.메시지2.기존입력데이터
	//ok 로그인할 수 있는 곳으로 보낸다 loginForm or index
	//공통점 다 어딘가로 가야함. 차이점 가는 방식이 다름.
	//ok redirection
	
	@URIMapping(value="/member/memberInsert.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();
		//미리공유 왜? 만약 가입하다가 검증에 실패. 그럴때마다 기존 데이터를 가지고 다녀야 해서 그러나 아직까진 비어있음
		req.setAttribute("member", member);
		
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		//검증 여러개가 안될 수 있으니까
		Map<String, String> errors = new HashMap<String, String>();
		//통과 또 안될 수 있으니까
		req.setAttribute("errors", errors);
		
		boolean valid = validate(member, errors);
		//------------update까지 똑같음
		
		
		String viewName = "member/memberForm";
		String message = null;
		boolean redirect = false;
		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				message = "아이디 중복";
				break;
			case FAILED:
				message = "서버 오류";
				break;
			default:
//				- OK   : redirect -> welcome page
				message = "수정 성공";
				redirect = true;
				viewName = "redirect:/";
				break;
			}

		} else {
			viewName = "member/memberForm";
		}
		req.setAttribute("message", message);
		//dispatch방식으로 기존 데이터를 갖고 갈 수 있다
		if(redirect) {
			return "redirect:/"+viewName;
		}else {
			return viewName;
		}
	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", "회원아이디 누락");
		}
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
