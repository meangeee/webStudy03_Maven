package kr.or.ddit.vo;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of={"al_id", "lic_code"})
@ToString(exclude="lic_image")
public class LicAlbaVO {
	private String al_id;
	private String lic_code;
	private String lic_name;
	private byte[] lic_image;
	private MultipartFile lic_img;
	
	
	public void setLic_img(MultipartFile lic_img) throws IOException {
		this.lic_img = lic_img;
		if(lic_img.getSize() <= 0) return;
		lic_image = lic_img.getBytes();
	}
	
	
	public String getLic_imageBase64(){
		if(lic_image==null) return null;
		return Base64.getEncoder().encodeToString(lic_image);
	}
}
