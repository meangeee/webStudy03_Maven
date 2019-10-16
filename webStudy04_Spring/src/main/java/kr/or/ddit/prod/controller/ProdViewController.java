package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodView.do")
public class ProdViewController {
	// view layer : prod/prodView
	// model name : prod
	
	@Inject
	IProdService service;
	@RequestMapping("{what}")
	public String prodView(
			@PathVariable(name="what", required=true)String prod_id,
			Model model
			) {
		
		ProdVO prod = service.retrieveProd(prod_id);
		model.addAttribute("prod", prod);
		return "prod/prodView";
	}
}
