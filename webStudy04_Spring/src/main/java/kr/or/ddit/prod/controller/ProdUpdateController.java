package kr.or.ddit.prod.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.hints.UpdateHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {
	@Inject
	IProdService service;

	@RequestMapping(method = RequestMethod.GET)
	public String updateForm(@RequestParam(name = "what", required = true) String prod_id, Model model) {
		ProdVO prod = service.retrieveProd(prod_id);
		model.addAttribute("vo", prod);
		return "prod/prodForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String update(
			@Validated(UpdateHint.class) @ModelAttribute("prod") ProdVO prod, Model model,
			Errors errors,
			@RequestParam(required = false, name = "prod_image") MultipartFile partWrapper) {

		model.addAttribute("errors", errors); // 검증에 통과하지못했을경우마다 출력하기위해서

		boolean vaild = !errors.hasErrors();
		String viewName = null;
		String message = null;
		if (vaild) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case FAILED: // 서버사이드
				message = "서버오류";
				viewName = "prod/prodForm";
				break;

			default:
				// -OK : redirect -> welcome page //클라이언트사이드
				message = "수정성공";
				viewName = "redirect:/prod/" + prod.getProd_id();
				break;
			}
		} else {
			viewName = "prod/prodForm";
		}

		model.addAttribute("message", message);

		return viewName;
	}

	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(prod.getProd_id())) {
			valid = false;
			errors.put("prod_id", "상품아이디 누락");
		}
		if (StringUtils.isBlank(prod.getProd_name())) {
			valid = false;
			errors.put("prod_name", "상품명 누락");
		}
		if (StringUtils.isBlank(prod.getProd_lgu())) {
			valid = false;
			errors.put("prod_lgu", "분류코드누락");
		}
		// if (StringUtils.isBlank(member.getMem_zip())) {
		// valid = false;
		// errors.put("mem_zip", "우편번호 누락");
		// }
		// if (StringUtils.isBlank(member.getMem_add1())) {
		// valid = false;
		// errors.put("mem_add1", "주소1 누락");
		// }
		// if (StringUtils.isBlank(member.getMem_add2())) {
		// valid = false;
		// errors.put("mem_add2", "주소2 누락");
		// }
		// if (StringUtils.isBlank(member.getMem_mail())) {
		// valid = false;
		// errors.put("mem_mail", "메일 누락");
		// }
		return valid;
	}

}