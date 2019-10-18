package kr.or.ddit.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.service.FileUploadService;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {
	@Inject
	FileUploadService service;


	@RequestMapping(method=RequestMethod.GET)
	public String get() {
		return "file/form";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String post(FileVO fileVO,
			RedirectAttributes redirectAttributes
			) throws IllegalStateException, IOException {
//		String saveName = UUID.randomUUID().toString();
//		fileVO.getUploadFile().transferTo(new File(saveFolder, saveName));
//		String saveUrl = clientUrl+saveName;
		
		service.upload(fileVO);
		
//		//꺼낸다음에 자동으로 삭제 근데 왜 이짓을 함?
		redirectAttributes.addFlashAttribute("fileVO", fileVO);
//		redirect 로 fileView.jsp로 이동.
		return "redirect:/fileUpload";
		//redirectAttributes 구조를 사용하기 위해서는 스프링의 FrontController를 타야함.
//		return "redirect:/fileView.jsp";
	}
}

