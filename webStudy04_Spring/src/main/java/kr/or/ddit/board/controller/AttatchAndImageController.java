package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	File saveFolder;
	@PostConstruct
	public void init() {
		application = container.getServletContext();
		saveFolder = new File("d:/saveFiles");
	}
	
	@RequestMapping("/board/download.do")
	public String download(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String what = req.getParameter("what");
		if(StringUtils.isBlank(what)) {
			resp.sendError(400);
			return null;
		}
		Attatch2VO attatch = service.downloadAttatch(Integer.parseInt(what));
		File downloadFile = new File(saveFolder, attatch.getAtt_savename());
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Length", attatch.getAtt_filesize()+"");
		// inline, attatchment
		String filename = attatch.getAtt_filename();
//		filename = URLEncoder.encode(filename, "UTF-8");
		filename = new String(filename.getBytes(), "ISO-8859-1");
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
		try(
			OutputStream os = resp.getOutputStream();
		){
			FileUtils.copyFile(downloadFile, os);
		}
		return null;
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






//package kr.or.ddit.board.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.logging.log4j.core.tools.picocli.CommandLine.Command;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import kr.or.ddit.board.service.IBoardService;
//import kr.or.ddit.mvc.annotation.URIMapping;
//import kr.or.ddit.vo.Attatch2VO;
//import kr.or.ddit.wrapper.MultipartRequestWrapper;
//import kr.or.ddit.wrapper.PartWrapper;
//
//@Controller
//public class AttatchAndImageController {
//	@Inject
//	IBoardService service;
//	
//	File saveFolder = new File("d:/saveFiles");
//	
//	@RequestMapping("/board/download.do")
//	public String download(
//			@RequestParam(required=true) String what) {
//		Attatch2VO attatch = service.downloadAttatch(Integer.parseInt(what));
//		File downloadFile = new File(saveFolder, attatch.getAtt_savename());
////		resp.setContentType("application/octet-stream");
////		resp.setHeader("Content-Length", attatch.getAtt_filesize()+"");
//		// inline, attatchment
//		String filename = attatch.getAtt_filename();
////		filename = URLEncoder.encode(filename, "UTF-8");
//		filename = new String(filename.getBytes(), "ISO-8859-1");
//		resp.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
//		try(
//			OutputStream os = resp.getOutputStream();
//		){
//			FileUtils.copyFile(downloadFile, os);
//		}
//		return null;
//	}
//	
//	@URIMapping(value="/board/imageUpload.do", method=HttpMethod.POST)
//	public String upload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		if(req instanceof MultipartRequestWrapper) {
//			//다운캐스팅가능
//			PartWrapper uploadFile = ((MultipartRequestWrapper) req).getPartWrapper("upload");
//			//실제업로드되었는지
//			if(uploadFile!=null) {
//				//해당폴더의위치를잡아서파일객체
//				String saveFolderURL = "/boardImages";			// 그래서 이걸 받아옴
//				String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
//				//절대경로필요 그래서 위에
//				File saveFolder = new File(saveFolderPath);
//				if(!saveFolder.exists()) saveFolder.mkdirs();		//uplodaFile saveFolder에 저장
//				String savename = UUID.randomUUID().toString();
//				try(
//					InputStream is = uploadFile.getInputStream();	
//				){
////저위치에다가 저 이름으로 만든다					FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename));
//				}
//				//카피를뜨고나면  이걸로 img태그에 src를 쓸테니까
//				String saveURL = req.getContextPath() + saveFolderURL+"/"+savename;
//				Map<String, Object> messageMap = new HashMap<>();
//				messageMap.put("fileName", uploadFile.getFileName());
//				messageMap.put("uploaded", 1);
//				messageMap.put("url", saveURL);
//				resp.setContentType("application/json;charset=UTF-8");
//				ObjectMapper mapper = new ObjectMapper();
//				try(
//					PrintWriter out = resp.getWriter();	
//				){
//					mapper.writeValue(out, messageMap);	
//				}
//			}
//		}
//		return null;
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
