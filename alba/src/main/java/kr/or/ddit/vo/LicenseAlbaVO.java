package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class LicenseAlbaVO {
	private String al_id;
	private String lic_code;
	private String lic_image;
	private List<LicenseVO> licenseList;
}
