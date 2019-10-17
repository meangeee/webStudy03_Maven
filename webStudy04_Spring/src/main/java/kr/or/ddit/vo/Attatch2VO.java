package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Attatch2VO implements Serializable{
	public Attatch2VO(MultipartFile multipartFile){
		this.partWrapper = multipartFile;
		att_filename = multipartFile.getOriginalFilename();
		att_mime = multipartFile.getContentType();
		att_filesize = multipartFile.getSize();
		att_fancysize = FileUtils.byteCountToDisplaySize(att_filesize);
		att_savename = UUID.randomUUID().toString();
	}
	private Integer att_no;
	private Integer bo_no;
	private String att_filename;
	private String att_savename;
	private String att_mime;
	private Long att_filesize;
	private String att_fancysize;
	private Integer att_downcount;
	
	private MultipartFile partWrapper;
	public void saveFile(File saveFolder) throws IOException {
		try(
			InputStream is = partWrapper.getInputStream();
		){
			FileUtils.copyInputStreamToFile(is, new File(saveFolder, att_savename));
		}
	}
}






