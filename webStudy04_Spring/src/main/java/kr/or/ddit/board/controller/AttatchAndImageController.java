package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.Attatch2VO;

@Controller
public class AttatchAndImageController {
	@Inject
	IBoardService service;
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping("/board/download.do")
	public String download(@RequestParam(required=true) int what,
			Model model) {
		Attatch2VO attatch = service.downloadAttatch(what);
		model.addAttribute("attatch", attatch);
		return "downloadView";
	}
	
	
	@RequestMapping(value="/board/imageUpload.do", 
					method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> upload(
				@RequestPart(required=true, name="upload")	MultipartFile uploadFile
			) throws IOException {
		Map<String, Object> messageMap = new HashMap<>();
		if(uploadFile!=null) {
			String saveFolderURL = "/boardImages";
			String saveFolderPath = application.getRealPath(saveFolderURL);
			File saveFolder = new File(saveFolderPath);
			if(!saveFolder.exists()) saveFolder.mkdirs();
			String savename = UUID.randomUUID().toString();
			try(
				InputStream is = uploadFile.getInputStream();	
			){
				FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename));
			}
			String saveURL = application.getContextPath() + saveFolderURL+"/"+savename;
			messageMap.put("fileName", uploadFile.getOriginalFilename());
			messageMap.put("uploaded", 1);
			messageMap.put("url", saveURL);
		}
		return messageMap;
	}
}











