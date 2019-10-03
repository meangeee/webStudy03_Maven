package kr.or.ddit.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.Command;

import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;

@Command
public class FileUploadTestController {
	@URIMapping(value="/file/upload.do", method=HttpMethod.POST)
	public String upload(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String uploader = req.getParameter("uploader");
		System.out.println(uploader);
		Part filePart = req.getPart("uploadFile");
		
		//1. 저장위치(/buyerImages)
		String saveFolderURL = "/buyerImages";
		String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
		File saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) saveFolder.mkdirs(); //파일이 없으면 만들기
		
		//2. 저장명
//	    Content-Disposition: form-data; name="uploadFile"; filename=""
		String header = filePart.getHeader("Content-Disposition");
		int firstIdx = header.indexOf("filename");
		int secondIdx = header.indexOf("=", firstIdx);
		String originalFileName = header.substring(secondIdx + 1).replace('"', ' ').trim();
		System.out.println(originalFileName);
		
		//저장하기
		String savename = UUID.randomUUID().toString();
		File saveFile = new File(saveFolder, savename);
		String fileMIME = filePart.getContentType();
		if(!StringUtils.startsWith(fileMIME, "image/")) {
			resp.sendError(400);
			return null;
		}
		
		long fileSize = filePart.getSize();
		
		try(
			InputStream is = filePart.getInputStream();
		){
			FileUtils.copyInputStreamToFile(is, saveFile);
		}
		String saveFileURL = saveFolderURL + "/" + savename;
		req.getSession().setAttribute("saveFileURL", saveFileURL);
		req.getSession().setAttribute("uploader", uploader);
		return "redirect:/file/fileUploadForm.jsp";
	}
}




































