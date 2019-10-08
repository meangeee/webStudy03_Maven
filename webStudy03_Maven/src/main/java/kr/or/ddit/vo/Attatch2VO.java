package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Attatch2VO implements Serializable{
	private Integer att_no;
	private Integer bo_no;
	private String att_filename;
	private String att_savename;
	private String att_mime;
	private long att_filesize;
	private String att_fancysize;
	private Integer att_downcount;
}
