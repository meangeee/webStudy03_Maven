package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdViewController {
	// view layer : prod/prodView
	// model name : prod
	
	@Inject
	IProdService service;
	@RequestMapping("/prod/prodView.do")
	public String prodView(
			@RequestParam(name="what", required=true)String prod_id,
			Model model
			) {
//		String prod_id = req.getParameter("what");
//		if(StringUtils.isBlank(prod_id)) {
//			resp.sendError(400);
//			return null;
//		}
		ProdVO prod = service.retrieveProd(prod_id);
		model.addAttribute("prod", prod);
		return "prod/prodView";
	}
}
