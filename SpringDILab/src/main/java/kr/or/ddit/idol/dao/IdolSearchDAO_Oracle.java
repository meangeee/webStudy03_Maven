package kr.or.ddit.idol.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdolSearchDAO_Oracle implements IIdolSearchDAO{

	
	public IdolSearchDAO_Oracle() {
		super();
		System.out.printf(" %s 객체 생성\n", getClass().getSimpleName());
	}
	
	Map<String, String[]> idolMap = new HashMap<String, String[]>();
	{
		idolMap.put("I001", new String[] {"SES", "이진", "슈", "효리"});
		idolMap.put("I002", new String[] {"COOL", "유리", "김성수", "이재훈"});
	}
	
	@Override
	public String[] selectIdol(String code) {
		return idolMap.get(code);
	}

	@Override
	public List<String[]> selectIdolList() {
		//map안에 있는 엔트리로 list 생성
		List<String[]> list = new ArrayList<String[]>(idolMap.values());
		return list;
	}

}















