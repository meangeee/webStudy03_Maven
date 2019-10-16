package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController{
	@Inject
	IMemberService service;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String doGet(){
		
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
	
	@RequestMapping(method=RequestMethod.POST)
	public String doPost(
			@Valid @ModelAttribute("member") MemberVO member,
			Errors errors, 
			Model model) {
		
		//true면 에러가 있음
		boolean valid = !errors.hasErrors();
				
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
		model.addAttribute("message", message);
		//dispatch방식으로 기존 데이터를 갖고 갈 수 있다
			return viewName;
	}

}
