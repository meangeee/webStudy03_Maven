package kr.or.ddit.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadTestController {
	@Inject
	WebApplicationContext container;
//	1. 저장위치 (/prodImages)
	String saveFolderURL = "/prodImages";
	private File saveFolder;
	@PostConstruct
	public void init() {
		String saveFolderPath = container.getServletContext().getRealPath(saveFolderURL);
		saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	@RequestMapping(value="/file/upload.do", method=RequestMethod.POST)
	public String upload(
			@RequestParam(required=false) String uploader
			, MultipartFile[] uploadFile
			, HttpServletResponse resp
			, HttpSession session
		) throws IOException {
		System.out.println(uploader);

//		2. 저장명
		if(uploadFile!=null) {
			List<String> saveFiles = new ArrayList<>();
			for(MultipartFile file : uploadFile ) {
				
				String originalFilename = file.getOriginalFilename();
				System.out.println(originalFilename);
				String savename = UUID.randomUUID().toString();
				File saveFile = new File(saveFolder, savename);
				String fileMIME = file.getContentType();
				if(!StringUtils.startsWith(fileMIME, "image/")) {
					resp.sendError(400);
					return null;
				}
				long filesize = file.getSize();
				try(
						InputStream is = file.getInputStream();	
						){
					FileUtils.copyInputStreamToFile(is, saveFile);
				}
				
				String saveFileURL = saveFolderURL+"/"+savename;
				saveFiles.add(saveFileURL);
			}
			
			session.setAttribute("saveFiles", saveFiles);
			session.setAttribute("uploader", uploader);
			
		}
		
		return "redirect:/13/fileUploadForm.jsp";
	}
}





//package kr.or.ddit.common.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import kr.or.ddit.mvc.annotation.CommandHandler;
//import kr.or.ddit.mvc.annotation.HttpMethod;
//import kr.or.ddit.mvc.annotation.URIMapping;
//import kr.or.ddit.wrapper.MultipartRequestWrapper;
//import kr.or.ddit.wrapper.PartWrapper;
//
//@CommandHandler
//public class FileUploadTestController {
//   
//   @URIMapping(value="/file/upload.do",method =HttpMethod.POST)
//   public String upload(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
//      String uploader = req.getParameter("uploader");
////      Part  req.getPart("uploader");
//      System.out.println(uploader);
//      
//      if(req instanceof MultipartRequestWrapper) {
//         MultipartRequestWrapper requestWrapper = (MultipartRequestWrapper)req;
//
//         PartWrapper[] array = requestWrapper.getPartWrappers("uploadFile");
//         //1.어디에 저장할지 서버에 저장할지 디비에 저장할지를 고민해야되는데 latency time -> 지배적으로 시간이 큰게 
//         // 이진데이터가 너무 많은 네트워크를 타야함으로 db에 저장하는 것은  시간이 오래 걸린다.
//         //미들티어에 저장 -> 저장할때 사용했던 메타정보를 디비에 넣어줘야한다.
//         //저장위치() 클라이언트가 webresource의 형태로 사용  /prodImages
//         String saveFolderURL = "/prodImages";
//         String saveFolerPath = req.getServletContext().getRealPath(saveFolderURL);
//         File saveFolder = new File(saveFolerPath);
//         if(!saveFolder.exists()) saveFolder.mkdirs();
//         //2.어떤 이름을 저장할지 
//         List<String> saveFiles = new ArrayList<>();
//         for(PartWrapper partWrapper : array) {
//            
//            String originalFileName =partWrapper.getFileName();
//            System.out.println(originalFileName);
//            String savename = UUID.randomUUID().toString();
//            File saveFile = new File(saveFolder,savename);
//            
//            String fileMIME = partWrapper.getContentType();
//            if(!StringUtils.startsWith(fileMIME, "image/") ) {
//               resp.sendError(400);
//               return null;
//            }
//            long filesize = partWrapper.getSize();
//            try(
//                  InputStream is = partWrapper.getInputStream();
//                  ){
//               FileUtils.copyInputStreamToFile(is, saveFile);
//            }
//            String saveFileURL =saveFolderURL+"/"+savename;   
//            saveFiles.add(saveFileURL);
//         }
//         
//         req.getSession().setAttribute("saveFiles", saveFiles);
//         req.getSession().setAttribute("uploader", uploader);
//      }
//      
//      return "redirect:/13/fileUploadForm.jsp";
//   } 
//
//}