package kr.or.ddit.vo;

import java.util.Base64;
import java.util.List;

import lombok.Data;

@Data
public class LicenseAlbaVO {
	private String al_id;
	private String lic_code;
	private byte[] lic_image;
	private List<LicenseVO> licenseList;

	public String getLic_base64() {
		if (lic_image == null)
			return null;
		return Base64.getEncoder().encodeToString(lic_image);
	}
}
