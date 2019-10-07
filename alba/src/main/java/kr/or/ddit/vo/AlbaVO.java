package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="al_id")
@ToString(exclude= {"al_spec"})
public class AlbaVO implements Serializable {
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
	private GradeVO grade;
	private LicenseVO licencse;
	
	//요청 파라미터 바인드용
	private String[] lic_codes;
	
	//데이터베이스 조회용
	private String gr_name;
	private List<LicenseAlbaVO> licenseAlbaList;

	private String[] deleteLic_codes;
}
