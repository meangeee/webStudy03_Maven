package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.hints.UpdateHint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="bo_no")
public class Board2VO implements Serializable{
	public Board2VO(int bo_no){
		this.bo_no = bo_no;
	}
	public Board2VO(int bo_no, String bo_pass){
		this.bo_no = bo_no;
		this.bo_pass = bo_pass;
	}
	private Integer rnum;
	private Integer level; // 계층 구조상에서 글의 깊이 1 : 원글, >1: 답글
	@NotNull(groups=UpdateHint.class)
	private Integer bo_no;
	@NotBlank
	private String board_type;
	private String board_name;
	@NotBlank
	private String bo_title;
	@NotBlank
	private String bo_writer;
	private String bo_date;
	private String bo_content;
	@NotBlank
	private String bo_pass;
	@NotBlank
	private String bo_ip;
	private Integer bo_hit;
	private Integer bo_like;
	private Integer bo_parent;
	
	private List<Reply2VO> replyList;
	private List<Attatch2VO> attatchList;
	private MultipartFile[] bo_file;
	public void setBo_file(MultipartFile[] bo_file) {
		this.bo_file = bo_file;
		if(bo_file==null || bo_file.length==0) return;
		attatchList = new ArrayList<>();
		for(MultipartFile tmp : bo_file) {
			//  비어있는 파일 필터링
			if(tmp.getSize()<=0) continue;
			attatchList.add(new Attatch2VO(tmp));
		}
	}
	private Integer[] delAttaches;
	
	private Integer attNoStart;
	
}




