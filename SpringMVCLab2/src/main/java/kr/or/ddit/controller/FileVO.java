package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileVO implements Serializable{
	private String uploader;
	private MultipartFile uploadFile;


	private String filename;
	private String mime;
	private long size;
	private String savename;
	private String fancysize;
	private int downcount;
	
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
		if(uploadFile.getSize()<=0) return;
		filename = uploadFile.getOriginalFilename();
		mime = uploadFile.getContentType();
		size = uploadFile.getSize();
		savename = UUID.randomUUID().toString();
	}
	
	public void transfer(File saveFolder) throws IllegalStateException, IOException {
		uploadFile.transferTo(new File(saveFolder, savename));
		
	}
}
