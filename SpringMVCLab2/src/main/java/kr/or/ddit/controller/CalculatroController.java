package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller //2가지 의미가 있음 bean으로 등록 됨 handler mapper에 의해 수집된다
@RequestMapping("/cal.do") //어떤 요청을 처리할것인가 필터링이라고 보면 됨 방법 중 하나 주소
public class CalculatroController {
	@RequestMapping(method=RequestMethod.GET) //필터링 방법중에하나 메서드
	public String get() {
		return "calForm";
	}
	
	public ModelAndView prePost(@RequestParam(required=true)int leftOp, 
								@RequestParam(required=false, defaultValue="-1") int rightOp) {
		return null;
	}
	
	
	//필요한건 handler adapter에게 받아라. argument의 형태로
	//두가지 정보를 받아야함 model과 view를 받아야함
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView post(@ModelAttribute("calVO") CalculatorVO calVO,
			@RequestParam(name="user", required=false) String who) {
		int result = calVO.getLeftOp() + calVO.getRightOp();
		calVO.setResult(result);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calForm");
		return mav;
	}
	
	//? 뭐하는애라구요?
	// 4가지로 식별 할 수 있어요
	//request에 ~헤더가 이렇게 들어오는지 확인함 이방식으로 들어왔다면 위에있는 핸들러가 아니라 밑에있는 핸들러를 사용
	//지금은 json으로 마샬링 됨.produces에 따라서 마샬링됨
	//model이면서 동시에 view 그래서 @ResponseBody
	@RequestMapping(method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody //json으로 마샬링하기 위한 어노테이션
	public CalculatorVO postAjax(@ModelAttribute("calVO") CalculatorVO calVO,
			@RequestParam(name="user", required=false) String who) {
		post(calVO, who);
		System.out.println(who);
		return calVO;
	}
}

