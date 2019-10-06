package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class AlbaVO {
	private String al_address ;
	private int al_age     ;
	private String al_btype   ;
	private String al_career  ;
	private String al_desc    ;
	private String al_gen     ;
	private String al_hp      ;
	private String al_id      ;
	private String al_mail    ;
	private String al_name    ;
	private String al_spec    ;
	private String gr_code	  ;
	private List<LicenseAlbaVO> licenseAlbaList;
	private GradeVO grade;
	private LicenseVO licencse;
}
