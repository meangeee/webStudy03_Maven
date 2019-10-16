package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController {
	@Inject
	IProdService service;

	@RequestMapping(method = RequestMethod.GET)
	public String insertForm() {
		return "prod/prodForm";

	}

//	JSR-303 방식의 객체 검증 단계
//	1. javax.validation 의존성 추가
//	2. hibernate-validator 의존성 추가
//	3. 검증 결과 메시지 커스터마이징(message_locale.properties)
//	4. @Valid, @Validated(Validation hint)를 사용해
//		검증 대상이 되는 커맨드 오브젝트를 검증하도록 설정.
//		1) 각 업무를 대상으로 한 hint interface구현
//		2) 공통 검증 룰을 적용할 hint는 Default 상속 구조.
//	5. 검증 대상이 되는 Domain layer 에 검증 어노테이션으로 룰 설정.
//	6. 검증 대상 객체 다음에, Errors/BindingResult를 사용하여, 검증 결과를 받음.
//	7. view layer 에서 검증 결과 메시지 출력.
//	   spring-form 태그 라이브러리 사용(form:form, form:errors)
	@RequestMapping(method = RequestMethod.POST)
	public String insert(
			@Validated(InsertHint.class) 
			@ModelAttribute("prod") ProdVO prod, BindingResult errors,
			Model model) throws IOException {

		boolean vaild = !errors.hasErrors();

		String viewName = null;
		String message = null;
		if (vaild) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAILED: // 서버사이드
				message = "서버오류";
				viewName = "prod/prodForm";
				break;

			default:
				// -OK : redirect -> welcome page //클라이언트사이드
				message = "추가성공";
				viewName = "redirect:/prod/"+ prod.getProd_id();
				break;
			}
		} else {
			viewName = "prod/prodForm";
		}

		model.addAttribute("message", message);

		return viewName;
	}

}