package kr.or.ddit.idol.dao;

import java.util.List;

public interface IIdolSearchDAO {
	//pk를 넘겨서 그 레코드를 가져옴
	public String[] selectIdol(String code);
	//
	public List<String[]> selectIdolList();
	
	
}
